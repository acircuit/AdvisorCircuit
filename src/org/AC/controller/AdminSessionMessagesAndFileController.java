package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.SessionMssagesDAO;
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
	private static final Logger logger = Logger.getLogger(AdminSessionMessagesAndFileController.class);     
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdminSessionMessagesAndFileController");
		Boolean isAdmin = false;
		Boolean isError = false;
		try{
			isAdmin = (Boolean) request.getSession().getAttribute("admin"); 
			}catch(Exception e){
				response.sendRedirect("Error");
				isError = true;
			}
		if(isAdmin == null){
			isError = true;
			response.sendRedirect("Error");
		}
		if(isError!= null &&  !isError){
		String fileId = (String)request.getParameter("fileId");
			String userMessageId = (String)request.getParameter("userMessageId");	
			String advisorMessageId = (String)request.getParameter("advisorMessageId");
			String status =(String)request.getParameter("status");
			Properties prop = new Properties();
		    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
		    prop.load(resourceAsStream);
			if(fileId != null && status != null){
				SessionMssagesDAO setStatus = new SessionMssagesDAO();
				Boolean isStatusCommit = setStatus.SetFileStatus(fileId, status);
				if(isStatusCommit){
					if(status.equals("REJECTED")){
						//Getting File Purpose.
						FilesDTO file = new FilesDTO();
						SessionMssagesDAO purpose = new SessionMssagesDAO();
						file = purpose.GetPurpose(fileId);
						String uploadedBy = file.getUploadedBy();
						String comment = file.getComment();
						int id = 0;
						int sessionId = file.getSessionId();
						//Getting the advisor Id;
						SessionMssagesDAO ids = new SessionMssagesDAO();
						id = ids.GetId(sessionId, uploadedBy);
						//Getting user/advisor email 
						SessionMssagesDAO emails = new SessionMssagesDAO();
						String email =emails.GetEmail(id,uploadedBy);
						String subject ="";
						String content ="";
						subject = "Your Communication has been rejected";
						content = "Hi,<br>We are sorry but your communication has been rejected. For further information please contact <b>Advisor Circuit at </b>contactus@advisorcircuit.com<br><h3>File Purpose : </h3>" +comment+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
						SendMail mail = new SendMail(subject, content,email ,prop.getProperty("MAIL_ADMIN"));
						mail.start();
						response.getWriter().write("THE STATUS HAS BEEN CHANGED.PLEASE REFRESH THE PAGE");
	
	
	
					}
				}
			}else if (userMessageId != null && status != null) {
					SessionMssagesDAO setStatus1 = new SessionMssagesDAO();
					Boolean isStatusCommit1 = setStatus1.SetUserMessage(userMessageId, status);
					if(isStatusCommit1){
						if(status.equals("REJECTED")){
							//Getting Message.
							SessionMssagesDAO message = new SessionMssagesDAO(); 
							MessageDTO messages = new MessageDTO();
							messages = message.GetMessage(userMessageId,"USER");
							int sessionId = messages.getSessionId();
							String userMessage = messages.getAdvisorMessage();
							//Getting the user Id;
							SessionMssagesDAO ids = new SessionMssagesDAO();
							int id = ids.GetId(sessionId, "USER");
							//Getting user/advisor email 
							SessionMssagesDAO emails = new SessionMssagesDAO();
							String email =emails.GetEmail(id,"USER");
							String subject ="";
							String content ="";
							subject = "Your Communication has been rejected";
							content = "Hi,<br>We are sorry but your communication has been rejected. For further information please contact <b>Advisor Circuit at </b>contactus@advisorcircuit.com<br><h3>Message : </h3>" +userMessage+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
							SendMail mail = new SendMail(subject, content, email ,prop.getProperty("MAIL_ADMIN"));
							mail.start();
								response.getWriter().write("THE STATUS HAS BEEN CHANGED.PLEASE REFRESH THE PAGE");
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
							int sessionId = messages.getSessionId();
							String advisorMessage = messages.getAdvisorMessage();
							//Getting the user Id;
							SessionMssagesDAO ids = new SessionMssagesDAO();
							int id = ids.GetId(sessionId, "ADVISOR");
							//Getting user/advisor email 
							SessionMssagesDAO emails = new SessionMssagesDAO();
							String email =emails.GetEmail(id,"ADVISOR");
							String subject ="";
							String content ="";
							subject = "Your Communication has been rejected";
							content = "Hi,<br>We are sorry but your communication has been rejected. For further information please contact <b>Advisor Circuit at </b>contactus@advisorcircuit.com<br><h3>Message : </h3>" +advisorMessage+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
							SendMail mail = new SendMail(subject, content,email ,prop.getProperty("MAIL_ADMIN"));
							mail.start();
								response.getWriter().write("THE STATUS HAS BEEN CHANGED.PLEASE REFRESH THE PAGE");
							}
						}
					}
		}
				logger.info("Exit doPost method of AdminSessionMessagesAndFileController");
	}

}
