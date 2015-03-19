 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 12/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ChangeRequestStatusDAO;
import org.AC.DAO.ChangeSessionStatusDAO;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch the details from the view details form and change the status 
* of the the request and session .
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMyAccountUpcomingSessionViewDetailsFormController
 */
@WebServlet("/AdminMyAccountUpcomingSessionViewDetailsFormController")
public class AdminMyAccountUpcomingSessionViewDetailsFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountUpcomingSessionViewDetailsFormController.class);     

       
	/**************************************COMMENTS***************************************************
	 * * This class will fetch the details from the view details form and change the status 
	 * of the the request and session .
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdminMyAccountUpcomingSessionViewDetailsFormController");
		Boolean isAdmin = false;
		Boolean isError = false;
		try{
			isAdmin = (Boolean) request.getSession().getAttribute("admin"); 
			}catch(Exception e){
				response.sendRedirect("Error");
				isError = true;
			}
		if(isAdmin == null){
			isError = true;
			response.sendRedirect("Error");
		}
		if(isError!= null &&  !isError){
			String rId = request.getParameter("rId");
			String sId = request.getParameter("sId");
			String advisorNoShow = request.getParameter("advisornoshow");
			String userNoShow = request.getParameter("usernoshow");
			String advisorUnavailable = request.getParameter("advisorunavailabale");
			String userUnavailable = request.getParameter("userunavailabale");
			String complete = request.getParameter("complete");
			String status = "";
			String redirect = "";
			Boolean isRequestStatusCommit =false;
			Boolean isSessionStatusCommit = false;
			if(rId != null  && sId != null){
				if(advisorNoShow != null && ("true").equals(advisorNoShow)){
					 status = "SESSION CANCELLED DUE TO ADVISOR NO SHOW";
					 redirect = "AdminCancelledSessions";
				}else if (userNoShow != null && ("true").equals(userNoShow)) {
					 status = "SESSION CANCELLED DUE TO USER NO SHOW";
					 redirect = "AdminCancelledSessions";
				}else if (advisorUnavailable != null && ("true").equals(advisorUnavailable)) {
					 status = "SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY";
					 redirect = "AdminCancelledSessions";
				}else if (userUnavailable != null && ("true").equals(userUnavailable)){
					status = "SESSION CANCELLED DUE TO USER UNAVAILABILITY";
					 redirect = "AdminCancelledSessions";
				}else{
					status = "SESSION COMPLETE";
					 redirect = "AdminPreviousSessions";
				}
				ChangeRequestStatusDAO requestStatus =  new ChangeRequestStatusDAO();
				isRequestStatusCommit = requestStatus.setStatus(status, Integer.parseInt(rId));
				if(isRequestStatusCommit){
					//Set Session Status
					ChangeSessionStatusDAO sessionStatus = new ChangeSessionStatusDAO();
					isSessionStatusCommit = sessionStatus.setStatus(status, sId);
				}
				if(isSessionStatusCommit){
					response.sendRedirect(redirect);
				}
				
			}
		}
		logger.info("Entered doGet method of AdminMyAccountUpcomingSessionViewDetailsFormController");
	}
}
