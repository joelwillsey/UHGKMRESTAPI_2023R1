$(document).ready(function() {
	// Load Search Results HTML
	$.get(searchServiceName + 'iset_search_results.html', function(data) {
		$('#search-results-widget').html(data);
	});
});