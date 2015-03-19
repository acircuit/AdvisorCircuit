package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.Util.CreateAdvisorFormPDF;
import org.AC.Util.ProfessionalBackgroundObject;
import org.AC.dto.ProfessionalBackgroundDTO;

/**
 * Servlet implementation class AdvisorRegistrationController
 */
@WebServlet("/AdvisorRegistrationController")
public class AdvisorRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		//Retrieving Advisor Professional Background
		String[] company = request.getParameterValues("company[]");
		String[] designation = request.getParameterValues("designation[]");
		String[] description = request.getParameterValues("description[]");
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		ProfessionalBackgroundObject professionalBackground = new ProfessionalBackgroundObject();
		/*list =  professionalBackground.getProfessionalBackgroundObject(company,designation,description);
		CreateAdvisorFormPDF pdf = new CreateAdvisorFormPDF();
		pdf.createPDF(response,name,gender,age,email,city,nationality,phone,industry,occupation,introduction,organisation,job,experience,ug,pg,others,achievements
				,knowyouradvisor,keyskills,hobbies,funfact,introduction_prof,list);*/
		
	}

}
