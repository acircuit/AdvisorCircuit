package org.AC.JDBC;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.AC.controller.UserForgotPasswordController;
import org.apache.log4j.Logger;
import org.apache.tomcat.jni.File;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	 private static ComboPooledDataSource comboPooledDataSource;
	 private static final Logger logger = Logger.getLogger(ConnectionFactory.class);

	 private ConnectionFactory() throws IOException, SQLException, PropertyVetoException {
	        Properties prop = new Properties();
	        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/jdbc.properties");
	        prop.load(resourceAsStream);
	        comboPooledDataSource = new ComboPooledDataSource();
	        comboPooledDataSource.setDriverClass(prop.getProperty("MYSQL_DB_DRIVER_CLASS")); 
	        comboPooledDataSource.setJdbcUrl(prop.getProperty("MYSQL_DB_URL"));
	        comboPooledDataSource.setUser(prop.getProperty("MYSQL_DB_USERNAME"));
	        comboPooledDataSource.setPassword(prop.getProperty("MYSQL_DB_PASSWORD"));
	        
	        //DB Tunning : You can set more, read it online
	        comboPooledDataSource.setMinPoolSize(Integer.parseInt(prop.getProperty("CON_POOL_MIN_SIZE")));
	        comboPooledDataSource.setMaxPoolSize(Integer.parseInt(prop.getProperty("CON_POOL_MAX_SIZE")));
	        
	        comboPooledDataSource.setMaxStatements(50);
	        comboPooledDataSource.setAcquireIncrement(5);
	        //comboPooledDataSource.setPreferredTestQuery("select 1;");
	        comboPooledDataSource.setIdleConnectionTestPeriod(300);
	        //comboPooledDataSource.setAcquireRetryAttempts(30);
	        comboPooledDataSource.setMaxIdleTime(300);
	        
	    }

	    public static Connection getConnection() throws SQLException, IOException, PropertyVetoException {
	        if (null==comboPooledDataSource) {
	            System.out.println("Setting up the connection pooling 1st time");
	            new ConnectionFactory();
				} 
			return comboPooledDataSource.getConnection();		
	    }
}
