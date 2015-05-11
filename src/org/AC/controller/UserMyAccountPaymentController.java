 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 20/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ChangeRequestStatusDAO;
import org.AC.DAO.ChangeSessionStatusDAO;
import org.AC.Util.SendMail;
import org.AC.dto.UserRequestDTO;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.*;

import com.ccavenue.security.*;

import java.util.*;

/**
 * Servlet implementation class UserMyAccountPaymentController
 */

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch the session Id and set the status as "WAITING FOR SESSION";
* 
* 
*
***************************************************************************************************/
@WebServlet("/UserMyAccountPaymentController")
public class UserMyAccountPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserMyAccountPaymentController.class);   
	
 	/**************************************COMMENTS*****************************************************
	 * This class will fetch the session Id and set the status as "WAITING FOR SESSION";
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
		logger.info("Entered doPost method of UserMyAccountPaymentController");
		String sId = request.getParameter("sessionId");
		String rId = request.getParameter("rId");
		String acceptedDate = request.getParameter("optionsRadios");
		String encResp= request.getParameter("encResp");
		String amount1= request.getParameter("amount");
		Properties prop1 = new Properties();
	    InputStream resourceAsStream1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop1.load(resourceAsStream1);
		Properties prop = new Properties();
	    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/Path.properties");
	    prop.load(resourceAsStream);
		if(sId!= null && rId != null && acceptedDate == null){

			double advisorPayment = 0.0;
			double fee = 0.0;
			double amount =0.0;
			String trackingId = "N/A";
			String paymentMode = "N/A";
			String order_status = "N/A";
			UserRequestDTO req = new UserRequestDTO();
		    ChangeRequestStatusDAO pay = new ChangeRequestStatusDAO();
			req = pay.GetPaymentInfo(Integer.parseInt(rId));
			if(req.getIsFree() != null && req.getIsFree()){
				if(req.getDuration().equals("0.5")){
					advisorPayment = 0.0;
					fee = 0.0;
				}else if (req.getDuration().equals("0.75")) {
					advisorPayment =  (int)Math.ceil(((req.getPrice() * 100)/(390)));
					fee = req.getAmount() - advisorPayment;
				}else if (req.getDuration().equals("1")) {
					advisorPayment =  (int)Math.ceil(((req.getPrice() * 100)/(260)));
					fee = req.getAmount() - advisorPayment;
				}else if (req.getDuration().equals("1.5")) {
					advisorPayment =  (int)Math.ceil(((req.getPrice() * 200)/(390)));
					fee = req.getAmount() - advisorPayment;
				}else{
					advisorPayment = 0.0;
					fee = 0.0;
				}
			}else{
				advisorPayment =  (req.getPrice() * 100)/(130);
				fee = req.getAmount() - advisorPayment;
			}
			//Entering values in Payment table
			ChangeSessionStatusDAO success = new ChangeSessionStatusDAO();
			Boolean isSessionUpdate = success.UpdateSessionPayment(sId,Integer.parseInt(rId),amount,advisorPayment,fee,trackingId,paymentMode,order_status);
		    
		    
			//Changing the request status to "USER PAYMENT DONE"
			String status = "USER PAYMENT DONE";
			ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
			Boolean isStatusCommit = requestStatus.setStatus(status, Integer.parseInt(rId));
			if(isStatusCommit){
			
				//Changing the session status to "WAITING FOR SESSION"
				String status1 ="WAITING FOR SESSION";
				ChangeSessionStatusDAO sessionStatus = new ChangeSessionStatusDAO();
				Boolean isSessionStatus = sessionStatus.setStatus(status1, sId);
				if(isSessionStatus){
					//Checkin the service=cvcritique or mockinterview
					ChangeRequestStatusDAO copy = new ChangeRequestStatusDAO();
					String service = copy.GetService(rId);				
					String subject = "User Payment Done!";
					String content = "Hi, <br><br>User Payment Done For Session Id : "+sId+" <br>Now, Waiting for session to happen"+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content, prop1.getProperty("MAIL_ADMIN"),prop1.getProperty("MAIL_ADMIN"));
					mail.start();
					response.sendRedirect("UserUpcomingSessions");
					//RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserMyaccountUpcomingSessionController");
			        //rd.forward(request, response);
				}
			}
		}else if (sId!= null && rId != null && acceptedDate != null) {
			if(amount1 != null && !amount1.equals("0")){
				acceptedDate =acceptedDate.replace(':','#');
				response.sendRedirect("payment?order_id="+sId+"&amount="+amount1+"&merchant_param1="+acceptedDate);
			}else{
				Date datepicker1 = null;
				//Changing the request status to "USER PAYMENT DONE"
				String status = "USER PAYMENT DONE";
				ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
				Boolean isStatusCommit = requestStatus.setStatus(status, Integer.parseInt(rId));
				if(isStatusCommit){
					/*DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
					try {
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
						datepicker1 = dateFormat1.parse(acceptedDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					double advisorPayment = 0.0;
					double fee = 0.0;
					double amount =0.0;
					String trackingId = "N/A";
					String paymentMode = "N/A";
					String order_status = "N/A";
					UserRequestDTO req = new UserRequestDTO();
				    ChangeRequestStatusDAO pay = new ChangeRequestStatusDAO();
					req = pay.GetPaymentInfo(Integer.parseInt(rId));
					if(req.getIsFree() != null && req.getIsFree()){
						if(req.getDuration().equals("0.5")){
							advisorPayment = 0.0;
							fee = 0.0;
						}else if (req.getDuration().equals("0.75")) {
							advisorPayment =  (int)Math.ceil(((req.getPrice() * 100)/(390)));
							fee = req.getAmount() - advisorPayment;
						}else if (req.getDuration().equals("1")) {
							advisorPayment =  (int)Math.ceil(((req.getPrice() * 100)/(260)));
							fee = req.getAmount() - advisorPayment;
						}else if (req.getDuration().equals("1.5")) {
							advisorPayment =  (int)Math.ceil(((req.getPrice() * 200)/(390)));
							fee = req.getAmount() - advisorPayment;
						}else{
							advisorPayment = 0.0;
							fee = 0.0;
						}
					}else{
						advisorPayment =  (req.getPrice() * 100)/(130);
						fee = req.getAmount() - advisorPayment;
					}
					//Entering values in Payment table
					ChangeSessionStatusDAO success = new ChangeSessionStatusDAO();
					Boolean isSessionUpdate = success.UpdateSessionPayment(sId,Integer.parseInt(rId),amount,advisorPayment,fee,trackingId,paymentMode,order_status);
				    
					//Changing the session status to "WAITING FOR SESSION" and the accepted date
					String status1 ="WAITING FOR SESSION";
					ChangeSessionStatusDAO sessionStatus = new ChangeSessionStatusDAO();
					Boolean isSessionStatus = sessionStatus.setStatus(status1, sId,acceptedDate);
					if(isSessionStatus){
						//Checkin the service=cvcritique or mockinterview
						ChangeRequestStatusDAO copy = new ChangeRequestStatusDAO();
						String service = copy.GetService(rId);
						String subject = "User Payment Done!";
						String content = "Hi, <br><br>User Payment Done For Session Id : "+sId+" <br>Now, Waiting for session to happen"+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
						SendMail mail = new SendMail(subject, content, prop1.getProperty("MAIL_ADMIN"),prop1.getProperty("MAIL_ADMIN"));
						mail.start();
						response.sendRedirect("UserUpcomingSessions");
						//RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserMyaccountUpcomingSessionController");
				        //rd.forward(request, response);
					}
				}
			}
		}else if (encResp != null) {
			String workingKey = prop.getProperty("WORKING_KEY");		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
			AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
			String decResp = aesUtil.decrypt(encResp);
			StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
			String order_status = "";
			String pair=null, pname=null, pvalue=null;
			String amount = "",trackingId="",paymentMode="",accDate="";
			while (tokenizer.hasMoreTokens()) {
				pair = (String)tokenizer.nextToken();
				if(pair!=null) {
					StringTokenizer strTok=new StringTokenizer(pair, "=");
					pname=""; pvalue="";
					if(strTok.hasMoreTokens()) {
						pname=(String)strTok.nextToken();
						if(strTok.hasMoreTokens())
							pvalue=(String)strTok.nextToken();
							if(pname.equals("order_id")){
								sId = pvalue;
							}
							if(pname.equals("order_status")){
								order_status = pvalue;
							}
							if(pname.equals("amount")){
								amount = pvalue;
							}
							if(pname.equals("tracking_id")){
								trackingId = pvalue;
							}
							if(pname.equals("payment_mode")){
								paymentMode = pvalue;
							}
							if(pname.equals("merchant_param1")){
								accDate = pvalue;
								accDate = accDate.replace('#',':');
							}
							
							
					}
				}
			}
			if(sId != null && !sId.equals("") && order_status.equals("Success")){
				int advisorPayment = 0;
				int fee = 0;
				//Getting the Request Id from session Id
				ChangeRequestStatusDAO reqId = new ChangeRequestStatusDAO();
				int requestId = reqId.GetRequestIdFromSessionId(sId);
				//Changing the request status to "USER PAYMENT DONE"
				String status = "USER PAYMENT DONE";
				ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
				Boolean isStatusCommit = requestStatus.setStatus(status, requestId);
				if(isStatusCommit){
					UserRequestDTO req = new UserRequestDTO();
					//Payment Successful
					//Getting required info from request table
					ChangeRequestStatusDAO pay = new ChangeRequestStatusDAO();
					req = pay.GetPaymentInfo(requestId);
					if(req.getIsFree() != null && req.getIsFree()){
						if(req.getDuration().equals("0.5")){
							advisorPayment = 0;
							fee = 0;
						}else if (req.getDuration().equals("0.75")) {
							advisorPayment =  (int)Math.ceil(((req.getPrice() * 100)/(390)));
							fee = req.getAmount() - advisorPayment;
						}else if (req.getDuration().equals("1")) {
							advisorPayment =  (int)Math.ceil(((req.getPrice() * 100)/(260)));
							fee = req.getAmount() - advisorPayment;
						}else if (req.getDuration().equals("1.5")) {
							advisorPayment =  (int)Math.ceil(((req.getPrice() * 200)/(390)));
							fee = req.getAmount() - advisorPayment;
						}else{
							advisorPayment = 0;
							fee = 0;
						}
					}else{
						advisorPayment =  (int)Math.ceil(((req.getPrice() * 100)/(130)));
						fee = req.getAmount() - (advisorPayment);
					}
						//Entering values in Payment table
						ChangeSessionStatusDAO success = new ChangeSessionStatusDAO();
						Boolean isSessionUpdate = success.UpdateSessionPayment(sId,requestId,Double.parseDouble(amount),advisorPayment,fee,trackingId,paymentMode,order_status);

					if(isSessionUpdate){
						//Changing the session status to "WAITING FOR SESSION"
						String status1 ="WAITING FOR SESSION";
						String ccavStatus= "Success";
						ChangeSessionStatusDAO sessionStatus = new ChangeSessionStatusDAO();
						Boolean isSessionStatus = sessionStatus.UpdateStatus(sId,accDate);
						
						if(isSessionStatus){
								String subject = "User Payment Done!";
								String content = "Hi, <br><br>User Payment Done For Session Id : "+sId+" <br>Now, Waiting for session to happen"+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
								SendMail mail = new SendMail(subject, content, prop1.getProperty("MAIL_ADMIN"),prop1.getProperty("MAIL_ADMIN"));
								mail.start();
								String site = new String("thankyouforpayment");
							    response.setStatus(response.SC_MOVED_TEMPORARILY);
							    response.setHeader("Location", site); 
						}
					}			
				}
			
			}else{
		     // New location to be redirected
		        String site = new String("UserRequests");
		        response.setStatus(response.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", site); 
		        
			}

		}
		logger.info("Exit doPost method of UserMyAccountPaymentController");
	}

}
