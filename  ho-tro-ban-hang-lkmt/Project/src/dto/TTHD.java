package dto;

public class TTHD {
	private String maGio; 
	private String maKH;
	private long total; 
	private String maNV;
	java.sql.Date date;
	
	public TTHD() {
		maGio = "";
		maKH = "";
		total = 0;
		maNV = "";
		date = new java.sql.Date(new java.util.Date().getTime());
	}
	
	public TTHD(String maGio, String maKH, long total, String maNV) {
		super();
		this.maGio = maGio;
		this.maKH = maKH;
		this.total = total;
		this.maNV = maNV;
		date = new java.sql.Date(new java.util.Date().getTime());
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
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
}
