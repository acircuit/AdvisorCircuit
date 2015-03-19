 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 15/12/2014
 * ************************************************************************************************/
package org.AC.controller;
/* *******************************CLASS SUMMARY****************************************************
* 
* This class will increment the advisor rating.
* 
*
***************************************************************************************************/

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
import org.AC.Util.SendMail;
import org.AC.dto.RecommendationDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class Recommendation
 */
@WebServlet("/Recommendation")
public class Recommendation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Recommendation.class);

 	/**************************************COMMENTS***************************************************
	 * This class will increment the advisor rating.
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
		logger.info("Entered doPost method of Recommendation");
		String review = request.getParameter("review");
		String sId = request.getParameter("sId");
		if(review == null){
			RecommendationDTO recommendation = new RecommendationDTO();
			ReviewAndRecommendationDAO rating = new ReviewAndRecommendationDAO();
			recommendation = rating.CheckIsRecommended(sId);		
				if(recommendation.getRatings() != null ){
					Boolean ratings= recommendation.getRatings();
					String reviewMessage = recommendation.getReviewMessage();
					if(ratings && reviewMessage != null){
						response.getWriter().write("YOU HAVE ALREADY RECOMMENDED THIS ADVISOR.");
					}else if (ratings && reviewMessage == null) {
						response.getWriter().write("YOU HAVE ALREADY RECOMMENDED THIS ADVISOR.PLEASE REVIEW THE ADVISOR");
					}else{
						Boolean isRatingsCommit = false;
						ReviewAndRecommendationDAO updateRating = new ReviewAndRecommendationDAO();
						isRatingsCommit = updateRating.UpdateRecommendationForSession(sId);
						if(isRatingsCommit){
							//Getting the advisor Id from the session Id.
							ReviewAndRecommendationDAO advisor = new ReviewAndRecommendationDAO();
							int advisorId = advisor.GetAdvisorId(sId);
							ReviewAndRecommendationDAO updateRatingForAdvsisor = new ReviewAndRecommendationDAO();
							Boolean isCommit = updateRatingForAdvsisor.UpdateRecommendationForAdvisor(advisorId);
							if(isCommit){
								if(reviewMessage == null){
								response.getWriter().write("THANK YOU FOR RECOMMENDING.PLEASE REVIEW THE ADVISOR");
								}else{
									response.getWriter().write("THANK YOU FOR RECOMMENDING.");
								}
							}
						}
					}
				}
		}else{
			String reviewmessage = request.getParameter("reviewmessage");
			if(reviewmessage != null){
				RecommendationDTO reviewMessages = new RecommendationDTO();
				ReviewAndRecommendationDAO message = new ReviewAndRecommendationDAO();
				reviewMessages = message.CheckIsRecommended(sId);
				String reviews = "";
					Boolean ratings= reviewMessages.getRatings();
					String reviewMessage = reviewMessages.getReviewMessage();	
						if(ratings && reviewMessage != null){
							reviews = "YOU HAVE ALREADY REVIEWED THE ADVISOR.";
						}else if (ratings && reviewMessage == null) {
							Boolean isReviewCommit = false;
							ReviewAndRecommendationDAO messages = new ReviewAndRecommendationDAO();
							isReviewCommit = message.UpdateReviewMessage(sId, reviewmessage);
							if(isReviewCommit){
								String subject ="";
								String content ="";
								subject = "An Advisor got reviewed";
								content = "Hi,<br>An advisor just got reviewed.Following are the details : <br> For SessionId : " +sId+ "<br>Review Message : " +reviewmessage+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
								SendMail mail = new SendMail(subject, content, "admin@advisorcircuit.com","admin@advisorcircuit.com");
								mail.start();
								reviews = "THANK YOU FOR YOUR REVIEW";
							}
						}else{
							if(reviewMessage == null){
								Boolean isReviewCommit = false;
								ReviewAndRecommendationDAO messages = new ReviewAndRecommendationDAO();
								isReviewCommit = message.UpdateReviewMessage(sId, reviewmessage);
								if(isReviewCommit){
									String subject ="";
									String content ="";
									subject = "An Advisor got reviewed";
									content = "Hi,<br>An advisor just got reviewed.Following are the details : <br> For SessionId : " +sId+ "<br>Review Message : " +reviewmessage+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
									SendMail mail = new SendMail(subject, content, "admin@advisorcircuit.com","admin@advisorcircuit.com");
									mail.start();
									reviews = "THANK YOU FOR YOUR REVIEW.PLEASE RECOMMEND THE ADVISOR";
								}
							}else{
								reviews = "YOU HAVE ALREADY REVIEWED THE ADVISOR.PLEASE RECOMMEND THE ADVISOR";
							}
						}
						if(!("").equals(reviews)){
							response.getWriter().write(reviews);
						}			
			
		}
		logger.info("Exit doPost method of Recommendation");
	}
}
}
	