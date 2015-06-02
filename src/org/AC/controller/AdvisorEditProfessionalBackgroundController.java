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

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.ProfessionalBackgroundObject;
import org.AC.Util.SendMail;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorEditProfessionalBackgroundController
 */
@WebServlet("/AdvisorEditProfessionalBackgroundController")
public class AdvisorEditProfessionalBackgroundController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(AdvisorEditProfessionalBackgroundController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorEditProfessionalBackgroundController");
		int advisorId = 0;
		String experience = "";
		try {
			advisorId = (int) request.getSession().getAttribute("advisorId");
		} catch (Exception e) {
			response.sendRedirect("Error");
		}
		if (advisorId != 0) {
			List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
			List<ProfessionalBackgroundDTO> descriptions = new ArrayList<ProfessionalBackgroundDTO>();
			List<Integer> profIds = new ArrayList<Integer>();
			// Getting Experience
			AdvisorRegistrationDAO exp = new AdvisorRegistrationDAO();
			experience = exp.GetExperience(advisorId);
			// Getting the company,designation,duration
			AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
			list = dao.GetProfessionalBackground(advisorId);
			if (list.size() > 0) {
				for (ProfessionalBackgroundDTO professionalBackgroundDTO : list) {
					profIds.add(professionalBackgroundDTO.getProfId());
				}
				// Getting Professional Background description
				AdvisorRegistrationDAO desc = new AdvisorRegistrationDAO();
				descriptions = desc
						.GetProfessionalBackgroundDescription(profIds);
				// Creating an array string

				for (ProfessionalBackgroundDTO professional : list) {
					int i = 0;
					String[] description = new String[10];
					for (ProfessionalBackgroundDTO profession : descriptions) {
						if (professional.getProfId() == profession.getProfId()) {
							description[i] = profession.getDescription();
							i++;
						}
					}
					professional.setDescriptionArray(description);
				}
				request.setAttribute("experience", experience);
				request.setAttribute("list", list);
				request.setAttribute("edit", true);
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/ProfessionalBackground.jsp");
				rd.forward(request, response);
			}
		}
		logger.info("Exit doGet method of AdvisorEditProfessionalBackgroundController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorEditProfessionalBackgroundController");
		int aId = 0;
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		try {
			aId = (int) request.getSession().getAttribute("advisorId");
		} catch (Exception e) {
			response.sendRedirect("Error");
		}
		// Retrieving Advisor Professional Background
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
		// This is for setting the professional background of the advisor
		if (aId != 0) {
			if (company.length > 0 && designation.length > 0
					&& duration.length > 0) {
				// Deleting the details from advisorprofessionalbackground table
				AdvisorRegistrationDAO profs = new AdvisorRegistrationDAO();
				profs.DeleteProfCompany(aId);
				AdvisorRegistrationDAO advisor = new AdvisorRegistrationDAO();
				advisor.DeleteProfDescription(aId);
				Boolean isDescriptionCommit = false;
				// Updating the professional backgroung intro
				AdvisorRegistrationDAO exp = new AdvisorRegistrationDAO();
				Boolean isExperience = exp.SetExperience(aId, experience);
				ProfessionalBackgroundObject professionalBackground = new ProfessionalBackgroundObject();
				list = professionalBackground.getProfessionalBackgroundObject(
						company, designation, duration);
				// Setting the company, designation and duration
				AdvisorRegistrationDAO prof = new AdvisorRegistrationDAO();
				List<Integer> profId = prof
						.addProfessionalBackground(list, aId);
				// Getting description object
				if (profId != null && profId.size() > 0) {
					// Getting the description object
					ProfessionalBackgroundObject desc = new ProfessionalBackgroundObject();
					List<ProfessionalBackgroundDTO> descriptions = desc
							.getProfDescription(profId, description0,
									description1, description2, description3,
									description4, description5, description6,
									description7, description8, description9,
									description10);
					// Setting the description of the professional background
					if (descriptions.size() > 0) {
						AdvisorRegistrationDAO professions = new AdvisorRegistrationDAO();
						isDescriptionCommit = professions.setDescription(aId,
								descriptions);
					}
				}
				if (isDescriptionCommit) {
					Properties prop = new Properties();
					InputStream resourceAsStream = Thread.currentThread()
							.getContextClassLoader()
							.getResourceAsStream("Resources/mail.properties");
					try {
						prop.load(resourceAsStream);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//Getting advisor name
					AdminNotificationDAO aName = new AdminNotificationDAO();
					String advisorName = aName.GetAdvisorName(String.valueOf(aId));
					
					String comment = advisorName + "with Id = "+aId+" edited his professional info ";
					String href = "AdvisorProfile?aId="+aId+"&admin=true";
					AdminNotificationDAO admin = new AdminNotificationDAO();
					admin.InsertNotification(comment, href);
					
					String subject = "";
					String content = "";
					subject = "An Advisor just edited his profile";
					content = "Hi, <br><br>An Advisor just edited his profile. Following are the details: <br> Advisor Id : "
							+ aId
							+ "<br>Section : Professional Background<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content,
							prop.getProperty("MAIL_ADMIN"),
							prop.getProperty("MAIL_ADMIN"));
					mail.start();
					response.sendRedirect("OtherInfoEdit");
				}
			}
		}
		logger.info("Exit doPost method of AdvisorEditProfessionalBackgroundController");

	}

}
