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
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationEmailController
 */
@WebServlet("/AdvisorRegistrationEmailController")
public class AdvisorRegistrationEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationEmailController.class);

       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This servlet will retrieve the email Id check for the existence of the advisor in the table and depending upon the status 
	 * redirect the advisor to respective page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BasicConfigurator.configure();
		logger.info("Entered doPost method of ForgotPasswordController");
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
			if(("").equals(status)){
				//If the advisor has not started the registration process then set the email address and change the status
				AdvisorRegistrationDAO dao1 = new AdvisorRegistrationDAO();
				advisorId = dao1.setEmail(email);
				request.getSession().setAttribute("aId", advisorId);
				request.getSession().setAttribute("email", email);
				response.sendRedirect("GeneralInfo.jsp");
			}else {
				request.getSession().setAttribute("aId", advisorId);
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("email", email);
				response.sendRedirect(status);
			}
		}
		
		
		
		
		logger.info("Exit doPost method of ForgotPasswordController");
	}

}
