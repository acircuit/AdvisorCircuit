 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 08/12/2014
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

/********************************CLASS SUMMARY*****************************************************
* 
* This class will get the email from the admin and get the AdvisorId from the advisordetails table.
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminAdvisorProfileEditController
 */
@WebServlet("/AdminAdvisorProfileEditController")
public class AdminAdvisorProfileEditController extends HttpServlet {
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
		if(email != null){
			AdminAdvisorDAO dao = new AdminAdvisorDAO();
			int advisorId = dao.GetAdvisorId(email);
			if(isError!= null && !isError){
				if(advisorId != 0){
					
				}else{
					Boolean isInvalid = true;
					request.setAttribute("isInvalidEmail",isInvalid);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/advisor_profile_edit_email_admin.jsp");
			        rd.forward(request, response);
				}
			}
		}
	}

}
