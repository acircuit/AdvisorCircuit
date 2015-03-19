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
import org.AC.dto.ContactUsDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminContactUsController
 */
@WebServlet("/AdminContactUsController")
public class AdminContactUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminContactUsController.class); 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdminContactUsController");
		//Getting the Contact us details
		List<ContactUsDTO> contactList = new ArrayList<ContactUsDTO>();
		AdminUserDAO contact = new AdminUserDAO();
		contactList = contact.GetContactUsDetails();
		request.setAttribute("contactList",contactList);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminContactUs.jsp");
        rd.forward(request, response);
		
		
		logger.info("Exit doGet method of AdminContactUsController");
	}
}
