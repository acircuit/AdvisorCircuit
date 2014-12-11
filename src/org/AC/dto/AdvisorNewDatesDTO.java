package org.AC.dto;

import java.sql.Timestamp;

public class AdvisorNewDatesDTO {
	
	private int sessionId;
	private Timestamp newDate1;
	private Timestamp newDate2;
	private Timestamp newDate3;
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
