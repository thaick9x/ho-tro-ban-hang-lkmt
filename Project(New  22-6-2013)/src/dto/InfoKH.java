package dto;

public class InfoKH {
	private String name;
	private String sdt;
	private String address;
	public String getName() {
		return name;
	}
	public InfoKH() {
		name = "";
		sdt = "";
		address = "";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
