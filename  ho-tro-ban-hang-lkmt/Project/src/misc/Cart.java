package misc;

import java.util.LinkedList;


import com.google.gson.*;

import dao.DAOCart;
import dto.ItemsInCart;

public class Cart {
	// Nhận các mã tên linh kiện trong giỏ và trả thông tin linh kiện khi khách hàng muốn xem giỏ hàng của mình
	static public ItemsInCart getItemsInCart(String maTenLK) throws JsonSyntaxException, Exception {
		Gson json = new Gson();
		ItemsInCart cart = new ItemsInCart();
		DAOCart dao = new DAOCart();
		cart = dao.getCartItemsInfo(json.fromJson(maTenLK, String[].class));
		return cart;
	}
	
	static public String getTotalPrice(String id, String amount) throws Exception {
		Gson json = new Gson();
		DAOCart dao = new DAOCart();
		return Currency.getCurrency((dao.calcuTotal(json.fromJson(id, String[].class), json.fromJson(amount, int[].class))));
	}
	
	static public LinkedList<String> receiveCart(String kh, String id, String amount) throws Exception {
		Gson json = new Gson();
		DAOCart cart = new DAOCart();
		LinkedList<String> kq = new LinkedList<>();
		try {
			kq = cart.checkCart(json.fromJson(id, String[].class), json.fromJson(amount, int[].class));
			if (kq.size() == 0) {
				cart.receiveCart(json.fromJson(kh, dto.InfoKH.class), json.fromJson(id, String[].class), json.fromJson(amount, int[].class));
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Exception ex = new Exception(e.getMessage());
			throw ex;
		}
		return kq;
	}
}
