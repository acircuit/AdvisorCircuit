/*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 30/12/2014
 * ************************************************************************************************/
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

import org.AC.DAO.AdminAdvisorDAO;
import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdminUserDAO;
import org.AC.DAO.UserDetailsDAO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserReferralDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
 * 
 * This class will get the details of all the users.
 * 
 *
 ***************************************************************************************************/

/**
 * Servlet implementation class AdminUsersController
 */
@WebServlet("/AdminUsersController")
public class AdminUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(AdminUsersController.class);

	/**************************************
	 * COMMENTS*************************************************** This class
	 * will get the details of all the advisors.
	 * 
	 * @return :None
	 * @param : HttpServletRequest request HttpServletResponse response
	 * 
	 *
	 ***************************************************************************************************/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdminUsersController");
		Boolean isAdmin = false;
		Boolean isError = false;
		try {
			isAdmin = (Boolean) request.getSession().getAttribute("admin");
		} catch (Exception e) {
			response.sendRedirect("Error");
			isError = true;
		}
		if (isAdmin == null) {
			isError = true;
			response.sendRedirect("Error");
		}
		if (isError != null && !isError) {
			List<UserDetailsDTO> users = new ArrayList<UserDetailsDTO>();
			// Get all the details of the advisor
			AdminUserDAO user = new AdminUserDAO();
			users = user.GetUsers();
			int id =1;
			UserDetailsDAO promotion = new UserDetailsDAO();
			Boolean isActive = promotion.IsPromotionActive(id);
			List<UserReferralDTO> referral = new ArrayList<UserReferralDTO>();
			AdminUserDAO ref = new AdminUserDAO();
			referral = ref.GetReferral();
			
			//Update Admin's Notification
    		String url =  request.getRequestURI();
			url = url.substring(url.lastIndexOf('/')+1);
			AdminNotificationDAO admin = new AdminNotificationDAO();
			admin.SetNotificationRead(url);
			request.setAttribute("users", users);
			request.setAttribute("isActive", isActive);
			request.setAttribute("referral", referral);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/AdminUser.jsp");
			rd.forward(request, response);
		}
		logger.info("Exit doGet method of AdminUsersController");
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdminUsersController");
			String uId = request.getParameter("uId");
			String count = request.getParameter("count");
			String val = request.getParameter("val");
			if(uId != null && count != null){
				AdminUserDAO update = new AdminUserDAO();
				update.UpdateReferralCount(uId,count);
			}else if (uId != null && val != null) {
				if(val.equals("true")){
					AdminUserDAO update = new AdminUserDAO();
					update.ToggleRefMessage(uId,false);
				}else{
					AdminUserDAO update = new AdminUserDAO();
					update.ToggleRefMessage(uId,true);
				}

			}
		
		logger.info("Exit doPost method of AdminUsersController");
	}
}
