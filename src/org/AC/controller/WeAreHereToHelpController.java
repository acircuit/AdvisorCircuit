package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.UserDetailsDAO;
import org.AC.Util.SendMail;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class WeAreHereToHelpController
 */
@WebServlet("/WeAreHereToHelpController")
public class WeAreHereToHelpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(WeAreHereToHelpController.class); 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of WeAreHereToHelpController");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		Properties prop = new Properties();
	    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop.load(resourceAsStream);
		if( email != null && phone != null && !email.isEmpty() && !phone.isEmpty()){
			
			UserDetailsDAO here = new UserDetailsDAO();
			Boolean isDetailsCommit = here.SetHereToHelpDetails(email,phone);
			if(isDetailsCommit){
				//Notify Admin
				String comment = email+" sent a message through We Are Here to Help";
				String href = "AdminHereToHelp";
				AdminNotificationDAO notify = new AdminNotificationDAO();
				notify.InsertNotification(comment, href);
				
				String subject ="";
				String content ="";
				subject = "We Are here to help";
				content = "Hi, <br><br>A new user has filled in details for We are here to help.Following are the details : <br> Email :"+email+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
				SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
				mail.start();
				response.getWriter().write("Thank you for reaching out to us! We'll help you find the right Advisor within 48 Hrs.");
			}

		}
		
		logger.info("Entered doPost method of WeAreHereToHelpController");
	}

}
