package org.AC.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADVISORTABLE1")
public class Advisor {
	@Id
	@Column(name="ADVISOR_ID")
	private int id;
	@Column(name="NAME")
   private String Name; 
   private int age;
   private String gender;
   private String username;
   private String password;
   private String acheivements;
   private String qualifications;
   private String aboutme;
   @Column(name="VIEW_ID")
	private int viewId;
   @Column(name="FUNFACT")
	private int funFact;
   
public int getFunFact() {
	return funFact;
}
public void setFunFact(int funFact) {
	this.funFact = funFact;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAcheivements() {
	return acheivements;
}
public void setAcheivements(String acheivements) {
	this.acheivements = acheivements;
}

public String getQualifications() {
	return qualifications;
}
public void setQualifications(String qualifications) {
	this.qualifications = qualifications;
}
public String getAboutme() {
	return aboutme;
}
public void setAboutme(String aboutme) {
	this.aboutme = aboutme;
}
	  
}
