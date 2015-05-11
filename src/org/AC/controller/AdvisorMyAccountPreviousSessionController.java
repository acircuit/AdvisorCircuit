 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 30/11/2014
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
import org.AC.dto.SessionDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch all session details from the session table where STATUS = "SESSION COMPLETE"
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdvisorMyAccountPreviousSessionController
 */
@WebServlet("/AdvisorMyAccountPreviousSessionController")
public class AdvisorMyAccountPreviousSessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountPreviousSessionController.class);     

 	/**************************************COMMENTS***************************************************
	 * This method will  fetch all the session details  where where STATUS = "SESSION COMPLETE"
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
		logger.info("Entered doGet method of AdvisorMyAccountPreviousSessionController");
		int advisorId = 0;
		String username = "";
		int userId = 0;
		int requestId = 0;
		String userName= "";
		Timestamp acceptedDate = null;
		List<Integer> requestIds = new ArrayList<Integer>();
		List<Integer> userIds = new ArrayList<Integer>();
		try{
	    advisorId = (int) request.getSession().getAttribute("advisorId"); 
		}catch(Exception e){
			response.sendRedirect("Error");
		}
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		if(advisorId != 0){
			List<UserDetailsDTO> list2 = new ArrayList<UserDetailsDTO>();
			List<UserRequestDTO> list1 = new ArrayList<UserRequestDTO>();

			//Getting the session details where aId= advisorId and status = "Waiting for session"
			String status = "SESSION COMPLETE";
			AdvisorMyAccountSessionDAO session = new AdvisorMyAccountSessionDAO();
			list = session.getSessionDetails(advisorId, status);
			for (SessionDTO sessionDTO : list) {
				requestIds.add(sessionDTO.getRequestId());
				userIds.add(sessionDTO.getUserId());
			}
			if(requestIds.size() > 0){
				//After retrieving the user details, get the user request details
				//String rId= Integer.toString(requestIds);
				MyAccountRequestDAO dao = new MyAccountRequestDAO();
				list1 = dao.getRequestDetails(requestIds);	
			}
			for (SessionDTO sessionDTO : list) {
				for (UserRequestDTO requests : list1) {
					if(requests.getRequestId() == sessionDTO.getRequestId()){
						if(requests.getQuery().length() > 120){
							requests.setQuery(requests.getQuery().substring(0, 120));
						}
						sessionDTO.setAcceptedDateString(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(sessionDTO.getAcceptedDate().getTime())));
						requests.setAcceptedDate(sessionDTO.getAcceptedDateString());
					}
				}
			}
			if(userIds.size() > 0){
				//Fetching user details from the uderdetails table
				UserDetailsDAO user1 = new UserDetailsDAO();
				list2 = user1.getUserDetails(userIds);
			}
			
			if(list1.size() > 0 && list2.size() > 0)  {
				request.setAttribute("requests", list1);
				request.setAttribute("userDetailsList", list2);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/PreviousSessions.jsp");
		        rd.forward(request, response);
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/PreviousSessions.jsp");
		        rd.forward(request, response);
			}
		}
		logger.info("Exit doGet method of AdvisorMyAccountPreviousSessionController");

	}

}

