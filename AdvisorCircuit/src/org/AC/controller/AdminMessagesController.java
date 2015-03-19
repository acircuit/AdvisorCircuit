package org.AC.controller;
/*************************************************************************************************
* ********************************ADVISOR CIRCUIT*************************************************
* ************************************************************************************************
* @author AdvisorCircuit
* @Date 31/12/2014
* ************************************************************************************************/
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminSessionDAO;
import org.AC.Util.SendMail;
import org.AC.dto.SessionDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/*********************************CLASS SUMMARY****************************************************
* 
* This class will fetch the session details.
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMessagesController
 */
@WebServlet("/AdminMessagesController")
public class AdminMessagesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminReviewController.class); 

	/**************************************COMMENTS***************************************************
	 * This method will fetch the session details.
	 *   
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/ 
	/**

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdminReviewController");
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

			//Get All the session details to show on the AdminReview.jsp
			List<SessionDTO> sessionList = new ArrayList<SessionDTO>();
			AdminSessionDAO session = new AdminSessionDAO();
			sessionList = session.GetSessionDetails();
			request.setAttribute("sessionList", sessionList);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminMessages.jsp");
			rd.forward(request, response);
		}
		logger.info("Exit doPost method of AdminReviewController");
	}
}
