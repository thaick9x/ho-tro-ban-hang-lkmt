    var old = "";
	$(function() {                        
    	$("[type='sort']").click(function() {
    		old = $('#menu').html();
            $.post('http://localhost:8080/Project/SortItems', {items:this.name}, function(responseText) { 
            	var json = jQuery.parseJSON(responseText);
            	var output = "";
            	var name = json.tenLK;
            	$.each(name, function(i, ten) {
            		var status = "";
            		if (json.soLuongLK[i] == 0) { 
            			status = "Hết hàng"; 
            		}
            		output += '<div class="item" id="linhKien" title="' + json.maTenLK[i] + '" style="height:215px;width:215px;float:left">' + 
    					'<img src="' + json.imgUrl[i] + '" height="42" width="42"/>' +
    					'<p>' + json.tenLK[i] + '</p>' + 
    					'<p>Giá: ' + json.giaLK[i] + '</p>' + status + '</div>';
            	});
                $('#menu').html(output);         
            });
        });
    });
    $(document).on ('click', '.item', function() {
		old = $('#menu').html();
    	$.post('http://localhost:8080/Project/GetInfoLK', {serial:this.title}, function(text) { 
    		var json = jQuery.parseJSON(text);
    		var out = "";
    		out = '<img src="' + json.img + '"/><p>Tên linh kiện: ' + json.tenLK + '</p><p>Giá: ' + json.giaLK + '</p><p> Bảo hành: ' 
    				 	      + json.baoHanh + ' tháng</p><p>Nhà sản xuất: ' + json.manufactor + '</p><p>Tình trạng: ' + json.tinhTrang + '</p>'; 
    		var extraInfo = json.extra;
    		$.each(extraInfo, function(i, info) {
    		if (info.value != "" && info.name != "img") {out +='<p>' + info.name + ': ' + info.value + '</p>';}
    		});
    		out += '<button id="addCart" name="' + json.maTenLK + '">Thêm vào giỏ</button><button id="back">back</button>';
    		$('#menu').html(out);
    	});	 
    });
    $(document).on ('click', '#back', function() {
    	$('#menu').html(old);
    });
    /*
    function total_price(cart) {
    	var total = 0;
    	$.each(cart, function(items) {
    		var price = items.price.replace(',', '');
    		total += price;
    	});
    	return total;
    }*/