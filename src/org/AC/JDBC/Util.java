package org.AC.JDBC;

import java.sql.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Util {
	public static Connection connect() {
		Connection conn = null;
		ComboPooledDataSource dataSource;
		try {
			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass("com.mysql.jdbc.Driver"); // loads the
																// jdbc driver
			dataSource.setJdbcUrl("jdbc:mysql://localhost/circuit");
			dataSource.setUser("root");
			dataSource.setPassword("uday");
			dataSource.setAutoCommitOnClose(false);
			dataSource.setMaxIdleTime(5);
			dataSource.setMaxPoolSize(50);
			// the settings below are optional -- c3p0 can work with defaults
			// conn.setMinPoolSize(5);
			// conn.setAcquireIncrement(5);
			// conn.setMaxPoolSize(20);
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			return conn;
		}// end try
	}
}
