package org.AC.controller;

import java.io.IOException;
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

import org.AC.DAO.UserLoginDAO;
import org.AC.JDBC.Util;
import org.AC.Util.PasswordHashing;
import org.AC.dto.SearchDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserLoginController
 */
@WebServlet("/UserLoginController")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorLoginController.class);
	Connection conn = null;
	Statement stmt = null;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of UserLoginController");
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		String loginFromAdvisorProfile = "";
		loginFromAdvisorProfile = (String) request.getParameter("loginFromAdvisorProfile");
		String advisorId ="";
		try{
			if(loginFromAdvisorProfile != null && loginFromAdvisorProfile != "") {
				advisorId = (String) request.getParameter("aId");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
		}

		java.util.List<SearchDTO> list = new ArrayList<SearchDTO>();
			try{
				if(!username.isEmpty() && username != null  && !password.isEmpty() &&  password != null){
					
						PasswordHashing pass = new PasswordHashing();
						String securedPassword = pass.doHash(password);
						if(!securedPassword.isEmpty() && securedPassword != null){
							
							
							UserLoginDAO dao = new UserLoginDAO();
							ResultSet results = dao.CheckLoginDetails(username, securedPassword);
							if(results.first()){
								int uId = results.getInt("USER_ID");
								HttpSession session=request.getSession();  
						        session.setAttribute("username",username); 
						        session.setAttribute("userId",uId); 
						        Cookie loginCookie = new Cookie("username", username);
						        loginCookie.setMaxAge(60*60);
						        response.addCookie(loginCookie);
						        if(remember!= null && remember.equals("1")){
						        	Cookie rememberCookie = new Cookie("RememberMe", username);
						        	rememberCookie.setMaxAge(60*60);
						        	//response.addCookie(rememberCookie);	
						        }
						        if(!("").equals(advisorId) && loginFromAdvisorProfile != null && loginFromAdvisorProfile.equals("true")){
						        	//If user has come from profile page then redirect him back to Profile page
						        	String url = "/Tomact_7/AdvisorProfileServlet?aId=" +advisorId ;
						        	response.sendRedirect(url);
						        	
						        }else{
						        	request.getSession().setAttribute("isLogin", "user");
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/MyAccount.jsp");
							        rd.forward(request, response);
						        }
							}else{
								request.setAttribute("loginverificationfailed","true");
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
						        rd.forward(request, response);
								}
						}
				}
				logger.info("Exit doPost method of UserLoginController");	
		   		}catch(NoSuchAlgorithmException | NoSuchProviderException e){
		   			logger.error("doPost method of UserLoginController threw error:"+e.getMessage());
					e.printStackTrace();
				}catch(SQLException se){
		   				logger.error("doPost method of UserLoginController threw error:"+se.getMessage());
				      se.printStackTrace();
			   }catch(Exception e){
				   	  logger.error("doPost method of UserLoginController threw error:"+e.getMessage());
				      e.printStackTrace();
			   }finally{
				      //finally block used to close resources
				      try{
				         if(stmt!=null)
				            conn.close();
				      }catch(SQLException se){
				      }// do nothing
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				    	  logger.error("doPost method of UserLoginController threw error:"+se.getMessage());
				         se.printStackTrace();
				      }//end finally try
				   }//end try
	}
}
