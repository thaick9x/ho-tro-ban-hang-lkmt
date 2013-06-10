package dao;

import java.sql.*;
import java.util.LinkedList;

import dto.CartInfo;
import dto.InfoKH;
import dto.InfoShowCart;
import dto.ItemsInCart;
import bean.KhoBean;
import bean.ListCartBean;
import bean.ReadyCartsBean;

public class DAOCart extends DAO {
	// Tìm tên linh kiện, giá niêm yết và mã tên linh kiện của 1 linh kiện để dùng cho giỏ hàng
	private InfoShowCart getItemsInfoCart(String maTenLK) throws Exception {
		String sqlStatement = "select ten_lk, gia_niem_yet from thong_tin_lk where ma_ten_lk = ?";
		InfoShowCart kq = new InfoShowCart();
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, maTenLK);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq.setMaTenLK(maTenLK);
			kq.setTenLK(result.getString(1));
			kq.setGiaLK(result.getLong(2));
		}
		return kq;
	}
	// Lấy thông tin của toàn bộ linh kiện có trong giỏ hàng 
	public ItemsInCart getCartItemsInfo(String[] maTenLK) throws Exception {
		ItemsInCart kq = new ItemsInCart();
		LinkedList<InfoShowCart> items = new LinkedList<>();
		
		for (String s : maTenLK) {
			items.add(getItemsInfoCart(s));
		}
		kq.setItems(items);
		return kq;
	}
	// Tìm mã giỏ theo mã khách hàng
	public String getMaGio(String maKH) throws Exception {
		String sqlStatement = "select ma_gio from thong_tin_gio_hang where ma_kh = ?";
		
		String kq = "";
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, maKH);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq = result.getString(1);
		}
		return kq;
	}
	// Tính số tiền tổng cộng mà khách hàng phải trả cho giỏ hàng chưa thuế
	public long calcuTotal(String[] id, int[] amount) throws Exception {
		long kq = 0;
		ItemsInCart items = getCartItemsInfo(id);
		for (int i = 0; i < id.length; i++) {
			
			kq += items.getItems().get(i).getGiaLK() * amount[i];
		}
		return kq;
	}
	// Hiện số tiền tổng cộng có dấu "," phân cách
	// Kiểm tra lượng hàng 
	public LinkedList<String> checkCart(String[] id, int[] amount) throws Exception {
		String sqlStatement = "select so_luong from thong_tin_lk where ma_ten_lk = ?";
		
		LinkedList<String> kq = new LinkedList<>();
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		for (int i = 0; i < id.length; i++) {
			statement.setString(1, id[i]);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				if (amount[i] > result.getInt(1)) {
					kq.add(id[i]);
				}
			}
		}
		return kq;
	}
	// Lưu chi tiết giỏ hàng
	public void saveCTGH(String[] id, int[] amount, String maGio) throws Exception {
		String sqlStatement = "insert into chi_tiet_gio_hang (ma_gio, ma_ten_lk, so_luong_dat) values (?,?,?)";
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, maGio);
		for (int i = 0; i < id.length; i++) {
			statement.setString(2, id[i]);
			statement.setInt(3, amount[i]);
			statement.executeUpdate();
		}
	}
	// Lưu thông tin giỏ hàng
	public void saveTTGH(InfoKH info, String[] id, int[] amount) throws Exception {
		DAOKhachHang kh = new DAOKhachHang();
		String sqlStatement = "insert into thong_tin_gio_hang (ma_kh, trang_thai) values (?, 'wait')";
		
		try {
			String maKH = kh.getMaKH(info.getSdt());
			PreparedStatement statement = getConn().prepareStatement(sqlStatement);
			statement.setString(1, maKH);
			statement.executeUpdate();
		} catch (Exception ex) {
			if (ex.getMessage().equals("not found")) {
				kh.saveInfoKH(info);
				saveTTGH(info, id, amount);
			} else {
				System.out.println(ex.getMessage());
			}
		}
	}
	// Thực hiện lưu thông tin, chi tiết giỏ hàng
	public void receiveCart(InfoKH info, String[] id, int[] amount) throws Exception {
		DAOKhachHang kh = new DAOKhachHang();
		saveTTGH(info, id, amount);
		saveCTGH(id, amount, getMaGio(kh.getMaKH(info.getSdt())));
	}
	// xuất danh sách các giỏ hàng đang chờ xuất kho
	public ListCartBean getListCart() throws Exception {
		ListCartBean kq = new ListCartBean();
		String sqlStatement = "select ma_gio, trang_thai from thong_tin_gio_hang";

		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			if (result.getString(2).equals("wait")) {
				kq.getListID().add(result.getString(1));
			}
		}
		return kq;
	}
	// Hiện thông tin giỏ hàng cho nhân viên giao dịch
	public CartInfo getCartInfoGD(String maGio) throws Exception {
		DAOKhachHang dao = new DAOKhachHang();
		CartInfo kq = new CartInfo();
		String sqlStatement = "select * from thong_tin_gio_hang where ma_gio = ?";
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, maGio);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq.setMaGio(maGio);
			kq.setMaKH(result.getString(2));
			kq.setStatus(result.getString(3));
		}
		getCTCart(kq);
		kq.setInfo(dao.getInfoKH(kq.getMaKH()));
		kq.setTenLK(new DAOInfo().getNameLK(kq.getMaTenLK()));
		return kq;
	}
	// Hiện thông tin giỏ hàng cho nhân viên kho
	public KhoBean getCartInfoKho(LinkedList<String> maGio) throws Exception {
		KhoBean bean = new KhoBean();	
		String sqlStatement = "select * from thong_tin_gio_hang where ma_gio = ?";
		for (String s : maGio) {
			PreparedStatement statement = getConn().prepareStatement(sqlStatement);
			statement.setString(1, s);
			CartInfo kq = new CartInfo();
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				
				kq.setMaGio(s);
				kq.setQuay(result.getString(4));
				getCTCart(kq);
				kq.setTenLK(new DAOInfo().getNameLK(kq.getMaTenLK()));
			}
		bean.getCart().add(kq);
		}
		return bean;
	}
	// Lấy chi tiết giỏ hàng
	public void getCTCart(CartInfo cart) throws Exception {
		String sqlStatement = "select ma_ten_lk, so_luong_dat from chi_tiet_gio_hang where ma_gio = ?"; 

		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, cart.getMaGio());
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			cart.getMaTenLK().add(result.getString(1));
			cart.getSoLuong().add(result.getInt(2));
		}
	}
	// Đổi trạng thái giỏ hàng từ chờ xử lý thành xuất kho
	public void changeStatusNVGD(String maGio, String quay) throws Exception {
		String sql = "update thong_tin_gio_hang set trang_thai = 'export', quay_xl = ? where ma_gio = ?";
		PreparedStatement statement = getConn().prepareStatement(sql);
		statement.setString(2, maGio);
		statement.setString(1, quay);
		statement.executeUpdate();
	}
	
	public void changeStatus(String maGio, String status) throws Exception {
		String sql = "update thong_tin_gio_hang set trang_thai = ? where ma_gio = ?";
		PreparedStatement statement = getConn().prepareStatement(sql);
		statement.setString(2, maGio);
		statement.setString(1, status);
		statement.executeUpdate();
	}
	// Xuất danh sách mã giỏ đã được yêu cầu xuất kho
	public LinkedList<String> getExportCart() throws SQLException {
		LinkedList<String> kq = new LinkedList<>();
		String sqlStatement = "select ma_gio from thong_tin_gio_hang where trang_thai = 'export' limit 3"; 
		PreparedStatement statement = getConn().prepareStatement(sqlStatement);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kq.add(result.getString(1));
		}
		return kq;
	}
	public ReadyCartsBean getReadyCarts(String quay) throws Exception {
		ReadyCartsBean kq = new ReadyCartsBean();
		String sql = "select ma_gio from thong_tin_gio_hang where trang_thai = 'ready'";
		PreparedStatement statement = getConn().prepareStatement(sql);
		//statement.setString(1, quay);
		ResultSet result =  statement.executeQuery();
		
		while (result.next()) {
			kq.getList().add(result.getString(1));
		}
		return kq;
	}
}
