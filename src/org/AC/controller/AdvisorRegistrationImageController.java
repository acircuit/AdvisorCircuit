package org.AC.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.RegistrationCompleteMail;
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
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationServicesController.class); 
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the image file and put into required folder and update the registration status.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorRegistrationImageController");	
		/*int advisorId = 0;
		String email ="";
		String name = "";
		advisorId = (int) request.getSession().getAttribute("aId");
		email = (String) request.getSession().getAttribute("email");
		name = (String) request.getSession().getAttribute("name");
		if(advisorId != 0 && !("").equals(email)){
			*/
		String email ="uday@gmail.com";
			//Calling util method which will put the image in the required folder.
			SetFormImage image = new SetFormImage();
			String url = image.putImage(request, response, email);
			/*
			if(!url.isEmpty() && !("").equals(url)){
				//Now put the value Image URL and registration status in the advisorregistrationprofiledetails table.
				
				String status = "RegistrationComplete.jsp";
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				Boolean isImageCommit = dao.setImageURL(advisorId, url, status);
				
				if(isImageCommit){
					//Mail the admin
					RegistrationCompleteMail mail = new RegistrationCompleteMail();
					mail.SendMail(name , email);
					response.sendRedirect("RegistrationComplete.jsp");
				}
			}
		}else{
			response.sendRedirect("Error.jsp");
		}*/
		logger.info("Entered doPost method of AdvisorRegistrationImageController");	
	}

}
