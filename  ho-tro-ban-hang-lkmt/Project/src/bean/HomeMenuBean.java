package bean;

import java.util.LinkedList;

/*
 * Hiện thông tin cơ bản của các linh kiện cho trang chủ
 * Dùng cho Home.jsp
 */

public class HomeMenuBean {
	private LinkedList<String> tenLK;
	private LinkedList<Integer> soLuongLK;
	private LinkedList<String> giaLK;
	private LinkedList<String> imgUrl;
	private LinkedList<String> maTenLK;
	
	public LinkedList<String> getMaTenLK() {
		return maTenLK;
	}

	public void setMaTenLK(LinkedList<String> maTenLK) {
		this.maTenLK = maTenLK;
	}

	public HomeMenuBean() {
		tenLK = new LinkedList<String>();
		soLuongLK = new LinkedList<Integer>();
		giaLK = new LinkedList<String>();
		imgUrl = new LinkedList<String>();
		maTenLK = new LinkedList<>();
	}
	
	public LinkedList<String> getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(LinkedList<String> imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LinkedList<String> getTenLK() {
		return tenLK;
	}
	public void setTenLK(LinkedList<String> tenLK) {
		this.tenLK = tenLK;
	}
	public LinkedList<Integer> getSoLuongLK() {
		return soLuongLK;
	}
	public void setSoLuongLK(LinkedList<Integer> soLuongLK) {
		this.soLuongLK = soLuongLK;
	}
	public LinkedList<String> getGiaLK() {
		return giaLK;
	}
	public void setGiaLK(LinkedList<String> giaLK) {
		this.giaLK = giaLK;
	}
	
}
