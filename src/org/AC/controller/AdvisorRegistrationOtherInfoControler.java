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
 * Servlet implementation class AdvisorRegistrationOtherInfoControler
 */
@WebServlet("/AdvisorRegistrationOtherInfoControler")
public class AdvisorRegistrationOtherInfoControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationOtherInfoControler.class); 



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the other info from the form and put them in the required table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorRegistrationOtherInfoControler");
		int aId = 0;
		aId = (int) request.getSession().getAttribute("aId");
		String achievement = request.getParameter("achievement");
		String keyskills =request.getParameter("keyskills");
		String hobbies =request.getParameter("hobbies");
		String funfact =request.getParameter("funfact");
		if(aId != 0){
			if(!achievement.isEmpty() && !hobbies.isEmpty() && !keyskills.isEmpty() && !funfact.isEmpty()){
				
				//Calling DAO to put the values into table
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				Boolean isOtherInfoCommit = dao.setOtherInfo(aId, achievement,hobbies, keyskills, funfact);
				if(isOtherInfoCommit){
					response.sendRedirect("ProfessionalBackground.jsp");
				}
			}
		}else{
			response.sendRedirect("Error.jsp");
		}
		logger.info("Ext doPost method of AdvisorRegistrationOtherInfoControler");
			
	}

}
