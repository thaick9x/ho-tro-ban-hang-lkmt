package bean;

import java.util.LinkedList;
/*
 * Class gồm danh sách các mã giỏ hàng đang chờ hoàn tất giao dịch
 * Sử dụng ở NVGD.jsp
 */

public class DsGioGD {
	private LinkedList<String> list;
	
	public DsGioGD() {
		list = new LinkedList<>();
	}

	public LinkedList<String> getList() {
		return list;
	}

	public void setList(LinkedList<String> list) {
		this.list = list;
	}
	
}
