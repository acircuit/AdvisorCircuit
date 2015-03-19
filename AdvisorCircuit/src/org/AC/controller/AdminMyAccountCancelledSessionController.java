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
* This class will fetch all the sessions which were cancelled by advisor or user and also which were rejected by advisor or user
* 
*
***************************************************************************************************/

/**
 * Servlet implementation class AdminMyAccountCancelledSessionControllerx
 */
@WebServlet("/AdminMyAccountCancelledSessionController")
public class AdminMyAccountCancelledSessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountCancelledSessionController.class); 

    
	/**************************************COMMENTS***************************************************
	 * This class will fetch all the sessions which were cancelled by advisor or user and also which 
	 * were rejected by advisor or user
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
		logger.info("Entered doGet method of AdminMyAccountCancelledSessionController");
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
		if(isError!= null && !isError){

			//Get all the session details having status = "WAITING FOR SESSION"
			String status1 = "SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY";
			String status2 = "SESSION CANCELLED DUE TO ADVISOR NO SHOW";
			String status3 = "SESSION CANCELLED DUE TO USER UNAVAILABILITY";
			String status4 = "SESSION CANCELLED DUE TO NO USER PAYMENT";
			String status5 = "SESSION REJECTED BY USER";
			String status6 = "SESSION CANCELLED DUE TO USER NO SHOW";
			List<SessionDTO> sessionList = new ArrayList<SessionDTO>();
			List<UserRequestDTO> userRequest = new ArrayList<UserRequestDTO>();
			List<UserDetailsDTO> userDetailsList = new ArrayList<UserDetailsDTO>();
			List<UserRequestDTO> list1 = new ArrayList<UserRequestDTO>();
			List<UserRequestDTO> list2 = new ArrayList<UserRequestDTO>();
	
	
			AdminSessionDAO session = new AdminSessionDAO();
			sessionList = session.GetSessionDetails(status1,status2,status3,status4,status5,status6);
			for (SessionDTO sessionDTO : sessionList) {
				requestIds.add(sessionDTO.getRequestId());
				userIds.add(sessionDTO.getUserId());
				advisorIds.add(sessionDTO.getAdvisorId());
			}
			//Getting the request details from request table where status = "REQUEST REJECTED BY ADVISOR"
			String requestStatus = "REQUEST REJECTED BY ADVISOR";
			String requestStatus1 = "REQUEST REJECTED BY ADMIN";
			String userRejectstatus = "REQUEST REJECTED BY USER";
			AdminSessionDAO requestDetails = new AdminSessionDAO();
			list1 = requestDetails.getUserRequestDetails(requestStatus,requestStatus1,userRejectstatus);
			for (UserRequestDTO userRequestDTO : list1) {
				int counter = 0;
				for (SessionDTO sessionDTO : sessionList) {
					if(sessionDTO.getStatus().equals("SESSION REJECTED BY USER") && (sessionDTO.getRequestId() == userRequestDTO.getRequestId())){
						counter = counter +1;
					}
				}
				if(counter == 0){
					list2.add(userRequestDTO);
					userIds.add(userRequestDTO.getUserId());
					advisorIds.add(userRequestDTO.getAdvisorId());
				}
			}	
			
			//Getting Request Details
			if(requestIds.size() > 0){
				MyAccountRequestDAO dao = new MyAccountRequestDAO();
				userRequest = dao.getRequestDetails(requestIds);
			}
			userRequest.addAll(list2);
	
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
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminCancelledSessions.jsp");
	        rd.forward(request, response);
		}
	}
}
