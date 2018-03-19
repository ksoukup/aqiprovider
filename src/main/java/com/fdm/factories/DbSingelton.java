package com.fdm.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.runner.Client;;

public class DbSingelton {
	private static String db_url;
	private static String db_user;
	private static String db_password;
	private static Connection conn;
	private static Logger logger;

	private DbSingelton() {
		/* 
		 *Default database parameters 
		 */
		logger = LogManager.getLogger(DbSingelton.class);

		db_url = "jdbc:oracle:thin:@localhost:1521:XE";
		db_user = "Trainee1";
		db_password = "!QAZSE4";
		/* Creation of an instance of the connection statement*/
		setConnection();
	}
	/* Private method charge to set the connection statement*/
	private static void setConnection() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			String url = "" + db_url;
			conn = DriverManager.getConnection(url, db_user, db_password);
		} catch (SQLException ex) {
			logger.error("Unable to set connection", ex);
		}
	}

	/* Private inner class responsible for instantiating the single instance of the singleton */
	private static class DbSingeltonManagerHolder {
		private final static DbSingelton instance = new DbSingelton();
	}

	/**
	 * @return
Public method, which is the only method allowed to return an instance of 
the singleton (the instance here is the database connection statement)
	 */
	public static DbSingelton getInstance() {
		try {
			return DbSingeltonManagerHolder.instance;
		} catch (ExceptionInInitializerError ex) {
			logger.error("Unable to get Instance", ex);
			return null;
		}

	}
	
	public Statement getStatement() {
		try {
			return conn.createStatement();
		} catch (SQLException ex) {
			logger.error("Unable to create statement", ex);
			return null;
		}

	}
	
	public PreparedStatement getPreparedStatement(String query){
		try {
			return conn.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}