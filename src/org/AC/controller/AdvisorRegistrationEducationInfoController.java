package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationEducationInfoController
 */
@WebServlet("/AdvisorRegistrationEducationInfoController")
public class AdvisorRegistrationEducationInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationEducationInfoController.class); 


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the education info from the form and put them in the required table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorRegistrationEducationInfoController");
		int aId = 0;
		aId = (int) request.getSession().getAttribute("aId");
		String ug = request.getParameter("ug");
		String pg = request.getParameter("pg");
		String others = request.getParameter("others");
		if(aId != 0){
			if(!ug.isEmpty() && !pg.isEmpty() && !others.isEmpty()){
				
				//Calling DAO to put the values into table
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				Boolean isEducationInfoCommit = dao.setEducationInfo(aId, ug, pg, others);
				if(isEducationInfoCommit){
					response.sendRedirect("OtherInfo.jsp");
				}
			}
		}else{
			response.sendRedirect("Error.jsp");
		}

		
		logger.info("Entered doPost method of AdvisorRegistrationEducationInfoController");
	}

}
