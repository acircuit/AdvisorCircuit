package org.AC.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.SetFormImage;
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationGeneralInfoControler
 */
@WebServlet("/AdvisorRegistrationGeneralInfoControler")
public class AdvisorRegistrationGeneralInfoControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationGeneralInfoControler.class); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Entered doGet method of AdvisorRegistrationGeneralInfoControler");
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("aId");
		}catch(Exception e){
			response.sendRedirect("Email");
		}
		String fromTab = request.getParameter("tab");
		if(advisorId != 0){
			if (fromTab != null && fromTab.equals("true")) {
				AdvisorProfileDTO profile = new AdvisorProfileDTO();
				//Getting General Information of the advisor
				AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
				profile = dao.GetGeneralInfo(advisorId);
				if(profile.getName() != null){
					request.setAttribute("profile",profile);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/GeneralInfo");
			        rd.forward(request, response);
				}else{
					//Getting the status of the registration process.
					AdvisorRegistrationDAO status = new AdvisorRegistrationDAO();
					String stat = status.getStatus(advisorId);
					response.sendRedirect(stat);
				}
			}
		}
		logger.info("Exit doGet method of AdvisorRegistrationGeneralInfoControler");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the general info from the form and put them in the required table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorRegistrationGeneralInfoControler");
		//String  saveDirectory = "E:\\AC\\WebContent\\assets\\UserImages\\";
		//retrieving the profile details and putting them into advisorregistrationprofiledetails table
		//MultipartRequest request1 = new MultipartRequest((HttpServletRequest) request, saveDirectory);
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("aId");
		}catch(Exception e){
			response.sendRedirect("Email");
		}
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String age = request.getParameter("age");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String nationality = request.getParameter("nationality");
			String phone = request.getParameter("phone");
			String industry = request.getParameter("industry");
			String intro = request.getParameter("intro");
			String edit = request.getParameter("edit");
			try{
				if( name != null && gender != null && age != null  && city != null && state != null && nationality != null && phone != null &&
						industry != null && intro != null && !name.isEmpty() && !gender.isEmpty() && !age.isEmpty()   && !city.isEmpty() 
						&& !nationality.isEmpty()  && !phone.isEmpty() && !state.isEmpty() && !industry.isEmpty() && !intro.isEmpty() 
						){
					
					if(advisorId != 0){
						Boolean isGeneralInfoCommit = false;
						//Calling DAO to put the values into table
							AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
							if(edit != null && edit.equals("true") ){
								isGeneralInfoCommit = dao.setGeneralInfo(name,gender,age,city,nationality,phone,industry,intro,advisorId,state,edit);
							}else{
								edit = "false";
								isGeneralInfoCommit = dao.setGeneralInfo(name,gender,age,city,nationality,phone,industry,intro,advisorId,state,edit);
							}
							if(isGeneralInfoCommit){
								response.sendRedirect("AdvisorRegistrationEducationInfo");
							}
					}
				}
				logger.info("Exit doPost method of AdvisorRegistrationGeneralInfoControler");
			}catch(Exception e){
				logger.error("doPost method of AdvisorRegistrationGeneralInfoControler threw error:"+e.getMessage());
				e.printStackTrace();
			}
	}

}
