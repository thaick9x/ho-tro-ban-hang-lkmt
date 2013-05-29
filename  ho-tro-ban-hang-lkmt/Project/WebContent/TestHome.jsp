<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).ready(function() {                        
        $("[type='sort']").click(function() {     
            $.post('http://localhost:8080/Project/SortItems', {items:this.name}, function(responseText) { 
                $('#menu').html(responseText);         
            });
        });
    });
</script>
</head>
<body>
	<%--Khai báo bean và những chi tiết liên quan--%>
	<jsp:useBean id="hotBean" class="bean.HomeMenuBean" scope="request" />
	<% 	LinkedList<String> tenLK = hotBean.getTenLK();
    		LinkedList<Long> giaLK = hotBean.getGiaLK();
    		LinkedList<Integer> soLuong = hotBean.getSoLuongLK(); 
    		LinkedList<String> imgUrl = hotBean.getImgUrl();
    	%>
	<jsp:useBean id="newBean" class="bean.HomeMenuBean" scope="request" />
	<% 	LinkedList<String> tenLKN = newBean.getTenLK();
    		LinkedList<Long> giaLKN = newBean.getGiaLK();
    		LinkedList<Integer> soLuongN = newBean.getSoLuongLK(); 
    		LinkedList<String> imgUrlN = newBean.getImgUrl();
    	%>
	<jsp:useBean id="menuLK" class="bean.ManufactorBean" scope="request" />
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
				<div id="hotItems" style="heigh: auto; width: 860px;">
					<h2>Linh kiện hot</h2>
					<% for (int i = 0; i < tenLK.size(); i++) { %>
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
				</div>
				<div id="newItems" style="heigh: auto; width: 860px; float: right;">
					<h2>Linh kiện mới</h2>
					<% for (int i = 0; i < tenLKN.size(); i++) { %>
					<div id="linhKien" style="height: 215px; width: 215px; float: left">
						<img src=<%=imgUrlN.get(i)%> height="42" width="42" />
						<p><%=tenLKN.get(i) %></p>
						<p>
							Giá:
							<%=giaLKN.get(i) %></p>
						<% if (soLuongN.get(i) == 0) { %>
						<p>Hết hàng</p>
						<% } %>
					</div>
					<% } %>
				</div>
				<form name="form1" method="post" action="">
					<input type="button" name="button-cart" id="button-cart"
						value="Thêm vào giỏ">
				</form>

			</div>
			<div style="heigh: auto; width: 100px; float: left">
				<%for (int i = 0; i < menuLK.getLoaiLK().size(); i++) { %>
				<div>
					<p align="center"><%=menuLK.getLoaiLK().get(i).getLoaiLK()%></p>
					<div>
						<%for (int j = 0; j < menuLK.getLoaiLK().get(i).getManufactor().size(); j++) { %>
						<a type="sort" href="#" name=<%=menuLK.getLoaiLK().get(i).getManufactor().get(j).toLowerCase() + "-" + 
						menuLK.getLoaiLK().get(i).getLoaiLK().toLowerCase() %> style="color: red"><%=menuLK.getLoaiLK().get(i).getManufactor().get(j) %></a>
						<%}%>
					</div>
				</div>
				<%}%>
			</div>
		</div>
	</div>
</body>
</html>
