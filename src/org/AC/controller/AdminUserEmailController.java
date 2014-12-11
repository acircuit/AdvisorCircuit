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

import org.AC.DAO.AdminAdvisorDAO;
import org.AC.DAO.AdminUserDAO;
import org.AC.dto.UserDetailsDTO;
/********************************CLASS SUMMARY*****************************************************
* 
* This class will get the email from the admin and get the UserId from the userdetails table.
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminUserEmailController
 */
@WebServlet("/AdminUserEmailController")
public class AdminUserEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**************************************COMMENTS***************************************************
	 * This method will get the email from the admin and get the userId from the userdetails table.
	 *
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		UserDetailsDTO userDetails = new UserDetailsDTO();
		int userId = 0;
		if(email != null){
			AdminUserDAO user = new AdminUserDAO();
			userDetails = user.GetUserDetails(email);
			userId = userDetails.getUserId();
			if(userId != 0){
				request.setAttribute("uId",userId);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Deactivate_Delete_User.jsp");
		        rd.forward(request, response);
				}else{
					Boolean isInvalid = true;
					request.setAttribute("isInvalidEmail",isInvalid);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Deactivate_User_Email.jsp");
			        rd.forward(request, response);
				}
			}
	}

}
