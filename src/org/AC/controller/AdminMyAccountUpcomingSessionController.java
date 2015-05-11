 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 12/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminRequestDAO;
import org.AC.DAO.AdminSessionDAO;
import org.AC.DAO.MyAccountRequestDAO;
import org.AC.DAO.UserDetailsDAO;
import org.AC.Util.GetTimeLeftForReply;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SessionDTO;
import org.AC.dto.TimeDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/*********************************CLASS SUMMARY****************************************************
* 
* This class will fetch all the sessions with status "WAITIN FOR SESSION"
* 
*
***************************************************************************************************/

/**
 * Servlet implementation class AdminMyAccountUpcomingSessionController
 */
@WebServlet("/AdminMyAccountUpcomingSessionController")
public class AdminMyAccountUpcomingSessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountUpcomingSessionController.class); 

       
 	/**************************************COMMENTS***************************************************
	 * This method will fetch all the sessions with status "WAITING FOR SESSION"
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
		
		logger.info("Entered doGet method of AdminMyAccountUpcomingSessionController");
		List<Integer> requestIds = new ArrayList<Integer>();
		List<Integer> userIds = new ArrayList<Integer>();
		List<Integer> advisorIds = new ArrayList<Integer>();
		List<AdvisorProfileDTO> advisorDetails = new ArrayList<AdvisorProfileDTO>();
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

			//Get all the session details having status = "WAITING FOR SESSION"
			String status = "WAITING FOR SESSION";
			List<SessionDTO> sessionList = new ArrayList<SessionDTO>();
			List<TimeDTO> difference = new ArrayList<TimeDTO>();
			List<UserRequestDTO> userRequest = new ArrayList<UserRequestDTO>();
			List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
			List<UserDetailsDTO> userDetailsList = new ArrayList<UserDetailsDTO>();
	
	
			AdminSessionDAO session = new AdminSessionDAO();
			sessionList = session.GetSessionDetails(status);
			for (SessionDTO sessionDTO : sessionList) {
				requestIds.add(sessionDTO.getRequestId());
				userIds.add(sessionDTO.getUserId());
				advisorIds.add(sessionDTO.getAdvisorId());
			}
			//Getting Request Details
			if(requestIds.size() > 0){
				MyAccountRequestDAO dao = new MyAccountRequestDAO();
				userRequest = dao.getRequestDetails(requestIds);	
			}
			//Setting time left for the session
			for (SessionDTO sessionDTO : sessionList) {
			   for (UserRequestDTO userRequestDTO : userRequest) {
					if(sessionDTO.getRequestId() == userRequestDTO.getRequestId()){
						if(userRequestDTO.getQuery().length() > 120){
							userRequestDTO.setQuery(userRequestDTO.getQuery().substring(0, 120));
						}
						Timestamp sessionDate = sessionDTO.getAcceptedDate();
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
						list.add(userRequestDTO);
					}
				}
			}
			//Getting the User Details
			if(userIds.size() > 0){
				//Fetching user details from the userdetails table
				UserDetailsDAO user1 = new UserDetailsDAO();
				userDetailsList = user1.getUserDetails(userIds);
			}
			
			//Getting Advisor Details
			if(advisorIds.size() > 0){
				AdminRequestDAO advisorDetail = new AdminRequestDAO();
				advisorDetails= advisorDetail.getAdvisorDetailsUsingAdvisorId(advisorIds);
			}
			
			request.setAttribute("requestDetails", list);
			request.setAttribute("userDetails", userDetailsList);
			request.setAttribute("advisorDetails", advisorDetails);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminUpcomingSessions.jsp");
	        rd.forward(request, response);
		}	
		logger.info("Entered doGet method of AdminMyAccountUpcomingSessionController");
	}

	
	// for search functionality
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("Entered doPost method of AdminMyAccountUpcomingSessionController");
		List<Integer> requestIds = new ArrayList<Integer>();
		List<Integer> userIds = new ArrayList<Integer>();
		List<Integer> advisorIds = new ArrayList<Integer>();
		List<AdvisorProfileDTO> advisorDetails = new ArrayList<AdvisorProfileDTO>();
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

			//Get all the session details having status = "WAITING FOR SESSION"
			String status = "WAITING FOR SESSION";
			List<SessionDTO> sessionList = new ArrayList<SessionDTO>();
			List<TimeDTO> difference = new ArrayList<TimeDTO>();
			List<UserRequestDTO> userRequest = new ArrayList<UserRequestDTO>();
			List<UserDetailsDTO> userDetailsList = new ArrayList<UserDetailsDTO>();
	
	
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
			AdminSessionDAO session = new AdminSessionDAO();
			sessionList = session.SearchSessionDetailsByDate(status, fromDate, toDate);
			for (SessionDTO sessionDTO : sessionList) {
				requestIds.add(sessionDTO.getRequestId());
				userIds.add(sessionDTO.getUserId());
				advisorIds.add(sessionDTO.getAdvisorId());
			}
			//Getting Request Details
			if(requestIds.size() > 0){
				MyAccountRequestDAO dao = new MyAccountRequestDAO();
				userRequest = dao.getRequestDetails(requestIds);	
			}
			//Setting time left for the session
			for (UserRequestDTO userRequestDTO : userRequest) {
				for (SessionDTO sessionDTO : sessionList) {
					if(sessionDTO.getRequestId() == userRequestDTO.getRequestId()){
						Timestamp sessionDate = sessionDTO.getAcceptedDate();
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
			//Getting the User Details
			if(userIds.size() > 0){
				//Fetching user details from the userdetails table
				UserDetailsDAO user1 = new UserDetailsDAO();
				userDetailsList = user1.getUserDetails(userIds);
			}
			
			//Getting Advisor Details
			if(advisorIds.size() > 0){
				AdminRequestDAO advisorDetail = new AdminRequestDAO();
				advisorDetails= advisorDetail.getAdvisorDetailsUsingAdvisorId(advisorIds);
			}
			
			request.setAttribute("requestDetails", userRequest);
			request.setAttribute("userDetails", userDetailsList);
			request.setAttribute("advisorDetails", advisorDetails);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminUpcomingSessions.jsp");
	        rd.forward(request, response);
		}	
		logger.info("Exit from doPost method of AdminMyAccountUpcomingSessionController");		
	}
}
