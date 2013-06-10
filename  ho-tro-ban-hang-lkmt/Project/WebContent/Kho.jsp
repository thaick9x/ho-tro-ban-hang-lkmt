<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="kho.js"></script>
<title>Untitled Document</title>
</head>
<body>
<jsp:useBean id="cart" class="bean.KhoBean" scope="request" />
<div id="header">
  <div align="right">Tên nhân viên
    <input type="button" name="Logout" id="Logout" value="Logout" />
    <input type="button" name="Đăng nhập lại" id="Đăng nhập lại" value="Đăng nhập lại" />
  </div>
</div>
<div id="content" style="margin-bottom:0;width:960px;">
    <%--
    <div id="manufactor" style="height:auto;width:10%;float:left;">
    <p align="center">Danh sách cần xuất kho</p>
  	</div> 
  --%>
  <div id="infoXK" style="heigh:auto;width:960px;float:left;">
   	<%for (int i = 0; i < cart.getCart().size(); i++) { %>
			<div id="<%=cart.getCart().get(i).getMaGio()%>">	
				<%for (int j = 0; j < cart.getCart().get(i).getMaTenLK().size(); j++) { %>
				<p>Quầy yêu cầu: <%=cart.getCart().get(i).getQuay() %></p>
				<p>Mã giỏ: <%=cart.getCart().get(i).getMaGio() %></p>
				<table border="1"><tr><th scope="col">Tên linh kiện</th><th scope="col">Mã linh kiện</th></tr>
					<%for (int m = 0; m < cart.getCart().get(i).getSoLuong().get(j); m++) { %>
						<tr id="<%=cart.getCart().get(i).getMaTenLK().get(j) + m%>">
							<td><%=cart.getCart().get(i).getTenLK().get(j)%></td>
							<td><input id="<%=cart.getCart().get(i).getMaTenLK().get(j)%>" type="text" name="serial"></td>
						</tr>
					<% } %>
				<% } %>
				</table>
				<button id="sent" name=<%=cart.getCart().get(i).getMaGio()%>>Gửi thông tin xuất kho</button>
			</div>
			
	<% } %>
  </div>
</div>
</body>
</html>
