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
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationOtherInfoControler
 */
@WebServlet("/AdvisorRegistrationOtherInfoControler")
public class AdvisorRegistrationOtherInfoControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationOtherInfoControler.class); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Entered doGet method of AdvisorRegistrationOtherInfoControler");
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("aId");
		}catch(Exception e){
			response.sendRedirect("Email");
		}
		String fromTab = request.getParameter("tab");
		String edit = request.getParameter("edit");

		if(advisorId != 0){
			List<AdvisorProfileDTO> achievements = new ArrayList<AdvisorProfileDTO>();
			List<AdvisorProfileDTO> keySkill = new ArrayList<AdvisorProfileDTO>();
			String know = "";
			if (fromTab != null && fromTab.equals("true")) {
				//Getting the Advisor Achievements
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				achievements = dao.GetAchievements(advisorId);
				
				//Getting the KeySkills
				AdvisorRegistrationDAO key = new AdvisorRegistrationDAO();
				keySkill = key.GetKeySkills(advisorId);
				
				//Getting Know your advisor
				AdvisorRegistrationDAO knowAdvisor = new AdvisorRegistrationDAO();
				know = knowAdvisor.GetKnowYourAdvisor(advisorId);
				if(keySkill.size() > 0){
					request.setAttribute("achievements",achievements);
					request.setAttribute("keySkill",keySkill);
					request.setAttribute("know",know);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/OtherInfo.jsp");
			        rd.forward(request, response);
				}else{
					//Getting the status of the registration process.
					AdvisorRegistrationDAO status = new AdvisorRegistrationDAO();
					String stat = status.getStatus(advisorId);
					response.sendRedirect(stat);
				}
			}else{
				//Getting the Advisor Achievements
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				achievements = dao.GetAchievements(advisorId);
				
				//Getting the KeySkills
				AdvisorRegistrationDAO key = new AdvisorRegistrationDAO();
				keySkill = key.GetKeySkills(advisorId);
				
				//Getting Know your advisor
				AdvisorRegistrationDAO knowAdvisor = new AdvisorRegistrationDAO();
				know = knowAdvisor.GetKnowYourAdvisor(advisorId);
				request.setAttribute("achievements",achievements);
				request.setAttribute("keySkill",keySkill);
				request.setAttribute("know",know);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/OtherInfo.jsp");
		        rd.forward(request, response);
			}
		}
		logger.info("Exit doGet method of AdvisorRegistrationOtherInfoControler");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the other info from the form and put them in the required table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Entered doPost method of AdvisorRegistrationOtherInfoControler");
		int aId = 0;
		Boolean isError = false;
		try{
		    aId = (int) request.getSession().getAttribute("aId");
			}catch(Exception e){
				response.sendRedirect("Email");
				isError = true;
			}
		String[] achievement = request.getParameterValues("achievement[]");
		String[] keyskills =request.getParameterValues("keyskills[]");
		String hobbies =request.getParameter("hobbies");
		String edit = request.getParameter("edit");
		if(edit == null){
			edit = "false";
		}
		Boolean isHobbyCommit = false;
		if(aId != 0){
			if( keyskills.length >0){
				//Deleting Previous Achievements.
				AdvisorRegistrationDAO achieve = new AdvisorRegistrationDAO();
				Boolean isDelete = achieve.RemoveAwards(aId);
				
				//Deleting Previous Key Skills.
				AdvisorRegistrationDAO keySkills = new AdvisorRegistrationDAO();
				Boolean isDeleteSkill = keySkills.RemoveSkills(aId);
				//Setting the achievements .
				if(achievement.length > 0 ){
				AdvisorRegistrationDAO awards = new AdvisorRegistrationDAO();
				awards.setAchievements(aId,achievement);
				}
				//Setting the KeySkills
				AdvisorRegistrationDAO skills = new AdvisorRegistrationDAO();
				Boolean isSkillCommit = skills.setSkills(aId, keyskills);
				if(isSkillCommit){
					if(!hobbies.isEmpty()){
						//Setting the Hobbies
						AdvisorRegistrationDAO hobby = new AdvisorRegistrationDAO();
						isHobbyCommit = hobby.setHobbies(aId, hobbies);
					}
				}
				if(!isError){
					if(isHobbyCommit  && edit.equals("true") ){
						response.sendRedirect("AdvisorRegistrationServices");
					}else{
						//Changing the status of the Advisor To ProfessionalBackground.jsp
						AdvisorRegistrationDAO status = new AdvisorRegistrationDAO();
						Boolean isStatusCommit = status.setRegistrationStatus(aId, "Services.jsp");
						if(isStatusCommit){
							response.sendRedirect("AdvisorRegistrationServices");
						}
					}
				}
			}
		}
		logger.info("Ext doPost method of AdvisorRegistrationOtherInfoControler");
			
	}

}
