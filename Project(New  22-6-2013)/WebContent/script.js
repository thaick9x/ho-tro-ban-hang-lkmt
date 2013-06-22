var old = "";
    
// Xuất menu linh kiện đã được lọc
function showItems(text) {
	var json = jQuery.parseJSON(text);
	var output = "";
	if (json.tenLK.length > 0) {
		for (var i = 0; i < json.tenLK.length; i++) {
			var status = "";
			if (json.soLuongLK[i] == 0) { 
				status = "Hết hàng"; 
			}
			output += '<div class="item" id="linhKien" title="' + json.maTenLK[i] + '" style="height:215px;width:215px;float:left">' + 
				'<img src="' + json.imgUrl[i] + '" height="42" width="42"/>' +
				'<p>' + json.tenLK[i] + '</p>' + 
				'<p>Giá: ' + json.giaLK[i] + '</p>' + status + '</div>';
		}
    	$('#menu').html(output);  
	} else {
		$('#menu').text('Không có loại linh kiện này.');
	}
} 

// Hiện thông tin đầy đủ của linh kiện
function showInfo(text) {
		var json = jQuery.parseJSON(text);
   		var out = "";
    	var status = "";
    	$('#out').text('');
    	if (json.soLuong == 0) {
    		status = 'Hết hàng';
    	}
    	out = '<img src="' + json.img + '"/><div align="left" id="extraInfo"><p id="out">' + status + '</p><p>Tên linh kiện: ' 
    			+ json.tenLK + '</p><p>Giá: ' 
    			+ json.giaLK + '</p><p> Bảo hành: ' 
    			+ json.baoHanh + ' tháng</p><p>Nhà sản xuất: ' 
    			+ json.manufactor + '</p><p>Tình trạng: ' 
    			+ json.tinhTrang + '</p>';
    	var extraInfo = json.extra;
    	for (var i = 0; i < extraInfo.length; i++) {
    		if (extraInfo[i].value != "" && extraInfo[i].name != "img") {
    			out +='<p>' + extraInfo[i].name.toUpperCase() + ': ' + extraInfo[i].value + '</p>';
    		}
    	}
    	out += '</div><p id="error"></p><button id="addCart" name="' + json.maTenLK + '">Thêm vào giỏ</button><button id="back">back</button>';
    	$('#menu').html(out);
}

function search() {
	var dkSearch = new Object();
	dkSearch.dk = document.getElementById("dkSearch").value;
	dkSearch.by = "name";
	searchResult(JSON.stringify(dkSearch));
}
	

function searchResult(dkSearch) {
	$.post('http://localhost:8080/Project/SearchLinhKien', {search:dkSearch}, function(text) {
		showItems(text);
	});
}

// Lấy dữ liệu từ server sau khi đã được lọc
$(function() {                        
	$("[type='sort']").click(function() {
    	old = $('#menu').html();
        $.get('http://localhost:8080/Project/SortItems', {items:this.name}, function(responseText) { 
        	showItems(responseText);
        });
    });
});

// Hiện thông tin đầy đủ của linh kiện khi click vào 
$(document).on ('click', '.item', function() {
	old = $('#menu').html();
  	$.post('http://localhost:8080/Project/GetInfoLK', {serial:this.title}, function(text) { 
  		showInfo(text);
    });	 
});

// Quay lại trang trước đó
$(document).on ('click', '#back', function() {
    $('#menu').html(old);
});