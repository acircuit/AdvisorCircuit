package org.AC.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.AC.JDBC.Util;
import org.AC.Util.Mail;
import org.AC.Util.PasswordGenerator;
import org.AC.Util.PasswordHashing;
import org.AC.dto.AdvisorModeDTO;
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
		
		BasicConfigurator.configure();
		logger.info("Entered doPost method of ForgotPasswordController");
		int advisorId = 0;
		String email = "";
		String userName = request.getParameter("username");
		String password = "";
		String hashPassword = "";
		Boolean isInvalidUsername = false;
		Boolean isInsertComplete =false;
		try {
			if(!userName.isEmpty()){
				 ForgotPasswordDAO dao = new ForgotPasswordDAO();
				 ResultSet results = dao.getForgotPasswordDetails(userName);				
					if(results.first()){	
						advisorId = results.getInt("ADVISOR_ID");
						email = results.getString("EMAIL"); 
						if(advisorId != 0 && !email.isEmpty()){
							PasswordGenerator pass = new PasswordGenerator();
							password = pass.generatePassword(userName,advisorId);
							if(!password.isEmpty() && !password.equals("")){
								PasswordHashing securedPassword = new PasswordHashing();
								hashPassword = securedPassword.doHash(password);
							}
							if(!hashPassword.isEmpty() && !hashPassword.equals("")){
								ForgotPasswordDAO dao1 = new ForgotPasswordDAO();
								isInsertComplete = dao1.setForgotPasswordDetails(advisorId,email,userName,hashPassword);
							}
							if(isInsertComplete){
								Mail mail = new Mail();
								Boolean isMailSent =  mail.SendMailToAdvisor(email,advisorId);
								if(isMailSent){
									response.sendRedirect("ForgotPasswordMailSent.jsp");
								}
							}
						}
					}else {
						 isInvalidUsername = true;
						 request.setAttribute("isInvalidUsername", isInvalidUsername);
						 RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
				         rd.forward(request, response);
					}
			}
			logger.info("Exit doPost method of ForgotPasswordController");
		} catch (SQLException e) {
			logger.error("doPost method of ForgotPasswordController threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.error("doPost method of ForgotPasswordController threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			logger.error("doPost method of ForgotPasswordController threw error:"+e.getMessage());
			e.printStackTrace();
		}
			
	}

}
