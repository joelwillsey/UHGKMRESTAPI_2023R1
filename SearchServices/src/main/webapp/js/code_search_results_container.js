
$(document).ready(function() {
	
	//Resize Window
	window.resizeTo(1024,440);
	
	//Move window to top right corner
	var ow =  screen.width - window.outerWidth;	
	window.moveTo(ow,0);
	
	
	// Load Search Results HTML
	$.get(searchServiceName + 'code_search_results.html', function(data) {
		$('#search-results-widget').html(data);
	});
});
