 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 10/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminRequestDAO;
import org.AC.DAO.AdvisorMyAccountSessionDAO;
import org.AC.Util.GetTimeLeftForReply;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.TimeDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch all the request sent to or by the advisor to show on the adminrequests.jsp
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMyAccountRequestController
 */
@WebServlet("/AdminMyAccountRequestController")
public class AdminMyAccountRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountRequestController.class); 
       
 	/**************************************COMMENTS***************************************************
	 * This method will fetch all the request sent to the advisor to show on the adminrequests.jsp
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
		logger.info("Entered doGet method of AdminMyAccountRequestController");
		String pending = request.getParameter("pending");
		String approved = request.getParameter("approved");
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
			//Retrieving the requests details.
			List<UserRequestDTO> requestList = new ArrayList<UserRequestDTO>();
			List<UserRequestDTO> requestLists = new ArrayList<UserRequestDTO>();
			List<AdvisorProfileDTO> advisorDetails = new ArrayList<AdvisorProfileDTO>();
			AdminRequestDAO requests = new AdminRequestDAO();
			requestList = requests.GetRequests();
			//Retrieving the AdvisorId's and UserId's
			List<Integer> userIds = new ArrayList<Integer>();
			List<Integer> advisorIds = new ArrayList<Integer>();
			List<Integer> requestIdsWithNewDates = new ArrayList<Integer>();
				for (UserRequestDTO userRequestDTO : requestList) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
					userRequestDTO.setBookingTimeString(dateFormat.format(userRequestDTO.getBookingTime()));
					if(userRequestDTO.getQuery().length() > 120){
						userRequestDTO.setQuery(userRequestDTO.getQuery().substring(0, 120));
					}
					String status = userRequestDTO.getStatus();
					Timestamp bookingDate;
					List<TimeDTO> difference = new ArrayList<TimeDTO>();
					if(pending != null && ("true").equals(pending)){
						if(("PENDING FOR ADMIN APPROVAL").equals(status)){
							userIds.add(userRequestDTO.getUserId());
							advisorIds.add(userRequestDTO.getAdvisorId());
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
							requestLists.add(userRequestDTO);
						}
						
					}else if (approved != null && ("true").equals(approved)) {
						if(("PENDING FOR ADVISOR APPROVAL").equals(status)){
							userIds.add(userRequestDTO.getUserId());
							advisorIds.add(userRequestDTO.getAdvisorId());
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
							requestLists.add(userRequestDTO);
						}else if (!("PENDING FOR ADMIN APPROVAL").equals(status)) {
							userIds.add(userRequestDTO.getUserId());
							advisorIds.add(userRequestDTO.getAdvisorId());
							requestLists.add(userRequestDTO);
						}
					}
				}
				//Retrieving the user details from the retrieved userId's
				List<UserDetailsDTO> userDetails = new ArrayList<UserDetailsDTO>();
				AdminRequestDAO userDetail = new AdminRequestDAO();
				userDetails = userDetail.getUserDetails(userIds);
				if(advisorIds.size() >0){
					//Retrieving the advisor details from the retrieved advisorid's
					AdminRequestDAO advisorDetail = new AdminRequestDAO();
					advisorDetails= advisorDetail.getAdvisorDetailsUsingAdvisorId(advisorIds);
				}
				
					
				request.setAttribute("requestDetails", requestLists);
				request.setAttribute("userDetails", userDetails);
				request.setAttribute("advisorDetails", advisorDetails);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_Requests.jsp");
		        rd.forward(request, response);
		}
		logger.info("Exit doGet method of AdminMyAccountRequestController");
	}

}
