package bean;

import java.util.LinkedList;

public class ManufactorBean {
	private LinkedList<Manufactor> loaiLK;
	
	public ManufactorBean() {
		loaiLK = new LinkedList<Manufactor>();
	}

	public LinkedList<Manufactor> getLoaiLK() {
		return loaiLK;
	}

	public void setLoaiLK(LinkedList<Manufactor> loaiLK) {
		this.loaiLK = loaiLK;
	}
	
	
}
