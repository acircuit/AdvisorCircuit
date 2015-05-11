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
import org.AC.dto.AdvisorServiceDTO;
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
		List<Integer> advisorIds = new ArrayList<Integer>();
		//Get all the details of the advisor
		AdminAdvisorDAO advisor = new AdminAdvisorDAO();
		advisors = advisor.GetAdvisors();
		for (AdvisorProfileDTO adv : advisors) {
			advisorIds.add(adv.getAdvisorId());
		}
		List<AdvisorServiceDTO> advisorServices = new ArrayList<AdvisorServiceDTO>();
		//Getting services details of the advisors
		AdminAdvisorDAO services = new AdminAdvisorDAO();
		advisorServices = services.GetServices(advisorIds);
		if( isError!= null &&  !isError){
			request.setAttribute("advisors", advisors);
			request.setAttribute("advisorServices", advisorServices);
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
			String noOfFreeSessionsCT = request.getParameter("noOfFreeSessionsCT");
			String noOfFreeSessionsMI = request.getParameter("noOfFreeSessionMI");
			String noOfFreeSessionsRC = request.getParameter("noOfFreeSessionsRC");
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
			List<AdvisorServiceDTO> advisors = new ArrayList<AdvisorServiceDTO>();
			if(noOfFreeSessionsCT != null){
				AdvisorServiceDTO service = new AdvisorServiceDTO();
				service.setService("careertalk");
				service.setIsFree(Integer.parseInt(noOfFreeSessionsCT));
				advisors.add(service);
			}
			if(noOfFreeSessionsMI	 != null){
				AdvisorServiceDTO service = new AdvisorServiceDTO();
				service.setService("mockinterview");
				service.setIsFree(Integer.parseInt(noOfFreeSessionsMI));
				advisors.add(service);
			}
			if(noOfFreeSessionsRC != null){
				AdvisorServiceDTO service = new AdvisorServiceDTO();
				service.setService("cvcritique");
				service.setIsFree(Integer.parseInt(noOfFreeSessionsRC));
				advisors.add(service);
			}
			AdminAdvisorDAO advisor = new AdminAdvisorDAO();
			advisor.UpdateFreeSessions(advisorId,advisors);
		}else if(operation.equalsIgnoreCase("updateDiscount")){
			
			int advisorId = Integer.parseInt(request.getParameter("advisorId"));
			String updateDiscountCT = request.getParameter("updateDiscountCT");
			String updateDiscountMI = request.getParameter("updateDiscountMI");
			String updateDiscountRC = request.getParameter("updateDiscountRC");
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
			List<AdvisorServiceDTO> advisors = new ArrayList<AdvisorServiceDTO>();
			if(updateDiscountCT != null){
				AdvisorServiceDTO service = new AdvisorServiceDTO();
				service.setService("careertalk");
				service.setDiscount(Integer.parseInt(updateDiscountCT));
				advisors.add(service);
			}
			if(updateDiscountMI	 != null){
				AdvisorServiceDTO service = new AdvisorServiceDTO();
				service.setService("mockinterview");
				service.setDiscount(Integer.parseInt(updateDiscountMI));
				advisors.add(service);
			}
			if(updateDiscountRC != null){
				AdvisorServiceDTO service = new AdvisorServiceDTO();
				service.setService("cvcritique");
				service.setDiscount(Integer.parseInt(updateDiscountRC));
				advisors.add(service);
			}
			AdminAdvisorDAO advisor = new AdminAdvisorDAO();
			advisor.UpdateDiscount(advisorId,advisors);
		}else if(operation.equalsIgnoreCase("discountToAll")){
			int discountCT = Integer.parseInt(request.getParameter("discountCT"));
			int discountMI = Integer.parseInt(request.getParameter("discountMI"));
			int discountRC = Integer.parseInt(request.getParameter("discountRC"));
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
			List<AdvisorServiceDTO> advisors = new ArrayList<AdvisorServiceDTO>();
				AdvisorServiceDTO service1 = new AdvisorServiceDTO();
				service1.setService("careertalk");
				service1.setDiscount(discountCT);
				advisors.add(service1);
			
				AdvisorServiceDTO service2 = new AdvisorServiceDTO();
				service2.setService("mockinterview");
				service2.setDiscount(discountMI);
				advisors.add(service2);

				AdvisorServiceDTO service3 = new AdvisorServiceDTO();
				service3.setService("cvcritique");
				service3.setDiscount(discountRC);
				advisors.add(service3);
			
			AdminAdvisorDAO advisor = new AdminAdvisorDAO();
			advisor.UpdateDiscountToAll(advisors);
		}else if (operation.equals("updatekeywords")) {
			String keywords = request.getParameter("keywords");
			String aId = request.getParameter("advisor");

			AdminAdvisorDAO advisor = new AdminAdvisorDAO();
			advisor.UpdateAdvisor(aId,keywords);
		}
		
		logger.info("Exit doPost method of AdminAdvisorsController");
	}

}
