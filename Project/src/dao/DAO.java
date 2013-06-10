package dao;

import java.sql.*;

public class DAO {
	private String driver;
	private String connectString;
	private Connection conn;
	
	public DAO() {
		driver = "com.mysql.jdbc.Driver";
		connectString = "jdbc:mysql://127.0.0.1:3306/mydb";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(connectString, "root" , "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		return conn;
	}
	/*
	protected void connectDB() throws ClassNotFoundException, SQLException {
		driver = "com.mysql.jdbc.Driver";
		connectString = "jdbc:mysql://127.0.0.1:3306/mydb";
		Class.forName(driver);
		conn = DriverManager.getConnection(connectString, "root" , "");
	}*/
}
