package org.AC.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorPaymentHistoryDAO;
import org.AC.DAO.UserPaymentHistoryDAO;
import org.AC.dto.PaymentDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorMyAccountPaymentHistoryController
 */
@WebServlet("/AdvisorMyAccountPaymentHistoryController")
public class AdvisorMyAccountPaymentHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountPaymentHistoryController.class);     



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorMyAccountPaymentHistoryController");
		int advisorId = 0;
		try{
		    advisorId = (int) request.getSession().getAttribute("advisorId"); 
			}catch(Exception e){
				response.sendRedirect("Error");
			}
		if(advisorId != 0){
			//Getting the info to display on the paymenthistory.jsp
			List<PaymentDTO> paymentHistory =new ArrayList<PaymentDTO>();
			List<PaymentDTO> requests =  new ArrayList<PaymentDTO>();
			List<PaymentDTO> payments =  new ArrayList<PaymentDTO>();
			List<Integer> sessionId =  new ArrayList<Integer>();
			List<Integer> requestId =  new ArrayList<Integer>();
			AdvisorPaymentHistoryDAO session = new AdvisorPaymentHistoryDAO();
			paymentHistory = session.GetSessionInfo(advisorId);
			for (PaymentDTO paymentDTO : paymentHistory) {
				sessionId.add(paymentDTO.getSessionId());
				requestId.add(paymentDTO.getRequestId());
				paymentDTO.setAcceptedDateString((new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(paymentDTO.getAcceptedDate().getTime()))));
			}
			
			
			//Getting service,mode from request table
			AdvisorPaymentHistoryDAO requestInfo = new AdvisorPaymentHistoryDAO();
			requests = requestInfo.GetRequestInfo(requestId);
			for (PaymentDTO paymentDTO : requests) {
				double discount = paymentDTO.getDiscount()* paymentDTO.getPrice() /100;
				paymentDTO.setDiscount((int)discount);
			}
			//Getting date of payment, payment mode, and tracking id from payment table
			AdvisorPaymentHistoryDAO paymentInfo = new AdvisorPaymentHistoryDAO();
			payments = paymentInfo.GetPaymentInfo(sessionId);			
			request.setAttribute("session", paymentHistory);
			request.setAttribute("request", requests);
			request.setAttribute("payment", payments);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/paymenthistrory");
	        rd.forward(request, response);
		}
		logger.info("Entered doGet method of AdvisorMyAccountPaymentHistoryController");
	}


}
