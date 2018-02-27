$(document).ready(function() {
	
	// Load Search Results HTML
	$.get(searchServiceName + 'code_search.html', function(data) {
		$('#code-search-widget').html(data);
	});
});