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
	double price;
	int discount;
	double amount;
	Timestamp dateOfPurchase;
	String purchaseDateString;
	Boolean paidToAdvisor;
	double amountPayable;
	double fee;
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(double amountPayable) {
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
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
