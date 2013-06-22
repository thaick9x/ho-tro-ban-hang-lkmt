package dto;

import java.util.LinkedList;


public class ItemsInfo {
	private String tenLK;
	private String giaLK;
	private int soLuong;
	private int baoHanh;
	private String manufactor;
	private String tinhTrang;
	private String img;
	private LinkedList<ExtraInfo> extra;
	private String maTenLK;
	
	public ItemsInfo() {
		tenLK = "";
		giaLK = "";
		soLuong = 0;
		baoHanh = 0;
		manufactor = "";
		tinhTrang = "";
		img = "";
		maTenLK = "";
		extra = new LinkedList<>();
	}
	
	public String getMaTenLK() {
		return maTenLK;
	}

	public void setMaTenLK(String maTenLK) {
		this.maTenLK = maTenLK;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTenLK() {
		return tenLK;
	}
	
	public int getBaoHanh() {
		return baoHanh;
	}

	public void setBaoHanh(int baoHanh) {
		this.baoHanh = baoHanh;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public void setExtra(LinkedList<ExtraInfo> extra) {
		this.extra = extra;
	}

	public void setTenLK(String tenLK) {
		this.tenLK = tenLK;
	}
	
	public String getGiaLK() {
		return giaLK;
	}
	
	public void setGiaLK(String giaLK) {
		this.giaLK = giaLK;
	}
	
	public int getSoLuong() {
		return soLuong;
	}
	
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public LinkedList<ExtraInfo> getExtra() {
		return extra;
	}
}
