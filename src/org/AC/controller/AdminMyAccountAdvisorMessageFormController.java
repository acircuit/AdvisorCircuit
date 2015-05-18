package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorNotificationDAO;
import org.AC.DAO.MessageDAO;
import org.AC.Util.SendMail;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminMyAccountAdvisorMessageFormController
 */
@WebServlet("/AdminMyAccountAdvisorMessageFormController")
public class AdminMyAccountAdvisorMessageFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountAdvisorMessageFormController.class);
 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdminMyAccountAdvisorMessageFormController");
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
		if(isError!= null && !isError){
			String messageId = request.getParameter("mId");
			String aId = request.getParameter("aId");
			String adminMessage = request.getParameter("adminmessage");
			String email = request.getParameter("email");
			Boolean isMessageCommit = false;
			if(messageId != null && adminMessage != null){
				//Put the message in the advisormessageadmin table
				 MessageDAO dao = new MessageDAO();
				 isMessageCommit = dao.setAdminMessage(adminMessage, messageId);
				 if(isMessageCommit){
					 Properties prop = new Properties();
			         InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
			         try {
						prop.load(resourceAsStream);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			         String comment = "You've got a message from our team.";
			         String href = "AdvisorMessageAdmin";
			         AdvisorNotificationDAO advisor = new AdvisorNotificationDAO();
			         advisor.InsertRequestNotification(comment, aId, href);
			         
					 String subject ="";
					String content ="";
					subject = "You received a communication by Admin";
					content = "Hi, <br><br>You've received a message from the Advisor Circuit Administrator. The message is given below: <br>"+adminMessage+" <br><br>Cheers, <br> Team Advisor Circuit<img src=http://www.advisorcircuit.com/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content,email ,prop.getProperty("MAIL_ADMIN"));
					mail.start();
					 request.setAttribute("email", email);
					 RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminAdvisorMessage");
				     rd.forward(request, response);
				 }
			}
		}
		logger.info("Exit doPost method of AdminMyAccountAdvisorMessageFormController");
	}

}
