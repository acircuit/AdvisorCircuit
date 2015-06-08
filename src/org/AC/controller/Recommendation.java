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
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

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
		String advisorname = request.getParameter("advisorname");		
		Properties prop = new Properties();
	    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop.load(resourceAsStream);
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
						response.getWriter().write("YOU HAVE ALREADY RECOMMENDED THIS ADVISOR.");
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
								int[] ids = new int[3];
								//GETTING THE USERID, ADVISORID, AND REQUEST ID
								UserNotificationDAO id = new UserNotificationDAO();
								ids = id.GetAdvisorId(sId);
								
								//Getting username
								AdminNotificationDAO name = new AdminNotificationDAO();
								String uName = name.GetUserName(String.valueOf(ids[0]));
								
								//Notify advisor
								String comment = "You've been recommended by "+uName;
								String href = "AdvisorProfile?aId="+ids[1];
								AdvisorNotificationDAO notify = new AdvisorNotificationDAO();
								notify.InsertRequestNotification(comment, String.valueOf(ids[1]) , href);
								
								//Getting advisor name
								AdminNotificationDAO aName = new AdminNotificationDAO();
								String advisorName = aName.GetAdvisorName(String.valueOf(ids[1]));
								
								//Notify Admin
								String adminComment = uName + " recommended "+advisorName;
								String adminHref = "AdminReview";
								AdminNotificationDAO admin = new AdminNotificationDAO();
								admin.InsertNotification(adminComment, adminHref);
								
								response.getWriter().write("THANK YOU FOR RECOMMENDING.");
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
					String reviewMessageStatus = reviewMessages.getReviewMessageStatus();
						if(ratings && (reviewMessage != null && !reviewMessageStatus.equals("REJECTED"))){
							reviews = "YOU HAVE ALREADY REVIEWED THE ADVISOR.";
						}else if (ratings && (reviewMessage == null || (reviewMessage != null && reviewMessageStatus.equals("REJECTED")))) {
							Boolean isReviewCommit = false;
							ReviewAndRecommendationDAO messages = new ReviewAndRecommendationDAO();
							isReviewCommit = message.UpdateReviewMessage(sId, reviewmessage);
							if(isReviewCommit){
								int[] ids = new int[3];
								//GETTING THE USERID, ADVISORID, AND REQUEST ID
								UserNotificationDAO id = new UserNotificationDAO();
								ids = id.GetAdvisorId(sId);
								
								//Getting username
								AdminNotificationDAO name = new AdminNotificationDAO();
								String uName = name.GetUserName(String.valueOf(ids[0]));
								
								//Getting advisor name
								AdminNotificationDAO aName = new AdminNotificationDAO();
								String advisorName = aName.GetAdvisorName(String.valueOf(ids[1]));
								
								//Notify Admin
								String adminComment = uName + " wrote a review for "+advisorName;
								String adminHref = "AdminReview";
								AdminNotificationDAO admin = new AdminNotificationDAO();
								admin.InsertNotification(adminComment, adminHref);
								String subject ="";
								String content ="";
								subject = "An Advisor got reviewed";
								content = "Hi, <br><br>An advisor just got reviewed. Following are the details : <br> For SessionId : " +sId+ "<br>Review Message : " +reviewmessage+"<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='15%'>";
								SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
								mail.start();
								reviews = "Thank you for the review. You can also recommend "+advisorname+" by clicking on the star above.";
							}
						}else{
							if(reviewMessage == null){
								Boolean isReviewCommit = false;
								ReviewAndRecommendationDAO messages = new ReviewAndRecommendationDAO();
								isReviewCommit = message.UpdateReviewMessage(sId, reviewmessage);
								if(isReviewCommit){
									
									int[] ids = new int[3];
									//GETTING THE USERID, ADVISORID, AND REQUEST ID
									UserNotificationDAO id = new UserNotificationDAO();
									ids = id.GetAdvisorId(sId);
									
									//Getting username
									AdminNotificationDAO name = new AdminNotificationDAO();
									String uName = name.GetUserName(String.valueOf(ids[0]));
									
									//Getting advisor name
									AdminNotificationDAO aName = new AdminNotificationDAO();
									String advisorName = aName.GetAdvisorName(String.valueOf(ids[1]));
									
									//Notify Admin
									String adminComment = uName + " wrote a review for "+advisorName;
									String adminHref = "AdminReview";
									AdminNotificationDAO admin = new AdminNotificationDAO();
									admin.InsertNotification(adminComment, adminHref);
									
									String subject ="";
									String content ="";
									subject = "An Advisor got reviewed";
									content = "Hi, <br><br>An advisor just got reviewed. Following are the details : <br> For SessionId : " +sId+ "<br>Review Message : " +reviewmessage+"<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='15%'>";
									SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
									mail.start();
									reviews = "Thank you for the review. You can also recommend "+advisorname+" by clicking on the star above.";
								}
							}else{
								reviews = "YOU HAVE ALREADY REVIEWED THE ADVISOR.";
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
	
