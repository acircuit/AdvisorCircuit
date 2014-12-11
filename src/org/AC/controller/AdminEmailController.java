 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 08/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminAdvisorDAO;
import org.AC.DAO.MessageDAO;
import org.AC.dto.MessageDTO;

/********************************CLASS SUMMARY*****************************************************
* 
* This class will get the email from the admin and get the AdvisorId from the advisordetails table.
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminEmailController
 */
@WebServlet("/AdminEmailController")
public class AdminEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
 	/**************************************COMMENTS***************************************************
	 * This class will get the email from the admin and get the AdvisorId from the advisordetails table.
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if(email != null){
			AdminAdvisorDAO dao = new AdminAdvisorDAO();
			int advisorId = dao.GetAdvisorId(email);
			if(advisorId != 0){
				request.setAttribute("aId",advisorId);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Deactivate_Delete.jsp");
		        rd.forward(request, response);
				}else{
					Boolean isInvalid = true;
					request.setAttribute("isInvalidEmail",isInvalid);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Deactivate_Email.jsp");
			        rd.forward(request, response);
				}
			}
		
		
		}
}	
