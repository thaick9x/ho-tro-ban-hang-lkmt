package bean;

import java.util.LinkedList;

public class Manufactor {
	private LinkedList<String> manufactor;
	private String loaiLK;
	
	public Manufactor() {
		manufactor = new LinkedList<String>();
		loaiLK = "";
	}
	public Manufactor(String loaiLK, LinkedList<String> manufactor) {
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
