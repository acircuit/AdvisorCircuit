package org.AC.dto;

import java.sql.Timestamp;

public class UserRequestDTO implements Comparable<UserRequestDTO> {

	int requestId;
	int advisorId;
	int userId;
	String service;
	String mode;
	String query;
	String duration;
	// Hours and Minutes are used to calculate the time left for the advisor to
	// reply
	long hours;
	long minutes;
	long days;
	String timeString1;
	String timeString2;

	public int getSessionId() {
		return sessionId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	String timeString3;
	String timeString4;
	String timeString5;
	String timeString6;
	String acceptedDate;
	String reviewMesage;
	int sessionId;
	int amount;
	Boolean rating;
	String reviewMessageStatus;
	Timestamp bookingTime;
	Timestamp time1;
	Timestamp time2;
	Timestamp time3;
	Timestamp time4;
	Timestamp time5;
	Timestamp time6;
	String bookingTimeString;
	String acceptedDateForClock;
	Boolean isFree;
	Boolean userIsFree;

	public Boolean getUserIsFree() {
		return userIsFree;
	}

	public void setUserIsFree(Boolean userIsFree) {
		this.userIsFree = userIsFree;
	}

	int discount;
	int price;

	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getReviewMessageStatus() {
		return reviewMessageStatus;
	}

	public void setReviewMessageStatus(String reviewMessageStatus) {
		this.reviewMessageStatus = reviewMessageStatus;
	}

	public Boolean getRating() {
		return rating;
	}

	public void setRating(Boolean rating) {
		this.rating = rating;
	}

	public String getReviewMesage() {
		return reviewMesage;
	}

	public void setReviewMesage(String reviewMesage) {
		this.reviewMesage = reviewMesage;
	}

	public String getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(String acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public String getTimeString2() {
		return timeString2;
	}

	public void setTimeString2(String timeString2) {
		this.timeString2 = timeString2;
	}

	public String getTimeString3() {
		return timeString3;
	}

	public void setTimeString3(String timeString3) {
		this.timeString3 = timeString3;
	}

	public String getTimeString4() {
		return timeString4;
	}

	public void setTimeString4(String timeString4) {
		this.timeString4 = timeString4;
	}

	public String getTimeString5() {
		return timeString5;
	}

	public void setTimeString5(String timeString5) {
		this.timeString5 = timeString5;
	}

	public String getTimeString6() {
		return timeString6;
	}

	public void setTimeString6(String timeString6) {
		this.timeString6 = timeString6;
	}

	public String getTimeString1() {
		return timeString1;
	}

	public void setTimeString1(String timeString1) {
		this.timeString1 = timeString1;
	}

	public String getAcceptedDateForClock() {
		return acceptedDateForClock;
	}

	public void setAcceptedDateForClock(String acceptedDateForClock) {
		this.acceptedDateForClock = acceptedDateForClock;
	}

	public String getBookingTimeString() {
		return bookingTimeString;
	}

	public void setBookingTimeString(String bookingTimeString) {
		this.bookingTimeString = bookingTimeString;
	}

	String status;

	public Timestamp getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Timestamp bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Timestamp getTime1() {
		return time1;
	}

	public void setTime1(Timestamp time1) {
		this.time1 = time1;
	}

	public Timestamp getTime2() {
		return time2;
	}

	public void setTime2(Timestamp time2) {
		this.time2 = time2;
	}

	public Timestamp getTime3() {
		return time3;
	}

	public void setTime3(Timestamp time3) {
		this.time3 = time3;
	}

	public Timestamp getTime4() {
		return time4;
	}

	public void setTime4(Timestamp time4) {
		this.time4 = time4;
	}

	public Timestamp getTime5() {
		return time5;
	}

	public void setTime5(Timestamp time5) {
		this.time5 = time5;
	}

	public Timestamp getTime6() {
		return time6;
	}

	public void setTime6(Timestamp time6) {
		this.time6 = time6;
	}

	public long getHours() {
		return hours;
	}

	public void setHours(long hours) {
		this.hours = hours;
	}

	public long getMinutes() {
		return minutes;
	}

	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	@Override
	public int compareTo(UserRequestDTO obj) {
		return obj.getBookingTime().compareTo(this.getBookingTime());
	}

}
