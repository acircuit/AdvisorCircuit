 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 11/12/2014
 * ************************************************************************************************/

package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.MyAccountRequestDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch all the request,user and advisor details to show on the view details page.
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMyAccountRequestViewDetailsController
 */
@WebServlet("/AdminMyAccountRequestViewDetailsController")
public class AdminMyAccountRequestViewDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountRequestViewDetailsController.class);     
	
 	/**************************************COMMENTS***************************************************
	 * This method will fetch all the request,user and advisor details to show on the view details page.
	 *   
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doGet method of AdminMyAccountRequestViewDetailsController");
		String rId = "";
		int advsorId = 0;
		int userId = 0;
		String userName = "";
		String advisorName = "";
		String userPicture = "";
		String advisorPicture = "";
		String userRelImage = "";
		String advisorRelImage = "";
	    rId = (String)request.getParameter("rId");
	    if(rId != null && !("").equals(rId)){
			List<UserRequestDTO> requestDetails = new ArrayList<UserRequestDTO>();
			List<UserDetailsDTO> userDetails = new ArrayList<UserDetailsDTO>();
			List<AdvisorProfileDTO> advisorDetails = new ArrayList<AdvisorProfileDTO>();
			MyAccountRequestDAO dao = new MyAccountRequestDAO();
			requestDetails = dao.getUserRequestDetails(rId);
			for (UserRequestDTO userRequestDTO : requestDetails) {
				advsorId = userRequestDTO.getAdvisorId();
				userId = userRequestDTO.getUserId();
			}
			//Getting user details to show on the view details page.
			if(userId != 0){
				MyAccountRequestDAO image = new MyAccountRequestDAO();
				userDetails = image.getUserImage(userId);
				for (UserDetailsDTO userDetailsDTO : userDetails) {
					userName = userDetailsDTO.getFullName();
					userPicture = userDetailsDTO.getImage();
				}
			}
			//Getting advisor details to show on the view details page.
			if(advsorId != 0){
				MyAccountRequestDAO name = new MyAccountRequestDAO();
				advisorDetails = name.getAdvisorName(advsorId);
				for (AdvisorProfileDTO advisorProfileDTO : advisorDetails) {
					advisorName = advisorProfileDTO.getName();
					advisorPicture = advisorProfileDTO.getImage();
				}
			}
			//Getting the relative image URL for user and advisor image
			if(!("").equals(userPicture) && !("").equals(advisorPicture)){
				GetRelativeImageURL image = new GetRelativeImageURL();
				 userRelImage = image.getImageURL(userPicture);
				 advisorRelImage = image.getImageURL(advisorPicture);

			}
			request.setAttribute("advisorImage", advisorRelImage);
			request.setAttribute("userImage", userRelImage);
			request.setAttribute("userName", userName);
			request.setAttribute("advisorName", advisorName);
			request.setAttribute("requestDetails", requestDetails);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_Request_ViewDetails.jsp");
	        rd.forward(request, response);
	    }
		logger.info("Exit doGet method of AdminMyAccountRequestViewDetailsController");
    }
}
