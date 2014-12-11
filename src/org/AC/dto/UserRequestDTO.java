package org.AC.dto;

import java.sql.Timestamp;

public class UserRequestDTO {
	
	int requestId;
	int advisorId;
	int userId;
	String service;
	String mode;
	String query;
	String duration;
	//Hours and Minutes are used to calculate the time left for the advisor to reply
	long hours;
	long minutes;
	long days;
	Timestamp bookingTime;
	Timestamp time1;
	Timestamp time2;
	Timestamp time3;
	Timestamp time4;
	Timestamp time5;
	Timestamp time6;
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
	
}
