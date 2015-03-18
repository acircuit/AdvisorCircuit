package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorMyAccountRequestViewDetailsDAO;
import org.AC.DAO.ChangeRequestStatusDAO;
import org.AC.DAO.ChangeSessionStatusDAO;
import org.AC.Util.SendMail;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserMyAccountRequestViewDetailsFormController
 */
@WebServlet("/UserMyAccountRequestViewDetailsFormController")
public class UserMyAccountRequestViewDetailsFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserMyAccountRequestViewDetailsFormController.class);     
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This servlet will retrieve the fields from the view details form and then put them into the session table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of UserMyAccountRequestViewDetailsFormController");
		
		String uId = "";
		String rId = "";
		String cancel = "";
		String sessionId = "";
		Boolean isStatusCommit = false;
		int aId = 0;
		 uId = (String) request.getParameter("uId");
		 rId = (String) request.getParameter("rId");
		 cancel = (String) request.getParameter("cancel");
		 sessionId = (String) request.getParameter("sessionId");
		 Properties prop = new Properties();
	     InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
		 prop.load(resourceAsStream);
		//Check if the user has cancelled the session
		if( cancel == null ){
				
		}else{
			//The user has cancelled the session
			//Change the status of the request to "REQUEST REJECTED BY USER"
			//Change the status of the session to ""SESSION REJECTED BY USER"
			String status1 = "REQUEST REJECTED BY USER";
			int rId1 = Integer.parseInt(rId);
			ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
			isStatusCommit = requestStatus.setStatus( status1,rId1);
			if(isStatusCommit && sessionId != null){
				ChangeSessionStatusDAO sessionStatus = new ChangeSessionStatusDAO();
				Boolean isSessionStatusCommit = sessionStatus.setStatus("SESSION REJECTED BY USER", sessionId);
				if(isSessionStatusCommit){
					String subject = "Session Rejected By User!";
					String content = "Hi,<br>The Session was rejected by the user for Session Id : "+sessionId+ " and Request Id :"+rId1+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
					mail.start();
					response.sendRedirect("UserCancelledSessions");
				}
			}else if (isStatusCommit) {
				String subject = "Session Rejected By User!";
				String content = "Hi,<br>The Session was rejected by the user for Request Id :"+rId1+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
				SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
				mail.start();
				response.sendRedirect("UserCancelledSessions");
			}
		}
		logger.info("Exit doPost method of UserMyAccountRequestViewDetailsFormController");
	}

}
