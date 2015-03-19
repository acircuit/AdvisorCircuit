package org.AC.controller;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorEditProfileDAO;
import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.JDBC.ConnectionFactory;
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
					response.sendRedirect("AdvisorProfile?aId="+aId);
				}
			}
			logger.info("Entered doPost method of AdvisorEditProfileImageController");	
		}
}
