 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 19/12/2014
 * ************************************************************************************************/
package org.AC.controller;
/********************************CLASS SUMMARY*****************************************************
* 
* This class will set the file and the comment in the file table
* 
*
***************************************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.SessionMssagesDAO;
import org.AC.DAO.UserNotificationDAO;
import org.AC.Util.SendMail;
import org.AC.Util.SetFile;
import org.AC.dto.FilesDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class SessionFileController
 */
@WebServlet("/SessionFileController")
@MultipartConfig
public class SessionFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SessionFileController.class); 


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of SessionFileController");
		String sId = request.getParameter("sId");
		String purpose = request.getParameter("purpose");
		String getFile = (String)request.getParameter("getFile");
		String isAdvisor = (String)request.getParameter("advisor");
		String fromUser = (String) request.getParameter("fromUser");
		
		List<FilesDTO> files = new ArrayList<FilesDTO>();
		Properties prop1 = new Properties();
	    InputStream resourceAsStream1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop1.load(resourceAsStream1);
		
	    if(sId != null && getFile == null){
			Properties prop = new Properties();
		    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/Path.properties");
		    prop.load(resourceAsStream);
			String path = prop.getProperty("SESSION_FILE_PATH");
			SetFile file = new SetFile();
			String fileURL = file.PutFile(request, response, sId,path);
			if(!("").equals(fileURL)){
				SessionMssagesDAO setFile = new SessionMssagesDAO();
				Boolean isFileCommit = setFile.SetFiles(sId, fileURL, purpose,fromUser);
				if(isFileCommit){
					 if(("true").equals(fromUser)){
						 int[] ids = new int[3];
							//GETTING THE USERID, ADVISORID, AND REQUEST ID
							UserNotificationDAO id = new UserNotificationDAO();
							ids = id.GetAdvisorId(sId);
							
							//Getting username
							AdminNotificationDAO name = new AdminNotificationDAO();
							String uName = name.GetUserName(String.valueOf(ids[0]));
							
							//Getting advisor name
							AdminNotificationDAO aName = new AdminNotificationDAO();
							String advisorName = aName.GetAdvisorName(String.valueOf(ids[1]));
							
							//Notify Admin
							String comment = uName+" sent a message/file to "+advisorName;
							String href = "AdminUserAdvisorMessages?sessionid="+sId;
							AdminNotificationDAO admin = new AdminNotificationDAO();
							admin.InsertNotification(comment, href);
					 }else{
						 int[] ids = new int[3];
							//GETTING THE USERID, ADVISORID, AND REQUEST ID
							UserNotificationDAO id = new UserNotificationDAO();
							ids = id.GetAdvisorId(sId);
							
							//Getting username
							AdminNotificationDAO name = new AdminNotificationDAO();
							String uName = name.GetUserName(String.valueOf(ids[0]));
							
							//Getting advisor name
							AdminNotificationDAO aName = new AdminNotificationDAO();
							String advisorName = aName.GetAdvisorName(String.valueOf(ids[1]));
							
							//Notify Admin
							String comment = advisorName+" sent a message/file to "+uName;
							String href = "AdminUserAdvisorMessages?sessionid="+sId;
							AdminNotificationDAO admin = new AdminNotificationDAO();
							admin.InsertNotification(comment, href);
					 }
					
					 String subject ="";
					 String content ="";
					 if(("true").equals(fromUser)){
						 subject = "New File Uploaded by User!!!!!";
						 content = "Hi, <br><br> A new File has been uploaded by user for : <br>Session Id  : " +sId+ " <br>Uploaded By : USER"+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
			         }else{
			        	 subject = "New File Uploaded by Advisor!!!!!";
			        	 content = "Hi, <br><br> A new File has been uploaded by Advisor for : <br>Session Id  : " +sId+ " <br>Uploaded By : ADVISOR"+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>"; 
			         }
					SendMail mail = new SendMail(subject, content, prop1.getProperty("MAIL_ADMIN"),prop1.getProperty("MAIL_ADMIN"));
					mail.start();
					response.getWriter().write("Your File has been Uploaded");
				}
			}
		}else if (("true").equals(getFile)) {
			String data = "";
			SessionMssagesDAO getFiles = new SessionMssagesDAO();
			files = getFiles.GetFilesList(sId);
			for (FilesDTO filesDTO : files) {
				//Time 
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
				if(filesDTO.getTime() != null){
					filesDTO.setTimeString(dateFormat.format(filesDTO.getTime()));
				}
				//FileName
				String fileURL = filesDTO.getFileURL();
				String fileName = fileURL.substring(fileURL.lastIndexOf("/")+1, fileURL.length());
				//Href for the html
				String href = "DownloadFile?id="+filesDTO.getId();
				if(("true").equals(isAdvisor)){
					if(("APPROVED").equals(filesDTO.getStatus()) && ("USER").equals(filesDTO.getUploadedBy())){
						String str = "<h4>File By User:<a href="+href+">"+fileName+"</a>:"+filesDTO.getTimeString()+"</h4><br>Message:"+filesDTO.getComment()+"</br>";
						data =data.concat(str);
					}
					if(("ADVISOR").equals(filesDTO.getUploadedBy())){
						String str = "<h4>File By Advisor:<a href="+href+">"+fileName+"</a>:"+filesDTO.getTimeString()+"</h4><br><h4>Message:"+filesDTO.getComment()+"</h4></br>";
						data = data.concat(str);
					}
				}else{
					if(("USER").equals(filesDTO.getUploadedBy())){
						String str = "<h4>File By User:<a href="+href+">"+fileName+"</a>:"+filesDTO.getTimeString()+"</h4><br>Message:"+filesDTO.getComment()+"</br>";
						data =data.concat(str);
					}
					if(("ADVISOR").equals(filesDTO.getUploadedBy()) && ("APPROVED").equals(filesDTO.getStatus())){
						String str = "<h4>File By Advisor:<a href="+href+">"+fileName+"</a>:"+filesDTO.getTimeString()+"</h4><br><h4>Message:"+filesDTO.getComment()+"</h4></br>";
						data = data.concat(str);
					}				
				}
			}
			if(("").equals(data)){
				data = "No Files Uploaded Yet";
			}
			response.getWriter().write(data);
		}
		logger.info("Exit doPost method of SessionFileController");
	}
}