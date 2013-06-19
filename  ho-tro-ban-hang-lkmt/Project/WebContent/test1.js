function showInfoLK(text) {
	var json = jQuery.parseJSON(text);
	//var out = "";
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


// Hiện thông tin đầy đủ của linh kiện
function showInfo(id) {
	alert(id.split("/").pop());
	var req = new XMLHttpRequest();
	req.open("GET", "http://localhost:8080/Project/GetInfoLK?serial=+" + id.split("/").pop(), false);
	req.send(null);
	if (req.status == 200) {
		//$.get('http://localhost:8080/Project/GetInfoLK', {serial:id.split("/").pop()}, function(text) { 
		showInfoLK(req.responseText);
		alert(responseText);
		setupHistoryClicks();
		return true;
	}
	return false;
	}

function addClicker(link) {
	  link.addEventListener("click", function(e) {
	    if (showInfo(link.href)) {
	      window.history.pushState(null, null, location.pathname + link.href);
	      e.preventDefault();
	    }
	  }, true);
	}

function setupHistoryClicks() {
	  addClicker(document.getElementById("linhkien"));
	  //addClicker(document.getElementById("photoprev"));
}

function supports_history_api() {
	  return !!(window.history && history.pushState);
	}

window.onload = function() {
	  if (!supports_history_api()) { return; }
	  setupHistoryClicks();
	  window.setTimeout(function() {
	    window.addEventListener("popstate", function(e) {
	    	showInfo(location.pathname);
	    }, false);
	  }, 1);
	};
/*
$(window).bind('popstate', function() {
	$.ajax({url:location.pathname+'?rel=tab',success: function(data){
		$('#content').html(data);
	}});
});*/
	
