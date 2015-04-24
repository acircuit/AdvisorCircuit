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
import org.AC.Util.AdvisorEducationObject;
import org.AC.Util.SendMail;
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
		String[] education = request.getParameterValues("education[]");
		if(aId != 0){
			if(education.length > 0){
				//Deleting the Education Details
				AdvisorRegistrationDAO deledu = new AdvisorRegistrationDAO();
				Boolean isDeleted = deledu.DeleteEducation(aId);
				//Calling DAO to put the values into table
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				Boolean isEducationInfoCommit = dao.setEducationInfo(education,aId);
				if(isEducationInfoCommit){
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
					 content = "Hi, <br><br>An Advisor just edited his profile. Following are the details: <br> Advisor Id : "+aId+"<br>Section : Education Info <br><img src=http://www.advisorcircuit.com/assets/img/logo_black.png\" style='float:right' width='25%'>";
					 SendMail mail = new SendMail(subject, content,prop.getProperty("MAIL_ADMIN") ,prop.getProperty("MAIL_ADMIN"));
					 mail.start();
						response.sendRedirect("ProfessionalBackgroundEdit");
				}
		}

		
		logger.info("Entered doPost method of AdvisorEditProfileEducationInfoController");
		}
	}

}
