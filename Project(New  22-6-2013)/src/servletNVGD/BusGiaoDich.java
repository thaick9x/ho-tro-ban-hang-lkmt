package servletNVGD;

import java.util.LinkedList;

import dao.DaoGioHang;
import dao.DaoTTLK;
import dao.DaoXK;
import dao.DaoCTHD;
import dao.DaoTTHD;
import dto.CTHD;
import dto.TTHD;

public class BusGiaoDich {
	
	// Hoàn tất giao dịch: thực hiện lưu thông tin hóa đơn, chi tiết hóa đơn
	public void finishGD(String maNV, String maGio) throws Exception{	
		DaoCTHD cthd = new DaoCTHD();
		DaoTTHD tthd = new DaoTTHD();
		DaoGioHang cart = new DaoGioHang();
		DaoTTLK info = new DaoTTLK();
		DaoXK xk = new DaoXK();
		try {
		LinkedList<String> maLK = xk.getMaLK(maGio);
		TTHD ttDto = new TTHD(maGio, cart.getCartInfoGD(maGio).getMaKH(), getTotal(maLK) , maNV);
		tthd.setTTHD(ttDto);
		CTHD ctDto = new CTHD(maLK, tthd.getMaHD(maGio));
		cthd.setCTHD(ctDto);
		cart.changeStatus(ttDto.getMaGio(), "finished");
		info.changeStatus(ctDto.getMaLK(), "sold");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	// Tính giá trị tổng cộng của hóa đơn
	private long getTotal(LinkedList<String> maLK) throws Exception {
		DaoXK xk = new DaoXK();
		long kq = 0;
		kq = xk.getTotalPrice(maLK);
		return kq;
	}
}
