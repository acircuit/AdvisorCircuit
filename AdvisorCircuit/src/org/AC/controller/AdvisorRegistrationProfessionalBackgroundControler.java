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
import org.AC.DAO.ProfessionalBackgroundDAO;
import org.AC.Util.ProfessionalBackgroundObject;
import org.AC.dto.AdvisorProfileDTO;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Entered doGet method of AdvisorRegistrationProfessionalBackgroundControler");
		int advisorId = 0;
		String experience="";
		try{
	    advisorId = (int) request.getSession().getAttribute("aId");
		}catch(Exception e){
			response.sendRedirect("Email");
		}
		String fromTab = request.getParameter("tab");
		if(advisorId != 0){
			if (fromTab != null && fromTab.equals("true")) {
				List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
				List<ProfessionalBackgroundDTO> descriptions = new ArrayList<ProfessionalBackgroundDTO>();
				List<Integer> profIds = new ArrayList<Integer>();
				//Getting Experience
				AdvisorRegistrationDAO exp = new AdvisorRegistrationDAO();
				experience = exp.GetExperience(advisorId);
				//Getting the company,designation,duration
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				list = dao.GetProfessionalBackground(advisorId);
				if(list.size() >0){
					for (ProfessionalBackgroundDTO professionalBackgroundDTO : list) {
						profIds.add(professionalBackgroundDTO.getProfId()); 
					}
					//Getting Professional Background description
					AdvisorRegistrationDAO desc = new AdvisorRegistrationDAO();
					descriptions = desc.GetProfessionalBackgroundDescription(profIds);
					//Creating an array string
					
					for (ProfessionalBackgroundDTO professional : list) {
						int i=0;
						String[] description = new String[10];
						for (ProfessionalBackgroundDTO profession : descriptions) {
							if(professional.getProfId() == profession.getProfId()){
								description[i]=profession.getDescription();
								i++;
							}
						}
						professional.setDescriptionArray(description);
					}
					request.setAttribute("list",list);
					request.setAttribute("experience",experience );
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/ProfessionalBackground.jsp");
			        rd.forward(request, response);
				}else{
					//Getting the status of the registration process.
					AdvisorRegistrationDAO status = new AdvisorRegistrationDAO();
					String stat = status.getStatus(advisorId);
					response.sendRedirect(stat);
				}
			}else{
				List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
				List<ProfessionalBackgroundDTO> descriptions = new ArrayList<ProfessionalBackgroundDTO>();
				List<Integer> profIds = new ArrayList<Integer>();
				//Getting Experience
				AdvisorRegistrationDAO exp = new AdvisorRegistrationDAO();
				experience = exp.GetExperience(advisorId);
				//Getting the company,designation,duration
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				list = dao.GetProfessionalBackground(advisorId);
				for (ProfessionalBackgroundDTO professionalBackgroundDTO : list) {
					profIds.add(professionalBackgroundDTO.getProfId()); 
				}
				//Getting Professional Background description
				AdvisorRegistrationDAO desc = new AdvisorRegistrationDAO();
				descriptions = desc.GetProfessionalBackgroundDescription(profIds);
				//Creating an array string
				
				for (ProfessionalBackgroundDTO professional : list) {
					int i=0;
					String[] description = new String[10];
					for (ProfessionalBackgroundDTO profession : descriptions) {
						if(professional.getProfId() == profession.getProfId()){
							description[i]=profession.getDescription();
							i++;
						}
					}
					professional.setDescriptionArray(description);
				}
				request.setAttribute("list",list);
				request.setAttribute("experience",experience );
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/ProfessionalBackground.jsp");
		        rd.forward(request, response);
			}
		}
		logger.info("Exit doGet method of AdvisorRegistrationProfessionalBackgroundControler");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the PROFESSIONAL BACKGROND from the form and put them in the required table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorRegistrationProfessionalBackgroundControler");
		int aId = 0;
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		try{
		aId = (int) request.getSession().getAttribute("aId");
		}catch(Exception e ){
			response.sendRedirect("Email");
		}
		//Retrieving Advisor Professional Background
		String intro = request.getParameter("intro");
		String experience = request.getParameter("experience");
		String[] company = request.getParameterValues("company[]");
		String[] designation = request.getParameterValues("designation[]");
		String[] duration = request.getParameterValues("duration[]");
		String[] description0 = request.getParameterValues("description0[]");
		String[] description1 = request.getParameterValues("description1[]");
		String[] description2 = request.getParameterValues("description2[]");
		String[] description3 = request.getParameterValues("description3[]");
		String[] description4 = request.getParameterValues("description4[]");
		String[] description5 = request.getParameterValues("description5[]");
		String[] description6 = request.getParameterValues("description6[]");
		String[] description7 = request.getParameterValues("description7[]");
		String[] description8 = request.getParameterValues("description8[]");
		String[] description9 = request.getParameterValues("description9[]");
		String[] description10 = request.getParameterValues("description10[]");
		//This is for setting the professional background of the advisor
		if(aId != 0){
			if(company.length > 0  && designation.length > 0 && duration.length > 0) {
				//Deleting the details from advisorprofessionalbackground table
				AdvisorRegistrationDAO profs = new AdvisorRegistrationDAO();
				profs.DeleteProfCompany(aId);
				AdvisorRegistrationDAO advisor = new AdvisorRegistrationDAO();
				advisor.DeleteProfDescription(aId);
				Boolean isDescriptionCommit = false; 
				
				//Updating the professional backgroung intro
				AdvisorRegistrationDAO  exp = new AdvisorRegistrationDAO();
				Boolean isExperience = exp.SetExperience(aId,experience);
				ProfessionalBackgroundObject professionalBackground = new ProfessionalBackgroundObject();
				list =  professionalBackground.getProfessionalBackgroundObject(company,designation,duration);
				//Setting the company, designation and duration
				AdvisorRegistrationDAO prof = new AdvisorRegistrationDAO();
				List<Integer> profId = prof.addProfessionalBackground(list,aId);
				//Getting description object
				if(profId != null && profId.size() >0){
					//Getting the description object
					ProfessionalBackgroundObject desc = new ProfessionalBackgroundObject();
					List<ProfessionalBackgroundDTO> descriptions = desc.getProfDescription(profId, description0,description1,description2,description3,description4,description5,description6,description7,description8,description9,description10);
					//Setting the description of the professional background
					if(descriptions.size() > 0){
						AdvisorRegistrationDAO professions = new AdvisorRegistrationDAO();
						isDescriptionCommit =	professions.setDescription(aId,descriptions);
					}
				}
					if(isDescriptionCommit){
						String status = "OtherInfo.jsp";
						//Change the Registration Status in advisorregistrationprofiledetails table
						AdvisorRegistrationDAO dao1 = new AdvisorRegistrationDAO();	
						Boolean isRegistrationStatusCommit = dao1.setRegistrationStatus(aId, status);
						if(isRegistrationStatusCommit){
							response.sendRedirect("AdvisorRegistrationOtherInfo");
						}
					}
			}
		}
		logger.info("Exit doPost method of AdvisorRegistrationProfessionalBackgroundControler");
	}
}
