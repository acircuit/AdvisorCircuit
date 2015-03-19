package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Servlet implementation class WordFileOpenerSample
 */
@WebServlet("/WordFileOpenerSample")
public class WordFileOpenerSample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(WordFileOpenerSample.class);     
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of WordFileOpenerSample");

		   String path = request.getParameter("path");
		   try {
		     if (Desktop.isDesktopSupported()) {
		       Desktop.getDesktop().open(new File(path));
		       response.getWriter().write("<script>window.close();</script>");
		     }
		   } catch (IOException ioe) {
				logger.error("doGet method of WordFileOpenerSample threw error:"+ioe.getMessage());
		     ioe.printStackTrace();
		  }
			logger.info("Entered doGet method of WordFileOpenerSample");
	}
}
