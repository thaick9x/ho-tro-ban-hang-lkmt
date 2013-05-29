package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ItemsInfo;

public class DAOInfo extends DAO {
	
	public ItemsInfo getInfo(String maTenLK) throws Exception {
		DAOXmlTTLK xml = new DAOXmlTTLK();
		
		ItemsInfo kq = new ItemsInfo();
		String sqlStatement = "select * from thong_tin_lk where ma_ten_lk = ?"; 
		
		try {
			connectDB();
		} catch (ClassNotFoundException e) {
			Exception ex = new Exception("Class not found");
			throw ex;
		} catch (SQLException e) {
			Exception ex = new Exception("Sql Exception");
			throw ex;
		}
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, maTenLK);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq.setTenLK(result.getString(1));
			kq.setGiaLK(result.getLong(2));
			kq.setBaoHanh(result.getInt(3));
			kq.setManufactor(result.getString(4));
			kq.setTinhTrang(result.getString(8));
			kq.setSoLuong(result.getInt(9));
			kq.setExtra(xml.getExtraInfo(result.getString(6), String.valueOf(result.getInt(1))));
			kq.setImg(xml.getImgUrl(result.getString(6), String.valueOf(result.getInt(1))));
		}
		return kq;
	}
}
