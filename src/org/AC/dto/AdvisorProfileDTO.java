package org.AC.dto;

public class AdvisorProfileDTO implements Comparable<AdvisorProfileDTO> {

	private int advisorId;
	private String Name;
	private String gender;
	private String age;
	private String phone;
	private String email;
	private String city;
	private String state;
	private String nationalty;
	private String industry;
	private String nameOfOrganisation;
	private String jobTitle;
	private String experience;
	private String image;
	private String ug;
	private String pg;
	private String others;
	private String[] othersStringArray;
	private String achievements;
	private String[] achievementsStringArray;
	private String keyskill;
	private String[] keyskills;
	private String hobbies;
	private String funfact;
	private String occupation;
	private String introduction;
	private String knowYourAdvisor;
	private int ratings;
	private Boolean isActive;
	private Boolean isVisible;
	
	private int pageRank;
	String status;

	public int getPageRank() {
		return pageRank;
	}
	public void setPageRank(int pageRank) {
		this.pageRank = pageRank;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	private String[] services;
	public String[] getServices() {
		return services;
	}
	public void setServices(String[] services) {
		this.services = services;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getKnowYourAdvisor() {
		return knowYourAdvisor;
	}
	public void setKnowYourAdvisor(String knowYourAdvisor) {
		this.knowYourAdvisor = knowYourAdvisor;
	}
	private String professional_intro;
	
	public String getProfessional_intro() {
		return professional_intro;
	}
	public void setProfessional_intro(String professional_intro) {
		this.professional_intro = professional_intro;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAchievements() {
		return achievements;
	}
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNationalty() {
		return nationalty;
	}
	public void setNationalty(String nationalty) {
		this.nationalty = nationalty;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getNameOfOrganisation() {
		return nameOfOrganisation;
	}
	public void setNameOfOrganisation(String nameOfOrganisation) {
		this.nameOfOrganisation = nameOfOrganisation;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
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

	public String getKeyskill() {
		return keyskill;
	}
	public void setKeyskill(String keyskill) {
		this.keyskill = keyskill;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getFunfact() {
		return funfact;
	}
	public void setFunfact(String funfact) {
		this.funfact = funfact;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String[] getOthersStringArray() {
		return othersStringArray;
	}
	public void setOthersStringArray(String[] othersStringArray) {
		this.othersStringArray = othersStringArray;
	}
	public String[] getKeyskills() {
		return keyskills;
	}
	public void setKeyskills(String[] keyskills) {
		this.keyskills = keyskills;
	}
	public String[] getAchievementsStringArray() {
		return achievementsStringArray;
	}
	public void setAchievementsStringArray(String[] achievementsStringArray) {
		this.achievementsStringArray = achievementsStringArray;
	}
	
	public Boolean getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	
	@Override
	public String toString() {
		return "advisorId"+ getAdvisorId()+ "Name" + getName() + "gender" + getGender() + "age" + getAge() + "phone" + getPhone() +
		"email" + getEmail() + "city"+ getCity() + "state" + getState() + "nationalty" + getNationalty() + "industry" + getIndustry() 
		+ "nameOfOrganisation" + getNameOfOrganisation() + "jobTitle" + getJobTitle() + "experience" + getExperience() + "image" + getImage() 
		+ "ug"+ getUg() + 
		
//		"pg" + getPg() +"others"+ get +"othersStringArray"+ get +"achievements"+ get +"achievementsStringArray"+ get +"keyskill"+ get +"keyskills"+ get +"hobbies"+ get +"funfact"+ get +"occupation"+ get +"introduction"+ get +"knowYourAdvisor"+ get +"ratings"+ get
		
		"isActive" + getServices() +"pageRank" + getPageRank() + "status" + getStatus();
		 
		
	}
	@Override
	public int compareTo(AdvisorProfileDTO obj) {
		return obj.getPageRank() - this.getPageRank();
	}
	
	
}
