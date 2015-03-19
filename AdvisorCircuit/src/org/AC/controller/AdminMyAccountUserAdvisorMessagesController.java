 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 20/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.SessionMssagesDAO;
import org.AC.dto.AdvisorMessageDTO;
import org.AC.dto.FilesDTO;
import org.AC.dto.UserMessageDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch all the messages between user and admin for a particular session Id along with the files.
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdminMyAccountUserAdvisorMessagesController
 */
@WebServlet("/AdminMyAccountUserAdvisorMessagesController")
public class AdminMyAccountUserAdvisorMessagesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminMyAccountUserAdvisorMessagesController.class);     

    
	/**************************************COMMENTS***************************************************
	 * This class will fetch all the messages between user and admin for a particular session Id along with the files.
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdminMyAccountUserAdvisorMessagesController");
		String sId = request.getParameter("sessionid");
		if(sId != null){
		//Get all User Messages for this session Id.
			List<UserMessageDTO> usermessages = new ArrayList<UserMessageDTO>();
			SessionMssagesDAO userMessage = new SessionMssagesDAO();
			usermessages = userMessage.GetMessages(sId);
			for (UserMessageDTO userMessageDTO : usermessages) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
				if(userMessageDTO.getUserMessageTime() != null){
					userMessageDTO.setUserMessageTimeString(dateFormat.format(userMessageDTO.getUserMessageTime()));
				}
			}
		//Get all Advisor Messages for this session Id.
			List<AdvisorMessageDTO> advisormessages = new ArrayList<AdvisorMessageDTO>();
			SessionMssagesDAO advisorMessage = new SessionMssagesDAO();
			advisormessages = advisorMessage.GetAdvisorMessages(sId);
			for (AdvisorMessageDTO advisorMessageDTO : advisormessages) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
				if(advisorMessageDTO.getAdvisorMessageTime() != null){
					advisorMessageDTO.setAdvisorMessageTimeString(dateFormat.format(advisorMessageDTO.getAdvisorMessageTime()));
				}
			}
		//Get All the Files Uploaded For this session Id.
			Properties prop = new Properties();
	         InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/Path.properties");
	         try {
				prop.load(resourceAsStream);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<FilesDTO> files = new ArrayList<FilesDTO>();
			SessionMssagesDAO file = new SessionMssagesDAO();
			files = file.GetFilesList(sId);
			for (FilesDTO filesDTO : files) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a");
				if(filesDTO.getTime() != null){
					filesDTO.setTimeString(dateFormat.format(filesDTO.getTime()));
				}
				String fileURL = filesDTO.getFileURL();
				String fileName = fileURL.substring(fileURL.lastIndexOf(prop.getProperty("FILE_NAME_SUBSTRING_LAST_INDEX"))+1, fileURL.length());
				String href = "DownloadFile?id="+filesDTO.getId();
				filesDTO.setFileName(fileName);
				filesDTO.setHref(href);
			}
			
			request.setAttribute("usermessages", usermessages);
			request.setAttribute("advisormessages", advisormessages);
			request.setAttribute("files", files);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdminMessagesAndFiles.jsp");
	        rd.forward(request, response);
		}
		logger.info("Exit doGet method of AdminMyAccountUserAdvisorMessagesController");
	}

}
