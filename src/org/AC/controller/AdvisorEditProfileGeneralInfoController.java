package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorEditProfileDAO;
import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.SendMail;
import org.AC.dto.AdvisorEducationDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorEditProfileGeneralInfoController
 */
@WebServlet("/AdvisorEditProfileGeneralInfoController")
public class AdvisorEditProfileGeneralInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(AdvisorEditProfileGeneralInfoController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorEditProfileGeneralInfoController");
		int advisorId = 0;
		try {
			advisorId = (int) request.getSession().getAttribute("advisorId");
		} catch (Exception e) {
			response.sendRedirect("Error");
		}
		if (advisorId != 0) {
			AdvisorProfileDTO profile = new AdvisorProfileDTO();
			// Getting General Information of the advisor
			AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
			profile = dao.GetGeneralInfo(advisorId);
			if (profile.getName() != null) {
				request.setAttribute("profile", profile);
				request.setAttribute("edit", true);
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/GeneralInfo.jsp");
				rd.forward(request, response);
			}
		}
		logger.info("Exit doGet method of AdvisorEditProfileGeneralInfoController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorEditProfileGeneralInfoController");
		// String saveDirectory = "E:\\AC\\WebContent\\assets\\UserImages\\";
		// retrieving the profile details and putting them into
		// advisorregistrationprofiledetails table
		// MultipartRequest request1 = new MultipartRequest((HttpServletRequest)
		// request, saveDirectory);
		int advisorId = 0;
		try {
			advisorId = (int) request.getSession().getAttribute("advisorId");
		} catch (Exception e) {
			response.sendRedirect("Error");
		}
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String phone = request.getParameter("phone");
		String industry = request.getParameter("industry");
		String intro = request.getParameter("intro");
		String edit = request.getParameter("edit");
		if (edit == null) {
			edit = "false";
		}
		if (intro != null) {
			intro = intro.replaceAll("\r\n", "");
			intro = intro.replaceAll("\r", "");
			intro = intro.replaceAll("\n", "");
		}
		try {
			if (name != null && gender != null && age != null && city != null
					&& state != null && phone != null && industry != null
					&& intro != null && !name.isEmpty() && !gender.isEmpty()
					&& !age.isEmpty() && !city.isEmpty() && !phone.isEmpty()
					&& !state.isEmpty() && !industry.isEmpty()
					&& !intro.isEmpty()) {

				if (advisorId != 0) {
					// Calling DAO to put the values into table
					AdvisorEditProfileDAO dao = new AdvisorEditProfileDAO();
					Boolean isGeneralInfoCommit = dao.setGeneralInfo(name,
							gender, age, city, phone, industry, intro,
							advisorId, state);
					if (isGeneralInfoCommit) {
						Properties prop = new Properties();
						InputStream resourceAsStream = Thread
								.currentThread()
								.getContextClassLoader()
								.getResourceAsStream(
										"Resources/mail.properties");
						try {
							prop.load(resourceAsStream);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String subject = "";
						String content = "";
						subject = "An Advisor just edited his profile";
						content = "Hi, <br><br>An Advisor just edited his profile. Following are the details: <br> Advisor Id : "
								+ advisorId
								+ "<br>Section : General Info <br><img src=http://www.advisorcircuit.com/assets/img/logo_black.png\" style='float:right' width='25%'>";
						SendMail mail = new SendMail(subject, content,
								prop.getProperty("MAIL_ADMIN"),
								prop.getProperty("MAIL_ADMIN"));
						mail.start();
						response.sendRedirect("EducationInfoEdit");
					}
				}
			}
			logger.info("Exit doPost method of AdvisorEditProfileGeneralInfoController");
		} catch (Exception e) {
			logger.error("doPost method of AdvisorEditProfileGeneralInfoController threw error:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

}
