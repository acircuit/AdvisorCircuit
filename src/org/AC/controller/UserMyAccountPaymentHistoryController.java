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

import org.AC.DAO.UserNotificationDAO;
import org.AC.DAO.UserPaymentHistoryDAO;
import org.AC.dto.PaymentDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserMyAccountPaymentHistoryController
 */
@WebServlet("/UserMyAccountPaymentHistoryController")
public class UserMyAccountPaymentHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserMyAccountPaymentHistoryController.class);   


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of UserMyAccountPaymentHistoryController");
		int userId=0;
		String username="";
		try{
			userId = (int) request.getSession().getAttribute("userId");
			}catch(Exception e){
				response.sendRedirect("Error");
			}
		if(userId != 0){
			//Getting the info to display on the paymenthistory.jsp
			List<PaymentDTO> paymentHistory =  new ArrayList<PaymentDTO>();
			List<PaymentDTO> requests =  new ArrayList<PaymentDTO>();
			List<PaymentDTO> payments =  new ArrayList<PaymentDTO>();
			List<Integer> sessionId =  new ArrayList<Integer>();
			List<Integer> requestId =  new ArrayList<Integer>();
			//Getting sessionId,RequestId,accepteddate from session_table
			UserPaymentHistoryDAO payment = new UserPaymentHistoryDAO();
			paymentHistory = payment.GetSessionInfo(userId);
			for (PaymentDTO paymentDTO : paymentHistory) {
				sessionId.add(paymentDTO.getSessionId());
				requestId.add(paymentDTO.getRequestId());
				paymentDTO.setAcceptedDateString((new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(paymentDTO.getAcceptedDate().getTime()))));

			}
			
			//Getting service,mode,price,discount,amount from request table
			UserPaymentHistoryDAO requestInfo = new UserPaymentHistoryDAO();
			requests = requestInfo.GetRequestInfo(requestId);
			for (PaymentDTO paymentDTO : requests) {
				double discount = paymentDTO.getDiscount()* paymentDTO.getPrice() /100;
				paymentDTO.setDiscount((int)discount);
			}
			
			//Getting date of payment, payment mode, and tracking id from payment table
			UserPaymentHistoryDAO paymentInfo = new UserPaymentHistoryDAO();
			payments = paymentInfo.GetPaymentInfo(sessionId);
			for (PaymentDTO paymentDTO : payments) {
				paymentDTO.setPurchaseDateString((new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(paymentDTO.getDateOfPurchase().getTime()))));
			}
			
			String url =  request.getRequestURI();
			url = url.substring(url.lastIndexOf('/')+1);
			UserNotificationDAO notify = new UserNotificationDAO();
			notify.SetNotificationRead(url, userId);
			
			request.setAttribute("session", paymentHistory);
			request.setAttribute("request", requests);
			request.setAttribute("payment", payments);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/userpaymenthistrory");
	        rd.forward(request, response);
		}

		logger.info("Entered doGet method of UserMyAccountPaymentHistoryController");
	}

}
