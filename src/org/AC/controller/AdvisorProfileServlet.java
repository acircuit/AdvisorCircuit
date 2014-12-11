package org.AC.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorModesDAO;
import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.DAO.AdvisorServicesDAO;
import org.AC.DAO.ProfessionalBackgroundDAO;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorProfileServlet
 */
@WebServlet("/AdvisorProfileServlet")
public class AdvisorProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorProfileServlet.class);       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Enter doGet method of AdvisorProfileServlet.java");
		String advisorId = request.getParameter("aId");
		String isAdmin = request.getParameter("admin");	
		java.util.List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		List<ProfessionalBackgroundDTO> list1 = new ArrayList<ProfessionalBackgroundDTO>();
		List<AdvisorServiceDTO> list2 = new ArrayList<AdvisorServiceDTO>();
		List<AdvisorModeDTO> list3 = new ArrayList<AdvisorModeDTO>();

		
		//This is to get the profile details for the advisor profile
		AdvisorProfileDetailsDAO dao = new AdvisorProfileDetailsDAO();
		list = dao.getAdvisorProfileDetails(advisorId);
		//This is to get the professional background for the advisor profile
		ProfessionalBackgroundDAO dao1 = new ProfessionalBackgroundDAO();
		list1 = dao1.getProfessionalBackground(advisorId);
		
		//This is to get the services provided by the advisor
		AdvisorServicesDAO dao2 = new AdvisorServicesDAO();
		list2 = dao2.getAdvisorServiceDetails(advisorId);
		
		//This is to get the modes and price for a service 
		AdvisorModesDAO dao3 = new AdvisorModesDAO();
		list3 = dao3.getAdvisorModesDetails(advisorId);
		if(list.size() > 0){
			request.setAttribute("advisordetails", list);
			
		}
		if(list1.size() > 0){
			request.setAttribute("advisorprofession", list1);
		}
		if(list2.size() > 0){
			request.setAttribute("advisorservices", list2);
		}
		if(list3.size() > 0){
			request.setAttribute("advisormodes", list3);
		}
		if(!("true").equals(isAdmin)){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdvisorProfilePage.jsp");
	        rd.forward(request, response);
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdvisorProfilePage_Admin.jsp");
	        rd.forward(request, response);
		}
        logger.info("Exit doGet method of AdvisorProfileServlet.java");
		}
	
}
	

