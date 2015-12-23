package com.digitalageexperts.csrtracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "CsrTracking")
public class CsrTrackingItem {
	
	private String _csrNumber;
	private String _csrType;
	private String _description;
	private String _fte;
	private String _roleSkill;
	private String _level;
	private String _requiredCerts;
	private String _skillsMandated;
	private String _skillsOptional;
	private String _clearance;
	private String _location;
	private String _resumeDueDate;
	
	@DynamoDBHashKey
	public String getCsrNumber() { return _csrNumber; }
    public void setCsrNumber(String CsrNumber) { this._csrNumber = CsrNumber; }
	
    @DynamoDBAttribute
    public String getCsrType() { return _csrType; }
    public void setCsrType(String CsrType) { this._csrType = CsrType; }
    
    @DynamoDBAttribute
    public String getDescription() { return _description; }
    public void setDescription(String Description) { this._description = Description; }
    
    @DynamoDBAttribute
    public String getFte() { return _fte; }
    public void setFte(String Fte) { this._fte = Fte; }
    
    @DynamoDBAttribute
    public String getRoleSkill() { return _roleSkill; }
    public void setRoleSkill(String RoleSkill) { this._roleSkill = RoleSkill; }
    
    @DynamoDBAttribute
    public String getLevel() { return _level; }
    public void setLevel(String Level) { this._level = Level; }
    
    @DynamoDBAttribute
    public String getRequiredCerts() { return _requiredCerts; }
    public void setRequiredCerts(String RequiredCerts) { this._requiredCerts = RequiredCerts; }
    
    @DynamoDBAttribute
    public String getSkillsMandated() { return _skillsMandated; }
    public void setSkillsMandated(String SkillsMandated) { this._skillsMandated = SkillsMandated; }
    
    @DynamoDBAttribute
    public String getSkillsOptional() { return _skillsOptional; }
    public void setSkillsOptional(String SkillsOptional) { this._skillsOptional = SkillsOptional; }
    
    @DynamoDBAttribute
    public String getClearance() { return _clearance; }
    public void setClearance(String Clearance) { this._clearance = Clearance; }
    
    @DynamoDBAttribute
    public String getLocation() { return _location; }
    public void setLocation(String Location) { this._location = Location; }
    
    @DynamoDBAttribute
    public String getResumeDueDate() { return _resumeDueDate; }
    public void setResumeDueDate(String ResumeDueDate) { this._resumeDueDate = ResumeDueDate; }
}
