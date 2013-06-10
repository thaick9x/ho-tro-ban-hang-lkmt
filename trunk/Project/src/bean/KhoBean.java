package bean;

import java.util.LinkedList;

import dto.CartInfo;

public class KhoBean {
	private LinkedList<CartInfo> cart;
	
	public KhoBean() {
		cart = new LinkedList<>();
	}

	public LinkedList<CartInfo> getCart() {
		return cart;
	}

	public void setCart(LinkedList<CartInfo> cart) {
		this.cart = cart;
	}
}
