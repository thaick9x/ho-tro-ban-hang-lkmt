package dao;

import java.sql.*;

import dto.DangNhap;

public class DaoDangNhap extends DAO {
	private String sql;
	private PreparedStatement statement;
	
	public DangNhap getThongTinDN(String user) throws Exception {
		DangNhap kq = new DangNhap();
		
		sql = "select user_id, user, pass, maNhomQuyen from thong_tin_dang_nhap where user = ?";
		
		statement = getConn().prepareStatement(sql);
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq.setUserID(result.getString(1));
			kq.setUser(result.getString(2));
			kq.setPass(result.getString(3));
			kq.setQuyen(result.getString(4));
		}
		return kq;
	}
	
	public boolean checkUserExits(String user) throws Exception {
		boolean kq = false;
		
		sql = "select user from thong_tin_dang_nhap";
		statement = getConn().prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while (result.next()) {
			if (result.getString(1).equals(user)) {
				kq = true;
				break;
			}
		}
		return kq;
	}
}
