 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 30/11/2014
 * ************************************************************************************************/

package org.AC.controller;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch the details from the view details form and change the status 
* of the the request depending upon the advisor acceptance or cancellation of the request
* 
* 
*
***************************************************************************************************/

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorMyAccountRequestViewDetailsDAO;
import org.AC.DAO.ChangeRequestStatusDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
	
	/**************************************COMMENTS***************************************************
	 * This function will first check whether the advisor has cancelled the session or accepted the request.
	 *   If the advisor has accepted the request then it will put the details in session tab and change the status accordingly.
	 *   If the advisor has cancelled the session then it will change the request status to "REQUEST REJECTED BY ADVISOR"
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
/**
 * Servlet implementation class AdvisorMyAccountRequestViewDetailsFormController
 */
@WebServlet("/AdvisorMyAccountRequestViewDetailsFormController")
public class AdvisorMyAccountRequestViewDetailsFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountRequestViewDetailsFormController.class);     
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This servlet will retrieve the fields from the view details form and then put them into the session table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorMyAccountRequestViewDetailsFormController");
		
		String uId = "";
		String rId = "";
		String cancel = "";
		Boolean isStatusCommit = false;
		int aId = 0;
		 uId = (String) request.getParameter("uId");
		 rId = (String) request.getParameter("rId");
		 cancel = (String) request.getParameter("cancel");
		try{
			aId = (int) request.getSession().getAttribute("advisorId");
		}catch(Exception e){
			e.printStackTrace();
		}
		//Check if the advisor has cancelled the session
		if( cancel == null ){
			//Advisor has aceepted the request
			String acceptedTime = request.getParameter("optionsRadios");
			String sessionPlan = request.getParameter("sessionplan");
			String date1 = request.getParameter("datetimepicker1");
			String date2 = request.getParameter("datetimepicker2");
			String date3 = request.getParameter("datetimepicker3");
			if(uId != null && !("").equals(uId) && rId != null && !("").equals(rId) && aId != 0
					&&  sessionPlan != null && !("").equals(sessionPlan)){
				
				
				if (date1 != null && !("").equals(date1) && date2 != null && !("").equals(date2) && date3 != null && !("").equals(date3)) {
						
						String status = "PENDING FOR USER PAYMENT WITH NEW DATES";
						AdvisorMyAccountRequestViewDetailsDAO session = new AdvisorMyAccountRequestViewDetailsDAO();
						int sessionId = session.setSessionDetails(aId, rId, uId, acceptedTime, sessionPlan,status);			
						if(sessionId != 0){
							AdvisorMyAccountRequestViewDetailsDAO newDates = new AdvisorMyAccountRequestViewDetailsDAO();
							Boolean isNewDatesCommit = newDates.setNewDates(sessionId, date1, date2, date3);
							if(isNewDatesCommit){
								String status1 = "REQUEST ACCEPTED BY ADVISOR WITH NEW DATES";
								int rId1 = Integer.parseInt(rId);
								ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
								isStatusCommit = requestStatus.setStatus( status1,rId1);
							}
						}
						
				}else{
					
						String status = "PENDING FOR USER PAYMENT";
						AdvisorMyAccountRequestViewDetailsDAO session = new AdvisorMyAccountRequestViewDetailsDAO();
						int sessionId = session.setSessionDetails(aId, rId, uId, acceptedTime, sessionPlan,status);
						if(sessionId != 0){
						String status1 = "REQUEST ACCEPTED BY ADVISOR";
						int rId1 = Integer.parseInt(rId);
						ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
						isStatusCommit = requestStatus.setStatus( status1,rId1);
						}
				}
				//RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdvisorMyAccountRequestController");
		        //rd.forward(request, response);
				if(isStatusCommit){
					response.sendRedirect("/Tomact_7/AdvisorMyAccountRequestController");
				}
						
			}	
		}else{
			//The advisor has cancelled the session
			//Change the status of the request to "REQUEST REJECTED BY ADVISOR"
			String status1 = "REQUEST REJECTED BY ADVISOR";
			int rId1 = Integer.parseInt(rId);
			ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
			isStatusCommit = requestStatus.setStatus( status1,rId1);
			if(isStatusCommit){
				response.sendRedirect("/Tomact_7/AdvisorMyAccountCancelledSessionController");
			}
		}
		logger.info("Exit doPost method of AdvisorMyAccountRequestViewDetailsFormController");
	}

}
