package org.AC.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminUserDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserMyAccountController
 */
@WebServlet("/UserMyAccountController")
public class UserMyAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(UserMyAccountController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of UserMyAccountController");
		int userId = 0;
		Boolean isError = false;
		try {
			userId = (int) request.getSession().getAttribute("userId");
		} catch (Exception e) {
			response.sendRedirect("Error");
			isError = true;
		}
		if (!isError) {
			UserDetailsDTO user = new UserDetailsDTO();
			AdminUserDAO details = new AdminUserDAO();
			user = details.GetUserDetails(userId);
			GetRelativeImageURL img = new GetRelativeImageURL();
			user.setImage(img.getImageURL(user.getImage()));
			user.setDor(new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a")
					.format(new Date(user.getDateOfRegistration().getTime())));
			request.setAttribute("user", user);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/userdashboard");
			rd.forward(request, response);
		}

		logger.info("Entered doGet method of UserMyAccountController");
	}
}
