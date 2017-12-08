	alert('Rename Bookmark Folder Container js');
$(document).ready(function() {
	// Load Rename Bookmarks Folder HTML
	$.get(searchServiceName + 'rename_bookmark_folder.html', function(data) {
		$('#rename-bookmark-folder-widget').html(data);
	});
})

