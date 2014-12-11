package org.AC.dto;

import java.sql.Timestamp;

public class MessageDTO {
	
	int messageId;
	String advisorMessage;
	Timestamp advisorMessageTime;
	int advisorId;
	String adminMessage;
	Timestamp adminMessageTime;
	//These two data types are used to show the time in the chat window
	String advisorMessageTimeString;
	String adminMessageTimeString;
	
	public String getAdvisorMessageTimeString() {
		return advisorMessageTimeString;
	}
	public void setAdvisorMessageTimeString(String advisorMessageTimeString) {
		this.advisorMessageTimeString = advisorMessageTimeString;
	}
	public String getAdminMessageTimeString() {
		return adminMessageTimeString;
	}
	public void setAdminMessageTimeString(String adminMessageTimeString) {
		this.adminMessageTimeString = adminMessageTimeString;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
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
	public int getAdvisorId() {
		return advisorId;
	}
	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}
	public String getAdminMessage() {
		return adminMessage;
	}
	public void setAdminMessage(String adminMessage) {
		this.adminMessage = adminMessage;
	}
	public Timestamp getAdminMessageTime() {
		return adminMessageTime;
	}
	public void setAdminMessageTime(Timestamp adminMessageTime) {
		this.adminMessageTime = adminMessageTime;
	}
}
