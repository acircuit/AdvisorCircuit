package org.AC.dto;

public class AdvisorModeDTO {
	
	private int advisorId;
	private String service;
	private String modeOfCommunication;
	private String price;
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
