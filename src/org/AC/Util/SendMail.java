package org.AC.Util;

import java.io.IOException;
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

public class SendMail extends Thread{
	private static final Logger logger = Logger.getLogger(SendMail.class);
	
	private String subject;
	private String content;
	private String mailTo;
	private String mailFrom;
	
	public SendMail(String subject, String content, String mailTo ,String mailFrom) {
		super();
		this.subject = subject;
		this.content = content;
		this.mailTo = mailTo;
		this.mailFrom = mailFrom;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public void run(){
		logger.info("Entered run method of SendMail");
	      // Assuming you are sending email from localhost
	      String host = "localhost";
	      try{
	    	  //Retrieving session
	    	  Session session = new MailSession().GetSession();
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(mailFrom,"Advisor Circuit"));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(mailTo));

	         // Set Subject: header field
	         message.setSubject(subject);
	         // Now set the actual message
	         message.setContent(content, "text/html");
	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	         logger.info("Exit run method of SendMail");
	      }catch (MessagingException | IOException mex) {
				logger.error("run method of SendMail threw error:"+mex.getMessage());
				mex.printStackTrace();
	      }
		logger.info("Exit run method of SendMail");
	}
}
