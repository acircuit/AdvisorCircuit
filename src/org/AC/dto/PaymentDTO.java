package org.AC.dto;

import java.sql.Timestamp;

public class PaymentDTO {
	
	int sessionId;
	int requestId;
	Timestamp acceptedDate;
	String acceptedDateString;
	public Boolean getPaidToAdvisor() {
		return paidToAdvisor;
	}
	public void setPaidToAdvisor(Boolean paidToAdvisor) {
		this.paidToAdvisor = paidToAdvisor;
	}
	String service;
	String mode;
	int price;
	int discount;
	int amount;
	Timestamp dateOfPurchase;
	String purchaseDateString;
	Boolean paidToAdvisor;
	int amountPayable;
	int fee;

	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(int amountPayable) {
		this.amountPayable = amountPayable;
	}
	public String getAcceptedDateString() {
		return acceptedDateString;
	}
	public void setAcceptedDateString(String acceptedDateString) {
		this.acceptedDateString = acceptedDateString;
	}
	public String getPurchaseDateString() {
		return purchaseDateString;
	}
	public void setPurchaseDateString(String purchaseDateString) {
		this.purchaseDateString = purchaseDateString;
	}
	String paymentMode;
	String trackingId;
	String userComment;
	String advisorComment; 
	String status;
	public String getUserComment() {
		return userComment;
	}
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
	public String getAdvisorComment() {
		return advisorComment;
	}
	public void setAdvisorComment(String advisorComment) {
		this.advisorComment = advisorComment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public Timestamp getAcceptedDate() {
		return acceptedDate;
	}
	public void setAcceptedDate(Timestamp acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Timestamp getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Timestamp dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
}
