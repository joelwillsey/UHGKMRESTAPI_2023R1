var authToken;
var authenticated = false;

// Setup the header to send token and authorization info
// this is used because we don't want hackers stealing our user "session"
$.fn.setupHeader = function(jqXHR) {
	var token = Math.random().toString(36).substr(2) + Math.random().toString(36).substr(2);
	document.cookie = 'CSRF-TOKEN=' + token + '; path=/';
	jqXHR.setRequestHeader('X-CSRF-TOKEN', token);
}

// Look at HTTP Response header
$.fn.interrogateResponse = function(headers) {
	log(headers);
}

// Handle the error specific to SAML2
$.fn.handleAuthError = function(jqXHR, textStatus, errorThrown) {
	log('handleAuthError()');
	if (typeof jqXHR != 'undefined' && typeof jqXHR.status != 'undefined'
			&& jqXHR.status === 401) {
		// Authentication error
		if (typeof jqXHR.responseJSON != 'undefined' && jqXHR.responseJSON.link != 'undefined') {
			window.location = jqXHR.responseJSON.link;
		}
		authenticated = false;
	} else 	if (typeof jqXHR != 'undefined' && typeof jqXHR.status != 'undefined'
		&& jqXHR.status === 403) {
		// Authorization error
		$('#background').addClass('background_on');
        $('#error-body').html('Error: User is not authorized to access this service');
        $('#error-message').addClass('error_message_on');
        authenticated = false;
	}
}

// Setup token based on user
$.fn.setupToken = function(username, password) {
	// Do nothing as SAML token is created on server
}

// Check if already authenticated user
$.fn.isUserAuthenticated = function() {
	// For SAML, let the service handle an unauthenticated user
	var myCookies = $.fn.getCookies();
	if (typeof myCookies != 'undefined' && myCookies != '') {
		authToken = $.fn.getCookie('AuthToken');
	} else {
	}
	authenticated = true;
	return authenticated;
}