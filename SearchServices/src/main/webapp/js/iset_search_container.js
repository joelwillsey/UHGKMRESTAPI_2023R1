$(document).ready(function() {
	
	// Load Search Results HTML
	$.get(searchServiceName + 'iset_search.html', function(data) {
		$('#search-widget').html(data);		
	});
});