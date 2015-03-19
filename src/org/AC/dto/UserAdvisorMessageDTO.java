package org.AC.dto;

import java.sql.Timestamp;

public class UserAdvisorMessageDTO {
	private String userMessage;
	private String advisorMessage;
	private Timestamp userMessageTime;
	private Timestamp advisorMessageTime;
	private String userMessageTimeString;
	private String advisorMessageTimeString;
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public String getAdvisorMessage() {
		return advisorMessage;
	}
	public void setAdvisorMessage(String advisorMessage) {
		this.advisorMessage = advisorMessage;
	}
	public Timestamp getUserMessageTime() {
		return userMessageTime;
	}
	public void setUserMessageTime(Timestamp userMessageTime) {
		this.userMessageTime = userMessageTime;
	}
	public Timestamp getAdvisorMessageTime() {
		return advisorMessageTime;
	}
	public void setAdvisorMessageTime(Timestamp advisorMessageTime) {
		this.advisorMessageTime = advisorMessageTime;
	}
	public String getUserMessageTimeString() {
		return userMessageTimeString;
	}
	public void setUserMessageTimeString(String userMessageTimeString) {
		this.userMessageTimeString = userMessageTimeString;
	}
	public String getAdvisorMessageTimeString() {
		return advisorMessageTimeString;
	}
	public void setAdvisorMessageTimeString(String advisorMessageTimeString) {
		this.advisorMessageTimeString = advisorMessageTimeString;
	}

}
