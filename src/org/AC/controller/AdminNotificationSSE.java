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

import org.AC.DAO.AdminNotificationDAO;
import org.AC.DAO.AdvisorNotificationDAO;
import org.AC.dto.NotificationDTO;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminNotificationSSE
 */
@WebServlet("/AdminNotificationSSE")
public class AdminNotificationSSE extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminNotificationSSE.class);         


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorNotificationSSE");
		Boolean isError = false;
		Boolean isAdmin = false;
		try{
	    isAdmin = (Boolean) request.getSession().getAttribute("admin"); 
		}catch(Exception e){
			isError =true;
			response.sendRedirect("Error");
		}
		if(!isError && isAdmin != null && isAdmin){
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
 
        PrintWriter writer = response.getWriter();
		List<NotificationDTO> notify = new ArrayList<NotificationDTO>();
        AdminNotificationDAO admin = new AdminNotificationDAO();
        notify = admin.GetNotifications();
        String data="<ul>";
        int count = 0;
        for (NotificationDTO notificationDTO : notify) {
        	if(!notificationDTO.getIsPrevious()){
				data = data + "<li style='color:#ffffff;border-bottom: 1px solid #dddddd'> <a href='"+notificationDTO.getHref()+"' ><p align='left' style='margin-bottom: 0px;font-size:16px'>"+notificationDTO.getComment()+"<br><span class='date'>"+new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(notificationDTO.getDate().getTime()))+"</span></p></a></li>";
				count++;
			}else{
				data = data + "<li style='color:#ffffff;border-bottom: 1px solid #dddddd;background:#e7e7e9';> <a href='"+notificationDTO.getHref()+"' ><p align='left' style='margin-bottom: 0px;font-size:16px'>"+notificationDTO.getComment()+"<br><span class='date'>"+new SimpleDateFormat("dd-MMM-yyyy' 'h:mm a").format(new Date(notificationDTO.getDate().getTime()))+"</span></p></a></li>";	
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
