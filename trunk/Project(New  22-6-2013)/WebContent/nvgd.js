//Hiện chi tiết giỏ hàng đang chờ được xử lý khi chọn 1 giỏ trong danh sách
$(document).on ('click', '.showCart', function() {
	var name = this.id;
	$.post('http://localhost:8080/Project/ShowCartInfo', {maGio:name}, function(response) {
		var json = jQuery.parseJSON(response);		
		if (json.status == 'wait') {
			
			//Thông tin khách hàng
			var output = '<p>Tên khách hàng: ' + json.info.name + '</p>'
				+ '<p>Địa chỉ: ' + json.info.address + '</p>'
				+ '<p>Số Điện thoại: ' + json.info.sdt + '</p>';
			
			//Thông tin giỏ
			output += '<table id="cart" width="100%" border="1"><tr><th scope="col">STT</th><th scope="col">Tên</th><th scope="col">Số lượng</th></tr>';
	        for ( var i = 0; i < json.maTenLK.length; i++) {
	        	output += '<tr id=' + json.maTenLK[i] + '><td>' + (i + 1) + '</td>'
	        		+ '<td>' + json.tenLK[i] + '</td>' 
	        		+ '<td>' + json.soLuong[i] + '</td></tr>';
	        }
	        output += '</table><button id="xuatKho" name=' + name + '>Xuất kho</button>';
	        $('#infoCart').html(output);
		} else {
			$('#listCart').remove(this);
			$('#infoCart').text('Giỏ này đã được xử lý');
		}
	});
});

//Hiện thông tin giỏ hàng đã được xử lý xuất kho khi chọn 1 giỏ ở danh sách các giỏ bên phải
$(document).on('click', '.showXK', function() {
	var name = this.id; //mã giỏ
	
	$.post('http://localhost:8080/Project/ShowXK', {maGio:name}, function(response) {
		var json = jQuery.parseJSON(response);
		var output = '<div id = "' + json.maKH + '"><p>Tên khách hàng: ' + json.info.name + '</p>'
					+ '<p>Địa chỉ: ' + json.info.address + '</p>'
					+ '<p>Số Điện thoại: ' + json.info.sdt + '</p></div>';
		output += '<table id="cart" width="100%" border="1"><tr><th scope="col">STT</th><th scope="col">Tên</th><th scope="col">Mã linh kiện</th></tr>';
		if (json.status == 'ready') {
			for (var i = 0; i < json.maLK.length; i++) {
				output += '<tr id=' + json.maLK[i] + '><td>' + (i + 1) + '</td>'
        		+ '<td>' + json.tenLK[i] + '</td>' 
        		+ '<td>' + json.maLK[i] + '</td></tr>';
			}
			output += '</table><p align="right">Tổng giá trị: ' 
					+ json.total + '</p><button id="GiaoDich" name=' + name + ' class="">Giao dịch</button>';
			$('#infoCart').html(output);
		}
	});
});

//Refresh lại danh sách các giỏ cần xử lý xuất kho
function refreshListXK() {
	$.post('http://localhost:8080/Project/RefreshListCart', function(response) {
		refresh(response, '#listCart');
	});
}

//Hiện lại danh sách các giỏ. Được gọi sau khi thực hiện  1 trong 2 ajax refresh
function refresh(text, selector) {
	var out = "";
	if (selector == '#listCart') {
		out += '<p>Danh sách giỏ hàng chờ xuất kho</p>';
	} else {
		out += '<p>Danh sách đã xuất kho và cần giao dịch</p>';
	}
	json = jQuery.parseJSON(text);
	if (json.list.length > 0) {
		for (var i = 0; i < json.list.length; i++) {
			out += '<a class="showCart" href="#" id="' + json.listID[i] + '" style="color: red">Giỏ ' + json.listID[i] + '</a><br></br>';
		}
	} else {
		out += '';
	}
	$(selector).html(out);
}

//Refresh danh sách các giỏ hàng cần hoàn tất giao dịch
function refreshListGD() {
	$.post('http://localhost:8080/Project/RefreshListGD', function(response){
		refresh(response, '#listGD');
	});
}

//Thêm vị trí quầy vào thông tin giỏ hàng và chuyển xuống nhân viên kho
$(document).on('click', '#xuatKho', function() {
	var name = this.name;
	$.post('http://localhost:8080/Project/XuatKho', {maGio:name,quay:"1"}, function(text) {
		if (text == "success") {
			$('#infoCart').html('<p>Gửi yêu cầu xuất kho thành công</p>');
			refreshListXK();
		}
		else {
			$('#infoCart').text(text);
		}
	});
});

//Lưu thông tin, chi tiết giao dịch
$(document).on('click', '#GiaoDich', function() {
	var name = this.name;
	var nv = $('.infoNV').attr('id').split('V').pop();
	$.post('http://localhost:8080/Project/LuuGiaoDich', {maGio:name, maNV:nv}, function(text) {
		if (text == 'success') {
			$('#infoCart').text("Lưu thông tin giao dịch thành công.");
			refreshListGD();
		} else {
			$('#infoCart').text(text);
		}
	});
});