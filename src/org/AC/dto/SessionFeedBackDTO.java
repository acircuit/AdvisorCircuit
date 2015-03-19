package org.AC.dto;

public class SessionFeedBackDTO {
	
	private int Id;
	private int sessionId;
	private String userBody;
	private String userSubject;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getUserBody() {
		return userBody;
	}
	public void setUserBody(String userBody) {
		this.userBody = userBody;
	}
	public String getUserSubject() {
		return userSubject;
	}
	public void setUserSubject(String userSubject) {
		this.userSubject = userSubject;
	}

	public Boolean getUserApproval() {
		return userApproval;
	}
	public void setUserApproval(Boolean userApproval) {
		this.userApproval = userApproval;
	}
	public Boolean getAdvisorApproval() {
		return advisorApproval;
	}
	public void setAdvisorApproval(Boolean advisorApproval) {
		this.advisorApproval = advisorApproval;
	}
	public String getUserFile() {
		return userFile;
	}
	public void setUserFile(String userFile) {
		this.userFile = userFile;
	}
	public String getAdvisorBody() {
		return advisorBody;
	}
	public void setAdvisorBody(String advisorBody) {
		this.advisorBody = advisorBody;
	}
	public String getAdvisorSubject() {
		return advisorSubject;
	}
	public void setAdvisorSubject(String advisorSubject) {
		this.advisorSubject = advisorSubject;
	}


	public String getAdvisorFile() {
		return advisorFile;
	}
	public void setAdvisorFile(String advisorFile) {
		this.advisorFile = advisorFile;
	}
	private Boolean userApproval;
	private String userFile;
	private String advisorBody;
	private String advisorSubject;
	private Boolean advisorApproval;
	private String advisorFile;
}
