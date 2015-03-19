 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 30/11/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorMyAccountSessionDAO;
import org.AC.DAO.MyAccountRequestDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.Util.GetTimeLeftForReply;
import org.AC.dto.AdvisorNewDatesDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SessionDTO;
import org.AC.dto.TimeDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch  session  and user details to display on the view details form.
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdmnSessionViewDetailController
 */
@WebServlet("/AdmnSessionViewDetailController")
public class AdmnSessionViewDetailController extends HttpServlet {
	private static final Logger logger = Logger.getLogger(AdmnSessionViewDetailController.class);     
      
	
 	/**************************************COMMENTS***************************************************
	 * This method will fetch  session  and user details to display on the view details form.
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
		logger.info("Entered doGet method of AdmnSessionViewDetailController");
		Boolean isAdmin = false;
		Boolean isError = false;
		try{
			isAdmin = (Boolean) request.getSession().getAttribute("admin"); 
			}catch(Exception e){
				response.sendRedirect("Error");
				isError = true;
			}
		if(isAdmin == null){
			isError = true;
			response.sendRedirect("Error");
		}
		if(isError!= null &&  !isError){
			String rId = "";
			int advsorId = 0;
			int userId = 0;
			String userName = "";
			String advisorName = "";
			String userPicture = "";
			String advisorPicture = "";
			String userRelImage = "";
			String advisorRelImage = "";
			String requestStatus = "";
			int sessionId = 0;
			List<UserRequestDTO> requestDetails = new ArrayList<UserRequestDTO>();
			List<UserDetailsDTO> userDetails = new ArrayList<UserDetailsDTO>();
			List<AdvisorProfileDTO> advisorDetails = new ArrayList<AdvisorProfileDTO>();
			List<SessionDTO> sessionDetail = new ArrayList<SessionDTO>();
			List<AdvisorNewDatesDTO> advisorNewDates = new ArrayList<AdvisorNewDatesDTO>();
		    rId = (String)request.getParameter("rId");
		    if(rId != null && !("").equals(rId)){	
				MyAccountRequestDAO dao = new MyAccountRequestDAO();
				requestDetails = dao.getUserRequestDetails(rId);
				for (UserRequestDTO userRequestDTO : requestDetails) {
					advsorId = userRequestDTO.getAdvisorId();
					userId = userRequestDTO.getUserId();
					requestStatus = userRequestDTO.getStatus();
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
				//Getting the session details
				AdvisorMyAccountSessionDAO sessionDetails = new AdvisorMyAccountSessionDAO();
				sessionDetail = sessionDetails.getSessionDetails(rId);
		    }
			request.setAttribute("advisorImage", advisorRelImage);
			request.setAttribute("userImage", userRelImage);
			request.setAttribute("userName", userName);
			request.setAttribute("advisorName", advisorName);
			request.setAttribute("requestDetails", requestDetails);
			request.setAttribute("sessionDetail", sessionDetail);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminSessionViewDetail.jsp");
	        rd.forward(request, response);
		}
		logger.info("Entered doGet method of AdmnSessionViewDetailController");
	}
}
