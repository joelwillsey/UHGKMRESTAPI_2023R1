
$(document).ready(function() {
	// Load Search Results HTML
	$.get(searchServiceName + 'code_search_results.html', function(data) {
		$('#search-results-widget').html(data);
	});
});
