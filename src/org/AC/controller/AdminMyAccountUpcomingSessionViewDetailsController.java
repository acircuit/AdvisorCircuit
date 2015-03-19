 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 12/12/2014
 * ************************************************************************************************/
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

import org.AC.DAO.AdvisorMyAccountSessionDAO;
import org.AC.DAO.MyAccountRequestDAO;
import org.AC.DAO.SessionFeedBackDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.AdvisorNewDatesDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SessionDTO;
import org.AC.dto.SessionFeedBackDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch all the session,request,user and advisor details to show on the view details page.
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMyAccountUpcomingSessionViewDetailsController
 */
@WebServlet("/AdminMyAccountUpcomingSessionViewDetailsController")
public class AdminMyAccountUpcomingSessionViewDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountUpcomingSessionViewDetailsController.class);     

       
 	/**************************************COMMENTS***************************************************
	 * This method will fetch all the session,request,user and advisor details to show on the view details page.
	 *   
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Entered doGet method of AdminMyAccountUpcomingSessionViewDetailsController");
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
		if(isError!= null &&  !isError){
			String rId = "";
			int advsorId = 0;
			int userId = 0;
			String userName = "";
			String advisorName = "";
			String userPicture = "";
			String advisorPicture = "";
			String userRelImage = "";
			String advisorRelImage = "";
			String requestStatus = "";
			String path ="";
			Boolean isFeedback =false;
			int sessionId = 0;
			SessionFeedBackDTO feed = new SessionFeedBackDTO();
			SessionFeedBackDTO mail = new SessionFeedBackDTO();
			List<UserRequestDTO> requestDetails = new ArrayList<UserRequestDTO>();
			List<UserDetailsDTO> userDetails = new ArrayList<UserDetailsDTO>();
			List<AdvisorProfileDTO> advisorDetails = new ArrayList<AdvisorProfileDTO>();
			List<SessionDTO> sessionDetail = new ArrayList<SessionDTO>();
			List<AdvisorNewDatesDTO> advisorNewDates = new ArrayList<AdvisorNewDatesDTO>();
		    rId = (String)request.getParameter("rId");
		    String isSessionPast = (String)request.getParameter("pastsession");
		    if(rId != null && !("").equals(rId)){	
				MyAccountRequestDAO dao = new MyAccountRequestDAO();
				requestDetails = dao.getUserRequestDetails(rId);
				for (UserRequestDTO userRequestDTO : requestDetails) {
					if(userRequestDTO.getService().equals("cvcritique") || userRequestDTO.getService().equals("mockinterview")){
						isFeedback = true;
					}
					advsorId = userRequestDTO.getAdvisorId();
					userId = userRequestDTO.getUserId();
					requestStatus = userRequestDTO.getStatus();
					if(userRequestDTO.getMode().equals("email")){
						userRequestDTO.setTimeString1(new SimpleDateFormat("dd-MMM-yyyy").format(new Date(userRequestDTO.getTime1().getTime())));
					}else{
						userRequestDTO.setTimeString1(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(userRequestDTO.getTime1().getTime())));
						userRequestDTO.setTimeString2(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(userRequestDTO.getTime2().getTime())));
						userRequestDTO.setTimeString3(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(userRequestDTO.getTime3().getTime())));
						userRequestDTO.setTimeString4(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(userRequestDTO.getTime4().getTime())));
						userRequestDTO.setTimeString5(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(userRequestDTO.getTime5().getTime())));
						userRequestDTO.setTimeString6(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(userRequestDTO.getTime6().getTime())));
					}
				}
				//Getting user details to show on the view details page.
				if(userId != 0){
					MyAccountRequestDAO image = new MyAccountRequestDAO();
					userDetails = image.getUserImage(userId);
					for (UserDetailsDTO userDetailsDTO : userDetails) {
						userName = userDetailsDTO.getFullName();
						userPicture = userDetailsDTO.getImage();
					}
				}
				//Getting advisor details to show on the view details page.
				if(advsorId != 0){
					MyAccountRequestDAO name = new MyAccountRequestDAO();
					advisorDetails = name.getAdvisorName(advsorId);
					for (AdvisorProfileDTO advisorProfileDTO : advisorDetails) {
						advisorName = advisorProfileDTO.getName();
						advisorPicture = advisorProfileDTO.getImage();
					}
				}
				//Getting the relative image URL for user and advisor image
				if(!("").equals(userPicture) && !("").equals(advisorPicture)){
					GetRelativeImageURL image = new GetRelativeImageURL();
					 userRelImage = image.getImageURL(userPicture);
					 advisorRelImage = image.getImageURL(advisorPicture);
	
				}
				//Getting the session details
				AdvisorMyAccountSessionDAO sessionDetails = new AdvisorMyAccountSessionDAO();
				sessionDetail = sessionDetails.getSessionDetails(rId);
				for (SessionDTO sessionDTO : sessionDetail) {
					sessionId =sessionDTO.getSessionId();
					sessionDTO.setAcceptedDateString(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(sessionDTO.getAcceptedDate().getTime())));
				}
				if(isFeedback){
					//Getting the FeedBack Form Path
					MyAccountRequestDAO form = new MyAccountRequestDAO();
					path = form.GetFeedbackPathForAdmin(sessionId);
				}
				//Get the session feedback details
				SessionFeedBackDAO feedback = new SessionFeedBackDAO();
				feed = feedback.GetFeedBack(sessionId);
				//Get mail details
				SessionFeedBackDAO mails = new SessionFeedBackDAO();
				mail = mails.GetMail(sessionId);
		    }
		    request.setAttribute("isSessionPast", isSessionPast);
			request.setAttribute("advisorImage", advisorRelImage);
			request.setAttribute("userImage", userRelImage);
			request.setAttribute("userName", userName);
			request.setAttribute("path", path);
			request.setAttribute("feed", feed);
			request.setAttribute("mail", mail);
			request.setAttribute("advisorName", advisorName);
			request.setAttribute("requestDetails", requestDetails);
			request.setAttribute("sessionDetail", sessionDetail);
	
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_Upcoming_ViewDetails.jsp");
	        rd.forward(request, response);
		}
		logger.info("Exit doGet method of AdminMyAccountUpcomingSessionViewDetailsController");

	}
}
