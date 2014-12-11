package org.AC.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ForgotPasswordDAO;
import org.AC.DAO.UserForgotPasswordDAO;
import org.AC.Util.Mail;
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

		BasicConfigurator.configure();
		logger.info("Entered doPost method of UserForgotPasswordController");
		String email = "";
		int userId = 0;
		Boolean isInvalidUsername = false;
		Boolean isInsertComplete = false;
		email = request.getParameter("email");
		try{
			if(!email.isEmpty() && !("").equals(email)){
				UserForgotPasswordDAO dao = new UserForgotPasswordDAO();
				ResultSet results = dao.getForgotPasswordDetails(email);
				if(results.first()){	
					userId = results.getInt("USER_ID");
				}else{
					 isInvalidUsername = true;
					 request.setAttribute("isInvalidUsername", isInvalidUsername);
					 RequestDispatcher rd = getServletContext().getRequestDispatcher("/User_Login.jsp");
			         rd.forward(request, response);
				}
				if(userId != 0){
					UserForgotPasswordDAO dao1 = new UserForgotPasswordDAO();
					isInsertComplete = dao1.setForgotPasswordDetails(userId, email);
					if(isInsertComplete){
						Mail mail = new Mail();
						Boolean isMailSent = mail.SendMailToUser(email,userId);
						if(isMailSent){
							response.sendRedirect("ForgotPasswordMailSent.jsp");
						}
					}
				}			
				
			}else{
				
				response.sendRedirect("Error.jsp");
			}
		}catch (SQLException e) {
			logger.error("doPost method of UserForgotPasswordController threw error:"+e.getMessage());
			e.printStackTrace();
		}
		
		
		logger.info("Exit doPost method of UserForgotPasswordController");
	}

}
