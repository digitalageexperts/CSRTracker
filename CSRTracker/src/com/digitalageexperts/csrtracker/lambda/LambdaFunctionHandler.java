package com.digitalageexperts.csrtracker.lambda;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.digitalageexperts.csrtracker.CsrItem;
import com.digitalageexperts.csrtracker.dynamodb.CsrTrackingItem;
import com.digitalageexperts.csrtracker.excel.ParseCsrExcel;

public class LambdaFunctionHandler implements RequestHandler<S3Event, Object> {

	private final String XLS_TYPE = (String) "xls";
	private final String XLSX_TYPE = (String) "xlsx";
	
    @Override
    public Object handleRequest(S3Event input, Context context) {
        context.getLogger().log("Input: " + input);
        
        S3EventNotificationRecord record = input.getRecords().get(0);

        String srcBucket = record.getS3().getBucket().getName();
        // Object key may have spaces or unicode non-ASCII characters.
        String srcKey = record.getS3().getObject().getKey()
                .replace('+', ' ');
        try {
			srcKey = URLDecoder.decode(srcKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			context.getLogger().log(e.getMessage());
			return "";
		}
        
        Matcher matcher = Pattern.compile(".*\\.([^\\.]*)").matcher(srcKey);
        if (!matcher.matches()) {
            System.out.println("Unable to infer file type for key "
                    + srcKey);
            return "";
        }
        String fileType = matcher.group(1);
        if (!(XLS_TYPE.equalsIgnoreCase(fileType)) && !(XLSX_TYPE.equalsIgnoreCase(fileType))) {
            System.out.println("Skipping non-excel " + srcKey);
            return "";
        }
        
        AmazonS3 s3Client = new AmazonS3Client();
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(
                srcBucket, srcKey));
        InputStream objectData = s3Object.getObjectContent();
        
        ParseCsrExcel parser = null;
        
        try {
        	parser = new ParseCsrExcel(objectData,fileType);
		} catch (Exception e) {
			context.getLogger().log(e.getMessage());
			return "";
		}
        
        AmazonDynamoDB dynamo = new AmazonDynamoDBClient();
		DynamoDBMapper mapper = new DynamoDBMapper(dynamo);
        
        while (parser.HasNextItem()) 
        {
			CsrItem item;
			try {
				item = parser.ReadNextItem();
			} catch (Exception e) {
				context.getLogger().log(e.getMessage());
				continue;
			}
			
			CsrTrackingItem dbitem = new CsrTrackingItem();
			dbitem.setCsrNumber(item.getCsrNumber());
			dbitem.setCsrType(item.getCsrType());
			dbitem.setDescription(item.getDescription());
			dbitem.setFte(item.getFte());
			dbitem.setRoleSkill(item.getRoleSkill());
			dbitem.setLevel(item.getLevel());
			dbitem.setRequiredCerts(item.getRequiredCerts());
			dbitem.setSkillsMandated(item.getSkillsMandated());
			dbitem.setSkillsOptional(item.getSkillsOptional());
			dbitem.setClearance(item.getClearance());
			dbitem.setLocation(item.getLocation());
			dbitem.setResumeDueDate(item.getResumeDueDate().toString());
			
			mapper.save(dbitem);
		}
        
        return null;
    }

}
