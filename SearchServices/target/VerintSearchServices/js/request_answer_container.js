$(document).ready(function() {
	// Load Request Answer HTML
	$.get(searchServiceName + 'request_answer.html', function(data) {
		$('#request-answer-widget').html(data);
	});
});