package dao;

import java.sql.*;

/*
 * Class tạo kết nối tới cơ sở dữ liệu
 */

public class DAO {
	private String driver; // Tên driver
	private String connectString; // Chuỗi kết nối
	private Connection conn; // Kết nối tới CSDL
	public DAO() {
		driver = "org.gjt.mm.mysql.Driver";
		connectString = "jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&characterEncoding=UTF-8";
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
}
