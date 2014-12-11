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
	private static final Logger logger = Logger.getLogger(AdvisorLoginController.class);
	Connection conn = null;
	Statement stmt = null;
	
	 	/**************************************COMMENTS***************************************************
		 * This function will retrieve the login credentials and check for valid credentials.Upon successfull validations
		 *   sets the cookie and session and then redirects it to the MyAccount Page
		 *   
		 *   @return :None
		 *   @param : HttpServletRequest request
		 *   		  HttpServletResponse response
		 *   		  
		 *
		 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		logger.info("Entered doPost method of AdvisorLoginController");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		String userName = "";
		String advisorId ="";
		String loginFromAdvisorProfile = "";
		loginFromAdvisorProfile = (String) request.getParameter("loginFromAdvisorProfile");
		if(loginFromAdvisorProfile != null && loginFromAdvisorProfile != "") {
			advisorId = (String) request.getParameter("aId");
		}
		java.util.List<SearchDTO> list = new ArrayList<SearchDTO>();
			try{
				if(!email.isEmpty() && !password.isEmpty()){
					
					PasswordHashing pass = new PasswordHashing();
					String securedPassword = pass.doHash(password);
					System.out.println(securedPassword);
					if(!securedPassword.isEmpty() && securedPassword != null){
						
						AdvisorLoginDAO dao = new AdvisorLoginDAO();
						ResultSet results = dao.CheckLoginDetails(email, securedPassword);
						if(results.first()){
							int aId = results.getInt("ADVISOR_ID");
							userName = results.getString("NAME");
							HttpSession session=request.getSession();  
					        session.setAttribute("username",userName); 
					        session.setAttribute("advisorId",aId); 
					        Cookie loginCookie = new Cookie("username", userName);
					        loginCookie.setMaxAge(60*60);
					        response.addCookie(loginCookie);
					        if(remember!= null && remember.equals("1")){
					        	Cookie rememberCookie = new Cookie("RememberMe", userName);
					        	rememberCookie.setMaxAge(60*60);
					        	response.addCookie(rememberCookie);	
					        }
					        if(!("").equals(advisorId) && loginFromAdvisorProfile != null && loginFromAdvisorProfile.equals("true")){
					        	//If user has come from profile page then redirect him back to Profile page
					        	String url = "/Tomact_7/AdvisorProfileServlet?aId=" +advisorId ;
					        	response.sendRedirect(url);
					        	
					        }else{
						        request.getSession().setAttribute("isLogin", "advisor");
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/MyAccount.jsp");
						        rd.forward(request, response);
					        }
						}else{
							request.setAttribute("loginverificationfailed","true");
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
					        rd.forward(request, response);
							}
					}
			}else{
				response.sendRedirect("Error.jsp");
			}

				
				logger.info("Exit doPost method of AdvisorLoginController");	
		   		}catch(NoSuchAlgorithmException | NoSuchProviderException e){
		   			logger.error("doPost method of AdvisorLoginController threw error:"+e.getMessage());
					e.printStackTrace();
				}catch(SQLException se){
		   				logger.error("doPost method of AdvisorLoginController threw error:"+se.getMessage());
				      se.printStackTrace();
			   }catch(Exception e){
				   	  logger.error("doPost method of AdvisorLoginController threw error:"+e.getMessage());
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
				    	  logger.error("doPost method of AdvisorLoginController threw error:"+se.getMessage());
				         se.printStackTrace();
				      }//end finally try
				   }//end try
	}
}
