package org.AC.dto;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="advisorskillview")
public class SearchDTO {
	
	@Id @Column(name="ID")
	private int id;
	@Column(name="ADVISOR_ID")
	private int advisorId;
	@Column(name="NAME")
	private String Name;
	@Column(name="PICTURE")
	private String picture;
	@Column(name="OCCUPATION")
	private String occupation;
	@Column(name="SERVICE")
	private String services;
	@Column(name="WORKPLACE")
	private String workplace;
	@Column(name="INTRODUCTION")
	private String intro;
	@Column(name="UG")
	private String ug;
	@Column(name="PG")
	private String pg;
	@Column(name="OTHERS")
	private String others;
	@Column(name="WORK_EXPERIENCE")
	private String workExperience;
	@Column(name="KEY_SKILL")
	private String keyskill;
	@Column(name="MODE_OF_COMMUNICATION")
	private String modeOfCommunication;
	private String relevantIndustry;
	public String getRelevantIndustry() {
		return relevantIndustry;
	}
	public void setRelevantIndustry(String relevantIndustry) {
		this.relevantIndustry = relevantIndustry;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdvisorId() {
		return advisorId;
	}
	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getUg() {
		return ug;
	}
	public void setUg(String ug) {
		this.ug = ug;
	}
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	public String getKeyskill() {
		return keyskill;
	}
	public void setKeyskill(String keyskill) {
		this.keyskill = keyskill;
	}
	public String getModeOfCommunication() {
		return modeOfCommunication;
	}
	public void setModeOfCommunication(String modeOfCommunication) {
		this.modeOfCommunication = modeOfCommunication;
	}
		
}
