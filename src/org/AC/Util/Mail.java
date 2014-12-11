package org.AC.Util;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


public class Mail {
	private static final Logger logger = Logger.getLogger(Mail.class);

	public Boolean SendMailToAdvisor(String mailto,int advisorId){
		
			BasicConfigurator.configure();
			logger.info("Entered SendMail method of Mail");
		// Recipient's email ID needs to be mentioned.
	     // String to = "udaykhatry91@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "udaykhatry91@gmail.com";
	      String url = "http://localhost:7080/AC/ForgotPassword?cgid="+advisorId;

	      // Assuming you are sending email from localhost
	      String host = "localhost";
	      Boolean isMailSent = false;

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      /*properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	      properties.setProperty("port","587");
	     // properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.password", "batman1234..");
	      properties.put("mail.stmp.user" , "udaykhatry91@gmail.com");
	      */
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
	         message.setSubject("This is the Subject Line!");
	         // Now set the actual message
	         message.setText("This is actual message");
	         message.setContent("<a href= '"+url+"'>"+url+"</a>", "text/html");

	         // Send message
	         Transport.send(message);
	         isMailSent = true;
	         System.out.println("Sent message successfully....");
	         logger.info("Exit SendMail method of Mail");
	      }catch (MessagingException mex) {
				logger.error("SendMail method of Mail threw error:"+mex.getMessage());
				mex.printStackTrace();
	      }
		return isMailSent;

	}
	
	public Boolean SendMailToUser(String mailto,int userId){
		
		BasicConfigurator.configure();
		logger.info("Entered SendMail method of Mail");
	// Recipient's email ID needs to be mentioned.
     // String to = "udaykhatry91@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "udaykhatry91@gmail.com";
      String url = "http://localhost:7080/AC/ForgotPasswordUser?cgid="+userId;

      // Assuming you are sending email from localhost
      String host = "localhost";
      Boolean isMailSent = false;

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      /*properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
      properties.setProperty("port","587");
     // properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.password", "batman1234..");
      properties.put("mail.stmp.user" , "udaykhatry91@gmail.com");
      */
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
         message.setSubject("This is the Subject Line!");
         // Now set the actual message
         message.setText("This is actual message");
         message.setContent("<a href= '"+url+"'>"+url+"</a>", "text/html");

         // Send message
         Transport.send(message);
         isMailSent = true;
         System.out.println("Sent message successfully....");
         logger.info("Exit SendMail method of Mail");
      }catch (MessagingException mex) {
			logger.error("SendMail method of Mail threw error:"+mex.getMessage());
			mex.printStackTrace();
      }
	return isMailSent;
	}
}

