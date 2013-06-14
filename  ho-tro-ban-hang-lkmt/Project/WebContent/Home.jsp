<%@page import="bean.NsxLoaiLKBean"%>
<%@page import="bean.NsxLoaiLK" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.LinkedList"%>
<%@page import="java.io.IOException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="script.js"></script>
<script src="misc.js"></script>
<script src="cart.js"></script>
<title>Trang chủ</title>
</head>
<body>

<%--chứa thông tin cơ bản của các linh kiện được truy cập nhiều nhất--%>
<jsp:useBean id="hotBean" class="bean.HomeMenuBean" scope="request" />

<%--Chứa thông tin cơ bản của các linh kiện mới được nhập --%>
<jsp:useBean id="newBean" class="bean.HomeMenuBean" scope="request" />

<%--Danh sách các loại linh kiện và hãng sản xuất cửa hàng có--%>
<jsp:useBean id="menuLK" class="bean.NsxLoaiLKBean" scope="request" />

<%--Hiện các sản phẩm--%>
<%!private void showItems(bean.HomeMenuBean bean, JspWriter out) {
	try {
		LinkedList<String> tenLK = bean.getTenLK();
		LinkedList<String> giaLK = bean.getGiaLK();
		LinkedList<Integer> soLuong = bean.getSoLuongLK(); 
		LinkedList<String> imgUrl = bean.getImgUrl();
		LinkedList<String> maTenLK = bean.getMaTenLK();
		
		for (int i = 0; i < tenLK.size(); i++) {
			out.println("<div class=\"item\" id=\"linhKien\" title=\"" + maTenLK.get(i) + 
				"\" style=\"height: 215px; width: 215px; float: left\">");
			out.println("<img src=\"" + imgUrl.get(i) + "\" height=\"42\" width=\"42\" />");
			out.println("<p>" + tenLK.get(i) + "</p>");
			out.println("<p>Giá: " + giaLK.get(i) + "</p>");
			if (soLuong.get(i) == 0) {
				out.println("<p>Hết hàng</p>");
			}
			out.println("</div>");
		}
	} catch (IOException ex) {
	}
} %>
	
<%--Hiện danh sách các loại linh kiện và hãng sản xuất loại linh kiện đó--%>
<%!private void showManufactor(NsxLoaiLKBean bean, JspWriter out) {
	LinkedList<NsxLoaiLK> loaiLK = bean.getLoaiLK();
		
	try {
		for (int i = 0; i < loaiLK.size(); i++) {
			out.println("<div>");
			out.println("<a type=\"sort\" href=\"#\" name=" + loaiLK.get(i).getLoaiLK().toLowerCase() + " style=\"color: blue\">" 
				+ loaiLK.get(i).getLoaiLK().toUpperCase() + "</a>");
			out.println("<div>");
			LinkedList<String> manufactor = loaiLK.get(i).getManufactor();
			for (int j = 0; j < manufactor.size(); j++) {
				out.println("<a type=\"sort\" href=\"#\" name=" + manufactor.get(j).toLowerCase() + "-" +
					loaiLK.get(i).getLoaiLK().toLowerCase() + " style=\"color: red\">" + manufactor.get(j) + "</a>");
				}
			out.println("</div>");
			out.println("</div>");
		}	
	} catch (IOException ex) {
	}
}%>
	<div id="page" align="center">
		<div id="header" style="width: 960px">
		<div align="right">
			<input type=button onClick="parent.location='http://localhost:8080/Project/Login.html'" value='Đăng nhập'>
		</div>	
			<p align="right">
				<a href="#" id="showCart">Giỏ hàng</a>
			</p>
			<p>
				<label for="textfield"> </label>
			<div align="right" style="width: 960px">
				Nhập tên sản phẩm cần tìm <input type="text" name="textfield"
					id="dkSearch" /> <input type="button" name="Search"
					id="search" value="Search" onClick="search()" />
			</div>
		</div>
		<div id="content" style="margin-bottom: 0; width: 960px;">
			<div id="menu" style="heigh: auto; width: 860px; float: right;">
				<div id="hotItems" style="heigh: auto; width: 860px;">
					<h2>Linh kiện hot</h2>
					<% showItems(hotBean, out); %>
				</div>
				<div id="newItems" style="heigh: auto; width: 860px; float: right;">
					<h2>Linh kiện mới</h2>
					<% showItems(newBean, out); %>
				</div>
			</div>
			<div style="heigh: auto; width: 100px; float: left">
				<% showManufactor(menuLK, out); %>
			</div>
		</div>
	</div>
</body>
</html>
