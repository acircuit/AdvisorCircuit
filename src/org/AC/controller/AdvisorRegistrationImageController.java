package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.SendMail;
import org.AC.Util.SetFormImage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationImageController
 */
@WebServlet("/AdvisorRegistrationImageController")
@MultipartConfig
public class AdvisorRegistrationImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationImageController.class); 
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Entered doGet method of AdvisorRegistrationImageController");
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("aId");
		}catch(Exception e){
			response.sendRedirect("Email");
		}
		if(advisorId != 0){
			//Getting the status of the registration process.
			AdvisorRegistrationDAO status = new AdvisorRegistrationDAO();
			String stat = status.getStatus(advisorId);
			response.sendRedirect(stat);
		}
		logger.info("Exit doGet method of AdvisorRegistrationImageController");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the image file and put into required folder and update the registration status.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorRegistrationImageController");	
		String email = "";
		Properties prop = new Properties();
	    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop.load(resourceAsStream);
		int aId = 0;
			try{
				 email = (String) request.getSession().getAttribute("email");
				aId = (int) request.getSession().getAttribute("aId");
				}catch(Exception e){
					response.sendRedirect("Email");
			}
			
			//Calling util method which will put the image in the required folder.
			SetFormImage image = new SetFormImage();
			String url = image.putImage(request, response, email,"ADVISOR");
			if(!url.isEmpty() && !("").equals(url)){
				//Now put the value Image URL and registration status in the advisorregistrationprofiledetails table.
				
				String status = "RegistrationComplete.jsp";
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				Boolean isImageCommit = dao.setImageURL(aId, url, status);
				
				if(isImageCommit){
					//Notify Admin
					String comment = email+" completed the registration process";
					String href = "AdvisorProfile?aId="+aId+"&admin=true";
					AdminNotificationDAO notify = new AdminNotificationDAO();
					notify.InsertNotification(comment, href);
					//Mail the admin
					String subject = "New Registration by Advisor!!!!!";
					String content = "Hi, <br><br>An advisor registered with us. Following are the details: <br>Email Id :</h3>" +email+"<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content,prop.getProperty("MAIL_ADMIN"),email);
					mail.start();
					response.sendRedirect("RegistrationComplete");
				}
			}
			logger.info("Entered doPost method of AdvisorRegistrationImageController");	
		}
}
