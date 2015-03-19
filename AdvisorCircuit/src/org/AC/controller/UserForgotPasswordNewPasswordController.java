package org.AC.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.ForgotPasswordDAO;
import org.AC.DAO.UserForgotPasswordDAO;
import org.AC.Util.PasswordHashing;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserForgotPasswordNewPasswordController
 */
@WebServlet("/UserForgotPasswordNewPasswordController")
public class UserForgotPasswordNewPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserForgotPasswordNewPasswordController.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of UserForgotPasswordNewPasswordController");
		String NewPassword = request.getParameter("NewPassword");
		String ConfirmPassword = request.getParameter("ConfirmPassword");
		String uId = (String) request.getParameter("id");	
		String securedPassword = "";
		Boolean isInsertComplete =false;
		try {	
			if(!uId.isEmpty() && !("").equals(uId)){
				PasswordHashing hash = new PasswordHashing();
				securedPassword=	hash.doHash(NewPassword);
				UserForgotPasswordDAO dao = new UserForgotPasswordDAO();
				isInsertComplete = dao.updatePassword(securedPassword, uId);
				if(isInsertComplete){
					response.sendRedirect("login");
				}
			}
		}catch (NoSuchAlgorithmException | NoSuchProviderException e) {
        	logger.error("doPost method of UserForgotPasswordNewPasswordController threw error:"+e.getMessage());
			e.printStackTrace();
		}
	
		logger.info("Exit doPost method of UserForgotPasswordNewPasswordController");
	}

}