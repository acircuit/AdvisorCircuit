package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.AC.JDBC.ConnectionFactory;
import org.apache.log4j.Logger;


public class MyDao {

	private static Logger logger= Logger.getLogger(MyDao.class);
	
	public  void getData() throws IOException, SQLException, PropertyVetoException {
		logger.info("Entry to MEthod");
		Connection connection = ConnectionFactory.getConnection();
		System.out.println(connection);
		//just for your test
		for(int i=0;i<=2000;i++)
		{
		    connection = ConnectionFactory.getConnection();
		    connection.setAutoCommit(false);
		    System.out.println(i+". "+connection);
		    connection.commit();
		    connection.close();
		}
		logger.info("Exit to MEthod");
	}
}
