$(document).ready(function() {
	// Load content widget
	$.get(contentServiceName + 'content.html', function(data) {
		$('#content-widget').html(data);
	});
});