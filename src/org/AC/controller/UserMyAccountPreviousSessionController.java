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
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SessionDTO;
import org.AC.dto.TimeDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserMyAccountPreviousSessionController
 */
@WebServlet("/UserMyAccountPreviousSessionController")
public class UserMyAccountPreviousSessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserMyAccountPreviousSessionController.class);     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of UserMyAccountPreviousSessionController");
		int advisorId = 0;
		String username = "";
		int userId = 0;
		int requestId = 0;
		String userName= "";
		Timestamp acceptedDate = null;
		List<Integer> requestIds = new ArrayList<Integer>();
		List<Integer> advisorIds = new ArrayList<Integer>();
		try{
		userId = (int) request.getSession().getAttribute("userId"); 
	    username = (String) request.getSession().getAttribute("username");
		}catch(Exception e){
			response.sendRedirect("Error");
		}
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		if(username != null &&  userId != 0 && !("").equals(username)){
			List<AdvisorProfileDTO> list2 = new ArrayList<AdvisorProfileDTO>();
			List<UserRequestDTO> list1 = new ArrayList<UserRequestDTO>();
			List<SessionDTO>   list11 = new ArrayList<SessionDTO>();

			//Getting the session details where uId= userId and status = "Waiting for session"
			String status = "SESSION COMPLETE";
			AdvisorMyAccountSessionDAO session = new AdvisorMyAccountSessionDAO();
			list11 = session.getSessionDetailsUsingUserId(userId, status);
			for (SessionDTO sessionDTO : list11) {
				advisorIds.add(sessionDTO.getAdvisorId());
				//userId = sessionDTO.getUserId();
				requestIds.add(sessionDTO.getRequestId());
			}
			
			///Fetching Advisor Name from the advisordetails table
			if(advisorIds.size() > 0){
				MyAccountRequestDAO name = new MyAccountRequestDAO();
				list2 = name.getAdvisorDetails(advisorIds);
			}	
			//Fetching request details
			if(requestIds.size() > 0){
				MyAccountRequestDAO dao = new MyAccountRequestDAO();
				list1 = dao.getRequestDetails(requestIds);	
			}
			for (UserRequestDTO requests : list1) {
				for (SessionDTO sessionDTO : list11) {
					if(requests.getRequestId() == sessionDTO.getRequestId()){
						sessionDTO.setAcceptedDateString(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(sessionDTO.getAcceptedDate().getTime())));
						requests.setAcceptedDate(sessionDTO.getAcceptedDateString());
					}
				}
			}
			
			if(list11.size() > 0  && list11.size() > 0 && list2.size() > 0) {
				request.setAttribute("requests", list1);
				request.setAttribute("advisordetails", list2);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserPreviousSession.jsp");
		        rd.forward(request, response);
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/UserPreviousSession.jsp");
		        rd.forward(request, response);
			}
		}
		logger.info("Exit doGet method of UserMyAccountPreviousSessionController");

	}
}