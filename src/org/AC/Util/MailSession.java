package org.AC.Util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class MailSession {
	
	private static Properties properties;
	
	public MailSession() throws IOException {
		  Properties prop = new Properties();
	      java.io.InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	      prop.load(resourceAsStream);
		 // Get system properties
	      properties = System.getProperties();
	      properties.put(prop.getProperty("JAVA_MAIL_HOST_LABEL"),prop.getProperty("JAVA_MAIL_HOST") );
	      properties.put(prop.getProperty("JAVA_MAIL_SOCKET_FACTORY_PORT_LABEL"),prop.getProperty("JAVA_MAIL_PORT"));
	      properties.put(prop.getProperty("JAVA_MAIL_SOCKET_FACTORY_CLASS_LABEL"),prop.getProperty("JAVA_MAIL_SOCKET_FACTORY_CLASS"));
	      properties.put(prop.getProperty("JAVA_MAIL_AUTH_LABEL"), prop.getProperty("JAVA_MAIL_TRUE"));
	      properties.put(prop.getProperty("JAVA_MAIL_PORT_LABEL"), prop.getProperty("JAVA_MAIL_PORT"));
	      properties.put(prop.getProperty("JAVA_MAIL_STARTTLS_LABEL"),prop.getProperty("JAVA_MAIL_TRUE"));
	}

	public Session GetSession() throws IOException{
		  final Properties prop1 = new Properties();
	      java.io.InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/mail.properties");
	      prop1.load(resourceAsStream);
		  new MailSession();
	      Session session = Session.getDefaultInstance(properties ,new javax.mail.Authenticator(){
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(
	                		prop1.getProperty("MAIL_ADMIN"), prop1.getProperty("MAIL_ADMIN_PASS"));// Specify the Username and the PassWord
	            }
	    });
		return session;
	}	
}
