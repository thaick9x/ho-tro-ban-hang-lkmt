package dto;

import java.util.LinkedList;


public class InfoXK extends CartInfo {
	private String total;
	private LinkedList<String> maLK;
	
	public InfoXK() {
		total = "";
		maLK = new LinkedList<>();
	}
	
	public LinkedList<String> getMaLK() {
		return maLK;
	}

	public void setMaLK(LinkedList<String> maLK) {
		this.maLK = maLK;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
}
