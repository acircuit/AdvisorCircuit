package org.AC.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.Util.ProfessionalBackgroundObject;
import org.AC.DAO.AdvisorServicesDAO;
import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.DAO.ProfessionalBackgroundDAO;
import org.AC.JDBC.Util;
import org.AC.Util.PasswordHashing;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RegistrationController.class); 
	Connection conn = null;
	Statement stmt = null;
	int auto_id = 0;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		int advisorId = 13;
		//Retrieving Advisor Profile DTO Attributes
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String nationality = request.getParameter("nationality");
		String phone = request.getParameter("phone");
		String industry = request.getParameter("industry");
		String occupation = request.getParameter("occupation");
		String introduction = request.getParameter("introduction");
		String organisation = request.getParameter("organisation");
		String job = request.getParameter("job");
		String experience = request.getParameter("experience");
		String ug = request.getParameter("ug");
		String pg = request.getParameter("pg");
		String others = request.getParameter("others");
		String achievements = request.getParameter("achievement");
		String knowyouradvisor = request.getParameter("knowyouradvisor");
		String keyskills = request.getParameter("keyskills");
		String hobbies = request.getParameter("hobbies");
		String funfact = request.getParameter("funfact");
		String introduction_prof = request.getParameter("introduction_professionalbackground");
		
		//Retrieving Advisor Services Attributes
		String[] services = request.getParameterValues("services");
		String careerTalkDescription = "";
		String careerTalkPricePhone = "";
		String careerTalkPriceEmail = "";
		String careerTalkPriceWebchat = "";
		String[] careerTalkMode = new String[0];
		String mockInterviewDescription = "";
		String[] mockInterviewMode = new String[0];
		String mockInterviewPricePhone = "";
		String mockInterviewPriceEmail = "";
		String mockInterviewPriceWebchat = "";
		String cvCritiqueDescription = "";
		String[] cvCritiqueMode = new String[0];
		String cvCritiquePricePhone = "";
		String cvCritiquePriceEmail = "";
		String cvCritiquePriceWebchat = "";
		String personalWorkshopDescription = "";
		String[] personalWorkshopMode = new String[0];
		String personalWorkshopPricePhone = "";
		String personalWorkshopPriceEmail = "";
		String personalWorkshopPriceWebchat = "";
		
		if(services.length > 0) {
			for (String service : services) {
				if(service.equals("careertalk")){
					careerTalkDescription = request.getParameter("careertalkdescription");
				    careerTalkMode = request.getParameterValues("careertalkmode");
					for (String mode : careerTalkMode) {
						if(mode.equals("phone")){
							 careerTalkPricePhone = request.getParameter("careertalkpricephone");
						}else if (mode.equals("email")) {
							 careerTalkPriceEmail = request.getParameter("careertalkpriceemail");
						}else {
							 careerTalkPriceWebchat = request.getParameter("careertalkpricewebchat");
						}
						
					}
				}else if (service.equals("mockinterview")) {
					 mockInterviewDescription = request.getParameter("mockinterviewdescription");
					 mockInterviewMode = request.getParameterValues("mockinterviewmode");
					for (String mode : mockInterviewMode) {
						if(mode.equals("phone")){
							 mockInterviewPricePhone = request.getParameter("mockinterviewpricephone");
						}else if (mode.equals("email")) {
							 mockInterviewPriceEmail = request.getParameter("mockinterviewpriceemail");
						}else {
							 mockInterviewPriceWebchat = request.getParameter("mockinterviewpricewebchat");
						}
						
					}
				}else if (service.equals("cvcritique")) {
					cvCritiqueDescription = request.getParameter("cvcritiquedescription");
					cvCritiqueMode = request.getParameterValues("cvcritiquemode");
					for (String mode : cvCritiqueMode) {
						if(mode.equals("phone")){
							 cvCritiquePricePhone = request.getParameter("cvcritiquepricephone");
						}else if (mode.equals("email")) {
							 cvCritiquePriceEmail = request.getParameter("cvcritiquepriceemail");
						}else {
							 cvCritiquePriceWebchat = request.getParameter("cvcritiquepricewebchat");
						}
						
					}
				}else{
					 personalWorkshopDescription = request.getParameter("personalworkshopdescription");
					 personalWorkshopMode = request.getParameterValues("personalworkshopmode");
					for (String mode : personalWorkshopMode) {
						if(mode.equals("phone")){
							personalWorkshopPricePhone = request.getParameter("personalworkshoppricephone");
						}else if (mode.equals("email")) {
						    personalWorkshopPriceEmail = request.getParameter("personalworkshoppriceemail");
						}else {
						    personalWorkshopPriceWebchat = request.getParameter("personalworkshoppricewebchat");
						}
						
					}
				}
			}
		}
		
		/*
		String careertalkprice = request.getParameter("careertalkprice");
		String[] careertalkmode = request.getParameterValues("careertalkmode");
		String careertalkdescription = request.getParameter("careertalkdescription");
		String mockinterviewprice = request.getParameter("mockinterviewprice");
		String[] mockinterviewmode = request.getParameterValues("mockinterviewmode");
		String mockinterviewdescription = request.getParameter("mockinterviewdescription");
		String cvcritiqueprice = request.getParameter("cvcritiqueprice");
		String cvcritiquemode = request.getParameter("cvcritiquemode");
		String cvcritiquedescription = request.getParameter("cvcritiquedescription");
		String personalworkshopprice = request.getParameter("personalworkshopprice");
		String[] personalworkshopmode = request.getParameterValues("personalworkshopmode");
		String personalworkshopdescription = request.getParameter("personalworkshopdescription");*/
		
		//Retrieving Advisor Professional Background
		String[] company = request.getParameterValues("company[]");
		String[] designation = request.getParameterValues("designation[]");
		String[] description = request.getParameterValues("description[]");
		String[] duration = request.getParameterValues("duration[]");
		
		
		if((!name.isEmpty()) && (!gender.isEmpty()) && (!age.isEmpty()) && (!email.isEmpty()) 
				&& (!city.isEmpty()) && (!nationality.isEmpty()) && (!phone.isEmpty()) && (!industry.isEmpty()) && (!occupation.isEmpty()) && (!organisation.isEmpty())
				&& (!job.isEmpty()) && (!experience.isEmpty()) && (!ug.isEmpty()) && (!pg.isEmpty()) 
				&& (!achievements.isEmpty()) && (!keyskills.isEmpty()) && (!hobbies.isEmpty()) && (!funfact.isEmpty()) && (!introduction_prof.isEmpty()) 
				&& (!introduction.isEmpty())){
		
			AdvisorProfileDTO profile = new AdvisorProfileDTO();
			profile.setName(name);
			profile.setGender(gender);
			profile.setAge(age);
			profile.setPhone(phone);
			profile.setEmail(email);
			profile.setCity(city);
			profile.setNationalty(nationality);
			profile.setIndustry(industry);
			profile.setNameOfOrganisation(organisation);
			profile.setJobTitle(job);
			profile.setExperience(experience);
			profile.setUg(ug);
			profile.setPg(pg);
			profile.setOthers(others);
			profile.setAchievements(achievements);
			profile.setKeyskill(keyskills);
			profile.setHobbies(hobbies);
			profile.setFunfact(funfact);
			profile.setOccupation(occupation);
			profile.setIntroduction(introduction);
			profile.setKnowYourAdvisor(knowyouradvisor);
			profile.setProfessional_intro(introduction_prof);
			
			AdvisorProfileDetailsDAO dao = new AdvisorProfileDetailsDAO();
			advisorId = dao.setAdvisorProfileDetails(profile);
			
		
		}
		//This is for setting the professional background of the advisor
		if(advisorId != 0 && company.length > 0  && designation.length > 0 && description.length > 0) {
			 
			 ProfessionalBackgroundObject professionalBackground = new ProfessionalBackgroundObject();
			 list =  professionalBackground.getProfessionalBackgroundObject(company,designation,duration,description);
			 ProfessionalBackgroundDAO dao = new ProfessionalBackgroundDAO();
			 dao.addProfessionalBackground(list, advisorId);	
		}	
		if(services.length > 0) {
			
			for (String service : services) {
				if(service.equals("careertalk")){
					AdvisorServicesDAO dao = new AdvisorServicesDAO();
					dao.setAdvisorServiceDetails("careertalk", advisorId , careerTalkDescription );
					if(careerTalkMode.length > 0){
						for (String mode : careerTalkMode) {
							if (mode.equals("phone")){
								dao.setAdvisorModes("careertalk", advisorId, mode, careerTalkPricePhone);
							}else if (mode.equals("email")) {
								dao.setAdvisorModes("careertalk", advisorId, mode, careerTalkPriceEmail);
							}else {
								dao.setAdvisorModes("careertalk", advisorId, mode, careerTalkPriceWebchat);
							}
						}
					}
				}else if (service.equals("mockinterview")) {
					AdvisorServicesDAO dao = new AdvisorServicesDAO();
					dao.setAdvisorServiceDetails("mockinterview", advisorId , mockInterviewDescription );
					if(mockInterviewMode.length > 0){
						for (String mode : mockInterviewMode) {
							if (mode.equals("phone")){
								dao.setAdvisorModes("mockinterview", advisorId, mode, mockInterviewPricePhone);
							}else if (mode.equals("email")) {
								dao.setAdvisorModes("mockinterview", advisorId, mode, mockInterviewPriceEmail);
							}else {
								dao.setAdvisorModes("mockinterview", advisorId, mode, mockInterviewPriceWebchat);
							}
						}
					}
				}else if (service.equals("cvcritique")) {
					AdvisorServicesDAO dao = new AdvisorServicesDAO();
					dao.setAdvisorServiceDetails("cvcritique", advisorId , cvCritiqueDescription );
					if(cvCritiqueMode.length > 0){
						for (String mode : cvCritiqueMode) {
							if (mode.equals("phone")){
								dao.setAdvisorModes("cvcritique", advisorId, mode, cvCritiquePricePhone);
							}else if (mode.equals("email")) {
								dao.setAdvisorModes("cvcritique", advisorId, mode, cvCritiquePriceEmail);
							}else {
								dao.setAdvisorModes("cvcritique", advisorId, mode, cvCritiquePriceWebchat);
							}
						}
					}
				}else{
					AdvisorServicesDAO dao = new AdvisorServicesDAO();
					dao.setAdvisorServiceDetails("personalworkshops", advisorId , personalWorkshopDescription );
					if(personalWorkshopMode.length > 0){
						for (String mode : personalWorkshopMode) {
							if (mode.equals("phone")){
								dao.setAdvisorModes("personalworkshops", advisorId, mode, personalWorkshopPricePhone);
							}else if (mode.equals("email")) {
								dao.setAdvisorModes("personalworkshops", advisorId, mode, personalWorkshopPriceEmail);
							}else {
								dao.setAdvisorModes("personalworkshops", advisorId, mode, personalWorkshopPriceWebchat);
							}
						}
					}
				}
			}
		}
		response.sendRedirect("Admin_View_Profile.jsp");
	}
}

