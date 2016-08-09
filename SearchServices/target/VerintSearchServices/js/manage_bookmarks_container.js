$(document).ready(function() {
	// Load Manage Bookmarks HTML
	$.get(searchServiceName + 'manage_bookmarks.html', function(data) {
		$('#manage-bookmarks-widget').html(data);
		$.fn.getBookmarks($.fn.manageBookmarkCallback);
	});
});