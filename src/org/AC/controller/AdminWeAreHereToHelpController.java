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

import org.AC.DAO.AdminUserDAO;
import org.AC.dto.HereToHelpDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminWeAreHereToHelpController
 */
@WebServlet("/AdminWeAreHereToHelpController")
public class AdminWeAreHereToHelpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(WeAreHereToHelpController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdminWeAreHereToHelpController");
		Boolean isAdmin = false;
		Boolean isError = false;
		try {
			isAdmin = (Boolean) request.getSession().getAttribute("admin");
		} catch (Exception e) {
			response.sendRedirect("Error");
		}
		if (isAdmin == null) {
			isError = true;
			response.sendRedirect("Error");
		}
		if (isError != null && !isError) {
			// Getting the details from heretohelp table
			List<HereToHelpDTO> list = new ArrayList<HereToHelpDTO>();
			AdminUserDAO user = new AdminUserDAO();
			list = user.GetHereToHelpDetails();
			request.setAttribute("heretohelp", list);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/WeAreHereToHelp.jsp");
			rd.forward(request, response);
		}
		logger.info("Entered doGet method of AdminWeAreHereToHelpController");
	}
}
