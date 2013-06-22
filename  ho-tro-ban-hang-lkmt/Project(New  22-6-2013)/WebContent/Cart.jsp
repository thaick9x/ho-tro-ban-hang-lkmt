<%@page import="bean.NsxLoaiLKBean"%>
<%@page import="dto.InfoShowCart" %>
<%@page import="bean.NsxLoaiLK" %>
<%@page import="dto.ItemsInCart" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.LinkedList"%>
<%@page import="java.io.IOException"%>
<!DOCTYPE html>
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="jquery.cookie.js"></script>
<script src="home.js"></script>
<script src="misc.js"></script>
<script src="cart.js"></script>
<link rel="stylesheet" href="style.css">
<title>Trang chủ</title>
</head>
<body>
<%--Danh sách các mã tên linh kiện trong giỏ --%>
<jsp:useBean id="items" class="dto.ItemsInCart" scope="request" />

<%--Danh sách các loại linh kiện và hãng sản xuất cửa hàng có--%>
<jsp:useBean id="menuLK" class="bean.NsxLoaiLKBean" scope="request" />
	
<%--Hiện danh sách các loại linh kiện và hãng sản xuất loại linh kiện đó--%>
<%!private void showManufactor(NsxLoaiLKBean bean, JspWriter out) {
	LinkedList<NsxLoaiLK> loaiLK = bean.getLoaiLK();
		
	try {
		out.println("<ul>");
		for (int i = 0; i < loaiLK.size(); i++) {
			out.println("<li><ul>");
				//SortItems?items=
				out.println("<li><a class=\"p_Catename\" type=\"sort\" href=\"SortItems?items=" + loaiLK.get(i).getLoaiLK().toLowerCase() 
						+ "\" style=\"color: blue\">" 
						+ loaiLK.get(i).getLoaiLK().toUpperCase() + "</a></li>");
				out.println("<li><ul>");
				
				LinkedList<String> manufactor = loaiLK.get(i).getManufactor();	
				for (int j = 0; j < manufactor.size(); j++) {
					out.println("<li><a type=\"sort\" href=\"SortItems?items=" + manufactor.get(j).toLowerCase() + "-" +
						loaiLK.get(i).getLoaiLK().toLowerCase() + "\" style=\"color: red\">" + manufactor.get(j) + "</a></li>");
				}
			out.println("</ul></li></ul></li>");
			/*
			out.println("<div>");
			out.println("<a class=\"p_Catename\" type=\"sort\" href=\"#\" name=" + loaiLK.get(i).getLoaiLK().toLowerCase() + " style=\"color: blue\">" 
				+ loaiLK.get(i).getLoaiLK().toUpperCase() + "</a>");
			out.println("<div>");
			LinkedList<String> manufactor = loaiLK.get(i).getManufactor();
			for (int j = 0; j < manufactor.size(); j++) {
				out.println("<a type=\"sort\" href=\"#\" name=" + manufactor.get(j).toLowerCase() + "-" +
					loaiLK.get(i).getLoaiLK().toLowerCase() + " style=\"color: red\">" + manufactor.get(j) + "</a>");
				}
			out.println("</div>");
			out.println("</div>");*/
		}	
		out.println("</ul>");
	} catch (IOException ex) {
	}
}%>

<%--Hiện giỏ hàng --%>
<%!private void showCart(ItemsInCart cart, JspWriter out) {
	LinkedList<InfoShowCart> items = cart.getItems();
	try {
		out.print("<p id=\"error\" align=\"right\"></p><table id=\"cart\" width=\"100%\" border=\"1\"><tr><th scope=\"col\">STT</th><th scope=\"col\">Tên</th><th scope=\"col\">Số lượng</th></tr>");
		for (int i = 0; i < items.size(); i++) {
       		out.print("<tr id=\"" + items.get(i).getMaTenLK() + "\" class=\"rowCart\"><td>" + (i + 1) + "");
       		out.print("</td><td>" + items.get(i).getTenLK() + "</td><td><input id=\"quantity\" type=\"text\" name=" + items.get(i).getTenLK() + "></td>");
       		out.print("<td><button name=removeRow>Remove</button></td></tr>");
       	}
       	out.print("</table><br><button id=\"confirmCart\">Xác nhận giỏ</button><div id=\"sendCart\"></div><button id=\"back\">Back</button>");
	} catch (IOException ex) {
	
	}
}%>

<div id="page" align="center">
		<div id="header" style="width: 960px">
		<div align="right">
			<input type=button onClick="parent.location='/Project/Login.html'" value='Đăng nhập' class="button">
		</div>	
			<p align="right" class="p2">
				<a href="ShowCartItems?cart=" id="showCart">
				<img src="cssImg/shopping-cart-icons - edited ver3.png" alt="12345" width="40" height="28">
				Giỏ hàng
				</a>
			</p>
			<p>
				<label for="textfield"> </label>
			<div align="right" style="width: 960px" class="p2">
				Nhập tên sản phẩm cần tìm 
				<input type="text" name="textfield" id="dkSearch"/><button id="search" class="button">Search</button>
			</div>
		</div>
		<div id="content" style="margin-bottom: 0; width: 960px;">
		<hr style="height:2px"></hr>
			<div id="menu" style="heigh: auto; width: 860px; float: right;">
				<% showCart(items, out); %>
			</div>
			<div style="heigh: auto; width: 100px; float: left">
				<% showManufactor(menuLK, out); %>
			</div>
		</div>
	</div>
</body>
</html>