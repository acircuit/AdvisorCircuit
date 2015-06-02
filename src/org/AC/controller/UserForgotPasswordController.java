package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
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
import org.AC.DAO.UserForgotPasswordDAO;
import org.AC.Util.SendMail;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserForgotPasswordController
 */
@WebServlet("/UserForgotPasswordController")
public class UserForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserForgotPasswordController.class);
	Connection conn = null;
	Statement stmt = null;
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of UserForgotPasswordController");
		String email = "";
		int userId = 0;
		Boolean isInvalidUsername = false;
		Boolean isInsertComplete = false;
		email = request.getParameter("email");
		Properties prop = new Properties();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
        try {
			prop.load(resourceAsStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UserDetailsDTO user = new UserDetailsDTO();
		if(!email.isEmpty() && !("").equals(email)){
			UserForgotPasswordDAO dao = new UserForgotPasswordDAO();
			user = dao.getForgotPasswordDetails(email);
			if(user.getUserId() != 0 && user.getEmail() != null){	
				userId = user.getUserId();
			}else{
				 response.getWriter().write("Sorry, This email Id is not registered with us.");
			}
			if(userId != 0){
				UserForgotPasswordDAO dao1 = new UserForgotPasswordDAO();
				isInsertComplete = dao1.setForgotPasswordDetails(userId, email);
				if(isInsertComplete){
				     String url = prop.getProperty("USER_FORGOT_PASSWORD_MAIL_URL")+userId;
					String subject ="Forgot Password";
					String content ="Hi, <br><br>Please Click on the below link to set your New Password: <br><a href='"+url+"'>Click Here To Set New Password</a>"+"<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					System.out.println(content);
					SendMail mail = new SendMail(subject, content, email,prop.getProperty("MAIL_ADMIN"));
					mail.start();	
					response.getWriter().write("<h3>A Mail has been sent to your registered Email Id.</h3><p>Please check your mail and reset your password</p>");
				}
			}			
			
		}
		
		
		logger.info("Exit doPost method of UserForgotPasswordController");
	}

}
