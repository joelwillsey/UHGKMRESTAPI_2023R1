	var keyword;
	var selectedFilter;
	
	$.fn.throwError = function(data) {
		$('#error-body').html(data);
		$('#background-popup-error').addClass('background_popup_error_on');
		$('#error-message').addClass('error_message_on');
	}

	// Submit button
	$.fn.requestAnswerSubmit = function() {
		$.fn.parseParams();
		var searchDate = new Date();
		var jsonDate = JSON.stringify(searchDate);
		var expectation = $('#request-answer-expectation').val();
		keyword = $('#request-answer-query').val();
		if(!keyword) keyword = " ";
		$('#request-answer-query').val('');
		$('#request-answer-expectation').val('');
		if (expectation === '') {
			$.fn.throwError('Fields cannot be empty.');
		} else {
			var postObject = '{"keyword":"' + keyword + '", "expectation":"' + expectation + '", "selectedFilter":"' + selectedFilter + '", "searchDate":' + jsonDate + '}';
			$.fn.suggestContent(postObject, function(data) {
				$('#request-answer-widget').html($('#popup').html());
				$('#background').removeClass('background_on');
				$('#popup').removeClass('popup_on');
			});
		}
	}
	
	$.fn.parseParams = function() {
		var tagList = $.fn.getAllParameters().tags.split(',');
		var categoryList = $.fn.getAllParameters().categories.split(',');
		if(tagList) {
			tagList = tagList.filter(function(tags) {
			    if(tags != "" && tags.indexOf('_') != -1)
			        return tags;
			});
		}
		if(categoryList) {
			categoryList = categoryList.filter(function(category) {
			    if(category != "" && category.indexOf('_') != -1)
			        return category;
			});
		}
		
		selectedFilter = "AKSC_locale::English;;";
		if(categoryList && categoryList.length > 0) {
			selectedFilter += "AKSC_contentCategory::";
			categoryList.forEach(function(category) {
			    selectedFilter += document.getElementById(category).title + ",";
			});
			selectedFilter = selectedFilter.substring(0, selectedFilter.length - 1);
			selectedFilter += ";;";
		}
		
		if(tagList && tagList.length > 0) {
			var kbaseTags = [], productTags = [], regionTags = [];
			tagList.forEach(function(tag) {
				if(tag.indexOf('kbase_') != -1) {
					kbaseTags.push(tag);
				}
				else if(tag.indexOf('product_') != -1) {
					productTags.push(tag);
				}
				else if(tag.indexOf('region_') != -1) {
					regionTags.push(tag);
				}
			});
			
			if(kbaseTags.length > 0) {
				selectedFilter += "kbase::";
				kbaseTags.forEach(function(tag) {
				    selectedFilter += document.getElementById(tag).title + ",";
				})
			}
			selectedFilter = selectedFilter.substring(0, selectedFilter.length - 1);
			selectedFilter += ";;";
			
			if(productTags.length > 0) {
				selectedFilter += "product::";
				productTags.forEach(function(tag) {
				    selectedFilter += document.getElementById(tag).title + ",";
				})
			}
			selectedFilter = selectedFilter.substring(0, selectedFilter.length - 1);
			selectedFilter += ";;";
			
			if(regionTags.length > 0) {
				selectedFilter += "region::";
				regionTags.forEach(function(tag) {
				    selectedFilter += document.getElementById(tag).title + ",";
				})
			}
			selectedFilter = selectedFilter.substring(0, selectedFilter.length - 1);
			selectedFilter += ";;";
		}
		
		selectedFilter += "status::Published;;";
	}

	// Cancel button
	$.fn.requestAnswerCancel = function() {
		$('#request-answer-query').val('');
		$('#request-answer-expectation').val('');
		$('#request-answer-widget').html($('#popup').html());
		$('#background').removeClass('background_on');
		$('#popup').removeClass('popup_on');
	}

	// Initialize the data
	$.fn.initData = function(keywordParam, selectedFilterParam) {
		log('keyword: ' + keywordParam);
		log('searchFilter: ' + selectedFilterParam);
		keyword = keywordParam;
		selectedFilter = selectedFilterParam;
		if(selectedFilter === '') {
			selectedFilter = $.fn.getAllParameters().tags;
		}
		keyword = $('#request-answer-query').val();
	}

	// Post suggested content
	$.fn.suggestContent = function(postObject, callBack) {
		console.log(postObject);
		$.fn.serviceCall('POST', postObject, searchServiceName + 'km/suggestcontent/', SEARCH_SERVICE_TIMEOUT, callBack);
	}