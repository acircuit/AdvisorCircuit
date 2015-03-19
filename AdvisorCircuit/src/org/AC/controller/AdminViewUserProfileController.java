 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 10/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminUserDAO;
import org.AC.Util.CreateUserFormPDF;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class will get the email from the admin and get the userId from the userdetails table.
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminViewUserProfileController
 */
@WebServlet("/AdminViewUserProfileController")
public class AdminViewUserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminViewUserProfileController.class); 
	
 	/**************************************COMMENTS***************************************************
	 * This class will get the email from the admin and get the UserId from the userdetails table.
	 *   and then fetch the user details
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdminViewUserProfileController");	
		String email = request.getParameter("email");
		if(email != null){
	    	UserDetailsDTO user = new UserDetailsDTO();
	    	AdminUserDAO userDetails = new AdminUserDAO();
	    	user = userDetails.GetUserDetails(email);
	    	if(user.getUserId() != 0 && user.getEmail() != null){
	    		CreateUserFormPDF pdf = new CreateUserFormPDF();
	    		pdf.createPDF(response, user.getUserId(), user.getEmail(), user.getFullName(), user.getPhone(), user.getAge(), user.getOccupation(), user.getImage(),user.getDateOfRegistration(),user.getIsActive());
	    		
	    	}
		}		
		logger.info("Entered doGet method of AdminViewUserProfileController");	
	}
}
