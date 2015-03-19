package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ChangeSessionStatusDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorMyAccountSessionViewDetailsFormController
 */
@WebServlet("/AdvisorMyAccountSessionViewDetailsFormController")
public class AdvisorMyAccountSessionViewDetailsFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountSessionViewDetailsFormController.class);     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorMyAccountSessionViewDetailsFormController");	
		
		String sessionId = request.getParameter("sId");
		System.out.println(sessionId);
		Boolean isStatusCommit = false;
		if(sessionId != null && !("").equals(sessionId)){
			
			//Set the status of the session as cancelled by advisor
			String status ="CANCELLED BY ADVISOR";	
			ChangeSessionStatusDAO dao = new ChangeSessionStatusDAO();
			isStatusCommit =  dao.setStatus(status, sessionId);
			if(isStatusCommit){
				response.sendRedirect("AdvisorCancelledSessions");
			}
		}
		logger.info("Exit doGet method of AdvisorMyAccountSessionViewDetailsFormController");
	}
}
