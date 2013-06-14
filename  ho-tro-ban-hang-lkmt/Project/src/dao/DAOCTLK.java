package dao;

import java.sql.*;
import java.util.LinkedList;

/*
 * Class chứa các hàm liên quan tới bảng chi_tiet_linh_kien
 */

public class DaoCTLK extends DAO {
	
	// Kiểm tra trạng thái và mã linh kiện đúng với tên của nó 
	public LinkedList<String> check(String[] idRow, String[] maLK, String[] maTenLK) throws SQLException {
		LinkedList<String> kq = new LinkedList<>();
		LinkedList<String> idLK = new LinkedList<>();
		String sql = "select ma_ten_lk, trang_thai, ma_lk from chi_tiet_linh_kien where ma_lk = ?";
		PreparedStatement statement = getConn().prepareStatement(sql);
		for (int i = 0; i < idRow.length; i++) {
			statement.setString(1, maLK[i]);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				idLK.add(result.getString(3));
				if ((result.getString(2).equals("avail") == false) 
						|| (result.getString(1).equals(maTenLK[i]) == false)) {
					kq.add(idRow[i]);
				}
			}
		}
		for (int j = 0; j < maLK.length; j++) {
			if (idLK.contains(maLK[j]) == false) {
				kq.add(idRow[j]);
			}
		}
		return kq;
	}
	
	// Đổi trạng thái của linh kiện
	public void changeStatus(String maLK, String status) throws SQLException {
		String sql = "update chi_tiet_linh_kien set trang_thai = ? where ma_lk = ?";
		PreparedStatement statement = getConn().prepareStatement(sql);
		statement.setString(2, maLK);
		statement.setString(1, status);
		statement.executeUpdate();
	}
}
