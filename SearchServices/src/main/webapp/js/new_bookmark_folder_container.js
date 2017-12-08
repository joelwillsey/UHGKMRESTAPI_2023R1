	alert('New Bookmark Folder Container js');
$(document).ready(function() {
	// Load New Bookmarks Folder HTML
	$.get(searchServiceName + 'new_bookmark_folder.html', function(data) {
		$('#new-bookmark-folder-widget').html(data);
	});
})

