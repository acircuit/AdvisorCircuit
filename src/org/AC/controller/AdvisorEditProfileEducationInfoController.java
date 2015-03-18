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
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorEditProfileEducationInfoController
 */
@WebServlet("/AdvisorEditProfileEducationInfoController")
public class AdvisorEditProfileEducationInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorEditProfileEducationInfoController.class); 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorEditProfileEducationInfoController");
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("advisorId");
		}catch(Exception e){
			response.sendRedirect("Error");
		}
		List<AdvisorEducationDTO> educations = new ArrayList<AdvisorEducationDTO>();
		if(advisorId != 0){
				AdvisorEducationDTO education = new AdvisorEducationDTO();
				//Getting Education Information of the advisor
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				educations = dao.GetEducationInfo(advisorId);
				if(educations.size() >0 ){
					request.setAttribute("education",educations);
					request.setAttribute("edit", true);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/EducationInfo.jsp");
			        rd.forward(request, response);
				}
		}
		logger.info("Exit doGet method of AdvisorEditProfileEducationInfoController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorEditProfileEducationInfoController");
		int aId = 0;
		try{
			aId = (int) request.getSession().getAttribute("advisorId");
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
						response.sendRedirect("ProfessionalBackgroundEdit");
				}
		}

		
		logger.info("Entered doPost method of AdvisorEditProfileEducationInfoController");
		}
	}

}
