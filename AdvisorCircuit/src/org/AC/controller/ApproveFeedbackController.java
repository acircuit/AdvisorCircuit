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

import org.AC.DAO.AdminSessionDAO;
import org.AC.DAO.AdvisorMyAccountSessionDAO;
import org.AC.DAO.ChangeRequestStatusDAO;
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
					response.getWriter().write("Your FeedBack has been submitted");
				}
		}else if (details != null && sId != null) {
			AdminSessionDAO setModeDetail = new AdminSessionDAO();
			Boolean isDetailsCommit = setModeDetail.SetModeDetails(sId , details);
			if(isDetailsCommit){
				response.getWriter().write("The Mode details has been submitted");
			}
		}
		
		logger.info("Entered doPost method of ApproveFeedbackController");
	}

}
