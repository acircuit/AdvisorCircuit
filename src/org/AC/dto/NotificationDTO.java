package org.AC.dto;

import java.sql.Timestamp;

public class NotificationDTO {
	private int nId;
	private Timestamp date;
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getnId() {
		return nId;
	}
	public void setnId(int nId) {
		this.nId = nId;
	}
	private String comment;
	private String href;
	Boolean isPrevious;
	public Boolean getIsPrevious() {
		return isPrevious;
	}
	public void setIsPrevious(Boolean isPrevious) {
		this.isPrevious = isPrevious;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
}
