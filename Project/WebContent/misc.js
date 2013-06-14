function isNormalInteger(str) {
    var n = ~~Number(str);
    return String(n) === str && n > 0;
}

$(document).on('focusout', '#quantity', function () {
	var trs = document.getElementsByTagName("tr");
	if (isNormalInteger(this.value) == false || this.value == "") {
		for(var i = 0; i < trs.length; i++) {
		   if (trs[i].id == this.name) {
			   $('#error').text('Sai số lượng.');
			   trs[i].style.backgroundColor = 'red';
		   }
		}
	} else {
		for(var i = 0; i < trs.length; i++) {
			if (trs[i].id == this.name) {
				$('#error').text('');
				trs[i].style.backgroundColor = 'white';
			 }
		}
	}
});

window.onbeforeunload = confirmExit;
function confirmExit()
{
  $.get('http://localhost:8080/Project/Logout', function() {

  });
}
