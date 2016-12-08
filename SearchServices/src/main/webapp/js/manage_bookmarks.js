
	// Launch content ID window
	$.fn.launchViewContent = function(data) {
		log(data);
		window.open (contentServiceName + 'content_container.html?id='+data, data + '-contentwindow','menubar=1,resizable=1,width=1025,height=850');
	}
	
	// Manage bookmark exit
	$.fn.bookmarkExit = function() {
		$('#popup').removeClass('popup_full');
		$('#popup').addClass('popup');
		$('#bookmark-html').html($('#popup').html());
		$('#popup').html('');
	}

	// View content button
	$.fn.bookmarkButtonView = function() {
		$('.bookmark_list div.bookmark_item').each(
			function(index) {
				var div = $(this);
				if (div.hasClass('bookmark_item_selected')) {
					$.fn.launchViewContent(div.attr('id'));
				}
			}
		);
	}

	// Remove bookmark button
	$.fn.bookmarkButtonRemove = function() {
		$('.bookmark_list div.bookmark_item').each(
			function(index) {
				var div = $(this);
				if (div.hasClass('bookmark_item_selected')) {
					$.fn.serviceCall('POST', '', searchServiceName + 'km/bookmark/remove?contentid=' + div.attr('id'), SEARCH_SERVICE_TIMEOUT, $.fn.manageCallback);
					div.remove();
				}
			}
		);
	}

	// Reorder Up bookmark button
	$.fn.bookmarkButtonUp = function() {
		$('.bookmark_list div.bookmark_item').each(
			function(index) {
				var div = $(this);
				if (div.hasClass('bookmark_item_selected')) {
					$.fn.serviceCall('POST', '', searchServiceName + 'km/bookmark/reorderup?contentid=' + div.attr('id'), SEARCH_SERVICE_TIMEOUT, $.fn.manageCallback);
				}
			}
		);
	}

	// Reorder Down bookmark button
	$.fn.bookmarkButtonDown = function() {
		$('.bookmark_list div.bookmark_item').each(
			function(index) {
				var div = $(this);
				if (div.hasClass('bookmark_item_selected')) {
					$.fn.serviceCall('POST', '', searchServiceName + 'km/bookmark/reorderdown?contentid=' + div.attr('id'), SEARCH_SERVICE_TIMEOUT, $.fn.manageCallback);
				}
			}
		);
	}

	// Row selected
	$.fn.bookmarkSelection = function(id, index) {
		var size = 0;
		$('.bookmark_list div.bookmark_item').each(
			function(indx) {
				var div = $(this);
				div.removeClass('bookmark_item_selected');
				size++;
			}
		);
		// Clear it first
		$('#bookmark-button-up').removeClass('bookmark_button_active');
		$('#bookmark-button-down').removeClass('bookmark_button_active');
		$('#bookmark-button-up').attr('disabled', 'disabled');
		$('#bookmark-button-down').attr('disabled', 'disabled');
		
		if (index === 0 && size > 1) {
			$('#bookmark-button-down').addClass('bookmark_button_active');
			$('#bookmark-button-down').removeAttr('disabled');
		} else if (size > 1 && ((index + 1) === size)) {
			$('#bookmark-button-up').addClass('bookmark_button_active');
			$('#bookmark-button-up').removeAttr('disabled');
		} else if (size > 2 && (index != 0 || ((index + 1) != size))) {
			$('#bookmark-button-up').addClass('bookmark_button_active');
			$('#bookmark-button-down').addClass('bookmark_button_active');
			$('#bookmark-button-up').removeAttr('disabled');
			$('#bookmark-button-down').removeAttr('disabled');
		}
		$('#' + id).addClass('bookmark_item_selected');
		$('#bookmark-button-view').addClass('bookmark_action_button_active');
		$('#bookmark-button-remove').addClass('bookmark_action_button_active');
	}
	
	// Populate bookmark screen
	$.fn.populateBookmarks = function(data) {
		log(data);
		var results = [];
		if (typeof data != 'undefined' && data != null && data.numberOfResults > 0) {
			for (var i=0;(data.knowledgeGroupUnits != null) && (i < data.knowledgeGroupUnits.length); i++) {
				results.push('<div id="' + data.knowledgeGroupUnits[i].contentID + '" class="bookmark_item" onclick="javascript:$.fn.bookmarkSelection(\'' + data.knowledgeGroupUnits[i].contentID + '\', ' + i + ');">');
				results.push('  <div class="bookmark_item_col bookmark_col1">');
				results.push('    <span>');
				results.push('      <div class="sr_lr_icon sr_lr_icon_' + data.knowledgeGroupUnits[i].knowledgeUnits[0].contentCategoryTags[0].systemTagName + '">&nbsp;&nbsp;</div>');
				results.push('    </span>');
				results.push('  </div>');
				results.push('  <div class="bookmark_item_col bookmark_col2">');
				results.push('    <span>');
				if (data.knowledgeGroupUnits[i].isFeatured) {
					results.push('  <img src="images/AKCBFeatured14x14.png" />');
				}
				results.push('    </span>');
				results.push('  </div>');
				results.push('  <div class="bookmark_item_col bookmark_col3 bookmark_item_white_space">');
				results.push('    <span>' + data.knowledgeGroupUnits[i].title + '</span>');
				results.push('  </div>');
				results.push('  <div id="bookmark-item-col-end" " class="bookmark_item_col bookmark_col4 bookmark_item_white_space">');
				results.push('    <span>' + data.knowledgeGroupUnits[i].knowledgeUnits[0].synopsis + '</span>');
				results.push('  </div>');
				results.push('</div>');
			}
		}
		$('.bookmark_list').html(results.join('\n'));
		results.length = 0; // Clear the array
	}

	// Manage bookmark callback
	$.fn.manageCallback = function(data) {
		if (typeof data != 'undefined' && data != null && typeof data.errorList != 'undefined' && data.errorList.length > 0) {
			for (var i=0; (data.errorList != null) && (i < data.errorList.length); i++) {
				// TODO; could be more than one error list
				$('#error-h1').html('Error code: ' + data.errorList[i].code + ' Error message: ' + data.errorList[i].message);
	        	$('#error-message').show();
			}
		} else if (typeof data != 'undefined' && data != null && typeof data.errorList != 'undefined' && data.errorList.length === 0) {
			$.fn.getBookmarks($.fn.manageBookmarkCallback);
		}
	}

	// Bookmark callback
	$.fn.manageBookmarkCallback = function(data) {
		$.fn.populateBookmarks(data);
	}

	// Get all the bookmarks for user
	$.fn.getBookmarks = function(callBack) {
		$.fn.serviceCall('GET', '', searchServiceName + 'km/knowledge/bookmarks?page=' + 1 + '&size=' + 100, SEARCH_SERVICE_TIMEOUT, callBack);
		$('#bookmark-button-up').removeClass('bookmark_button_active');
		$('#bookmark-button-down').removeClass('bookmark_button_active');
		$('#bookmark-button-up').attr('disabled', 'disabled');
		$('#bookmark-button-down').attr('disabled', 'disabled');
	}