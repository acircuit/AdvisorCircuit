package org.AC.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class UserSSENotificationController {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserSSENotificationController.class);   
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		logger.info("Entered doGet method of UserSSENotificationController");

        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
 
        PrintWriter writer = response.getWriter();
        int upVote = 0;
        int downVote = 0;
        for (int i = 0; i < 20; i++) {
 
            upVote = upVote + (int) (Math.random() * 10);
            downVote = downVote + (int) (Math.random() * 10);
 
            writer.write("event:up_vote\n");
            writer.write("data: " + upVote + "\n\n");
 
            writer.write("event:down_vote\n");
            writer.write("data: " + downVote + "\n\n");
 
            writer.flush();
             
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writer.close();
		logger.info("Exit doGet method of UserSSENotificationController");
    }
}
