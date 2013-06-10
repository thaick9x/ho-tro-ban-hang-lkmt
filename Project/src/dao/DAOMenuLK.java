package dao;

import java.sql.*;
import java.util.LinkedList;

import bean.Manufactor;
import bean.ManufactorBean;

public class DAOMenuLK extends DAO {
	private PreparedStatement statement;
	private String sqlStatement;
	
	// Hàm lấy ra danh sách các loại linh kiện cửa hàng đang có
	private LinkedList<String> getListLoaiLK() throws Exception {
		LinkedList<String> loaiLK = new LinkedList<String>();
		sqlStatement = "Select loai_lk from loai_lk";
		statement = getConn().prepareStatement(sqlStatement);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			loaiLK.add(result.getString(1).toUpperCase());
		}
		return loaiLK;
	}
	
	// Hàm lấy ra danh sách các nhà sản xuất theo loại linh kiện
	public ManufactorBean getManufactor() throws Exception {
		LinkedList<String> loaiLK = getListLoaiLK();
		ManufactorBean bean = new ManufactorBean();
		
		sqlStatement = "Select nha_san_xuat from thong_tin_lk where loai_lk = ?";
		statement = getConn().prepareStatement(sqlStatement);
		
		for (int i = 0; i < loaiLK.size(); i++) {
			LinkedList<String> nhaSanXuat = new LinkedList<String>();
			statement.setString(1, loaiLK.get(i));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				if (nhaSanXuat.contains(result.getString(1).toUpperCase()) == false) {
					nhaSanXuat.add(result.getString(1).toUpperCase());
				}
			}
			Manufactor  manufactor = new Manufactor(loaiLK.get(i), nhaSanXuat);
			bean.getLoaiLK().add(manufactor);
		}
		return bean;
	}
}
