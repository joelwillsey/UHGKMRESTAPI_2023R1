var reorderEvent = null;

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
		// only enable view for bookmarks, not folders.
		if (node.type == "folder"){
			alert('Please select a bookmark to view.');
		}else{
			$.fn.launchViewContent(node.id);
		}
		
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
			alert('Please empty folder before removing.');
		}else{
				
		// remove a folder
			if (node.type == "folder"){
				$.fn.serviceCall('GET', '', searchServiceName + 'km/bookmarksv2/manage?folderID='+node.id+'&userAction=REMOVEFOLDER', SEARCH_SERVICE_TIMEOUT, function(data){
					$.fn.populateBookmarks(data);
				});
			}else{
				$.fn.serviceCall('POST', '', searchServiceName + 'km/bookmark/remove?contentid=' + node.id, SEARCH_SERVICE_TIMEOUT, $.fn.manageCallback); 
				$.fn.serviceCall('GET', '', searchServiceName + 'km/bookmarksv2/listallbookmarks', SEARCH_SERVICE_TIMEOUT, function(data){
					$.fn.populateBookmarks(data);
				});
			}
			
		}
		
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

		$('#bookmark-button-view').addClass('bookmark_action_button_active');
		$('#bookmark-button-remove').addClass('bookmark_action_button_active');
		$('#bookmark-button-new-folder').addClass('bookmark_action_button_active');
		$('#bookmark-button-rename').addClass('bookmark_action_button_active');

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

		log("Populating Bookmarks");
		
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

		// Build the tree, adding any options we need.
		$(function() {
		    $('#bookmarkTree').tree({
		        data: results,
				autoOpen: true,
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
					
					// Can only drop into folders
					if (moved_node.type == "bookmark" && target_node.type == "folder"){
						return (position != 'before');
					}
					
					// only allow folders to ne dropped before bookmarks.
					if ((target_node.type == "bookmark" && moved_node.type == "folder") && position != 'before'){
						return false;
					}
					
					
					// prevent more than 4 tiers
					if (moved_node.type == "folder" && target_node.getLevel() == 3){ 
						return false;
					} 
					if (moved_node.type == "folder" && target_node.getLevel() == 2){ 
						if (moved_node.children != null){
							for (var w=0;(moved_node.children != null) && (w < moved_node.children.length); w++) {
								// if moving to level 2 and the folder's children have children then they will be level 4 so not allowed.
								if (moved_node.children[w].children.length >0){
									return false;
								}
							}
						}
					}
					if (moved_node.type == "folder" && target_node.getLevel() == 1){ 
						if (moved_node.children != null){
							for (var y=0;(moved_node.children != null) && (y < moved_node.children.length); y++) {
								// if moving to level 2 and the folder's children have children then they will be level 4 so not allowed.
								if (moved_node.children[y].children != null){
									for (var x=0;(moved_node.children[y].children != null) && (x < moved_node.children[y].children.length); x++) {
										if (moved_node.children[y].children[x].children.length > 0){
											return false;
										}
									}	
								}
							}
						}
					}
					
					// just setting up a true return for folders writing to above, below and inside other folders once we have made it through the above checks.
					if (moved_node.type == "folder" && target_node.type == "folder") {
						return true;
					}
					
					
					// do not allow a bookmark to be dropped inside another bookmark
					if (moved_node.type == "bookmark" && target_node.type == "bookmark") {
							// not alowwed inside a bookmark but before and after ok.
						return (position != 'inside');
					}
						else {
						return true;
					}
				}

		    });
		});
				
		$('#searchResultsBookmarkTree').tree('loadData', results);
		$('#bookmarkTree').tree('loadData', results);
		
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
		$('#bookmarkTree').on('mouseover', function(e) {

			  var node = $('#bookmarkTree').tree('getNodeByHtmlElement', e.target);	
			  if ($('#bookmarkTree').tooltip()) {
				  $('#bookmarkTree').tooltip( "destroy" );  
				  //$("#bookmarkTree").parents('div').remove();  
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
			});
		
		//check level that's being dropped to to prevent more than 3 levels deep
		$('#bookmarkTree').bind(
				'tree.move',
				function(event) {				    
					
					// remove tooltip if any exists on move
					  if ($('#bookmarkTree').tooltip()) {
						  $('#bookmarkTree').tooltip( "destroy" );  
						  //$("#bookmarkTree").parents('div').remove();  
					  } 
					
					var level = event.move_info.target_node.getLevel();
					var moveDirection = null;
					var noOfMoveSpaces;
					var id;
					var type;
					var parentFolderId;
					var position;
					var parameterString = null;
					log("tree.move event below:");
					log (event);
					
					var targetNode = event.move_info.target_node;
					var currentNode = event.move_info.moved_node;
					var seqNbrs = $.fn.getSequenceNumbers(currentNode, targetNode);
					
					// prevent the default and create a move info event so that we can grab the details.
					event.preventDefault(); 
					event.move_info.do_move();
					
					id = event.move_info.moved_node.id;
					type = event.move_info.moved_node.type;
					position = event.move_info.position; 

					
					// Iain Vize - commented this out for the moment, please do not remove.
					/*
					var match = false;
					var nextFound = false;
					var nextBookmarkSequenceNumber;
					var targetId = event.move_info.target_node.id;
					loop1: for (var res=0;(results != null) && (res < results.length); res++) {
						
							if (match){
						// see if next in the loop is a bookmark
								if (results[res].type == "bookmark"){
									alert('1. next bookmark title is '+results[res].name);
									match = false;
									break loop1;
								}
							}
							if (results[res].id == targetId){ 
								alert ('1. target id matched');
								alert ('1. found matching bookmark res id = '+results[res].id+', target id ='+targetId);
								match = true;

							}else{
								// check for children
								if (results[res].children != null){
									// loop round any children
									for (var resChildren=0;(results[res].children != null) && (resChildren < results[res].children.length); resChildren++) {
										if (match){
											// see if next in the loop is a bookmark
											if (results[res].children[resChildren].type == "bookmark"){
												alert('2. next bookmark title is '+results[res].children[resChildren].name);
												match = false;
												break loop1;
											}
										}
										if (results[res].children[resChildren].id == targetId){ 
											alert ('2. target id matched');
											alert ('2. found matching bookmark res id = '+results[res].children[resChildren].id+'target id ='+targetId);
											match = true;
										}else{
											// check for children of children
											if (results[res].children[resChildren].children != null){
												for (var resChildrenChildren=0;(results[res].children[resChildren].children != null) && (resChildrenChildren < results[res].children[resChildren].children.length); resChildrenChildren++) {
													if (match){
														// see if next in the loop is a bookmark
														if (results[res].children[resChildren].children[resChildrenChildren].type == "bookmark"){
															alert('3. next bookmark title is '+results[res].children[resChildren].children[resChildrenChildren].name);
															match = false;
															break loop1;
														}
													}													
													if (results[res].children[resChildren].children[resChildrenChildren].id == targetId){ 
														alert ('3. target id matched');
														alert ('3. found matching bookmark res id = '+results[res].children[resChildren].children[resChildrenChildren].id+'target id ='+targetId);
														match = true;
													}else{
														// last children check due to constraints on the table limiting the number of nodes.
														if (results[res].children[resChildren].children[resChildrenChildren].children != null){
															for (var resChildrenChildrenChildren=0;(results[res].children[resChildren].children[resChildrenChildren].children != null) && (resChildrenChildrenChildren < results[res].children[resChildren].children[resChildrenChildren].children.length); resChildrenChildrenChildren++) {
																if (match){
																	// see if next in the loop is a bookmark
																	if (results[res].children[resChildren].children[resChildrenChildren].children[resChildrenChildrenChildren].type == "bookmark"){
																		alert('4. next bookmark title is '+results[res].children[resChildren].children[resChildrenChildren].children[resChildrenChildrenChildren].name);
																		match = false;
																		break loop1;
																	}
																}			
																if (results[res].children[resChildren].children[resChildrenChildren].children[resChildrenChildrenChildren].id == targetId){
																	alert ('4. target id matched');		
																	alert ('4. found matching bookmark res id = '+results[res].children[resChildren].children[resChildrenChildren].children[resChildrenChildrenChildren].id+'target id ='+targetId);
																	match = true;
																}
															}		
														}
													}
												}
											}
										}
									}
								}
							}
						}
						 
					*/
					
					// call reorder on every move
					//////alert('oroginal sequence number = ' + event.move_info.moved_node.sequenceNumber + ', position = ' + event.move_info.position + ', this sequence number: ' + event.move_info.target_node.sequenceNumber);
					
					// If position is inside then we are either dumping in a folder for the first time, or we are dumping the bookmark at the top of the folder
					//either way we need to get the next bookmark to see work out the move direction and move spaces.
					if (type == "bookmark" && position == "inside"){
						// get selected node so we can 
						var nextNode = event.move_info.moved_node.getNextNode();
						// now check if it's a folder or bookmark.
						/////alert('next node type = ' + nextNode.type);
						if (nextNode != null){
							if (nextNode.type == "bookmark"){
								if (nextNode.sequenceNumber > event.move_info.moved_node.sequenceNumber){
									moveDirection = "DOWN";
									noOfMoveSpaces = nextNode.sequenceNumber - event.move_info.moved_node.sequenceNumber;
								}else{
									moveDirection = "UP";
									noOfMoveSpaces = event.move_info.moved_node.sequenceNumber - nextNode.sequenceNumber;
									if (event.move_info.position == "after"){
										noOfMoveSpaces = noOfMoveSpaces - 1;
									}
								}
							} else{
								// try again for next bookmark.
								var nextNextNode = nextNode.getNextNode();
								////alert('next next node type = ' + nextNextNode.type);
								if (nextNextNode.type == "bookmark"){
									if (nextNextNode.sequenceNumber > event.move_info.moved_node.sequenceNumber){
										moveDirection = "DOWN";
										noOfMoveSpaces = nextNextNode.sequenceNumber - event.move_info.moved_node.sequenceNumber;
									}else{
										moveDirection = "UP";
										noOfMoveSpaces = event.move_info.moved_node.sequenceNumber - nextNextNode.sequenceNumber;
										if (event.move_info.position == "after"){
											noOfMoveSpaces = noOfMoveSpaces - 1;
										}
									}
								}
							}
						}else{
							// only one bookmark so we can set the direction to up and the move nummber to zero
							moveDirection = "UP";
							noOfMoveSpaces = 0;
						}
					}
					
					
					
					
					// first add the id of the item we are moving to the parameterString.
					if (type == "folder"){
						parameterString = "folderID="+id;
					}else{
						parameterString = "contentID="+id;
					}
					
					
					// if we are puting a bookmark into a folder then the destination position is inside, we can then grab the destination Id.
					if (position == "inside"){
						parameterString = parameterString + "&destFolder=" + event.move_info.target_node.id;
					}else{
						//if position is not inside then check for a parent folder so we have a dest folder id
						if (event.move_info.target_node.parent.id != null){
							parameterString = parameterString + "&destFolder=" + event.move_info.target_node.parent.id;
						}
					}
			
					
					// figure out direction and amount of spaces moved
					if (moveDirection == null){
						if (event.move_info.target_node.sequenceNumber > event.move_info.moved_node.sequenceNumber){
							moveDirection = "DOWN";
							noOfMoveSpaces = event.move_info.target_node.sequenceNumber - event.move_info.moved_node.sequenceNumber;
							//noOfMoveSpaces = noOfMoveSpaces + 1; 
							/////alert ('move dowm '+noOfMoveSpaces+' spaces.');
						}else{
							moveDirection = "UP";
							noOfMoveSpaces = event.move_info.moved_node.sequenceNumber - event.move_info.target_node.sequenceNumber;
							if (event.move_info.position == "after"){
								noOfMoveSpaces = noOfMoveSpaces - 1;
							}
							/////alert ('move up '+noOfMoveSpaces+' spaces.');
						}
					}
					
					// add move direction to the parameter string
					if (moveDirection != null){
						parameterString = parameterString + "&direction=" + moveDirection;
					}
					
					// add moved amount of spaces to the parameter string
					if (noOfMoveSpaces != null){
						parameterString = parameterString + "&numMoved=" + noOfMoveSpaces
					}
					
					
					if (parameterString != null){
						/////alert('parameterString = '+ parameterString);
						
						
						//check to see if this is the same event (sometimes the same event gets fired multiple times) if not let it through
						if (typeof reorderEvent == 'undefined' || reorderEvent == null || reorderEvent != event){
							//store the event for comaparsion
							log("Calling km/bookmarksv2/reorder?" + parameterString);
							reorderEvent = event;
							$.fn.serviceCall('GET', '', searchServiceName + 'km/bookmarksv2/reorder?'+parameterString, SEARCH_SERVICE_TIMEOUT, function(data){								
								$.fn.populateBookmarks(data);
								//we are done processing clear the event
								reorderEvent = null;
							});
						} else {
							log('Received same event that is already processing, ignoring it.')
						}
					}
					
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
	
	$.fn.getSequenceNumbers = function(currentNode, targetNode){
		//TODO  Not sure if this is working yet it appears to be going to the next node that is showing so i skipping over the non expanded nodes
		
		var nextSequenceBookmark =  null;
		var nextSequenceFolder =  null;
		var nextSequenceBookmarkId =  null;
		var nextSequenceFolderId =  null;
		var nextParentFolderId =  null;
		var foundNextSequenceBookmark = false;
		var foundNextSequenceFolder = false;
		var previousSequenceBookmark =  null;
		var previousSequenceFolder =  null;
		var previousSequenceBookmarkId =  null;
		var previousSequenceFolderId =  null;
		var previousParentFolderId =  null;
		var foundPreviousSequenceBookmark = false;
		var foundPreviousSequenceFolder = false;
		
		log('currentNode type: ' +  currentNode.type + " id: " + currentNode.id + ' sequenceNumber: ' + currentNode.sequenceNumber);
		log('targetNode type: ' +  targetNode.type + " id: " + targetNode.id + ' sequenceNumber: ' + targetNode.sequenceNumber);
		var nextNode = targetNode.getNextNode();
	
		while ((!foundNextSequenceBookmark || !foundNextSequenceFolder) && nextNode != null){			
			log('nextNode type: ' +  nextNode.type + " id: " + nextNode.id + ' sequenceNumber: ' + nextNode.sequenceNumber);
			if (nextNode == currentNode){
				//we found the current node no need to search any more
				if (!foundNextSequenceBookmark && nextNode.type == 'bookmark'){
					nextSequenceBookmark = nextNode.sequenceNumber;;
					nextSequenceBookmarkId =  nextNode.id;
					foundNextSequenceBookmark = true;
					log('End of the node list no next bookmark sequenceNumber');
				}
				if (!foundNextSequenceFolder && nextNode.type == 'folder'){
					nextSequenceFolder =  nextNode.sequenceNumber;
					nextParentFolderId - nextNode.parent.id;;
					nextSequenceFolderId =  nextNode.id;
					foundNextSequenceFolder = true;
				}
			}				
			if (nextNode.type == "folder" && !foundNextSequenceFolder) {
				//this is the next folder
				nextSequenceFolder =  nextNode.sequenceNumber;
				nextSequenceFolderId =  nextNode.id;
				nextParentFolderId = nextNode.parent.id;
				foundNextSequenceFolder = true;
				log('Found next folder - sequenceNumber: ' + nextNode.sequenceNumber);
			} else if (nextNode.type == "bookmark" && !foundNextSequenceBookmark){
				//this is the next bookmark
				nextSequenceBookmark =  nextNode.sequenceNumber;
				nextSequenceBookmarkId =  nextNode.id;
				foundNextSequenceBookmark = true;
				log('Found next bookmark - sequenceNumber: ' + nextNode.sequenceNumber);
			}
			
			nextNode = nextNode.getNextNode();
		}
		
		var previousNode = targetNode.getPreviousNode();
		
		while ((!foundPreviousSequenceBookmark || !foundPreviousSequenceFolder) && previousNode != null){			
			log('previousNode type: ' +  previousNode.type + " id: " + previousNode.id + ' sequenceNumber: ' + previousNode.sequenceNumber);
			if (previousNode == currentNode){
				//we found the current node no need to search any more
				if (!foundPreviousSequenceBookmark  && previousNode.type == 'bookmark'){
					previousSequenceBookmark = previousNode.sequenceNumber;
					previousSequenceBookmarkId = previousNode.id;
					foundPreviousSequenceBookmark = true;
					log('End of the node list no next bookmark sequenceNumber');
				}
				if (!foundPreviousSequenceFolder  && previousNode.type == 'folder'){
					previousSequenceFolder =  previousNode.sequenceNumber;
					previousSequenceFolderId =  previousNode.id;
					previousParentFolderId = previousNode.parent.id;
					foundPreviousSequenceFolder = true;

				}
			}					
			if (previousNode.type == "folder" && !foundPreviousSequenceFolder) {
				//this is the next folder
				previousSequenceFolder =  previousNode.sequenceNumber;
				previousSequenceFolderId =  previousNode.id;
				previousParentFolderId = previousNode.parent.id;
				foundPreviousSequenceFolder = true;
				log('Found previous folder - sequenceNumber: ' + previousNode.sequenceNumber);
			} else if (previousNode.type == "bookmark" && !foundPreviousSequenceBookmark){
				//this is the next bookmark
				previousSequenceBookmark =  previousNode.sequenceNumber;
				previousSequenceBookmarkId =  previousNode.id;
				foundPreviousSequenceBookmark = true;
				log('Found previous bookmark - sequenceNumber: ' + previousNode.sequenceNumber);
			}
			
			previousNode = previousNode.getPreviousNode();
		}		
				
		var data = {
				nextSequenceBookmark: nextSequenceBookmark,
				nextSequenceBookmarkId: nextSequenceBookmarkId,
				nextSequenceFolder: nextSequenceFolder,
				nextSequenceFolderId: nextSequenceFolderId,
				nextParentFolderId: nextParentFolderId,
				previousSequenceBookmark:  previousSequenceBookmark,
				previousSequenceBookmarkId:  previousSequenceBookmarkId,
				previousSequenceFolder: previousSequenceFolder,
				previousSequenceFolderId: previousSequenceFolderId,
				previousParentFolderId: previousParentFolderId
			};		
		log(data);
		return data;
	}
