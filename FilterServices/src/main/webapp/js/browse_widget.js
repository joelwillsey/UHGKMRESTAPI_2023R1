	// Create the tree filter
	$.fn.createTreeFilter = function(tagList, startTag, type, started) {
		var retValue = '';
		if (typeof tagList != 'undefined' && tagList != null && tagList.length > 0 &&
				typeof startTag != 'undefined') {
			var parentList = [];
			var lastParent = '';
			var tagsHtml = [];
			var firstTime = true;
			var lastTag;

			// Check if we want this to be the first
			if (started) {
				parentList.push(tagList[0]);
				lastParent = tagList[0].parentTagName;				
			}

			// Loop through the whole list
			for (var x = 0; x < tagList.length; x++) {
				// Look for the starting location
				if (tagList[x].systemTagName === startTag) {
					started = true;
					tagList[x].parentTagName = tagList[x].systemTagName;
					parentList.push(tagList[x]);
					lastParent = tagList[x].systemTagName;
				} else if (started) { // Past the start tag
					// If this is the first time in, setup the data
					if (firstTime) {
						tagsHtml.push('<ul id="ul-filter-section-tree" class="fs_di_tagset_list">');
						tagsHtml.push('  <li class="fs_di_tagset_list_li">');
						tagsHtml.push('    <a id="tag-' + tagList[x].systemTagName + '" class="fs_di_tl_li_link" value="' + tagList[x].systemTagDisplayName + '" onclick="$.fn.tagSelected(\'' + type + '\', \'' + tagList[x].systemTagName + '\');" href="javascript:void(0);" title="' + tagList[x].systemTagDisplayName + '">');
						tagsHtml.push('      <i class="fs_di_tl_li_icon fs_arrow ion-pricetag"></i>');
						tagsHtml.push('      <span id="tag-span-' + tagList[x].systemTagName + '">' + tagList[x].systemTagDisplayName + '</span>');
						firstTime = false;
					} else {
						// Check if my parent is on the same level
						if (tagList[x].parentTagName === lastParent) {
							tagsHtml.push('      <i class="fs_di_tl_li_rmore_i fs_arrow ion-chevron-right"></i>');
							tagsHtml.push('    </a>');
							tagsHtml.push('  </li>');
							tagsHtml.push('  <li class="fs_di_tagset_list_li">');
							tagsHtml.push('    <a id="tag-' + tagList[x].systemTagName + '" class="fs_di_tl_li_link" value="' + tagList[x].systemTagDisplayName + '"  onclick="$.fn.tagSelected(\'' + type + '\', \'' + tagList[x].systemTagName + '\');" href="javascript:void(0);" title="' + tagList[x].systemTagDisplayName + '">');
							tagsHtml.push('      <i class="fs_di_tl_li_icon fs_arrow ion-pricetag"></i>');
							tagsHtml.push('      <span id="tag-span-' + tagList[x].systemTagName + '">' + tagList[x].systemTagDisplayName + '</span>');
						} else {
							// Check to see if the parent is already one of the tags
							var parentFound = false;
							for (var i = 0; i < parentList.length; i++) {
								if (parentList[i].parentTagName === tagList[x].parentTagName) {
									parentFound = true;
									break;
								}
							}
							// Check if we are unwinding the tree
							if (parentFound) {
								var pLength = parentList.length;
								tagsHtml.push('        </a>');
								for (var i = 0; i < pLength; i++) {
									var nextInList = parentList.pop();
									// Do we need to get out of the loop?
									if (typeof nextInList != 'undefined' && nextInList.parentTagName === tagList[x].parentTagName) {
										lastParent = nextInList.parentTagName;
										parentList.push(nextInList);
										break;
									}
									if (i === (pLength - 1)) {
										parentList.push(nextInList);
									}
									// Now add an ending
									tagsHtml.push('      </li>');
									tagsHtml.push('    </ul>');
								}
								tagsHtml.push('  <li class="fs_di_tagset_list_li">');
								tagsHtml.push('    <a id="tag-' + tagList[x].systemTagName + '" class="fs_di_tl_li_link" value="' + tagList[x].systemTagDisplayName + '" onclick="$.fn.tagSelected(\'' + type + '\', \'' + tagList[x].systemTagName + '\');" href="javascript:void(0);" title="' + tagList[x].systemTagDisplayName + '">');
								tagsHtml.push('      <i class="fs_di_tl_li_icon fs_arrow ion-pricetag"></i>');
								tagsHtml.push('      <span id="tag-span-' + tagList[x].systemTagName + '">' + tagList[x].systemTagDisplayName + '</span>');
							} else {
								tagsHtml.push('    </a>');
								tagsHtml.push('    <a id="expand-' + lastTag.systemTagName + '" class="fs_di_tl_li_more" onclick="$.fn.expandTag(\'' + lastTag.systemTagName + '\');" href="javascript:void(0);">');
								tagsHtml.push('      <i id="ie-' + lastTag.systemTagName + '" class="fs_di_tl_li_right fs_arrow ion-plus-round"></i>');
								tagsHtml.push('    </a>');
								tagsHtml.push('    <a id="arrow-' + lastTag.systemTagName + '" class="fs_di_tl_li_lmore" onclick="$.fn.expandArrowTag(\'' + lastTag.systemTagName + '\');" href="javascript:void(0);">');
								tagsHtml.push('      <i id="i-' + lastTag.systemTagName + '" class="fs_arrow ion-chevron-right"></i>');
								tagsHtml.push('    </a>');
								tagsHtml.push('    <ul id="ul-' + lastTag.systemTagName + '" class="fs_di_tagset_list fs_tree_off">');
								tagsHtml.push('      <li class="fs_di_tagset_list_li">');
								tagsHtml.push('        <a id="tag-' + tagList[x].systemTagName + '" class="fs_di_tl_li_link" value="' + tagList[x].systemTagDisplayName + '" onclick="$.fn.tagSelected(\'' + type + '\', \'' + tagList[x].systemTagName + '\');" href="javascript:void(0);" title="' + tagList[x].systemTagDisplayName + '">');
								tagsHtml.push('          <i class="fs_di_tl_li_icon fs_arrow ion-pricetag"></i>');
								tagsHtml.push('          <span id="tag-span-' + tagList[x].systemTagName + '">' + tagList[x].systemTagDisplayName + '</span>');
								parentList.push(tagList[x]);
								lastParent = tagList[x].parentTagName;
							}
						}
					}
				}
				lastTag = tagList[x];
			}
			tagsHtml.push('      <i class="fs_di_tl_li_rmore_i fs_arrow ion-chevron-right"></i>');
			tagsHtml.push('    </a>');
			// Unwind the tree
			for (var i = 0; i < parentList.length; i++) {
				tagsHtml.push('    </li>');
				tagsHtml.push('  </ul>');
			}
			parentList.length = 0;
			retValue = tagsHtml.join('\n');
			tagsHtml.length = 0;
		}
		return retValue;
	}

	// Expand Tag
	$.fn.expandTag = function(data) {
		if ($('#ul-' + data).hasClass('fs_tree_off')) {
			$('#ul-' + data).removeClass('fs_tree_off');
			$('#ul-' + data).addClass('fs_tree_on');
			$('#ie-' + data).removeClass('ion-plus-round');
			$('#ie-' + data).addClass('ion-minus-round');
		} else {
			$('#ul-' + data).removeClass('fs_tree_on');
			$('#ul-' + data).addClass('fs_tree_off');			
			$('#ie-' + data).removeClass('ion-minus-round');
			$('#ie-' + data).addClass('ion-plus-round');
		}
	}

	// Expand Tag Arrow
	$.fn.expandArrowTag = function(data) {
		if ($('#ul-' + data).hasClass('fs_tree_off')) {
			$('#ul-' + data).removeClass('fs_tree_off');
			$('#ul-' + data).addClass('fs_tree_on');
			$('#i-' + data).removeClass('ion-chevron-right');
			$('#i-' + data).addClass('ion-chevron-down');
		} else {
			$('#ul-' + data).removeClass('fs_tree_on');
			$('#ul-' + data).addClass('fs_tree_off');			
			$('#i-' + data).removeClass('ion-chevron-down');
			$('#i-' + data).addClass('ion-chevron-right');
		}
	}

	// Tag Selected
	$.fn.tagSelected = function(type, data) {
		// if this is a topic tag, first remove the current one
		if (data.indexOf("topic") != -1) {
			$('#ul-filter-section-tree li a span').each(
				function(index){  
					var span = $(this);
					if (span.hasClass('tree_selected')) {
						span.removeClass('tree_selected');
					} else {
						span.removeClass('tree_selected');
					}
				}
			);
			$.fn.addToSearchCloud('topic', data);	
		} else {
			// check if already selected
			if ($('#tag-span-' + data).hasClass('tree_selected')) {
				return;
			} else {
				$.fn.tagClick(type, data);
			}
			
//			$.fn.addToSearchCloud(data, data);
		}
		//$('#tag-span-' + data).addClass('tree_selected');

		var tags = $.fn.getAllTags();
		var selectedContentTypes = $.fn.getSelectedContentTags();
		$(".dpui-widget").trigger("dpui:setupTags", tags);
		$(".dpui-widget").trigger("dpui:setupContentTypeTags", selectedContentTypes);
		$(".dpui-widget").trigger("dpui:runSearch");
	}