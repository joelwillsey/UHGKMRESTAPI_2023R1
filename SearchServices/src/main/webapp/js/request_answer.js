	var keyword;
	var selectedFilter;
	
	$.fn.throwError = function(data) {
		$('#error-body').html(data);
		$('#background-popup-error').addClass('background_popup_error_on');
		$('#error-message').addClass('error_message_on');
	}

	// Submit button
	$.fn.requestAnswerSubmit = function() {
		var searchDate = new Date();
		var jsonDate = JSON.stringify(searchDate);
		var expectation = $('#request-answer-expectation').val();
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
		$('#request-answer-query').val(keyword);
	}

	// Post suggested content
	$.fn.suggestContent = function(postObject, callBack) {
		console.log(postObject);
		$.fn.serviceCall('POST', postObject, searchServiceName + 'km/suggestcontent/', SEARCH_SERVICE_TIMEOUT, callBack);
	}