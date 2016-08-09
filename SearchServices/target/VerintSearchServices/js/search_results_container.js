$(document).ready(function() {
	// Load Search Results HTML
	$.get(searchServiceName + 'search_results.html', function(data) {
		$('#search-results-widget').html(data);
	});
});