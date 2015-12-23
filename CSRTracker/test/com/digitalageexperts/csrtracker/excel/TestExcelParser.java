package com.digitalageexperts.csrtracker.excel;

import java.io.FileInputStream;

import com.digitalageexperts.csrtracker.CsrItem;
import com.digitalageexperts.csrtracker.excel.ParseCsrExcel;


public class TestExcelParser {

	public static void main(String[] args) {
		if(args.length != 1)
		{
			System.out.println("Provide path to the a file to parse");
		}
		
		try
		{
			FileInputStream reader = new FileInputStream(args[0]);
			
			ParseCsrExcel parser = new ParseCsrExcel(reader,args[0].substring(args[0].lastIndexOf(".")));
			
			while (parser.HasNextItem()) {
				CsrItem item = parser.ReadNextItem();
				System.out.println("========================================================================");
				System.out.println("Item:"+(parser.GetNextRowNumber()-1));
				System.out.println("========================================================================");
				System.out.println("CsrNumber: "+item.getCsrNumber());
				System.out.println("CsrType: "+item.getCsrType());
				System.out.println("Description: "+item.getDescription());
				System.out.println("Fte: "+item.getFte());
				System.out.println("Role/Skill: "+item.getRoleSkill());
				System.out.println("Level: "+item.getLevel());
				System.out.println("RequiredCerts: "+item.getRequiredCerts());
				System.out.println("Mandated Skills: "+item.getSkillsMandated());
				System.out.println("Optional Skills: "+item.getSkillsOptional());
				System.out.println("Required Clearance: "+item.getClearance());
				System.out.println("Location: "+item.getLocation());
				System.out.println("Resumes Due Dates: "+item.getResumeDueDate().toString());
				System.out.println();
				System.out.println();
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
