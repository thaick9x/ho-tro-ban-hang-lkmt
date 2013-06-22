//Hiện thông tin đầy đủ sau ajax
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

//Thực hiện ajax lấy thông tin linh kiện
function showInfoLK(maTenLK) {
	$.post('/Project/GetInfoLK', {serial:maTenLK}, function(text) {
		showInfo(text);
	});
}

//Hiện lại trang Home
function showHome() {
	var out = "";
	$.post('/Project/Home', function(text) {
		if (text.indexOf("!") != -1) {
			var hotJson = text.split("!")[0];
			var newJson = text.split("!")[1];
			//Hiện các linh kiện được truy cập nhiều nhất
			out += '<div id="hotItems" style="heigh: auto; width: 860px;">' 
				+ '<div align="left"><ul><li><img src="C:/Users/Nocturne/Desktop/websource/Testing room/tintucsukien.png"'
				+ 'style="width:65px; height:55px; position:absolute; top:-10px; right:0;" /></li>'
				+ '<li><h2>LINH KIỆN HOT</h2></li></ul></div> <br><br>';
			out += showItemsHome(hotJson);
			out += '</div>';
			
			//Hiện các linh kiện mói được nhập
			out += '<div id="newItems" style="heigh: auto; width: 860px; float: right;"><hr style="height:2px"></hr>'
				+ '<div align="left"><h2>LINH KIỆN MỚI</h2></div> <br><br>';
			out += showItemsHome(newJson);
			out += '</div>';
			
			$('#menu').html(out);
		} else {
			$('#menu').text(text);
		}
	});
}

//Hiện danh sách các linh kiện sau ajax
function showItemsHome(text) {
	var bean = jQuery.parseJSON(text);
	var out = '';
	for(var i = 0; i< bean.tenLK.length; i++) {
		out += '<div style="height:215px;width:215px;float:left"><a class="item" id="linhkien" href="GetInfoLK?serial=' + bean.maTenLK[i] + '">'
			+ '<img src="' + bean.imgUrl[i] + '" height="42" width="42" />'
			+ '<p class="thongtinsp">' + bean.tenLK[i] + '</p>'
			+ '<p>Giá: <font class="price">' + bean.giaLK[i] + '</font></p>';
		if (bean.soLuongLK[i] == 0) {
			out += '<p class="hethang">Hết hàng</p>';
		}
		out += '</a></div>';
	}
	return out;
}

//Thực hiện ajax lọc linh kiện
function showItems(dkSort) {
	$.post('/Project/SortItems', {items:dkSort}, function(responseText) { 
		$('#menu').html(showItemsHome(responseText));
	 });
}	

//Event khi thực hiện lọc linh kiện ở div trái
$(document).on('click', "[type='sort']", function(e) {
	href = this.href;
	var dkSort = this.href.split("=").pop();
	showItems(dkSort);
    window.history.pushState(null, null, href);
    e.preventDefault();
});

//Event khi thực hiện hiện thông tin linh kiện
$(document).on('click', '.item', function(e) {
	href = this.href;
	var maTenLK = this.href.split("=").pop();
	showInfoLK(maTenLK);
	window.history.pushState(null, null, href);
	e.preventDefault();
});

//Xử lý chức năng tìm linh kiện theo tên
$(document).on('click', '#search', function(e) {	
	var dkSearch = document.getElementById("dkSearch").value + '!' + "name";
	var href = 'SearchLinhKien?search=' + dkSearch;
	
	search(dkSearch);
	if (supportAPI()) {
    	window.history.pushState(null, null, href);
    	e.preventDefault();
    } else {
    	var oldHref = window.location.href;
    	var link = oldHref.replace(oldHref.split("/").pop() , '') + href;
    	window.location = link;
    }
});

//Gọi ajax tìm linh kiện
function search(dkSearch) {
	$.post('/Project/SearchLinhKien', {search:dkSearch}, function(text) {
		$('#menu').html(showItemsHome(text));
	});
}

//Back và forward với ajax
$(window).bind("popstate", function(e) {
	var href = location.href;
	if (href.indexOf('Home') != -1) {
		showHome();
	}
	if (href.indexOf('Sort') != -1) {
		var dkSort = href.split("=").pop();
		showItems(dkSort);
	}
	if (href.indexOf('GetInfo') != -1) {
		var maTenLK = href.split("=").pop();
		showInfoLK(maTenLK);
	}
	if (href.indexOf('Cart') != -1) {
		showCart(jQuery.parseJSON($.cookie('CartArr')));
	}
	if (href.indexOf('Search') != -1 ) {
		var dkSearch = href.split("=").pop();
		search(dkSearch);
	}
	e.preventDefault();
  });