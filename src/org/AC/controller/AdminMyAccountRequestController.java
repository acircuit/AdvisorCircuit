 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 10/12/2014enerated method stub
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

import org.AC.DAO.AdminRequestDAO;
import org.AC.DAO.AdvisorMyAccountSessionDAO;
import org.AC.dto.AdvisorProfileDTO;
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
		BasicConfigurator.configure();
		logger.info("Entered doGet method of AdminMyAccountRequestController");
		
		//Retrieving the requests details.
		List<UserRequestDTO> requestList = new ArrayList<UserRequestDTO>();
		AdminRequestDAO requests = new AdminRequestDAO();
		requestList = requests.GetRequests();
		
		//Retrieving the AdvisorId's and UserId's
		List<Integer> userIds = new ArrayList<Integer>();
		List<Integer> advisorIds = new ArrayList<Integer>();

		for (UserRequestDTO userRequestDTO : requestList) {
			userIds.add(userRequestDTO.getUserId());
			advisorIds.add(userRequestDTO.getAdvisorId());
		}
		
		//Retrieving the user details from the retrieved userId's
		List<UserDetailsDTO> userDetails = new ArrayList<UserDetailsDTO>();
		AdminRequestDAO userDetail = new AdminRequestDAO();
		userDetails = userDetail.getUserDetails(userIds);
		
		//Retrieving the advisor details from the retrieved advisorid's
		List<AdvisorProfileDTO> advisorDetails = new ArrayList<AdvisorProfileDTO>();
		AdminRequestDAO advisorDetail = new AdminRequestDAO();
		advisorDetails= advisorDetail.getAdvisorDetailsUsingAdvisorId(advisorIds);
		
		if(userDetails.size() > 0 && advisorDetails.size() > 0 && requestList.size() > 0){
			
			request.setAttribute("requestDetails", requestList);
			request.setAttribute("userDetails", userDetails);
			request.setAttribute("advisorDetails", advisorDetails);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_Requests.jsp");
	        rd.forward(request, response);
			
		}
		
		
		logger.info("Exit doGet method of AdminMyAccountRequestController");
	}

}
