package org.AC.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Message;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.DAO.MessageDAO;
import org.AC.DAO.MyAccountRequestDAO;
import org.AC.Util.NewMessageByAdminMail;
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorMyAccountMessageAdminFormController
 */
@WebServlet("/AdvisorMyAccountMessageAdminFormController")
public class AdvisorMyAccountMessageAdminFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountMessageAdminFormController.class);        
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorMyAccountMessageAdminFormController");
		int advisorId = 0;
		String advisorMessage = "";
		try{
		    advisorId = (int) request.getSession().getAttribute("advisorId"); 		    
			}catch(Exception e){
				response.sendRedirect("Error.jsp");
			}
		 advisorMessage = request.getParameter("advisormessage");
		 if(advisorId != 0 && advisorMessage != null && !("").equals(advisorMessage)){
			 
			 //Put the message in the advisormessageadmin table
			 MessageDAO dao = new MessageDAO();
			 Timestamp time = dao.setAdvisorMessage(advisorMessage, advisorId);
			 if(time != null){
				 String advisorName = "";
				 String advisorEmail = "";
				 List<AdvisorProfileDTO> name = new ArrayList<AdvisorProfileDTO>();
				 AdvisorProfileDetailsDAO detail =new AdvisorProfileDetailsDAO();
				 name = detail.getAdvisorProfileDetails(Integer.toString(advisorId));
				 for (AdvisorProfileDTO advisorProfileDTO : name) {
					 advisorName = advisorProfileDTO.getName();
					 advisorEmail = advisorProfileDTO.getEmail();
				}
				 if(advisorId != 0  && !("").equals(advisorEmail) && !("").equals(advisorName)){
					 NewMessageByAdminMail mail = new NewMessageByAdminMail();
					 mail.SendMail(advisorName, advisorEmail,advisorId,advisorMessage);
				 }
				 RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdvisorMyAccountMessageAdminController");
			     rd.forward(request, response);
			 }
		 }
	
			logger.info("Exit doPost method of AdvisorMyAccountMessageAdminFormController");
	}

}
