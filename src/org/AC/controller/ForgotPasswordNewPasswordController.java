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
import org.AC.Util.PasswordHashing;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class ForgotPasswordLoginController
 * This servlet retrieves the form fields of the Login.jsp and authenticate the user.
 */
@WebServlet("/ForgotPasswordNewPasswordController")
public class ForgotPasswordNewPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ForgotPasswordNewPasswordController.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("Entered doPost method of ForgotPasswordNewPasswordController");
		String NewPassword = request.getParameter("NewPassword");
		String ConfirmPassword = request.getParameter("ConfirmPassword");
		String aId = (String) request.getParameter("id");	
		String securedPassword = "";
		Boolean isInsertComplete =false;
		try {
				PasswordHashing hash = new PasswordHashing();
				securedPassword=	hash.doHash(NewPassword);
				ForgotPasswordDAO dao = new ForgotPasswordDAO();
				isInsertComplete = dao.updatePassword(securedPassword, aId);
				if(isInsertComplete){
					response.sendRedirect("login");
				}
		}catch (NoSuchAlgorithmException | NoSuchProviderException e) {
        	logger.error("doPost method of ForgotPasswordNewPasswordController threw error:"+e.getMessage());
			e.printStackTrace();
		}
	
		logger.info("Exit doPost method of ForgotPasswordLoginController");
	}

}
