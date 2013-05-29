package bean;

import java.util.LinkedList;

public class HomeMenuBean {
	private LinkedList<String> tenLK;
	private LinkedList<Integer> soLuongLK;
	private LinkedList<Long> giaLK;
	private LinkedList<String> imgUrl;
	
	public HomeMenuBean() {
		tenLK = new LinkedList<String>();
		soLuongLK = new LinkedList<Integer>();
		giaLK = new LinkedList<Long>();
		imgUrl = new LinkedList<String>();
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
	public LinkedList<Long> getGiaLK() {
		return giaLK;
	}
	public void setGiaLK(LinkedList<Long> giaLK) {
		this.giaLK = giaLK;
	}
	
}
