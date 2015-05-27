package org.AC.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorNotificationDAO;
import org.AC.DAO.UserNotificationDAO;
import org.AC.dto.NotificationDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdvisorNotificationSSE
 */
@WebServlet("/AdvisorNotificationSSE")
public class AdvisorNotificationSSE extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorNotificationSSE.class);         

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorNotificationSSE");
		Boolean isError = false;
		int advisorId = 0;
		try{
	    advisorId = (int) request.getSession().getAttribute("advisorId");
		}catch(Exception e){
			isError =true;
			response.sendRedirect("Error");
		}
		if(!isError){
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
 
        PrintWriter writer = response.getWriter();
		List<NotificationDTO> notify = new ArrayList<NotificationDTO>();
        AdvisorNotificationDAO user = new AdvisorNotificationDAO();
        notify = user.GetNotification(advisorId);
        String data="<ul>";
        int count = 0;
        for (NotificationDTO notificationDTO : notify) {
			data = data + "<ul><li><a href='"+notificationDTO.getHref()+"'>"+notificationDTO.getComment()+"</a><span class='date'>"+new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(notificationDTO.getDate().getTime()))+"</span><hr/></li></ul>";
			if(!notificationDTO.getIsPrevious()){
				count++;
			}
		}
        data= data + "</ul>"; 
        writer.write("event:notify\n");
        writer.write("data: " + data + "\n\n");
        writer.write("event:count\n");
        writer.write("data: " + count + "\n\n");
        writer.flush();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writer.close();
		}
      	logger.info("Exit doGet method of AdvisorNotificationSSE");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
