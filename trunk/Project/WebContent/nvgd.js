$(document).on ('click', '.showCart', function() {
	var name = this.name;
	$.post('http://localhost:8080/Project/ShowCartInfo', {maGio:this.name}, function(response) {
		var json = jQuery.parseJSON(response);		
		
		if (json.status == 'wait') {
			var output = '<p>Tên khách hàng: ' + json.info.name + '</p>'
				+ '<p>Địa chỉ: ' + json.info.address + '</p>'
				+ '<p>Số Điện thoại: ' + json.info.sdt + '</p>';
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

$(document).on('click', '.showXK', function() {
	var name = this.name;
	$.post('http://localhost:8080/Project/ShowXK', {maGio:this.name}, function(response) {
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
			output += '</table><button id="GiaoDich" name=' + name + ' class="">Giao dịch</button><p align="right">' 
					+ json.total + '</p>';
			$('#infoCart').html(output);
		}
	});
});

function refreshListXK() {
	var out = '<p>Danh sách giỏ hàng chờ xuất kho</p>';
			$.post('http://localhost:8080/Project/RefreshListCart', function(response) {
				var json = jQuery.parseJSON(response);
				for (var i = 0; i < json.listID.length; i++) {
					out = out.concat('<a class="showCart" href="#" name="' + json.listID[i] + '" style="color: red">Giỏ ' + json.listID[i] + '</a><br></br>');
				}
			});
			alert(out);
			$('#listCart').html(out);
}

function refreshListGD() {
	var out = '<p>Danh sách đã xuất kho và cần giao dịch</p>';
	$.post('http://localhost:8080/Project/RefreshListGD', function(response){
		var json = jQuery.parseJSON(response);
		for (var i = 0; i < json.listID.length; i++) {
			out = out.concat('<a class="showXK" href="#" name="' + json.listID[i] + '" style="color: red">Giỏ ' + json.listID[i] + '</a><br></br>');
		}
	});
	alert(out);
	$('#listGD').html(out);
}

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
$(document).on('click', '#GiaoDich', function() {
	var name = this.name;
	$.post('http://localhost:8080/Project/LuuGiaoDich', {maGio:name, maNV:"1"}, function(text) {
		if (text == 'success') {
			$('#infoCart').text("Lưu thông tin giao dịch thành công.");
			refreshListGD();
		} else {
			$('#infoCart').text(text);
		}
	});
});