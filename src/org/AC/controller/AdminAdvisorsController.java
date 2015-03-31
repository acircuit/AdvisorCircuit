 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 30/12/2014
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

import org.AC.DAO.AdminAdvisorDAO;
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class will get the details of all the advisors.
* 
*
***************************************************************************************************/

/**
 * Servlet implementation class AdminAdvisorsController
 */
@WebServlet("/AdminAdvisorsController")
public class AdminAdvisorsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminAdvisorsController.class);


 	/**************************************COMMENTS***************************************************
	 * This class will get the details of all the advisors.
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
		logger.info("Entered doPost method of AdminAdvisorsController");
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
		List<AdvisorProfileDTO> advisors = new ArrayList<AdvisorProfileDTO>();
		//Get all the details of the advisor
		AdminAdvisorDAO advisor = new AdminAdvisorDAO();
		advisors = advisor.GetAdvisors();
		if( isError!= null &&  !isError){
			request.setAttribute("advisors", advisors);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminAdvisor.jsp");
	        rd.forward(request, response);
		}
		logger.info("Exit doGet method of AdminAdvisorsController");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdminAdvisorsController");
		
		String operation = request.getParameter("operation");
				
		if(operation.equalsIgnoreCase("updatePriorityLevel")){
			int updatedPriorityLevel = Integer.parseInt(request.getParameter("updatedPriorityLevel"));
			int advisorId = Integer.parseInt(request.getParameter("advisorId"));

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

			AdminAdvisorDAO advisor = new AdminAdvisorDAO();
			advisor.updatePriorityLevel(advisorId, updatedPriorityLevel);			
		}else if(operation.equalsIgnoreCase("toggleAdvisorVisibilty")){
			int advisorId = Integer.parseInt(request.getParameter("advisorId"));

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

			AdminAdvisorDAO advisor = new AdminAdvisorDAO();
			advisor.toggleAdvisorVisibilty(advisorId);			
		}else if(operation.equalsIgnoreCase("updateFreeSessions")){
			
			int advisorId = Integer.parseInt(request.getParameter("advisorId"));
			int noOfFreeSessions = Integer.parseInt(request.getParameter("noOfFreeSessions"));
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
			
			AdminAdvisorDAO advisor = new AdminAdvisorDAO();
			advisor.UpdateFreeSessions(advisorId,noOfFreeSessions);
		}else if(operation.equalsIgnoreCase("updateDiscount")){
			
			int advisorId = Integer.parseInt(request.getParameter("advisorId"));
			int discount = Integer.parseInt(request.getParameter("updateDiscount"));
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
			
			AdminAdvisorDAO advisor = new AdminAdvisorDAO();
			advisor.UpdateDiscount(advisorId,discount);
		}else if(operation.equalsIgnoreCase("discountToAll")){
			int discount = Integer.parseInt(request.getParameter("discount"));
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
			
			AdminAdvisorDAO advisor = new AdminAdvisorDAO();
			advisor.UpdateDiscountToAll(discount);
		}
		
		logger.info("Exit doPost method of AdminAdvisorsController");
	}

}
