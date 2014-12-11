package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.DAO.ProfessionalBackgroundDAO;
import org.AC.Util.ProfessionalBackgroundObject;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationProfessionalBackgroundControler
 */
@WebServlet("/AdvisorRegistrationProfessionalBackgroundControler")
public class AdvisorRegistrationProfessionalBackgroundControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationProfessionalBackgroundControler.class); 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the PROFESSIONAL BACKGROND from the form and put them in the required table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorRegistrationProfessionalBackgroundControler");
		int aId = 0;
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		aId = (int) request.getSession().getAttribute("aId");
		//Retrieving Advisor Professional Background
		String[] company = request.getParameterValues("company[]");
		String[] designation = request.getParameterValues("designation[]");
		String[] duration = request.getParameterValues("duration[]");
		String[] description = request.getParameterValues("description[]");
		
		//This is for setting the professional background of the advisor
		if(aId != 0){
			if(company.length > 0  && designation.length > 0 && description.length > 0 && duration.length > 0) {
				 
				 ProfessionalBackgroundObject professionalBackground = new ProfessionalBackgroundObject();
				 list =  professionalBackground.getProfessionalBackgroundObject(company,designation,duration,description);
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				Boolean isProfessionalBackgorundCommit = dao.addProfessionalBackground(list, aId);
				if(isProfessionalBackgorundCommit){
					String status = "Services.jsp";
					//Change the Registration Status in advisorregistrationprofiledetails table
					AdvisorRegistrationDAO dao1 = new AdvisorRegistrationDAO();	
					Boolean isRegistrationStatusCommit = dao1.setRegistrationStatus(aId, status);
					if(isRegistrationStatusCommit){
						response.sendRedirect(status);
					}
				}
			}
		}else{
			response.sendRedirect("Error.jsp");
		}
		
		logger.info("Exit doPost method of AdvisorRegistrationProfessionalBackgroundControler");
	}
}
