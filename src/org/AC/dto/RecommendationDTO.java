package org.AC.dto;

public class RecommendationDTO {

	private int userId;
	private Boolean ratings;
	private String reviewMessage;
	private String reviewMessageStatus;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReviewMessageStatus() {
		return reviewMessageStatus;
	}

	public void setReviewMessageStatus(String reviewMessageStatus) {
		this.reviewMessageStatus = reviewMessageStatus;
	}

	public Boolean getRatings() {
		return ratings;
	}

	public void setRatings(Boolean ratings) {
		this.ratings = ratings;
	}

	public String getReviewMessage() {
		return reviewMessage;
	}

	public void setReviewMessage(String reviewMessage) {
		this.reviewMessage = reviewMessage;
	}

}
