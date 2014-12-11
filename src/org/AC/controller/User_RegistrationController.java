package org.AC.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.AC.DAO.UserDetailsDAO;
import org.AC.Util.PasswordHashing;
import org.AC.Util.SetFormImage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class User_RegistrationController
 * This class will retrieve the user details from the form and populate the user table
 */
@WebServlet("/User_RegistrationController")
@MultipartConfig
public class User_RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(User_RegistrationController.class);
	Connection conn = null;
	Statement stmt = null;   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BasicConfigurator.configure();
		logger.info("Entered doPost method of User_RegistrationController");
		//Retrieving the form fields from the user_registration form.
		String email = request.getParameter("email");
		String password = request.getParameter("passwd");
		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");
		String age = request.getParameter("age");
		String nationality = request.getParameter("nationality");
		String occupation = request.getParameter("occupation");
		String hashPassword = "";
		int result = 0;
		
		try {
			if(!email.isEmpty() && !password.isEmpty() && !fullname.isEmpty() && !phone.isEmpty() 
					&& !age.isEmpty() && !nationality.isEmpty() && !occupation.isEmpty() && email != null && email != null
					&& email != null && email != null && email != null && email != null && email != null){
					//Setting the image retrieved from the user to the required file location
					SetFormImage image = new SetFormImage();
					String absolutePath = image.putImage(request,response,email);
					
					//Hashing the retrieved password from the user.
					PasswordHashing securedPass = new PasswordHashing();
					hashPassword = securedPass.doHash(password);
					
					//Setting the user details in the userdetails table
					UserDetailsDAO dao = new UserDetailsDAO();
					 Boolean isDetailsCommit = dao.setUserDetails(email,hashPassword,fullname,phone,age,occupation,nationality,absolutePath);
					if(isDetailsCommit){
						 response.sendRedirect("User_Login.jsp");
				
					}
			}
		logger.info("Exit doPost method of User_RegistrationController");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			logger.error("doPost method of User_RegistrationController threw error:"+e.getMessage());
			e.printStackTrace();
		}

	}

}
