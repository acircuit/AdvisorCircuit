 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/

package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.AC.DAO.AdvisorModesDAO;
import org.AC.DAO.BookASessionDAO;
import org.AC.Util.SetCV;
import org.AC.dto.AdvisorModeDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *************************CLASS SUMMARY***********************************************************
 * 
 * This class retrieves the values from "BOOK A SESSION"Form and put them into Request Table 
 * 
 * 
 *
 ***************************************************************************************************/


@WebServlet("/BookASessionServlet")
@MultipartConfig
public class BookASessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BookASessionServlet.class);
	
	/**************************************COMMENTS***************************************************
	 * This function  retrieves the values from "BOOK A SESSION"Form and put them into Request Table .
	 *   @
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BasicConfigurator.configure();
		logger.info("Entered doPost method of BookASessionServlet");
		try{
		//Retrieving the form values
		String service = request.getParameter("services_dropdown");
		String mode = request.getParameter("mode");
		String duration = request.getParameter("duration");
		String datetimepicker1 = request.getParameter("datetimepicker1");
		String datetimepicker2 = request.getParameter("datetimepicker2");
		String datetimepicker3 = request.getParameter("datetimepicker3");
		String datetimepicker4 = request.getParameter("datetimepicker4");
		String datetimepicker5 = request.getParameter("datetimepicker5");
		String datetimepicker6 = request.getParameter("datetimepicker6");
		String query = request.getParameter("query");
		String aId = (String) request.getSession().getAttribute("advisorId");
		int userId = 0;
		int requestId = 0;
		userId = (Integer)request.getSession().getAttribute("userId");
		String absoluteURL = "";
		Boolean isCvCommit = false;
		
		//Instantiate Book a session dao class for setting the value in the userrequest table.
		BookASessionDAO dao = new BookASessionDAO();
		requestId = dao.setBookASessionDetails(aId, service,mode,duration,datetimepicker1,datetimepicker2,datetimepicker3,datetimepicker4,datetimepicker5,datetimepicker6,query,userId);
		System.out.println(request.getPart("cv"));
		//If the service was cvcritique or moack interview then the user would have uploaded the Cv.
		//So need to set the CV in the required folder and put the CV details in the user_cv table.
		if(("mockinterview").equals(service) || ("cvcritique").equals(service) ){
			
			//set the CV in the required folder and retrieving the absolute URL
			SetCV cv = new SetCV();
			absoluteURL = cv.putCV(request, response, userId);
			if(!("").equals(absoluteURL)){
				BookASessionDAO resume = new BookASessionDAO();
				isCvCommit = resume.setCV(absoluteURL, requestId, userId);
			}
			if(isCvCommit){
				response.sendRedirect("UserMyAccountRequestController?bookasession=true");
			}
			//?bookasession=true has been added to the URL so that the UserMyAccount can idendtify and show request booked message
		}else{
			response.sendRedirect("UserMyAccountRequestController?bookasession=true");
		}
		logger.info("Exit doPost method of BookASessionServlet");
		}catch(Exception e){
			logger.equals("doPost method of BookASessionServlet threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}
}
