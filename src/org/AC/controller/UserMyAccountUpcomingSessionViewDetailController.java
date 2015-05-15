package org.AC.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorMyAccountSessionDAO;
import org.AC.DAO.MyAccountRequestDAO;
import org.AC.DAO.SessionFeedBackDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.Util.GetTimeLeftForReply;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SessionDTO;
import org.AC.dto.SessionFeedBackDTO;
import org.AC.dto.TimeDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserMyAccountUpcomingSessionViewDetailController
 */
@WebServlet("/UserMyAccountUpcomingSessionViewDetailController")
public class UserMyAccountUpcomingSessionViewDetailController extends
		HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(UserMyAccountUpcomingSessionViewDetailController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of UserMyAccountUpcomingSessionViewDetailController");
		int advisorId = 0;
		String username = "";
		int userId = 0;
		String rId = "";
		String advisorName = "";
		String picture = "";
		String relImage = "";
		String userName = "";
		int sId = 0;
		String details = "";
		String path = "";
		String mode = "";
		Boolean isFeedback = false;
		Boolean isModeDetail = false;
		Timestamp acceptedDate = null;
		rId = (String) request.getParameter("rId");
		try {
			userId = (int) request.getSession().getAttribute("userId");
			username = (String) request.getSession().getAttribute("username");

		} catch (Exception e) {
			response.sendRedirect("Error");
		}
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		if (username != null && userId != 0 && !("").equals(username)) {
			List<UserDetailsDTO> list2 = new ArrayList<UserDetailsDTO>();
			List<UserRequestDTO> list1 = new ArrayList<UserRequestDTO>();
			List<AdvisorProfileDTO> list3 = new ArrayList<AdvisorProfileDTO>();
			// Getting the session details
			AdvisorMyAccountSessionDAO session = new AdvisorMyAccountSessionDAO();
			list = session.getSessionDetails(rId);
			for (SessionDTO SessionDTO : list) {
				sId = SessionDTO.getSessionId();
				advisorId = SessionDTO.getAdvisorId();
				acceptedDate = SessionDTO.getAcceptedDate();
				SessionDTO.setAcceptedDateString(new SimpleDateFormat(
						"dd-MMM-yyyy' 'h:mm a").format(new Date(SessionDTO
						.getAcceptedDate().getTime())));
			}

			MyAccountRequestDAO name = new MyAccountRequestDAO();
			list3 = name.getAdvisorName(advisorId);
			for (AdvisorProfileDTO advisorProfileDTO : list3) {
				advisorName = advisorProfileDTO.getName();
				picture = advisorProfileDTO.getImage();
			}
			MyAccountRequestDAO image = new MyAccountRequestDAO();
			list2 = image.getUserImage(userId);
			for (UserDetailsDTO userDetailsDTO : list2) {
				userName = userDetailsDTO.getFullName();
			}
			if (!("").equals(picture)) {
				GetRelativeImageURL image1 = new GetRelativeImageURL();
				relImage = image1.getImageURL(picture);
			}
			List<TimeDTO> difference = new ArrayList<TimeDTO>();
			// After retrieving the user details, get the user request details
			MyAccountRequestDAO dao = new MyAccountRequestDAO();
			list1 = dao.getUserRequestDetails(rId);
			GetTimeLeftForReply time = new GetTimeLeftForReply();
			difference = time.getTimeLeftForSession(acceptedDate);
			for (UserRequestDTO userRequestDTO : list1) {
				mode = userRequestDTO.getMode();
				if (userRequestDTO.getService().equals("cvcritique")
						|| userRequestDTO.getService().equals("mockinterview")) {
					isFeedback = true;
				}
				if (userRequestDTO.getMode().equals("phone")
						|| userRequestDTO.getMode().equals("webchat")) {
					isModeDetail = true;
				}
				if (difference.size() > 0) {
					for (TimeDTO timeDTO : difference) {
						userRequestDTO.setDays(timeDTO.getDay());
						userRequestDTO.setHours(timeDTO.getHours());
						userRequestDTO.setMinutes(timeDTO.getMinutes());
					}
				} else {
					userRequestDTO.setDays(0);
					userRequestDTO.setHours(0);
					userRequestDTO.setMinutes(0);
				}
			}
			String sessionDate = "";
			String date = "";
			String time1 = "";
			if (acceptedDate != null) {
				sessionDate = acceptedDate.toString();
				Timestamp timestamp = new Timestamp(acceptedDate.getTime());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"dd-MMM-yyyy");
				SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
						"h:mm a");
				date = simpleDateFormat.format(timestamp);
				if (mode.equals("email")) {
					time1 = "N/A";
				} else {
					time1 = simpleDateFormat1.format(timestamp);
				}

			} else {
				date = "NOT FIXED";
				time1 = "NOT FIXED";
			}
			if (isFeedback) {
				// Getting the FeedBack Form Path
				MyAccountRequestDAO form = new MyAccountRequestDAO();
				path = form.GetFeedbackPathForUser(sId);
			}
			if (isModeDetail) {
				// Getting Pin or Link
				MyAccountRequestDAO detail = new MyAccountRequestDAO();
				details = detail.GetModeDetails(sId);
			}
			SessionFeedBackDTO feedUser = new SessionFeedBackDTO();
			SessionFeedBackDTO feedAdvisor = new SessionFeedBackDTO();
			// Check if admin has approved whether to show the session feedback
			SessionFeedBackDAO sessionFeedback = new SessionFeedBackDAO();
			Boolean visible = sessionFeedback.CheckForVisibility(sId);
			// Check if the user has given any feedback
			SessionFeedBackDAO feedback = new SessionFeedBackDAO();
			feedUser = feedback.GetUserFeedBackDetailsForUser(sId);
			SessionFeedBackDAO feedbackAdvisor = new SessionFeedBackDAO();
			feedAdvisor = feedbackAdvisor.GetAdvisorFeedBackDetailsForUser(sId);

			SessionFeedBackDTO emailUser = new SessionFeedBackDTO();
			SessionFeedBackDTO emailAdvisor = new SessionFeedBackDTO();
			SessionFeedBackDAO mail = new SessionFeedBackDAO();
			emailUser = mail.GetUserMailForUser(sId);
			SessionFeedBackDAO mailAdvisor = new SessionFeedBackDAO();
			emailAdvisor = mailAdvisor.GetAdvisorMailForUser(sId);
			if (list.size() > 0 && list1.size() > 0 && list2.size() > 0) {
				request.setAttribute("advisorname", advisorName);
				request.setAttribute("userName", userName);
				request.setAttribute("image", relImage);
				request.setAttribute("sessions", list);
				request.setAttribute("time", time1);
				request.setAttribute("date", date);
				request.setAttribute("feedUser", feedUser);
				request.setAttribute("feedAdvisor", feedAdvisor);
				request.setAttribute("emailUser", emailUser);
				request.setAttribute("emailAdvisor", emailAdvisor);
				request.setAttribute("visible", visible);
				request.setAttribute("details", details);
				request.setAttribute("path", path);
				request.setAttribute("requests", list1);
				request.setAttribute("userdetails", list2);
				request.setAttribute("sessionDate", sessionDate);
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/UserSession_ViewDetails.jsp");
				rd.forward(request, response);
			}
		}
		logger.info("Exit doGet method of UserMyAccountUpcomingSessionViewDetailController");

	}
}
