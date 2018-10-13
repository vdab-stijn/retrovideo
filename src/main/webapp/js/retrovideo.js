var eSearch;
window.onload = function() {
	eSearch = document.getElementById('searchMovieDB');
	
	eSearch.focus();
	eSearch.addEventListener('keydown', searchElementChanged);
}

function searchElementChanged(event) {
	// Disable default reaction for ENTER
	if (event.keyCode == 13) { event.preventDefault(); };
	
	var value = this.value;
	
	if (value.length > 2) {
		makeAjaxRequest(window.location + 'ajax?search=' + value);
	}
}

var ajaxRequest;
function getAjaxRequest() {
	if (ajaxRequest !== undefined && ajaxRequest !== false)
		return ajaxRequest;
	
	// Opera 8.0+, Mozilla Firefox, Safari
	try {
		ajaxRequest = new XMLHttpRequest();
	}
	// Internet explorer
	catch (e) {
		try {
			ajaxRequest = new ActiveXObject('Msxml12.XMLHTTP');
		}
		catch (e) {
			try {
				ajaxRequest = new ActiveXObject('Microsoft.XMLHTTP');
			}
			catch (e) {
				console.log('ERROR: no ajax object available');
				
				return false;
			}
		}
	}
	
	return ajaxRequest;
}

function makeAjaxRequest(url) {
	getAjaxRequest();
	
	ajaxRequest.onreadystatechange = processAjaxResponse;
	
	ajaxRequest.open('GET', url, true);
	ajaxRequest.send(null);
}

function processAjaxResponse() {
	console.log(ajaxRequest);
	if (ajaxRequest.readyState == 4) {
		if (ajaxRequest.status == 200) {
			console.log("AJAX: " + ajaxRequest.responseText);
			
			var movies = JSON.parse(ajaxRequest.response);
			
			console.log('MOVIES FOUND: ' + movies.length);
			
			eSearch.value = '';
		}
	}
}

