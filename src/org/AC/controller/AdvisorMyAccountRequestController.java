 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ChangeRequestStatusDAO;
import org.AC.DAO.MyAccountRequestDAO;
import org.AC.DAO.UserDetailsDAO;
import org.AC.Util.GetTimeLeftForReply;
import org.AC.dto.TimeDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch all the request sent to or by the advisor to show on the requests.jsp
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdvisorMyAccountRequestController
 */
@WebServlet("/AdvisorMyAccountRequestController")
public class AdvisorMyAccountRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountRequestController.class);     
	
	
 	/**************************************COMMENTS***************************************************
	 * This class will fetch all the request sent to or by the advisor to show on the requests.jsp
	 *   
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BasicConfigurator.configure();
		logger.info("Entered doGet method of AdvisorMyAccountRequestController");
		int advisorId = 0;
		String username = "";
		//int[] id = new int[50];;
		//int[] userIds = new int[];
		List<Integer> userIds = new ArrayList<Integer>();
		//List<Integer> requestId = new ArrayList<Integer>();
		int i= 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("advisorId");
	    username = (String) request.getSession().getAttribute("username");
		}catch(Exception e){
			response.sendRedirect("Error.jsp");
		}
		List<UserDetailsDTO> userDetailsList = new ArrayList<UserDetailsDTO>();
		//List<UserRequestDTO> userRequestDetails = new ArrayList<UserRequestDTO>();
		int userId = 0;
		//int requestId = 0;
		Timestamp bookingDate = null;
		if(username != null &&  advisorId != 0 && !("").equals(username)){
		/*	//Getting request Id from the session table where STATUS = "PENDING FOR USER PAYMENT" AND "PENDING FOR USER PAYMENT WITH NEW DATES"
			MyAccountRequestDAO requestIdList = new MyAccountRequestDAO();
			requestId = requestIdList.getRequestId(advisorId);

			if(requestId != null && requestId.size() > 0) {
				//Getting request details of the advisor from the 'session table'
				MyAccountRequestDAO requestDetails = new MyAccountRequestDAO();
				userRequestDetails = requestDetails.getRequestDetails(requestId);
				for (UserRequestDTO userRequestDTO : userRequestDetails) {
					userIds[i] = userRequestDTO.getUserId();
					i++;
				}
			}
			*/
			
			//Getting request details of the advisor from the 'userrequest' where STATUS = "PENDING FOR ADVISOR APPROVAL" to display on the Request Tab of the Myaccount page.
			MyAccountRequestDAO dao = new MyAccountRequestDAO();
			List<UserRequestDTO> list = dao.getAdvisorRequestDetails(advisorId);
			List<UserRequestDTO> list1 = new ArrayList<UserRequestDTO>();
			List<TimeDTO> difference = new ArrayList<TimeDTO>();
			Boolean isStatusCommit = false;
			for (UserRequestDTO userRequestDTO : list) {
				String status = userRequestDTO.getStatus();
				if(("PENDING FOR ADVISOR APPROVAL").equals(status)){
					userIds.add(userRequestDTO.getUserId()) ;
					bookingDate = userRequestDTO.getBookingTime();
					GetTimeLeftForReply time = new GetTimeLeftForReply();
					difference = time.getHoursAndMinutes(bookingDate);
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
					/*	//If advisor has lapsed the the time to reply, then change the status of the request.
						String status1 = "SESSION LAPSE BY ADVISOR";
						ChangeRequestStatusDAO change = new ChangeRequestStatusDAO();
						isStatusCommit = change.setStatus(status1,requestId);
						userRequestDTO.setStatus(status1); */
					}
					list1.add(userRequestDTO);
				}else if (("REQUEST ACCEPTED BY ADVISOR").equals(status)) {
					userIds.add(userRequestDTO.getUserId()) ;
					userRequestDTO.setDays(0);
					userRequestDTO.setHours(0);
					userRequestDTO.setMinutes(0);
					userRequestDTO.setStatus("PENDING FOR USER PAYMENT");
					list1.add(userRequestDTO);
				}else if (("REQUEST ACCEPTED BY ADVISOR WITH NEW DATES").equals(status)) {
					userIds.add(userRequestDTO.getUserId()) ;
					i++;
					userRequestDTO.setDays(0);
					userRequestDTO.setHours(0);
					userRequestDTO.setMinutes(0);
					userRequestDTO.setStatus("PENDING FOR USER PAYMENT WITH NEW DATES");
					list1.add(userRequestDTO);
				}				
			}
			//Fetching the name of the user with id in int[] userIds.
			if(userIds.size() > 0 ){
				//Fetching user details from the uderdetails table
				UserDetailsDAO user1 = new UserDetailsDAO();
				userDetailsList = user1.getUserDetails(userIds);
			}
			request.setAttribute("list1", list1);
			//request.setAttribute("userRequestDetails", userRequestDetails);
			request.setAttribute("userDetailsList", userDetailsList);
			request.setAttribute("type", "advisor");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/requests.jsp");
	        rd.forward(request, response);
		}
		
		logger.info("Exit doGet method of AdvisorMyAccountRequestController");
	}
}
