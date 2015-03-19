package org.AC.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ForgotPasswordDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class ForgotPasswordRedirectController
 * This servlet  retrieves the Advisor Id from the URL and get the Timestamp of the Id from the forgotpassword_admin table and
 * then compares  the  current date and retrieved timestamp + 1day.
 */
@WebServlet("/ForgotPasswordURLController")
public class ForgotPasswordURLController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ForgotPasswordURLController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of ForgotPasswordURLController");
		String aId = request.getParameter("cgid");
		try{
			if(!aId.isEmpty() && !("").equals(aId)){
				ForgotPasswordDAO dao = new ForgotPasswordDAO();
				Timestamp time = dao.getAdvisorTimestamp(aId);
				//Adding 1 Day to the timestamp retrived from the database
				Calendar c = Calendar.getInstance(); 
				c.setTime(new Date(time.getTime())); 
				c.add(Calendar.DATE, 1);
				Date date = c.getTime();
				Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone("IST"));  
		         mbCal.setTimeInMillis(new Date().getTime());      
		         Calendar cal = Calendar.getInstance();  
		         cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));  
		         cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));  
		         cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));  
		         cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));  
		         cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));  
		         cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));  
		         cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
		         Date date1 = cal.getTime();
				int comparision = date1.compareTo(date); 
				if(comparision <= 0){
					response.sendRedirect("NewPassword?aId="+aId);
				}
				else{
					response.sendRedirect("DeactivatedLink");
				}
			}else{
				
			}
		}catch(Exception e){
			logger.error("doGet method of ForgotPasswordURLController threw error:"+e.getMessage());
		}
		logger.info("Exit doGet method of ForgotPasswordURLController");
	}
}
