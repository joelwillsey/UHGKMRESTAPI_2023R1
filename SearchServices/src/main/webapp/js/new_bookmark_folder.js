// launch new folder pop up
$.fn.newFolderButtonConfirm = function() {
	var folderName = document.getElementById('new_folder-name-input');
	if (folderName.value == null || folderName.value == ""){
		alert('Please enter a folder name.')
	}else{
		$.fn.serviceCall('GET', '', searchServiceName + 'km/bookmarksv2/manage?folderName='+folderName.value+'&userAction=ADDFOLDER', SEARCH_SERVICE_TIMEOUT, function(data){
			$.fn.newFolderButtonCancel(data);
		});
	}
}

//launch new folder pop up
$.fn.newFolderButtonCancel= function() {
	// Load Manage Bookmarks HTML
	$.get(searchServiceName + 'manage_bookmarks.html', function(data) {
		$('#manage-bookmarks-widget').html(data);
		$.fn.getBookmarks($.fn.manageBookmarkCallback);
		$('#popup').html($('#manage-bookmarks-widget').html());
		$('#manage-bookmarks-widget').html('');
		//$('#popup').removeClass('popup');
		$('#popup-manage-bookmarks').removeClass('new_folder_popup_on');
		$('#manage-bookmarks-background').removeClass('background_on');
		$('#popup').addClass('popup_on'); 
		$('#popup').addClass('popup_full');
		$("#popup").css("overflow", "scroll");
		$('.content_body_field_custom_data').hide();
	});
}

