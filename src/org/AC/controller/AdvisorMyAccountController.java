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

import org.AC.DAO.AdvisorNotificationDAO;
import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.NotificationDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorMyAccountController
 */
@WebServlet("/AdvisorMyAccountController")
public class AdvisorMyAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountController.class);
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorMyAccountController");
		Boolean isError = false;
		int aId = 0;
		try{
			aId = (int) request.getSession().getAttribute("advisorId");
		}catch(Exception e){
			response.sendRedirect("Error");
			isError = true;
		}
		if(!isError){
			AdvisorProfileDTO advisors = new AdvisorProfileDTO();
			//Getting advisorDetails
			AdvisorRegistrationDAO advisor = new AdvisorRegistrationDAO();
			advisors = advisor.GetGeneralInfo(aId);
			GetRelativeImageURL img = new GetRelativeImageURL();
			advisors.setImage(img.getImageURL(advisors.getImage()));
			List<NotificationDTO> notify = new ArrayList<NotificationDTO>();
			AdvisorNotificationDAO advisorNotification = new AdvisorNotificationDAO();
			notify = advisorNotification.GetNotification(aId);
			request.setAttribute("advisor", advisors);
			request.setAttribute("notify", notify);
	        RequestDispatcher rd = getServletContext().getRequestDispatcher("/dashboard");
	        rd.forward(request, response);
		}
		logger.info("Entered doGet method of AdvisorMyAccountController");
	}

}
