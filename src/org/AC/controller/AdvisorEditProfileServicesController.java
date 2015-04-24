package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.Util.SendMail;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.dto.AdvisorModeDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorEditProfileServicesController
 */
@WebServlet("/AdvisorEditProfileServicesController")
public class AdvisorEditProfileServicesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorEditProfileServicesController.class); 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorEditProfileServicesController");
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("advisorId");
		}catch(Exception e){
			response.sendRedirect("Error");
		}
		if(advisorId != 0){
				List<AdvisorServiceDTO> list = new ArrayList<AdvisorServiceDTO>();
				//Getting Service Information of the advisor
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				list = dao.GetServices(advisorId);
				//Getting Advisor Services
				List<AdvisorModeDTO> list1 = new ArrayList<AdvisorModeDTO>();
				AdvisorRegistrationDAO mode = new AdvisorRegistrationDAO();
				list1 = mode.GetModes(advisorId);
				if(list.size() >0 && list1.size() > 0){
					request.setAttribute("advisorservices",list);
					request.setAttribute("advisormodes",list1);
					request.setAttribute("edit",true);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Services.jsp");
			        rd.forward(request, response);
				}
		}
		logger.info("Exit doGet method of AdvisorEditProfileServicesController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorEditProfileServicesController");	
		int advisorId = 0;
		String name ="";
		String email = "";
		try{
		advisorId = (int) request.getSession().getAttribute("advisorId");
		}catch(Exception e){
			response.sendRedirect("Error");
		}
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
		String isFreeCareerTalk = request.getParameter("isfreecareertalk");
		String isFreeMockInterview = request.getParameter("isfreemockinterview");
		String isFreeResumeCritique = request.getParameter("isfreeresumecritique");
		int isCareerTalkDiscount =0;
		int isMockInterviewDiscount =0;
		int isResumeCritiqueDiscount =0;

		Boolean isServiceCommit = false;
		if(advisorId !=0){
		// Fetching the Discount details
		List<AdvisorServiceDTO> ser = new ArrayList<AdvisorServiceDTO>();
		AdvisorRegistrationDAO discount = new AdvisorRegistrationDAO();
		ser =  discount.GetServicesDiscount(advisorId);
		for (AdvisorServiceDTO advisorServiceDTO : ser) {
			if(advisorServiceDTO.getService().equals("careertalk")){
				isCareerTalkDiscount = advisorServiceDTO.getDiscount();
			}
			if(advisorServiceDTO.getService().equals("mockinterview")){
				isMockInterviewDiscount = advisorServiceDTO.getDiscount();
			}
			if(advisorServiceDTO.getService().equals("cvcritique")){
				isResumeCritiqueDiscount = advisorServiceDTO.getDiscount();
			}
		}
		//Deleting the advisor services
			AdvisorRegistrationDAO deleteService = new AdvisorRegistrationDAO();
			deleteService.DeleteServices(advisorId);
		//Deleting the advisor modes
			AdvisorRegistrationDAO deleteMode = new AdvisorRegistrationDAO();
			deleteMode.DeleteModes(advisorId);
			
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
					isServiceCommit = dao.setAdvisorServiceDetails("careertalk", advisorId , careerTalkDescription, isFreeCareerTalk ,isCareerTalkDiscount);
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
					isServiceCommit = dao.setAdvisorServiceDetails("mockinterview", advisorId , mockInterviewDescription,isFreeMockInterview,isMockInterviewDiscount);
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
					isServiceCommit = dao.setAdvisorServiceDetails("cvcritique", advisorId , cvCritiqueDescription, isFreeResumeCritique ,isResumeCritiqueDiscount);
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
				}
			}
		}
		//After successfull registration  :
		//Redirect to CompleteRegistration.jsp
		Properties prop = new Properties();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
        try {
			prop.load(resourceAsStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 String subject ="";
		 String content ="";
		 subject = "An Advisor just edited his profile";
		 content = "Hi, <br><br>An Advisor just edited his profile. Following are the details: <br> Advisor Id : "+advisorId+"<br>Section : Services <br><img src=http://www.advisorcircuit.com/assets/img/logo_black.png\" style='float:right' width='25%'>";
		 SendMail mail = new SendMail(subject, content,prop.getProperty("MAIL_ADMIN") ,prop.getProperty("MAIL_ADMIN"));
		 mail.start();
		response.sendRedirect("ImageEdit?edit=true");
		logger.info("Exit doPost method of AdvisorEditProfileServicesController");		

	}
	}

}
