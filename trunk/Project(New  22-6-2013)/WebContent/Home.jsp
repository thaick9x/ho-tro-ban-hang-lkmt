<%@page import="bean.NsxLoaiLKBean"%>
<%@page import="bean.NsxLoaiLK" %>
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

<%--Java Bean chứa thông tin cơ bản của các linh kiện được truy cập nhiều nhất--%>
<jsp:useBean id="hotBean" class="bean.HomeMenuBean" scope="request" />

<%-- Java Bean chứa thông tin cơ bản của các linh kiện mới được nhập --%>
<jsp:useBean id="newBean" class="bean.HomeMenuBean" scope="request" />

<%--Danh sách các loại linh kiện và hãng sản xuất cửa hàng có--%>
<jsp:useBean id="menuLK" class="bean.NsxLoaiLKBean" scope="request" />

<%--Hiện các linh kiện sau khi nhận được bean--%>
<%!private void showItems(bean.HomeMenuBean bean, JspWriter out) {
	try {
		LinkedList<String> tenLK = bean.getTenLK(); //Danh sách các tên linh kiện
		LinkedList<String> giaLK = bean.getGiaLK(); //Danh sách giá
		LinkedList<Integer> soLuong = bean.getSoLuongLK(); //Danh sách số lượng còn của từng linh kiện
		LinkedList<String> imgUrl = bean.getImgUrl(); //Dãy các link ảnh cho từng linh kiện
		LinkedList<String> maTenLK = bean.getMaTenLK(); //Dãy các tên linh kiện
		
		for (int i = 0; i < maTenLK.size(); i++) {
			//1 linh kiện được hiện ra
			out.println("<div style=\"height: 215px; width: 215px; float: left\">");
			out.println("<a class=\"item\" id=\"linhkien\" href=\"GetInfoLK?serial=" + maTenLK.get(i) + "\">");
			out.println("<img src=\"" + imgUrl.get(i) + "\" height=\"42\" width=\"42\" />");
			out.println("<p class=\"thongtinsp\">" + tenLK.get(i) + "</p>");
			out.println("<p>Giá: <font class=\"price\">" + giaLK.get(i) + "</font></p>");
		
			//Kiểm tra số lượng nếu = 0 hiện hết hàng
			if (soLuong.get(i) == 0) {
				out.println("<p class=\"hethang\">Hết hàng</p>");
			}
			out.println("</a></div>");
		}
	} catch (IOException ex) {
	}
} %>
	
<%--Hiện danh sách các loại linh kiện và hãng sản xuất loại linh kiện đó--%>
<%!private void showManufactor(NsxLoaiLKBean bean, JspWriter out) {
	LinkedList<NsxLoaiLK> loaiLK = bean.getLoaiLK();
		
	try {
		out.println("<ul>");
		for (int i = 0; i < loaiLK.size(); i++) {
			out.println("<li><ul>");
			
				//Hiện tên loại linh kiện
				out.println("<li><a class=\"p_Catename\" type=\"sort\" href=\"SortItems?items=" + loaiLK.get(i).getLoaiLK().toLowerCase() 
						+ "\" style=\"color: blue\">" 
						+ loaiLK.get(i).getLoaiLK().toUpperCase() + "</a></li>");
				out.println("<li><ul>");
				
				//Hiện các nhà sản xuất của loại linh kiện trên
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
				<input type="text" name="textfield" id="dkSearch"/><button id="search" class="button">Search</button>
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
