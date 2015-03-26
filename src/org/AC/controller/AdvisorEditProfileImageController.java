package org.AC.controller;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorEditProfileDAO;
import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.JDBC.ConnectionFactory;
import org.AC.Util.GetRelativeImageURL;
import org.AC.Util.SendMail;
import org.AC.Util.SetFormImage;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorEditProfileImageController
 */
@WebServlet("/AdvisorEditProfileImageController")
@MultipartConfig
public class AdvisorEditProfileImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorEditProfileImageController.class); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorEditProfileServicesController");
		int advisorId = 0;
		String path = "";
		try{
	    advisorId = (int) request.getSession().getAttribute("advisorId");
		}catch(Exception e){
			response.sendRedirect("Error");
		}
		if(advisorId != 0){
			AdvisorRegistrationDAO image = new AdvisorRegistrationDAO();
			path = image.GetImagePath(advisorId);
			GetRelativeImageURL relImage = new GetRelativeImageURL();
			path = relImage.getImageURL(path);
		}
		request.setAttribute("edit",true);
		request.setAttribute("path",path);
		logger.info("Exit doGet method of AdvisorEditProfileServicesController");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorEditProfileImageController");		
		String email = "";
		int aId = 0;
			try{
				 email = (String) request.getSession().getAttribute("email");
				aId = (int) request.getSession().getAttribute("advisorId");
				}catch(Exception e){
					response.sendRedirect("Email");
			}
			
			//Calling util method which will put the image in the required folder.
			SetFormImage image = new SetFormImage();
			String url = image.putImage(request, response, email,"ADVISOR");
			if(!url.isEmpty() && !("").equals(url)){
				//Now put the value Image URL and registration status in the advisorregistrationprofiledetails table.	
				String status = "RegistrationComplete.jsp";
				AdvisorEditProfileDAO dao = new AdvisorEditProfileDAO();
				Boolean isImageCommit = dao.setImageURL(aId, url);
				
				if(isImageCommit){
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
					 content = "Hi, <br><br>An Advisor just edited his profile. Following are the details: <br> Advisor Id : "+aId+"<br>Section : IMAGE <br><img src=http://www.advisorcircuit.com/assets/img/logo_black.png\" style='float:right' width='25%'>";
					 SendMail mail = new SendMail(subject, content,prop.getProperty("MAIL_ADMIN") ,prop.getProperty("MAIL_ADMIN"));
					 mail.start();
					response.sendRedirect("AdvisorProfile?aId="+aId);
				}
			}
			logger.info("Entered doPost method of AdvisorEditProfileImageController");	
		}
}
