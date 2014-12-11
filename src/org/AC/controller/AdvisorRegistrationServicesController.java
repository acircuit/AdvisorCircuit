package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.RegistrationCompleteMail;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationServicesController
 */
@WebServlet("/AdvisorRegistrationServicesController")
public class AdvisorRegistrationServicesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationServicesController.class); 


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the Service Info from the form and put them in the required table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorRegistrationServicesController");	
		
		int advisorId = 0;
		String name ="";
		String email = "";
		advisorId = (int) request.getSession().getAttribute("aId");
		name = (String) request.getSession().getAttribute("name");
		email = (String) request.getSession().getAttribute("email");
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
		
		Boolean isServiceCommit = false;
		if(advisorId !=0 && !("").equals(name) && !("").equals(email)){
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

		if(services.length > 0) {
			for (String service : services) {
				if(service.equals("careertalk")){
					AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
					isServiceCommit = dao.setAdvisorServiceDetails("careertalk", advisorId , careerTalkDescription );
					if( isServiceCommit && careerTalkMode.length > 0){
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
					AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
					isServiceCommit = dao.setAdvisorServiceDetails("mockinterview", advisorId , mockInterviewDescription );
					if(isServiceCommit && mockInterviewMode.length > 0){
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
					AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
					isServiceCommit = dao.setAdvisorServiceDetails("cvcritique", advisorId , cvCritiqueDescription );
					if(isServiceCommit && cvCritiqueMode.length > 0){
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
					AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
					isServiceCommit = dao.setAdvisorServiceDetails("personalworkshops", advisorId , personalWorkshopDescription );
					if(isServiceCommit && personalWorkshopMode.length > 0){
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
		
		//Changing the Registration status to Complete.
		String status = "Image.jsp";
		AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
		Boolean isRegistrationStatusCommit = dao.setRegistrationStatus(advisorId, status);
		if(isRegistrationStatusCommit){
			//After successfull registration  :
			//Redirect to CompleteRegistration.jsp
			response.sendRedirect("Image.jsp");
		}	
		}else{
			response.sendRedirect("Error.jsp");
		}
		logger.info("Entered doPost method of AdvisorRegistrationServicesController");		

	}

}
