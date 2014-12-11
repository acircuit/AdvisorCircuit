 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 10/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminAdvisorDAO;
import org.AC.DAO.AdvisorProfileDetailsDAO;
import org.AC.DAO.MessageDAO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.MessageDTO;
/********************************CLASS SUMMARY*****************************************************
* 
* This class will get the email from the admin and get the AdvisorId from the advisordetails table.
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMyAccountAdvisorMessageController
 */
@WebServlet("/AdminMyAccountAdvisorMessageController")
public class AdminMyAccountAdvisorMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	/**************************************COMMENTS***************************************************
	 * This class will get the email from the admin and get the AdvisorId from the advisordetails table.
	 *   and then fetch the mesges from the advisormessageadmin table.
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if(email == null){
			 email = (String) request.getAttribute("email");
		}
		if(email != null){
			AdminAdvisorDAO dao = new AdminAdvisorDAO();
			int advisorId = dao.GetAdvisorId(email);
			if(advisorId != 0){
				String advisorName = "";
				 List<AdvisorProfileDTO> name = new ArrayList<AdvisorProfileDTO>();
				 AdvisorProfileDetailsDAO detail =new AdvisorProfileDetailsDAO();
				 name = detail.getAdvisorProfileDetails(Integer.toString(advisorId));
				 for (AdvisorProfileDTO advisorProfileDTO : name) {
					 advisorName = advisorProfileDTO.getName();
				}
				List<MessageDTO> message = new ArrayList<MessageDTO>();
				//Get advisor messages and response from advisormessageadmin table
				MessageDAO messages = new MessageDAO();
				message = messages.getAdvisorMessages(advisorId);
				if(message.size() > 0){
					for (MessageDTO messageDTO : message) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'HH:mm a");
						messageDTO.setAdvisorMessageTimeString(dateFormat.format(messageDTO.getAdvisorMessageTime()));
						if(messageDTO.getAdminMessageTime() != null){
							messageDTO.setAdminMessageTimeString(dateFormat.format(messageDTO.getAdminMessageTime()));
						}
				}	
				request.setAttribute("email", email);	
				request.setAttribute("message", message);
				request.setAttribute("name", advisorName);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_Messages.jsp");
		        rd.forward(request, response);
				}else{
					Boolean isInvalid = true;
					request.setAttribute("isInvalidEmail",isInvalid);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_Messages_Email.jsp");
			        rd.forward(request, response);
				}
			}
		}
	}
}
