package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminAdvisorDAO;
import org.AC.DAO.AdvisorModesDAO;
import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.DAO.AdvisorServicesDAO;
import org.AC.DAO.ProfessionalBackgroundDAO;
import org.AC.Util.AdvisorRegistrationPDF;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.AdvisorEducationDTO;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class RegistrationPDFController
 */
@WebServlet("/RegistrationPDFController")
public class RegistrationPDFController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorLoginController.class);


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of RegistrationPDFController");
		String advisorId = request.getParameter("aId");
		java.util.List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		List<ProfessionalBackgroundDTO> list1 = new ArrayList<ProfessionalBackgroundDTO>();
		List<AdvisorServiceDTO> list2 = new ArrayList<AdvisorServiceDTO>();
		List<AdvisorModeDTO> list3 = new ArrayList<AdvisorModeDTO>();
		
		
		//This is to get the profile details for the advisor profile
		AdvisorProfileDetailsDAO dao = new AdvisorProfileDetailsDAO();
		list = dao.getAdvisorProfileDetails(advisorId);
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
		
		//This is to get the skills
		List<AdvisorProfileDTO> keySkills = new ArrayList<AdvisorProfileDTO>();
		AdvisorProfileDetailsDAO skill = new AdvisorProfileDetailsDAO();
		keySkills = skill.GetKeySkill(advisorId);
		
		//This is to get the award and achievements
		List<AdvisorProfileDTO> awards = new ArrayList<AdvisorProfileDTO>();
		AdvisorProfileDetailsDAO achievement = new AdvisorProfileDetailsDAO();
		awards = achievement.GetAchievements(advisorId);	

		AdvisorRegistrationPDF pdf = new AdvisorRegistrationPDF();
		pdf.createPDF(response, list, list1, list2, list3,edu,keySkills,awards);
		
		
		logger.info("Entered doGet method of RegistrationPDFController");
	}
}
