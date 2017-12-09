
	// Launch content ID window
	$.fn.launchViewContent = function(data) {
		log(data);
		window.open (contentServiceName + 'content_container.html?id='+data, data + '-contentwindow','menubar=1,resizable=1,width=1025,height=850');
	}
	
	// Manage bookmark exit
	$.fn.bookmarkExit = function() {
		$('.content_body_field_custom_data').show();
		$("#popup").removeAttr("style");
		$('#popup').removeClass('popup_full');
		$('#popup').addClass('popup');
		$('#bookmark-html').html($('#popup').html());
		$('#popup').html('');
	}
	

	// View content button
	$.fn.bookmarkButtonView = function() {
		
		var node = $('#bookmarkTree').tree('getSelectedNode');
		//alert('node id = ' + node.id + ', type = ' + node.type);
		$.fn.launchViewContent(node.id);
		
		/*$('.bookmark_list div.bookmark_item').each(
			function(index) {
				var div = $(this);
				if (div.hasClass('bookmark_item_selected')) {
					$.fn.launchViewContent(div.attr('id'));
				}
			}
		);*/
	}

	// Remove bookmark button
	$.fn.bookmarkButtonRemove = function() {
		var node = $('#bookmarkTree').tree('getSelectedNode');
		if (node.children.length > 0){
			alert('Please empty folder before removing.')
		}else{
			alert('Ok to remove.')
		}
		//for (var i=0; i < node.children.length; i++) {
		//    var child = node.children[i];
		//}
		
		// remove a folder
		if (node.type == "folder"){
			$.fn.serviceCall('GET', '', searchServiceName + 'km/bookmarksv2/manage?folderID='+node.id+'&userAction=REMOVEFOLDER', SEARCH_SERVICE_TIMEOUT);
		}else{
			
			//$('.bookmark_list div.bookmark_item').each(
			//		function(index) {
			//			var div = $(this);
			//			if (div.hasClass('bookmark_item_selected')) {
							$.fn.serviceCall('POST', '', searchServiceName + 'km/bookmark/remove?contentid=' + node.id, SEARCH_SERVICE_TIMEOUT, $.fn.manageCallback);
			//				$.fn.serviceCall('POST', '', searchServiceName + 'km/bookmark/remove?contentid=' + div.attr('id'), SEARCH_SERVICE_TIMEOUT, $.fn.manageCallback);
			//				div.remove();
			//			}
			//		}
			//);
		}
		
		$.fn.getBookmarks();
		
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
	
	
	// New Folder bookmark button
	$.fn.bookmarkButtonNewFolder = function() {
		$.fn.launchNewFolder();
		
		
		/*$('.bookmark_list div.bookmark_item').each(
			function(index) {
				var div = $(this);
				if (div.hasClass('bookmark_item_selected')) {
					alert('New Folder Button clicked');
					$.fn.launchNewFolder();}
			}
		);*/
	}
	
	// launch new folder pop up
	$.fn.launchNewFolder = function() {
		var node = $('#bookmarkTree').tree('getSelectedNode');
//		if (node.children.length > 0){
		if (node){
			var level = node.getLevel();
		}
			//alert('level = ' + level);
///		}else{
//			alert('Ok to remove.')
//		}popup-manage-bookmarks
		$.get(searchServiceName + 'new_bookmark_folder.html', function(data) {
			$('#new-bookmark-folder-widget').html(data);
			$('#popup-manage-bookmarks').html($('#new-bookmark-folder-widget').html());
			$('#new-bookmark-folder-widget').html('');
			$('#manage-bookmarks-background').addClass('background_on');
			$('#popup-manage-bookmarks').addClass('new_folder_popup_on');

		});
		
//		$('#popup-manage-bookmarks').ready( function(){
//			console.log('Started new folder events');
//			$('#new_folder-name-input').change( function() {	    
//				    	console.log('This is text');
//				    	$("#new-folder-button-accept").attr("disabled", "true"); 
//				    	if ($(this).val() != initVal && $(this).val() != "") {
//				            $("#new-folder-button-accept").removeAttr("disabled");
//				        } else {
//				            $("#new-folder-button-accept").attr("disabled", "true");        
//				        }
//				    });
//		});
		
		
		//var node = $('#bookmarkTree').tree('getSelectedNode');
		//document.getElementById("rename_folder-name-input").value = node.name;
	};

	
	
	// Rename Folder bookmark button
	$.fn.bookmarkButtonRenameFolder = function() {

		var node = $('#bookmarkTree').tree('getSelectedNode');
		localStorage["key"] = node.name;
		localStorage["id"] = node.id;
		if (node.type != "folder") {
			alert('Please select a folder to rename.');
		}else{
			$.fn.launchRenameFolder();
		}	
		

	}
	
	// launch rename folder pop up
	$.fn.launchRenameFolder = function() {
		$.get(searchServiceName + 'rename_bookmark_folder.html', function(data) {
			$('#rename-bookmark-folder-widget').html(data);
			$('#popup-manage-bookmarks').html($('#rename-bookmark-folder-widget').html());
			$('#rename-bookmark-folder-widget').html('');
			$('#manage-bookmarks-background').addClass('background_on');
			$('#popup-manage-bookmarks').addClass('rename_folder_popup_on');

		});
	};
	
	
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
		$('#' + id).addClass('bookmark_item_selected');
		log("id= #" + id);
		var node = $('#bookmarkTree').tree('getSelectedNode');
		log("Node Type selected: " + node.type + " node name: " + node.name);
		if (typeof node != 'undefined' && node != null){
			if (node.type == "folder") {
				//folder action
				$('#bookmark-button-view').removeClass('bookmark_action_button_active');
				$("#bookmark-button-view").attr("disabled", "true");				
				$('#bookmark-button-remove').addClass('bookmark_action_button_active');
				$('#bookmark-button-remove').removeAttr("disabled");
				$('#bookmark-button-new-folder').addClass('bookmark_action_button_active');
				$("#bookmark-button-new-folder").removeAttr("disabled");
				$('#bookmark-button-rename').addClass('bookmark_action_button_active');
				$("#bookmark-button-rename").removeAttr("disabled");
			} else {
				//bookmark action
				$("#bookmark-button-view").addClass('bookmark_action_button_active');
				$('#bookmark-button-view').removeAttr("disabled");
				$('#bookmark-button-remove').addClass('bookmark_action_button_active');
				$("#bookmark-button-remove").removeAttr("disabled");
				$('#bookmark-button-new-folder').addClass('bookmark_action_button_active');
				$("#bookmark-button-new-folder").removeAttr("disabled");
				$('#bookmark-button-rename').removeClass('bookmark_action_button_active');
				$('#bookmark-button-rename').attr("disabled", "true");
				
			}
		} else {
			//this was a click event nothing was selected yet so just enable new folder
			$('#bookmark-button-view').removeClass('bookmark_action_button_active');
			$("#bookmark-button-view").removeAttr("disabled");
			$('#bookmark-button-remove').removeClass('bookmark_action_button_active');
			$("#bookmark-button-remove").attr("disabled", "true");
			$('#bookmark-button-new-folder').addClass('bookmark_action_button_active');
			$("#bookmark-button-new-folder").removeAttr("disabled");
			$('#bookmark-button-rename').removeClass('bookmark_action_button_active');
			$("#bookmark-button-rename").attr("disabled", "true");
		}
	}

	
	// Populate bookmark screen
	$.fn.populateBookmarks = function(data) {
		var results = [];

		
		if (typeof data != 'undefined' && data != null && data.contentBookmarksV2 != null) {
			for (var i=0;(data.contentBookmarksV2.folders != null) && (i < data.contentBookmarksV2.folders.length); i++) {
				var topFolderChildren = [];	
				//var subFolders = [];
				// populate sub folder data
				if (data.contentBookmarksV2.folders[i].subFolders != null){
					for (var k=0;(data.contentBookmarksV2.folders[i].subFolders != null) && (k < data.contentBookmarksV2.folders[i].subFolders.length); k++) {
						var subFolderChildren = [];	
						
						// check if sub folders contain bookmarks or folders.
						if (data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders != null){
							for (var m=0;(data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders != null) && (m < data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders.length); m++) {
								var subSubFolderChildren = []
								if (data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].bookmarkContentList != null){
									for (var f=0;(data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].bookmarkContentList != null) && (f < data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].bookmarkContentList.length); f++) {
										subSubFolderChildren.push({name: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].bookmarkContentList[f].title, id: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].bookmarkContentList[f].contentId, type: 'bookmark', synopsis: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].bookmarkContentList[f].synopsis, systemTagName: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].bookmarkContentList[f].contentType, sequenceNumber: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].bookmarkContentList[f].sequenceNumber}); 
										
									}
								}								
								
								if (subSubFolderChildren != null){
									subFolderChildren.push({name: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].title, id: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].folderId, type: 'folder', sequenceNumber: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].sequenceNumber, children: subSubFolderChildren});
								}else{
									subFolderChildren.push({name: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].title, id: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].folderId, type: 'folder', sequenceNumber: data.contentBookmarksV2.folders[i].subFolders[k].subSubFolders[m].sequenceNumber});
								}
							}
						}
						
						if (data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.bookmarkContentList != null){
							for (var n=0;(data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.bookmarkContentList != null) && (n < data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.bookmarkContentList.length); n++) {
								subFolderChildren.push({name: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.bookmarkContentList[n].title, id: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.bookmarkContentList[n].contentId, type: 'bookmark', synopsis: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.bookmarkContentList[n].synopsis, systemTagName: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.bookmarkContentList[n].contentType, sequenceNumber: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.bookmarkContentList[n].sequenceNumber}); 
								
							}
						}
							
						// check for sub level folders and bookmarks.
						if (subFolderChildren != null){
							topFolderChildren.push({name: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.title, id: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.folderId, type: 'folder', sequenceNumber: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.sequenceNumber, children: subFolderChildren});
						}else{
							topFolderChildren.push({name: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.title, id: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.folderId, type: 'folder', sequenceNumber: data.contentBookmarksV2.folders[i].subFolders[k].bookmarkFolderContent.sequenceNumber});
						}
					} 
				}
				
					
					// populate top level folder data
				if (data.contentBookmarksV2.folders[i].bookmarkFolderContent.bookmarkContentList != null){

					for (var j=0;(data.contentBookmarksV2.folders[i].bookmarkFolderContent.bookmarkContentList != null) && (j < data.contentBookmarksV2.folders[i].bookmarkFolderContent.bookmarkContentList.length); j++) {
						// populate bookmarks in folders
						topFolderChildren.push({name: data.contentBookmarksV2.folders[i].bookmarkFolderContent.bookmarkContentList[j].title, id: data.contentBookmarksV2.folders[i].bookmarkFolderContent.bookmarkContentList[j].contentId, type: 'bookmark', synopsis: data.contentBookmarksV2.folders[i].bookmarkFolderContent.bookmarkContentList[j].synopsis, systemTagName: data.contentBookmarksV2.folders[i].bookmarkFolderContent.bookmarkContentList[j].contentType, sequenceNumber: data.contentBookmarksV2.folders[i].bookmarkFolderContent.bookmarkContentList[j].sequenceNumber}); 
					}
				}
				if (topFolderChildren != null){ 
					results.push({name: data.contentBookmarksV2.folders[i].bookmarkFolderContent.title, id: data.contentBookmarksV2.folders[i].bookmarkFolderContent.folderId, type: 'folder', sequenceNumber: data.contentBookmarksV2.folders[i].bookmarkFolderContent.sequenceNumber, children: topFolderChildren});						
				}else{
					if (data.contentBookmarksV2.folders[i].subFolders == null){
						results.push({name: data.contentBookmarksV2.folders[i].bookmarkFolderContent.title, id: data.contentBookmarksV2.folders[i].bookmarkFolderContent.folderId, type: 'folder', sequenceNumber: data.contentBookmarksV2.folders[i].bookmarkFolderContent.sequenceNumber});					
					}
				}
			}
			for (var i=0;(data.contentBookmarksV2.bookmarks != null) && (i < data.contentBookmarksV2.bookmarks.length); i++) {
				// populate bookmark data
				results.push({name: data.contentBookmarksV2.bookmarks[i].title, id: data.contentBookmarksV2.bookmarks[i].contentId, type: 'bookmark', synopsis: data.contentBookmarksV2.bookmarks[i].synopsis, systemTagName: data.contentBookmarksV2.bookmarks[i].contentType, sequenceNumber: data.contentBookmarksV2.bookmarks[i].sequenceNumber});	
			}
		}
		
		$('#searchResultsBookmarkTree').tree('loadData', results);

		// Build the tree, adding any options we need.
		$(function() {
		    $('#bookmarkTree').tree({
		        data: results,
				autoOpen: false,
				dragAndDrop: true,
				selectable: true,
			    onCreateLi: function(node, $li, is_selected) {
			    	if (node.type == "folder"){
			    		if (node.children.length == 0){
			    			$li.find('.jqtree-title').before('<span class="sr_lr_icon_tree_EmptyFolder"></span>');
			    		}
			    		$li.find('.jqtree-title').css('color', 'black');
			    		$li.find('.jqtree-title').css('font-size', '14px');
			    	}
			    	// for each content type we need to explicitly state the class to display the icon. Not great code but can't find an alternative.
			    	if (node.systemTagName == "KnowledgeArticleED"){
			    		 $li.find('.jqtree-title').before('<span class="sr_lr_icon_tree sr_lr_icon_tree_KnowledgeArticleED"></span>');
			    	}else{
			    		if (node.systemTagName == "KnowledgeAlertED"){
				    		 $li.find('.jqtree-title').before('<span class="sr_lr_icon_tree sr_lr_icon_tree_KnowledgeAlertED"></span>');
				    	}else{
				    		if (node.systemTagName == "KnowledgeFAQED"){
					    		 $li.find('.jqtree-title').before('<span class="sr_lr_icon_tree sr_lr_icon_tree_KnowledgeFAQED"></span>');
					    	}else{
					    		if (node.systemTagName == "KnowledgeUploadED"){
						    		 $li.find('.jqtree-title').before('<span class="sr_lr_icon_tree sr_lr_icon_tree_KnowledgeUploadED"></span>');
						    	}else{
						    		if (node.systemTagName == "Unstructured"){
							    		 $li.find('.jqtree-title').before('<span class="sr_lr_icon_tree sr_lr_icon_tree_Unstructured"></span>');
							    	}else{
							    		if (node.systemTagName == "pageSet"){
								    		 $li.find('.jqtree-title').before('<span class="sr_lr_icon_tree sr_lr_icon_tree_pageSet"></span>');
								    	}else{
								    		if (node.systemTagName == "KnowledgeSegmentED"){
									    		 $li.find('.jqtree-title').before('<span class="sr_lr_icon_tree sr_lr_icon_tree_KnowledgeSegmentED"></span>');
									    	}else{
									    		if (node.systemTagName == "KnowledgeRemoteDocumentED"){
										    		 $li.find('.jqtree-title').before('<span class="sr_lr_icon_tree sr_lr_icon_tree_KnowledgeRemoteDocumentED"></span>');
										    	}
									    	}
								    	}
							    	}
						    	}
					    	}
				    	}
			    	}
			    },
				onCanMoveTo: function(moved_node, target_node, position) {
					// don't allow more than 3 folders deep on the tree or allow users to drop bookmarks into other bookmarks to create folders.
					
					if ((target_node.getLevel() == 4) || (target_node.type == "bookmark")) {
						// not alowwed inside a bookmark but before and after ok.
						return (position != 'inside');
					}
					else {
						return true;
					}
				}
			    

			    
			    
				// the icons are set in the tree.css as images in the class associated with the state of the jqtree.toggle.	
			    //openedIcon: $('<i class="ui-icon ui-icon-folder-open"></i>'),
			    //closedIcon: $('<i class="ui-icon ui-icon-folder-collapsed"></i>') 
		    });
		});
				
		// bind 'tree.click' event
		$('#bookmarkTree').bind(
		    'tree.click',
		    function(event) {
					// The clicked node is 'event.node'
				var node = event.node;
				//$.fn.bookmarkSelection();
			
		    }
		);
		
		// bind 'tree.select' event
		$('#bookmarkTree').bind(
				'tree.select',
				function(event) {
					if (event.node) {
						// node was selected
						var node = event.node;
						//alert(node.name);
						$.fn.bookmarkSelection();
					}
					else {
						// event.node is null
						// a node was deselected
						// e.previous_node contains the deselected node
					}	 
				}
			
		);
		
		// Add the synopsis as hover text
		/*$('#bookmarkTree').on('mouseover', function(e) {

			  var node = $('#bookmarkTree').tree('getNodeByHtmlElement', e.target);	
			  if ($('#bookmarkTree').tooltip()) {
				  $('#bookmarkTree').tooltip( "destroy" );
			  }
				$( '#bookmarkTree' ).tooltip({
				      items: "span, a",
				      content: function() {
				        var element = $( this );
				        if ( element.is( "a" ) ) {
				          return 'Click me to expand/collapse.';
				        }
				        if ( element.is( "span" ) ) {
				        	if (node != null){
				        		return node.synopsis;
				        	}
				        }
				      }
				    });
				$('input').tooltip();
			});*/
		
		//check level that's being dropped to to prevent more than 3 levels deep
		$('#bookmarkTree').bind(
				'tree.move',
				function(event) {				    
					var level = event.move_info.target_node.getLevel();
					
					if (level >= 4){
						alert('Unable to add further folder levels');
						return null;
					}
					
					event.preventDefault();
					event.move_info.do_move();
					
									
					//alert('oroginal sequence number = ' + event.move_info.moved_node.sequenceNumber + ', position = ' + event.move_info.position + ', this sequence number: ' + event.move_info.target_node.sequenceNumber);
					//alert('target Node Type = ' + event.move_info.target_node.type);
					//alert('Move Info Position = ' + event.move_info.position + ', Move Info Previous Parent = ' + event.move_info.previous_parent);
					//alert('moved node = ' + event.move_info.moved_node + ', target node = ' + event.move_info.target_node); 
					//alert('moved node index = ' + event.move_info.moved_node.index + ', target node index = ' + event.move_info.target_node.index);
				}	
		);
		
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
		//$.fn.serviceCall('GET', '', searchServiceName + 'km/knowledge/bookmarks?page=' + 1 + '&size=' + 100, SEARCH_SERVICE_TIMEOUT, callBack);
		$.fn.serviceCall('GET', '', searchServiceName + 'km/bookmarksv2/listallbookmarks', SEARCH_SERVICE_TIMEOUT, callBack); 


		//$('#bookmark-button-up').removeClass('bookmark_button_active');
		//$('#bookmark-button-down').removeClass('bookmark_button_active');
		//$('#bookmark-button-up').attr('disabled', 'disabled');
		//$('#bookmark-button-down').attr('disabled', 'disabled');
	}
	
