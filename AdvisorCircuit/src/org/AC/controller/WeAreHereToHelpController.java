package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String occupation = request.getParameter("occupation");
		String industry = request.getParameter("industry");
		String service = request.getParameter("service");
		String phonemode = request.getParameter("phonemode");
		String emailmode = request.getParameter("emailmode");
		String webchat = request.getParameter("webchat");
		String query = request.getParameter("query");
		String other = request.getParameter("other");
		if(name != null && email != null && occupation != null && industry != null && service != null && phonemode != null && emailmode != null && webchat != null
				&& query != null && !name.isEmpty() && !email.isEmpty() && !occupation.isEmpty() && !industry.isEmpty() && !service.isEmpty() && !phonemode.isEmpty() && !emailmode.isEmpty() 
				&& !webchat.isEmpty() && !query.isEmpty()){
			
			UserDetailsDAO here = new UserDetailsDAO();
			Boolean isDetailsCommit = here.SetHereToHelpDetails(name,email,phone,occupation,industry,service,phonemode,emailmode,webchat,query,other);
			if(isDetailsCommit){
				String subject ="";
				String content ="";
				subject = "We Are here to help";
				content = "Hi,<br>A new user has filled in details for We are here to help.Following are the details : <br> Name: "+ name +" <br> Email :"+email+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
				SendMail mail = new SendMail(subject, content, "admin@advisorcircuit.com","admin@advisorcircuit.com");
				mail.start();
				response.getWriter().write("We'll get back to you in no time.");
			}

		}
		
		logger.info("Entered doPost method of WeAreHereToHelpController");
	}

}
