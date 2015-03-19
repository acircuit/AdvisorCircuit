package org.AC.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminAdvisorDAO;
import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.DAO.MessageDAO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.MessageDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminMyAccountAdvisorMessageController
 */
@WebServlet("/AdminMyAccountAdvisorMessageController")
public class AdminMyAccountAdvisorMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountAdvisorMessageController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdminMyAccountAdvisorMessageController");
		String email = request.getParameter("email");
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
			if(email == null){
				 email = (String) request.getAttribute("email");
			}
			if(email != null){
				AdminAdvisorDAO dao = new AdminAdvisorDAO();
				int advisorId = dao.GetAdvisorId(email);
				String advisorName = "";
				 List<AdvisorProfileDTO> name = new ArrayList<AdvisorProfileDTO>();
				 AdvisorProfileDetailsDAO detail =new AdvisorProfileDetailsDAO();
					List<MessageDTO> message = new ArrayList<MessageDTO>();
	
				if(advisorId != 0){
	
					 name = detail.getAdvisorProfileDetails(Integer.toString(advisorId));
					 for (AdvisorProfileDTO advisorProfileDTO : name) {
						 advisorName = advisorProfileDTO.getName();
					}
					//Get advisor messages and response from advisormessageadmin table
					MessageDAO messages = new MessageDAO();
					message = messages.getAdvisorMessages(advisorId);
					if(message.size() > 0){
						for (MessageDTO messageDTO : message) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
							messageDTO.setAdvisorMessageTimeString(dateFormat.format(messageDTO.getAdvisorMessageTime()));
							if(messageDTO.getAdminMessageTime() != null){
								messageDTO.setAdminMessageTimeString(dateFormat.format(messageDTO.getAdminMessageTime()));
							}
					}	
					
					}
				}
				request.setAttribute("email", email);	
				request.setAttribute("message", message);
				request.setAttribute("name", advisorName);
				request.setAttribute("aId", Integer.toString(advisorId));
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_Messages.jsp");
		        rd.forward(request, response);
			}
		}
		logger.info("Entered doGet method of AdminMyAccountAdvisorMessageController");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdminMyAccountAdvisorMessageController");
		String email = request.getParameter("email");
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
			if(email == null){
				 email = (String) request.getAttribute("email");
			}
			if(email != null){
				AdminAdvisorDAO dao = new AdminAdvisorDAO();
				int advisorId = dao.GetAdvisorId(email);
				String advisorName = "";
				 List<AdvisorProfileDTO> name = new ArrayList<AdvisorProfileDTO>();
				 AdvisorProfileDetailsDAO detail =new AdvisorProfileDetailsDAO();
					List<MessageDTO> message = new ArrayList<MessageDTO>();
	
				if(advisorId != 0){
	
					 name = detail.getAdvisorProfileDetails(Integer.toString(advisorId));
					 for (AdvisorProfileDTO advisorProfileDTO : name) {
						 advisorName = advisorProfileDTO.getName();
					}
					//Get advisor messages and response from advisormessageadmin table
					MessageDAO messages = new MessageDAO();
					message = messages.getAdvisorMessages(advisorId);
					if(message.size() > 0){
						for (MessageDTO messageDTO : message) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
							messageDTO.setAdvisorMessageTimeString(dateFormat.format(messageDTO.getAdvisorMessageTime()));
							if(messageDTO.getAdminMessageTime() != null){
								messageDTO.setAdminMessageTimeString(dateFormat.format(messageDTO.getAdminMessageTime()));
							}
					}	
					
					}
				}
				request.setAttribute("email", email);	
				request.setAttribute("message", message);
				request.setAttribute("name", advisorName);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_Messages.jsp");
		        rd.forward(request, response);
			}
		}
		logger.info("Entered doPost method of AdminMyAccountAdvisorMessageController");
		
		
	}


}
