
$(document).ready(function() {
	
	log("Resizing window and moving it");
	//Resize Window
	window.resizeTo(1024,440);
	
	//Move window to top right corner
	var ow =  screen.width - window.outerWidth;	
	window.moveTo(ow,0);
	
	//This is commented out and moved to the code_search.js file to ensure the widget there  has started before the widget contained the js below starts as it can cause errors
/*	// Load Search Results HTML
	$.get(searchServiceName + 'code_search_results.html', function(data) {
		$('#search-results-widget').html(data);
	});*/
});
