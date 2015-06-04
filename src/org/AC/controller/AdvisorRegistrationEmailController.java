package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.Util.PasswordHashing;
import org.AC.Util.SendMail;
import org.AC.dto.AdvisorRegistrationCheckEmailDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorRegistrationEmailController
 */
@WebServlet("/AdvisorRegistrationEmailController")
public class AdvisorRegistrationEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationEmailController.class);

       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This servlet will retrieve the email Id check for the existence of the advisor in the table and depending upon the status 
	 * redirect the advisor to respective page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorRegistrationEmailController");
		String email = request.getParameter("email");
		String password = request.getParameter("passwd");
		String status ="";
		int advisorId = 0;
		String name = "";
		Properties prop = new Properties();
	    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop.load(resourceAsStream);
		AdvisorRegistrationCheckEmailDTO advisor = new AdvisorRegistrationCheckEmailDTO();

		//Once we have retrieved the email we have to check whether the advisor have already started registration process.
		//And if yes, then at what point did he stop the registration.
		if(!email.isEmpty()){
			String securedPassword = "";
			PasswordHashing pass = new PasswordHashing();
			try {
				securedPassword = pass.doHash(password);
			} catch (NoSuchAlgorithmException e) {
				logger.error("doPost method of AdvisorRegistrationEmailController threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				logger.error("doPost method of AdvisorRegistrationEmailController threw error:"+e.getMessage());
				e.printStackTrace();
			}
			//Check if the email Id is taken already
			AdvisorRegistrationDAO dao1 = new AdvisorRegistrationDAO();
			advisor = dao1.checkEmail(email,securedPassword);
			if(advisor.getAdvisorId() == 0){
				
					AdvisorRegistrationDAO dao = new AdvisorRegistrationDAO();
					int aId = dao.VerifyEmail(email);
					if(aId == 0){
						//If the advisor has not started the registration process then set the email address and change the status
						AdvisorRegistrationDAO dao2 = new AdvisorRegistrationDAO();
						advisorId = dao2.setEmail(email,securedPassword);
						String comment = email+" with AdvisorId =" + advisorId+" started the registration process";
						String href = "AdvisorProfile?aId="+advisorId+"&admin=true";
						//Notify Admin
						AdminNotificationDAO notify = new AdminNotificationDAO();
						notify.InsertNotification(comment,href);
						
						//This is a new advisor.Send verification mail
						String subject ="";
						String content ="";
						subject = "Thank Your For Registering";
						content = "Hi, <br><br>ThankYou for registering with AdvisorCircuit. Please Click on the below link to create your own profile:<br> <a href='"+prop.getProperty("ADVISOR_REGISTRATION_VERIFICATION_LINK")+advisorId+"'>Click Here to Create Your Profile</a>"+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='15%'>";
						SendMail mail = new SendMail(subject, content, email,prop.getProperty("MAIL_ADMIN"));
						mail.start();
						response.sendRedirect("AdvisorRegistrationComplete");
					}else{
						request.setAttribute("isInvalid", true);
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/Email");
				        rd.forward(request, response);
					}
				
			}else{
				if(advisor.getIsVerified()){
					advisorId = advisor.getAdvisorId();
					status = advisor.getRegistrationStatus();
					name = advisor.getName();
					request.getSession().setAttribute("aId", advisorId);
					request.getSession().setAttribute("name", name);
					request.getSession().setAttribute("email", email);
					response.sendRedirect(status);
				}else{
					request.setAttribute("isNotVerified", true);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Email");
			        rd.forward(request, response);
				}
				
			}
		logger.info("Exit doPost method of AdvisorRegistrationEmailController");

		}
	}
}
