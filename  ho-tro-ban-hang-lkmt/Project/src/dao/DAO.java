package dao;

import java.sql.*;


public class DAO {
	private String driver;
	private String connectString;
	private Connection conn;
	
	public Connection getConn() {
		return conn;
	}
	
	protected void connectDB() throws ClassNotFoundException, SQLException {
		driver = "com.mysql.jdbc.Driver";
		connectString = "jdbc:mysql://127.0.0.1:3306/mydb";
		Class.forName(driver);
		conn = DriverManager.getConnection(connectString, "root" , "");
	}
}
