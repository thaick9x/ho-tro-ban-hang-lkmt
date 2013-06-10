package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import dto.ItemsInfo;

public class DAOInfo extends DAO {
	
	private void updateLuotTC(String maTenLK) throws Exception {
		String sqlStatement = "update thong_tin_lk set luot_truy_cap = luot_truy_cap + 1 where ma_ten_lk = ?"; 
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, maTenLK);
		statement.executeUpdate();
	}
	
	public void changeStatus(LinkedList<String> maLK, String status) throws SQLException {
		String sqlStatement = "update chi_tiet_linh_kien set trang_thai = ? where ma_lk = ?"; 
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		for (String s : maLK) {
			statement.setString(1, status);
			statement.setString(2, s);
			statement.executeUpdate();
		}
	}
	
	public ItemsInfo getInfo(String maTenLK) throws Exception {
		DAOXmlTTLK xml = new DAOXmlTTLK();
		
		ItemsInfo kq = new ItemsInfo();
		String sqlStatement = "select * from thong_tin_lk where ma_ten_lk = ?"; 
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, maTenLK);
		updateLuotTC(maTenLK);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq.setTenLK(result.getString(2));
			kq.setGiaLK(misc.Currency.getCurrency(result.getLong(3)));
			kq.setBaoHanh(result.getInt(4));
			kq.setManufactor(result.getString(5));
			kq.setTinhTrang(result.getString(8));
			kq.setSoLuong(result.getInt(9));
			kq.setExtra(xml.getExtraInfo(result.getString(6), maTenLK));
			kq.setImg(xml.getImgUrl(result.getString(6), String.valueOf(result.getInt(1))));
			kq.setMaTenLK(maTenLK);
		}
		return kq;
	}
	
	public LinkedList<String> getNameLK(LinkedList<String> maTenLK) throws SQLException {
		LinkedList<String> kq = new LinkedList<>();
		String sql = "select ten_lk from thong_tin_lk where ma_ten_lk = ?";
		PreparedStatement statement = getConn().prepareStatement(sql);
		for (String s : maTenLK) {
			statement.setString(1, s);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				kq.add(result.getString(1));
			}
		}
		return kq;
	}
	
	public LinkedList<String> getNameXK(LinkedList<String> maLK) {
		LinkedList<String> kq = new LinkedList<>();
		String sql = "select ten_lk from thong_tin_lk T, chi_tiet_linh_kien C where T.ma_ten_lk = C.ma_ten_lk and C.ma_lk = ?";
		PreparedStatement statement;
		try {
			statement = getConn().prepareStatement(sql);
			for (String s : maLK) {
				statement.setString(1, s);
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					kq.add(result.getString(1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}
	public LinkedList<Long> getPrices(LinkedList<String> maLK) throws SQLException {
		LinkedList<Long> kq = new LinkedList<>();
		String sql = "select gia_niem_yet from thong_tin_lk T , chi_tiet_linh_kien L where ma_lk = ? and T.ma_ten_lk = L.ma_ten_lk";
		PreparedStatement statement = getConn().prepareStatement(sql);
		for (String s : maLK) {
			statement.setString(1, s);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				kq.add(result.getLong(1));
			}
		}
		return kq;
	}
}