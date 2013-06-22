package dao;

import java.sql.*;
import java.util.LinkedList;

import misc.Currency;

import com.google.gson.*;

import dto.InfoXK;

public class DaoXK extends DAO {	
	// xuát giá linh kiện theo mã linh kiện
	public long getTotalPrice(LinkedList<String> maLK) throws Exception {
		String sql = "select gia_niem_yet from thong_tin_lk T , chi_tiet_linh_kien L where ma_lk = ? and T.ma_ten_lk = L.ma_ten_lk";
		PreparedStatement statement = getConn().prepareStatement(sql);
		long total = 0;
		for (String s : maLK) {
			statement.setString(1, s);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				total += result.getLong(1);
			}
		}
		return total;
	}
	
	// lưu thông tin xuất kho
	public LinkedList<String> setInfoXK(String maGio,String row, String id, String tenLK) throws Exception {
		DaoCTLK dao = new DaoCTLK();
		DaoGioHang cart = new DaoGioHang();
		Gson json = new Gson();
		String[] rowID = json.fromJson(row, String[].class);
		String[] maLK = json.fromJson(id, String[].class);
		String[] maTenLK = json.fromJson(tenLK, String[].class);
		LinkedList<String> kq = dao.check(rowID, maLK, maTenLK);
		if (kq.size() == 0) {
			cart.changeStatus(maGio, "ready");
			for (String s : maLK) {
				dao.changeStatus(s, "ready");
				String sql = "insert into thong_tin_xuat_kho (ngay_xk, ma_gio, ma_lk) values (?,?,?)";
				PreparedStatement statement = getConn().prepareStatement(sql);
				statement.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
				statement.setString(2, maGio);
				statement.setString(3, s);
				statement.executeUpdate();
			}
		}
		return kq;
	}
	
	// Tìm các mã linh kiện theo giỏ
	public LinkedList<String> getMaLK(String maGio) throws SQLException {
		LinkedList<String> kq = new LinkedList<>();
		String sql = "select ma_lk from thong_tin_xuat_kho where ma_gio = ?";
		PreparedStatement statement = getConn().prepareStatement(sql);
		statement.setString(1, maGio);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq.add(result.getString(1));
		}
		return kq;
	}
	
	// Lấy thông tin từ bảng thong_tin_xuat_kho
	public InfoXK getInfoXK(String maGio) throws Exception{
		DaoKhachHang dao = new DaoKhachHang();
		InfoXK kq = new InfoXK();
		String sqlStatement = "select * from thong_tin_gio_hang where ma_gio = ?";
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		
		statement.setString(1, maGio);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq.setMaGio(maGio);
			kq.setMaKH(result.getString(2));
			kq.setStatus(result.getString(3));
		}
		kq.setMaLK(getMaLK(maGio));
		kq.setTotal(Currency.getCurrency(getTotalPrice(kq.getMaLK())));
		kq.setInfo(dao.getInfoKH(kq.getMaKH()));
		kq.setTenLK(new DaoTTLK().getNameXK(kq.getMaLK()));
		return kq;
	}
}
