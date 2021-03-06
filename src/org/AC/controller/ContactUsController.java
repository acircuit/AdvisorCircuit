/*************************************************************************************************
/ * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 12/13/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.ContactUsDAO;
import org.AC.Util.SendMail;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
 * 
 * This class will retrieve the contact us information and put them in contactus table.
 * 
 * 
 *
 ***************************************************************************************************/
/**
 * Servlet implementation class ContactUsController
 */
@WebServlet("/ContactUsController")
public class ContactUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(ContactUsController.class);

	/**************************************
	 * COMMENTS*************************************************** This class
	 * will retrieve the contact us information and put them in contactus table.
	 * 
	 * @return :None
	 * @param : HttpServletRequest request HttpServletResponse response
	 * 
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of ContactUsController");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");
		message = message.replaceAll("\r\n", "<br>");
		message = message.replaceAll("\r", "<br>");
		message = message.replaceAll("\n", "<br>");
		if (name != null && email != null && phone != null && message != null) {
			// Putting the values in the table
			Boolean isCommit = false;
			ContactUsDAO contact = new ContactUsDAO();
			isCommit = contact.SetContactUsDetails(name, email, phone, message);
			if(isCommit){
				//Notify Admin
				String comment = name + "  sent a message through the Contact Us Page";
				String href = "AdminContactUs";
				AdminNotificationDAO notify = new AdminNotificationDAO();
				notify.InsertNotification(comment, href);
				int image = 1;
				String subject = "Contact Us!";
				String content = "Hi, <br><br>A user has sent a query through the contact us page. Following are the details:<br>Name: "
						+ name
						+ "<br>Email Id: "
						+ email
						+ "<br>Phone: "
						+ phone
						+ "<br>Message: "
						+ message
						+ "<br><img src=\"https://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='15%'>";
				SendMail mail = new SendMail(subject, content,
						"udaykhatry@advisorcircuit.com", email);
				mail.start();
				response.sendRedirect("ContactUsThankyou");
			}
		}
		logger.info("Exit doPost method of ContactUsController");
	}
}
