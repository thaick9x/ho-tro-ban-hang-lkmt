<%@page import="dto.ItemsInfo"%>
<%@page import="bean.NsxLoaiLKBean"%>
<%@page import="bean.NsxLoaiLK" %>
<%@ page import="java.util.LinkedList"%>
<%@page import="java.io.IOException"%>
<%@page import="dto.ExtraInfo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="jquery.cookie.js"></script>
<script src="home.js"></script>
<script src="misc.js"></script>
<script src="cart.js"></script>
<link rel="stylesheet" href="style.css">
<title>Trang chủ</title>
</head>
<body>

<%--Java Bean chứa thông tin cơ bản của các linh kiện được truy cập nhiều nhất--%>
<jsp:useBean id="info" class="dto.ItemsInfo" scope="request" />

<%--Danh sách các loại linh kiện và hãng sản xuất cửa hàng có--%>
<jsp:useBean id="menuLK" class="bean.NsxLoaiLKBean" scope="request" />
	
<%--Hiện danh sách các loại linh kiện và hãng sản xuất loại linh kiện đó--%>
<%!private void showManufactor(NsxLoaiLKBean bean, JspWriter out) {
	LinkedList<NsxLoaiLK> loaiLK = bean.getLoaiLK();
		
	try {
		out.println("<ul>");
		for (int i = 0; i < loaiLK.size(); i++) {
			out.println("<li><ul>");
			
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
		}	
		out.println("</ul>");
	} catch (IOException ex) {
	}
}%>

<%!private void showInfo(ItemsInfo info, JspWriter out) {
	String status = "";
	LinkedList<ExtraInfo> extra = info.getExtra();

	if (info.getSoLuong() == 0) {
		status = "Hết hàng";
	}
	
	try {
		if (info.getExtra().size() != 0) {	
			out.println("<img src=\"" + info.getImg() + "\" /><div align=left id=\"extraInfo\"><p id=\"out\">" 
				+ status + "</p><p>Tên linh kiện: "
				+ info.getTenLK() + "</p><p>Giá: "
				+ info.getGiaLK() + "</p><p> Bảo hành: "
				+ info.getBaoHanh() + " tháng</p><p>Nhà sản xuất: "
				+ info.getManufactor() + "</p><p>Tình trạng: "
				+ info.getTinhTrang() + "</p>");
			
			for (int i = 0; i < extra.size(); i++) {
				if (extra.get(i).getValue() != "" && extra.get(i).getName() != "img") {
    				out.println("<p>" + extra.get(i).getName().toUpperCase() + ": " + extra.get(i).getValue() + "</p>"); 
    			}
			}
			out.println("</div><p id=\"error\"></p><button id=\"addCart\" name=\"" + info.getMaTenLK() 
				+ "\">Thêm vào giỏ</button><button id=\"back\">back</button>");
				
		} else {
			out.println("Không có linh kiện này");
		}
	} catch (IOException ex) {
	
	}
} %>
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
				<% showInfo(info, out);  %>
			</div>
			<div style="heigh: auto; width: 100px; float: left">
				<% showManufactor(menuLK, out); %>
			</div>
		</div>
	</div>
</body>
</html>
