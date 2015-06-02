 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 16/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdvisorNotificationDAO;
import org.AC.DAO.ReviewAndRecommendationDAO;
import org.AC.DAO.UserNotificationDAO;
import org.AC.dto.RecommendationDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/*********************************CLASS SUMMARY****************************************************
* 
* This class will fetch the review and recommendation for the particular session.
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMyAccountReviewController
 */
@WebServlet("/AdminMyAccountReviewController")
public class AdminMyAccountReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountReviewController.class); 

	/**************************************COMMENTS***************************************************
	 * This class will fetch the review and recommendation for the particular session
	 *   
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdminMyAccountReviewController");
		String approveorreject = "";
		approveorreject  = request.getParameter("approveorreject");
			String sId = request.getParameter("sId");
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
				if(approveorreject != null && ("APPROVE").equals(approveorreject)){
					ReviewAndRecommendationDAO reviewApprove = new ReviewAndRecommendationDAO();
					Boolean isApprovalCommit = reviewApprove.ApproveReview(sId,"APPROVED");
					if(isApprovalCommit){
						int[] ids = new int[3];
						//GETTING THE USERID, ADVISORID, AND REQUEST ID
						UserNotificationDAO id = new UserNotificationDAO();
						ids = id.GetAdvisorId(sId);
						
						//Getting username
						AdminNotificationDAO name = new AdminNotificationDAO();
						String uName = name.GetUserName(String.valueOf(ids[0]));
						
						//Notify advisor
						String comment = uName + " has written a review for you";
						String href = "AdvisorProfile?aId="+ids[1];
						AdvisorNotificationDAO notify = new AdvisorNotificationDAO();
						notify.InsertRequestNotification(comment, String.valueOf(ids[1]) , href);
						response.getWriter().write("<p><b>The Review has been approved</b></p>");
					}
				}else if (approveorreject != null && ("REJECT").equals(approveorreject)) {
					ReviewAndRecommendationDAO reviewApprove = new ReviewAndRecommendationDAO();
					Boolean isRejectionCommit = reviewApprove.ApproveReview(sId,"REJECTED");
					if(isRejectionCommit){
						response.getWriter().write("<p><b>The Review has been rejected</b></p>");
					}
				}
			}
		logger.info("Exit doPost method of AdminMyAccountReviewController");
	}

}
