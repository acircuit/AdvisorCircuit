package org.AC.dto;

import java.sql.Timestamp;

public class UserMessageDTO {
	
	int messageId;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	int sessionId;
	String userMessage;
	Timestamp userMessageTime;
	String userMessageTimeString;
	String status;
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public Timestamp getUserMessageTime() {
		return userMessageTime;
	}
	public void setUserMessageTime(Timestamp userMessageTime) {
		this.userMessageTime = userMessageTime;
	}
	public String getUserMessageTimeString() {
		return userMessageTimeString;
	}
	public void setUserMessageTimeString(String userMessageTimeString) {
		this.userMessageTimeString = userMessageTimeString;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
