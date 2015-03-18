package org.AC.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class LogConfigListeners
 *
 */
@WebListener
public class LogConfigListeners implements ServletContextListener {

	public void contextInitialized(ServletContextEvent e) {

		ServletContext ctx = e.getServletContext();
		String prefix =  ctx.getRealPath("/");     
		String file = "WEB-INF"+System.getProperty("file.separator")+"log4j.properties";

		if(file != null) {
			PropertyConfigurator.configure(prefix+file);
			System.out.println("Log4J Logging started for application: " + prefix+file);
		}
	}
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
