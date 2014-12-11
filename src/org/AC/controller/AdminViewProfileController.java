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
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class will get the email from the admin and get the AdvisorId from the advisordetails table.
* Then send the advisor id for the advisor profile servlet.
* 
*
***************************************************************************************************/

/**
 * Servlet implementation class AdminViewProfileController
 */
@WebServlet("/AdminViewProfileController")
public class AdminViewProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminViewProfileController.class); 
       
 	/**************************************COMMENTS***************************************************
	 * This class will get the email from the admin and get the AdvisorId from the advisordetails table.
	 * Then send the advisor id for the advisor profile servlet.
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
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdminViewProfileController");
		String email = request.getParameter("email");
		if(email != null){
			AdminAdvisorDAO dao = new AdminAdvisorDAO();
			int advisorId = dao.GetAdvisorId(email);
			if(advisorId != 0){
				response.sendRedirect("AdvisorProfileServlet?aId=" + advisorId + "&admin=true");
			}else{
				Boolean isInvalid = true;
				request.setAttribute("isInvalidEmail",isInvalid);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_View_Profile_Email.jsp");
		        rd.forward(request, response);
			}
		}
		
		logger.info("EXit doPost method of AdminViewProfileController");
	}

}
