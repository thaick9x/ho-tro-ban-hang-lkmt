package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import misc.Currency;
import bean.HomeMenuBean;
import dto.ItemsInfo;

/*
 * Class chứa các hàm liên quan tới bảng Thong_tin_lk
 */

public class DaoTTLK extends DAO {
	
	private String sql;
	
	// Truyền data từ result set vào HomeMenuBean
	private HomeMenuBean transferData(ResultSet result) throws Exception {
		HomeMenuBean kq = new HomeMenuBean();;
		LinkedList<Integer> soLuong = new LinkedList<Integer>();;
		LinkedList<String> giaLK = new LinkedList<String>();
		LinkedList<String> tenLK = new LinkedList<String>();
		LinkedList<String> imgUrl = new LinkedList<String>();
		LinkedList<String> maTenLK = new LinkedList<>();
		DaoXmlTTLK dao = new DaoXmlTTLK();
		
		while (result.next()) {
			soLuong.add(result.getInt(3));
			giaLK.add(Currency.getCurrency(result.getLong(2)));
			tenLK.add(result.getString(1));
			imgUrl.add(dao.getImgUrl(result.getString(5), result.getString(4)));
			maTenLK.add(result.getString(4));
		}
		// Đưa thông tin của 20 linh kiện chọn ra vào bean HomeMenuBean
		kq.setGiaLK(giaLK);
		kq.setTenLK(tenLK);
		kq.setSoLuongLK(soLuong);
		kq.setImgUrl(imgUrl);
		kq.setMaTenLK(maTenLK);
		
		return kq;
	}
 	
	// lấy ra các linh kiện
	public HomeMenuBean getItems(int i) throws Exception {	
		sql = "";	
		HomeMenuBean kq = new HomeMenuBean();;
			
		// Lấy thông tin của 20 linh kiện có lượt truy cập cao nhất để hiện ở trang chủ
		if (i == 1) {
			sql = "select ten_lk, gia_niem_yet, so_luong, ma_ten_lk, loai_lk from thong_tin_lk order by luot_truy_cap desc limit 20";
		} else {
			// Lấy thông tin của 20 linh kiện được nhập mới nhất
			sql = "select L.ten_lk, L.gia_niem_yet, L.so_luong, L.ma_ten_lk, L.loai_lk " +
					"from thong_tin_lk L, chi_tiet_nhap_kho C, thong_tin_nhap_kho T " +
					"where L.ma_ten_lk = C.ma_ten_lk and C.ma_nk = T.ma_nk " +
					"order by T.ngay_nhap desc limit 20";
		}
		try {
			PreparedStatement statement = getConn().prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			kq = transferData(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	// Tìm linh kiện theo tên linh kiện
	public HomeMenuBean searchName(String name) throws Exception {
		sql = "select ten_lk, gia_niem_yet, so_luong, ma_ten_lk, loai_lk from thong_tin_lk where ten_lk like ?";
		HomeMenuBean kq = new HomeMenuBean();;
		try {
			PreparedStatement statement = getConn().prepareStatement(sql);
			statement.setString(1, "%" + name + "%");
			ResultSet result = statement.executeQuery();
			kq = transferData(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	// Tìm các linh kiện theo loại và nhà sản xuất
	public HomeMenuBean sortItems(String manufactor, String loaiLK) throws Exception {
			sql = "select ten_lk, gia_niem_yet, so_luong, ma_ten_lk, loai_lk from thong_tin_lk where nha_san_xuat = ? and loai_lk = ?";
			HomeMenuBean kq = new HomeMenuBean();
			
			try {
				PreparedStatement statement = getConn().prepareStatement(sql);
				statement.setString(1, manufactor);
				statement.setString(2, loaiLK);
				ResultSet result = statement.executeQuery();	
				kq = transferData(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return kq;
		}
		
	// Tìm các linh kiện theo loại linh kiện
	public HomeMenuBean sortItems(String loaiLK) throws Exception {
			sql = "select ten_lk, gia_niem_yet, so_luong, ma_ten_lk, loai_lk from thong_tin_lk where loai_lk = ?";
			HomeMenuBean kq = new HomeMenuBean();
			
			try {
				PreparedStatement statement = getConn().prepareStatement(sql);
				statement.setString(1, loaiLK);
				ResultSet result = statement.executeQuery();
				kq = transferData(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return kq;
		}
	
	// Tăng lượt truy cập lên 1 mỗi khi thông tin của linh kiện đó được xem
	private void updateLuotTC(String maTenLK) throws Exception {
		sql = "update thong_tin_lk set luot_truy_cap = luot_truy_cap + 1 where ma_ten_lk = ?"; 
		PreparedStatement statement = getConn().prepareStatement(sql);
		statement.setString(1, maTenLK);
		statement.executeUpdate();
	}
	
	// Đổi trạng thái cho 1 loạt các linh kiện
	public void changeStatus(LinkedList<String> maLK, String status) throws SQLException {
		sql = "update chi_tiet_linh_kien set trang_thai = ? where ma_lk = ?"; 
		PreparedStatement statement = getConn().prepareStatement(sql);
		for (String s : maLK) {
			statement.setString(1, status);
			statement.setString(2, s);
			statement.executeUpdate();
		}
	}
	
	// Lấy thông tin đầy đủ của linh kiện theo mã tên linh kiện
	public ItemsInfo getInfo(String maTenLK) throws Exception {
		DaoXmlTTLK xml = new DaoXmlTTLK();
		ItemsInfo kq = new ItemsInfo();
		sql = "select * from thong_tin_lk where ma_ten_lk = ?"; 
		PreparedStatement statement = getConn().prepareStatement(sql);
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
	
	// Lấy tên của linh kiện theo mã tên linh kiện
	public LinkedList<String> getNameLK(LinkedList<String> maTenLK) throws SQLException {
		LinkedList<String> kq = new LinkedList<>();
		sql = "select ten_lk from thong_tin_lk where ma_ten_lk = ?";
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
	
	// Lấy tên linh kiện theo mã linh kiện
	public LinkedList<String> getNameXK(LinkedList<String> maLK) {
		LinkedList<String> kq = new LinkedList<>();
		sql = "select ten_lk from thong_tin_lk T, chi_tiet_linh_kien C where T.ma_ten_lk = C.ma_ten_lk and C.ma_lk = ?";
		try {
			PreparedStatement statement = getConn().prepareStatement(sql);
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
	
	// Trả về mảng giá niêm yết theo mã linh kiện
	public LinkedList<Long> getPrices(LinkedList<String> maLK) throws SQLException {
		LinkedList<Long> kq = new LinkedList<>();
		sql = "select gia_niem_yet from thong_tin_lk T , chi_tiet_linh_kien L where ma_lk = ? and T.ma_ten_lk = L.ma_ten_lk";
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