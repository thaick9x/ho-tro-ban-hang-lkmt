package dto;

import java.sql.SQLException;
import java.util.LinkedList;

import dao.DAOInfo;

public class CTHD {
	private LinkedList<String> maLK;
	private String maHD;
	private LinkedList<Long> price;
	
	public CTHD() {
		maLK = new LinkedList<String>();
		maHD = "";
		price = new LinkedList<>();
	}
	
	public CTHD(LinkedList<String> maLK, String maHD) throws SQLException {
		super();
		this.maLK = maLK;
		this.maHD = maHD;
		this.price = new DAOInfo().getPrices(maLK);
	}

	public LinkedList<Long> getPrice() {
		return price;
	}

	public void setPrice(LinkedList<Long> price) {
		this.price = price;
	}

	public LinkedList<String> getMaLK() {
		return maLK;
	}
	public void setMaLK(LinkedList<String> maLK) {
		this.maLK = maLK;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	
	
}
