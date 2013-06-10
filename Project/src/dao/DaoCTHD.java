package dao;

import java.sql.*;
import java.util.LinkedList;

public class DaoCTHD extends DAO {
	public void setCTHD(dto.CTHD dto) throws SQLException {
		String sql = "insert into chi_tiet_hd (ma_lk, ma_hd, gia_trong_hd) values (?, ?, ?)";
		PreparedStatement statement = getConn().prepareStatement(sql);
		LinkedList<String> maLK = dto.getMaLK();
		LinkedList<Long> price = dto.getPrice();
		
		for (int i = 0; i < price.size(); i++) {
			statement.setString(2, dto.getMaHD());
			statement.setString(1, maLK.get(i));
			statement.setLong(3, price.get(i));
			statement.executeUpdate();
		}
	}
}
