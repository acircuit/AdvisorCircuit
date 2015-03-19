package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.SendMail;
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.Logger;
   
/**
 * Servlet implementation class AdvisorEditOtherinfoController
 */
@WebServlet("/AdvisorEditOtherinfoController")
public class AdvisorEditOtherinfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorEditOtherinfoController.class); 
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorEditOtherinfoController");
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("advisorId");
		}catch(Exception e){
			response.sendRedirect("Error");
		}
		if(advisorId != 0){
			List<AdvisorProfileDTO> achievements = new ArrayList<AdvisorProfileDTO>();
			List<AdvisorProfileDTO> keySkill = new ArrayList<AdvisorProfileDTO>();
			String know = "";
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
					request.setAttribute("edit",true);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/OtherInfo.jsp");
			        rd.forward(request, response);
				}
		}
		logger.info("Exit doGet method of AdvisorEditOtherinfoController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorEditOtherinfoController");
		int aId = 0;
		try{
		    aId = (int) request.getSession().getAttribute("advisorId");
			}catch(Exception e){
				response.sendRedirect("Error");
			}
		String[] achievement = request.getParameterValues("achievement[]");
		String[] keyskills =request.getParameterValues("keyskills[]");
		String hobbies =request.getParameter("hobbies");
		Boolean isHobbyCommit = false;
		Boolean isAchieve= true;
		if(aId != 0){
			if(!hobbies.isEmpty() && keyskills.length >0){
				//Deleting Previous Achievements.
				AdvisorRegistrationDAO achieve = new AdvisorRegistrationDAO();
				Boolean isDelete = achieve.RemoveAwards(aId);
				
				//Deleting Previous Key Skills.
				AdvisorRegistrationDAO keySkills = new AdvisorRegistrationDAO();
				Boolean isDeleteSkill = keySkills.RemoveSkills(aId);
				//Setting the achievements .
				if(achievement.length > 0){
				AdvisorRegistrationDAO awards = new AdvisorRegistrationDAO();
				awards.setAchievements(aId,achievement);
				}
				//Setting the KeySkills
				AdvisorRegistrationDAO skills = new AdvisorRegistrationDAO();
				Boolean isSkillCommit = skills.setSkills(aId, keyskills);
				if(isSkillCommit){
					//Setting the Hobbies
					AdvisorRegistrationDAO hobby = new AdvisorRegistrationDAO();
					isHobbyCommit = hobby.setHobbies(aId, hobbies);
				}
				if(isHobbyCommit){
					Properties prop = new Properties();
			         InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
			         try {
						prop.load(resourceAsStream);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 String subject ="";
					 String content ="";
					 subject = "An Advisor just edited his profile";
					 content = "Hi, <br><br>An Advisor just edited his profile. Following are the details: <br> Advisor Id : "+aId+"<br>Section : Other Info <br><img src=http://www.advisorcircuit.com/assets/img/logo_black.png\" style='float:right' width='25%'>";
					 SendMail mail = new SendMail(subject, content,prop.getProperty("MAIL_ADMIN") ,prop.getProperty("MAIL_ADMIN"));
					 mail.start();
					response.sendRedirect("ServicesEdit");
				}
			}
		}
		logger.info("Ext doPost method of AdvisorEditOtherinfoController");
	}

}
