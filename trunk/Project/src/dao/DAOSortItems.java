package dao;

import java.sql.*;
import java.util.LinkedList;
import misc.Currency;
import bean.HomeMenuBean;

public class DAOSortItems extends DAO{
	protected String sqlStatement;
	protected PreparedStatement statement;
	protected HomeMenuBean kq;
	protected LinkedList<Integer> soLuong;
	protected LinkedList<String> giaLK;
	protected LinkedList<String> tenLK;
	protected LinkedList<String> imgUrl;
	private LinkedList<String> maTenLK;
	protected DAOXmlTTLK dao;
	
	// Tìm các linh kiện theo loại và nhà sản xuất
	public HomeMenuBean getBean (String manufactor, String loaiLK) throws Exception {
		kq = new HomeMenuBean();
		soLuong = new LinkedList<Integer>();
		giaLK = new LinkedList<String>();
		tenLK = new LinkedList<String>();
		imgUrl = new LinkedList<String>();
		maTenLK = new LinkedList<String>();
		dao = new DAOXmlTTLK();

		sqlStatement = "select ten_lk, gia_niem_yet, so_luong, ma_ten_lk, loai_lk from thong_tin_lk where nha_san_xuat = ? and loai_lk = ?";
		try {
			statement = getConn().prepareStatement(sqlStatement);
			statement.setString(1, manufactor);
			statement.setString(2, loaiLK);
			ResultSet result = statement.executeQuery();	
			while (result.next()) {
				soLuong.add(result.getInt(3));
				giaLK.add(Currency.getCurrency(result.getLong(2)));
				tenLK.add(result.getString(1));
				imgUrl.add(dao.getImgUrl(result.getString(5), result.getString(4)));
				maTenLK.add(result.getString(4));
			}
			// đưa các giá trị tìm được vào bean kết quả
			kq.setGiaLK(giaLK);
			kq.setTenLK(tenLK);
			kq.setSoLuongLK(soLuong);
			kq.setImgUrl(imgUrl);
			kq.setMaTenLK(maTenLK);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
}
