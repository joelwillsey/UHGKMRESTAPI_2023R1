log = function(message) {
	if (typeof console != 'undefined') {
		console.log(message);
	}
}

$(document).ready(function() {
	$.fn.getParameterByName = function(name) {
	    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
	    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
	}
	var cId = $.fn.getParameterByName('id');

	// Catch enter key; call the search service
	$(document).keydown( function(event) {
		if (event.which === 13) {
			var username = $('#username').val();
			var password= $('#password').val();
			
			$.fn.loginService(username, password);
		}
	});

	// Login Service
	$.fn.loginService = function(username, password) {
		// Check for valid username/password
		if (username === null || username === '' ||
			password === null || password === '') {
			$('#show-alert').addClass('alert_on');
			return;
		}

		// Call the service
		var dataPackage = '{"username":"' + username + '", "password":"' + password + '"}';
		$.fn.serviceCall('POST', dataPackage, 'km/login', 15000, function(data) {
			// Check for a valid result code
			if (data.loginResult === 1) {
				var username = $('#username').val();
	            var password= $('#password').val();
	            var encodedCredentials = '';
        		if (window.btoa) {
        			encodedCredentials = btoa(username+':'+password);
            	} else {
            		var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}
            		encodedCredentials = Base64.encode(username+':'+password);
            	}
	            basicAuth = ' Basic ' + encodedCredentials;
	            document.cookie = 'BasicAuth=' + basicAuth + '; path=/';
	            if (typeof data.firstName != 'undefined' && data.firstName != '' &&
	            	typeof data.lastName != 'undefined' && data.lastName != '') {
	            	document.cookie = 'AgentInfo=' + data.firstName + ' ' + data.lastName + '; path=/';
	            }
	            if (typeof cId != 'undefined' && cId != null && cId != '') {
	            	window.document.location = postLogin + '?id=' + cId;
	            } else {
	            	window.document.location = postLogin;
	            }
			} else {
				$('#show-alert').addClass('alert_on');
			}
		});		
	}
	
	// Setup login button
	$('#loginButton').click(function() {
		var username = $('#username').val();
		var password= $('#password').val();
		
		$.fn.loginService(username, password);
	});

	// Error message popup OK button
	$.fn.errorButtonFunc = function() {
		if ($('#background-popup-error').hasClass('background_popup_error_on')) {
			$('#background-popup-error').removeClass('background_popup_error_on');			
		} else {
			$('#background').removeClass('background_on');
		}
		$('#error-message').removeClass('error_message_on');
	}
	
	// Show the spinner
	$.fn.setupSpinner = function() {
		$('#background').addClass('background_on');
		$('#overlay-back').addClass('overlay_back_on');
	}
	// Disable spinner
	$.fn.disableSearchSpinner = function() {
		$('#background').removeClass('background_on');
		$('#overlay-back').removeClass('overlay_back_on');
	}
	
	// Setup the header to send token and authorization info
	// this is used because we don't want hackers stealing our user "session"
	$.fn.setupHeader = function (jqXHR) {
		var token = Math.random().toString(36).substr(2) + Math.random().toString(36).substr(2);
		document.cookie = 'CSRF-TOKEN=' + token + '; path=/';
	    jqXHR.setRequestHeader('X-CSRF-TOKEN', token);
	}

	// Handle the error(s) in a uniform way
	$.fn.handleError = function(jqXHR, textStatus, errorThrown) {
    	log(jqXHR);
    	log(textStatus);
    	log(errorThrown);
    	// Let the status code function handle "normal" errors
    	if (typeof jqXHR != 'undefined') {
    		if (jqXHR.status === 401) {
    			$('#background').addClass('background_on');
    			$('#error-h1').html('Error: User is not authenticated');
            	$('#error-message').addClass('error_message_on');
    		} else if (typeof jqXHR.responseJSON != 'undefined') {
        		log(jqXHR.responseJSON.message);
        		$('#background').addClass('background_on');
                $('#error-body').html('Error: ' + jqXHR.responseJSON.message);
                $('#error-message').addClass('error_message_on');
        	} else {
        		log(jqXHR.textStatus);
        		$('#background').addClass('background_on');
        		$('#error-body').html('Error: ' + textStatus);
        		$('#error-message').addClass('error_message_on');
        	}
    	} else if (typeof textStatus != 'undefined' && textStatus != null) {
    		$('#background').addClass('background_on');
    		$('#error-h1').html('Error: ' + textStatus);
    		$('#error-message').addClass('error_message_on');
    	}
	}

	// Service call function
	$.fn.serviceCall = function(type, data, url, timeout, successCallback) {
		// Call the search service
	    $.ajax({
	        type: type,
	        contentType: 'application/json',
	        data: data,
	        url: url,
	        dataType: 'json',
	        timeout: timeout,
            beforeSend: function(jqXHR, settings) {
            	$.fn.setupHeader(jqXHR);
            	$.fn.setupSpinner();
            },
	        success: function(data) {
	        	log(data);
	        	successCallback(data);
	        },
            error: function(jqXHR, textStatus, errorThrown) {
            	$.fn.disableSearchSpinner();
            	$.fn.handleError(jqXHR, textStatus, errorThrown);
            },
	        statusCode: {
	        	// Auth error code
	            401: function(jqXHR, textStatus, errorThrown) {
	            	$.fn.disableSearchSpinner();
	            	$.fn.handleError(jqXHR, textStatus, errorThrown);
	            },
	            // Not found error code
            	404: function(jqXHR, textStatus, errorThrown) {
                	$.fn.disableSearchSpinner();
                	$.fn.handleError(jqXHR, textStatus, errorThrown);
	            }
	        },
	    }).then(function(data) {
	    	$.fn.disableSearchSpinner();
	    }).responseJSON;		
	}
});