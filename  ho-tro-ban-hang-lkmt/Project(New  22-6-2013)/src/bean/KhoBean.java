package bean;

import java.util.LinkedList;

import dto.CartInfo;

/*
 * Class chứa các thông tin giỏ hàng cho nhân viên kho sử dụng
 * Dùng cho NVKho.jsp
 */

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
