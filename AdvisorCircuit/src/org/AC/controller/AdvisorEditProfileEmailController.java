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
import org.AC.dto.AdvisorRegistrationCheckEmailDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorEditProfileEmailController
 */
@WebServlet("/AdvisorEditProfileEmailController")
public class AdvisorEditProfileEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorEditProfileEmailController.class);
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorEditProfileEmailController");
		String email = request.getParameter("email");
		String status ="";
		int advisorId = 0;
		String name = "";
		List<AdvisorRegistrationCheckEmailDTO> list = new ArrayList<AdvisorRegistrationCheckEmailDTO>();

		//Once we have retrieved the email we have to check whether the advisor have already started registration process.
		//And if yes, then at what point did he stop the registration.
		if(!email.isEmpty()){
			AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
			list = dao.checkEmail(email);
			for (AdvisorRegistrationCheckEmailDTO advisorRegistrationCheckEmailDTO : list) {
				advisorId = advisorRegistrationCheckEmailDTO.getAdvisorId();
				status = advisorRegistrationCheckEmailDTO.getRegistrationStatus();
				name = advisorRegistrationCheckEmailDTO.getName();
			}
			if(advisorId == 0){
				request.setAttribute("invalidEmail", true);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/EditProfile.jsp");
		        rd.forward(request, response);
				
			}else {
				request.getSession().setAttribute("advisorId", advisorId);
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("email", email);
				response.sendRedirect("GeneralInfoEdit");
			}
		}
		logger.info("Exit doPost method of AdvisorEditProfileEmailController");
	}

}
