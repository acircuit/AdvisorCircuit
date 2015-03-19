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
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

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
		Properties prop1 = new Properties();
	    InputStream resourceAsStream1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop1.load(resourceAsStream1);
		if(sId!= null && rId != null && acceptedDate == null){
			Properties prop = new Properties();
		    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/Path.properties");
		    prop.load(resourceAsStream);
			
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
					String content = "Hi,<br>User Payment Done For Session Id : "+sId+" <br>Now, Waiting for session to happen"+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content, prop1.getProperty("MAIL_ADMIN"),prop1.getProperty("MAIL_ADMIN"));
					mail.start();
					response.sendRedirect("UserUpcomingSessions");
					//RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserMyaccountUpcomingSessionController");
			        //rd.forward(request, response);
				}
			}
		}else if (sId!= null && rId != null && acceptedDate != null) {
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
				//Changing the session status to "WAITING FOR SESSION" and the accepted date
				String status1 ="WAITING FOR SESSION";
				ChangeSessionStatusDAO sessionStatus = new ChangeSessionStatusDAO();
				Boolean isSessionStatus = sessionStatus.setStatus(status1, sId,acceptedDate);
				if(isSessionStatus){
					//Checkin the service=cvcritique or mockinterview
					ChangeRequestStatusDAO copy = new ChangeRequestStatusDAO();
					String service = copy.GetService(rId);
					String subject = "User Payment Done!";
					String content = "Hi,<br>User Payment Done For Session Id : "+sId+" <br>Now, Waiting for session to happen"+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content, prop1.getProperty("MAIL_ADMIN"),prop1.getProperty("MAIL_ADMIN"));
					mail.start();
					response.sendRedirect("UserUpcomingSessions");
					//RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserMyaccountUpcomingSessionController");
			        //rd.forward(request, response);
				}
			}
		}
		logger.info("Exit doPost method of UserMyAccountPaymentController");
	}

}
