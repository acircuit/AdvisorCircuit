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
import org.AC.DAO.AdvisorMyAccountRequestViewDetailsDAO;
import org.AC.DAO.AdvisorNotificationDAO;
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
		 uId = (String) request.getParameter("uId");
		 rId = (String) request.getParameter("rId");
		 cancel = (String) request.getParameter("cancel");
		 sessionId = (String) request.getParameter("sessionId");
		 String isFree = request.getParameter("isFree");
		 String aId = request.getParameter("aId");
		 String service = request.getParameter("service");
		 String userIsFree = request.getParameter("userIsFree");
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
					if(isFree.equals("true")){
						//Decrement the free session count from the advisor services table
						AdvisorMyAccountRequestViewDetailsDAO decrem = new AdvisorMyAccountRequestViewDetailsDAO();
						decrem.DecrementFreeSession(Integer.parseInt(aId),service);
					}
					if(userIsFree.equals("true")){
						//Toggle the free session column in the userdetails table
						AdvisorMyAccountRequestViewDetailsDAO toggle = new AdvisorMyAccountRequestViewDetailsDAO();
						toggle.ToggleUserFreeSession(Integer.parseInt(uId));
					}
					//Getting UserName
					AdminNotificationDAO name = new AdminNotificationDAO();
					String userName = name.GetUserName(uId);
					//Notify Advisor
					String comment = userName + " cancelled the session";
					String href = "UserCancelledSessions";
					AdvisorNotificationDAO advisor = new AdvisorNotificationDAO();
					advisor.InsertRequestNotification(comment, aId, href);
					
					AdminNotificationDAO advName = new AdminNotificationDAO();
					String advisorName = advName.GetAdvisorName(aId);
					
					String adminComment = userName +" cancelled the session with "+advisorName;
					String adminHref = "AdminCancelledSessions";
					//Admin Notification
					AdminNotificationDAO admin = new AdminNotificationDAO();
					admin.InsertNotification(adminComment, adminHref);
					
					String subject = "Session Rejected By User!";
					String content = "Hi, <br><br>The Session was rejected by the user for Session Id : "+sessionId+ " and Request Id :"+rId1+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
					mail.start();
					response.sendRedirect("UserCancelledSessions");
				}
			}else if (isStatusCommit) {
				if(userIsFree.equals("true")){
					//Toggle the free session column in the userdetails table
					AdvisorMyAccountRequestViewDetailsDAO toggle = new AdvisorMyAccountRequestViewDetailsDAO();
					toggle.ToggleUserFreeSession(Integer.parseInt(uId));
				}
				//Getting UserName
				AdminNotificationDAO name = new AdminNotificationDAO();
				String userName = name.GetUserName(uId);
				//Notify Advisor
				String comment = userName + " cancelled the session";
				String href = "UserCancelledSessions";
				AdvisorNotificationDAO advisor = new AdvisorNotificationDAO();
				advisor.InsertRequestNotification(comment, aId, href);
				
				AdminNotificationDAO advName = new AdminNotificationDAO();
				String advisorName = advName.GetAdvisorName(aId);
				
				String adminComment = userName +" cancelled the session with "+advisorName;
				String adminHref = "AdminCancelledSessions";
				//Admin Notification
				AdminNotificationDAO admin = new AdminNotificationDAO();
				admin.InsertNotification(adminComment, adminHref);
				String subject = "Session Rejected By User!";
				String content = "Hi, <br><br>The Session was rejected by the user for Request Id :"+rId1+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
				SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
				mail.start();
				response.sendRedirect("UserCancelledSessions");
			}
		}
		logger.info("Exit doPost method of UserMyAccountRequestViewDetailsFormController");
	}

}
