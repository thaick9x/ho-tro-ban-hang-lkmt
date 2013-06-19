<%@page import="bean.NsxLoaiLKBean"%>
<%@page import="bean.NsxLoaiLK" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.LinkedList"%>
<%@page import="java.io.IOException"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="test1.js"></script>
<script src="misc.js"></script>
<script src="cart.js"></script>
<link rel="stylesheet" href="style.css">
<title>Trang chủ</title>
</head>
<body>

<%--Java Bean chứa thông tin cơ bản của các linh kiện được truy cập nhiều nhất--%>
<jsp:useBean id="hotBean" class="bean.HomeMenuBean" scope="request" />

<%-- Java Bean chứa thông tin cơ bản của các linh kiện mới được nhập --%>
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
		
		out.println("<a class=\"item\" id=\"linhkien\" href=\"" + maTenLK.get(0) + "\" \">");
		out.println("<img src=\"" + imgUrl.get(0) + "\" height=\"42\" width=\"42\" />");
		out.println("<p class=\"thongtinsp\">" + tenLK.get(0) + "</p>");
		out.println("<p>Giá: <font class=\"price\">" + giaLK.get(0) + "</font></p>");
		if (soLuong.get(0) == 0) {
			out.println("<p class=\"hethang\">Hết hàng</p>");
		}
		//out.println("</div>");
		out.println("</a>");
		
		/*
		for (int i = 0; i < tenLK.size(); i++) {
			//out.println("<div class=\"item\" id=\"linhKien\" title=\"" + maTenLK.get(i) + 
				//"\" style=\"height: 215px; width: 215px; float: left\">");
			out.println("<a class=\"item\" id=\"linhkien\" href=\"" + maTenLK.get(i) + "\" \">");
			out.println("<img src=\"" + imgUrl.get(i) + "\" height=\"42\" width=\"42\" />");
			out.println("<p class=\"thongtinsp\">" + tenLK.get(i) + "</p>");
			out.println("<p>Giá: <font class=\"price\">" + giaLK.get(i) + "</font></p>");
			if (soLuong.get(i) == 0) {
				out.println("<p class=\"hethang\">Hết hàng</p>");
			}
			//out.println("</div>");
			out.println("</a>");
		}*/
		
	} catch (IOException ex) {
	}
} %>
	
<%--Hiện danh sách các loại linh kiện và hãng sản xuất loại linh kiện đó--%>
<%!private void showManufactor(NsxLoaiLKBean bean, JspWriter out) {
	LinkedList<NsxLoaiLK> loaiLK = bean.getLoaiLK();
		
	try {
		for (int i = 0; i < loaiLK.size(); i++) {
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
			out.println("</div>");
		}	
	} catch (IOException ex) {
	}
}%>
	<div id="page" align="center">
		<div id="header" style="width: 960px">
		<div align="right">
			<input type=button onClick="parent.location='http://localhost:8080/Project/Login.html'" value='Đăng nhập' class="button">
		</div>	
			<p align="right" class="p2">
				<a href="#" id="showCart">
				<img src="cssImg/shopping-cart-icons - edited ver3.png" alt="12345" width="40" height="28">
				Giỏ hàng
				</a>
			</p>
			<p>
				<label for="textfield"> </label>
			<div align="right" style="width: 960px" class="p2">
				Nhập tên sản phẩm cần tìm <input type="text" name="textfield"
					id="dkSearch" /> <input type="button" name="Search"
					id="search" value="Search" onClick="search()" class="button" />
			</div>
		</div>
		<div id="content" style="margin-bottom: 0; width: 960px;">
		<hr style="height:2px"></hr>
			<div id="menu" style="heigh: auto; width: 860px; float: right;">
				<div id="hotItems" style="heigh: auto; width: 860px;">
					<!-- Nơi đây đã từng là h2 -->
						<div align="left">
							<ul>
								<li>	<img src="C:/Users/Nocturne/Desktop/websource/Testing room/tintucsukien.png"  style="width:65px; height:55px; position:absolute; top:-10px; right:0;" />	</li>
								<li>	<h2>LINH KIỆN HOT</h2> </li>
								
							</ul>
						</div> <br><br>
					<!-- h2 end -->
					<% showItems(hotBean, out); %>
				</div>
				<div id="newItems" style="heigh: auto; width: 860px; float: right;">
					<hr style="height:2px"></hr>
					<!-- Nơi đây từng là h2 -->
						<div align="left">
							<h2>LINH KIỆN MỚI</h2>
						</div> <br><br>
					<!-- h2 end -->
					<%-- showItems(newBean, out); --%>
				</div>
			</div>
			<div style="heigh: auto; width: 100px; float: left">
				<% showManufactor(menuLK, out); %>
			</div>
		</div>
	</div>
</body>
</html>
