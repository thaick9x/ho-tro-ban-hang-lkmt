<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" ></script>
<script src="nvgd.js"></script>
<title>Nhân viên giao dịch</title>
</head>
<body>
	<jsp:useBean id="list" class="bean.ListCartBean" scope="request" />
	<jsp:useBean id="ready" class="bean.ReadyCartsBean" scope="request" />
	<% 	LinkedList<String> listCart = list.getListID();	
		LinkedList<String> listReady = ready.getList();
	%>
	<div id="page" align="center">
		<div id="header" style="width:960px">
			<p align="right">Tên nhân viên</p>
			<input type="button" name="Logout" id="Logout" value="Logout" /> <input
				type="button" name="Đăng nhập lại" id="Đăng nhập lại"
				value="Đăng nhập lại" />
			<div align="right"  style="width: 960px">
				<p>Nhập tên sản phẩm cần tìm</p>
				<input type="text" name="textfield" id="textfield" /> <input
					type="submit" name="Search" id="button-search" value="Search" />
			</div>
		</div>
		<div id="content" style="height: auto; width: 960px; float: left">
			<div id="listCart" style="height: auto; width: 100px; float: left;">
				<p>Danh sách giỏ hàng chờ xuất kho</p>
				<%for (int j = 0; j < listCart.size(); j++) { %>
					<a class="showCart" href="#" name=<%=listCart.get(j)%> style="color: red">Giỏ <%=listCart.get(j)%></a><br></br>
				<%}%>
			</div>
			<div id="listGD" style="height: auto; width: 100px; float: right;">
				<p align="center">Danh sách đã xuất kho và cần giao dịch</p>
				<%for (int j = 0; j < listReady.size(); j++) { %>
					<a class="showXK" href="#" name=<%=listReady.get(j)%> style="color: red">Giỏ <%=listReady.get(j)%></a><br></br>
				<%}%>
			</div>
			<div id="infoCart" style="heigh: auto; width: 760px; float: left;">
			</div>
		</div>
	</div>
</body>
</html>
