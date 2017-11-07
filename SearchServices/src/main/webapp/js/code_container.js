$(document).ready(function() {
	// Load Search Results HTML
	$.get(searchServiceName + 'code_search_container.html', function(data) {
		$('#code_search_container-widget').html(data);
	});
});