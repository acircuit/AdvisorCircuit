package org.AC.controller;

import java.io.IOException;
import com.oreilly.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.SetFormImage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.jpa.boot.internal.SettingsImpl;

/**
 * Servlet implementation class AdvisorRegistrationGeneralInfoControler
 */
@WebServlet("/AdvisorRegistrationGeneralInfoControler")
public class AdvisorRegistrationGeneralInfoControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationGeneralInfoControler.class); 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method will retrieve the general info from the form and put them in the required table
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorRegistrationGeneralInfoControler");
		//String  saveDirectory = "E:\\AC\\WebContent\\assets\\UserImages\\";
		//retrieving the profile details and putting them into advisorregistrationprofiledetails table
		//MultipartRequest request1 = new MultipartRequest((HttpServletRequest) request, saveDirectory);
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("aId");
		}catch(Exception e){
			response.sendRedirect("Error.jsp");
		}
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String nationality = request.getParameter("nationality");
		String phone = request.getParameter("phone");
		String industry = request.getParameter("industry");
		String organisation = request.getParameter("organisation");
		String job = request.getParameter("job");
		String experience = request.getParameter("experience");
		try{
			if( name != null && gender != null && age != null  && city != null && state != null && nationality != null && phone != null &&
					industry != null && organisation != null && job != null && experience != null && !name.isEmpty() && !gender.isEmpty() && !age.isEmpty()   && !city.isEmpty() 
					&& !nationality.isEmpty()  && !phone.isEmpty() && !state.isEmpty() && !industry.isEmpty() && !organisation.isEmpty() 
					&& !job.isEmpty() && !experience.isEmpty()){
				
				if(advisorId != 0){
					//Calling DAO to put the values into table
						AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
						Boolean isGeneralInfoCommit = dao.setGeneralInfo(name,gender,age,city,nationality,phone,industry,organisation,job,experience,advisorId,state);
						if(isGeneralInfoCommit){
							request.getSession().setAttribute("aId", advisorId);
							request.getSession().setAttribute("name", name);
							response.sendRedirect("EducationInfo.jsp");
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
