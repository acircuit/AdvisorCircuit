 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/

package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import org.AC.Util.SendMail;
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
		logger.info("Entered doPost method of BookASessionServlet");
		int userId = 0;
		String userName = "";
		Properties prop = new Properties();
	    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop.load(resourceAsStream);
		try{
			userId = (Integer)request.getSession().getAttribute("userId");
			userName = (String)request.getSession().getAttribute("username");
		}catch(Exception e){
			response.sendRedirect("Error");
		}
		try{
		//Retrieving the form values
		String service = request.getParameter("services_dropdown");
		String mode = request.getParameter("mode");
		String duration = request.getParameter("duration");
		String datetime1 = request.getParameter("datetimepicker1");
		String datetime2 = request.getParameter("datetimepicker2");
		String datetime3 = request.getParameter("datetimepicker3");
		String datetime4 = request.getParameter("datetimepicker4");
		String datetime5 = request.getParameter("datetimepicker5");
		String datetime6 = request.getParameter("datetimepicker6");
		String query = request.getParameter("query");
		String datetimeemail = request.getParameter("datetimepickeremail");
		String price = request.getParameter("price");
		String isFree =request.getParameter("isFree");
		String registrationPrice = request.getParameter("registrationPrice");
		String discount = request.getParameter("discount");
		int requestId = 0;
		String aId = (String) request.getParameter("aId");
		String datetimepicker1 = "";
		String datetimepicker2 = "";
		String datetimepicker3 = "";
		String datetimepicker4 = "";
		String datetimepicker5 = "";
		String datetimepicker6 = "";
		if(query != null){
			query = query.replaceAll("\r\n", "");
			query = query.replaceAll( "\r", "");
			query = query.replaceAll("\n", "");
		}
		if(mode.equals("email")){
			duration = "N/A";
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date datepicker1 = dateFormat.parse(datetimeemail);
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		    datetimepicker1 = dateFormat1.format(datepicker1);
		}else{
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
			Date datepicker1 = dateFormat.parse(datetime1); 
			Date datepicker2 = dateFormat.parse(datetime2);
			Date datepicker3 = dateFormat.parse(datetime3);
			Date datepicker4 = dateFormat.parse(datetime4);
			Date datepicker5 = dateFormat.parse(datetime5);
			Date datepicker6 = dateFormat.parse(datetime6);
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
			datetimepicker1 = dateFormat1.format(datepicker1);
			datetimepicker2 = dateFormat1.format(datepicker2);
			datetimepicker3 = dateFormat1.format(datepicker3);
			datetimepicker4 = dateFormat1.format(datepicker4);
			datetimepicker5 = dateFormat1.format(datepicker5);
			datetimepicker6 = dateFormat1.format(datepicker6);
		}
		String absoluteURL = "";
		Boolean isCvCommit = false;
		//Instantiate Book a session dao class for setting the value in the userrequest table.
		BookASessionDAO dao = new BookASessionDAO();
		requestId = dao.setBookASessionDetails(aId, service,mode,duration,datetimepicker1,datetimepicker2,datetimepicker3,datetimepicker4,datetimepicker5,datetimepicker6,query,userId,price,isFree,registrationPrice,discount);
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
		}
		if(isFree.equals("true")){
			BookASessionDAO free = new BookASessionDAO();
			free.DecrementIsFree(aId,service);
		}
		if(requestId != 0){	
			//Send Mail to Admin
			String subject = "A new session request!";
			String content = "Hi, <br><br>A new SESSION REQUEST by the user ! Following are the details :<br>User Name : " +userName+"<br>Query: "+query+"<br>Mode : "+mode+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
			SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
			mail.start();
			response.sendRedirect("UserRequests?bookasession=true");
		}
		logger.info("Exit doPost method of BookASessionServlet");
		}catch(Exception e){
			logger.equals("doPost method of BookASessionServlet threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}
}
