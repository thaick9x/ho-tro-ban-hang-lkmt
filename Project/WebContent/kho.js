$(document).on('click', '#sent', function() {
	var serial = new Array();
	var idArr = new Array();
	var maTen = new Array();
	var name = this.name;
	$('div[id="' + name + '"').find('input').each(function() {
		maTen.push(this.id);
		serial.push(this.value);
	});
	$('div[id="' + name + '"').find('tr').each(function(i) {
		if (i > 0) {
			idArr.push(this.id);
		}
	});
	$.post('http://localhost:8080/Project/CheckXK', {maGio:name,idRow:JSON.stringify(idArr), maLK:JSON.stringify(serial), maTenLK:JSON.stringify(maTen)}, function(text) {
		if (text == "success") {
			$('#infoXK').html('<p>Gửi yêu cầu xuất kho thành công</p>');
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