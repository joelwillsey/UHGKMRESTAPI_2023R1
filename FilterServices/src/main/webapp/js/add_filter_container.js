$(document).ready(function() {
	$.get(filtersServiceName + "add_filter.html", function(data) {
		$("#add-filter-widget").html(data);
	});
});