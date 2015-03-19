package org.AC.dto;

import java.sql.Timestamp;

public class UserDetailsDTO {
	
	int userId;
	String email;
	String fullName;
	String phone;
	String age;
	String nationality;
	String occupation;
	String image;
	Timestamp dateOfRegistration;
	String dor;
	Boolean isActive;
	public Timestamp getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Timestamp dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public String getDor() {
		return dor;
	}
	public void setDor(String dor) {
		this.dor = dor;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
