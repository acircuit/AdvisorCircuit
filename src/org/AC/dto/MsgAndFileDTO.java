package org.AC.dto;

import java.sql.Timestamp;


public class MsgAndFileDTO {

	private String timeStampStr;
	private String message;
	private String msgType;				// msg or file link
	private String sender;
	private Timestamp messageTime;
	
	
	public MsgAndFileDTO(String timeStampStr, String message, String msgType, String sender, Timestamp messageTime) {
		this.timeStampStr = timeStampStr;
		this.message = message;
		this.msgType = msgType;
		this.sender = sender;
		this.messageTime = messageTime;
	}
	
	
	public String getTimeStampStr() {
		return timeStampStr;
	}
	public void setTimeStampStr(String timeStampStr) {
		this.timeStampStr = timeStampStr;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Timestamp getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(Timestamp messageTime) {
		this.messageTime = messageTime;
	}
		
	
	@Override
	public String toString() {
		String data = "";
		
		if(this.getSender().equals("advisor") && this.getMsgType().equals("text")){
			data = "<li class='left clearfix'><span class='chat-img pull-left'><i class='glyphicon glyphicon-user red'></i></span><div class='chat-body clearfix' style='margin-left:0px'>"
					+ "<div class='header'><strong class='primary-font'>Advisor</strong><small class='pull-right text-muted'><i class='fa fa-clock-o fa-fw gray' style='width:inherit; font-size:12px'>"+
					this.getTimeStampStr()+"</i></small></div><p>"+this.getMessage()+"</p></div></li>";			
		}else if(this.getSender().equals("user") && this.getMsgType().equals("text")){
			data = "<li class='left clearfix'><span class='chat-img pull-right'><i class='glyphicon glyphicon-user'></i></span><div class='chat-body clearfix' style='margin-left:0px'><div class='header'>"
					+ "<small ><i class='fa fa-clock-o fa-fw gray' style='width:inherit; font-size:12px'>"+this.getTimeStampStr()+"</i></small><strong class='pull-right primary-font'>User</strong></div>"
							+ "<p class = 'pull-right'>"+this.getMessage()+"</p></div></li>";			
		}else if(this.getMsgType().equals("file")){
			String color = "";
			
			if(this.getSender() == "ADVISOR"){
				color = "red";
			}
			
			data = "<li class='left clearfix'><span class='chat-img pull-right'><i class='glyphicon glyphicon-user "+color+"'></i></span><div class='chat-body clearfix' style='margin-left:0px'><div class='header'>"
					+ "<small ><i class='fa fa-clock-o fa-fw gray' style='width:inherit; font-size:12px'>"+this.getTimeStampStr()+"</i></small><strong class='pull-right primary-font'>"+this.getSender()+"</strong></div>"
							+ "<p class = 'pull-right'>"+this.getMessage()+"</p></div></li>";			
		}
		
		return data;		
	}

}
