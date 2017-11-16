
$(document).ready(function() {
	// Load Search Results HTML
	$.get(searchServiceName + 'code_content.html', function(data) {
		$('#content-widget').html(data);
	});
});
