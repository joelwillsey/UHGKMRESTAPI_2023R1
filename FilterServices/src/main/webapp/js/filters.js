var  UnauthorizedPageRedirect = '/verintkm/unauthorized.html.html';

$(document).ready(function() {

	// Service Cloud arrow link
	$('#fs-li-cloud').on('click', function() {
		$.fn.changeArrow('#fs-cloud-data', '#fs-i-cloud');
	});

	// Browse arrow link
	$('#fs-li-browse').on('click', function() {
		$.fn.changeArrow('#fs-browse-data', '#fs-i-browse');
	});

	// Filters arrow link
	$('#fs-li-filters').on('click', function() {
		$.fn.changeArrow('#fs-filters-data', '#fs-i-filters');
	});

	// Service Cloud Reset link
	$('.fs_cloud_reset').on('click', function() {    
		var kbasename = 'null';
		$('.ul_all_tags li').each(function(index) {
			log("Index: " + index);
			var li = $(this);
			log("li: " + li);
			var id = li.attr('id');
			id = id.substring(3);
			var tagtype = $('#' + li.attr('id') + ' a').attr('tagtype');
			
			// check if we have kbase tag (should always be there)
			var topicTag = li.context.id.substring(
					0, 8);
			if (topicTag == 'sc-kbase') {
				// grab the name for use later.
				kbasename = id;
			}
			
			if (typeof tagtype != 'undefined'){
				$.fn.removeTag(tagtype, id);
			}
												
			li.remove();
			
		});

		$('#ul-filter-section-tree li a span').each(function(index) {
			var a = $(this);
			if (a.hasClass('tree_selected')) {
				a.removeClass('tree_selected');
			}
		});
		$('.ul_all_tags').html('');
		$('.fs_dt_info_label').css('display', 'block');
		$(".dpui-widget").trigger("dpui:resetSearch",'*');
		
		// reset search removes the kbase tag but doesn't handle removing from the kbase field.
		// add tag again and remove correctly. tagClick will set up the cross tags/remove and add etc.
		if (kbasename != 'null' ){
			$.fn.addToSearchCloud('kbase', kbasename);
			$.fn.tagClick('kbase', kbasename);
		}

	});

	// Topic Reset button
	$('#fs-reset-topic').on('click',function() {
		$('.ul_all_tags li').each(function(index) {
			var li = $(this);
			var topicTag = li.attr('tagtype');
			if (typeof topicTag != 'undefined' && topicTag != '') {
				li.remove();
			}
		});
		$('#ul-filter-section-tree li a span').each(function(index) {
			var a = $(this);
			if (a.hasClass('tree_selected')) {
				a.removeClass('tree_selected');
			}
		});

		// Send inter-widget communication
		$.fn.widgetCommunication();
	});

	// Product/Region Reset button
	$('#fs-reset-button').on('click',function() {
		$('#kbase-selection li').each(function(index) {
			log("KBase Index: " + index);
			var id = $(this).attr('id');
			/*if (typeof id != 'undefined'
					&& id != 'kbase-search-input') {
				$.fn.removeTag('kbase',id);
			}*/
		});
		$('#cntnttype-selection li').each(function(index) {
			console.log(index);
			var id = $(this).attr('id');
			if (typeof id != 'undefined' && id != 'cntnttype-search-input') {
				$.fn.removeTag('cntnttype', id);
			}
		});
		$('#product-selection li').each(function(index) {
			console.log(index);
			var id = $(this).attr('id');
			if (typeof id != 'undefined' && id != 'product-search-input') {
				$.fn.removeTag('product',id);
			}
		});
		$('#region-selection li').each(function(index) {
			log(index);
			var id = $(this).attr('id');
			if (typeof id != 'undefined' && id != 'region-search-input') {
				$.fn.removeTag('region',id);
			}
		});
		$('#div-content-tags div input').each(function(index) {
			var input = $(this);
			input.prop('checked',true);
		});
		$('#filter-content-id').val('');

		// Send inter-widget communication
		$.fn.widgetCommunication();
	});

	// Apply button
	$('#fs-apply-button').on('click',function() {
		$('.ul_all_tags li').each(function(index) {
			var li = $(this);
			log(li);
			var id = li.attr('id');
			if (id === 'sc-ContentTypes') {
				li.remove();
			}
		});
		var contentCollection = '';
		$('#div-content-tags div input').each(function(index) {
			var input = $(this);
			if (input.is(":checked")) {
				// it is checked
				contentCollection += input.attr('value') + ' ';
			}
		});
		var buildLi = '<li id="sc-ContentTypes' + '" class="search-choice search-choice-cloud" title="Content Types">';
		buildLi += '<span>' + contentCollection.substring(0, 12) + '...</span>';
		buildLi += '</li>';
		$('.ul_all_tags').append(buildLi);

		// Send inter-widget communication
		$(".dpui-widget").trigger("dpui:setupPublishedid", $('#filter-content-id').val());
		$.fn.widgetCommunication();
	});

	// kbase Add Filter
	$('#fs-di-tagset-kbase-add').on('click', function() {
		filterType = 'kbase';
		$.fn.addFilter('#div-kbase-tags');
	});

	// cntnttype Add Filter
	$('#fs-di-tagset-cntnttype-add').on('click', function() {
		filterType = 'cntnttype';
		$.fn.addFilter('#div-cntnttype-tags');
	});

	// Product Add Filter
	$('#fs-di-tagset-product-add').on('click', function() {
		filterType = 'product';
		$.fn.addFilter('#div-product-tags', '#ul-product-tags');
		// $.fn.addFilter('#div-product-tags');
	});

	// Region Add Filter
	$('#fs-di-tagset-region-add').on('click', function() {
		filterType = 'region';
		$.fn.addFilter('#div-region-tags', '#ul-region-tags');
		// $.fn.addFilter('#div-region-tags');
	});

	// Product input field
	$("#product-input").keyup(function(e) {
		var keyCode = e.keyCode || e.which;
		$.fn.filter(this, '#ul-product-tags', keyCode, 'product');
	});

	// Region input field
	$("#region-input").keyup(function(e) {
		var keyCode = e.keyCode || e.which;
		$.fn.filter(this, '#ul-region-tags', keyCode,'region');
	});

	// kbase Input popup
	$('#kbase-selection').click(function() {
		// close all of the other popups
		$('#cntnttype-tags').hide();
		$('#product-tags').hide();
		$('#region-tags').hide();
		$.fn.showPopup('#kbase-tags','#ul-kbase-tags'); 
		$(document).click(function(e) {
			var eTarget = $(e.target);
			var cl = $(e.target).closest('#ul-kbase-tags').length;
			var l = cl.length;
			if ($(e.target).closest('#ul-kbase-tags').length != 0 || $(e.target).closest('#kbase-selection').length)
				return false;
			$('#kbase-tags').hide();
			$(document).unbind("click");
		});
	});

	// cntnttype Input popup
	$('#cntnttype-selection').click(function() {
		// close all of the other popups
		$('#kbase-tags').hide();
		$('#product-tags').hide();
		$('#region-tags').hide();
		$.fn.showPopup('#cntnttype-tags','#ul-cntnttype-tags'); 
		// sorts the list alphabetically
		$.fn.sortList('#ul-cntnttype-tags');
		$(document).click(function(e) {
			var eTarget = $(e.target);
			var cl = $(e.target).closest('#ul-cntnttype-tags').length;
			var l = cl.length;
			if ($(e.target).closest('#ul-cntnttype-tags').length != 0 || $(e.target).closest('#cntnttype-selection').length)
				return false;
			$('#cntnttype-tags').hide();
			$(document).unbind("click");
		});
	});

	// Product Input popup
	$('#product-selection').click(function() {
		// close all of the other popups
		$('#kbase-tags').hide();
		$('#cntnttype-tags').hide();
		$('#region-tags').hide();

		$.fn.showPopup('#product-tags','#ul-prodct-tags'); 
		// sorts the list alphabetically
		$.fn.sortList('#ul-prodct-tags');

		$(document).click(function(e) {
			var eTarget = $(e.target);
			var cl = $(e.target).closest('#ul-prodct-tags').length;
			var l = cl.length;
			if ($(e.target).closest('#ul-prodct-tags').length != 0 || $(e.target).closest('#product-selection').length)
				return false;
			$('#product-tags').hide();
			$(document).unbind("click");
		});
	});

	// Region Input popup
	$('#region-selection').click(function() {
		// close all of the other popups
		$('#kbase-tags').hide();
		$('#cntnttype-tags').hide();
		$('#product-tags').hide();

		$.fn.showPopup('#region-tags', '#ul-region-tags');

		// sorts the list alphabetically
		$.fn.sortList('#ul-region-tags');
		$(document).click(function(e) {
			var eTarget = $(e.target);
			var cl = $(e.target).closest('#ul-region-tags').length;
			var l = cl.length;
			if ($(e.target).closest('#ul-region-tags').length != 0 || $(e.target).closest('#region-selection').length)
				return false;
			$('#region-tags').hide();
			$(document).unbind("click");
		});
	});

	// BEGIN: SUPPORTING BUTTON/LINK METHODS
	// Add Filter supporting method
	$.fn.addFilter = function(name, ulList) {
		$(name).insertAfter('#add-filter-tree');
		$('#searchlist').html('<ul style="color: #000000; cursor: pointer;">' + $(ulList).html() + '</ul>');
		$(name).css('display', 'block');
		$('#ul-filter-section-tree > li').show();

		// alphabetizes the add popup list
		var myList = $(name + ' > #ul-filter-section-tree');
		var listitems = $(name + ' > #ul-filter-section-tree > li');

		listitems.sort(function(a, b) {
			return $(a).text().toUpperCase().localeCompare(
					$(b).text().toUpperCase())
		});
		$.each(listitems, function(idx, itm) {
			myList.append(itm);
		});

		$('#popup').html($('#add-filter-widget').html());
		$('#add-filter-widget').html('');
		$('#background').addClass('background_on');
		$('#popup').addClass('popup_on');
		$('#popup').addClass('popup_filters');
	}

	// Change the arrow direction
	$.fn.changeArrow = function(iddata, idname) {
		if ($(iddata).hasClass('fs_data_off')) {
			$(iddata).removeClass('fs_data_off');
			$(idname).removeClass('ion-chevron-right');
			$(idname).addClass('ion-chevron-down');
		} else {
			$(iddata).addClass('fs_data_off');
			$(idname).removeClass('ion-chevron-down');
			$(idname).addClass('ion-chevron-right');
		}
	}

	// Function to show our popups
	$.fn.showPopup = function(divValue, ulValue) {
		var docHeight = $(document).height(); 
		var scrollTop = $(window).scrollTop();
		$(divValue).show();
		$(ulValue).show();
	}

	// Filter function
	$.fn.filter = function(element, what, keyCode, type) {
		var value = $(element).val();
		log(value);
		log(keyCode);
		value = value.toLowerCase().replace(/\b[a-z]/g, function(letter) {
			return letter.toUpperCase();
		});

		// Filter list
		if (value == '') {
			$(what + ' > li').show();
		} else if (keyCode === 13) {
			var first = true;
			$(what + ' > li').each(function(index) {
				var li = $(this);
				if (li.is(":visible") && first) {
					first = false;
					$.fn.tagClick(type, li.attr('id'));
				}
			});
			$(element).val('');
		} else {
			$(what + ' > li:not(:contains(' + value + '))')
					.hide();
			$(what + ' > li:contains(' + value + ')').show();
			// Get the first in the list and highlight
			var first = true;
			$(what + ' > li').each(function(index) {
				var li = $(this);
				if (li.is(":visible") && first) {
					log(li);
					li.addClass('selected');
					first = false;
				} else {
					li.removeClass('selected');
				}
			});
		}
	};

	// Toggle Tag Selection
	$.fn.toggleTag = function(data) {
		log(data);
		if ($('#div-' + data).hasClass('tag_tree_off')) {
			$('#div-' + data).removeClass('tag_tree_off');
			$('#div-' + data).addClass('tag_tree_on');
			$('#i-' + data).removeClass('ion-chevron-right');
			$('#i-' + data).addClass('ion-chevron-down');
		} else {
			$('#div-' + data).removeClass('tag_tree_on');
			$('#div-' + data).addClass('tag_tree_off');
			$('#i-' + data).removeClass('ion-chevron-down');
			$('#i-' + data).addClass('ion-chevron-right');
		}
	}
	// END: SUPPORTING BUTTON/LINK METHODS

	// InsertAt
	$.fn.insertAt = function(index, $parent) {
		return this.each(function() {
			if (index === 0) {
				$parent.prepend(this);
			} else {
				$parent.children().eq(index - 1).after(this);
			}
		});
	}

	// Get all the tags from the Service Cloud list
	$.fn.getAllTags = function() {
		var tags = '';
		$('.ul_all_tags li').each(function(index) {
			var li = $(this);
			var id = li.attr('id');
			var tagType = li.attr('tagtype');
			var isSearchTerm = false;
			if (typeof tagType != undefined && tagType != '' && tagType == 'search_term'){
				isSearchTerm = true
			}
			id = id.substring(3);
			if (typeof id != 'undefined' && id != '' && id != 'English' && id != 'ContentTypes' && !isSearchTerm) {
				tags += id + ',';
			}
		});

		return tags;
	}

	// Function to retrieve all selected content type tags
	$.fn.getSelectedContentTags = function() {
		var contentCollection = '';
		$('#div-content-tags div input').each(function(index) {
			var input = $(this);
			if (input.is(":checked")) {
				// it is checked
				contentCollection += input.attr('id') + ',';
			}
		});
		return contentCollection;
	}

	// Add to search cloud
	$.fn.addToSearchCloud = function(type, element) {
		log('addToSearchCloud: ' + element);
		$('.fs_dt_info_label').css('display', 'none');
		var n = $('.ul_all_tags li').length;
		if (n === 0) {
			var buildLi = '<li id="sc-' + 'English' + '" class="search-choice search-choice-cloud" title="English">';
			buildLi += '<span>English</span>';
			buildLi += '</li>';
			$('.ul_all_tags').append(buildLi);
		}
		if (type === 'search_term') {
			var term;
			if (typeof element === 'undefined'|| element === '') {
				element = '*';
				term = 'all';
			} else if (element === '*') {
				term = 'all';
			} else {
				term = $.fn.removeSpecialCharacters(element);
			}
			// Replace existing search term
			$('.ul_all_tags li').each(function(index) {
				var li = $(this);
				var searchTerm = li.attr('tagtype');
				if (typeof searchTerm != 'undefined' && searchTerm != '' && searchTerm === 'search_term') {
					$('#tag-' + li.attr('id').substring(3)).removeClass('tree_selected');
					li.remove();
				}
			});
			var buildLi = '<li id="sc-'+ term + '" class="search-choice search-choice-cloud" title="' + element + '" tagtype="' + type + '">';
			var value = element;
			buildLi += '<span>' + value + '</span>';
			buildLi += '<a class="search_choice_close" rel="0" onclick="$.fn.removeCloudTag(\'' + type + '\', \'' + term
					+ '\');" href="javascript:void(0);" tagtype="' + type + '"></a>';
			buildLi += '</li>';
			log(buildLi);
			$(buildLi).insertAt(1, $('.ul_all_tags'));
		} else if (type === 'topic') {
			// Replace existing topic
			$('.ul_all_tags li').each(function(index) {
				var li = $(this);
				var topicTag = li.attr('tagtype');
				if (typeof topicTag != 'undefined' && topicTag != '') {
					$('#tag-'+ li.attr('id').substring(3)).removeClass('tree_selected');
					li.remove();
				}
			});
			var buildLi = '<li id="sc-' + element + '" class="search-choice search-choice-cloud" title="'
					+ $('#tag-' + element).attr('value') + '" tagtype="' + type + '">';
			var value = $('#tag-' + element).attr('value');
			buildLi += '<span>' + value + '</span>';
			buildLi += '<a class="search_choice_close" rel="0" onclick="$.fn.removeCloudTag(\''
					+ type + '\', \'' + element + '\');" href="javascript:void(0);" tagtype="' + type + '"></a>';
			buildLi += '</li>';
			log(buildLi);
			$(buildLi).insertAt(1, $('.ul_all_tags'));
		} else if (type === 'publishedid') {
			// Do nothing for now
		} else if (type === 'kbase') {
			// we only want to allow one 'kbase' tag to be
			// active at one time

			// Run check on whether another kbase tag is active
			$('.ul_all_tags li').each(function(index) {
				var li = $(this);
				var topicTag = li.context.id.substring(0, 8);
				if (topicTag == 'sc-kbase') {
					// calls remove tag on existing
					// kbase tag in cloud and search
					$.fn.removeTag(type, li.context.id.substring(3));
				}
			});

			// creating the tags to put into the cloud/search
			var buildLi = '<li id="sc-'
					+ element
					+ '" class="search-choice search-choice-cloud" title="' + $('#' + element).text() + '" rel="'
					+ $('#' + element).attr('rel') + '">';
			buildLi += '<span>' + $('#' + element).text() + '</span>';
			buildLi += '</li>';
			log(buildLi);

			// inserts the html code at the designated spot
			$(buildLi).insertAt(1, $('.ul_all_tags'));

		} else {
			var buildLi = '<li id="sc-' + element + '" class="search-choice search-choice-cloud" title="' 
			+ $('#' + element).text() + '" rel="' + $('#' + element).attr('rel') + '">';
			buildLi += '<span>' + $('#' + element).text() + '</span>';
			buildLi += '<a class="search_choice_close" rel="' + $('#' + element).attr('rel') + '" onclick="$.fn.removeCloudTag(\''
					+ type + '\', \'' + $('#' + element).attr('id') + '\');" href="javascript:void(0);" tagtype="' + type + '"></a>';
			buildLi += '</li>';
			log(buildLi);
			$(buildLi).insertAt(1, $('.ul_all_tags'));
		}

		// Get all the content type filters
		var contentTypes = $('#div-content-tags div input').length;
		log(contentTypes);
		var contentCollection = '';
		var liCttitle = $('#sc-ContentTypes').attr('title')
		if (typeof liCttitle === 'undefined' || liCttitle === null) {
			$('#div-content-tags div input').each(function(index) {
				var input = $(this);
				if (input.is(":checked")) {
					// it is checked
					contentCollection += input.attr('value') + ' ';
				}
			});
			var buildLi = '<li id="sc-ContentTypes'
					+ '" class="search-choice search-choice-cloud" title="Content Types">';
			buildLi += '<span>' + contentCollection.substring(0, 12) + '...</span>';
			buildLi += '</li>';
			$('.ul_all_tags').append(buildLi);
		}

		$(".dpui-widget").trigger("dpui:updateSearchCloud", buildLi);
	}

	// Remove Cloud Search specific tag
	$.fn.removeCloudTag = function(type, item) {
		log(item);
		if (typeof type != 'undefined' && type != '') {
			if (type === 'topic') {
				$('#tag-' + item).removeClass('tree_selected');
				var item2 = $('#sc-' + item);
				item2.remove();
				$('#ul-filter-section-tree li a span').each(function(index) {
					var a = $(this);
					if (a.hasClass('tree_selected')) {
						a.removeClass('tree_selected');
					}
				});
			} else {
				$.fn.removeTag(type, item);
			}
		}

		$(".dpui-widget").trigger("dpui:setupTags", $.fn.getAllTags());
		$(".dpui-widget").trigger("dpui:setupContentTypeTags", $.fn.getSelectedContentTags());
		if (typeof type != 'undefined' && type != null && type === 'search_term') {
			$(".dpui-widget").trigger("dpui:resetSearch");
		} else {
			$(".dpui-widget").trigger("dpui:runSearch");
		}
	}

	// For testing only
	$.fn.setupTestTreeData = function() {
		var tags = '[{"parentTagName"\:"","systemTagSetName":"", "systemTagSetDisplayName": "", "systemTagName": "topic", "systemTagDisplayName": "Topic"},';
		tags += '{"parentTagName": "topic", "systemTagSetName": "", "systemTagSetDisplayName": "", "systemTagName": "topic_westjet", "systemTagDisplayName": "WestJet"},';
		tags += '{"parentTagName": "topic_westjet", "systemTagSetName": "", "systemTagSetDisplayName": "", "systemTagName": "topic_baggage", "systemTagDisplayName": "Baggage"},';
		tags += '{"parentTagName": "topic_baggage", "systemTagSetName": "", "systemTagSetDisplayName": "", "systemTagName": "topic_baggageissues", "systemTagDisplayName": "Baggage Issues"},';
		tags += '{"parentTagName": "topic_baggage", "systemTagSetName": "", "systemTagSetDisplayName": "", "systemTagName": "topic_baggagerestrictions", "systemTagDisplayName": "Baggage Restrictions"},';
		tags += '{"parentTagName": "topic_westjet", "systemTagSetName": "", "systemTagSetDisplayName": "", "systemTagName": "topic_atairport", "systemTagDisplayName": "At Airport"},';
		tags += '{"parentTagName": "topic_atairport", "systemTagSetName": "", "systemTagSetDisplayName": "", "systemTagName": "topic_security", "systemTagDisplayName": "Security"}]';
		var jsonObj = eval(tags);
		return jsonObj;
	}

	// Function to manage filter clicks
	$.fn.tagClick = function(type, element) {
		$.fn.setupSpinner();
		log('TagClick: ' + element);
		var buildLi = '<li id="' + element + '" class="search-choice" title="' + $('#' + element).text() + '" rel="'
				+ $('#' + element).attr('rel') + '">';
		buildLi += '<span>' + $('#' + element).text() + '</span>';
		if (type != 'kbase') {
			buildLi += '<a class="search_choice_close" rel="' + $('#' + element).attr('rel') + '"onclick="$.fn.removeCloudTag(\'' + type
					+ '\', \'' + element + '\');"	href="javascript:void(0);"></a>';
		}
		buildLi += '</li>';
		$.fn.addToSearchCloud(type, element);
		log('TagClick: ' + '#tag-span-' + element);
		$('#tag-span-' + element).addClass('tree_selected');
		$('#' + element).remove();
		log('tagClick: ' + buildLi);
		log('tagClick: ' + type);
		$(buildLi).prependTo('#' + type + '-selection');
		$('#' + element + '-input').width('102px');
		$('#' + element + '-input').attr('value', '');

		if (type == "kbase") {
			// calling the crosstags to update
			$.fn.getCrossTag(element, 'topic', 'cntnttype', 'region', 'product');
			$.fn.toggleMenu('#tab-search-button.search_search');
		}

		// Send inter-widget communication
		$.fn.widgetCommunication(true);
		$.fn.manageTheme();
	}

	// Remove Tag
	$.fn.removeTag = function(type, item) {
		log(item);
		var buildLi = '<li id="' + item
				+ '" class="active-result" title="' + $('#' + item + ' span').text() + '" onclick="$.fn.tagClick(\'' + type
				+ '\', \'' + item + '\');" style="" rel="' + $('#' + item).attr('rel') + '">' + $('#' + item + ' span').text() + '</li>';
		var rel = parseInt($('#' + item).attr('rel'));
		$('#' + item).remove();
		$('#sc-' + item).remove();
		$('#tag-span-' + item).removeClass('tree_selected');
		var n = $('#' + type + '-selection li').length;
		if (n === 1) {
			$('#' + type + '-input').width('102px');
		}
		$(buildLi).insertAt(rel, $('#ul-' + type + '-tags'));

		// added special remove cases for when we remove kbase
		// tags
		if (type == 'kbase') {

			// clears a lot of the values to not be able to
			// select them when there is no kbase tag selected
			var treeData = "";
			$('#div-topic-tags').html(treeData);
			$('#ul-cntnttype-tags').html(treeData);
			$('#div-cntnttype-tags').html(treeData);
			$('#ul-region-tags').html(treeData);
			$('#div-region-tags').html(treeData);
			$('#ul-product-tags').html(treeData);
			$('#div-product-tags').html(treeData);

			// resets the search cloud, unless its the English
			// tag
			$('.ul_all_tags li').each(function(index) {
				log(index);
				var li = $(this);
				log(li);
				var id = li.attr('id');
				id = id.substring(3);
				var tagtype = $('#' + li.attr('id') + ' a').attr('tagtype');
				if (typeof tagtype != 'undefined') {
					$.fn.removeTag(tagtype, id);
				}
				if (id != "English") {
					li.remove();
				}

			});
		}
	}

	// Setup Filter
	$.fn.setupFilter = function(data, startTag, methodName) {
		var filterData = '';
		var begin = false;
		var startRel = 0;
		// Loop through the data
		for (var x = 0; x < data.length; x++) {
			if (data[x].systemTagName === startTag) {
				begin = true;
			} else if (begin) {
				filterData += '<li id="' + data[x].systemTagName + '" rel="' + startRel + '" class="active-result" style="" onclick="$.fn.tagClick(\''
						+ methodName + '\', \'' + data[x].systemTagName + '\');" title="' + data[x].systemTagDisplayName + '">'
						+ data[x].systemTagDisplayName + '</li>';
				startRel++;
			}
		}
		return filterData;
	}

	// Setup the Content Filter
	$.fn.setupContentFilter = function(data) {
		var filterData = '';
		// Loop through the data
		for (var x = 0; x < data.length; x++) {
			if (data[x].systemTagName != 'content'
					&& data[x].systemTagName != 'content_segment') {
				filterData += '<div class="filter_section_data_info_tagset_list_content_types"><input id="'
						+ data[x].systemTagName + '" class="" type="checkbox" title="' 
						+ data[x].systemTagDisplayName + '" tabindex="" checked="checked" name="" value="'
						+ data[x].systemTagDisplayName + '"/>';
				filterData += '<label id="' + data[x].systemTagName + '_label" style="" for="">'
						+ data[x].systemTagDisplayName + '</label></div>';
			}
		}
		return filterData;
	}

	// Send tags, content types and run a search
	$.fn.widgetCommunication = function(runSearch) {
		$(".dpui-widget").trigger("dpui:setupTags", $.fn.getAllTags());
		$(".dpui-widget").trigger("dpui:setupContentTypeTags", $.fn.getSelectedContentTags());
		if (runSearch) {
			$(".dpui-widget").trigger("dpui:blankSearch");
			$(".dpui-widget").trigger("dpui:alertSearch");
		} else {
			$(".dpui-widget").trigger("dpui:runSearch");
		}

	}

	// Parse the Tags
	$.fn.parseTags = function(data) {
		if (typeof data.tagSets != 'undefined' && data.tagSets != null && data.tagSets.length > 0) {
			for (var x = 0; x < data.tagSets.length; x++) {
				if (data.tagSets[x].systemTagName === 'topic') {
					if (typeof data.tagSets[x].tags != 'undefined' && data.tagSets[x].tags != null && data.tagSets[x].tags.length > 0) {
						var treeData = $.fn.createTreeFilter(data.tagSets[x].tags, 'topic', 'topic', false);
						// $('#div-topic-tags').html(treeData);
					}
				} else if (data.tagSets[x].systemTagName === 'product') {
					if (typeof data.tagSets[x].tags != 'undefined' && data.tagSets[x].tags != null && data.tagSets[x].tags.length > 0) {
						var treeData = $.fn.createTreeFilter(data.tagSets[x].tags, 'product', 'product', false);
						$('#div-product-tags').html(treeData);
						$('#ul-product-tags').html($.fn.setupFilter(data.tagSets[x].tags,'product', 'product'));
					}
				} else if (data.tagSets[x].systemTagName === 'kbase') {
					if (typeof data.tagSets[x].tags != 'undefined' && data.tagSets[x].tags != null && data.tagSets[x].tags.length > 0) {
						var treeData = $.fn.createTreeFilter(data.tagSets[x].tags, 'kbase', 'kbase');
						$('#div-kbase-tags').html(treeData);
						$('#ul-kbase-tags').html($.fn.setupFilter(data.tagSets[x].tags, 'kbase', 'kbase'));
					}
				} else if (data.tagSets[x].systemTagName === 'region') {
					if (typeof data.tagSets[x].tags != 'undefined' && data.tagSets[x].tags != null && data.tagSets[x].tags.length > 0) {
						var treeData = $.fn.createTreeFilter(data.tagSets[x].tags, 'region','region', false);
						$('#div-region-tags').html(treeData);
						$('#ul-region-tags').html($.fn.setupFilter(data.tagSets[x].tags,'region', 'region'));
					}
				} else if (data.tagSets[x].systemTagName === 'cntnttype') {
					if (typeof data.tagSets[x].tags != 'undefined' && data.tagSets[x].tags != null && data.tagSets[x].tags.length > 0) {
						var treeData = $.fn.createTreeFilter(data.tagSets[x].tags, 'cntnttype', 'cntnttype');
						$('#div-cntnttype-tags').html(treeData);
						$('#ul-cntnttype-tags').html($.fn.setupFilter(data.tagSets[x].tags,'cntnttype','cntnttype'));
					}
				} else if (data.tagSets[x].systemTagName === 'content') {
					if (typeof data.tagSets[x].tags != 'undefined' && data.tagSets[x].tags != null && data.tagSets[x].tags.length > 0) {
						var contentData = $.fn.setupContentFilter(data.tagSets[x].tags);
						$('#div-content-tags').html(contentData);
						$(".dpui-widget").trigger("dpui:setupContentTypeTags",$.fn.getSelectedContentTags());
					}
				}
			}
		}
	}

	// Setup the inter-widget communication
	$.widget("ui.ajaxStatus", {
		options : {},
		_create : function() {
			var self = this;
			self.element.addClass("dpui-widget");
			self.element.bind("dpui:startWidget", function(e) {
				log("startWidget");
				$(".dpui-widget").trigger("dpui:registerFilter");
			});
			self.element.bind("dpui:registerSearch", function(e) {
						log("registerSearch");
					});
			self.element.bind("dpui:registerContentView", function(e) {
						log("registerContentView");
					});
			self.element.bind("dpui:resultData", function(e, data) {
				log(data);
		    	// put the scrool bar back to the top
		    	$('#f_wrapper').scrollTop(0);
			});
			self.element.bind("dpui:searchTerm", function(e, data) {
				log(data);
		    	// put the scrool bar back to the top
		    	$('#f_wrapper').scrollTop(0);

				$.fn.addToSearchCloud('search_term', data);
			});
		},
		destroy : function() {
			$.Widget.prototype.destroy.apply(this, arguments);
		},
	});

	// Register the Filters widget
	$.ui.ajaxStatus({}, $("<div></div>").appendTo("body"));
	$(".dpui-widget").trigger("dpui:startWidget");

	// populates the cross tags according to source tag and 4
	// target tags
	$.fn.getCrossTag = function(source, target, target1, target2, target3) {
		jQuery.ajaxSetup({
			async : false
		});
		
    	// put the scrool bar back to the top
    	$('#f_wrapper').scrollTop(0);
		
		// topic tag would be populated first, since this could
		// have several appended to it
		var url = filtersServiceName + 'km/crosstags?sourcetag=' + source + '&targettagset=' + target;
		$.fn.serviceCall('GET', '', url, 15000, function(data) {
			$.fn.populateCrossTags(data);
		});

		// populates the rest
		var url = filtersServiceName + 'km/crosstags?sourcetag=' + source + '&targettagset=' + target1
				+ '&targettagset1=' + target2 + '&targettagset2=' + target3;
		$.fn.serviceCall('GET', '', url, 15000, function(data) {
			$.fn.populateCrossTags(data);
		});
		jQuery.ajaxSetup({
			async : true
		});
	}

	$.fn.populateCrossTags = function(data) {
		// create an array of all of the crosstag types, to
		// remove later and populate missing ones
		var crossTagTypes = [ 'cntnttype', 'region', 'product' ];

		// $('#div-topic-tags').html("");
		var topicTreeData = "";

		// goes through all of the tags in crosstags and
		// populates and removes them from the array
		for (var i = 0; i < data.crossTags.length; ++i) {
			
			if (typeof  data.crossTags[i].tags[0] != 'undefined' &&  data.crossTags[i].tags[0] != null ) {
				var currentTargetSet = data.crossTags[i].tags[0].parentTagName;

				if (currentTargetSet == 'topic') {
					var treeValue = $.fn.createTreeFilter(data.crossTags[i].tags,data.crossTags[i].tags["0"].systemTagName,'topic', false);

					topicTreeData += treeValue;

					$('#div-topic-tags').html(topicTreeData);

					// remove the crossTag from the array of all tag types
					var index = crossTagTypes.indexOf(currentTargetSet);
					if (index > -1) {
						crossTagTypes.splice(index, 1);
					}
				} else if (currentTargetSet == 'cntnttype') {
					var treeData = $.fn.createTreeFilter(data.crossTags[i].tags,data.crossTags[i].tags["0"].systemTagName,'cntnttype', false);
					$('#div-cntnttype-tags').html(treeData);
					$('#ul-cntnttype-tags').html($.fn.setupFilter(data.crossTags[i].tags,data.crossTags[i].tags["0"].systemTagName,'cntnttype'));

					// remove the crossTag from the array of all tag types
					var index = crossTagTypes.indexOf(currentTargetSet);
					if (index > -1) {
						crossTagTypes.splice(index, 1);
					}
				} else if (currentTargetSet == 'region') {
					var treeData = $.fn.createTreeFilter(data.crossTags[i].tags,data.crossTags[i].tags["0"].systemTagName,'region', false);
					$('#div-region-tags').html(treeData);
					$('#ul-region-tags').html($.fn.setupFilter(data.crossTags[i].tags,data.crossTags[i].tags["0"].systemTagName,'region'));

					// remove the crossTag from the array of all tag types
					var index = crossTagTypes.indexOf(currentTargetSet);
					if (index > -1) {
						crossTagTypes.splice(index, 1);
					}
				} else if (currentTargetSet == 'product') {
					var treeData = $.fn.createTreeFilter(data.crossTags[i].tags,data.crossTags[i].tags["0"].systemTagName,'product', true);
					$('#div-product-tags').html(treeData);
					$('#ul-product-tags').html($.fn.setupFilter(data.crossTags[i].tags,data.crossTags[i].tags["0"].systemTagName,'product'));

					// remove the crossTag from the array of all tag types
					var index = crossTagTypes.indexOf(currentTargetSet);
					if (index > -1) {
						crossTagTypes.splice(index, 1);
					}
				}
			}
		}

		// create list of crosstags that need to have all of
		// their tags populated, and then populate them
		var emptyTags = crossTagTypes.toString();
		if (emptyTags != null && emptyTags != "") {
			$.fn.getTagsforTagSets(emptyTags);
		}
	}
	// Get all the tags for the TagSets, will use this for the
	// content categories only (since most others will be
	// overwritten)
	$.fn.getTagsforTagSets = function(tagSets) {
		var url = filtersServiceName + 'km/tags/gettagsfortagsets?tagsets=' + tagSets;
		
    	// put the scrool bar back to the top
    	$('#f_wrapper').scrollTop(0);

    	jQuery.ajaxSetup({
			async : false
		});
    	
		$.fn.serviceCall('GET', '', url, 15000, function(data) {
			$.fn.parseTags(data);
		});
		
		jQuery.ajaxSetup({
			async : true
		});
	}
	// get all the kbase tags
	$.fn.getKBaseTags = function() {
		var url = filtersServiceName + 'km/kbasetags';
		
    	// put the scroll bar back to the top
    	$('#f_wrapper').scrollTop(0);

    	jQuery.ajaxSetup({
			async : false
		});
    	
		$.fn.serviceCall('GET', '', url, 15000, function(data) {
			$.fn.populateKBaseTags(data);
		});

		jQuery.ajaxSetup({
			async : true
		});
		
		$.fn.getTagsforTagSets("content");
	}

	// Parse the kbase tags Tags
	$.fn.populateKBaseTags = function(data) {
		// populates the HTML with KBase selections
		var kbaseData = '';
		if (typeof data.tags != 'undefined' && data.tags != null && data.tags.length > 0) {
			for (var x = 0; x < data.tags.length; x++) {
				if (typeof data.tags[x] != 'undefined' && data.tags[x] != null && data.tags[x].systemTagName.length > 0) {
					$('#div-kbase-tags').html(data.tags[x].systemTagDisplayName);
					kbaseData += '<li id="' + data.tags[x].systemTagName
							+ '" rel="' + x + '" class="active-result" style="" onclick="$.fn.tagClick(\'kbase\', \''
							+ data.tags[x].systemTagName + '\');" title="' + data.tags[x].systemTagDisplayName + '">'
							+ data.tags[x].systemTagDisplayName + '</li>';
					$('#ul-kbase-tags').html(kbaseData);
				}
			}

			// this pulls in the params and puts them into an
			// array
			var kTagsParameterStrings = $.fn.getParameterByName('tags');
			var parameterArray = "";

			if (kTagsParameterStrings != null) {
				parameterArray = kTagsParameterStrings.split(',');
			}

			var noKBaseInParameters = true;

			// crosschecks the params with the ktags
			PARAMSLOOP: for (var i = 0; i < parameterArray.length; ++i) {
				for (var j = 0; j < data.tags.length; ++j) {
					if (parameterArray[i] == data.tags[j].systemTagName) {
						// invoking the ktag onclick function if
						// we find a match in the params
						var elem = document.getElementById(data.tags[j].systemTagName);

						if (typeof elem.onclick == "function") {
							elem.onclick.apply(elem);
							noKBaseInParameters = false;

							// breaks the loop in order to not
							// go through all of the kbase tags
							// in the loop, handles having
							// multiple kbase tags in the url
							break PARAMSLOOP;
						}
					}
				}
			}

			// handles if there isn't a kbase parameter in the
			// url
			if (noKBaseInParameters) {
				if (data.tags[0] != 'undefined') {
					// applies the first kbase parameter that gets
					var elem = document.getElementById(data.tags[0].systemTagName);
					if (typeof elem.onclick == "function") {
						elem.onclick.apply(elem);
					}
				}
			}
		} else {
			log('No KnowledgeBases found redirecting to ' + UnauthorizedPageRedirect);
		window.document.location =  UnauthorizedPageRedirect;
		}
	}

	// sort function, currently being used for alphabetically
	// sorting the crosstags
	$.fn.sortList = function(data) {
		var myList = $(data);
		var listitems = myList.children('li').get();
		listitems.sort(function(a, b) {
			return $(a).text().toUpperCase().localeCompare($(b).text().toUpperCase());
		});
		$.each(listitems, function(idx, itm) {
			myList.append(itm);
		});
	}

	// manages the themes for various kbase tags, basically just
	// switches around the css sheets in the verintkm html
	$.fn.manageTheme = function() {
		/**
		// Removed logic to get theme list and defaul all KB's to 'knowledgeCentralTheme''
		var current = $.fn.getParameterByName('tags');					
		//var configList = "";
		var configList = $.fn.getProperty('themes.knowledgeCentralTheme');
	
		if (current != 'undefined' && current != null) {
			var currentArray = current.split(",");
			var configArray = configList.split(",");

			if ($.fn.hasCommonElement(currentArray, configArray)) { 				*/

				document.getElementById('knowledgeCentralTheme').disabled = false;
				$.fn.setFavicon("images/faviconKC.png");
				document.title = "Knowledge Central"
				document.getElementById('defaultTheme').disabled = true; /*
			} else {
				document.getElementById('knowledgeCentralTheme').disabled = true;
				document.getElementById('defaultTheme').disabled = false;
				$.fn.setFavicon("images/favicon.png");				
			}
		} else {
			document.getElementById('knowledgeCentralTheme').disabled = true;
			document.getElementById('defaultTheme').disabled = false;
			$.fn.setFavicon("images/favicon.png");
		}
		**/
	}

	$.fn.setFavicon = function(src) {
		removeFavicon();
		var link=document.createElement('link');
		link.type='image/x-icon';
		link.rel='icon';
		link.href=src;
		document.getElementsByTagName('head')[0].appendChild(link);
		if (window.console) console.log("Set FavIcon URL to " + getFavicon().href);
	}
	
	function removeFavicon()
	{
	    var links=document.getElementsByTagName('link');
	    var head=document.getElementsByTagName('head')[0];
	    for(var i=0; i<links.length; i++)
	    {
	        if(links[i].getAttribute('rel')==='icon')
	        {
	            head.removeChild(links[i])
	        }         
	    }      
	}
	
	function getFavicon()
	{
	    var links=document.getElementsByTagName('link');
	    for(var i=0; i<links.length; i++)
	    {
	        if (links[i].getAttribute('rel')==='icon')
	        {
	            return links[i];
	        }       
	    }
	    return undefined;
	}
	
	$.fn.removeSpecialCharacters = function(data){
		//these characters where causing jquery exceptions if in the element name
		log('removeSpecialCharacters start - ' + data);
		data = data.replace(/\(/g, " ");
		data = data.replace(/\)/g, " ");
		data = data.replace(/\//g, " ");
		data = data.replace(/\'/g, " ");
		data = data.replace(/\?/g, " ");
		data = data.replace(/\;/g, " ");
		data = data.replace(/\:/g, " ");
		data = data.replace(/\!/g, " ");
		data = data.replace(/\&/g, " ");
		data = data.replace(/\%/g, " ");
		log('removeSpecialCharacters end - ' + data);
		return data;
	}
	
	// Array has common elements helper function
	$.fn.hasCommonElement = function(arr1, arr2) {
		var bExists = false;
		$.each(arr2, function(index, value) {

			if ($.inArray(value, arr1) != -1) {
				if (value != "") {
					console.log(value);
					bExists = true;
				}
			}

			if (bExists) {
				return false;
			}
		});
		return bExists;
	}

	// Get the Tags
	// $.fn.getTagsforTagSets()
	$.fn.getKBaseTags();
});

