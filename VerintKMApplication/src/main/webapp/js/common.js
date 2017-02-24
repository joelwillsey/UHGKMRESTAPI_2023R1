// Log function; IE9 does not support console.log
log = function(message) {
	if (typeof console != 'undefined') {
		console.log(message);
	}
}

// Error message popup OK button
$.fn.errorButtonFunc = function() {
	if ($('#background-popup-error').hasClass('background_popup_error_on')) {
		$('#background-popup-error').removeClass('background_popup_error_on');
	} else {
		$('#background').removeClass('background_on');
	}
	$('#error-message').removeClass('error_message_on');

	// If the user is not authenticated make them login
	if (!authenticated) {
		window.document.location = "login.html";
	}
}

// Show the spinner
$.fn.setupSpinner = function() {
	log('setupSpinner');
	$('#background').addClass('background_on');
	$('#overlay-back').addClass('overlay_back_on');
//	if (typeof document.getElementById("overlay-back") != 'undefined' && document.getElementById("overlay-back") != null) {
//		document.getElementById("overlay-back").style.display = "block";
//	}
//	if (typeof document.getElementById("background") != 'undefined' && document.getElementById("background") != null) {
//		document.getElementById("background").style.display = "block";
//	}
}

// Disable spinner
$.fn.disableSpinner = function() {
	log('disableSpinner');
	$('#background').removeClass('background_on');
	$('#overlay-back').removeClass('overlay_back_on');
//	if (typeof document.getElementById("overlay-back") != 'undefined' && document.getElementById("overlay-back") != null) {
//		document.getElementById("overlay-back").style.display = "none";
//	}
//	if (typeof document.getElementById("background") != 'undefined' && document.getElementById("background") != null) {
//		document.getElementById("background").style.display = "none";
//	}
}

// Handle the error(s) in a uniform way
$.fn.handleError = function(jqXHR, textStatus, errorThrown) {
	log('HandleError()');
	if (typeof jqXHR != 'undefined' && typeof jqXHR.status != 'undefined'
		&& jqXHR.status != 401 && jqXHR.status != 403) {
		// Let the status code function handle "normal" errors
		if (typeof jqXHR != 'undefined') {
			if (typeof jqXHR.responseJSON != 'undefined'
					&& typeof jqXHR.responseJSON.message != 'undefined') {
				log(jqXHR.responseJSON.message);
				$('#background').addClass('background_on');
				$('#error-body').html('Error: ' + jqXHR.responseJSON.message);
				$('#error-message').addClass('error_message_on');
			} else if (typeof jqXHR.textStatus != 'undefined') {
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
}

//Handle the error(s) in a uniform way for now service call errors
$.fn.handleErrorText = function(textStatus) {
	log('HandleErrorText()');
	if (typeof textStatus != 'undefined' && textStatus != null) {
			$('#background').addClass('background_on');
			$('#error-body').html('Error: ' + textStatus);
			$('#error-message').addClass('error_message_on');
	}
}

// Service call function
$.fn.serviceCall = function(type, data, url, timeout, successCallback) {
	// Call the search service
	$.ajax({
		type : type,
		contentType : 'application/json',
		data : data,
		url : url,
		dataType : 'json',
		timeout : timeout,
		beforeSend : function(jqXHR, settings) {
			log('beforeSend');
			$.fn.setupHeader(jqXHR);
			$.fn.setupSpinner();
		},
		success : function(data, textStatus, jqXHR) {
			// Send response headers
			$.fn.interrogateResponse(jqXHR.getAllResponseHeaders());
			successCallback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$.fn.disableSpinner();
			$.fn.handleError(jqXHR, textStatus, errorThrown);
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
}

//Service call function
$.fn.serviceCallAsyncFalse = function(type, data, url, timeout, successCallback) {
	$.fn.setupSpinner();
	// enable this when posting to server
    setTimeout(function () { 
    	// Call the search service
    	$.ajax({
    		async: false,
    		type : type,
    		contentType : 'application/json',
    		data : data,
    		url : url,
    		dataType : 'json',
    		timeout : timeout,
    		beforeSend : function(jqXHR, settings) {
    			log('beforeSend');
    			$.fn.setupHeader(jqXHR);
    		},
    		success : function(data, textStatus, jqXHR) {
    			// Send response headers
    			$.fn.interrogateResponse(jqXHR.getAllResponseHeaders());
    			successCallback(data);
    			$.fn.disableSpinner();
    		},
    	    complete: function () {
    	    	$.fn.disableSpinner();
    	    },    
    		error : function(jqXHR, textStatus, errorThrown) {
    			$.fn.handleError(jqXHR, textStatus, errorThrown);
    			$.fn.disableSpinner();
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
    	    log('Calling disableSpinner');
    	    $.fn.disableSpinner();
    	}).responseJSON;
    }, 500);
}

//Service call function with no spinner
$.fn.serviceCallNoSpin = function(type, data, url, timeout, successCallback) {
	// Call the search service
	$.ajax({
		type : type,
		contentType : 'application/json',
		data : data,
		url : url,
		dataType : 'json',
		timeout : timeout,
		beforeSend : function(jqXHR, settings) {
			$.fn.setupHeader(jqXHR);
		},
		success : function(data, textStatus, jqXHR) {
			// Send response headers
			$.fn.interrogateResponse(jqXHR.getAllResponseHeaders());
			successCallback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$.fn.disableSpinner();
			$.fn.handleError(jqXHR, textStatus, errorThrown);
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
	}).responseJSON;
}
// Dynamically create a class
$.fn.createClass = function(name, rules) {
	var style = document.createElement('style');
	style.type = 'text/css';
	document.getElementsByTagName('head')[0].appendChild(style);
	if (!(style.sheet || {}).insertRule) {
		(style.styleSheet || style.sheet).addRule(name, rules);
	} else {
		style.sheet.insertRule(name + "{" + rules + "}", 0);
	}
}

// Dynamically delete a class
$.fn.deleteClass = function(name) {
	var hd = $('head').children();
	hd.each(function(index) {
				var ele = $(this)[0];
				if (typeof ele != 'undefined' && ele != null) {
					var sheet = $(this)[0].sheet;
					try {
						if (typeof sheet != 'undefined' && sheet != null
								&& typeof sheet.cssRules != 'undefined'
								&& sheet.cssRules != null) {
							if (typeof sheet.cssRules[0].selectorText != 'undefined'
									&& sheet.cssRules[0].selectorText != null) {
								var cssEle = sheet.cssRules[0].selectorText;
								if (typeof cssEle != 'undefined'
										&& cssEle != null && cssEle != ''
										&& cssEle === name) {
									// Fix for IE 9/Opera
									var theThis = $(this)[0];
									$(theThis).remove();
								}
							}
						}
					} catch (e) {
						try {
							if (typeof sheet != 'undefined' && sheet != null
									&& typeof sheet.cssRules != 'undefined'
									&& sheet.cssRules != null) {
								if (typeof sheet.cssRules[0].selectorText != 'undefined'
										&& sheet.cssRules[0].selectorText != null) {
									var cssEle = sheet.cssRules[0].selectorText;
									if (typeof cssEle != 'undefined'
											&& cssEle != null && cssEle != ''
											&& cssEle === name) {
										// Fix for IE 9/Opera
										var theThis = $(this)[0];
										$(theThis).remove();
									}
								}
							}
						} catch (e) {
							try {
								if (typeof sheet != 'undefined'
										&& sheet != null
										&& typeof sheet.cssRules != 'undefined'
										&& sheet.cssRules != null) {
									if (typeof sheet.cssRules[0].selectorText != 'undefined'
											&& sheet.cssRules[0].selectorText != null) {
										var cssEle = sheet.cssRules[0].selectorText;
										if (typeof cssEle != 'undefined'
												&& cssEle != null
												&& cssEle != ''
												&& cssEle === name) {
											// Fix for IE 9/Opera
											var theThis = $(this)[0];
											$(theThis).remove();
										}
									}
								}
							} catch (e) {
								alert(e);
							}
						}
					}
				}
			});
}

// Get all the cookies
$.fn.getCookies = function() {
	var pairs = document.cookie.split(";");
	var cookies = {};
	for (var i = 0; i < pairs.length; i++) {
		var pair = pairs[i].split("=");
		cookies[pair[0]] = unescape(pair[1]);
	}
	return cookies;
}

// Get an individual cookie
$.fn.getCookie = function(c_name) {
	var i, x, y, ARRcookies = document.cookie.split(";");
	for (i = 0; i < ARRcookies.length; i++) {
		x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
		y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
		x = x.replace(/^\s+|\s+$/g, "");
		if (x == c_name) {
			return unescape(y);
		}
	}
}

// Check for IE version
$.fn.isIE = function() {
	var myNav = navigator.userAgent.toLowerCase();
	return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
}

// Get parameter by name
$.fn.getParameterByName = function(name) {
	var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
	var retValue = match && decodeURIComponent(match[1].replace(/\+/g, ' '));
	if (retValue === 'undefined' || retValue === null || retValue === 'null') {
		retValue = '';
	}
	return retValue;
}

// Get all of the parameters in a string
$.fn.getAllParametersString = function() {
	return window.location.search.substr(1);
}

// Get all of the parameters
$.fn.getAllParameters = function() {
    var prmstr = window.location.search.substr(1);
    return prmstr != null && prmstr != "" ? transformToAssocArray(prmstr) : {};
}

// Get parameters into an array
function transformToAssocArray(prmstr) {
	var params = {};
	var prmarr = prmstr.split("&");
	for ( var i = 0; i < prmarr.length; i++) {
		var tmparr = prmarr[i].split("=");
		params[tmparr[0]] = tmparr[1];
	}
	return params;
}