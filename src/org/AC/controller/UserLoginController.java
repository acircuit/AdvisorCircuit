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
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class UserLoginController
 */
@WebServlet("/UserLoginController")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserLoginController.class);
	Connection conn = null;
	Statement stmt = null;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of UserLoginController");
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		String advisorId = request.getParameter("aId");
		String fromProfile = request.getParameter("profile");
		String loginFromAdvisorProfile = "";
		loginFromAdvisorProfile = (String) request.getParameter("loginFromAdvisorProfile");
		String userId ="";
		try{
			if(loginFromAdvisorProfile != null && loginFromAdvisorProfile != "") {
				userId = (String) request.getParameter("aId");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("Error");
		}

		java.util.List<SearchDTO> list = new ArrayList<SearchDTO>();
		UserDetailsDTO user = new UserDetailsDTO();

			try{
				if(!username.isEmpty() && username != null  && !password.isEmpty() &&  password != null){
					
						PasswordHashing pass = new PasswordHashing();
						String securedPassword = pass.doHash(password);
						if(!securedPassword.isEmpty() && securedPassword != null){
							
							
							UserLoginDAO dao = new UserLoginDAO();
							user= dao.CheckLoginDetails(username, securedPassword);
							if(user.getUserId() != 0 && user.getFullName() != null){
								int uId = user.getUserId();
								String userName = user.getFullName();
								HttpSession session=request.getSession();  
						        session.setAttribute("username",userName); 
						        session.setAttribute("userId",uId); 
						        Cookie loginCookie = new Cookie("username", username);
						        loginCookie.setMaxAge(60*60);
						        response.addCookie(loginCookie);
						        if(remember!= null && remember.equals("1")){
						        	Cookie rememberCookie = new Cookie("RememberMe", username);
						        	rememberCookie.setMaxAge(60*60);
						        	//response.addCookie(rememberCookie);	
						        }
						        if(!("").equals(userId) && loginFromAdvisorProfile != null && loginFromAdvisorProfile.equals("true")){
						        	//If user has come from profile page then redirect him back to Profile page
						        	String url = "AdvisorProfile?aId=" +userId ;
						        	response.sendRedirect(url);
						        	
						        }else{
						        	request.getSession().setAttribute("isLogin", "user");
						        	response.sendRedirect("usermyaccount");
									
						        }
							}else{
									if(advisorId!= null && fromProfile!= null){
										request.setAttribute("loginverificationfailed","true");
										RequestDispatcher rd = getServletContext().getRequestDispatcher("/login");
								        rd.forward(request, response);										
									}else{
										request.setAttribute("loginverificationfailed","true");
										RequestDispatcher rd = getServletContext().getRequestDispatcher("/login");
								        rd.forward(request, response);
									}
								}
						}
				}
				logger.info("Exit doPost method of UserLoginController");	
		   		}catch(NoSuchAlgorithmException | NoSuchProviderException e){
		   			logger.error("doPost method of UserLoginController threw error:"+e.getMessage());
					e.printStackTrace();
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
