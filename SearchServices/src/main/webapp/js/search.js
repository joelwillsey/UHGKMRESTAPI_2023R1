var page = 1;
var size = 20;
var contentTypeTags = '';
var filterTags = '';
var publishedid = '';

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
		$(".dpui-widget").trigger("dpui:runSearch");
	});

	$('#search-button').focusin(function() {
		$('#typeahead-container').removeClass('result');
		$('#typeahead-container').removeClass('backdrop');
	});

	// Method added to drop the autosuggest pop up when we click off of it.
	$.typeahead({
	    input: '.search_text',
	    minLength: 2,
	    order: "asc",
	    dynamic: true,
	    delay: 1000,
	    backdrop: {
	        "background-color": "#fff"
	    },
	    source: {
	        "suggestion": {
	            display: "suggestion",
	            data: [{
	                "suggestion": ""
	            }],
	            ajax: function (query) {
	                return {
	                    type: "GET",
	                    contentType : 'application/json',
	                    url: "/searchservices/km/autocomplete/suggest",
	                    dataType : 'json',
	                    path: "suggestion",
	            		beforeSend : function(jqXHR, settings) {
	            			$.fn.setupHeader(jqXHR);
	            		},
	                    data: {
	                        text: "{{query}}"
	                    },
	                    callback: {
	                        done: function (data) {
	                        	log(data);
	                            return data;
	                        }
	                    }
	                }
	            }
	        }
	    },
	    callback: {
	        onClick: function (node, a, item, event) {
	            // You can do a simple window.location of the item.href
//	            alert(JSON.stringify(item));
	        }
	    },
	    debug: true
	});

	// Alert button
    $('#tab-alert-button').on('click', function() {
    	$('#sr-numbers').show();
    	$.fn.toggleMenu(this);
    	$.fn.toggleSearch('alert');
    	$('.dpui-widget').trigger('dpui:hideManageButton');
    	var kTagParameter = $.fn.getParameterKbaseTag();
    	$("#searchResultsTree").addClass("sr_listing_bookmarks_off");
    	$("#srListingResults").removeClass("sr_listing_result_off");
    	$.fn.search('', page, size, kTagParameter, 'content_knowledgealert', 'publishedDate', '', function(data) {		
    		$.fn.sendToResults('Knowledge Alert', data);
    	});

	});

	// Bookmark button
    $('#tab-bookmarks-button').on('click', function() {
    	$('#sr-numbers').hide();
    	$.fn.toggleMenu(this);
    	$.fn.toggleSearch('bookmark');
    	$('.dpui-widget').trigger('dpui:showManageButton');
    	$('#searchResultsTree').removeClass("sr_listing_bookmarks_off");
    	$('#searchResultsTree').addClass("sr_listing_bookmarks");
    	$("#srListingResults").addClass("sr_listing_result_off");
    	var kTagsParameterStrings = $.fn.getParameterKbaseTag();
    	$.fn.bookmark(page, size, kTagsParameterStrings);

	});

    // Featured Content button
	$('#tab-featured-button').on('click', function() {
		$('#sr-numbers').show();
    	$.fn.toggleMenu(this);
    	$.fn.toggleSearch('featured');
    	$('.dpui-widget').trigger('dpui:hideManageButton');
    	$("#searchResultsTree").addClass("sr_listing_bookmarks_off");
    	$("#srListingResults").removeClass("sr_listing_result_off");
    	var kTagsParameterStrings = $.fn.getParameterKbaseTag();
    	$.fn.featured(page, size, kTagsParameterStrings);

	});

	// New or Changed Button button
	$('#tab-new-changed-button').on('click', function() {
		$('#sr-numbers').show();
    	$.fn.toggleMenu(this);
    	$.fn.toggleSearch('neworchanged');
    	$('.dpui-widget').trigger('dpui:hideManageButton');
    	$("#searchResultsTree").addClass("sr_listing_bookmarks_off");
    	$("#srListingResults").removeClass("sr_listing_result_off");
    	var kTagsParameterStrings = $.fn.getParameterKbaseTag();
		
		if (kTagsParameterStrings != null) {
			$.fn.newOrChanged(page, size, kTagsParameterStrings);
		 }

	});

    // Search button
	$('#tab-search-button').on('click', function() {
		$('#sr-numbers').show();
    	// Setup the tabs
    	$.fn.toggleMenu(this);
		$.fn.toggleSearch('search');
		$('.dpui-widget').trigger('dpui:hideManageButton');
    	$("#searchResultsTree").addClass("sr_listing_bookmarks_off");
    	$("#srListingResults").removeClass("sr_listing_result_off");
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
	/*$(document).keydown( function(event) {
		if (event.which === 13){
			$.fn.toggleMenu($('#tab-search-button'));
			$(".dpui-widget").trigger("dpui:runSearch");
		}
	});*/
	
	$.fn.checkEnter = function(e) {
		if (e.which === 13) {
			$.fn.toggleMenu($('#tab-search-button'));
			$(".dpui-widget").trigger("dpui:runSearch");
		}
	}

	// Handle search results
	$.fn.sendToResults = function(label, data) {
		if(label === "Knowledge Alert"){
        	data.isAlert = true;
		}
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
		var kbase = $.fn.getParameterKbaseTag();
		$.fn.serviceCallAsyncFalse('GET', '', searchServiceName + 'km/featured?page=' + page + '&size=' + size + '&kbase_tags=' + kbase, SEARCH_SERVICE_TIMEOUT, function(data) {
			$.fn.sendToResults('Featured Content', data);
        });
		
		/*
		$.fn.serviceCallAsyncFalse('GET', '', searchServiceName + 'km/knowledge/featuredcontent?page=' + page + '&size=' + size + '&tags=' + tags, SEARCH_SERVICE_TIMEOUT, function(data) {
			
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
		});*/
	}

	// New or Changed Service
	$.fn.newOrChanged = function(page, size, kbase) {
        $.fn.serviceCallAsyncFalse('GET', '', searchServiceName + 'km/neworchanged?page=' + page + '&size=' + size + '&kbase_tags=' + kbase, SEARCH_SERVICE_TIMEOUT, function(data) {
        	$.fn.sendToResults('New or Changed', data);
        });
	}

	// Bookmark function
	$.fn.bookmark = function(page, size, tags) {
		//$.fn.serviceCallAsyncFalse('GET', '', searchServiceName + 'km/knowledge/bookmarks?page=' + page + '&size=' + size + '&tags=' + tags, SEARCH_SERVICE_TIMEOUT, function(data) {
		$.fn.serviceCall('GET', '', searchServiceName + 'km/bookmarksv2/listallbookmarks', SEARCH_SERVICE_TIMEOUT, function(data){
			$.fn.sendToResults('My Bookmarks', data);
		});
	}

	// Search function
	$.fn.search = function(search_text, page, size, tags, categories, sort, publishedid, callBack) {
		$.fn.populateURL(search_text, page, size, tags, categories, sort, publishedid, callBack);
		if(!tags) {
			tags = "search_showinsearch";
		} else {
			if (tags.slice(-1) != ","){
				tags += ",";
			}
			tags += "search_showinsearch";
		}
		$.fn.serviceCallAsyncFalse('GET', '', searchServiceName + 'km/knowledge/search?query=' + search_text + '&page=' + page + '&size=' + size + '&tags=' + tags + '&categories=' + categories + '&sort=' + sort + '&publishedid=' + publishedid, SEARCH_SERVICE_TIMEOUT, callBack);
	}
	
	$.fn.populateURL = function(search_text, page, size, tags, categories, sort, publishedid, callBack) {
		if (typeof search_text === 'undefined' || search_text === null || search_text ==='') {
			search_text = '';
		} else {
			// Deals with the '&' character that messes things up
			search_text = search_text.replace("&", "and");			
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
			//added escape to search string to catch all the special characters
			query += 'query=' + escape(search_text) + '&';
			//query += 'query=' + search_text + '&';
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
                	search_text = '*';
                	$('#search-text').val(search_text);
                	$.fn.search(search_text, page, size, filterTags, contentTypeTags, '', publishedid, function(data) {
                		$.fn.sendToResults('Search', data);
                	});
                }
            });
	        self.element.bind("dpui:blankSearch", function(e, data) {
	            log("blankSearch");
	            /*  Commented out as blank search is always followed by alert search but we still need to populate the URL
           	   $.fn.serviceCall('GET', '', searchServiceName + 'km/knowledge/blankResponse' , SEARCH_SERVICE_TIMEOUT, function(data) {
            		$.fn.sendToResults('Search', data);
            	}) */
            	$.fn.populateURL("", page, size, filterTags, contentTypeTags, '', publishedid);
            });
	        self.element.bind("dpui:alertSearch", function(e, data) {
	            log("alertSearch");
	            $.fn.toggleMenu('#tab-alert-button.left.search_alerts');
	            $.fn.toggleSearch('alert');
	            $('.dpui-widget').trigger('dpui:hideManageButton');
	            $('#sr-numbers').show();
	            var kTagParameter = $.fn.getParameterKbaseTag();
	            $("#searchResultsTree").addClass("sr_listing_bookmarks_off");
	        	$("#srListingResults").removeClass("sr_listing_result_off");
	            log("Alert Search Trigger");
	        	$.fn.search('', page, size, kTagParameter, 'content_knowledgealert', 'publishedDate', '', function(data) {
	        		$.fn.sendToResults('Knowledge Alert', data);
	        	});            	
            });
	        self.element.bind("dpui:runSearch", function(e, data) {
                log("runSearch");
                log(data);
                $.fn.toggleMenu('#tab-search-button.search_search');
                $('#sr-numbers').show();
                $.fn.toggleSearch('search');
            	$('.dpui-widget').trigger('dpui:hideManageButton');
            	$("#searchResultsTree").addClass("sr_listing_bookmarks_off");
    	    	$("#srListingResults").removeClass("sr_listing_result_off");
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
                	var kTagParameter = $.fn.getParameterKbaseTag();
                	if (data === 'Knowledge Alert')
                		log("Knowledge Alert Search");
                		$.fn.search('', page, size, kTagParameter, 'content_knowledgealert', '', '', function(data) {
                    		$.fn.sendToResults('Knowledge Alert', data);
                    	});
                	if (data === 'My Bookmarks')
                		$.fn.bookmark(page, size);
                	if (data === 'Featured Content')
                		//pulls in kbase tags from url
                		$.fn.featured(page, size, kTagParameter);
                	if (data === 'Top Content')
                		$.fn.newOrChanged(page, size, kTagParameter);
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

    // Display the Bookmark error(s) in a uniform way
    $.fn.handleBookmarkError = function(contentId) {
    	log('handleBookmarkError()');    	
		$('#background').addClass('background_on');
		if ($('#popup').hasClass('popup_full')){
			//The manage bookmark screen is showing
			$('#manage-bookmarks-background').addClass('background_on');
		}
		$('#error-bookmark-body').html('This content has been archived');
		$('#error-bookmark-message').addClass('error_message_on'); 
        $('#error-remove-button').attr('onclick', '$.fn.errorButtonRemove(\'' + contentId + '\');')	
    }
    
    // Bookmark Error message popup Cancel button
    $.fn.errorButtonCancel = function() {
    	log('errorButtonCancel()');
    	if ($('#background-popup-error').hasClass('background_popup_error_on')) {
    		$('#background-popup-error').removeClass('background_popup_error_on');
    	} else {
    		$('#background').removeClass('background_on');
    	}
    	//for the manage screen
    	if($('#manage-bookmarks-background').hasClass('background_on')){
    		//The manage bookmark screen is showing
    		$('#manage-bookmarks-background').removeClass('background_on');
    	}
    	
    	$('#error-bookmark-message').removeClass('error_message_on');

    	// If the user is not authenticated make them login
    	if (!authenticated) {
    		//window.document.location = "login.html";
    	}
    }
    
 // Bookmark Error message popup Remove button
    $.fn.errorButtonRemove = function(contentId) {
    	log('errorButtonRemove(' + contentId + ')');
    	if ($('#background-popup-error').hasClass('background_popup_error_on')) {
    		$('#background-popup-error').removeClass('background_popup_error_on');
    	} else {
    		$('#background').removeClass('background_on');
    	}
    	//for the manage screen
    	if($('#manage-bookmarks-background').hasClass('background_on')){
    		$('#manage-bookmarks-background').removeClass('background_on');
    	}
    	$('#error-bookmark-message').removeClass('error_message_on');

    	if (contentId != 'undefined' && contentId != '') {
			log('Remove bookmark: ' + contentId);
			if ($('#popup').hasClass('popup_full')){
				//The manage bookmark screen is showing
				$.fn.bookmarkButtonRemove();
			} else {
				$.fn.serviceCall('POST', '', searchServiceName + 'km/bookmark/remove?contentid=' + contentId, SEARCH_SERVICE_TIMEOUT, function() {
					$('#tab-bookmarks-button').click();
				}); 
			}
		}
    	
    	// If the user is not authenticated make them login
    	if (!authenticated) {
    		//window.document.location = "login.html";
    	}
    }
    
    //Used to validate bookmark before view content
    $.fn.validateBookmarkContent = function(contentId, contentType) {
    	var packagedData = [];
		packagedData = {
			"contentId" : contentId,
			"contentType" : contentType
		};    	
    	log('Validate bookmark - contentType=' + contentType +  ' contentId=' + contentId);
    	
		if (typeof contentType != 'undefined' && contentType != '' && contentType != null) {
			if (contentType != 'pageSet') {
				// Call the content service
				$.ajax({
					type : 'GET',
					contentType : 'application/json',
					data : '',
					url : contentServiceName + 'km/content/?contentid=' + encodeURIComponent(contentId) + "&contentType=" + contentType,
					async: true,
					dataType : 'json',
					timeout : CONTENT_SERVICE_TIMEOUT,
					beforeSend : function(jqXHR, settings) {
						$.fn.setupHeader(jqXHR);
						$.fn.setupSpinner();
					},
					success : function(data, textStatus, jqXHR) {
						// valid content type, send content through retrieve process again
						log('Bookmark validated - contentType=' + contentType +  ' contentId=' + contentId);
						if ($('#popup').hasClass('popup_full')){
							//The manage bookmark screen is showing
							$.fn.launchViewContent(contentId, contentType);
						} else {
							$('.dpui-widget').trigger("dpui:viewContent", packagedData);
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						//This not a valid contentId
						$.fn.disableSpinner();
						log('Bookmark validation failed - contentType=' + contentType +  ' contentId=' + contentId);
						$.fn.handleBookmarkError(contentId);
					},
					statusCode : {
						// Authentication error code
						401 : function(jqXHR, textStatus, errorThrown) {
							$.fn.disableSpinner();
							$.fn.handleAuthError(jqXHR, textStatus, errorThrown);
						},
						// Authorization error code
						403 : function(jqXHR, textStatus, errorThrown) {
							$.fn.disableSpinner();
							$.fn.handleAuthError(jqXHR, textStatus, errorThrown);
						},
						// Not found error code
						404 : function(jqXHR, textStatus, errorThrown) {
							$.fn.disableSpinner();
							$.fn.handleError(jqXHR, textStatus, errorThrown);
						}
					},
				}).then(function(data) {
					$.fn.disableSpinner();
				}).responseJSON;					
			} else {
				log('No validation for d-trees (pageSet)');
				$.fn.launchDTContent(contentId, contentType);
			}
		}
	}
    
    

    
    
    // Check if the parameters passed in required a search
    if (!$.fn.checkForContentId()) {
	    if (!$.fn.checkForUrlSearch()) {
	    	/**  Dont need to do this anymore as alerts have been added to the dpui:alertSearch which runs after a dpui:blankSearch
			// Show the alerts on default of page load 
				log('CALLING KNOWLEDGE ALERT');
				$.fn.toggleMenu($('#tab-alert-button'));
		    	$.fn.toggleSearch('alert');
	    		$.fn.search('', page, size, '', 'content_knowledgealert', '', '', function(data) {
					$.fn.sendToResults('Knowledge Alert', data);
				});
	    	}	   
	    	**/ 
	    }
    }
});
