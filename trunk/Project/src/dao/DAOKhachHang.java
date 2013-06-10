package dao;

import java.sql.*;

import dto.InfoKH;


public class DAOKhachHang extends DAO {
	
	public String getMaKH(String sdt) throws Exception {
		String sqlStatement = "select ma_kh from thong_tin_khach_hang where sdt_kh = ?"; 
		String kq = "";
		boolean found = false;
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, sdt);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq = result.getString(1);
			found = true;
		}
		if (found == false) {
			Exception e = new Exception("not found");
			throw e;
		}
		return kq;
	}
	
	public void saveInfoKH(InfoKH info) throws Exception {
		String sqlStatement = "insert into thong_tin_khach_hang (ten_KH, dia_chi_kh, sdt_kh) values (?,?,?)"; 
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, info.getName());
		statement.setString(2, info.getAddress());
		statement.setString(3, info.getSdt());
		statement.executeUpdate();
	}
	
	public InfoKH getInfoKH(String maKH) throws Exception {
		String sqlStatement = "select ten_kh, dia_chi_kh, sdt_kh from thong_tin_khach_hang where ma_kh = ?"; 
		InfoKH kq = new InfoKH();
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, maKH);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq.setName(result.getString(1));
			kq.setAddress(result.getString(2));
			kq.setSdt(result.getString(3));
		}
		return kq;
	}
}
