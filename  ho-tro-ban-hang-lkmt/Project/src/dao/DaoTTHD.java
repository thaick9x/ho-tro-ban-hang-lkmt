package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Class chứa các hàm linh quan tới bảng thong_tin_hoa_don
 */

public class DaoTTHD extends DAO {
	
	// Lưu thông tin hóa đơn
	public void setTTHD(dto.TTHD dto) throws Exception {
		String sql = "insert into thong_tin_hd (ngay_lap_hd, don_gia, ma_nv, ma_kh, ma_gio) values (?, ?, ?, ?, ?)";
		PreparedStatement statement = getConn().prepareStatement(sql);
		statement.setDate(1, dto.getDate());
		statement.setLong(2, dto.getTotal());
		statement.setString(3, dto.getMaNV());
		statement.setString(4, dto.getMaKH());
		statement.setString(5, dto.getMaGio());
		statement.executeUpdate();
	}
	
	//Lấy mã hóa đơn theo mã giỏ hàng
	public String getMaHD(String maGio) throws SQLException {
		String kq = "";
		String sql = "select ma_hd from thong_tin_hd where ma_gio = ?";
		PreparedStatement statement = getConn().prepareStatement(sql);
		statement.setString(1, maGio);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq = result.getString(1);
		}
		return kq;
	}
}
