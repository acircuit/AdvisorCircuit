 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 11/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ChangeRequestStatusDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch the details from the view details form and change the status 
* of the the request depending upon the admin acceptance or cancellation of the request
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMyAccountRequestViewDetailsFormController
 */
@WebServlet("/AdminMyAccountRequestViewDetailsFormController")
public class AdminMyAccountRequestViewDetailsFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountRequestViewDetailsFormController.class);     
       
	/**************************************COMMENTS***************************************************
	 * This function will first check whether the admin has cancelled  or accepted the request.
	 *   If the admin has accepted the request then it will change the request status to "PENDING FOR ADVISOR APPROVAL"
	 *   If the admin has cancelled the request then it will change the request status to "REQUEST REJECTED BY ADMIN"
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
		
		logger.info("Entered doGet method of AdminMyAccountRequestViewDetailsFormController");
		String rId = "";
		String cancel = "";
		rId = (String) request.getParameter("rId");
		cancel = (String) request.getParameter("cancel");
		//Check if the admin has accepted the request
		if( cancel == null ){
			String status = "PENDING FOR ADVISOR APPROVAL";
			ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
			Boolean isStatusCommit = requestStatus.setStatus(status, Integer.parseInt(rId));
			if(isStatusCommit){
				response.sendRedirect("AdminRequests?approved=true");
			}
		}else{
			//If admin has cancelled the session
			String status = "REQUEST REJECTED BY ADMIN";
			ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
			Boolean isStatusCommit = requestStatus.setStatus(status, Integer.parseInt(rId));
			if(isStatusCommit){
				response.sendRedirect("AdminCancelledSessions");
			}
			
		}
	}
}
