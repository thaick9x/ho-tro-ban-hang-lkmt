package dao;

import java.sql.*;
import java.util.*;

import bean.HomeMenuBean;

public class DAOMainPage extends DAO {
	protected String sqlStatement;
	protected PreparedStatement statement;
	protected HomeMenuBean kq;
	protected LinkedList<Integer> soLuong;
	protected LinkedList<Long> giaLK;
	protected LinkedList<String> tenLK;
	protected LinkedList<String> imgUrl;
	protected DAOXmlTTLK dao;
	
	public HomeMenuBean getItems(int i) throws Exception {
		
		kq = new HomeMenuBean();
		soLuong = new LinkedList<Integer>();
		giaLK = new LinkedList<Long>();
		tenLK = new LinkedList<String>();
		imgUrl = new LinkedList<String>();
		dao = new DAOXmlTTLK();
		
		// kết nối DB
		try {
			connectDB();
		} catch (ClassNotFoundException e) {
			Exception ex = new Exception("Class not found");
			throw ex;
		} catch (SQLException e) {
			Exception ex = new Exception("Sql Exception");
			throw ex;
		}
		
		// Lấy thông tin của 20 linh kiện có lượt truy cập cao nhất để hiện ở trang chủ
		if (i == 1) {
			sqlStatement = "select ten_lk, gia_niem_yet, so_luong, ma_ten_lk, loai_lk from thong_tin_lk order by luot_truy_cap desc limit 20";
		} else {
			sqlStatement = "select L.ten_lk, L.gia_niem_yet, L.so_luong, L.ma_ten_lk, L.loai_lk " +
					"from thong_tin_lk L, chi_tiet_nhap_kho C, thong_tin_nhap_kho T " +
					"where L.ma_ten_lk = C.ma_ten_lk and C.ma_nk = T.ma_nk " +
					"order by T.ngay_nhap desc limit 20";
		}
		try {
			statement = getConn().prepareStatement(sqlStatement);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				soLuong.add(result.getInt(3));
				giaLK.add(result.getLong(2));
				tenLK.add(result.getString(1));
				imgUrl.add(dao.getImgUrl(result.getString(5), result.getString(4)));
			}
			getConn().close();
			// Đưa thông tin của 20 linh kiện chọn ra vào bean HomeMenuBean
			kq.setGiaLK(giaLK);
			kq.setTenLK(tenLK);
			kq.setSoLuongLK(soLuong);
			kq.setImgUrl(imgUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
}
	

