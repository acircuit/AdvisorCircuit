package org.AC.dto;

public class AdvisorModeDTO {
	
	private int advisorId;
	private String service;
	private String modeOfCommunication;
	private String price;
	private int discounted_price;
	private int user_isfree_price;
	
	public int getUser_isfree_price() {
		return user_isfree_price;
	}
	public void setUser_isfree_price(int user_isfree_price) {
		this.user_isfree_price = user_isfree_price;
	}
	public int getDiscounted_price() {
		return discounted_price;
	}
	public void setDiscounted_price(int discounted_price) {
		this.discounted_price = discounted_price;
	}
	public int getAdvisorId() {
		return advisorId;
	}
	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getModeOfCommunication() {
		return modeOfCommunication;
	}
	public void setModeOfCommunication(String modeOfCommunication) {
		this.modeOfCommunication = modeOfCommunication;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
