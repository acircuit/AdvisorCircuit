 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.sql.Timestamp;
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
import org.AC.DAO.UserDetailsDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.Util.GetTimeLeftForReply;
import org.AC.dto.SessionDTO;
import org.AC.dto.TimeDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch all session details from the session table where STATUS = "WAITING FOR SESSION"
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdvisorMyAccountUpcomingSessionController
 */
@WebServlet("/AdvisorMyAccountUpcomingSessionController")
public class AdvisorMyAccountUpcomingSessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountUpcomingSessionController.class);     
	
 	/**************************************COMMENTS***************************************************
	 * This method will  fetch all the session details  where where STATUS = "WAITING FOR SESSION"
	 *   and then fetch the user details
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorMyAccountUpcomingSessionController");
		int advisorId = 0;
		int userId = 0;
		int requestId = 0;
		String userName= "";
		String SessionDate = "";
		String acceptedDateForClock = "";
		long date =0;
		List<Integer> requestIds = new ArrayList<Integer>();
		List<Integer> userIds = new ArrayList<Integer>();
		Timestamp acceptedDate = null;
		try{
	    advisorId = (int) request.getSession().getAttribute("advisorId"); 
		}catch(Exception e){
			response.sendRedirect("Error");
		}
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		if(advisorId != 0 ){
			List<UserDetailsDTO> userDetailsList = new ArrayList<UserDetailsDTO>();
			List<UserRequestDTO> list1 = new ArrayList<UserRequestDTO>();
			List<TimeDTO> difference = new ArrayList<TimeDTO>();

			//Getting the session details where aId= advisorId and status = "Waiting for session"
			String status = "WAITING FOR SESSION";
			AdvisorMyAccountSessionDAO session = new AdvisorMyAccountSessionDAO();
			list = session.getSessionDetails(advisorId, status);
			for (SessionDTO sessionDTO : list) {
				requestIds.add(sessionDTO.getRequestId());
				userIds.add(sessionDTO.getUserId());
			}			
				
			if(requestIds.size() > 0){
				MyAccountRequestDAO dao = new MyAccountRequestDAO();
				list1 = dao.getRequestDetails(requestIds);	
			}
			//Setting time left for the session
			for (UserRequestDTO userRequestDTO : list1) {
				for (SessionDTO sessionDTO : list) {
					if(sessionDTO.getRequestId() == userRequestDTO.getRequestId()){
						Timestamp sessionDate = sessionDTO.getAcceptedDate();
						SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM dd','yyyy' 'h:mm a");
						userRequestDTO.setAcceptedDateForClock(dateFormat.format(sessionDTO.getAcceptedDate()));
						SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
						sessionDTO.setAcceptedDateString(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(sessionDTO.getAcceptedDate().getTime())));
						userRequestDTO.setAcceptedDate(sessionDTO.getAcceptedDateString());
						SessionDate = dateFormat1.format(sessionDate);
						GetTimeLeftForReply time = new GetTimeLeftForReply();
						difference = time.getTimeLeftForSession(sessionDate);
						if(difference.size() > 0){
							for (TimeDTO timeDTO : difference) {
								userRequestDTO.setDays(timeDTO.getDay());
								userRequestDTO.setHours(timeDTO.getHours());
								userRequestDTO.setMinutes(timeDTO.getMinutes());
							}	
						}else{
							userRequestDTO.setDays(0);
							userRequestDTO.setHours(0);
							userRequestDTO.setMinutes(0);
						}
						
					}
				}
			}
			if(userIds.size() > 0){
				//Fetching user details from the uderdetails table
				UserDetailsDAO user1 = new UserDetailsDAO();
				userDetailsList = user1.getUserDetails(userIds);
			}	
				request.setAttribute("SessionDate", SessionDate);
				request.setAttribute("requests", list1);
				request.setAttribute("userDetailsList", userDetailsList);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/UpcomingSessions.jsp");
		        rd.forward(request, response);
			
		}
		logger.info("Exit doGet method of AdvisorMyAccountUpcomingSessionController");

	}

}
