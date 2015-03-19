 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 23/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ReviewAndRecommendationDAO;
import org.AC.DAO.UserDetailsDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.RecommendationDTO;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch  the advisorId and then get the reviews
* 
* 
***************************************************************************************************/
/**
 * Servlet implementation class GetReviewsController
 */
@WebServlet("/GetReviewsController")
public class GetReviewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GetReviewsController.class);     

    
 	/**************************************COMMENTS***************************************************
	 * This method will fetch  the advisorId and then get the reviews
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
		logger.info("Entered doGet method of AdminMyAccountCancelledSessionViewDetailsController");
		String advisorId = request.getParameter("aId");
		if(advisorId != null){
			List<RecommendationDTO> reviews = new ArrayList<RecommendationDTO>();
			List<Integer> userIds = new ArrayList<Integer>();
			//Get all the review from the session table where aId = advisorId
			ReviewAndRecommendationDAO review = new ReviewAndRecommendationDAO();
			reviews = review.GetReviews(advisorId);
			for (RecommendationDTO recommendationDTO : reviews) {
				int counter = 0;
				if(userIds.size() == 0){
					userIds.add(recommendationDTO.getUserId());
				}else{
					for (Integer integer : userIds) {
						if(integer == recommendationDTO.getUserId()){
							counter++;
						}
					}
					if(counter == 0){
						userIds.add(recommendationDTO.getUserId());
					}
				}
			}
			//Getting the User Details
			List<UserDetailsDTO> list = new ArrayList<UserDetailsDTO>();
			UserDetailsDAO userDetails = new UserDetailsDAO();
			list = userDetails.getUserDetails(userIds);
			String data = "";
			for (UserDetailsDTO userDetailsDTO : list) {
				for (RecommendationDTO reviewMessage : reviews) {
					if(userDetailsDTO.getUserId() == reviewMessage.getUserId()){
						GetRelativeImageURL image = new GetRelativeImageURL();
						String img = image.getImageURL(userDetailsDTO.getImage());
						String str = "<img src ='"+img+"'></img><h3>"+userDetailsDTO.getFullName()+"</h3><p style='text-align: left;font-weight: bold;color: #000000;width: 550px;'>"+reviewMessage.getReviewMessage()+"</p>";
						data = data.concat(str);
					}
				}
			}
			if(reviews.size() > 0){
			response.getWriter().write(data);
			}else{
				response.getWriter().write("<p style='color: #000000;'>The Advisor has not been reviewed yet</p>");
			}
		}	
		logger.info("Entered doGet method of AdminMyAccountCancelledSessionViewDetailsController");
	}

}
