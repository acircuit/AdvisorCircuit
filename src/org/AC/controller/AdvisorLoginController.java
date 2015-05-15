/*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/

package org.AC.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.AC.DAO.AdvisorLoginDAO;
import org.AC.DAO.UserLoginDAO;
import org.AC.JDBC.Util;
import org.AC.Util.PasswordHashing;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SearchDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
 * 
 * This class will check for the authentication of the user and redirects to MAccount Page
 * 
 * 
 *
 ***************************************************************************************************/
/**
 * Servlet implementation class AdvisorLoginController
 */
@WebServlet("/AdvisorLoginController")
public class AdvisorLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(AdvisorLoginController.class);
	Connection conn = null;
	Statement stmt = null;

	/**************************************
	 * COMMENTS*************************************************** This function
	 * will retrieve the login credentials and check for valid credentials.Upon
	 * successfull validations sets the cookie and session and then redirects it
	 * to the MyAccount Page
	 * 
	 * @return :None
	 * @param : HttpServletRequest request HttpServletResponse response
	 * 
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of AdvisorLoginController");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		String userName = "";
		String advisorId = "";
		String fromProfile = "";
		String loginFromAdvisorProfile = "";
		loginFromAdvisorProfile = (String) request
				.getParameter("loginFromAdvisorProfile");
		if (loginFromAdvisorProfile != null && loginFromAdvisorProfile != "") {
			advisorId = (String) request.getParameter("aId");
			request.setAttribute("loginverificationfailedfromprofile", true);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/Login.jsp");
			rd.forward(request, response);
		} else {
			java.util.List<SearchDTO> list = new ArrayList<SearchDTO>();
			try {
				if (!email.isEmpty() && !password.isEmpty()) {

					PasswordHashing pass = new PasswordHashing();
					String securedPassword = pass.doHash(password);
					System.out.println(securedPassword);
					if (!securedPassword.isEmpty() && securedPassword != null) {
						AdvisorProfileDTO advisor = new AdvisorProfileDTO();
						AdvisorLoginDAO dao = new AdvisorLoginDAO();
						advisor = dao.CheckLoginDetails(email, securedPassword);
						if (advisor.getAdvisorId() != 0
								&& advisor.getName() != null) {
							int aId = advisor.getAdvisorId();
							userName = advisor.getName();
							HttpSession session = request.getSession();
							session.setAttribute("username", userName);
							session.setAttribute("advisorId", aId);
							Cookie loginCookie = new Cookie("username",
									userName);
							loginCookie.setMaxAge(60 * 60);
							response.addCookie(loginCookie);
							if (remember != null && remember.equals("1")) {
								Cookie rememberCookie = new Cookie(
										"RememberMe", userName);
								rememberCookie.setMaxAge(60 * 60);
								response.addCookie(rememberCookie);
							}
							request.getSession().setAttribute("isLogin",
									"advisor");
							response.sendRedirect("myaccount");
						} else {
							request.setAttribute("loginverificationfailed",
									"true");
							RequestDispatcher rd = getServletContext()
									.getRequestDispatcher("/login");
							rd.forward(request, response);
						}
					}
				} else {
					response.sendRedirect("Error");
				}

				logger.info("Exit doPost method of AdvisorLoginController");
			} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
				logger.error("doPost method of AdvisorLoginController threw error:"
						+ e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("doPost method of AdvisorLoginController threw error:"
						+ e.getMessage());
				e.printStackTrace();
			} finally {
				// finally block used to close resources
				try {
					if (stmt != null)
						conn.close();
				} catch (SQLException se) {
				}// do nothing
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					logger.error("doPost method of AdvisorLoginController threw error:"
							+ se.getMessage());
					se.printStackTrace();
				}// end finally try
			}// end try
		}
	}

}
