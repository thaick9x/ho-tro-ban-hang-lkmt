<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="script.js">
</script>
</head>
<body>
	<div id="page" align="center">
		<div id="header" style="width: 960px">
			<p align="right">
				<input type="button" name="Đăng nhập" id="Đăng nhập lại"
					value="Đăng nhập lại" />
			</p>
			<p align="right">
				<a href="#">Giỏ hàng</a>
			</p>
			<p>
				<label for="textfield"> </label>
			<div align="right" style="width: 960px">
				Nhập tên sản phẩm cần tìm <input type="text" name="textfield"
					id="textfield" /> <input type="submit" name="Search"
					id="button-search" value="Search" />
			</div>
		</div>
		<div id="content" style="margin-bottom: 0; width: 960px;">
			<div id="menu" style="heigh: auto; width: 860px; float: right;">
				<p align="center" id="aa" onclick="loadXMLDoc()">Danh mục và
					thông tin sản phẩm</p>
				<jsp:useBean id="hotBean" class="bean.HomeMenuBean" scope="request" />
				<% 	LinkedList<String> tenLK = hotBean.getTenLK();
    		LinkedList<String> giaLK = hotBean.getGiaLK();
    		LinkedList<Integer> soLuong = hotBean.getSoLuongLK(); 
    		LinkedList<String> imgUrl = hotBean.getImgUrl();
    		
    		for (int i = 0; i < tenLK.size(); i++) { %>
				<div id="linhKien" style="height: 215px; width: 215px; float: left">
					<img src=<%=imgUrl.get(i)%> height="42" width="42" />
					<p><%=tenLK.get(i) %></p>
					<p>
						Giá:
						<%=giaLK.get(i) %></p>
					<% if (soLuong.get(i) == 0) { %>
					<p>Hết hàng</p>
					<% } %>
				</div>
				<% } %>

				<form name="form1" method="post" action="">
					<input type="button" name="button-cart" id="button-cart"
						value="Thêm vào giỏ">
				</form>

			</div>
			<div style="heigh: auto; width: 100px; float: left">
				<div id="manufacto" style="height: auto; width: 100px; float: left;">
					<p id="loaiLK" align="center">CPU</p>
					<div align="center">
						<a name="intel-cpu" id="button" style="background-color: red" href="#">intel</a><br />
						<input id="button" name="intel-cpu" type="button" value="AMD">
					</div>
				</div>
				<div id="manufactor" style="auto: 100px; width: 100px; float: left;">
					<p align="center">VGA</p>
					<div align="center">
						<input name="vga-gefore" type="button" value="Gefore"><br>
						<input name="vga-ati" type="button" value="ATI">
					</div>
				</div>
				<div id="manufactor"
					style="height: 100px; width: 100px; float: left;">
					<p align="center">RAM</p>
				</div>
				<div id="manufactor"
					style="height: 100px; width: 100px; float: left;">
					<p align="center">MainBoard</p>
				</div>
				<div id="manufactor"
					style="height: 100px; width: 100px; float: left;">
					<p align="center">PSU</p>
				</div>
				<div id="manufactor"
					style="height: 100px; width: 100px; float: left;">
					<p align="center">Monitor</p>
				</div>
				<div id="manufactor"
					style="height: 100px; width: 100px; float: left;">
					<p align="center">Case</p>
				</div>
				<div id="manufactor"
					style="height: 100px; width: 100px; float: left;">
					<p align="center">Keyboard</p>
				</div>
				<div id="manufactor"
					style="height: 100px; width: 100px; float: left;">
					<p align="center">Mouse</p>
				</div>
				<div id="manufactor"
					style="height: 100px; width: 100px; float: left;">
					<p align="center">Keyboard + Mouse</p>
				</div>
				<div id="manufactor"
					style="height: 100px; width: 100px; float: left;">
					<p align="center">Mouse Pad</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
