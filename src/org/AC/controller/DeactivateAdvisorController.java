 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 8/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminAdvisorDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will deacivate the advisor by setting the isActive flag in the advisordetails table to false.
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class DeactivateAdvisorController
 */
@WebServlet("/DeactivateAdvisorController")
public class DeactivateAdvisorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeactivateAdvisorController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doGet method of DeactivateAdvisorController");
		String aId = request.getParameter("aId");
		if(aId != null){
			AdminAdvisorDAO dao = new AdminAdvisorDAO();
			Boolean isFlagCommit = dao.SetAdvisorFlag(aId);
			if(isFlagCommit){
				response.sendRedirect("AdminMyAccount.jsp");
			}
		}
		logger.info("Exit doGet method of DeactivateAdvisorController");
	}
}
