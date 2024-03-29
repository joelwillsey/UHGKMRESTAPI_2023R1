var page = 1;
var size = 20;
var contentTypeTags = ''; //'content_article,content_remotedocument,content_decisiontree,content_faq,content_knowledgealert,content_spidereddocument';
var filterTags = '';
var publishedid = '';
var kbaseTag = '';
var codesTopicTag = '';
var isAuthorizedForCodesKB = false;

$(document).ready(function() {

	//Need to start widgets first because functions after it triggers some of the events
	
	// Inter-widget communication
	$.widget("ui.ajaxStatus", {
        options: {
        },
        _create : function() {
            var self = this;
            self.element.addClass("dpui-widget");
            self.element.bind("dpui:startWidget", function(e) {
                log("startWidget - code_search");
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
	            log("setupTags: " + data);
	            filterTags = data;
	        });
	        self.element.bind("dpui:setupPublishedid", function(e, data) {
	            log("setupPublishedid");
	            publishedid = data;
	        });
	        self.element.bind('dpui:registerCodeSearchResults', function(e) {
                log('registerCodeSearchResults');
            });
	        self.element.bind("dpui:resetSearch", function(e, search_text) {
                log("resetSearch");
                log(search_text);
            	// Search function
                $('#search-text').val(search_text);
                
                var search_text_value = $('#search-text').val();
                search_text = '';
                
                //unless exact search is checked add a wildcard to end of search
                if($('#exact-search').is(':checked')){
                	search_text = search_text_value;
                	log('Exact search query: ' + search_text);
                } else {
                	if (search_text_value != '*'){
                		search_text = search_text_value + "*";
                    	log('Adding wildcard to search query: ' + search_text);
                	} else {
                		search_text = "*";
                	}

                }
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
	        self.element.bind("dpui:runSearch", function(e, data) {
                log("runSearch");
                log(data);
               // $.fn.toggleMenu('#tab-search-button.search_search');
                $.fn.toggleSearch('search');
            	$('.dpui-widget').trigger('dpui:hideManageButton');
                var search_text_value = $('#search-text').val();
                var search_text = '';
                
                //unless exact search is checked add a wildcard to end of search
                if($('#exact-search').is(':checked')){
                	search_text = search_text_value;
                	log('Exact search query: ' + search_text);
                } else {
                	if (search_text_value != '*'){
                		search_text = search_text_value + "*";
                    	log('Adding wildcard to search query: ' + search_text);
                	} else {
                		search_text = "*";
                	}
                }
                
                $(".dpui-widget").trigger("dpui:searchTerm", search_text);
                
                if(typeof filterTags != 'undefined' && filterTags != null){
	                var fTags = kbaseTag + ',' + codesTopicTag;
	    		    $(".dpui-widget").trigger("dpui:setupTags", fTags);
	    		    
                }
                
                $.fn.fillSearchCloud(filterTags);
                
                if (typeof search_text != 'undefined' && search_text != null && search_text != '') {
        			$.fn.addToSearchCloud('search_term', search_text)
        		}
                
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
            self.element.bind("dpui:updateSearchCloud", function(e, data) {
            	log("UpdateSearchCloud " + data);
            	// Refresh by the type
                if (typeof data != 'undefined' && data != null && data != '') {
                }
            });
        },
        destroy: function(){
            $.Widget.prototype.destroy.apply(this, arguments);
        },
    });

	// Send out a "ping" to other widgets
	$.ui.ajaxStatus( {}, $( "<div></div>" ).appendTo( "body") );
    $(".dpui-widget").trigger("dpui:startWidget");
    
 // Load Search Results HTML This is done to ensure the widget defined above has started before the widget contained the js below starts as it can cause errors
	$.get(searchServiceName + 'code_search_results.html', function(data) {
		$('#search-results-widget').html(data);
	});
	
	kbaseTag = $.fn.getProperty('code.search.kbase');
	var topicTags = $.fn.getProperty('code.search.topics');
		
	if (typeof kbaseTag != 'undefined' && kbaseTag && kbaseTag != '') {
		//add tag to cloud search
		var arrayTopicTags = topicTags.split(",")
		log(arrayTopicTags);
		for (var i = 0; i < arrayTopicTags.length; i++) {
			var tagInfo = arrayTopicTags[i].split("|");
			
			if (typeof tagInfo != 'undefined' && tagInfo && tagInfo != '') {
				if (tagInfo.length == 2){
					
					var displayName = tagInfo[0];
					var systemTagName = tagInfo[1];
					$('#code-selection').append($('<option>', {
						value: systemTagName,
						text: displayName
					}));
					
					if (i == 0) {
						//first tag in dropdown is the all tags, make it default
						codesTopicTag = systemTagName;	
					}
					
				} else {
					//config error in code.search.topics					
				}
			} else {
				//config error in code.search.topics			
			}			
		}
		
		topicTag = $("#code-selection").val(); // The value of the selected option
		
	} else {
		//config error in code.search.topics		
	}
	
	
	 $('#code-selection').change(function(){
		    log('codesTopicTag: ' + $(this).val());
		    codesTopicTag = $(this).val();
		    var fTags = kbaseTag + ',' + codesTopicTag;
		    $(".dpui-widget").trigger("dpui:setupTags", fTags);
		  });
	 
	// Service Cloud arrow link
	$('#fs-li-cloud').on('click', function() {
		$.fn.changeArrow('#fs-cloud-data', '#fs-i-cloud');
	});
	
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
		//$.fn.toggleMenu($('#tab-search-button'));
		$('.dpui-widget').trigger('dpui:hideManageButton');
		
	    codesTopicTag = $('#code-selection').val();
	    var fTags = kbaseTag + ',' + codesTopicTag;
	    $(".dpui-widget").trigger("dpui:setupTags", fTags); 
	    if (isAuthorizedForCodesKB){
	    	$(".dpui-widget").trigger("dpui:runSearch");
	    }
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
	
	$.fn.checkEnter = function(e) {
		if (e.which === 13) {
			//$.fn.toggleMenu($('#tab-search-button'));
		    codesTopicTag = $('#code-selection').val();
		    var fTags = kbaseTag + ',' + codesTopicTag;
		    $(".dpui-widget").trigger("dpui:setupTags", fTags);
		    if (isAuthorizedForCodesKB){
		    	$(".dpui-widget").trigger("dpui:runSearch");
		    }
		}
	}

	// Handle search results
	$.fn.sendToResults = function(label, data) {
		var packagedData = [];
		packagedData = {
				"label": label,
				"data": data,
				"query": $('#search-text').val()
		}
		// Send to Search Results Widget
		log('Send event dpui:resultData');
		$(".dpui-widget").trigger("dpui:resultData", packagedData);			
	}

	// Search function
	$.fn.search = function(search_text, page, size, tags, categories, sort, publishedid, callBack) {
		$.fn.populateURL(search_text, page, size, tags, categories, sort, publishedid, callBack);
		if(!tags) {
			tags = "search_showinsearch";
		} else {
			tags += ",search_showinsearch";
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
				history.pushState(stateObj, "newPage", searchServiceName + 'code_search_container.html' + query);
			}
	        document.title = defaultTitle;
		}
		//adding in "search_showinsearch tag" so that it doesn't show up in the url
		if (tags.substring(0,20) != "search_showinsearch,"){
			tags = "search_showinsearch,"+tags;
		}

	}

	
    
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
    		
    		var arrayTopicTags = [];
    		$('#code-selection').find('option').each(function() { arrayTopicTags.push( $(this).val() ); });
  
    		var arrayParamTags = sTags.split(",");
    		
    		for (var i = 0; i < arrayParamTags.length; i++) {
    			
    			var tagName = arrayParamTags[i];
    			var tagInfo = tagName.split("_");
    			if (tagInfo[0] == 'topic'){
    				//found a topic tag
    				for (var y = 0; y < arrayTopicTags.length; y++){
    					if (arrayTopicTags[y] == tagName){
    						//found a good topic tag
    						$('#code-selection').val(arrayTopicTags[y]);
    						break;
    					}
    				}
    			}    			
    		}
    		
    		codesTopicTag = $('#code-selection').val();
 		    var fTags = kbaseTag + ',' + codesTopicTag;
 		    $(".dpui-widget").trigger("dpui:setupTags", fTags);
 		    
    		sTags = kbaseTag + ',' + codesTopicTag;
    		
    		 $(".dpui-widget").trigger("dpui:setupTags", sTags);
    		 
    		if (sCategories != 'undefined' && sCategories != ''){
    			contentTypeTags = sCategories;
    		} else {
    			sCategories = contentTypeTags;
    		}
    		
            //fill in the search box
            $('#search-text').val(sQuery);
            
    		 //unless exact search is checked add a wildcard to end of search
            if($('#exact-search').is(':checked')){
            	log('Exact search query: ' + sQuery);            	
            } else { 
            	if (sQuery != '*'){
	            	sQuery = sQuery + "*";
	            	log('Adding wildcard to search query: ' + sQuery);
            	}
            }    		
    		

            
    		if (typeof sTags != 'undefined' && sTags != null && sTags != '') {
    			$.fn.fillSearchCloud(sTags)
    		}
    		if (typeof sQuery != 'undefined' && sQuery != null && sQuery != '') {
    			$.fn.addToSearchCloud('search_term', sQuery)
    		}    		    		    	   
    	    
    		// Call the search
        	$.fn.search(sQuery, sPage, sSize, sTags, sCategories, sSort, sPublishedid, function(data) {
        		$.fn.sendToResults('Search', data);
        	});
        	
        	
        	retValue = true;
    	}
    	return retValue;
    }
    
    $.fn.fillSearchCloud = function(tags){
		
    	// First parse the list
		var tagArray = tags.split(",");

		var kbaseArray = new Array();
		var topicArray = new Array();
		var productArray = new Array();
		var regionArray = new Array();
		var cntntsmeArray = new Array();
		var cntnttypeArray = new Array();
		var newchangeArray = new Array();
		var searchArray = new Array();
		if (typeof tagArray != 'undefined' && tagArray.length > 0) {
			for (var b=0; b < tagArray.length; b++) {
				var index = tagArray[b].indexOf('_');
				if (index != -1) {
					var tagSet = tagArray[b].substring(0, index);
					var tagName = tagArray[b].substring(index + 1);
					if (tagSet === 'topic') {
						topicArray.push(tagName);
					} else if (tagSet === 'product') {
						productArray.push(tagName);
					} else if (tagSet === 'region') {
						regionArray.push(tagName);
					} else if (tagSet === 'cntntsme') {
						cntntsmeArray.push(tagName);
					} else if (tagSet === 'kbase') {
						kbaseArray.push(tagName);
					} else if (tagSet === 'cntnttype') {
						cntnttypeArray.push(tagName);
					} else if (tagSet === 'newchang') {
						newchangeArray.push(tagName);
					} else if (tagSet === 'search') {
						searchArray.push(tagName);
					}
					
				}
			}
		}
		
		
		var requestTagsets = '';
		if (typeof kbaseArray != 'undefined' && kbaseArray.length > 0) {
			requestTagsets = 'kbase,';
		}
		if (typeof topicArray != 'undefined' && topicArray.length > 0) {
			requestTagsets += 'topic,';
		}
		if (typeof productArray != 'undefined' && productArray.length > 0) {
			requestTagsets += 'product,';
		}
		if (typeof regionArray != 'undefined' && regionArray.length > 0) {
			requestTagsets += 'region,';
		}
		if (typeof cntntsmeArray != 'undefined' && cntntsmeArray.length > 0) {
			requestTagsets += 'cntntsme,';
		}
		if (typeof cntnttypeArray != 'undefined' && cntnttypeArray.length > 0) {
			requestTagsets += 'cntnttype,';
		}
		if (typeof newchangeArray != 'undefined' && newchangeArray.length > 0) {
			requestTagsets += 'newchange,';
		}
		if (typeof searchArray != 'undefined' && searchArray.length > 0) {
			requestTagsets += 'search';
		}
		
		log('Query Tagsets" ' + requestTagsets);
			
		var url = filtersServiceName + 'km/tags/gettagsfortagsets?tagsets=' + requestTagsets;			

    	jQuery.ajaxSetup({
			async : false
		});
    	
		$.fn.serviceCall('GET', '', url, 15000, function(data) {
			log(data);
			
			if (typeof data.tagSets != 'undefined' && data.tagSets != null && data.tagSets.length > 0) {
				for (var x = 0; x < data.tagSets.length; x++) {					
					var currentTagSet = data.tagSets[x];
					if (typeof currentTagSet.tags != 'undefined' && currentTagSet.tags != null && currentTagSet.tags.length > 0) {
						if (currentTagSet.systemTagName === 'topic') {
							for (var y = 0; y < topicArray.length; y++){						
								for (var z = 0; z <currentTagSet.tags.length; z++) {
									if (currentTagSet.tags[z].systemTagName == 'topic_' + topicArray[y]){
										$.fn.addToSearchCloud('topic', currentTagSet.tags[z].systemTagDisplayName);
										break;
									}
								}							
							}						
						} else if (currentTagSet.systemTagName === 'product') {
							for (var y = 0; y < productArray.length; y++){
								for (var z = 0; z < currentTagSet.tags.length; z++) {
									if (currentTagSet.tags[z].systemTagName == 'product_' + productArray[y]){
										$.fn.addToSearchCloud('product', currentTagSet.tags[z].systemTagDisplayName);
										break;
									}
								}							
							}						
						} else if (currentTagSet.systemTagName === 'kbase') {
							for (var y = 0; y < kbaseArray.length; y++){
								for (var z = 0; z < currentTagSet.tags.length; z++) {
									if (currentTagSet.tags[z].systemTagName == 'kbase_' + kbaseArray[y]){
										$.fn.addToSearchCloud('kbase', currentTagSet.tags[z].systemTagDisplayName);
										break;
									}
								}							
							}						
						} else if (currentTagSet.systemTagName === 'region') {
							for (var y = 0; y < regionArray.length; y++){
								for (var z = 0; z < currentTagSet.tags.length; z++) {
									if (currentTagSet.tags[z].systemTagName == 'region_' + regionArray[y]){										
										$.fn.addToSearchCloud('region', currentTagSet.tags[z].systemTagDisplayName);
										break;
									}
								}							
							}												
						} else if (currentTagSet.systemTagName === 'cntnttype') {
							for (var y = 0; y < cntnttypeArray.length; y++){
								for (var z = 0; z < currentTagSet.tags.length; z++) {
									if (currentTagSet.tags[z].systemTagName == 'cntnttype_' + cntnttypeArray[y]){
										//Note the 0 index is the tagset display name
										$.fn.addToSearchCloud('cntnttype', currentTagSet.tags[z].systemTagDisplayName);
										break;
									}
								}							
							}												
						} else if (currentTagSet.systemTagName === 'cntntsme') {						
							for (var y = 0; y < cntntsmeArray.length; y++){
								for (var z = 0; z < currentTagSet.tags.length; z++) {
									if (currentTagSet.tags[z].systemTagName == 'cntntsme_' + cntntsmeArray[y]){
										$.fn.addToSearchCloud('cntntsme', currentTagSet.tags[z].systemTagDisplayName);
										break;
									}
								}							
							}												
						} else if (currentTagSet.systemTagName === 'newchange') {						
							for (var y = 0; y < newchangeArray.length; y++){
								for (var z = 0; z < currentTagSet.tags.length; z++) {
									if (currentTagSet.tags[z].systemTagName == 'newchange_' + newchangeArray[y]){
										$.fn.addToSearchCloud('newchange', currentTagSet.tags[z].systemTagDisplayName);
										break;
									}
								}							
							}											
						} else if (currentTagSet.systemTagName === 'search') {						
							for (var y = 0; y < searchArray.length; y++){
								for (var z = 0; z < currentTagSet.tags.length; z++) {
									if (currentTagSet.tags[z].systemTagName == 'search_' + searchArray[y]){
										$.fn.addToSearchCloud('search', currentTagSet.tags[z].systemTagDisplayName);
										break;
									}
								}							
							}												
						}
					}
				}
			}
			
		});
		
		jQuery.ajaxSetup({
			async : true
		});
				
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
    		//$.fn.toggleMenu($('#tab-search-button'));
    		$.fn.toggleSearch('search');
    	}
    	return retValue;
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
			//buildLi += '<a class="search_choice_close" rel="0" tagtype="' + type + '"></a>';
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
					+  'Topic: ' + element + '" tagtype="' + type + '">';
			var value = element;
			buildLi += '<span>' + value + '</span>';
			buildLi += '</li>';
			//log(buildLi);
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
					var kbaseId = li.context.id;
					$('#'+kbaseId).remove();
				}
			});

			// creating the tags to put into the cloud/search
			var buildLi = '<li id="sc-'
					+ type + '_' + element
					+ '" class="search-choice search-choice-cloud" title="Knowledge Base: ' +  element + '" rel="0">';
					//+ $('#' + element).attr('rel') + '">';
			//buildLi += '<span>' + $('#' + element).text() + '</span>';
			buildLi += '<span>' + element + '</span>';
			buildLi += '</li>';
			log(buildLi);

			// inserts the html code at the designated spot
			$(buildLi).insertAt(1, $('.ul_all_tags'));	
		} else {
			var buildLi = '<li id="sc-' + element + '" class="search-choice search-choice-cloud" title="' 
			+ $('#' + element).text() + '" rel="' + $('#' + element).attr('rel') + '">';
			buildLi += '<span>' + $('#' + element).text() + '</span>';
			buildLi += '<a class="search_choice_close" rel="' + $('#' + element).attr('rel') + ' tagtype="' + type + '"></a>';
			buildLi += '</li>';
			log(buildLi);
			$(buildLi).insertAt(1, $('.ul_all_tags'));
		}

		
		if (typeof contentTypeTags != 'undefined' || contentTypeTags != null) {
			var contentTags = contentTypeTags.split(",");
			var contentDisplayNames = '';
			
			for (var i = 0; i < contentTags.length; i++) {
				
				switch(contentTags[i]) {
				case 'content_article':
					contentDisplayNames += 'Article ';
					break;
				case 'content_remotedocument':
					contentDisplayNames += 'Remote Document ';
					break;
				case 'content_decisiontree':
					contentDisplayNames += 'Decision Tree ';
					break;
				case 'content_faq':
					contentDisplayNames += 'FAQ ';
					break;
				case 'content_knowledgealert':
					contentDisplayNames += 'Knowledge Alert ';
					break;
				case 'content_spidereddocument':
					contentDisplayNames += 'Spidered Document ';
					break;
				case 'content_uploadeddocument':
					contentDisplayNames += 'Uploaded Document ';
					break;				
				}				
			}
			
			var buildLi = '<li id="sc-ContentTypes" class="search-choice search-choice-cloud" title="Content Types">';			
			buildLi += '<span>' + contentDisplayNames.substring(0, 12) + '...</span>';
			buildLi += '</li>';
			
			if ($('#sc-ContentTypes').length){
				$('#sc-ContentTypes').remove();
				$('.ul_all_tags').append(buildLi);
			} else {
				$('.ul_all_tags').append(buildLi);
			}
		}

		//$(".dpui-widget").trigger("dpui:updateSearchCloud", buildLi);
	}	

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
	
	$.fn.checkKBaseTags = function(systemTagName) {
		var url = filtersServiceName + 'km/kbasetags';		
		var result = false;
		
    	jQuery.ajaxSetup({
			async : false
		});
    	
		$.fn.serviceCall('GET', '', url, 15000, function(data) {
			
			var kbaseData = '';
			if (typeof data.tags != 'undefined' && data.tags != null && data.tags.length > 0) {
				for (var x = 0; x < data.tags.length; x++) {
					if (typeof data.tags[x] != 'undefined' && data.tags[x] != null && data.tags[x].systemTagName.length > 0) {
						//log('tag[' + x + '].systemTagName=' + data.tags[x].systemTagName);
						if (data.tags[x].systemTagName == systemTagName){
							result = true;
							//log ('result = ' + result);
							break;}
						}
					}
				}
		});

		jQuery.ajaxSetup({
			async : true
		});
		log ('Agent result of $.fn.checkKBaseTags(' + systemTagName + ') = '+ result);
		return result;
	}
	
	
    // Check if the parameters passed in required a search
	if ($.fn.checkKBaseTags(kbaseTag)){
		isAuthorizedForCodesKB = true;
	    if (!$.fn.checkForContentId()) {
		    if (!$.fn.checkForUrlSearch()) {
		    	// Setup initial sizes
		    	//$.fn.setupSearchWidget();
		    	//$.fn.setupSearchResultsWidget();
		    }
	    }
	} else {
		//not authorized for code KB
		log('Show error message: Error: You are not authorized for code search');
		$('#background').addClass('background_on');
		$('#error-body').html('Error: You are not authorized for code search');
		$('#error-message').addClass('error_message_on');
	}
});