package com.digitalageexperts.csrtracker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * The persistent class for the csr database table.
 * 
 */
@Entity
@NamedQuery(name="Csr.findAll", query="SELECT c FROM Csr c")
@Table(name="Csr")
public class Csr implements Serializable {
	public Csr(String csrnumber, String csrtype, String description, Date dueDate, String fte, String laborCategory,
			String location, String requiredCerts, String skillLevel, String skillsMandatory, String skillsOptional) {
		super();
		this.csrnumber = csrnumber;
		this.csrtype = csrtype;
		this.description = description;
		this.dueDate = dueDate;
		this.fte = fte;
		this.laborCategory = laborCategory;
		this.location = location;
		this.requiredCerts = requiredCerts;
		this.skillLevel = skillLevel;
		this.skillsMandatory = skillsMandatory;
		this.skillsOptional = skillsOptional;
	}

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String csrnumber;

	private String csrtype;

	@Temporal(TemporalType.DATE)
	@Column(name="date_added")
	private Date dateAdded;

	@Temporal(TemporalType.DATE)
	@Column(name="date_released")
	private Date dateReleased;

	@Lob
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	private String fte;

	@Column(name="labor_category")
	private String laborCategory;

	private String location;

	@Column(name="required_certs")
	private String requiredCerts;

	@Column(name="skill_level")
	private String skillLevel;

	@Lob
	@Column(name="skills_mandatory")
	private String skillsMandatory;

	@Lob
	@Column(name="skills_optional")
	private String skillsOptional;

	public Csr() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCsrnumber() {
		return this.csrnumber;
	}

	public void setCsrnumber(String csrnumber) {
		this.csrnumber = csrnumber;
	}

	public String getCsrtype() {
		return this.csrtype;
	}

	public void setCsrtype(String csrtype) {
		this.csrtype = csrtype;
	}

	public Date getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateReleased() {
		return this.dateReleased;
	}

	public void setDateReleased(Date dateReleased) {
		this.dateReleased = dateReleased;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getFte() {
		return this.fte;
	}

	public void setFte(String fte) {
		this.fte = fte;
	}

	public String getLaborCategory() {
		return this.laborCategory;
	}

	public void setLaborCategory(String laborCategory) {
		this.laborCategory = laborCategory;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRequiredCerts() {
		return this.requiredCerts;
	}

	public void setRequiredCerts(String requiredCerts) {
		this.requiredCerts = requiredCerts;
	}

	public String getSkillLevel() {
		return this.skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getSkillsMandatory() {
		return this.skillsMandatory;
	}

	public void setSkillsMandatory(String skillsMandatory) {
		this.skillsMandatory = skillsMandatory;
	}

	public String getSkillsOptional() {
		return this.skillsOptional;
	}

	public void setSkillsOptional(String skillsOptional) {
		this.skillsOptional = skillsOptional;
	}
	
	

}
	
