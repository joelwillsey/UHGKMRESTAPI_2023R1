$(document).ready(function() {
	$.get(filtersServiceName + "filters.html", function(data) {
		$("#filters-widget").html(data);
	});
});