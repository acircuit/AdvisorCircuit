package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ForgotPasswordDAO;
import org.AC.JDBC.Util;
import org.AC.Util.PasswordGenerator;
import org.AC.Util.PasswordHashing;
import org.AC.Util.SendMail;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class ForgotPasswordController
 */
@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ForgotPasswordController.class);
	Connection conn = null;
	Statement stmt = null;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method retrieves the username from the forgot password form,retrieves the email and advisor id from the table,
	 * Then puts the values in the forgot password table and mails to the advisor
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of ForgotPasswordController");
		int advisorId = 0;
		String email = "";
		String userName = request.getParameter("email");
		Boolean isInvalidUsername = false;
		Boolean isInsertComplete =false;
		
		String register = (String)request.getParameter("register");
		Properties prop = new Properties();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
        Properties prop1 = new Properties();
	    InputStream resourceAsStream1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop1.load(resourceAsStream1);
        try {
			prop.load(resourceAsStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(register == null){
			register = "false";
		}
		AdvisorProfileDTO advisor = new AdvisorProfileDTO();
		try {
			if(!userName.isEmpty()){
				 ForgotPasswordDAO dao = new ForgotPasswordDAO();
				 advisor = dao.getForgotPasswordDetails(userName,register);				
					if(advisor.getAdvisorId() != 0 && advisor.getEmail() != null){	
						advisorId = advisor.getAdvisorId();
						email = advisor.getEmail(); 
						if(advisorId != 0 && !email.isEmpty()){
							ForgotPasswordDAO dao1 = new ForgotPasswordDAO();
							isInsertComplete = dao1.setForgotPasswordDetails(advisorId,email);
							if(isInsertComplete){
								String url = prop.getProperty("ADVISOR_FORGOT_PASSWORD_MAIL_URL")+advisorId;
								String subject ="Forgot Password";
								String content ="Hi, <br><br>Please Click on the below link to set your New Password: <br><a href= '"+url+"'>Click Here To Set New Password</a>"+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
								SendMail mail = new SendMail(subject, content, email,prop1.getProperty("MAIL_ADMIN"));
								mail.start();	
									response.getWriter().write("<h3>A Mail has been sent to your registered Email Id.</h3><p>Please check your mail and reset your password</p>");
							}
						}
					}else {
						response.getWriter().write("Sorry, This email Id is not registered with us.");
					}
			}
			logger.info("Exit doPost method of ForgotPasswordController");
		} catch (Exception e) {
			logger.error("doPost method of ForgotPasswordController threw error:"+e.getMessage());
			e.printStackTrace();
		}
			
	}

}
