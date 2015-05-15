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
	private static final Logger logger = Logger
			.getLogger(DeactivateAdvisorController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of DeactivateAdvisorController");
		String activate = (String) request.getParameter("activate");
		String aId = request.getParameter("aId");
		if (aId != null && activate == null) {

			AdminAdvisorDAO dao = new AdminAdvisorDAO();
			Boolean isFlagCommit = dao.SetAdvisorFlag(aId, false);
			if (isFlagCommit) {
				response.getWriter().write(
						"The Advisor has been deactivated.Please Refresh");
			}
		} else if (aId != null && activate != null) {
			AdminAdvisorDAO dao = new AdminAdvisorDAO();
			Boolean isStatusCommit = dao.SetAdvisorFlag(aId, true);
			if (isStatusCommit) {
				response.getWriter().write(
						"The Advisor has been activated.Please Refresh");
			}
		}
		logger.info("Exit doPost method of DeactivateAdvisorController");
	}
}
