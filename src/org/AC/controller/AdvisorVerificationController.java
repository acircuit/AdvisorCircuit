package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.dto.AdvisorRegistrationCheckEmailDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorVerificationController
 */
@WebServlet("/AdvisorVerificationController")
public class AdvisorVerificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(AdvisorVerificationController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorVerificationController");
		String aId = request.getParameter("verify");
		AdvisorRegistrationCheckEmailDTO dto = new AdvisorRegistrationCheckEmailDTO();
		if (aId != null && !aId.isEmpty() && !aId.equals("")) {
			AdvisorRegistrationDAO advisor = new AdvisorRegistrationDAO();
			dto = advisor.Verify(aId);
		}
		if (dto.getAdvisorId() != 0) {
			// update ISVERIFIED attribute of advisordetails
			AdvisorRegistrationDAO advisor = new AdvisorRegistrationDAO();
			Boolean isVerfied = advisor.UpdateVerification(dto.getAdvisorId());
			if (isVerfied) {
				request.getSession().setAttribute("aId", dto.getAdvisorId());
				request.getSession().setAttribute("email", dto.getEmail());
				response.sendRedirect("GeneralInfo");
			} else {
				response.sendRedirect("verificationfailed");
			}
		} else {
			response.sendRedirect("verificationfailed");
		}

		logger.info("Exit doGet method of AdvisorVerificationController");
	}

}
