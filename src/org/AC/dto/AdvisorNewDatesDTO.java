package org.AC.dto;

import java.sql.Timestamp;

public class AdvisorNewDatesDTO {
	
	private int sessionId;
	private Timestamp newDate1;
	private Timestamp newDate2;
	private Timestamp newDate3;
	private String newDateString1;
	private String newDateString2;
	private String newDateString3;

	public String getNewDateString1() {
		return newDateString1;
	}
	public void setNewDateString1(String newDateString1) {
		this.newDateString1 = newDateString1;
	}
	public String getNewDateString2() {
		return newDateString2;
	}
	public void setNewDateString2(String newDateString2) {
		this.newDateString2 = newDateString2;
	}
	public String getNewDateString3() {
		return newDateString3;
	}
	public void setNewDateString3(String newDateString3) {
		this.newDateString3 = newDateString3;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public Timestamp getNewDate1() {
		return newDate1;
	}
	public void setNewDate1(Timestamp newDate1) {
		this.newDate1 = newDate1;
	}
	public Timestamp getNewDate2() {
		return newDate2;
	}
	public void setNewDate2(Timestamp newDate2) {
		this.newDate2 = newDate2;
	}
	public Timestamp getNewDate3() {
		return newDate3;
	}
	public void setNewDate3(Timestamp newDate3) {
		this.newDate3 = newDate3;
	}

}
