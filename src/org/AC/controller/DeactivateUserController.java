 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 10/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminAdvisorDAO;
import org.AC.DAO.AdminUserDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will deacivate the user by setting the isActive flag in the userdetails table to false.
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class DeactivateUserController
 */
@WebServlet("/DeactivateUserController")
public class DeactivateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeactivateUserController.class);


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doGet method of DeactivateUserController");
		String uId = request.getParameter("uId");
		if(uId != null){
			AdminUserDAO dao = new AdminUserDAO();
			Boolean isFlagCommit = dao.SetUserFlag(uId);
			if(isFlagCommit){
				response.sendRedirect("AdminMyAccount.jsp");
			}
		}
	
		logger.info("Exit doGet method of DeactivateUserController");
	}
}
