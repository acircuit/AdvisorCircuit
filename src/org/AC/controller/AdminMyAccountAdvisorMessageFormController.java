package org.AC.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.DAO.MessageDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminMyAccountAdvisorMessageFormController
 */
@WebServlet("/AdminMyAccountAdvisorMessageFormController")
public class AdminMyAccountAdvisorMessageFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountAdvisorMessageFormController.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdminMyAccountAdvisorMessageFormController");
		String messageId = request.getParameter("mId");
		String adminMessage = request.getParameter("adminmessage");
		String email = request.getParameter("email");
		Boolean isMessageCommit = false;
		if(messageId != null && adminMessage != null){
			//Put the message in the advisormessageadmin table
			 MessageDAO dao = new MessageDAO();
			 isMessageCommit = dao.setAdminMessage(adminMessage, messageId);
			 if(isMessageCommit){
				 request.setAttribute("email", email);
				 RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminMyAccountAdvisorMessageController");
			     rd.forward(request, response);
			 }
		}
		logger.info("Exit doPost method of AdminMyAccountAdvisorMessageFormController");
	}

}
