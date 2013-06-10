package bean;

import java.util.LinkedList;

public class ReadyCartsBean {
	private LinkedList<String> list;
	
	public ReadyCartsBean() {
		list = new LinkedList<>();
	}

	public LinkedList<String> getList() {
		return list;
	}

	public void setList(LinkedList<String> list) {
		this.list = list;
	}
	
}
