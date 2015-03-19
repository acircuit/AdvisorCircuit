package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.DAO.MessageDAO;
import org.AC.DAO.MyAccountRequestDAO;
import org.AC.Util.SendMail;
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorMyAccountMessageAdminFormController
 */
@WebServlet("/AdvisorMyAccountMessageAdminFormController")
public class AdvisorMyAccountMessageAdminFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountMessageAdminFormController.class);        
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorMyAccountMessageAdminFormController");
		int advisorId = 0;
		String advisorMessage = "";
		try{
		    advisorId = (int) request.getSession().getAttribute("advisorId"); 		    
			}catch(Exception e){
				response.sendRedirect("Error");
			}
		 advisorMessage = request.getParameter("advisormessage");
		 Properties prop = new Properties();
	     InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	     prop.load(resourceAsStream);
		 if(advisorId != 0 && advisorMessage != null && !("").equals(advisorMessage)){
			 
			 //Put the message in the advisormessageadmin table
			 MessageDAO dao = new MessageDAO();
			 Timestamp time = dao.setAdvisorMessage(advisorMessage, advisorId);
			 if(time != null){
				 String advisorName = "";
				 String advisorEmail = "";
				 List<AdvisorProfileDTO> name = new ArrayList<AdvisorProfileDTO>();
				 AdvisorProfileDetailsDAO detail =new AdvisorProfileDetailsDAO();
				 name = detail.getAdvisorProfileDetails(Integer.toString(advisorId));
				 for (AdvisorProfileDTO advisorProfileDTO : name) {
					 advisorName = advisorProfileDTO.getName();
					 advisorEmail = advisorProfileDTO.getEmail();
				}
				 if(advisorId != 0  && !("").equals(advisorEmail) && !("").equals(advisorName)){
					 	String subject ="New Message From Advisor To Admin!!!!!";
						String content ="Hi,<br> An Advisor has sent a message to admin.Following are the details: <br>Message By : " +advisorName+ "<br>Email Id: " +advisorEmail+ "<br>Advisor Id: " +advisorId+ "<br>Message: " +advisorMessage+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
						SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
						mail.start();
				 }
				 RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdvisorMessageAdmin");
			     rd.forward(request, response);
			 }
		 }
	
			logger.info("Exit doPost method of AdvisorMyAccountMessageAdminFormController");
	}

}
