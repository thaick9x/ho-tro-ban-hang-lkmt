package dto;

import java.util.LinkedList;


public class ItemsInCart {

	private LinkedList<InfoShowCart> items;
	
	public ItemsInCart() {
		items = new LinkedList<>();
	}
	public LinkedList<InfoShowCart> getItems() {
		return items;
	}

	public void setItems(LinkedList<InfoShowCart> items) {
		this.items = items;
	}
}
