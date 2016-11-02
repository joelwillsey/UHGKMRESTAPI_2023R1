var page = 1;
var size = 20;
var contentTypeTags = '';
var filterTags = '';
var publishedid = '';
// List item global variable for the autosuggest feature.
var $liSelected;


$(document).ready(function() {

	// Toggle the search cloud
	$.fn.toggleSearchCloudSection = function() {
		// determine which way it is pointing
		if ($('#search-filter-arrow').hasClass('ion-chevron-down')) {
			$('#search-filter-arrow').removeClass('ion-chevron-down');
			$('#search-filter-arrow').addClass('ion-chevron-right');
			$('#search-filter-data').removeClass('on');
			$('#search-filter-data').addClass('off');
		} else {
			$('#search-filter-arrow').addClass('ion-chevron-down');
			$('#search-filter-arrow').removeClass('ion-chevron-right');
			$('#search-filter-data').removeClass('off');
			$('#search-filter-data').addClass('on');
		}
	}

	// Toggle the search menu
	$.fn.toggleMenu= function(selection) {
		$('#search-menu-ul li a').each(
			function(index) {  
				var a = $(this);
				var id = a.attr('id');
				var selId = $(selection).attr('id');
				if (typeof id != 'undefined' && id != null && id != '' && id === selId) {
					a.addClass('sel');
				} else {
			    	a.removeClass('sel');
				}
			}
		);
	}
	
	// Toggle search box/cloud wrapper
	$.fn.toggleSearch = function(type) {
		// Hide search box and cloud wrapper if on tablet/mobile for non-search
		if ($(window).outerWidth(true) < 1025) {
			if (type === 'search') {
				$('#search-wrapper').removeClass('off');
				$('#search-wrapper').addClass('on');
				$('#search-cloud-wrapper').removeClass('off');
				$('#search-cloud-wrapper').addClass('on');
			} else {
				$('#search-wrapper').removeClass('on');
				$('#search-wrapper').addClass('off');
				$('#search-cloud-wrapper').removeClass('on');
				$('#search-cloud-wrapper').addClass('off');				
			}
		} else {
			$('#search-wrapper').removeClass('off');
			$('#search-wrapper').addClass('on');
			$('#search-cloud-wrapper').removeClass('on');
			$('#search-cloud-wrapper').addClass('off');	
		}
	}

	// Search button
	$('#search-button').on('click', function() {
		$.fn.toggleMenu($('#tab-search-button'));
		$.fn.toggleSearch('search');
		$('.dpui-widget').trigger('dpui:hideManageButton');
    	$.fn.search($('#search-text').val(), page, size, '', '', '', '', function(data) {
    		$.fn.sendToResults('Search', data);
    	});
	});

	// Alert button
    $('#tab-alert-button').on('click', function() {
    	$.fn.toggleMenu(this);
    	$.fn.toggleSearch('alert');
    	$('.dpui-widget').trigger('dpui:hideManageButton');
    	var kTagsParameterStrings = $.fn.getParameterByName('tags');
    	$.fn.search('', page, size, kTagsParameterStrings, 'content_knowledgealert', '', '', function(data) {
    		$.fn.sendToResults('Knowledge Alert', data);
    	});
	});

	// Bookmark button
    $('#tab-bookmarks-button').on('click', function() {
    	$.fn.toggleMenu(this);
    	$.fn.toggleSearch('bookmark');
    	$('.dpui-widget').trigger('dpui:showManageButton');
    	var kTagsParameterStrings = $.fn.getParameterByName('tags').split(",");
    	$.fn.bookmark(page, size, kTagsParameterStrings[1]);
	});

    // Featured Content button
	$('#tab-featured-button').on('click', function() {
    	$.fn.toggleMenu(this);
    	$.fn.toggleSearch('featured');
    	$('.dpui-widget').trigger('dpui:hideManageButton');
    	var kTagsParameterStrings = $.fn.getParameterByName('tags').split(",");
    	
    	$.fn.featured(page, size, kTagsParameterStrings[1]);
	});

	// New or Changed Button button
	$('#tab-new-changed-button').on('click', function() {
    	$.fn.toggleMenu(this);
    	$.fn.toggleSearch('neworchanged');
    	$('.dpui-widget').trigger('dpui:hideManageButton');
    	
    	 //this pulls in the params and puts them into an array
		 var kTagsParameterStrings = $.fn.getParameterByName('tags');
		 var parameterArray = "";
		 var currentKTag = "";
		 
		 if (kTagsParameterStrings != null){
			 parameterArray = kTagsParameterStrings.split(',');
		 }
		 
		 for (var i = 0; i < parameterArray.length; i++){
			 var current = parameterArray[i].substring(0,5);
			 if(parameterArray[i].substring(0,5) == "kbase"){
				 currentKTag = parameterArray[i];
			 }
		 }
    	
    	$.fn.newOrChanged(page, size, currentKTag);
	});

    // Search button
	$('#tab-search-button').on('click', function() {
    	// Setup the tabs
    	$.fn.toggleMenu(this);
		$.fn.toggleSearch('search');
		$('.dpui-widget').trigger('dpui:hideManageButton');

		$.fn.search("", 1, 20, filterTags, contentTypeTags, "", "", function(data) {
    		$.fn.sendToResults('Search', data);
    	});
		
		// For now, don't call search
    	//$.fn.search('Search', page, size, '', '');
	});

	// Tab menu
	$('.search_tab_menu').on('click', function() {
    	// Setup the tabs
    	$.fn.toggleMenu(this);
		$.fn.toggleSearch('search');

    	// For now, don't call search
    	//$.fn.search('Search', page, size, '', '');
	});

	
	// Mobile specific logic for menu options
	$('.search_menu_toggle').on('click', function() {
		 if ($('.search_menu').css('display') == 'none') {
			 // element is hidden
			 $('.search_menu').removeClass('close');
			 $('.search_menu').addClass('open');
			 $('#search-menu-toggle-icon').removeClass('ion-chevron-right');
			 $('#search-menu-toggle-icon').addClass('ion-chevron-down');
			 $('.search_menu_toggle').addClass('open');
		 } else {
			 $('.search_menu').removeClass('open');
			 $('.search_menu').addClass('close');
			 $('#search-menu-toggle-icon').removeClass('ion-chevron-down');
			 $('#search-menu-toggle-icon').addClass('ion-chevron-right');
			 $('.search_menu_toggle').removeClass('open');
		 }
	});

	// Catch enter key; call the search service
	$(document).keydown( function(event) {
		if (event.which === 13){
			if (!$liSelected.hasClass('selected')) {
				$.fn.toggleMenu($('#tab-search-button'));
		    	$.fn.search($('#search-text').val(), page, size, '', '', '', '', function(data) {
		    		$.fn.sendToResults('Search', data);
		    	});
			}
		}
	});

	// Handle search results
	$.fn.sendToResults = function(label, data) {
		var packagedData = [];
		packagedData = {
				"label": label,
				"data": data,
				"query": $('#search-text').val()
		}
		// Send to Search Results Widget
		$(".dpui-widget").trigger("dpui:resultData", packagedData);			
	}

	// Featured Content Service
	$.fn.featured = function(page, size, tags) {
		$.fn.serviceCall('GET', '', searchServiceName + 'km/knowledge/featuredcontent?page=' + page + '&size=' + size + '&tags=' + tags, 15000, function(data) {
			
			//super janky filter to make up for the fact that the soap call hasnt been fixed
			var newData = jQuery.extend(true, {}, data);
			newData.knowledgeGroupUnits = [];
			newData.numberOfResults = 0;
			
			for (var i = 0; i < data.knowledgeGroupUnits.length; ++i){
				for (var j = 0; j < data.knowledgeGroupUnits[i].knowledgeUnits["0"].tags.length; ++j){
					if (data.knowledgeGroupUnits[i].knowledgeUnits["0"].tags[j].systemTagName == tags){
						newData.knowledgeGroupUnits.push(data.knowledgeGroupUnits[i]);
						++newData.numberOfResults;
					}
				}				
			}
			
			$.fn.sendToResults('Featured Content', newData);
		});
	}

	// New or Changed Service
	$.fn.newOrChanged = function(page, size, kbase) {
        $.fn.serviceCall('GET', '', searchServiceName + 'km/neworchanged?page=' + page + '&kbase_tags=' + kbase, 15000, function(data) {
        	$.fn.sendToResults('New or Changed', data);
        });
	}

	// Bookmark function
	$.fn.bookmark = function(page, size, tags) {
		$.fn.serviceCall('GET', '', searchServiceName + 'km/knowledge/bookmarks?page=' + page + '&size=' + size + '&tags=' + tags, 15000, function(data) {
			$.fn.sendToResults('My Bookmarks', data);
		});
	}

	// Search function
	$.fn.search = function(search_text, page, size, tags, categories, sort, publishedid, callBack) {
		if (typeof search_text === 'undefined' || search_text === null || search_text ==='') {
			search_text = '';
		}
		if (typeof tags != 'undefined' && tags != null && tags != '') {
				tags = tags;
		} else {
			if (typeof categories != 'undefined' && categories != null && categories === 'content_knowledgealert') {
				tags = '';
			} else {
				tags = filterTags;
			}
		}
		if (typeof categories != 'undefined' && categories != null && categories != '') {
			categories = categories;
		} else {
			categories = contentTypeTags;
		}
		if (typeof sort === 'undefined' || sort === null || sort === '') {
			sort = '';
		}
		if (typeof publishedid === 'undefined' || publishedid === null || publishedid === '') {
			publishedid = '';
		}

		$(".dpui-widget").trigger("dpui:searchTerm", search_text);
		var query = '?';
		if (search_text != '') {
			query += 'query=' + search_text + '&';
		}
		if (page != '') {
			query += 'page=' + page + '&'; 
		}
		if (size != '') {
			query += 'size=' + size + '&';
		}
		if (tags != '') {
			query += 'tags=' + tags + '&';
		}
		if (categories != '') {
			query += 'categories=' + categories + '&';
		}
		if (sort != '') {
			query += 'sort=' + sort + '&';
		}
		if (publishedid != '') {
			query += 'publishedid=' + publishedid + '&';
		}

		// First check if pushState is supported and if so is it enabled
		// Changes the browser URL to use the parameters so users can save searches and/or bookmark them
		if (history.pushState && typeof historyPushEnabled != 'undefined' && historyPushEnabled) {
	    	var stateObj = { path: query };
			// IE9 has an issue with history
			if (navigator.userAgent.toLowerCase().indexOf("msie") != -1) {
				history.replaceState(stateObj, "newPage", query);
			} else {
				history.pushState(stateObj, "newPage", verintKmServiceName + 'verintkm.html' + query);
			}
	        document.title = defaultTitle;
		}
		//adding in "search_showinsearch tag" so that it doesn't show up in the url
		if (tags.substring(0,20) != "search_showinsearch,"){
			tags = "search_showinsearch,"+tags;
		}
		
		$.fn.serviceCall('GET', '', searchServiceName + 'km/knowledge/search?query=' + search_text + '&page=' + page + '&size=' + size + '&tags=' + tags + '&categories=' + categories + '&sort=' + sort + '&publishedid=' + publishedid, 15000, callBack);
	}

	// Inter-widget communication
	$.widget("ui.ajaxStatus", {
        options: {
        },
        _create : function() {
            var self = this;
            self.element.addClass("dpui-widget");
            self.element.bind("dpui:startWidget", function(e) {
                log("startWidget");
                $(".dpui-widget").trigger("dpui:registerSearch");
            });
            self.element.bind("dpui:registerSearchResults", function(e) {
                log("registerSearchResults");
            });
	        self.element.bind("dpui:registerFilter", function(e, data) {
	            log("registerFilter");
	        });
	        self.element.bind("dpui:setupContentTypeTags", function(e, data) {
	            log("setupContentTypeTags");
	            contentTypeTags = data;
	        });
	        self.element.bind("dpui:setupTags", function(e, data) {
	            log("setupTags");
	            filterTags = data;
	        });
	        self.element.bind("dpui:setupPublishedid", function(e, data) {
	            log("setupPublishedid");
	            publishedid = data;
	        });
	        self.element.bind("dpui:resetSearch", function(e, search_text) {
                log("resetSearch");
                log(search_text);
            	// Search function
                $('#search-text').val(search_text);
                if (typeof search_text != 'undefined' && search_text != null && search_text != '') {
                	$.fn.search(search_text, page, size, filterTags, contentTypeTags, '', publishedid, function(data) {
                		$.fn.sendToResults('Search', data);
                	});
                } else {
                	$.fn.search(search_text, page, size, filterTags, contentTypeTags, '', publishedid, function(data) {
                		$.fn.sendToResults('Search', data);
                	});
                }
            });
	        self.element.bind("dpui:runSearch", function(e, data) {
                log("runSearch");
                log(data);
                var search_text = $('#search-text').val();
                if (typeof data != 'undefined' && data != null) {
                	$.fn.search(search_text, data.page, size, filterTags, contentTypeTags, data.sort, publishedid, function(data) {
                		$.fn.sendToResults('Search', data);
                	});
                } else {
                	$.fn.search(search_text, page, size, filterTags, contentTypeTags, '', publishedid, function(data) {
                		$.fn.sendToResults('Search', data);
                	});
                }
            });
            self.element.bind("dpui:runRefresh", function(e, data) {
                log("Refreshing " + data);
                
            	// Refresh by the type
                if (typeof data != 'undefined' && data != null && data != '') {
                	if (data === 'Knowledge Alert')
                		$.fn.search('', page, size, '', 'content_knowledgealert', '', '', function(data) {
                    		$.fn.sendToResults('Knowledge Alert', data);
                    	});
                	if (data === 'My Bookmarks')
                		$.fn.bookmark(page, size);
                	if (data === 'Featured Content')
                		//pulls in kbase tags from url
                		var kTagsParameterStrings = $.fn.getParameterByName('tags');
                		$.fn.featured(page, size, kTagsParameterStrings);
                	if (data === 'Top Content')
                		$.fn.newOrChanged(page, size, "");
                }
            });
            self.element.bind("dpui:updateSearchCloud", function(e, data) {
            	log("UpdateSearchCloud " + data);
            	// Refresh by the type
                if (typeof data != 'undefined' && data != null && data != '') {
//                	$('#fs-dt-info-label').css('display', 'none');
//                	$('#ul-all-tags').append(buildLi);
                }
            });
        },
        destroy: function(){
            $.Widget.prototype.destroy.apply(this, arguments);
        },
    });

	$.ui.ajaxStatus( {}, $( "<div></div>" ).appendTo( "body") );
    $(".dpui-widget").trigger("dpui:startWidget");
    
    // Check for all possible search parameters
    $.fn.checkForUrlSearch = function() {
    	var retValue = false;
    	var sQuery = $.fn.getParameterByName('query');
    	var sPage = $.fn.getParameterByName('page');
    	var sSize = $.fn.getParameterByName('size');
    	var sTags = $.fn.getParameterByName('tags');
    	var sCategories = $.fn.getParameterByName('categories');
    	var sSort = $.fn.getParameterByName('sort');
    	var sPublishedid = $.fn.getParameterByName('publishedid');

    	// check for any valid value
    	if ((sQuery != 'undefined' && sQuery != '') ||
    		(sPage != 'undefined' && sPage != '') ||
    		(sSize != 'undefined' && sSize != '') ||
    		(sTags != 'undefined' && sTags != '') ||
    		(sCategories != 'undefined' && sCategories != '') ||
    		(sSort != 'undefined' && sSort != '') ||
    		(sPublishedid != 'undefined' && sPublishedid != '')) {
    		if (sPage === '') {
    			sPage = page;
    		}
    		if (sSize === '') {
    			sSize = size;
    		}

    		// Call the search
        	$.fn.search(sQuery, sPage, sSize, sTags, sCategories, sSort, sPublishedid, function(data) {
        		$.fn.sendToResults('Search', data);
        	});
        	retValue = true;
    	}
    	return retValue;
    }

    // Check for content id being passed first
    $.fn.checkForContentId = function() {
    	var contentId = $.fn.getParameterByName('id');
    	var retValue = false;
    	if (contentId != 'undefined' && contentId != '') {
    		// Setup the tabs to be no searching
    		retValue = true;
    		
    		log('CONTENT ID IS PASSED');
    		// Show the search bar as empty
    		$.fn.toggleMenu($('#tab-search-button'));
    		$.fn.toggleSearch('search');
    	}
    	return retValue;
    }

    // Check if the parameters passed in required a search
    if (!$.fn.checkForContentId()) {
	    if (!$.fn.checkForUrlSearch()) {
			// Show the alerts on default of page load
			log('CALLING KNOWLEDGE ALERT');
			$.fn.search('', page, size, '', 'content_knowledgealert', '', '', function(data) {
				$.fn.sendToResults('Knowledge Alert', data);
			});
	    }
    }
  
    /*
    ** AutoSuggest SOLR features and methods.
    **
    */
    
    // Auto suggest features and listeners.
	$("#search-text").on('keyup', function(e){
		
		// Added a key listener for the up or down buttons. Otherwise types out the wording.
		if (e.which === 38){
			// Up arrow detected.
			if($liSelected){
				$liSelected.removeClass('selected');
				var $previousLi = $liSelected.prev();
				
				if($previousLi.length) {
					$liSelected = $previousLi.addClass('selected');
				}else{
					$liSelected = $("#autoSuggest").children('li').last().addClass('selected');
				}
			}else{
				$liSelected = $("#autoSuggest").children('li').last().addClass('selected');
			}
			return false;
		}else if (e.which === 40){
			// Down arrow detected.
			if($liSelected){
				$liSelected.removeClass('selected');
				var $nextLi = $liSelected.next();
				
				if($nextLi.length) {
					$liSelected = $nextLi.addClass('selected');
				}else{
					$liSelected = $("#autoSuggest").children('li').first().addClass('selected');
				}
			}else{
				$liSelected = $("#autoSuggest").children('li').first().addClass('selected');
			}
			return false;
		}else if(e.which === 13){
			if ($liSelected){
				$liSelected.trigger("click");
			}
		}else{

			// Quick and easy delay function so that this doesn't pop up right away.
			var delay = (function(){
				  var timer = 0;
				  return function(callback, ms){
				    clearTimeout (timer);
				    timer = setTimeout(callback, ms);
				  };
				})();
			
			// Calls half second delay between the key up trigger, in order to not interupt people typing.
			delay(function(){
				
				// Pulls in the last word in the search, and passes to the search.
				var words =  $("#search-text").val();
				//.split(' ');
				var searchText = words.trim();
				//[words.length - 1];
				
				// Checks that the length of the term is more than 2 letters, making it easier to identify. 
				if( searchText.length >= 2){
					
					// Call on the autocomplete auto suggest service, that brings back the top 3 words to auto suggest.
					$.fn.serviceCallNoSpin('GET', '', searchServiceName + 'km/autocomplete/suggest?text=' + searchText , 15000, function(data) {
						var finalURL = "";
						
						// Populate the new html that will be a part of this popup.
						for(var i = 0; i < data.suggestion.length; ++i){
								var autoSuggestHTML = "<li class=\"autoselectItem\" id=\"autoSuggestItem\" onclick=$.fn.suggestedTextSelected(\'" + encodeURI(data.suggestion[i]) + "\');>"+ data.suggestion[i] +"</li>";
								finalURL += autoSuggestHTML +"\n";
						}
						
						// Takes the newly created list of <a> tags and populates the html with them.
						$("#autoSuggest").html(finalURL);
					});
					
					// Displays or hides the popup.
					$("#autoSuggest").show();
				} else {
					$("#autoSuggest").hide();
				}
			},500);
		}
	});
	
	// Method added to drop the autosuggest pop up when we click off of it. 
	$(document).click(function() {
		$("#autoSuggest").hide();
		$liSelected.removeClass('selected');
		
	});
	
	// Method called when we select an auto suggestion to change the text in our search text field.
	$.fn.suggestedTextSelected = function(selectedText ) {
		var string = encodeURI(selectedText);
		$("#search-text").val(decodeURI(selectedText));
		$("#autoSuggest").hide();
	}
	
	$(".autoselectItem").hover(function () {
		$(this).addClass('selected');
	},
	function () {
		$(this).removeClass('selected');
	});
});