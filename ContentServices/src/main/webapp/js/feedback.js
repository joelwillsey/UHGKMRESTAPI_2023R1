var contentId;
var rating;
var viewID;

$(document).ready(function() {

	// Submit button
	$.fn.feedbackSubmit = function() {
		var feedbackCode = $('#feedback-code-list').val();
		var feedbackComment = $('#feedback-comment').val();
		var feedbackCommentEscaped = feedbackComment.replace(/\n/g, '\\n');
		if (feedbackCode === 'Please select') {
			$('#error-body').html('Please select a feedback for this content.');
			$('#background-popup-error').addClass('background_popup_error_on');
			$('#error-message').addClass('error_message_on');
		} else {
			if ($('#feedback-notify-me').is(":checked")) {
				var notification = true;
				var name = $('#feedback-name-input').val();
				var email = $('#feedback-email-input').val();
				if ((typeof name != 'undefined' && name != null && name != '') &&
					(typeof email != 'undefined' && email != null && email != '' && email.indexOf('@') != -1))
				{
					var postObject = [];
						postObject.push('{"contentId":"' + contentId + '"'); 
						postObject.push(',"comment":"' + feedbackCommentEscaped + '"');
						postObject.push(',"notification":"' + notification + '"');
						postObject.push(',"locale":"' + 'en-US' + '"');
						postObject.push(',"feedbackCodeID":' + feedbackCode);
						postObject.push(',"rating":' + rating);
						postObject.push(',"viewID":"' + viewID + '"');
						postObject.push(',"email":"' + email + '"');
						postObject.push(',"name":"' + name + '"}');
						$('#feedback-comment').val('');
						$('#feedback_name_input').val('');
						$('#feedback-email-input').val('');
						$.fn.submitFeedback(postObject.join('\n'), $.fn.feedbackCallback);
						postObject.length = 0; // Clear the array					
				} else {
					// TODO
				}
			} else {
				var notification = false;
				var postObject = [];
					postObject.push('{"contentId":"' + contentId + '"'); 
					postObject.push(',"comment":"' + feedbackCommentEscaped + '"');
					postObject.push(',"notification":"' + notification + '"');
					postObject.push(',"locale":"' + 'en-US' + '"');
					postObject.push(',"feedbackCodeID":' + feedbackCode);
					postObject.push(',"rating":' + rating);
					postObject.push(',"viewID":"' + viewID + '"');
					postObject.push(',"email":"' + '' + '"');
					postObject.push(',"name":"' + '' + '"}');
					$('#feedback-comment').val('');
					$('#feedback_name_input').val('');
					$('#feedback-email-input').val('');
				$.fn.submitFeedback(postObject.join('\n'), $.fn.feedbackCallback);
				postObject.length = 0; // Clear the array
			}
		}
	}

	// Cancel button
	$.fn.feedbackCancel = function() {
		$('#feedback-comment').val('');
		$('#feedback_name_input').val('');
		$('#feedback-email-input').val('');
		$('#feedback-widget').html($('#popup').html());
		$('#background').removeClass('background_on');
		$('#popup').removeClass('popup_on');
	}

	// Notify checkbox
	$.fn.feedbackNotifyMe = function() {
		if ($('#feedback-name-input').hasClass('feedback_name_input_off')) {
			$('#feedback-name-input').removeAttr('disabled');
			$('#feedback-email-input').removeAttr('disabled');
			$('#feedback-name-input').removeClass('feedback_name_input_off');
			$('#feedback-email-input').removeClass('feedback_email_input_off');
		} else {
			$('#feedback-name-input').attr('disabled');
			$('#feedback-email-input').attr('disabled');
			$('#feedback-name-input').addClass('feedback_name_input_off');
			$('#feedback-email-input').addClass('feedback_email_input_off');			
		}
	}

	// Initialize the data
	$.fn.initFeedbackData = function(content_id, rating_value, view_id) {
		log('content_id: ' + content_id);
		log('rating_value: ' + rating_value);
		log('view_id: ' + view_id);
		contentId = content_id;
		rating = rating_value;
		viewID = view_id;
	}

	// Feedback callback
	$.fn.feedbackCallback = function(data) {
		$('#feedback-widget').html($('#popup').html());
		$('#background').removeClass('background_on');
		$('#popup').removeClass('popup_on');
	}

	// Post submit feedback
	$.fn.submitFeedback = function(postObject, callBack) {
		$.fn.serviceCall('POST', postObject, contentServiceName + 'km/feedback/', FEEDBACK_SERVICE_TIMEOUT, callBack);
	}
});