package bean;

import java.util.LinkedList;

/*
 * Class chứa tất cả các loại linh kiện cùng hãng sản xuất 
 * Dùng ở Home.jsp
 */

public class NsxLoaiLKBean {
	private LinkedList<NsxLoaiLK> loaiLK;
	
	public NsxLoaiLKBean() {
		loaiLK = new LinkedList<NsxLoaiLK>();
	}

	public LinkedList<NsxLoaiLK> getLoaiLK() {
		return loaiLK;
	}

	public void setLoaiLK(LinkedList<NsxLoaiLK> loaiLK) {
		this.loaiLK = loaiLK;
	}
	
	
}
