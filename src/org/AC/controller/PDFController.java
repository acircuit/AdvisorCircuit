package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorPDFDetailsDAO;
import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.Util.CreateAdvisorFormPDF;
import org.AC.Util.ProfessionalBackgroundObject;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


/**
 * Servlet implementation class PDFController
 */
@WebServlet("/PDFController")
public class PDFController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationOtherInfoControler.class); 
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)and get the data from the tables and show a PDF
	 * This function will retrieve the email Id from the form and get the details from the table in order to show the profile PDF to the admin
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorRegistrationOtherInfoControler");
		java.util.List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
			String email = request.getParameter("email");
			int advisorId = 0;
			String name = "";
			String gender= "";
			String age= "";
			String city= "";
			String nationality= "";
			String phone= "";
			String industry= "";
			String introduction= "";
			String organisation= "";
			String job= "";
			String experience= "";
			String ug= "";
			String pg= "";
			String others= "";
			String achievements= "";
			String knowyouradvisor = "";
			String keyskills= "";
			String hobbies= "";
			String funfact= "";
			String image= "";
			if(!email.isEmpty()){
			AdvisorPDFDetailsDAO dao = new AdvisorPDFDetailsDAO();
			list = dao.getAdvisorProfileDetails(email);
			//Retrieving Advisor Profile DTO Attributes
			if(list.size() > 0) {
				for (AdvisorProfileDTO advisorProfileDTO : list) {
					advisorId = advisorProfileDTO.getAdvisorId();
					 name =advisorProfileDTO.getName();
					 gender= advisorProfileDTO.getGender();
					 age=advisorProfileDTO.getAge();
					 city=advisorProfileDTO.getCity();
					 nationality=advisorProfileDTO.getNationalty();
					 phone= advisorProfileDTO.getPhone();
					 industry = advisorProfileDTO.getIndustry();
					 organisation =advisorProfileDTO.getNameOfOrganisation(); 
					 image = advisorProfileDTO.getImage();
					 job = advisorProfileDTO.getJobTitle();
					 experience = advisorProfileDTO.getExperience();
					 ug = advisorProfileDTO.getUg();
					 pg = advisorProfileDTO.getPg();
					 others = advisorProfileDTO.getOthers();
					 achievements = advisorProfileDTO.getAchievements();
					 keyskills = advisorProfileDTO.getKeyskill();
					 hobbies = advisorProfileDTO.getHobbies();
					 funfact = advisorProfileDTO.getFunfact();
				}
				
				
			}
			//Retrieving Advisor Professional Background
			//String[] company = request.getParameterValues("company[]");
			//String[] designation = request.getParameterValues("designation[]");
			//String[] description = request.getParameterValues("description[]");
			//ProfessionalBackgroundObject professionalBackground = new ProfessionalBackgroundObject();
			//list1 =  professionalBackground.getProfessionalBackgroundObject(company,designation,description);
			List<ProfessionalBackgroundDTO> list1 = new ArrayList<ProfessionalBackgroundDTO>();
			AdvisorPDFDetailsDAO professionBackground  = new AdvisorPDFDetailsDAO();
			list1 = professionBackground.getAdvisorProfessionalBackground(advisorId);
			
			
			//RETRIEVING ADVISOR SERVICES
			List<AdvisorServiceDTO> list2 = new ArrayList<AdvisorServiceDTO>();
			AdvisorPDFDetailsDAO services  = new AdvisorPDFDetailsDAO();
			list2 = services.getAdvisorServices(advisorId);
			
			//RETRIEVING ADVISOR MODES AND PRICES
			List<AdvisorModeDTO> list3 = new ArrayList<AdvisorModeDTO>();
			AdvisorPDFDetailsDAO modes  = new AdvisorPDFDetailsDAO();
			list3 = modes.getAdvisorModes(advisorId);
			
			CreateAdvisorFormPDF pdf = new CreateAdvisorFormPDF();
			pdf.createPDF(response,name,gender,age,email,city,nationality,phone,industry,organisation,job,experience,ug,pg,others,achievements
					,keyskills,hobbies,funfact,list1,list2,list3,image);
			
	}else{
		response.sendRedirect("Error.jsp");
	}
		logger.info("Entered doPost method of AdvisorRegistrationOtherInfoControler");

	}

}
