package org.AC.Util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class NewMessageByAdminMail {

	private static final Logger logger = Logger.getLogger(NewMessageByAdminMail.class);

	public void SendMail(String name,String email,int advisorId,String advisorMessage){
		
			BasicConfigurator.configure();
			logger.info("Entered SendMail method of NewMessageByAdminMail");
		// Recipient's email ID needs to be mentioned.
	     // String to = "udaykhatry91@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "udaykhatry91@gmail.com";
	      // Assuming you are sending email from localhost
	      String host = "localhost";
	      String mailto = "udaykhatry91@gmail.com";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	     /* properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	      properties.setProperty("port","587");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.password", "batman1234..");
	      properties.put("mail.stmp.user" , "udaykhatry91@gmail.com");*/
	      properties.put("mail.smtp.host", "smtp.gmail.com");
	      properties.put("mail.smtp.socketFactory.port", "465");
	      properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.port", "465");

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties ,new javax.mail.Authenticator(){
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(
	                    "UdayKhatry91@gmail.com", "batman1234..");// Specify the Username and the PassWord
	            }
	    });

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(mailto));

	         // Set Subject: header field
	         message.setSubject("New Message!!!!!");
	         // Now set the actual message
	         message.setText("Message By :" + name);
	         message.setText("Email Id :" + email);
	         message.setContent("<h3>Message By : </h3>" +name+ "<br><h3>Email Id</h3>" +email+ "<br><h3>Advisor Id</h3>" +advisorId+ "<br><h3>Message:</h3>" +advisorMessage, "text/html");
	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	         logger.info("Exit SendMail method of NewMessageByAdminMail");
	      }catch (MessagingException mex) {
				logger.error("SendMail method of NewMessageByAdminMail threw error:"+mex.getMessage());
				mex.printStackTrace();
	      }

	}
	
}
