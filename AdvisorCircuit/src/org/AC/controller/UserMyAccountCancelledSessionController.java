 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SessionDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class will fetch all session details from the session table where 
* 			 status1 = "SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY";
*			 status2 = "SESSION CANCELLED DUE TO ADVISOR NO SHOW";
*			 status3 = "SESSION CANCELLED DUE TO USER UNAVAILABILITY";
*			 status4 = "SESSION CANCELLED DUE TO NO USER PAYMENT";
*			 status5 = "SESSION REJECTED BY USER";
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class UserMyAccountCancelledSessionController
 */
@WebServlet("/UserMyAccountCancelledSessionController")
public class UserMyAccountCancelledSessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserMyAccountCancelledSessionController.class);     

 	/**************************************COMMENTS***************************************************
	 * This method will  fetch all the session details  where status1 = "SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY";
	 *			 status2 = "SESSION CANCELLED DUE TO ADVISOR NO SHOW";
	 *			 status3 = "SESSION CANCELLED DUE TO USER UNAVAILABILITY";
	 *			 status4 = "SESSION CANCELLED DUE TO NO USER PAYMENT";
	 *			 status5 = "SESSION REJECTED BY USER";
	 *   and then fetch the user details
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
		logger.info("Entered doGet method of UserMyAccountCancelledSessionController");
		int advisorId = 0;
		int userId = 0;
		String username = "";
		String advisorName = "";
		try{
		userId = (int) request.getSession().getAttribute("userId");
	    username = (String) request.getSession().getAttribute("username");
		}catch(Exception e){
			response.sendRedirect("Error");
		}
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		List<Integer> requestIds = new ArrayList<Integer>();
		List<Integer> advisorIds = new ArrayList<Integer>();
		List<UserRequestDTO> list1 = new ArrayList<UserRequestDTO>();
		List<UserRequestDTO> list2 = new ArrayList<UserRequestDTO>();
		List<UserRequestDTO> list3 = new ArrayList<UserRequestDTO>();
		List<AdvisorProfileDTO> advisorDetails = new ArrayList<AdvisorProfileDTO>();

		if(username != null &&  userId != 0 && !("").equals(username)){
			
			//Getting the session details where uId= uId 
			ArrayList<String> status = new ArrayList<String>();
			status.add("SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY");
			status.add("SESSION CANCELLED DUE TO ADVISOR NO SHOW");
			status.add("SESSION CANCELLED DUE TO USER UNAVAILABILITY");
			status.add("SESSION CANCELLED DUE TO NO USER PAYMENT");
			status.add("SESSION REJECTED BY USER");
			status.add("SESSION CANCELLED DUE TO USER NO SHOW");
			
			AdvisorMyAccountSessionDAO session = new AdvisorMyAccountSessionDAO();
			list = session.getSessionDetailsUsingUserId(userId, status);
			for (SessionDTO sessionDTO : list) {
				requestIds.add(sessionDTO.getRequestId());
			}
			//Getting the request details from request table where status = "REQUEST REJECTED BY ADVISOR"
			ArrayList<String> requestStatus = new ArrayList<String>();
			requestStatus.add("REQUEST REJECTED BY ADVISOR");
			requestStatus.add("REQUEST REJECTED BY USER");
			requestStatus.add("REQUEST REJECTED BY ADMIN");
			UserDetailsDAO requestDetails = new UserDetailsDAO();
			list1 = requestDetails.getUserRequestDetailsUsingUserId(requestStatus,userId);
			for (UserRequestDTO userRequestDTO : list1) {
				int counter = 0;
				for (SessionDTO sessionDTO : list) {
					if(sessionDTO.getStatus().equals("SESSION REJECTED BY USER") && (sessionDTO.getRequestId() == userRequestDTO.getRequestId())){
						counter = counter +1;
					}
				}
				if(counter == 0){
					list3.add(userRequestDTO);
					advisorIds.add(userRequestDTO.getAdvisorId());
				}
			}
			if(requestIds.size() > 0 ){
				MyAccountRequestDAO dao = new MyAccountRequestDAO();
				list2 = dao.getRequestDetails(requestIds);	
				for (UserRequestDTO userRequestDTO : list2) {
					advisorIds.add(userRequestDTO.getAdvisorId());
				}
				if(list2.size() > 0){
					list3.addAll(list2);
				}
			}
			if(advisorIds.size() > 0){
				//Fetching advisor details from the advisordetails table
				AdvisorMyAccountSessionDAO profile = new AdvisorMyAccountSessionDAO();
				advisorDetails  = profile.getAdvisorDetailsUsingAdvisorId(advisorIds);
			}
				request.setAttribute("requests", list3);
				request.setAttribute("advisorDetails", advisorDetails);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserCancelledSessions.jsp");
		        rd.forward(request, response);
			
		}
		logger.info("Entered doGet method of UserMyAccountCancelledSessionController");
	}
}
