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
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ChangeRequestStatusDAO;
import org.AC.DAO.MyAccountRequestDAO;
import org.AC.DAO.UserDetailsDAO;
import org.AC.Util.GetTimeLeftForReply;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.TimeDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/*********************************CLASS SUMMARY****************************************************
* 
* This class will fetch all the request details sent to or by the advisor to show on the user_requests.jsp
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class UserMyAccountRequestController
 */
@WebServlet("/UserMyAccountRequestController")
public class UserMyAccountRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserMyAccountRequestController.class);     
   
 	/**************************************COMMENTS***************************************************
 	 * This class will fetch all the request details sent to or by the advisor to show on the user_requests.jsp
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
		logger.info("Entered doGet method of UserMyAccountRequestController");
		int advisorId = 0;
		int userId = 0;
		String username = "";
		String advisorName = "";
		Boolean error = false;
		try{
		userId = (int) request.getSession().getAttribute("userId");
	    username = (String) request.getSession().getAttribute("username");
		}catch(Exception e){
			response.sendRedirect("Error");
			error = true;
		}
		List<UserDetailsDTO> userDetailsList = new ArrayList<UserDetailsDTO>();
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		List<Integer> advisorIds = new ArrayList<Integer>();
		List<AdvisorProfileDTO> list3 = new ArrayList<AdvisorProfileDTO>();
		Timestamp bookingDate = null;
		int requestId = 0;
		List<UserRequestDTO> list1 = new ArrayList<UserRequestDTO>();
		List<TimeDTO> difference = new ArrayList<TimeDTO>();

		if(username != null &&  userId != 0 && !("").equals(username)){
			
			//Getting details of the REQUEST from the 'userrequest'to display on the Request Tab of the Myaccount page.
			MyAccountRequestDAO dao = new MyAccountRequestDAO();
			list = dao.getRequestDetails(userId);
			for (UserRequestDTO userRequestDTO : list) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
				userRequestDTO.setBookingTimeString(dateFormat.format(userRequestDTO.getBookingTime()));	
				String status = userRequestDTO.getStatus();
				if(("PENDING FOR ADVISOR APPROVAL").equals(status) ||("PENDING FOR ADMIN APPROVAL").equals(status)){
					advisorIds.add(userRequestDTO.getAdvisorId()) ;
					bookingDate = userRequestDTO.getBookingTime();
					GetTimeLeftForReply time = new GetTimeLeftForReply();
					difference = time.getHoursAndMinutes(bookingDate);
					if(("PENDING FOR ADMIN APPROVAL").equals(status)){
						userRequestDTO.setStatus("PENDING FOR ADVISOR APPROVAL");
					}
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
					advisorIds.add(userRequestDTO.getAdvisorId()) ;
					userRequestDTO.setDays(0);
					userRequestDTO.setHours(0);
					userRequestDTO.setMinutes(0);
					userRequestDTO.setStatus("PENDING FOR USER PAYMENT");
					list1.add(userRequestDTO);
				}else if (("REQUEST ACCEPTED BY ADVISOR WITH NEW DATES").equals(status)) {
					advisorIds.add(userRequestDTO.getAdvisorId()) ;
					userRequestDTO.setDays(0);
					userRequestDTO.setHours(0);
					userRequestDTO.setMinutes(0);
					userRequestDTO.setStatus("PENDING FOR USER PAYMENT WITH NEW DATES");
					list1.add(userRequestDTO);
				}				
			}			
			}
			//Fetching Advisor Name from the advisordetails table
			if(advisorIds.size() > 0){
				MyAccountRequestDAO name = new MyAccountRequestDAO();
				list3 = name.getAdvisorDetails(advisorIds);
			}	
			if(!error){
				request.setAttribute("list1", list1);
				request.setAttribute("advisorDetailsList", list3);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserRequests.jsp");
		        rd.forward(request, response);
			}
		
		logger.info("Exit doGet method of UserMyAccountRequestController");
	}
}
