package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdminSessionDAO;
import org.AC.DAO.AdvisorMyAccountSessionDAO;
import org.AC.DAO.AdvisorNotificationDAO;
import org.AC.DAO.ChangeRequestStatusDAO;
import org.AC.DAO.UserNotificationDAO;
import org.AC.Util.SendMail;
import org.AC.Util.SetFile;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class ApproveFeedbackController
 */
@WebServlet("/ApproveFeedbackController")
@MultipartConfig
public class ApproveFeedbackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ApproveFeedbackController.class);     


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of ApproveFeedbackController");
		String sId = request.getParameter("sId");
		String type = request.getParameter("type");
		String details= request.getParameter("details");
		String service= request.getParameter("service");
		if(sId != null && type == null && details== null ){
			
			//Approve the Feedback
			AdminSessionDAO form = new AdminSessionDAO();
			Boolean isApproved = form.ApproveFeedback(sId);
			if(isApproved){
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
				
				String userComment = "You've received a feedback form from "+advisorName;
				String userHref ="UserUpcomingSessionViewDetails?rId="+ids[2];
				UserNotificationDAO user = new UserNotificationDAO();
				user.InsertNotification(userComment, userHref, String.valueOf(ids[0]));
				
				//Notify advisor
				String comment = "Your feedback has been successfully sent to "+uName;
				String href = "AdvisorUpcomingSessionViewDetails?rId="+ids[2];
				AdvisorNotificationDAO notify = new AdvisorNotificationDAO();
				notify.InsertRequestNotification(comment, String.valueOf(ids[1]) , href);
				response.getWriter().write("The FeedBack has been approved");
			}
		}else if (sId != null && type != null && type.equals("advisor") && details== null && service!= null) {
				//Delete Previous Feedbacks
				AdminSessionDAO form = new AdminSessionDAO();
				Boolean isDeleted = form.DeleteFeedback(sId);
				Properties prop = new Properties();
			    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/Path.properties");
			    prop.load(resourceAsStream);
			    String path ="";
			    String fileURL ="";
			    if(service.equals("mi")){
			    	 path = MessageFormat.format(prop.getProperty("MOCKINTERVIEW_CRTIQUE_FEEDBACK_FORM_DESTINATION_PATH"), sId);
			    }else if (service.equals("cv")) {
			    	path = MessageFormat.format(prop.getProperty("CV_CRTIQUE_FEEDBACK_FORM_DESTINATION_PATH"), sId);
				}
				SetFile file = new SetFile();
				fileURL = file.PutFile(request, response, sId,path);
				Boolean isFeedbackFormCommit = false;
				 if(service.equals("mi")){
					 ChangeRequestStatusDAO file1 = new ChangeRequestStatusDAO();
					  isFeedbackFormCommit = file1.SetFeedBack(sId,"mockinterview",fileURL,false);
			    }else if (service.equals("cv")) {
			    	 ChangeRequestStatusDAO file1 = new ChangeRequestStatusDAO();
				      isFeedbackFormCommit = file1.SetFeedBack(sId,"cvcritique",fileURL,false);
				}
				
				if(isFeedbackFormCommit){
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
					
					 String comment = advisorName + "  has sent a Feedback form to " + uName;
					 String href = "AdminMyUpcomingSessionViewDetail?rId="+ids[2];
					 AdminNotificationDAO admin = new AdminNotificationDAO();
					 admin.InsertNotification(comment, href);
					
					Properties prop1 = new Properties();
			        InputStream resourceAsStream1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
			        prop1.load(resourceAsStream1);
			        String subject1= "Advisor "+service+" Feedback!";
					String content1 = "Hi, <br><br> An Advisor gave "+service+ "feedback. Following are the details: <br>Session Id : "+sId+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='15%'>";
					SendMail mail1 = new SendMail(subject1, content1, prop1.getProperty("MAIL_ADMIN"),prop1.getProperty("MAIL_ADMIN"));
					mail1.start();
					response.getWriter().write("Your FeedBack has been submitted");
				}
		}else if (details != null && sId != null) {
			AdminSessionDAO setModeDetail = new AdminSessionDAO();
			Boolean isDetailsCommit = setModeDetail.SetModeDetails(sId , details);
			if(isDetailsCommit){
				int[] ids = new int[3];
				//GETTING THE USERID, ADVISORID, AND REQUEST ID
				UserNotificationDAO id = new UserNotificationDAO();
				ids = id.GetAdvisorId(sId);
				//Notify advisor
				String comment = "Mode of communication details for your session have been updated ";
				String href = "AdvisorUpcomingSessionViewDetails?rId="+ids[2];
				AdvisorNotificationDAO notify = new AdvisorNotificationDAO();
				notify.InsertRequestNotification(comment, String.valueOf(ids[1]) , href);
				
				String userComment = "Mode of communication details for your session have been updated";
				String userHref = "UserUpcomingSessionViewDetails?rId="+ids[2];
				UserNotificationDAO user = new UserNotificationDAO();
				user.InsertNotification(userComment, userHref, String.valueOf(ids[0]));
				
				response.getWriter().write("The Mode details has been submitted");
			}
		}
		
		logger.info("Entered doPost method of ApproveFeedbackController");
	}

}
