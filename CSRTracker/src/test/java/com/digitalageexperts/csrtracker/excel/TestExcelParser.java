package com.digitalageexperts.csrtracker.excel;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import com.digitalageexperts.csrtracker.excel.ParseCsrExcel;
import com.digitalageexperts.csrtracker.model.Csr;


public class TestExcelParser {
	
	@Test
	public void testExcelParser()
	{
		System.out.println("starting test");
		
		try
		{
			InputStream reader = this.getClass().getClassLoader().getResourceAsStream("testCSRFileTOTES.xlsx");
			String ext = "xlsx";
			
			parseExcelFile(reader, ext);
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		if(args.length != 1)
		{
			System.out.println("Provide path to the a file to parse");
		}
		
		try
		{
			FileInputStream reader = new FileInputStream(args[0]);
			String ext = args[0].substring(args[0].lastIndexOf("."));
			
			parseExcelFile(reader, ext);
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	private static void parseExcelFile(InputStream reader, String ext) throws Exception {
		ParseCsrExcel parser = new ParseCsrExcel(reader,ext);
		
		while (parser.HasNextItem()) {
			Csr item = parser.ReadNextItem();
			System.out.println("========================================================================");
			System.out.println("Item:"+(parser.GetNextRowNumber()-1));
			System.out.println("========================================================================");
			System.out.println("CsrNumber: "+item.getCsrnumber());
			System.out.println("CsrType: "+item.getCsrnumber());
			System.out.println("Description: "+item.getDescription());
			System.out.println("Fte: "+item.getFte());
			System.out.println("Role/Skill: "+item.getLaborCategory());
			System.out.println("Level: "+item.getSkillLevel());
			System.out.println("RequiredCerts: "+item.getRequiredCerts());
			System.out.println("Mandated Skills: "+item.getSkillsMandatory());
			System.out.println("Optional Skills: "+item.getSkillsOptional());
			
			System.out.println("Location: "+item.getLocation());
			System.out.println("Resumes Due Dates: "+item.getDueDate().toString());
			System.out.println();
			System.out.println();
		}
	}

}
