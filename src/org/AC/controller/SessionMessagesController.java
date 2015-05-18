 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 17/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.SessionMssagesDAO;
import org.AC.Util.SendMail;
import org.AC.dto.AdvisorMessageDTO;
import org.AC.dto.FilesDTO;
import org.AC.dto.MsgAndFileDTO;
import org.AC.dto.UserMessageDTO;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class will get the messages sent to or by the advisor.
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class SessionMessagesController
 */
@WebServlet("/SessionMessagesController")
public class SessionMessagesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SessionMessagesController.class); 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of SessionMessagesController");
		String inputMessage = request.getParameter("message");
		String isAdvisor = request.getParameter("advisor");
		String sId = request.getParameter("sId");
		String user = request.getParameter("user");
		String data = "";
		
		Properties prop = new Properties();
	    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	    prop.load(resourceAsStream);
	    
		if(inputMessage == null && sId != null){
			List<UserMessageDTO> usermessages = new ArrayList<UserMessageDTO>();
			List<AdvisorMessageDTO> advisormessages = new ArrayList<AdvisorMessageDTO>();

			if(user == null){
				//Getting Advisor Messages List.
				SessionMssagesDAO advisormessage = new SessionMssagesDAO();
				advisormessages = advisormessage.GetAdvisorMessages(sId ,"advisor");
				
				//Getting User Messages List.
				SessionMssagesDAO message = new SessionMssagesDAO();
				usermessages = message.GetMessages(sId,"advisor");
			}else if (user != null && ("true").equals(user)) {
				SessionMssagesDAO advisormessage = new SessionMssagesDAO();
				advisormessages = advisormessage.GetAdvisorMessages(sId , "user");
				
				//Getting User Messages List.
				SessionMssagesDAO message = new SessionMssagesDAO();
				usermessages = message.GetMessages(sId,"user");
			}
			
			
			
			for (AdvisorMessageDTO advisorMessageDTO : advisormessages) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'HH:mm a");
				if(advisorMessageDTO.getAdvisorMessageTime() != null){
					advisorMessageDTO.setAdvisorMessageTimeString(dateFormat.format(advisorMessageDTO.getAdvisorMessageTime()));
				}
			}
			
			for (UserMessageDTO userMessageDTO : usermessages) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");				
				if(userMessageDTO.getUserMessageTime() != null){
					userMessageDTO.setUserMessageTimeString(dateFormat.format(userMessageDTO.getUserMessageTime()));
				}
			}
			
			
			if((advisormessages.size() > 0 || usermessages.size() > 0)){
	
				List<MsgAndFileDTO> listMsgAndFile = new ArrayList<MsgAndFileDTO>();
				
				if(advisormessages.size() > 0){
					for(AdvisorMessageDTO advisorMessageObj: advisormessages) {
						MsgAndFileDTO msgAndFileDTOObj = new MsgAndFileDTO(advisorMessageObj.getAdvisorMessageTimeString(), advisorMessageObj.getAdvisorMessage(), "text", "advisor", advisorMessageObj.getAdvisorMessageTime(),advisorMessageObj.getStatus());
						listMsgAndFile.add(msgAndFileDTOObj);
					}
				}
				
				if(usermessages.size() > 0){
					for(UserMessageDTO userMessageObj: usermessages) {
						MsgAndFileDTO msgAndFileDTOObj = new MsgAndFileDTO(userMessageObj.getUserMessageTimeString(), userMessageObj.getUserMessage(), "text", "user", userMessageObj.getUserMessageTime(),userMessageObj.getStatus());
						listMsgAndFile.add(msgAndFileDTOObj);
					}
				}
				
				
				// retrieve file list
				SessionMssagesDAO sessionMsgDAO = new SessionMssagesDAO();
				List<FilesDTO> filesDTOList = sessionMsgDAO.GetFilesList(sId);
				
				if (filesDTOList.size() > 0) {
						
					for (FilesDTO fileDTOObj : filesDTOList) {
						
						String fileName = "";
						if(fileDTOObj.getFileURL().contains("\\")){
							fileName = fileDTOObj.getFileURL().split("\\\\")[1];
						}else{
							String fileURL = fileDTOObj.getFileURL();
							fileName = fileDTOObj.getFileURL().substring(fileURL.lastIndexOf("/")+1, fileURL.length());
						}
						
						
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
						if(fileDTOObj.getTime() != null){
							fileDTOObj.setTimeString(dateFormat.format(fileDTOObj.getTime()));
						}
						
						if(("true").equals(isAdvisor)){
							if((("APPROVED").equals(fileDTOObj.getStatus()) && ("USER").equals(fileDTOObj.getUploadedBy())) || ("ADVISOR").equals(fileDTOObj.getUploadedBy())){
								MsgAndFileDTO msgAndFileDTOObj = new MsgAndFileDTO(fileDTOObj.getTimeString(), "<a href='DownloadFile?id="+fileDTOObj.getId()+"'>"+fileName+"</a>", "file", fileDTOObj.getUploadedBy(), fileDTOObj.getTime(),fileDTOObj.getStatus());
								listMsgAndFile.add(msgAndFileDTOObj);
							}
						}else{
							if((("APPROVED").equals(fileDTOObj.getStatus()) && ("ADVISOR").equals(fileDTOObj.getUploadedBy())) || ("USER").equals(fileDTOObj.getUploadedBy())){
								MsgAndFileDTO msgAndFileDTOObj = new MsgAndFileDTO(fileDTOObj.getTimeString(), "<a href='DownloadFile?id="+fileDTOObj.getId()+"'>"+fileName+"</a>", "file", fileDTOObj.getUploadedBy(), fileDTOObj.getTime(),fileDTOObj.getStatus());
								listMsgAndFile.add(msgAndFileDTOObj);
							}				
						}
					}
				}
				
				Collections.sort(listMsgAndFile);
				
				
				data = listMsgAndFile.toString().replace("[", "").replace(",", "").replace("]", "");
			} else{
				data = "You Have No messages";
			}
			
			logger.info(data);

	     	response.setContentType("text/html");  
	        response.setCharacterEncoding("UTF-8"); 
	        response.getWriter().write(data);
		}else if (inputMessage != null && isAdvisor != null && sId != null) {
			if(("true").equals(isAdvisor)){
				Boolean isMessageCommit = false;
				SessionMssagesDAO message = new SessionMssagesDAO();
				isMessageCommit = message.SetMessage(sId, inputMessage,"advisor");
				if(isMessageCommit){
					String subject ="";
					String content ="";
					subject = "New Message From Advisor To User!!!!!";
					content = "Hi, <br><br>Advisor Sent a new message to user for : <br>Session Id  :" +sId+ "<br>Message : " +inputMessage+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
					mail.start();
					response.getWriter().write("true");
				}else{
					response.getWriter().write("false");
				}
			}else{
				Boolean isMessageCommit = false;
				SessionMssagesDAO message = new SessionMssagesDAO();
				isMessageCommit = message.SetMessage(sId, inputMessage,"user");
				if(isMessageCommit){
					String subject ="";
					String content ="";
					subject = "New Message From User To Advisor!!!!!";
					content = "Hi, <br><br>User Sent a new message to advisor for : <br>Session Id  :" +sId+ "<br>Message : " +inputMessage+"<br><img src=\"http://www.advisorcircuit.com/Test/assets/img/logo_black.png\" style='float:right' width='25%'>";
					SendMail mail = new SendMail(subject, content, prop.getProperty("MAIL_ADMIN"),prop.getProperty("MAIL_ADMIN"));
					mail.start();
					response.getWriter().write("true");
				}else{
					response.getWriter().write("false");
				}
				
				
			}
		}
		logger.info("Exit doPost method of SessionMessagesController");
	}
}
