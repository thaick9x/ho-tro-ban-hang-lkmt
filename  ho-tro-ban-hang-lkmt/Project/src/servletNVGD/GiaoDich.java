package servletNVGD;

import java.util.LinkedList;

import dao.DAOCart;
import dao.DAOInfo;
import dao.DAOXK;
import dao.DaoCTHD;
import dao.DaoTTHD;
import dto.CTHD;
import dto.TTHD;

public class GiaoDich {
	public void finishGD(String maNV, String maGio) throws Exception{	
		DaoCTHD cthd = new DaoCTHD();
		DaoTTHD tthd = new DaoTTHD();
		DAOCart cart = new DAOCart();
		DAOInfo info = new DAOInfo();
		DAOXK xk = new DAOXK();
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
	
	private long getTotal(LinkedList<String> maLK) throws Exception {
		DAOXK xk = new DAOXK();
		long kq = 0;
		kq = xk.getTotalPrice(maLK);
		return kq;
	}
}
