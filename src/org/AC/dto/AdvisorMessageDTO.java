package org.AC.dto;

import java.sql.Timestamp;

public class AdvisorMessageDTO {
	
	int messageId;
	int sessionId;
	String advisorMessage;
	Timestamp advisorMessageTime;
	String advisorMessageTimeString;
	String status;
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getAdvisorMessage() {
		return advisorMessage;
	}
	public void setAdvisorMessage(String advisorMessage) {
		this.advisorMessage = advisorMessage;
	}
	public Timestamp getAdvisorMessageTime() {
		return advisorMessageTime;
	}
	public void setAdvisorMessageTime(Timestamp advisorMessageTime) {
		this.advisorMessageTime = advisorMessageTime;
	}
	public String getAdvisorMessageTimeString() {
		return advisorMessageTimeString;
	}
	public void setAdvisorMessageTimeString(String advisorMessageTimeString) {
		this.advisorMessageTimeString = advisorMessageTimeString;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
