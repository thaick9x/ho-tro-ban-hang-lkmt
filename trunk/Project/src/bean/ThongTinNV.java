package bean;

public class ThongTinNV {
	private String nameNV;
	private String maNV;
	private String quyen;
	
	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String viTri) {
		this.quyen = viTri;
	}

	public String getNameNV() {
		return nameNV;
	}
	
	public ThongTinNV(String nameNV, String maNV) {
		super();
		this.nameNV = nameNV;
		this.maNV = maNV;
	}
	
	public ThongTinNV() {
		super();
		nameNV = "";
		maNV = "";
	}
	
	public void setNameNV(String nameNV) {
		this.nameNV = nameNV;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	
}
