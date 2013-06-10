package bean;

import java.util.LinkedList;

public class ListCartBean {
	private LinkedList<String> listID;
	
	public ListCartBean() {
		listID = new LinkedList<>();
	}

	public LinkedList<String> getListID() {
		return listID;
	}

	public void setListID(LinkedList<String> cart) {
		this.listID = cart;
	}
	
}
