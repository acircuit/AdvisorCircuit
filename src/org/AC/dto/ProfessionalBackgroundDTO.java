package org.AC.dto;

public class ProfessionalBackgroundDTO {
	
	private int advisorId;
	private int profId;
	public int getProfId() {
		return profId;
	}
	public void setProfId(int profId) {
		this.profId = profId;
	}
	private String company;
	private String designation;
	private String duration;
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	private String description;
	private String[] descriptionArray;
	
	public String[] getDescriptionArray() {
		return descriptionArray;
	}
	public void setDescriptionArray(String[] descriptionArray) {
		this.descriptionArray = descriptionArray;
	}
	public int getAdvisorId() {
		return advisorId;
	}
	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
