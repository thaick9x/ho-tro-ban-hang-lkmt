package dao;

import java.sql.*;
import java.util.LinkedList;

import bean.HomeMenuBean;

public class DAOSortItems extends DAO{
	protected String sqlStatement;
	protected PreparedStatement statement;
	protected HomeMenuBean kq;
	protected LinkedList<Integer> soLuong;
	protected LinkedList<Long> giaLK;
	protected LinkedList<String> tenLK;
	protected LinkedList<String> imgUrl;
	protected DAOXmlTTLK dao;
	
	// Tìm các linh kiện theo loại và nhà sản xuất
	private HomeMenuBean getBean (String manufactor, String loaiLK) throws Exception {
		kq = new HomeMenuBean();
		soLuong = new LinkedList<Integer>();
		giaLK = new LinkedList<Long>();
		tenLK = new LinkedList<String>();
		imgUrl = new LinkedList<String>();
		dao = new DAOXmlTTLK();
		try {
			connectDB();
		} catch (ClassNotFoundException e) {
			Exception ex = new Exception("Class not found");
			throw ex;
		} catch (SQLException e) {
			Exception ex = new Exception("Sql Exception");
			throw ex;
		}
		sqlStatement = "select ten_lk, gia_niem_yet, so_luong, ma_ten_lk, loai_lk from thong_tin_lk where nha_san_xuat = ? and loai_lk = ?";
		try {
			statement = getConn().prepareStatement(sqlStatement);
			statement.setString(1, manufactor);
			statement.setString(2, loaiLK);
			ResultSet result = statement.executeQuery();	
			while (result.next()) {
				soLuong.add(result.getInt(3));
				giaLK.add(result.getLong(2));
				tenLK.add(result.getString(1));
				imgUrl.add(dao.getImgUrl(result.getString(5), result.getString(4)));
			}
			getConn().close();
			// đưa các giá trị tìm được vào bean kết quả
			kq.setGiaLK(giaLK);
			kq.setTenLK(tenLK);
			kq.setSoLuongLK(soLuong);
			kq.setImgUrl(imgUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	// Trả về các div chứa các linh kiện tìm theo nhà sản xuất và loại linh kiện
	public String getItems(String manufactor, String loaiLK) {
		String kq = "";
		try {
			HomeMenuBean bean = getBean(manufactor, loaiLK);
			LinkedList<String> tenLK = bean.getTenLK();
    		LinkedList<Long> giaLK = bean.getGiaLK();
    		LinkedList<Integer> soLuong = bean.getSoLuongLK(); 
    		LinkedList<String> imgUrl = bean.getImgUrl();
    		
    		String status = "";		
    		for (int i = 0; i < tenLK.size(); i++) {
				if (soLuong.get(i) == 0) {
					status = "Hết hàng.";
				}
    			kq += "<div id=\"linhKien\" style=\"height:215px;width:215px;float:left\">" + 
    					"<img src=\""+ imgUrl.get(i) + "\"height=\"42\" width=\"42\"/>" +
    					"<p>" + tenLK.get(i) + "</p>" + 
    					"<p>Giá: " + giaLK.get(i) + "</p>" + status + "</div>";
			}	
    		System.out.println(kq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
}
