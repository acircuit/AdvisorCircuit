package org.AC.dto;

import java.sql.Timestamp;

public class SessionDTO {
	
	int sessionId;
	int requestId;
	int advisorId;
	int ratings;
	Boolean isRating;
	String reviewMessage;
	String reviewMessageStatus;
	String modeDetails;
	public String getModeDetails() {
		return modeDetails;
	}
	public void setModeDetails(String modeDetails) {
		this.modeDetails = modeDetails;
	}
	public int getRatings() {
		return ratings;
	}
	public Boolean getIsRating() {
		return isRating;
	}
	public void setIsRating(Boolean isRating) {
		this.isRating = isRating;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public String getReviewMessage() {
		return reviewMessage;
	}
	public void setReviewMessage(String reviewMessage) {
		this.reviewMessage = reviewMessage;
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
	public int getAdvisorId() {
		return advisorId;
	}
	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSessionPlan() {
		return sessionPlan;
	}
	public void setSessionPlan(String sessionPlan) {
		this.sessionPlan = sessionPlan;
	}
	public Timestamp getAcceptedDate() {
		return acceptedDate;
	}
	public String getReviewMessageStatus() {
		return reviewMessageStatus;
	}
	public void setReviewMessageStatus(String reviewMessageStatus) {
		this.reviewMessageStatus = reviewMessageStatus;
	}
	public void setAcceptedDate(Timestamp acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	int userId;
	String sessionPlan;
	Timestamp acceptedDate;
	String acceptedDateString;
	public String getAcceptedDateString() {
		return acceptedDateString;
	}
	public void setAcceptedDateString(String acceptedDateString) {
		this.acceptedDateString = acceptedDateString;
	}
	String status;
}
