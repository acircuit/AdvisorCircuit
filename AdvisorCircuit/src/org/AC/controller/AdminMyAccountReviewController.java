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

import org.AC.DAO.ReviewAndRecommendationDAO;
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
			if(approveorreject != null && ("APPROVE").equals(approveorreject)){
				ReviewAndRecommendationDAO reviewApprove = new ReviewAndRecommendationDAO();
				Boolean isApprovalCommit = reviewApprove.ApproveReview(sId,"APPROVED");
				if(isApprovalCommit){
					response.getWriter().write("<p><b>The Review has been approved</b></p>");
				}
			}else if (approveorreject != null && ("REJECT").equals(approveorreject)) {
				ReviewAndRecommendationDAO reviewApprove = new ReviewAndRecommendationDAO();
				Boolean isRejectionCommit = reviewApprove.ApproveReview(sId,"REJECTED");
				if(isRejectionCommit){
					response.getWriter().write("<p><b>The Review has been rejected</b></p>");
				}
			}
		logger.info("Exit doPost method of AdminMyAccountReviewController");
	}

}
