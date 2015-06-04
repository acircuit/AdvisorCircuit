 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 30/11/2014
 * ************************************************************************************************/

package org.AC.controller;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch the details from the view details form and change the status 
* of the the request depending upon the advisor acceptance or cancellation of the request
* 
* 
*
***************************************************************************************************/

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
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

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdvisorMyAccountRequestViewDetailsDAO;
import org.AC.DAO.ChangeRequestStatusDAO;
import org.AC.DAO.UserNotificationDAO;
import org.AC.Util.SendMail;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
	
	/**************************************COMMENTS***************************************************
	 * This function will first check whether the advisor has cancelled the session or accepted the request.
	 *   If the advisor has accepted the request then it will put the details in session tab and change the status accordingly.
	 *   If the advisor has cancelled the session then it will change the request status to "REQUEST REJECTED BY ADVISOR"
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
/**
 * Servlet implementation class AdvisorMyAccountRequestViewDetailsFormController
 */
@WebServlet("/AdvisorMyAccountRequestViewDetailsFormController")
public class AdvisorMyAccountRequestViewDetailsFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountRequestViewDetailsFormController.class);     
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This servlet will retrieve the fields from the view details form and then put them into the session table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorMyAccountRequestViewDetailsFormController");
		
		String uId = "";
		String rId = "";
		String cancel = "";
		String advisorName = "";
		Boolean isStatusCommit = false;
		int aId = 0;
		 uId = (String) request.getParameter("uId");
		 rId = (String) request.getParameter("rId");
		 cancel = (String) request.getParameter("cancel");
		String acceptedTime = request.getParameter("optionsRadios");
		String sessionPlan = request.getParameter("sessionplan");
		String emailDate = request.getParameter("emaildate");
		String isFree  = request.getParameter("isFree");
		String service  = request.getParameter("service");
		 String userIsFree = request.getParameter("userIsFree");
		Properties prop = new Properties();
	    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop.load(resourceAsStream);
		try{
			aId = (int) request.getSession().getAttribute("advisorId");
			advisorName = (String) request.getSession().getAttribute("username");
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("login");
		}
		//Check if the advisor has cancelled the session
		if( cancel == null ){
			//Advisor has aceepted the request
			int sessionId = 0;
			
			if(uId != null && !("").equals(uId) && rId != null && !("").equals(rId) && aId != 0
					&&  sessionPlan != null && !("").equals(sessionPlan)){
				
				if(acceptedTime != null){
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				    Date date = null;
					try {
						date = format1.parse(acceptedTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    acceptedTime = format2.format(date);
					String status = "PENDING FOR USER PAYMENT";
					AdvisorMyAccountRequestViewDetailsDAO session = new AdvisorMyAccountRequestViewDetailsDAO();
				    sessionId = session.setSessionDetails(aId, rId, uId, acceptedTime, sessionPlan,status,false);
					if(sessionId != 0){
					String status1 = "REQUEST ACCEPTED BY ADVISOR";
					int rId1 = Integer.parseInt(rId);
					ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
					isStatusCommit = requestStatus.setStatus( status1,rId1);
					//Notify the user 
					String comment = "Your request has been accepted by the Advisor . Pay to confirm the session";
					String href = "UserRequestViewDetails?rId="+rId;
					UserNotificationDAO userNotification = new UserNotificationDAO();
					userNotification.InsertNotification(comment, href, uId);
					
					//Getting username and the advisor name for the admin notification
					AdminNotificationDAO userName = new AdminNotificationDAO();
					String uName = userName.GetUserName(uId);
					
					String comment1 = advisorName+ " accepted the session with "+ uName;
					String ahref = "AdminRequestViewDetails?rId="+rId;;
					//Notify Admin
					AdminNotificationDAO notify = new AdminNotificationDAO();
					notify.InsertNotification(comment1, ahref);
					}
				}else{
					Date datepicker1 = null;
					Date datepicker2= null;
					Date datepicker3= null;
					String datetimepicker1="";
					String datetimepicker2="";
					String datetimepicker3="";
					String date1 = request.getParameter("datetimepicker1");
					String date2 = request.getParameter("datetimepicker2");
					String date3 = request.getParameter("datetimepicker3");
					String date4 = request.getParameter("datetimepickeremail");
					Boolean isNewDate = true;
					Boolean isEmail = false;
					Boolean isAccepted = false;;
					Boolean isNewDatesCommit =false;
					if(date4 != null && !date4.equals("")){
						DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						try {
							datepicker1 = dateFormat.parse(date4);
						} catch (ParseException e) {
							logger.error("doPost method of AdvisorMyAccountRequestViewDetailsFormController threw error:"+e.getMessage());
							e.printStackTrace();
						}
						SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
						datetimepicker1 = dateFormat1.format(datepicker1);
						isEmail = true;
						isAccepted= true;
					}else if (date1!= null && date2 != null && date3 != null && date4 != null && date1.equals("") && date2.equals("") && date3.equals("") && date4.equals("")) {
						SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
						Date date = null;
						try {
							date = format1.parse(emailDate);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
						emailDate = dateFormat1.format(date);
					
						acceptedTime= emailDate;
						isNewDate = false;
						isEmail = true;
					}else{
						DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
						try {
							datepicker1 = dateFormat.parse(date1);
							datepicker2 = dateFormat.parse(date2);
							datepicker3 = dateFormat.parse(date3);
						} catch (ParseException e) {
							logger.error("doPost method of AdvisorMyAccountRequestViewDetailsFormController threw error:"+e.getMessage());
							e.printStackTrace();
						}
						SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
						datetimepicker1 = dateFormat1.format(datepicker1);
						datetimepicker2 = dateFormat1.format(datepicker2);
						datetimepicker3 = dateFormat1.format(datepicker3);
					}
					String status = "";
					if(isEmail && !isAccepted){
						status = "PENDING FOR USER PAYMENT";
					}else{
						status = "PENDING FOR USER PAYMENT WITH NEW DATES";
					}
					AdvisorMyAccountRequestViewDetailsDAO session = new AdvisorMyAccountRequestViewDetailsDAO();
					sessionId = session.setSessionDetails(aId, rId, uId, acceptedTime, sessionPlan,status,isEmail);			
					if(sessionId != 0 && isNewDate){
						AdvisorMyAccountRequestViewDetailsDAO newDates = new AdvisorMyAccountRequestViewDetailsDAO();
						isNewDatesCommit = newDates.setNewDates(sessionId, datetimepicker1, datetimepicker2, datetimepicker3);
						
					}	
						String status1="";
						if(isEmail && !isAccepted){
							status1 = "REQUEST ACCEPTED BY ADVISOR";
						}else{
							status1 = "REQUEST ACCEPTED BY ADVISOR WITH NEW DATES";
						}
						int rId1 = Integer.parseInt(rId);
						ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
						isStatusCommit = requestStatus.setStatus( status1,rId1);
						if(sessionId != 0 && isNewDate){
							//Notify the user 
							String comment = "Your request has been accepted by the Advisor with revised dates! Choose 1 date and Pay to confirm the session";
							String href = "UserRequestViewDetails?rId="+rId;
							UserNotificationDAO userNotification = new UserNotificationDAO();
							userNotification.InsertNotification(comment, href, uId);
							
							//Getting username and the advisor name for the admin notification
							AdminNotificationDAO userName = new AdminNotificationDAO();
							String uName = userName.GetUserName(uId);
							
							String comment1 = advisorName+ " accepted the session with "+ uName+" with revised dates";
							String ahref = "AdminRequestViewDetails?rId="+rId;
							//Notify Admin
							AdminNotificationDAO notify = new AdminNotificationDAO();
							notify.InsertNotification(comment1, ahref);
						}else{
							//Notify the user 
							String comment = "Your request has been accepted by the Advisor . Pay to confirm the session";
							String href = "UserRequestViewDetails?rId="+rId;
							UserNotificationDAO userNotification = new UserNotificationDAO();
							userNotification.InsertNotification(comment, href, uId);
							
							//Getting username and the advisor name for the admin notification
							AdminNotificationDAO userName = new AdminNotificationDAO();
							String uName = userName.GetUserName(uId);
							
							String comment1 = advisorName+ " accepted the session with "+ uName;
							String ahref = "AdminRequestViewDetails?rId="+rId;
							//Notify Admin
							AdminNotificationDAO notify = new AdminNotificationDAO();
							notify.InsertNotification(comment1, ahref);
						}
				}
				//RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdvisorMyAccountRequestController");
		        //rd.forward(request, response);
				if(isStatusCommit){
					
					//Send Mail to Admin
					String subject = "Advisor Accepted the session!";
					String content = "Hi, <br><br>The SESSION REQUEST was accepted by the advisor!! Following are the details:<br>Advisor Name : " +advisorName+"<br>Request Id: "+rId+"<br>Session Id: "+sessionId+"<br> Accepted Date: "+acceptedTime+"<br>Session Plan: "+sessionPlan+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='15%'>";
					SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
					mail.start();
					response.sendRedirect("AdvisorRequests?answered=true");
				}
						
			}
		}else{
			//The advisor has cancelled the session
			//Change the status of the request to "REQUEST REJECTED BY ADVISOR"
			String status1 = "REQUEST REJECTED BY ADVISOR";
			int rId1 = Integer.parseInt(rId);
			ChangeRequestStatusDAO requestStatus = new ChangeRequestStatusDAO();
			isStatusCommit = requestStatus.setStatus( status1,rId1);
			if(isStatusCommit){
				if(isFree.equals("true")){
					//Decrement the free session count from the advisor services table
					AdvisorMyAccountRequestViewDetailsDAO decrem = new AdvisorMyAccountRequestViewDetailsDAO();
					decrem.DecrementFreeSession(aId,service);
				}
				if(userIsFree.equals("true")){
					//Toggle the free session column in the userdetails table
					AdvisorMyAccountRequestViewDetailsDAO toggle = new AdvisorMyAccountRequestViewDetailsDAO();
					toggle.ToggleUserFreeSession(Integer.parseInt(uId));
				}
				//Notify the user 
				String comment = "We're sorry but the Advisor has declined the session. You will get a mail regarding this soon.";
				String href = "UserCancelledSessionViewDetails?rId="+rId;
				UserNotificationDAO userNotification = new UserNotificationDAO();
				userNotification.InsertNotification(comment, href, uId);
				
				//Getting username and the advisor name for the admin notification
				AdminNotificationDAO userName = new AdminNotificationDAO();
				String uName = userName.GetUserName(uId);
				
				String comment1 = advisorName+ " has rejected the session with "+ uName;
				String ahref = "AdminCancelledSessionViewDetails?rId="+rId;
				//Notify Admin
				AdminNotificationDAO notify = new AdminNotificationDAO();
				notify.InsertNotification(comment1, ahref);
				
				//Send Mail to Admin
				String subject = "Advisor Rejected the session!";
				String content = "Hi, <br><br>The SESSION REQUEST was accepted by the advisor!! Following are the details: <br>Advisor Name : " +advisorName+"<br>Request Id: "+rId1+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='15%'>";
				SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
				mail.start();
				response.sendRedirect("AdvisorCancelledSessions");
			}
		}
		logger.info("Exit doPost method of AdvisorMyAccountRequestViewDetailsFormController");
	}

}
