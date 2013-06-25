var soLuong;
var idArr;

//Hiện giỏ hàng
function showCart(items) {
    var output = '<p id="error" align="right"></p><table id="cart" width="100%" border="1"><tr><th scope="col">STT</th><th scope="col">Tên</th><th scope="col">Số lượng</th></tr>';
    $.post('/Project/ShowCartItems', {cart:JSON.stringify(items)}, function(text)  {
    	var cart = jQuery.parseJSON(text);
       	for (var i = 0; i < cart.items.length; i++) {
       		output += '<tr id="' + cart.items[i].maTenLK + '" class="rowCart"><td>' + (i + 1) 
       			+ '</td><td>' + cart.items[i].tenLK + '</td><td><input id="quantity" type="text" name=' + cart.items[i].maTenLK + '></td>' 
       			+ '<td><button name=removeRow>Remove</button></td></tr>';
       	}
       	output += '</table><br><button id="confirmCart">Xác nhận giỏ</button><div id="sendCart"></div><button id="back">Back</button>';
       	$('#menu').html(output);
   	});
}

$(document).on ('click', '#addCart', function() {
	var items;
	var c_value = $.cookie('CartArr');
	
	if (c_value == null || c_value == '' ) {
		items = new Array();
		items.push(this.name);
		$.cookie('CartArr', JSON.stringify(items));
	} else {
		items = jQuery.parseJSON(c_value);
		items.push(this.name);
		overrideCookie(items);
	}
	changeHref();
});

/*
//Xử lý thêm linh kiện vào giỏ   
$(document).on ('click', '#addCart', function() { 
    if ($('#out').text() != "Hết hàng") {
    	if (itemsInCart.indexOf(this.name) == -1) {
    		itemsInCart.push(this.name);
    	} else {
    		$('#error').text('Đã có món linh kiện này trong giỏ.');
    	}
    } else {
    	$('#error').text('Linh kiện này đã hết hàng');
    }
});*/

//Xử lý nhấn vào giỏ hàng ở góc phải
$(document).on ('click', '#showCart', function(e) {
	var c_value = $.cookie('CartArr');
	if (c_value == null || c_value == '' ) {
		var items = new Array();
		$.cookie('CartArr', JSON.stringify(items));
		showCart(items);
		var href = ('ShowCartItems?cart=[]');
		windown.history.pushState(null, null, href);
	} else {
		var href = ('ShowCartItems?cart=' + c_value);
		changeHref();
		showCart(jQuery.parseJSON($.cookie('CartArr')));
		window.history.pushState(null, null, href);
	}
	e.preventDefault();
});

//Xóa 1 linh kiện ra khỏi giỏ hàng
$(document).on('click', "[name='removeRow']", function(e) {
	var c_value = $.cookie('CartArr');
	var items = jQuery.parseJSON(c_value);
	
	//Xoá trên giao diện
	$('#infoKH').remove();
	var i = this.parentNode.parentNode.rowIndex;
    document.getElementById('cart').deleteRow(i);
    items.splice(i - 1, 1);
    
    //Xóa trong cookie
    overrideCookie(items);

    //Chỉnh đường link
    var href = 'ShowCartItems?cart=' + $.cookie('CartArr');
    changeHref();
    showCart(jQuery.parseJSON($.cookie('CartArr')));
    if (supportAPI()) {
    	window.history.pushState(null, null, href);
    	e.preventDefault();
    } else {
    	var link = window.location.href.split('=')[0] + '=' + $.cookie('CartArr');
    	window.location = link;
    }
});

//Xác nhận giỏ hàng
$(document).on('click', '#confirmCart', function() {
	var out ="";
	soLuong = new Array();
	idArr = new Array();
	$('tr').find('input').each(function() {
		soLuong.push(this.value);
	});
	$('tr').each(function(i) {
		if (i > 0) {
			idArr.push(this.id);
		}
	});
	if ((idArr.length != 0) && (soLuong.indexOf('') == -1) ) {
		$.post('/Project/ConfirmCart', {id:JSON.stringify(idArr), amount:JSON.stringify(soLuong)}, function(text) {
			out += '<div id="infoKH"><label for="nameKH">Tên khách hàng: </label>'
				+ '<input type="text" name="nameKH" id="nameKH"></input><br />'
				+ '<label for="addressKH">Địa chỉ: </label>'
				+ '<input type="text" name="addressKH" id="addressKH"></input><br />'
				+ '<label for="telKH">Số điện thoại: </label>'
				+ '<input type="text" name="telKH" id="telKH"></input></div><p>Tổng tiền thanh toán cho giỏ: ' + text + '</p><button id="sent">Gửi giỏ hàng</button>';
			$('#sendCart').html(out);
		});
	} else {
		$('#error').text("Chưa có món hàng nào trong giỏ.");
	}
});

//Gửi giỏ hàng lên cho nhân viên giao dịch
$(document).on('click', '#sent', function() {
	
	//Lấy thông tin khách hàng được nhập vào
	var infoArr = new Array();
	$('#infoKH').find('input').each(function() {
		infoArr.push(this.value);
	});
	var kh = new Object();
	kh.name = infoArr[0];
	kh.address = infoArr[1];
	kh.sdt = infoArr[2];
	if (kh.name != '' && kh.address != '' && kh.sdt !='') {
		$.post('/Project/ReceiveCart', {info:JSON.stringify(kh),id:JSON.stringify(idArr), amount:JSON.stringify(soLuong)}, function(text) {	
			if (text == 'success') {
				$('#menu').text('Giỏ hàng đã được nhận');
				idArr.length = 0;
				soLuong.length = 0;
				itemsInCart.length = 0;
			} else {
				var json = jQuery.parseJSON(text);
				for (var j = 0; j < json.length; j++) {
					var trs = document.getElementsByTagName("tr");
					for(var i = 0; i < trs.length; i++)
					{
						if (trs[i].id == json[j]) {
							$('#error').text('Sai số lượng.');
							trs[i].style.backgroundColor = 'red';
						}
					}
				}
			}
		});
	} else {
		$('#error').text('Bạn phải điền thông tin.');
	}
});

function supportAPI() {
	  return !!(window.history && history.pushState);
}

function changeHref() {
	var href = ('ShowCartItems?cart=' + $.cookie('CartArr'));
	document.getElementById('showCart').setAttribute('href', href);
}

function overrideCookie(items) {
	$.removeCookie('CartArr');
	$.cookie('CartArr', JSON.stringify(items));
}
