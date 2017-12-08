
$.fn.getCurrentFolderName = function() {
	var selectedNodeName = localStorage["key"];
	var selectedNodeId = localStorage["id"];
	var currentFolderName= document.getElementById("rename_folder-name-input");
	currentFolderName.value = selectedNodeName;
}
	
// confirm button action
$.fn.renameFolderButtonConfirm = function() {
	var newFolderName = document.getElementById('rename_folder-name-input'); 
	var selectedNodeId = localStorage["id"];
	if (newFolderName.value == null || newFolderName.value == ""){
		alert('Please enter a folder name.')
	}else{
		$.fn.serviceCall('GET', '', searchServiceName + 'km/bookmarksv2/manage?folderName='+newFolderName.value+'&folderID=' +selectedNodeId+'&userAction=RENAMEFOLDER', SEARCH_SERVICE_TIMEOUT, function(data){
			$.fn.renameFolderButtonCancel(data);
		});
	}
}

// exit button action
$.fn.renameFolderButtonCancel= function() {
	// Load Manage Bookmarks HTML
	$.get(searchServiceName + 'manage_bookmarks.html', function(data) {
		$('#manage-bookmarks-widget').html(data);
		$.fn.getBookmarks($.fn.manageBookmarkCallback);
		$('#popup').html($('#manage-bookmarks-widget').html());
		$('#manage-bookmarks-widget').html('');
		$('#popup').removeClass('popup');
		$('#popup').addClass('popup_on'); 
		$('#popup').addClass('popup_full');
		$("#popup").css("overflow", "scroll");
		$('.content_body_field_custom_data').hide();
	});
}

