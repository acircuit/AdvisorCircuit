package org.AC.dto;

public class AdvisorEducationDTO {
	int advisorId;
	private String education;
	public int getAdvisorId() {
		return advisorId;
	}
	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String type;
}
