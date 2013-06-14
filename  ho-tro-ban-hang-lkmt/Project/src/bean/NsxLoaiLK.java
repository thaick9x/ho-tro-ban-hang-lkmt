package bean;

import java.util.LinkedList;

/*
 * Class dùng lưu loại linh kiện cùng hãng sản xuất tương ứng có ở cửa hàng
 */

public class NsxLoaiLK {
	private LinkedList<String> manufactor;
	private String loaiLK;
	
	public NsxLoaiLK() {
		manufactor = new LinkedList<String>();
		loaiLK = "";
	}
	public NsxLoaiLK(String loaiLK, LinkedList<String> manufactor) {
		this.loaiLK = loaiLK;
		this.manufactor = manufactor;
	}

	public LinkedList<String> getManufactor() {
		return manufactor;
	}

	public void setManufactor(LinkedList<String> manufactor) {
		this.manufactor = manufactor;
	}

	public String getLoaiLK() {
		return loaiLK;
	}

	public void setLoaiLK(String loaiLK) {
		this.loaiLK = loaiLK;
	}
	
	
}
