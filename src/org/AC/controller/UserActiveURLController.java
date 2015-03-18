package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.UserDetailsDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserActiveURLController
 */
@WebServlet("/UserActiveURLController")
public class UserActiveURLController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserActiveURLController.class);


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of UserActiveURLController");
		String userId = request.getParameter("activate");
		//Activate the user
		UserDetailsDAO user = new UserDetailsDAO();
		Boolean activated = user.ActivateUser(userId);
		if(activated){
			response.sendRedirect("UserActivationURL");
		}else{
			response.sendRedirect("UserActivationURL?active=false");
		}
		
		logger.info("Exit doGet method of UserActiveURLController");
	}
}
