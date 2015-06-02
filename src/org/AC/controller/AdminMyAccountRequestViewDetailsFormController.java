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

import org.AC.DAO.AdvisorMyAccountRequestViewDetailsDAO;
import org.AC.DAO.AdvisorNotificationDAO;
import org.AC.DAO.ChangeRequestStatusDAO;
import org.AC.DAO.UserNotificationDAO;
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

	/**************************************
	 * COMMENTS*************************************************** This function
	 * will first check whether the admin has cancelled or accepted the request.
	 * If the admin has accepted the request then it will change the request
	 * status to "PENDING FOR ADVISOR APPROVAL" If the admin has cancelled the
	 * request then it will change the request status to
	 * "REQUEST REJECTED BY ADMIN"
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

		logger.info("Entered doGet method of AdminMyAccountRequestViewDetailsFormController");
		Boolean isAdmin = false;
		Boolean isError = false;
		try {
			isAdmin = (Boolean) request.getSession().getAttribute("admin");
		} catch (Exception e) {
			response.sendRedirect("Error");
			isError = true;
		}
			if(isAdmin == null){
				isError = true;
				response.sendRedirect("Error");
			}
			
			if(isError!= null &&  !isError){
				String rId = "";
				String cancel = "";
				rId = (String) request.getParameter("rId");
				cancel = (String) request.getParameter("cancel");
				 String isFree = request.getParameter("isFree");
				 String aId = request.getParameter("aId");
				 String service = request.getParameter("service");
				 String userIsFree = request.getParameter("userIsFree");
				 String uId = request.getParameter("uId");
				//Check if the admin has accepted the request
				if( cancel == null ){
					String status = "PENDING FOR ADVISOR APPROVAL";
					ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
					Boolean isStatusCommit = requestStatus.setStatus(status, Integer.parseInt(rId));
					if(isStatusCommit){
						String comment = "Your request has been succesfully placed. Keep checking your account for updates.";
						String href = "UserRequestViewDetails?rId="+rId;
						//Notify User 
						UserNotificationDAO notify = new UserNotificationDAO();
						notify.InsertNotification(comment, href,uId);
						
						//Notify Advisor
						String advisorComment = "You've got a new session request !";
						String advisorHref =  "AdvisorRequestViewDetail?rId=" +rId;
						AdvisorNotificationDAO notifyAdvisor = new AdvisorNotificationDAO();
						notifyAdvisor.InsertRequestNotification(advisorComment, aId, advisorHref);
						
						response.sendRedirect("AdminRequests?approved=true");
					}
					if (userIsFree != null && userIsFree.equals("true")) {
						// Toggle the free session column in the userdetails
						// table
						AdvisorMyAccountRequestViewDetailsDAO toggle = new AdvisorMyAccountRequestViewDetailsDAO();
						toggle.ToggleUserFreeSession(Integer.parseInt(uId));
					}
				}else{
					//If admin has cancelled the session
					String status = "REQUEST REJECTED BY ADMIN";
					ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
					Boolean isStatusCommit = requestStatus.setStatus(status, Integer.parseInt(rId));
					if(isStatusCommit){
						if(isFree.equals("true")){
							//Decrement the free session count from the advisor services table
							AdvisorMyAccountRequestViewDetailsDAO decrem = new AdvisorMyAccountRequestViewDetailsDAO();
							decrem.DecrementFreeSession(Integer.parseInt(aId),service);
						}
						if(userIsFree.equals("true")){
							//Toggle the free session column in the userdetails table
							AdvisorMyAccountRequestViewDetailsDAO toggle = new AdvisorMyAccountRequestViewDetailsDAO();
							toggle.ToggleUserFreeSession(Integer.parseInt(uId));
						}
						String comment = "We're sorry but your request could not be placed. You will get a mail regarding this soon.";
						String href = "UserCancelledSessionViewDetails?rId="+rId;
						//Notify User 
						UserNotificationDAO notify = new UserNotificationDAO();
						notify.InsertNotification(comment, href,uId);
						
						response.sendRedirect("AdminCancelledSessions");
					}
				}

			}
		}
}
