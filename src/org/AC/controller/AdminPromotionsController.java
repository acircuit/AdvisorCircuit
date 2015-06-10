package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminPromotionsDAO;
import org.AC.dto.PromotionsDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminPromotionsController
 */
@WebServlet("/AdminPromotionsController")
public class AdminPromotionsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminPromotionsController.class);


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdminPromotionsController");
		
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
		if(isError!= null && !isError){
			//Getting all the promotions.
			List<PromotionsDTO> promotions = new ArrayList<PromotionsDTO>();		
			AdminPromotionsDAO promo = new AdminPromotionsDAO();
			promotions = promo.GetPromotions();
			request.setAttribute("promotions", promotions);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminPromotions.jsp");
	        rd.forward(request, response);		
		}	
		logger.info("Exit doGet method of AdminPromotionsController");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdminPromotionsController");
			
			Boolean isCommit = false;
			String id = request.getParameter("Id");
			String activate = request.getParameter("activate"); 
			String referral = request.getParameter("Referral");
			String promo_code = request.getParameter("promo_code");
			
			if(id != null && activate != null){
				AdminPromotionsDAO action = new AdminPromotionsDAO();
				isCommit = action.UpdatePromotion(id,activate);
				if(isCommit){
					if(activate.equals("true")){
						response.getWriter().write("The promotion has been activated. Please refresh.");
					}else{
						response.getWriter().write("The promotion has been deactivated. Please refresh.");
					}
				}
			}else if (referral != null) {
				int pId =1;
				AdminPromotionsDAO action = new AdminPromotionsDAO();
				int promoId = action.CheckPromotionActive(pId);
				if(promoId == 1){
					response.getWriter().write("true");
				}
			}else if (promo_code != null) {
				AdminPromotionsDAO action = new AdminPromotionsDAO();
				int userId = action.CheckPromoCode(promo_code);
				response.getWriter().write(String.valueOf(userId));
			}
			
		logger.info("Exit doPost method of AdminPromotionsController");
		
	}

}
