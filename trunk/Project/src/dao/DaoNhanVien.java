package dao;

import java.sql.*;

import bean.ThongTinNV;


public class DaoNhanVien extends DAO {
	private String sql;
	private PreparedStatement statement;
	
	public ThongTinNV getThongTinNV(String userID) throws SQLException {
		ThongTinNV info = new ThongTinNV();
		
		sql = "select ma_nv, ten_nv from thong_tin_nhan_vien, nhom where user_id = ?";
		statement = getConn().prepareStatement(sql);
		statement.setString(1, userID);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			info.setNameNV(result.getString(2)); 
			info.setMaNV(result.getString(1));
		}
		return info;
	}
}
