package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.AdvisorEducationObject;
import org.AC.dto.AdvisorEducationDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationEducationInfoController
 */
@WebServlet("/AdvisorRegistrationEducationInfoController")
public class AdvisorRegistrationEducationInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationEducationInfoController.class); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Entered doGet method of AdvisorRegistrationEducationInfoController");
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("aId");
		}catch(Exception e){
			response.sendRedirect("Email");
		}
		String fromTab = request.getParameter("tab");
		List<AdvisorEducationDTO> educations = new ArrayList<AdvisorEducationDTO>();
		if(advisorId != 0){
			if (fromTab != null && fromTab.equals("true")) {
				AdvisorEducationDTO education = new AdvisorEducationDTO();
				//Getting Education Information of the advisor
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				educations = dao.GetEducationInfo(advisorId);
				if(educations.size() >0 ){
					request.setAttribute("education",educations);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/EducationInfo.jsp");
			        rd.forward(request, response);
				}else{
					//Getting the status of the registration process.
					AdvisorRegistrationDAO status = new AdvisorRegistrationDAO();
					String stat = status.getStatus(advisorId);
					response.sendRedirect(stat);
				}
			}else{
					AdvisorEducationDTO education = new AdvisorEducationDTO();
					//Getting Education Information of the advisor
					AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
					educations = dao.GetEducationInfo(advisorId);
					request.setAttribute("education",educations);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/EducationInfo.jsp");
			        rd.forward(request, response);
			}
		}
		logger.info("Exit doGet method of AdvisorRegistrationEducationInfoController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the education info from the form and put them in the required table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Entered doPost method of AdvisorRegistrationEducationInfoController");
		int aId = 0;
		try{
			aId = (int) request.getSession().getAttribute("aId");
			}catch(Exception e){
				response.sendRedirect("Email");
			}
		String ug = request.getParameter("ug");
		String pg = request.getParameter("pg");
		String[] others = request.getParameterValues("others[]");
		if(aId != 0){
			if(!ug.isEmpty()){
				List<AdvisorEducationDTO> education = new ArrayList<AdvisorEducationDTO>();
				//Deleting the Education Details
				AdvisorRegistrationDAO deledu = new AdvisorRegistrationDAO();
				Boolean isDeleted = deledu.DeleteEducation(aId);
				//Getting Education info object
				AdvisorEducationObject obj = new AdvisorEducationObject();
				education = obj.getEducationObject(aId,ug, pg, others);
				//Calling DAO to put the values into table
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				Boolean isEducationInfoCommit = dao.setEducationInfo(education);
				if(isEducationInfoCommit){
					//Changing the status of the Advisor To ProfessionalBackground.jsp
					AdvisorRegistrationDAO status = new AdvisorRegistrationDAO();
					Boolean isStatusCommit = status.setRegistrationStatus(aId, "ProfessionalBackground.jsp");
					if(isStatusCommit){
						response.sendRedirect("AdvisorRegistrationProfessionalBackground");
					}
			}
		}

		
		logger.info("Entered doPost method of AdvisorRegistrationEducationInfoController");
		}
	}
}
