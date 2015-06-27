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
import java.text.MessageFormat;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.UserDetailsDAO;
import org.AC.DAO.UserNotificationDAO;
import org.AC.Util.PasswordHashing;
import org.AC.Util.SendMail;
import org.AC.Util.SetFormImage;
import org.apache.commons.io.FileUtils;
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
		logger.info("Entered doPost method of User_RegistrationController");
		String emailFromAjax = request.getParameter("emailFromAjax");
		//Retrieving the form fields from the user_registration form.
		String email = request.getParameter("email");
		String password = request.getParameter("passwd");
		String fullname = request.getParameter("fullname");
		String promo_code = request.getParameter("refcode");
		String uId = request.getParameter("refCodeUserId");
		String isPromoActive = request.getParameter("isPromoActive");
		String isCodeApplied = request.getParameter("isCodeApplied");
		String newsletter = request.getParameter("newsletter");
		String hashPassword = "";
		int result = 0;
		Properties prop = new Properties();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
        Properties prop1 = new Properties();
        InputStream resourceAsStream1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/Path.properties");
        try {
			prop.load(resourceAsStream);
			prop1.load(resourceAsStream1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			if(email != null && password != null && fullname != null &&  !email.isEmpty() && !password.isEmpty() && !fullname.isEmpty()  
					 && emailFromAjax == null ){
						String absolutePath="";
						//Setting the image retrieved from the user to the required file location
					    File source = new File(prop1.getProperty("DUMMY_USER_IMAGE_SOURCE_PATH"));
					    absolutePath = MessageFormat.format(prop1.getProperty("DUMMY_USER_IMAGE_DESTINATION_PATH"), email);
						File dest = new File(absolutePath);
						try {
						    FileUtils.copyFile(source, dest);
						} catch (IOException e) {
						    e.printStackTrace();
						}
						//Hashing the retrieved password from the user.
						PasswordHashing securedPass = new PasswordHashing();
						hashPassword = securedPass.doHash(password);
						
						//Setting the user details in the userdetails table
						UserDetailsDAO dao = new UserDetailsDAO();
						int userId = dao.setUserDetails(email,hashPassword,fullname,absolutePath,newsletter);
						if(userId != 0){
							if(isPromoActive != null && isPromoActive.equals("true")){
								//Enter the promo code for the user 
								String code = "ACREF"+userId;
								UserDetailsDAO promo = new UserDetailsDAO();
								Boolean isCommit = promo.SetUserPromo(userId,code);
								if(isCommit && promo_code != null && uId != null && !promo_code.equals("") && isCodeApplied.equals("true")){
									//Enter the user's referral code used.
									UserDetailsDAO ref = new UserDetailsDAO();
									ref.SetUserSignUpReferral(promo_code,userId);
									String uId1 = "";
									if(!promo_code.equals("")){
										uId1 = code.substring(5);
									}
									
									String comment = fullname+" signed up as a user using "+promo_code+" as promo code";
									String href = "AdminViewUserProfile?email="+email;
									AdminNotificationDAO notify = new AdminNotificationDAO();
									notify.InsertNotification(comment, href);
									
								
								}else{
									String comment = fullname+" signed up as a user";
									String href = "AdminViewUserProfile?email="+email;
									AdminNotificationDAO notify = new AdminNotificationDAO();
									notify.InsertNotification(comment, href);
								}
							}else{
								String comment = fullname+" signed up as a user";
								String href = "AdminViewUserProfile?email="+email;
								AdminNotificationDAO notify = new AdminNotificationDAO();
								notify.InsertNotification(comment, href);
							}
							String userComment = "Welcome to Advisor Circuit. Find your Advisor now ! If you need any help, call us on +91 9999372087";
							String userHref = "Advisors?service=All";
							UserNotificationDAO user = new UserNotificationDAO();
							user.InsertNotification(userComment, userHref, String.valueOf(userId));
							
						 	String subject ="";
							String content ="";
							subject = "Thank Your For Registering";
							content = "Hi, <br><br> ThankYou for registering with AdvisorCircuit. Please Click on the below link to activate your account:<br> <a href='"+prop.getProperty("USER_REGISTRATION_VERIFICATION_LINK")+userId+"'>Click Here to Activate Your Account</a>"+"<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='15%'>";
							SendMail mail = new SendMail(subject, content, email,prop.getProperty("MAIL_ADMIN"));
							mail.start();
							String subject1= "A New User Sign Up!";
							String content1 = "Hi, <br><br> A new user has signed up with us. Following are the details: <br>Full Name : "+fullname+"<br>Email Id : " +email+"<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='15%'>";
							SendMail mail1 = new SendMail(subject1, content1, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
							mail1.start();
							response.sendRedirect("UserRegistrationComplete");
						}
			}else if (emailFromAjax != null) {
				//Check if user has already been signed up
				UserDetailsDAO login = new UserDetailsDAO();
				Boolean isNew = login.CheckRegistraion(emailFromAjax);
				if(isNew){
					response.getWriter().write("true");
				}else{
					response.getWriter().write("false");
				}
			}
		logger.info("Exit doPost method of User_RegistrationController");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			logger.error("doPost method of User_RegistrationController threw error:"+e.getMessage());
			e.printStackTrace();
		}

	}

}
