<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.io.IOException" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" ></script>
<script src="nvgd.js"></script>
<script src="misc.js"></script>
<title>Nhân viên giao dịch</title>
</head>
<body>
	
	<%--Chứa thông tin nhân viên--%>
	<jsp:useBean id="infoNVGD" class="bean.ThongTinNV" scope="request" />
	
	<%--Chứa danh sách các giỏ hàng cần xuất kho--%>
	<jsp:useBean id="list" class="bean.DsGioHangBean" scope="request" />
	<% LinkedList<String> listCart = list.getListID(); %>
	
	<%--Chứa danh sách các giỏ hàng đã được xuất kho--%>
	<jsp:useBean id="ready" class="bean.DsGioGD" scope="request" />
	<% LinkedList<String> listReady = ready.getList(); %>
	
	<%--Hiện thông tin nhân viên--%>
	<%! private void showTTNV(bean.ThongTinNV info, JspWriter out) {
			try {
				out.println("<div id=\"infoNV\" name=\"" + info.getMaNV() + "\">");
				out.println("<p>Tên nhân viên: " + info.getNameNV() + "</p>");
				out.println("</div>");
			} catch (IOException ex) {
			
			}
	} %>
	
	<div id="page" align="center">
		<div align="right" id="header" style="width:960px">
			<%showTTNV(infoNVGD, out); %>
			<input type=button onClick="parent.location='http://localhost:8080/Project/Logout'" value='Đăng xuất'>
		</div>
		<div id="content" style="height: auto; width: 960px; float: left">
			<div id="listCart" style="height: auto; width: 100px; float: left;">
				<p>Danh sách giỏ hàng chờ xuất kho</p>
				
				<% for (int j = 0; j < listCart.size(); j++) { %>
					<a class="showCart" href="#" id=<%=listCart.get(j)%> style="color: red">Giỏ <%=listCart.get(j)%></a><br></br>
				<%}%>
				
			</div>
			<div id="listGD" style="height: auto; width: 100px; float: right;">
				<p align="center">Danh sách đã xuất kho và cần giao dịch</p>
				
				<%for (int j = 0; j < listReady.size(); j++) { %>
					<a class="showXK" href="#" id=<%=listReady.get(j)%> style="color: red">Giỏ <%=listReady.get(j)%></a><br></br>
				<%}%>
				
			</div>
			<div id="infoCart" style="heigh: auto; width: 760px; float: left;">
			</div>
		</div>
	</div>
</body>
</html>
