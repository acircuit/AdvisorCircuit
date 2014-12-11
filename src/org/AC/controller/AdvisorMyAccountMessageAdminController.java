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

import org.AC.DAO.MessageDAO;
import org.AC.dto.MessageDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorMyAccountMessageAdminController
 */
@WebServlet("/AdvisorMyAccountMessageAdminController")
public class AdvisorMyAccountMessageAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountMessageAdminController.class);        
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doGet method of AdvisorMyAccountMessageAdminController");
		int advisorId = 0;
		try{
		    advisorId = (int) request.getSession().getAttribute("advisorId"); 		    
			}catch(Exception e){
				response.sendRedirect("Error.jsp");
			}
		if(advisorId != 0) {
			List<MessageDTO> message = new ArrayList<MessageDTO>();
			//Get advisor messages and response from advisormessageadmin table
			MessageDAO dao = new MessageDAO();
			message = dao.getAdvisorMessages(advisorId);
			if(message.size() > 0)
			for (MessageDTO messageDTO : message) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'HH:mm a");
				messageDTO.setAdvisorMessageTimeString(dateFormat.format(messageDTO.getAdvisorMessageTime()));
				if(messageDTO.getAdminMessageTime() != null){
					messageDTO.setAdminMessageTimeString(dateFormat.format(messageDTO.getAdminMessageTime()));
				}
			}
			request.setAttribute("message", message);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Message_Admin.jsp");
		     rd.forward(request, response);			
		}
		
		logger.info("Exit doGet method of AdvisorMyAccountMessageAdminController");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorMyAccountMessageAdminController");
		doGet(request, response);
		logger.info("Exit doPost method of AdvisorMyAccountMessageAdminController");

	}
}
