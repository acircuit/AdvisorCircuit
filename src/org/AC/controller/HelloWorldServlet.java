package org.AC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(HelloWorldServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.info("Started Serving Do get");
		org.AC.DAO.MyDao myDao = new org.AC.DAO.MyDao();
		try {
			myDao.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.getWriter().write("Hello World");

		logger.info("Ended Serving Do get");
	}
}
