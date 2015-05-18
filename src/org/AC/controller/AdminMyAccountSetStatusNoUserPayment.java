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

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdvisorMyAccountRequestViewDetailsDAO;
import org.AC.DAO.AdvisorNotificationDAO;
import org.AC.DAO.ChangeRequestStatusDAO;
import org.AC.DAO.ChangeSessionStatusDAO;
import org.AC.DAO.UserNotificationDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY****************************************************
* 
* This class will change the request and session status
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMyAccountSetStatus
 */
@WebServlet("/AdminMyAccountSetStatusNoUserPayment")
public class AdminMyAccountSetStatusNoUserPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountSetStatusNoUserPayment.class);     
	
 	/**************************************COMMENTS***************************************************
	 * This method will change the request and session status 
	 *   
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
		logger.info("Entered doGet method of AdminMyAccountSetStatusNoUserPayment");
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
			String userIsFree = request.getParameter("userIsFree");
			String uId = request.getParameter("uId");

			if(rId != null && sId != null){
				Boolean isRequestStatusCommit = false;
				Boolean isSessionStatusCommit = false;
				String status = "SESSION CANCELLED DUE TO NO USER PAYMENT";
				//Change the status of the request and session
				ChangeRequestStatusDAO requestStatus =  new ChangeRequestStatusDAO();
				isRequestStatusCommit = requestStatus.setStatus(status, Integer.parseInt(rId));
				if(isRequestStatusCommit){
					//Set Session Status
					ChangeSessionStatusDAO sessionStatus = new ChangeSessionStatusDAO();
					isSessionStatusCommit = sessionStatus.setStatus(status, sId);
				}
				if(isSessionStatusCommit){
					if(userIsFree.equals("true")){
						//Toggle the free session column in the userdetails table
						AdvisorMyAccountRequestViewDetailsDAO toggle = new AdvisorMyAccountRequestViewDetailsDAO();
						toggle.ToggleUserFreeSession(Integer.parseInt(uId));
					}
					String userComment = "Your session was cancelled due to no payment";
					String userHref = "UserCancelledSessions";
					UserNotificationDAO user = new UserNotificationDAO();
					user.InsertNotification(userComment,userHref , uId);
					
					//Getting the username
					AdminNotificationDAO name = new AdminNotificationDAO();
					String userName = name.GetUserName(uId);
					
					int[] ids = new int[2];
					//Getting AdvisorId from requestId
					UserNotificationDAO id = new UserNotificationDAO();
					ids = id.GetId(rId);
					//Notify Advisor
					String advisorComment = "The session with "+userName+" has been cancelled due to no payment";
					String advisorHref = "AdvisorCancelledSessions";
					AdvisorNotificationDAO advisor = new AdvisorNotificationDAO();
					advisor.InsertRequestNotification(advisorComment, String.valueOf(ids[1]), advisorHref);
					
					response.sendRedirect("AdminCancelledSessions");
				}
			}
		}
		logger.info("Exit doGet method of AdminMyAccountSetStatusNoUserPayment");
	}
}
