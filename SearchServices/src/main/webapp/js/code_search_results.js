var pageSelected;
var sortBy;
var query;
var base_query;
var filterTags;
var contentType;
var kbaseTag = '';
var policiesTopicTag = '';
var codesTopicTag;
var maxSizeOfPolicyResults;
var defaultTitle = 'Verint Code Search';
var searchFeedbackURL;


$(document).ready(function() {

	kbaseTag = $.fn.getProperty('code.search.kbase');
	policiesTopicTag = $.fn.getProperty('code.search.topic.policies');
	maxSizeOfPolicyResults = parseInt($.fn.getProperty('code.search.topic.policies.maxsize'));
	
	if(maxSizeOfPolicyResults == 'undefined' || maxSizeOfPolicyResults == null){
		maxSizeOfPolicyResults = 50;
	}
	
	// Setup package data
	var setupPackage = function(toSortBy) {
		sortBy = toSortBy;
    	var packagedData = [];
    	packagedData = {
    			"page": pageSelected,
    			"sort": sortBy
    	}
    	return packagedData;
	}

	codesTopicTag = $('#code-selection').val();
	filterTags = kbaseTag + "," + codesTopicTag;
	

	log("filterTags:" + filterTags);
	base_query = $('#search-text').val();
	log("base_query:" + base_query);
	contentType = $.fn.getParameterByName('categories');
	log("contentType:" + contentType);

	
	// Sort by Relevance
	var sortByRelevance = function(event) {
    	$('#sr-sort-by-date').removeClass('active');
    	$('#sr-sort-by-relevance').addClass('active');
    	$('#sr-sort-by-relevance').off('click');
    	$('#sr-sort-by-date').on('click', function(event) {
        	$('#sr-sort-by-relevance').removeClass('active');
        	$('#sr-sort-by-date').addClass('active');
        	$('#sr-sort-by-relevance').on('click', sortByRelevance);
        	$('#sr-sort-by-date').off('click');
        	$('.dpui-widget').trigger("dpui:runSearch", setupPackage('publishedDate'));
    	});
    	$('.dpui-widget').trigger("dpui:runSearch", setupPackage(''));
	}

	// Did you mean button
	$.fn.didYouMeanButton = function(query) {
		// Call search widget to do new search
		$('.dpui-widget').trigger("dpui:resetSearch", query);
	}
	
	// Sort by date link
	$('#sr-sort-by-date').on('click', function(event) {
    	$('#sr-sort-by-relevance').removeClass('active');
    	$('#sr-sort-by-date').addClass('active');
    	$('#sr-sort-by-relevance').on('click', sortByRelevance);
    	$('#sr-sort-by-date').off('click');
    	$('.dpui-widget').trigger("dpui:runSearch", setupPackage('publishedDate'));
	});

	// Request Answer
	$('#sr-request').on('click', function() {
		
		//need to set the URL parameters
		
		codesTopicTag = $('#code-selection').val();
		filterTags = kbaseTag + "," + codesTopicTag;
		base_query = $('#search-text').val();
		
		$.fn.populateURL(base_query, page, size, filterTags, contentType, '', publishedid);
		
		log('query: ' + base_query);
		// Load Request Answer HTML
			$('#popup').html($('#request-answer-widget').html());
			$('#request-answer-widget').html('');
			$.fn.initData(query, '');
			$('#background').addClass('background_on');
			$('#popup').addClass('popup');
			$('#popup').addClass('popup_on');
			

			
	});
		
	// Call search to paginate
	$.fn.pageSelected = function(data) {
		log("pageSelected - " + data);
		
    	var packagedData = [];
    	
    	if (sortBy == undefined) {
    		sortBy = "";
    	}
    	
    	if (query == undefined || query == "") {
    		query = base_query;   		
    	}
    	    	
    	
    	// put the scroll bar back to the top
    	$('#sr-listing').scrollTop(0);

    	codesTopicTag = $('#code-selection').val();
    	filterTags = kbaseTag + "," + codesTopicTag;
    	
		$.fn.search(query, data, size, filterTags, contentType, sortBy, publishedid, function(data) {
    		$.fn.sendToResults('Search', data);
    	});
    	
	}
	
	// Call to view Content
	$.fn.viewContent = function(contentId, contentType, externalSearchId) {
		var packagedData = [];
		packagedData = {
			"contentId" : contentId,
			"contentType" : contentType,
			"externalSearchId" : externalSearchId
		}

		//hide the code search window
		$('.dpui-widget').trigger('dpui:hideCodeSearch');
		
		var sPageURL = decodeURIComponent(window.location.search.substring(1));
		$('.dpui-widget').trigger("dpui:viewContent", packagedData);

	}
	$.fn.viewExternalContent = function(contentId, url, isFeatured, averageRating, numRatings, title, publishedDate, tags) {
    	var packagedData = [];
    	packagedData = {
    			"contentId": contentId,
    			"url": url,
    			"isFeatured": isFeatured,
    			"averageRating": averageRating,
    			"numberOfRatings": numRatings,
    			"title": title,
    			"publishedDate": publishedDate,
    			"tags": tags,
    			"bookmarked": false
    	}

    	//hide the code search window
    	$('.dpui-widget').trigger('dpui:hideCodeSearch');
    	
    	log(packagedData);
		$('.dpui-widget').trigger("dpui:viewExternalContent", packagedData);
	}

	// Open up new window/tab to view content
	$.fn.launchViewContent = function(data) {

		window.open (contentServiceName + 'iset_content_container.html?id=' + data, data + '_contentwindow','scrollbars=1,menubar=1,resizable=1,width=1040,height=850');
	}
	$.fn.launchDTContent = function(data) {

		window.open (contentServiceName + 'iset_content_container.html?dtreeid=' + data, data + '_contentwindow','scrollbars=1,menubar=1,resizable=1,width=1040,height=850');
	}
	$.fn.launchViewExternalContent = function(contentId, url, isFeatured, averageRating, numRatings, title, publishedDate, tags) {

		var passedUrl = 'contentId=' + encodeURIComponent(contentId) + '&url=' + encodeURIComponent(url) + '&isFeatured=' + isFeatured + '&averageRating=' + averageRating;
		passedUrl += '&numRatings=' + numRatings + '&title=' + encodeURIComponent(title) + '&publishedDate=' + encodeURIComponent(publishedDate) + '&tags=' + encodeURIComponent(tags);
		window.open (contentServiceName + 'iset_content_container.html?'+ passedUrl, contentId + '_contentwindow','scrollbars=1,menubar=1,resizable=1,width=1030,height=750');
	}

	$.fn.addslashes = function( str ) {
		// added the .replace(/\\"/g, "&quot;") because the highlighting of search terms broke the format of the function when the " was in the string
	    return (str + '').replace(/[\\"']/g, '\\$&').replace(/\u0000/g, '\\0').replace(/\\"/g, "&quot;").replace(/\'/g, "&#39;");
	}
	
	// Setup search results numbers
	$.fn.setupResultsNumbers = function(data) {
		
		//declare these variables locally to deal with services that dont return them
		var page;
		var size;
		
		//runs checks to give default values to page and size
		if(data.page == 'undefined' || data.page == null){
			page = 1;
		} else {
			page = data.page;
		}
		if(data.size == 'undefined' || data.size == null){
			size = 20;
		} else {
			size = data.size;
		}
		
		data.totalPages = Math.ceil(data.numberOfResults / data.size);
		// sets values for the showing numbers
		var oneOf = ((page-1) * size);
		var twoOf = oneOf + size;
		// Check if we are at the end of the pagination
		if ((data.numberOfResults < size) || (data.numberOfResults < twoOf)) {
			var twoOf = data.numberOfResults;				
		}
		$('.sr_numbers_showing').html('Showing ' + (oneOf + 1) + ' - ' + twoOf + ' of ' + data.numberOfResults + ' Results');
	}
	
	// Setup slice results if there are any
	$.fn.setupSlicedContent = function(data, results, addPolicySearch, externalSearchId) {
		results.push('<article>');
		if (addPolicySearch){
			results.push('  <a class="sr_lr_icon_expand_collapse" href="javascript:void(0);" onclick="$.fn.launchPolicySearch(\'' + $.fn.getAlphaNumeric(data.contentID) + '\', \'' + $.fn.addslashes(data.title) + '\');"><div id="policy_' + $.fn.getAlphaNumeric(data.contentID) + '" class="sr_lr_icon_expand_collapse sr_lr_icon_content_toggle_expand">&nbsp;&nbsp;</div></a>');
		}
		// Check for a decision tree or not
		if (data.contentType === 'pageSet') {
			results.push('  <a class="sr_lr_article" href="javascript:void(0);" onclick="$.fn.launchDTContent(\'' + data.contentID + '\', \'' + data.contentType + '\');">');
		}  else if (data.contentType === 'Spidered') {
			//Spidered Content
			
			//Grab the tags for display, only have system name not display names
			var nTags = data.knowledgeUnits[0].tags;
			var passTags = '';
			if (typeof nTags != 'undefined' && nTags != null && nTags.length > 0) {
				for (var j=0; j < nTags.length; j++) {
					passTags += nTags[j].systemTagName + ",";
				}
			}
/*			results.push('  <a class="sr_lr_article" href="javascript:void(0);" onclick="$.fn.viewExternalContent(\'' +
					data.contentID + '\',\'' +
					data.knowledgeUnits[0].associatedContentURL + '\',' + 
					data.isFeatured + ',' +
					data.averageRating + ',' +
					data.numberOfRatings + ',\'' +
					$.fn.addslashes(data.title) + '\',\'' +
					data.knowledgeUnits[0].lastPublishedDate + '\',\'' +
					passTags + '\');">');*/
			results.push('  <a class="sr_lr_article" href="javascript:void(0);" onclick="$.fn.viewContent(\'' + data.contentID + '\', \'' + data.contentType + '\', \'' + externalSearchId +'\');">');
		} else {
			results.push('  <a class="sr_lr_article" href="javascript:void(0);" onclick="$.fn.viewContent(\'' + data.contentID + '\', \'' + data.contentType + '\', \'' + externalSearchId + '\');">');
		}		
		results.push('    <div class="sr_lr_icon sr_lr_icon_' + data.knowledgeUnits[0].contentCategoryTags[0].systemTagName + '">&nbsp;&nbsp;</div>');
		results.push('    <div class="sr_lr_title">' + data.title);
		if (data.isFeatured) {
			results.push('  <img src="images/AKCBFeatured14x14.png" />');
		}
		if ($.fn.isContentNewOrChanged(data.knowledgeUnits["0"].lastModifiedDate, data.knowledgeUnits["0"].tags)) {
			results.push('  <img src="images/iconNewContent.png" title="New Content"/>');
		}
		results.push('&nbsp;</div>');
		results.push('  </a>');

		// Setup the slices if there are any
		for (var x=0; x < data.knowledgeUnits.length; x++) {
			results.push('<a class="sr_lr_page" href="javascript:void(0);"><div class="sr_lr_title">Page ' + (x+1) + '</div></a>');
			results.push('<div class="sr_lr_synopsis">' + data.knowledgeUnits[x].synopsis + '</div>');
			results.push('<div class="sr_lr_info">');
			results.push('  <div class="sr_lr_info_date">' + data.knowledgeUnits[x].lastPublishedDate + '</div>');
			results.push('</div>');
		}
		if (addPolicySearch){
			results.push('<div id="policy_search_' + $.fn.getAlphaNumeric(data.contentID) + '" class="sr_lr_policy_search_off"><p><span id="policies_header_' + $.fn.getAlphaNumeric(data.contentID) + '" class="policies_header">Policies</span></p><div id="policy_search_results_' + $.fn.getAlphaNumeric(data.contentID) + '" class="sr_lr_policy_search_results policies_search_box"><span style="color: #a2a2a2;">Search Results</span></div></div>');
		}
		results.push('</article>');
		return results;
	}
	
	// Setup external content links
	$.fn.setupExternalContent = function(data, results, addPolicySearch, externalSearchId) {
		//log('setupExternalContent');
		//log(data);
		var externalSearchIdParam = '';		
		if (typeof externalSearchId != 'undefined' && externalSearchId != ''){
			externalSearchIdParam = ", " + externalSearchId
		}
		
		var nTags = data.knowledgeUnits[0].tags;
		var passTags = '';
		if (typeof nTags != 'undefined' && nTags != null && nTags.length > 0) {
			for (var j=0; j < nTags.length; j++) {
				passTags += nTags[j].systemTagName + ",";
			}
		}
		results.push('<article>');
		if (addPolicySearch){
			results.push('  <a class="sr_lr_icon_expand_collapse" href="javascript:void(0);" onclick="$.fn.launchPolicySearch(\'' + $.fn.getAlphaNumeric(data.contentID) + '\', \'' + $.fn.addslashes(data.title) + '\');"><div id="policy_' + $.fn.getAlphaNumeric(data.contentID) + '" class="sr_lr_icon_expand_collapse sr_lr_icon_content_toggle_expand">&nbsp;&nbsp;</div></a>');
		}
/*		results.push('  <a class="sr_lr_article" href="javascript:void(0);" onclick="$.fn.viewExternalContent(\'' +
			data.contentID + '\',\'' +
			data.knowledgeUnits[0].associatedContentURL + '\',' + 
			data.isFeatured + ',' +
			data.averageRating + ',' +
			data.numberOfRatings + ',\'' +
			$.fn.addslashes(data.title) + '\',\'' +
			data.knowledgeUnits[0].lastPublishedDate + '\',\'' +
			passTags + '\');">');*/
		results.push('  <a class="sr_lr_article" href="javascript:void(0);" onclick="$.fn.viewContent(\'' + data.contentID + '\', \'' + data.contentType + '\', \'' + externalSearchId + '\');">');
		results.push('    <div class="sr_lr_icon sr_lr_icon_' + data.knowledgeUnits[0].contentCategoryTags[0].systemTagName + '">&nbsp;&nbsp;</div>');
		results.push('    <div class="sr_lr_title">' + data.title);

		// Should we show the featured indicator?
		if (data.isFeatured) {
			results.push('<img src="images/AKCBFeatured14x14.png" />');
		}

		results.push('&nbsp;</div>');
		results.push('    <div class="sr_lr_synopsis">' + data.knowledgeUnits[0].synopsis + '</div>');
		results.push('    <div class="sr_lr_info">');
		results.push('      <div class="sr_lr_info_date">' + data.knowledgeUnits[0].lastPublishedDate + '</div>');
		results.push('      <div class="sr_lr_info_status">');
		results.push('        <div class="sr_lr_info_status_published">'+ data.knowledgeUnits[0].workflowState + '</div>');
		results.push('      </div>');
		results.push('    </div>');
		results.push('  </a>');
		results.push('  <a class="sr_lr_link" href="javascript:void(0);" title="Open in new window" onclick="$.fn.launchViewExternalContent(\'' + 
			data.contentID + '\',\'' +
			data.knowledgeUnits[0].associatedContentURL + '\',' + 
			data.isFeatured + ',' +
			data.averageRating + ',' +
			data.numberOfRatings + ',\'' +
			$.fn.addslashes(data.title) + '\',\'' +
			data.knowledgeUnits[0].lastPublishedDate + '\',\'' +
			passTags + '\');"><img src="images/ReadLaterGray16x16.png"/></a>');
		if (addPolicySearch){
			results.push('<div id="policy_search_' + $.fn.getAlphaNumeric(data.contentID) + '" class="sr_lr_policy_search_off"><p><span id="policies_header_' + $.fn.getAlphaNumeric(data.contentID) + '" class="policies_header">Policies</span></p><div id="policy_search_results_' + $.fn.getAlphaNumeric(data.contentID) + '" class="sr_lr_policy_search_results policies_search_box"><span style="color: #a2a2a2;">Search Results</span></div></div>');
		}
		results.push('</article>');
		return results;		
	}
	
	// Setup results links
	$.fn.setupResultsLinks = function(data, results, addPolicySearch) {
		results.push('<article>');
		if (addPolicySearch){
			results.push('  <a class="sr_lr_icon_expand_collapse" href="javascript:void(0);" onclick="$.fn.launchPolicySearch(\'' + $.fn.getAlphaNumeric(data.contentID) + '\', \'' + $.fn.addslashes(data.title) + '\');"><div id="policy_' + $.fn.getAlphaNumeric(data.contentID) + '" class="sr_lr_icon_expand_collapse sr_lr_icon_content_toggle_expand">&nbsp;&nbsp;</div></a>');
		}
		// Check for a decision tree or not
		if (data.contentType === 'pageSet') {
			results.push('  <a class="sr_lr_article" href="javascript:void(0);" onclick="$.fn.launchDTContent(\'' + data.contentID + '\', \'' + data.contentType + '\');">');
		} else {
			results.push('  <a class="sr_lr_article" href="javascript:void(0);" onclick="$.fn.viewContent(\'' + data.contentID + '\', \'' + data.contentType + '\');">');
		}
		results.push('    <div class="sr_lr_icon sr_lr_icon_' + data.knowledgeUnits[0].contentCategoryTags[0].systemTagName + '">&nbsp;&nbsp;</div>');
		results.push('    <div class="sr_lr_title">' + data.title);
		if (data.isFeatured) {
			results.push('  <img src="images/AKCBFeatured14x14.png" />');
		}
		
		if ($.fn.isContentNewOrChanged(data.knowledgeUnits["0"].lastModifiedDate, data.knowledgeUnits["0"].tags)) {
			results.push('  <img src="images/iconNewContent.png" title="New Content"/>');
		}
		
		results.push('&nbsp;</div>');
		results.push('    <div class="sr_lr_synopsis">' + data.knowledgeUnits[0].synopsis + '</div>');
		results.push('    <div class="sr_lr_info">');
		results.push('      <div class="sr_lr_info_date">' + data.knowledgeUnits[0].lastPublishedDate + '</div>');
		results.push('      <div class="sr_lr_info_status">');
		results.push('        <div class="sr_lr_info_status_published">' + data.knowledgeUnits[0].workflowState + '</div>');
		results.push('      </div>');
		results.push('    </div>');
		results.push('  </a>');
		// Check for a decision tree or not
		if (data.contentType === 'pageSet') {
			results.push('  <a class="sr_lr_link" href="javascript:void(0);" title="Open in new window" onclick="$.fn.launchDTContent(\'' + data.contentID + '\');"><img src="images/ReadLaterGray16x16.png"/></a>');
		} else {
			results.push('  <a class="sr_lr_link" href="javascript:void(0);" title="Open in new window" onclick="$.fn.launchViewContent(\'' + data.contentID + '\');"><img src="images/ReadLaterGray16x16.png"/></a>');
		}
		if (addPolicySearch){
			results.push('<div id="policy_search_' + $.fn.getAlphaNumeric(data.contentID) + '" class="sr_lr_policy_search_off"><p><span id="policies_header_' + $.fn.getAlphaNumeric(data.contentID) + '" class="policies_header">Policies</span></p><div id="policy_search_results_' + $.fn.getAlphaNumeric(data.contentID) + '" class="sr_lr_policy_search_results policies_search_box"><span style="color: #a2a2a2;">Search Results</span></div></div>');
		}
		results.push('</article>');
		return results;
	}
	
	// Setup search results list
	$.fn.setupResultsListing = function(data) {
		var results = [];
		var searchFeedbackURL;
		var externalSearchId = '';
		
		// First check if we have suggested content
		if (typeof data != 'undefined' && data != null && typeof data.suggestedQueries != 'undefined' && data.suggestedQueries != null && data.suggestedQueries.length > 0) {
			// Check for 0 results
			if (data.numberOfResults === 0) {
				$('.sr_numbers_showing').html('Showing 0 of 0 results');
			}
			externalSearchId = data.externalSearchId;
			
			var queryText = '';
			// Check for a REPLACED_SPECIFIC_TERMS first
			for (var i=0; i < data.suggestedQueries.length; i++) {
				if (data.suggestedQueries[i].type === 'REPLACED_SPECIFIC_TERMS') {
					results.push('<div>');
					results.push('	<div class="sr_lr_didyoumean_label" title="Did you mean:">Did you mean:</div>');
					results.push('	<div style="position:relative;">');
					results.push('		<a id="sr-lr-didyoumean" class="sr_lr_didyoumean" title="' + data.suggestedQueries[i].queryText + '" tabindex="" onclick="$.fn.didYouMeanButton(\'' + data.suggestedQueries[i].queryText + '\');" href="javascript:void(0);">' + data.suggestedQueries[i].queryText + '</a>');
					results.push('	</div>');
					results.push('</div>');
					queryText = data.suggestedQueries[i].queryText;
				}
			}
			results.push('<div class="sr_lr_suggested">');
			results.push('  <div>');
			results.push('	  <h3 class="">Suggested Searches</h3>');
			results.push('  </div>');
			results.push('  <div class="sr_lr_suggested_box">');
			var qText = ''
			// Go back through the suggested results
			for (var i=0; i < data.suggestedQueries.length; i++) {
				if (data.suggestedQueries[i].type != 'REPLACED_SPECIFIC_TERMS') {
					qText = data.suggestedQueries[i].queryText;
					var n = qText.indexOf("<del>");
					var o = qText.indexOf("</del>");
					while (n != -1 && o != -1) {
						var beginqText = qText.substring(0, n);
						var endqText = qText.substring(o + 6);
						qText = beginqText + endqText;
						n = qText.indexOf("<del>");
						o = qText.indexOf("</del>");						
					}
					results.push('	  <div class="sr_lr_suggested_results">');
					results.push('		<a onclick="$.fn.didYouMeanButton(\'' + qText + '\');" href="javascript:void(0);" tabindex="">');
					results.push('			' + data.suggestedQueries[i].numberOfResults + ' results for ' + data.suggestedQueries[i].queryText);
					results.push('		</a>');
					results.push('	  </div>');
					results.push('	  <div style="padding: 18px 6px 12px;">');
					if (typeof data != 'undefined' && data != null && typeof data.suggestedQueries != 'undefined' && typeof data.suggestedQueries[i] != 'undefined' && data.suggestedQueries[i] != null && typeof data.suggestedQueries[i].knowledgeGroupUnits != 'undefined' && data.suggestedQueries[i].knowledgeGroupUnits != null && data.suggestedQueries[i].knowledgeGroupUnits.length > 0) {
						// Loop through the results
						for (var p = 0; (data.suggestedQueries[i].knowledgeGroupUnits != null) && (p < data.suggestedQueries[i].knowledgeGroupUnits.length); p++) {
							if (data.suggestedQueries[i].knowledgeGroupUnits[p].knowledgeUnits != null && 
								data.suggestedQueries[i].knowledgeGroupUnits[p].knowledgeUnits.length > 1) {
								results = $.fn.setupSlicedContent(data.suggestedQueries[i].knowledgeGroupUnits[p], results, true, externalSearchId);
							} else {
								// Do we have spidered content to setup?
								if (data.suggestedQueries[i].knowledgeGroupUnits[p].contentType === 'Spidered') {
									results = $.fn.setupExternalContent(data.suggestedQueries[i].knowledgeGroupUnits[p], results, true, externalSearchId);
									
								} else {
									// "regular" content
									results = $.fn.setupResultsLinks(data.suggestedQueries[i].knowledgeGroupUnits[p], results, true);
								}
							}
						}
					}
					results.push('	  </div>');
				}
			}
			results.push('    <div class="sr_lr_seemore_wrapper">');
			results.push('      <a class="sr_lr_seemore" tabindex="-1" onclick="$.fn.didYouMeanButton(\'' + qText + '\');" href="javascript:void(0);">See More >></a>');
			results.push('    </div>');
			results.push('  </div>');
			results.push('</div>');
		}

		// See if there is regular results
		if (typeof data.knowledgeGroupUnits != 'undefined' && data.knowledgeGroupUnits != null && data.knowledgeGroupUnits.length > 0) {
			// Loop through the results
			for (var i=0;(data.knowledgeGroupUnits != null) && (i < data.knowledgeGroupUnits.length); i++) {
				if (data.knowledgeGroupUnits[i].knowledgeUnits != null && data.knowledgeGroupUnits[i].knowledgeUnits.length > 1) {
					results = $.fn.setupSlicedContent(data.knowledgeGroupUnits[i], results, true, externalSearchId);
				} else {
					// Do we have spidered content to setup?
					if (data.knowledgeGroupUnits[i].contentType === 'Spidered') {
						results = $.fn.setupExternalContent(data.knowledgeGroupUnits[i], results, true, externalSearchId);
					} else {
						// "regular" content
						results = $.fn.setupResultsLinks(data.knowledgeGroupUnits[i], results, true);
					}
				}
			}
		}
		$('.sr_listing_result').html(results.join('\n'));
		results.length = 0; // Clear the array
	}
	
	// Setup pagination view
	$.fn.setupPagination = function(data) {
		if($('#tab-bookmarks-button').hasClass('sel')) {
			data.totalPages = 1;
		}
		// Now setup the pagination
		var pagination = [];
		pagination.push('<nav><ul>');
		// Check if we are on page 1 or another page
		if (data.page === 1) {
			pagination.push('<li class="prev"><a class="prev">&lt;</a></li>');
		} else {
			pagination.push('<li class="prev"><a class="prev active" onclick="$.fn.pageSelected(' + (data.page-1) + ');">&lt;</a></li>');
		}
		// Check if there are more than 5 pages
		if (data.totalPages <= 5) { 
			for (var z = 1; z <= data.totalPages; z++) {
				if (data.page === z) {
					pagination.push('<li class="sel"><a class="sel" id="pagination' + z + '">' + z + '</a></li>');
				} else {
					pagination.push('<li><a id="pagination' + z + '" onclick="$.fn.pageSelected(' + z + ');">' + z + '</a></li>');
				}
			}
		} else {
			// Check if we are on page 3 or less
			if (data.page <= 3) {
				for (var z = 1; z <= 5; z++) {
					if (data.page === z) {
						pagination.push('<li class="sel"><a class="sel" id="pagination' + z + '">' + z + '</a></li>');
					} else {
						pagination.push('<li><a id="pagination' + z + '" onclick="$.fn.pageSelected(' + z + ');">' + z + '</a></li>');
					}
				}
			} else {
				for (var z = (data.page-2); z <= (data.page+2); z++) {
					if (data.page === z) {
						pagination.push('<li class="sel"><a class="sel" id="pagination' + z + '">' + z + '</a></li>');
					} else if (z <= data.totalPages) {
						pagination.push('<li><a id="pagination' + z + '" onclick="$.fn.pageSelected(' + z + ');">' + z + '</a></li>');
					}
				}					
			}
		}
		// Now check for the last pages
		if (data.page === data.totalPages) {
			pagination.push('<li class="next"><a class="next">&gt;</a></li>');
		} else {			
			pagination.push('<li class="next"><a class="next active" onclick="$.fn.pageSelected(' + (data.page+1) + ');">&gt;</a></li>');
		}
		pagination.push('</ul></nav>');
		$('.sr_pagination').html(pagination.join('\n'));
		pagination.length = 0; // Clear the array
	}
	
	// Display the search results
	$.fn.displayResults = function(data) {
		// Check for more than one result
		if (typeof data != 'undefined' && data != null &&data.numberOfResults > 0) {
			// Setup search results numbers
			$.fn.setupResultsNumbers(data);
			// Setup search results list
			$.fn.setupResultsListing(data);
			// Setup pagination results
			$.fn.setupPagination(data);
		} else {
			// No results to show, so clear out everything
			$('.sr_numbers_showing').html('No results can be found');
			// Setup search results list
			$.fn.setupResultsListing(data);
			$('.sr_pagination').html('');
		}
		// Reshape widgets
		if (typeof $.fn.setupSearchResultsWidget === "function") { 
			$.fn.setupSearchResultsWidget();
		}
	}	
	
	// checks the current date of the content and whether or no it should have the new or changed label
	// returns true if less then the date agreed on, otherwise returns false
	$.fn.isContentNewOrChanged = function(currentDate, tagData){
		//CURRENTLY SET TO 3 DAYS; CHANGE HERE TO CHANGE THE TIME
		var newOrChangedTime = 60*60*24*7; // time in seconds (seconds*minutes*hours*days)
		
		// Returns false if the service doesn't return a lastModifiedDate (e.g. the bookmark search)
		if(currentDate == ""){
			return false;
		}
		
		// converts both times to epoch time
		var a=currentDate.split("T");
		var s=a[1].split("+");
		var d=a[0].split("-");
		var t=s[0].split(":");
		var date = new Date(d[0],(d[1]-1),d[2],t[0],t[1],t[2]).getTime()/1000.0;
		var currentDate = new Date().getTime()/1000.0;
		
		// calculates the difference between both dates
		var dateDifference = currentDate - date;
		
		//check for the new or changed tag when adding the icon to the knowledge piece
		
		//create a flag
		var tagsFlag = false;
		
		for(var i = 0; i < tagData.length; i ++){
			if (tagData[i].systemTagName == "newchange_neworchanged"){
				tagsFlag = true;
			}
		}
		
		if (dateDifference < newOrChangedTime && tagsFlag) {
			return true;
		} else {
			return false;
		}
	}

	$.fn.launchPolicySearch = function(id, title) {
		 var iconId = 'policy_' + id;
		 var searchId = 'policy_search_' + id;
		 var searchCode = '';
		 
		//The next two statements remove the html formatting from the string
		var tempHTML = $.parseHTML(title);
		var strTitle = $(tempHTML).text();
		
		//Remove any whitespaces
		strTitle = strTitle.trim();
		
		//Code is all the text up to the first space character
		searchCode = strTitle.substr(0, strTitle.indexOf(' '));
		log('searchCode: ' + searchCode);
			
		console.log(iconId);
		if ($('#'+iconId).hasClass('sr_lr_icon_content_toggle_expand')){
			//need to expand and show the policy search box
			console.log('Expend policy search');
			$('#'+iconId).removeClass('sr_lr_icon_content_toggle_expand');
			$('#'+iconId).addClass('sr_lr_icon_content_toggle_collapse');
			
			$('#'+searchId).removeClass('sr_lr_policy_search_off');
			$('#'+searchId).addClass('sr_lr_policy_search_on');
			
			$.fn.policySearch(searchCode, id);
			
		} else {
			console.log('Collapse policy search');
			$('#'+iconId).removeClass('sr_lr_icon_content_toggle_collapse');
			$('#'+iconId).addClass('sr_lr_icon_content_toggle_expand');
			
			$('#'+searchId).removeClass('sr_lr_policy_search_on');
			$('#'+searchId).addClass('sr_lr_policy_search_off');
		}
		

	}
	
	$.fn.getAlphaNumeric = function(data){
		 var code, i, len;
		 var result = '';

		  for (i = 0, len = data.length; i < len; i++) {
		    code = data.charCodeAt(i);
		    if ((code > 47 && code < 58) || // numeric (0-9)
		        (code > 64 && code < 91) || // upper alpha (A-Z)
		        (code > 96 && code < 123)) { // lower alpha (a-z)
		      result += data.charAt(i);
		    }
		  }
		  
		  return result;
	}
	
	$.fn.policySearch = function(search_text, id){
		
		var resultsHTML = [];
		
    	if (sortBy == undefined) {
    		sortBy = "";
    	}    	
    	
		var fTags = kbaseTag + "," + policiesTopicTag;
		
		//$.fn.search(search_text, 1, maxSizeOfPolicyResults, fTags, contentType, sortBy, publishedid, function(data) {
		//remove contentType constraint
		$.fn.search(search_text, 1, maxSizeOfPolicyResults, fTags, '', sortBy, publishedid, function(data) {
			log('Policy Search Below:');
			log(data);
			$.fn.setPolicySearchResults(data, resultsHTML, id);
			//log('resultsHTML: ' + resultsHTML);
    	});
	}
	
	// Setup search results numbers
	$.fn.setupPolicyResultsNumbers = function(data, id) {
		
		//declare these variables locally to deal with services that dont return them
		var page;
		var size;
		
		//runs checks to give default values to page and size
		if(data.page == 'undefined' || data.page == null){
			page = 1;
		} else {
			page = data.page;
		}
		if(data.size == 'undefined' || data.size == null){
			size = 20;
		} else {
			size = data.size;
		}
		
		data.totalPages = Math.ceil(data.numberOfResults / data.size);
		// sets values for the showing numbers
		var oneOf = ((page-1) * size);
		var twoOf = oneOf + size;
		// Check if we are at the end of the pagination
		if ((data.numberOfResults < size) || (data.numberOfResults < twoOf)) {
			var twoOf = data.numberOfResults;				
		}
		
		if (data.numberOfResults == 0){
			$('#policies_header_'+id).html('Policies - No results were found');
			//$('#policy_search_results_'+id).addClass('policies_search_box_off');
		} else {
			if(data.numberOfResults > maxSizeOfPolicyResults){
				$('#policies_header_'+id).html('Policies - Showing the first ' + maxSizeOfPolicyResults + ' relevant  results');
			} else {
				if (data.numberOfResults == 1){
					$('#policies_header_'+id).html('Policies - Found ' + data.numberOfResults + ' Result');
				} else {
					$('#policies_header_'+id).html('Policies - Found ' + data.numberOfResults + ' Results');
				}
			}
		}
	}
	
	$.fn.setPolicySearchResults = function(data, results, id){
	
		var externalSearchId = data.externalSearchId;
		log('externalSearchId=' + externalSearchId);
		
		$.fn.setupPolicyResultsNumbers(data,id);
		
		// See if there is regular results
		if (typeof data.knowledgeGroupUnits != 'undefined' && data.knowledgeGroupUnits != null && data.knowledgeGroupUnits.length > 0) {
			// Loop through the results
			for (var i=0;(data.knowledgeGroupUnits != null) && (i < data.knowledgeGroupUnits.length); i++) {
				if (data.knowledgeGroupUnits[i].knowledgeUnits != null && data.knowledgeGroupUnits[i].knowledgeUnits.length > 1) {
					results = $.fn.setupSlicedContent(data.knowledgeGroupUnits[i], results, false, externalSearchId);
				} else {
					// Do we have spidered content to setup?
					if (data.knowledgeGroupUnits[i].contentType === 'Spidered') {
						results = $.fn.setupExternalContent(data.knowledgeGroupUnits[i], results, false, externalSearchId);
					} else {
						// "regular" content
						results = $.fn.setupResultsLinks(data.knowledgeGroupUnits[i], results, false);
					}
				}
			}
			$('#policy_search_results_'+id).html(results.join('\n'));
		}
		
		log('results: ' + results);
		results.length = 0; // Clear the array
		
	}
	
	// Setup the inter-widget communication
	$.widget("ui.ajaxStatus", {
	    options: {
	    },
	    _create : function() {
	        var self = this;
	        self.element.addClass('dpui-widget');
            self.element.bind('dpui:startWidget', function(e) {
                log('startWidget - code_search_result');
                // register the widget with other widgets
                $('.dpui-widget').trigger('dpui:registerCodeSearchResults');
            });
	        self.element.bind('dpui:registerSearch', function(e) {
	            log("registerSearch");
	        });
	        self.element.bind('dpui:registerContentView', function(e) {
	            log('registerContentView');
	        });
	        self.element.bind('dpui:showManageButton', function(e) {
	            log('showManageButton');
	            $('#sr-hns-manage').removeClass('sr_hns_right_off');
	        });
	        self.element.bind('dpui:hideManageButton', function(e) {
	            log('hideManageButton');
	            $('#sr-hns-manage').addClass('sr_hns_right_off');
	        });
	        self.element.bind('dpui:hideCodeSearch', function(e) {
	            log('hideCodeSearch');
	            $('#code-search-area').addClass('code_area_off');	            
	        });
	        self.element.bind('dpui:showCodeSearch', function(e) {
	            log('showCodeSearch');
	            $('#code-search-area').removeClass('code_area_off');
	            document.title = defaultTitle;
	        });
	        self.element.bind("dpui:resultData", function(e, data) {
	        	log("Received dpui:resultData event data below:");
	        	log(data);
            	// check for regular search
            	if (typeof data != 'undefined' && 
            			data != null && 
            			typeof data.label != 'undefined' && 
            			data.label != '' && 
            			data.label === 'Search') {
            		$('#sr-header-non-search').removeClass('sr_header_non_search_on');
            		$('#sr-header-non-search').addClass('sr_header_non_search_off');
            		$('#sr-header-search').removeClass('sr_header_search_off');
            		$('#sr-header-search').addClass('sr_header_search_on');
            	} else {
            		$('#sr-header-non-search').removeClass('sr_header_non_search_off');
            		$('#sr-header-non-search').addClass('sr_header_non_search_on');
            		$('#sr-header-search').removeClass('sr_header_search_on');
            		$('#sr-header-search').addClass('sr_header_search_off');
            		$('.sr_label').html(data.label);
            	}
            	pageSelected = data.data.page;
            	searchFeedbackURL = data.data.searchFeedbackURL;
				
            	
            	//make sure the base query is always there
            	if (!data.query == ""){
            		query = base_query + " " +  data.query;
            	}
            	

		    	// put the scroll bar back to the top
            	$('#sr-listing').scrollTop(0);

            	// Display the results
            	$.fn.displayResults(data.data);
            });
	    },
	    destroy: function(){
	        $.widget.prototype.destroy.apply(this, arguments);
	    },
	});

	// Send out a "ping" to other widgets
	$.ui.ajaxStatus({}, $("<div></div>").appendTo("body"));
	$('.dpui-widget').trigger("dpui:startWidget");
});