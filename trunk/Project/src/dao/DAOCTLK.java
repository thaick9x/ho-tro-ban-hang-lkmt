package dao;

import java.sql.*;
import java.util.LinkedList;

public class DAOCTLK extends DAO {
	public LinkedList<String> check(String[] idRow, String[] maLK, String[] maTenLK) throws SQLException {
		LinkedList<String> kq = new LinkedList<>();
		String sql = "select ma_ten_lk, trang_thai from chi_tiet_linh_kien where ma_lk = ?";
		PreparedStatement statement = getConn().prepareStatement(sql);
		for (int i = 0; i < idRow.length; i++) {
			statement.setString(1, maLK[i]);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				if ((result.getString(2).equals("avail") == false) 
						|| (result.getString(1).equals(maTenLK[i]) == false)) {
					kq.add(idRow[i]);
				}
			}
		}
		return kq;
	}
	
	public void changeStatus(String maLK, String status) throws SQLException {
		String sql = "update chi_tiet_linh_kien set trang_thai = ? where ma_lk = ?";
		PreparedStatement statement = getConn().prepareStatement(sql);
		statement.setString(2, maLK);
		statement.setString(1, status);
		statement.executeUpdate();
	}
}
