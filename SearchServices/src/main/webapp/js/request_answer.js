	var keyword;
	var selectedFilter;

	// Submit button
	$.fn.requestAnswerSubmit = function() {
		var searchDate = new Date();
		var jsonDate = JSON.stringify(searchDate);
		var expectation = $('#request-answer-expectation').val();
		if (expectation === '') {
			$('#error-body').html('Please enter a valid expectation');
			$('#background-popup-error').addClass('background_popup_error_on');
			$('#error-message').addClass('error_message_on');
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
		$('#request-answer-query').val(keyword);
	}

	// Post suggested content
	$.fn.suggestContent = function(postObject, callBack) {
		$.fn.serviceCall('POST', postObject, searchServiceName + 'km/suggestcontent/', 15000, callBack);
	}