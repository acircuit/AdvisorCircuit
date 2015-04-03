package org.AC.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorModesDAO;
import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.DAO.AdvisorServicesDAO;
import org.AC.DAO.ProfessionalBackgroundDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.AdvisorEducationDTO;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.AC.dto.RecommendationDTO;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorProfileServlet
 */
@WebServlet("/AdvisorProfileServlet")
public class AdvisorProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorProfileServlet.class);       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Enter doGet method of AdvisorProfileServlet.java");
		String advisorId = request.getParameter("aId");
		String isAdmin = request.getParameter("admin");	
		java.util.List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		List<ProfessionalBackgroundDTO> list1 = new ArrayList<ProfessionalBackgroundDTO>();
		List<AdvisorServiceDTO> list2 = new ArrayList<AdvisorServiceDTO>();
		List<AdvisorModeDTO> list3 = new ArrayList<AdvisorModeDTO>();
		
		System.out.println(advisorId);
		//This is to get the profile details for the advisor profile
		AdvisorProfileDetailsDAO dao = new AdvisorProfileDetailsDAO();
		list = dao.getAdvisorProfileDetails(advisorId);
		for (AdvisorProfileDTO advisorProfileDTO : list) {
			GetRelativeImageURL image = new GetRelativeImageURL();
			advisorProfileDTO.setImage(image.getImageURL(advisorProfileDTO.getImage()));
		}
		//This is to get the professional background for the advisor profile
		//Getting the company,designation and duration
		ProfessionalBackgroundDAO dao1 = new ProfessionalBackgroundDAO();
		list1 = dao1.getProfessionalBackground(advisorId);
		
		//Getting the descriptions
		List<ProfessionalBackgroundDTO> description = new ArrayList<ProfessionalBackgroundDTO>();
		ProfessionalBackgroundDAO desc = new ProfessionalBackgroundDAO();
		description = desc.GetProfessionalDescription(advisorId);
		for (ProfessionalBackgroundDTO professional : list1) {
			int i=0;
			String[] descript = new String[50];
			for (ProfessionalBackgroundDTO profession : description) {
				if(professional.getProfId() == profession.getProfId()){
					descript[i]=profession.getDescription();
					i++;
				}
			}
			professional.setDescriptionArray(descript);
		}
		
		//This is to get the education details
		List<AdvisorEducationDTO> edu = new ArrayList<AdvisorEducationDTO>();
		AdvisorProfileDetailsDAO education = new AdvisorProfileDetailsDAO();
		edu = education.GetEducation(advisorId);
		
		//This is to get the services provided by the advisor
		AdvisorServicesDAO dao2 = new AdvisorServicesDAO();
		list2 = dao2.getAdvisorServiceDetails(advisorId);
		
		//This is to get the modes and price for a service 
		AdvisorModesDAO dao3 = new AdvisorModesDAO();
		list3 = dao3.getAdvisorModesDetails(advisorId);
		for (AdvisorModeDTO mode : list3) {
			int priceWithServiceFee = Integer.parseInt(mode.getPrice()) + (30 * Integer.parseInt(mode.getPrice()) /100);
			mode.setPrice(String.valueOf(priceWithServiceFee));
			if(!mode.getModeOfCommunication().equals("email")){
				int price = Integer.parseInt(mode.getPrice())/2;
				mode.setPrice(String.valueOf(price));
			}
			for (AdvisorServiceDTO services : list2) {
				if(mode.getService().equals(services.getService())){
					int discount = services.getDiscount();
					double discountedPrice = 	Integer.parseInt(mode.getPrice()) - (discount * Integer.parseInt(mode.getPrice()) /100);
					mode.setDiscounted_price(discountedPrice);
				}
			}
		}
		
		//This is to get the skills
		List<AdvisorProfileDTO> keySkills = new ArrayList<AdvisorProfileDTO>();
		AdvisorProfileDetailsDAO skill = new AdvisorProfileDetailsDAO();
		keySkills = skill.GetKeySkill(advisorId);
		
		//This is to get the award and achievements
		List<AdvisorProfileDTO> awards = new ArrayList<AdvisorProfileDTO>();
		AdvisorProfileDetailsDAO achievement = new AdvisorProfileDetailsDAO();
		awards = achievement.GetAchievements(advisorId);
		
		//Getting Advisor Reviews
		List<RecommendationDTO> reviews = new ArrayList<RecommendationDTO>();
		List<Integer> reviewUsers=new ArrayList<Integer>();
		AdvisorProfileDetailsDAO review = new AdvisorProfileDetailsDAO();
		reviews = review.getAdvisorReviews(advisorId);
		if( reviews != null && reviews.size() > 0){
			for (RecommendationDTO recommendationDTO : reviews) {
				reviewUsers.add(recommendationDTO.getUserId());
			}
		}
		List<UserDetailsDTO> reviewUserDetails = new ArrayList<UserDetailsDTO>();
		//Getting User Details
		if(reviewUsers != null && reviewUsers.size() > 0){
			AdvisorProfileDetailsDAO reviewUser = new AdvisorProfileDetailsDAO();
			reviewUserDetails = reviewUser.getUserDetails(reviewUsers);
		}
		for (UserDetailsDTO userDetailsDTO : reviewUserDetails) {
			GetRelativeImageURL img= new GetRelativeImageURL();
			String relImage = img.getImageURL(userDetailsDTO.getImage());
			userDetailsDTO.setImage(relImage);
		}
		if(list.size() > 0){
			request.setAttribute("advisordetails", list);
			
		}
		if(list1.size() > 0){
			request.setAttribute("advisorprofession", list1);
		}
		if(list2.size() > 0){
			request.setAttribute("advisorservices", list2);
		}
		if(list3.size() > 0){
			request.setAttribute("advisormodes", list3);
		}
		if(edu.size() > 0){
			request.setAttribute("advisoreducation", edu);
		}
		if(keySkills.size() > 0){
			request.setAttribute("advisorskills", keySkills);
		}
		if(awards.size() > 0){
			request.setAttribute("awards", awards);
		}
		if(reviews.size() > 0 && reviewUserDetails.size() > 0){
			request.setAttribute("reviews", reviews);
			request.setAttribute("reviewUserDetails", reviewUserDetails);
		}
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if(!("true").equals(isAdmin)){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdvisorProfile.jsp");
	        rd.forward(request, response);
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdvisorProfilePage_Admin.jsp");
	        rd.forward(request, response);
		}
        logger.info("Exit doGet method of AdvisorProfileServlet.java");
		}
	
}
	

