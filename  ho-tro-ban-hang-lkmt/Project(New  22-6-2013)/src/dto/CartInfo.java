package dto;

import java.util.LinkedList;


public class CartInfo {
	private String maGio;
	private String maKH;
	private String status;
	private LinkedList<String> maTenLK;
	private LinkedList<Integer> soLuong;
	private InfoKH info;
	private LinkedList<String> tenLK;
	private String quay;

	public CartInfo() {
		maTenLK = new LinkedList<>();
		soLuong = new LinkedList<>();
		maGio = "";
		maKH = "";
		status = "";
		info = new InfoKH();
		tenLK = new LinkedList<>();
		quay = "";
	}
	
	public String getQuay() {
		return quay;
	}

	public void setQuay(String quay) {
		this.quay = quay;
	}

	public LinkedList<String> getTenLK() {
		return tenLK;
	}

	public void setTenLK(LinkedList<String> tenLK) {
		this.tenLK = tenLK;
	}

	public InfoKH getInfo() {
		return info;
	}

	public void setInfo(InfoKH info) {
		this.info = info;
	}

	public LinkedList<String> getMaTenLK() {
		return maTenLK;
	}

	public void setMaTenLK(LinkedList<String> maTenLK) {
		this.maTenLK = maTenLK;
	}

	public LinkedList<Integer> getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(LinkedList<Integer> soLuong) {
		this.soLuong = soLuong;
	}

	public String getMaGio() {
		return maGio;
	}
	public void setMaGio(String maGio) {
		this.maGio = maGio;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}
