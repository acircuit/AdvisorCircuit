package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdminPromotionsDAO;
import org.AC.DAO.UserDetailsDAO;
import org.AC.DAO.UserNotificationDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserActiveURLController
 */
@WebServlet("/UserActiveURLController")
public class UserActiveURLController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(UserActiveURLController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of UserActiveURLController");
		String userId = request.getParameter("activate");
		// Activate the user
		UserDetailsDAO user = new UserDetailsDAO();
		Boolean activated = user.ActivateUser(userId);
		if (activated) {
			//Check if the promotion is active
			int pId =1;
			AdminPromotionsDAO action = new AdminPromotionsDAO();
			int promoId = action.CheckPromotionActive(pId);
			if(promoId == 1){
				//Get the promo code used by the user
				UserDetailsDAO promo = new UserDetailsDAO();
				String code = promo.GetPromoCodeUsed(userId);
				if(!code.equals("")){
					code = code.substring(5);
				}
				
				//Increment the user's referral count
				UserDetailsDAO count = new UserDetailsDAO();
				count.IncrementReferral(code);
		
				UserDetailsDAO refCount = new UserDetailsDAO();
				int referralCount = refCount.GetReferralCount(code);
				
				String comment2 = "User with UserId = "+userId+" signed up as a user using your referral code";
				String href2 = "usermyaccount";
				UserNotificationDAO userNotify = new UserNotificationDAO();
				userNotify.InsertNotification(comment2, href2, code);
				
				
				if(referralCount == 10){
					String comment1 = "User with User Id="+ code+" has 10 referrals";
					String href1 = "AdminUsers";
					AdminNotificationDAO notify1 = new AdminNotificationDAO();
					notify1.InsertNotification(comment1, href1);
					
					String comment3 = "Congratulations ! You've won the competition ! We'll contact you within 24 Hours !";
					String href3 = "usermyaccount";
					UserNotificationDAO userNotify1 = new UserNotificationDAO();
					userNotify1.InsertNotification(comment3, href3, code);
				}
			}
			response.sendRedirect("UserActivationURL");
		} else {
			response.sendRedirect("UserActivationURL?active=false");
		}

		logger.info("Exit doGet method of UserActiveURLController");
	}
}
