package com.digitalageexperts.csrtracker;

import java.time.LocalDate;



public class CsrItem {
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
	private LocalDate _resumeDueDate;
	
	
	public CsrItem(String CsrNumber, String CsrType, String Description, String Fte, String RoleSkill,
			String Level, String RequiredCerts, String SkillsMandated, String SkillsOptional,
			String Clearance, String Location, LocalDate ResumeDueDate)
	{
		if(CsrNumber == null || CsrNumber.equals(""))
			throw new IllegalArgumentException("CsrNumber cannot be null or empty");
		_csrNumber = CsrNumber;
		if(CsrType == null || CsrType.equals(""))
			throw new IllegalArgumentException("CsrType cannot be null or empty");
		_csrType = CsrType;
		if(Description == null || Description.equals(""))
			throw new IllegalArgumentException("Description cannot be null or empty");
		_description = Description;
		if(Fte == null || Fte.equals(""))
			throw new IllegalArgumentException("Fte cannot be null or empty");
		_fte = Fte;
		if(RoleSkill == null || RoleSkill.equals(""))
			throw new IllegalArgumentException("RoleSkill cannot be null or empty");
		_roleSkill = RoleSkill;
		if(Level == null || Level.equals(""))
			throw new IllegalArgumentException("Level cannot be null or empty");
		_level = Level;
		_requiredCerts = RequiredCerts;
		if(SkillsMandated == null || SkillsMandated.equals(""))
			throw new IllegalArgumentException("SkillsMandated cannot be null or empty");
		_skillsMandated = SkillsMandated;
		if(SkillsOptional == null || SkillsOptional.equals(""))
			throw new IllegalArgumentException("SkillsOptional cannot be null or empty");
		_skillsOptional = SkillsOptional;
		if(Clearance == null || Clearance.equals(""))
			throw new IllegalArgumentException("Clearance cannot be null or empty");
		_clearance = Clearance;
		if(Location == null || Location.equals(""))
			throw new IllegalArgumentException("Location cannot be null or empty");
		_location = Location;
		if(ResumeDueDate == null || ResumeDueDate.equals(""))
			throw new IllegalArgumentException("ResumeDueDate cannot be null or empty");
		_resumeDueDate = ResumeDueDate;
	}
	
	public String getCsrNumber()
	{ return _csrNumber;}
	public String getCsrType()
	{ return _csrType;}
	public String getDescription()
	{ return _description;}
	public String getFte()
	{ return _fte;}
	public String getRoleSkill()
	{ return _roleSkill;}
	public String getLevel()
	{ return _level;}
	public String getRequiredCerts()
	{ return _requiredCerts;}
	public String getSkillsMandated()
	{ return _skillsMandated;}
	public String getSkillsOptional()
	{ return _skillsOptional;}
	public String getClearance()
	{ return _clearance;}
	public String getLocation()
	{ return _location;}
	public LocalDate getResumeDueDate()
	{ return _resumeDueDate;}
	
	
	/*
	public Item getItem()
	{
		Item item = new Item()
                .withPrimaryKey("CsrNumber", CsrNumber)
                .withString("CsrType", CsrType)
                .withString("Description", Description)
                .withString("Fte", Fte)
                .withString("RoleSkill", RoleSkill)
                .withString("Level", Level)
                .withString("RequiredCerts", RequiredCerts)
                .withString("SkillsMandated", SkillsMandated)
                .withString("SkillsOptional", SkillsOptional)
                .withString("Clearance", Clearance)
                .withString("Location", Location)
                .withDate("ResumeDueDate", ResumeDueDate);
		return Item;
		
	}
	
	
	*/
}
