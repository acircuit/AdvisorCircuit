package org.AC.dto;

public class UserReferralDTO {

	private int userId;
	private String refCode;
	private int refCount;
	Boolean refMessage;
	public Boolean getRefMessage() {
		return refMessage;
	}
	public void setRefMessage(Boolean refMessage) {
		this.refMessage = refMessage;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	public int getRefCount() {
		return refCount;
	}
	public void setRefCount(int refCount) {
		this.refCount = refCount;
	}
}
