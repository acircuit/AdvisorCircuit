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
import org.AC.DAO.AdvisorNotificationDAO;
import org.AC.DAO.SessionMssagesDAO;
import org.AC.DAO.UserNotificationDAO;
import org.AC.Util.SendMail;
import org.AC.dto.FilesDTO;
import org.AC.dto.MessageDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminSessionMessagesAndFileController
 */
@WebServlet("/AdminSessionMessagesAndFileController")
public class AdminSessionMessagesAndFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(AdminSessionMessagesAndFileController.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdminSessionMessagesAndFileController");
		Boolean isAdmin = false;
		Boolean isError = false;
		try {
			isAdmin = (Boolean) request.getSession().getAttribute("admin");
		} catch (Exception e) {
			response.sendRedirect("Error");
			isError = true;
		}
		if (isAdmin == null) {
			isError = true;
			response.sendRedirect("Error");
		}
		if (isError != null && !isError) {
			String fileId = (String) request.getParameter("fileId");
			String userMessageId = (String) request
					.getParameter("userMessageId");
			String advisorMessageId = (String) request
					.getParameter("advisorMessageId");
			String status = (String) request.getParameter("status");
			String sessionId = request.getParameter("sId");
			System.out.println(sessionId);
			Properties prop = new Properties();
			InputStream resourceAsStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("Resources/mail.properties");
			prop.load(resourceAsStream);
			if (fileId != null && status != null) {
				SessionMssagesDAO setStatus = new SessionMssagesDAO();
				Boolean isStatusCommit = setStatus
						.SetFileStatus(fileId, status);
				if (isStatusCommit) {
					if (status.equals("REJECTED")) {
						// Getting File Purpose.
						FilesDTO file = new FilesDTO();
						SessionMssagesDAO purpose = new SessionMssagesDAO();
						file = purpose.GetPurpose(fileId);
						String uploadedBy = file.getUploadedBy();
						String comment = file.getComment();
						int id = 0;
						// Getting the advisor Id;
						int sessionId1 = file.getSessionId();
						//Getting the advisor Id;
						SessionMssagesDAO ids = new SessionMssagesDAO();
						id = ids.GetId(sessionId1, uploadedBy);
						// Getting user/advisor email
						id = ids.GetId(sessionId1, uploadedBy);
						//Getting user/advisor email 
						SessionMssagesDAO emails = new SessionMssagesDAO();
						String email = emails.GetEmail(id, uploadedBy);
		
						if(uploadedBy.equals("USER")){
							int[] ids1 = new int[3];
							//GETTING THE USERID, ADVISORID, AND REQUEST ID
							UserNotificationDAO id1 = new UserNotificationDAO();
							ids1 = id1.GetAdvisorId(sessionId);
							//Getting username
							AdminNotificationDAO name = new AdminNotificationDAO();
							String aName = name.GetAdvisorName(String.valueOf(ids1[1]));
							
							//Notify User
							String userComment = "Your communication to "+aName+" has been rejected. You'll received a mail with the details.";
							String userHref ="UserUpcomingSessionViewDetails?rId="+ids1[2];
							UserNotificationDAO user = new UserNotificationDAO();
							user.InsertNotification(userComment, userHref, String.valueOf(ids1[0]));
						}else{
							int[] ids1 = new int[3];
							//GETTING THE USERID, ADVISORID, AND REQUEST ID
							UserNotificationDAO id1 = new UserNotificationDAO();
							ids1 = id1.GetAdvisorId(sessionId);
							//Getting username
							AdminNotificationDAO name = new AdminNotificationDAO();
							String uName = name.GetUserName(String.valueOf(ids1[0]));
							
							//Notify advisor
							String advComment =  " Your communication to "+uName+" has been rejected. You'll received a mail with the details.";
							String href = "AdvisorUpcomingSessionViewDetails?rId="+ids1[2];
							AdvisorNotificationDAO notify = new AdvisorNotificationDAO();
							notify.InsertRequestNotification(advComment, String.valueOf(ids1[1]) , href);
						}
						String subject ="";
						String content ="";
						subject = "Your Communication has been rejected";
						content = "Hi, <br><br>We are sorry but your communication has been rejected. For further information please contact <b>Advisor Circuit at </b>contactus@advisorcircuit.com<br><h3>File Purpose : </h3>"
								+ comment
								+ "<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
						SendMail mail = new SendMail(subject, content, email,
								prop.getProperty("MAIL_ADMIN"));
						mail.start();
						response.getWriter()
								.write("THE STATUS HAS BEEN CHANGED.PLEASE REFRESH THE PAGE");

						response.getWriter().write("THE STATUS HAS BEEN CHANGED.PLEASE REFRESH THE PAGE");
	
	
	
					}else if (status.equals("APPROVED")) {
						
						// Getting File Purpose.
						FilesDTO file1 = new FilesDTO();
						SessionMssagesDAO purpose1 = new SessionMssagesDAO();
						file1 = purpose1.GetPurpose(fileId);
						String uploadedBy1 = file1.getUploadedBy();
						if(uploadedBy1.equals("USER")){
							int[] ids1 = new int[3];
							//GETTING THE USERID, ADVISORID, AND REQUEST ID
							UserNotificationDAO id1 = new UserNotificationDAO();
							ids1 = id1.GetAdvisorId(sessionId);
							//Getting username
							AdminNotificationDAO name = new AdminNotificationDAO();
							String uName = name.GetUserName(String.valueOf(ids1[0]));
							
							//Notify advisor
							String comment = uName + " sent you a file.";
							String href = "AdvisorUpcomingSessionViewDetails?rId="+ids1[2];
							AdvisorNotificationDAO notify = new AdvisorNotificationDAO();
							notify.InsertRequestNotification(comment, String.valueOf(ids1[1]) , href);
						}else{
							int[] ids1 = new int[3];
							//GETTING THE USERID, ADVISORID, AND REQUEST ID
							UserNotificationDAO id1 = new UserNotificationDAO();
							ids1 = id1.GetAdvisorId(sessionId);
							//Getting advisor name
							AdminNotificationDAO aName = new AdminNotificationDAO();
							String advisorName = aName.GetAdvisorName(String.valueOf(ids1[1]));
							//Notify advisor
							String comment = advisorName + " sent you a file.";
							String href = "UserUpcomingSessionViewDetails?rId="+ids1[2];
							UserNotificationDAO user = new UserNotificationDAO();
							user.InsertNotification(comment, href, String.valueOf(ids1[0]));
							
						}

					}
				}
			} else if (userMessageId != null && status != null) {
					SessionMssagesDAO setStatus1 = new SessionMssagesDAO();
					Boolean isStatusCommit1 = setStatus1.SetUserMessage(userMessageId, status);
					if(isStatusCommit1){
						if(status.equals("REJECTED")){
							//Getting Message.
							SessionMssagesDAO message = new SessionMssagesDAO(); 
							MessageDTO messages = new MessageDTO();
							messages = message.GetMessage(userMessageId,"USER");
							int sessionId1 = messages.getSessionId();
							String userMessage = messages.getAdvisorMessage();
							//Getting the user Id;
							SessionMssagesDAO ids = new SessionMssagesDAO();
							int id = ids.GetId(sessionId1, "USER");
							//Getting user/advisor email 
							SessionMssagesDAO emails = new SessionMssagesDAO();
							String email =emails.GetEmail(id,"USER");
							
							int[] ids1 = new int[3];
							//GETTING THE USERID, ADVISORID, AND REQUEST ID
							UserNotificationDAO id1 = new UserNotificationDAO();
							ids1 = id1.GetAdvisorId(String.valueOf(sessionId1));
							
							//Getting advisor name
							AdminNotificationDAO aName = new AdminNotificationDAO();
							String advisorName = aName.GetAdvisorName(String.valueOf(ids1[1]));
							String userComment = "Your communication to "+advisorName+" has been rejected. You'll received a mail with the details.";
							String href = "UserUpcomingSessionViewDetails?rId="+ids1[2];
							UserNotificationDAO user = new UserNotificationDAO();
							user.InsertNotification(userComment, href, String.valueOf(ids1[0]));
							
							String subject ="";
							String content ="";
							subject = "Your Communication has been rejected";
							content = "Hi, <br><br>We are sorry but your communication has been rejected. For further information please contact <b>Advisor Circuit at </b>contactus@advisorcircuit.com<br><h3>Message : </h3>" +userMessage+"<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
							SendMail mail = new SendMail(subject, content, email ,prop.getProperty("MAIL_ADMIN"));
							mail.start();
								response.getWriter().write("THE STATUS HAS BEEN CHANGED.PLEASE REFRESH THE PAGE");
						}else if (status.equals("APPROVED")) {
							int[] ids1 = new int[3];
							//GETTING THE USERID, ADVISORID, AND REQUEST ID
							UserNotificationDAO id1 = new UserNotificationDAO();
							ids1 = id1.GetAdvisorId(sessionId);
							//Getting username
							AdminNotificationDAO name = new AdminNotificationDAO();
							String uName = name.GetUserName(String.valueOf(ids1[0]));
							
							//Notify advisor
							String comment = uName + " sent you a message.";
							String href = "AdvisorUpcomingSessionViewDetails?rId="+ids1[2];
							AdvisorNotificationDAO notify = new AdvisorNotificationDAO();
							notify.InsertRequestNotification(comment, String.valueOf(ids1[1]) , href);
						}
					} 
				}else if (advisorMessageId != null && status != null) {
					SessionMssagesDAO setStatus1 = new SessionMssagesDAO();
					Boolean isStatusCommit1 = setStatus1.SetAdvisorMessage(advisorMessageId, status);
					if(isStatusCommit1){
						if(status.equals("REJECTED")){
							//Getting Message.
							SessionMssagesDAO message = new SessionMssagesDAO(); 
							MessageDTO messages = new MessageDTO();
							messages = message.GetMessage(advisorMessageId,"ADVISOR");
							int sessionId1 = messages.getSessionId();
							String advisorMessage = messages.getAdvisorMessage();
							//Getting the user Id;
							SessionMssagesDAO ids = new SessionMssagesDAO();
							int id = ids.GetId(sessionId1, "ADVISOR");
							//Getting user/advisor email 
							SessionMssagesDAO emails = new SessionMssagesDAO();
							String email =emails.GetEmail(id,"ADVISOR");
							
							int[] ids1 = new int[3];
							//GETTING THE USERID, ADVISORID, AND REQUEST ID
							UserNotificationDAO id1 = new UserNotificationDAO();
							ids1 = id1.GetAdvisorId(String.valueOf(sessionId1));
							
							//Getting USERNAME 
							AdminNotificationDAO aName = new AdminNotificationDAO();
							String userName = aName.GetUserName(String.valueOf(ids1[0]));
							String comment = "Your communication to "+userName+" has been rejected. You'll received a mail with the details.";
							String href = "AdvisorUpcomingSessionViewDetails?rId="+ids1[2];
							AdvisorNotificationDAO advisor = new AdvisorNotificationDAO();
							advisor.InsertRequestNotification(comment, String.valueOf(String.valueOf(ids1[1])), href);
							
							
							String subject ="";
							String content ="";
							subject = "Your Communication has been rejected";
							content = "Hi, <br><br>We are sorry but your communication has been rejected. For further information please contact <b>Advisor Circuit at </b>contactus@advisorcircuit.com<br><h3>Message : </h3>" +advisorMessage+"<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
							SendMail mail = new SendMail(subject, content,email ,prop.getProperty("MAIL_ADMIN"));
							mail.start();
								response.getWriter().write("THE STATUS HAS BEEN CHANGED.PLEASE REFRESH THE PAGE");
							}else{
								
								int[] ids1 = new int[3];
								//GETTING THE USERID, ADVISORID, AND REQUEST ID
								UserNotificationDAO id1 = new UserNotificationDAO();
								ids1 = id1.GetAdvisorId(sessionId);
								
								//Getting advisor name
								AdminNotificationDAO aName = new AdminNotificationDAO();
								String advisorName = aName.GetAdvisorName(String.valueOf(ids1[1]));
								//Notify user
								String comment = advisorName + " sent you a message.";
								String href = "UserUpcomingSessionViewDetails?rId="+ids1[2];
								UserNotificationDAO user = new UserNotificationDAO();
								user.InsertNotification(comment, href, String.valueOf(ids1[0]));
								
							}
						}
					}
			logger.info("Exit doPost method of AdminSessionMessagesAndFileController");
				}
			}
		}

