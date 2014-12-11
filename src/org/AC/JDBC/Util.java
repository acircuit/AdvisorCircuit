package org.AC.JDBC;


import java.sql.*;


public class Util {

	  // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/circuit";
	   
	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "";
	   
	   public static Connection connect() {
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      conn.setAutoCommit(false);
		      System.out.println("Connected database successfully...");
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   return conn;
		   }//end try
		}
}

		
		



