package bean;

import java.util.LinkedList;

/*
 * Class danh sách các giỏ hàng đã được nhận từ khách hàng
 * Sử dụng ở NVGD.jsp
 */

public class DsGioHangBean {
	private LinkedList<String> listID;
	
	public DsGioHangBean() {
		listID = new LinkedList<>();
	}

	public LinkedList<String> getListID() {
		return listID;
	}

	public void setListID(LinkedList<String> cart) {
		this.listID = cart;
	}
	
}
