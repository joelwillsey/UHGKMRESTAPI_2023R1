$(document).ready(function() {
	
	// Load Search Results HTML
	$.get(searchServiceName + 'iset_search.html', function(data) {
		$('#search-widget').html(data);
		
	//Resize Window
	window.resizeTo(530,440);
	
	//Move window to top right corner
	var ow =  screen.width - window.outerWidth;	
	window.moveTo(ow,0);
	

	});
});