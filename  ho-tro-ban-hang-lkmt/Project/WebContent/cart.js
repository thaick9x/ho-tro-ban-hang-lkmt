
    var itemsInCart = new Array();
	var soLuong;
	var idArr;
	
    function showCart(itemsInArray) {
    	var output = '<p id="error" align="right"></p><table id="cart" width="100%" border="1"><tr><th scope="col">STT</th><th scope="col">Tên</th><th scope="col">Số lượng</th></tr>';
    	$.post('http://localhost:8080/Project/ShowCartItems', {cart:JSON.stringify(itemsInCart)}, function(text)  {
    		var cart = jQuery.parseJSON(text);
        	for (var i = 0; i < cart.items.length; i++) {
        		output += '<tr id="' + cart.items[i].maTenLK + '" class="rowCart"><td>' + (i + 1) 
        			+ '</td><td>' + cart.items[i].tenLK + '</td><td><input id="quantity" type="text" name=' + cart.items[i].maTenLK + '></td>' 
        			+ '<td><button onclick="deleteRow(this)">Remove</button></td></tr>';
        	}
        	output += '</table><br><button id="confirmCart">Xác nhận giỏ</button><div id="sendCart"></div><button id="back">Back</button>';
        	$('#menu').html(output);
    	});
    }
    
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
    });

    $(document).on ('click', '#showCart', function() {  
    	old = $('#menu').html();
    	showCart(itemsInCart);
    	
    });

function deleteRow(r)
{
	$('#infoKH').remove();
	var i=r.parentNode.parentNode.rowIndex;
    	document.getElementById('cart').deleteRow(i);
    	itemsInCart.splice(i-1, 1);
    	showCart(itemsInCart);
}

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
		$.post('http://localhost:8080/Project/ConfirmCart', {id:JSON.stringify(idArr), amount:JSON.stringify(soLuong)}, function(text) {
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
$(document).on('click', '#sent', function() {
	var infoArr = new Array();
	$('#infoKH').find('input').each(function() {
		infoArr.push(this.value);
	});
	
	var kh = new Object();
	kh.name = infoArr[0];
	kh.address = infoArr[1];
	kh.sdt = infoArr[2];
	
	if (kh.name != '' && kh.address != '' && kh.sdt !='') {
		$.post('http://localhost:8080/Project/ReceiveCart', {info:JSON.stringify(kh),id:JSON.stringify(idArr), amount:JSON.stringify(soLuong)}, function(text) {	
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
