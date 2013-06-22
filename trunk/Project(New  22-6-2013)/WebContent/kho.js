// Gửi thông tin xuất kho từ kho tới cho nhân viên giao dịch ở quầy tương ứng
$(document).on('click', '#sent', function() {
	var serial = new Array(); // Mã giỏ
	var idArr = new Array(); // id của tr
	var maTen = new Array(); // mã tên linh kiện
	var name = this.name;
	
	$('div[id="' + name + '"]').find('input').each(function() {
		maTen.push(this.id);
		serial.push(this.value);
	});
	
	$('div[id="' + name + '"]').find('tr').each(function(i) {
		if (i > 0) {
			idArr.push(this.id);
		}
	});
	$.post('http://localhost:8080/Project/CheckXK', {maGio:name, idRow:JSON.stringify(idArr), maLK:JSON.stringify(serial), maTenLK:JSON.stringify(maTen)}, function(text) {
		if (text == "success") {
			refreshPage();
		}
		else {
			var json = jQuery.parseJSON(text);
			for (var j = 0; j < json.length; j++) {
				var trs = document.getElementsByTagName("tr");
				for(var i=0;i<trs.length;i++)
				{
				   if (trs[i].id == json[j]) {
					   trs[i].style.backgroundColor = 'red';
				   }
				}
			}
		}
	});
});

// Refresh lại trang chính của nhân viên kho sau khi đã gửi đi 1 giỏ
function refreshPage() {
	$.post('http://localhost:8080/Project/Refresh', function(response) {
		refresh(response);
	});
}
function refresh(text) {
	var json = jQuery.parseJSON(text);
	var out = '';
	for (var i = 0; i < json.cart.length; i++) { 
		out += '<div id="' + json.cart[i].maGio + '">';	
		out += '<p>Quầy yêu cầu: ' + json.cart[i].quay + '</p>' +
		'<p>Mã giỏ: ' + json.cart[i].maGio + '</p>' +
		'<table border="1"><tr><th scope="col">Tên linh kiện</th><th scope="col">Mã linh kiện</th></tr>';
		for (var j = 0; j < json.cart[i].maTenLK.length; j++) { 	
			for (var m = 0; m < json.cart[i].soLuong[j]; m++) { 
				out += '<tr id="' + (json.cart[i].maTenLK[j] + m + json.cart[i].maGio) + '">' +
						'<td>' + json.cart[i].tenLK[j] + '</td>' +
						'<td><input id="' + json.cart[i].maTenLK[j] + '" type="text" name="serial"></td></tr>';
			 } 
		 } 
		out += '</table><button id="sent" name=' + json.cart[i].maGio + '>Gửi thông tin xuất kho</button></div>';
	} 
	$('#infoXK').html(out);
}


