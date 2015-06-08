package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdminNotificationDAO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class NotificationViewedController
 */
@WebServlet("/NotificationViewedController")
public class NotificationViewedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(NotificationViewedController.class);         

	// This servlet is used to mark the notification if they have been viewed by the account holder once.

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of NotificationViewedController");
			String id = request.getParameter("id");
			String type = request.getParameter("type");
			String[] ids = id.split(",");
			AdminNotificationDAO notify = new AdminNotificationDAO();
			notify.SetNotificationsViewed(ids,type );
		
		
		logger.info("Exit doGet method of NotificationViewedController");
	}

}
