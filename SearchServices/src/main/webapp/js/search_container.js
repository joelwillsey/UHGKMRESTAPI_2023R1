$(document).ready(function() {
	// Load Search Results HTML
	$.get(searchServiceName + 'search.html', function(data) {
		$('#search-widget').html(data);
	});
});