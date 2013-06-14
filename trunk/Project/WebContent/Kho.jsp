<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="kho.js"></script>
<script src="misc.js"></script>
<title>Nhân viên kho</title>
</head>
<body>
	<jsp:useBean id="cart" class="bean.KhoBean" scope="request" />
	
	<jsp:useBean id="infoNVK" class="bean.ThongTinNV" scope="request" />
	
	<%! private void showTTNV(bean.ThongTinNV info, JspWriter out) {
			try {
				out.println("<div id=\"infoNV\" name=\"" + info.getMaNV() + "\">");
				out.println("<p>Tên nhân viên: " + info.getNameNV() + "</p>");
				out.println("</div>");
			} catch (IOException ex) {
			
			}
	} %>
	
<%! private void XuatTTGio(bean.KhoBean cart, JspWriter out) {
	try {
		for (int i = 0; i < cart.getCart().size(); i++) {
   		out.println("<p>Quầy yêu cầu: " + cart.getCart().get(i).getQuay() + "</p>");
		out.println("<p>Mã giỏ: " + cart.getCart().get(i).getMaGio() + "</p>");
		out.println("<div id=\"" + cart.getCart().get(i).getMaGio() + "\">");	
		out.println("<table border=\"1\"><tr><th scope=\"col\">Tên linh kiện</th><th scope=\"col\">Mã linh kiện</th></tr>");
		for (int j = 0; j < cart.getCart().get(i).getMaTenLK().size(); j++) {
			for (int m = 0; m < cart.getCart().get(i).getSoLuong().get(j); m++) {
				out.println("<tr id=\"" + cart.getCart().get(i).getMaTenLK().get(j) + m + cart.getCart().get(i).getMaGio() + "\">");
				out.println("<td>" + cart.getCart().get(i).getTenLK().get(j) + "</td>");
				out.println("<td><input id=\"" + cart.getCart().get(i).getMaTenLK().get(j) + "\" type=\"text\" name=\"serial\"></td>");
				out.println("</tr>");
			}
		}
		out.println("</table><button id=\"sent\" name=" + cart.getCart().get(i).getMaGio() + ">Gửi thông tin xuất kho</button></div>");
		}  
	}
	catch (IOException ex) {
		
	}
} %>
<div align="center">
	<div id="header">
  		<div align="right">
			<%showTTNV(infoNVK, out); %>
			<input type=button onClick="parent.location='http://localhost:8080/Project/Logout'" value='Đăng xuất'>
  		</div>
	</div>
	<div id="content" style="margin-bottom:0;width:960px;">
  		<div id="infoXK" style="heigh:auto;width:960px;float:left;">
   			<% XuatTTGio(cart, out); %>
  		</div>
	</div>
</div>
</body>
</html>
