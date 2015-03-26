package org.AC.dto;

public class AdvisorServiceDTO {
	
	private int advisorId;
	private String service;
	private String description;
	private int isFree;
	private int discount;
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getIsFree() {
		return isFree;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
