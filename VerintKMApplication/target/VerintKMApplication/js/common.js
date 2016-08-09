	var basicAuth;
	var notAuthenticated = false;

	// Console.log wrapper
	log = function(message) {
		if (window.console) {
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
		if (notAuthenticated) {
			window.location = "login.html";
		}
	}
	
	// Show the spinner
	$.fn.setupSpinner = function() {
		$('#background').addClass('background_on');
		$('#overlay-back').addClass('overlay_back_on');
	}
	// Disable spinner
	$.fn.disableSpinner = function() {
		$('#background').removeClass('background_on');
		$('#overlay-back').removeClass('overlay_back_on');
	}
	
	// Setup the header to send token and authorization info
	// this is used because we don't want hackers stealing our user "session"
	$.fn.setupHeader = function (jqXHR) {
		var token = Math.random().toString(36).substr(2) + Math.random().toString(36).substr(2);
		document.cookie = 'CSRF-TOKEN=' + token + '; path=/';
	    jqXHR.setRequestHeader('X-CSRF-TOKEN', token);
	    jqXHR.setRequestHeader('x-km-authorization', basicAuth);
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
    			$('#error-body').html('Error: User is not authenticated');
            	$('#error-message').addClass('error_message_on');
            	notAuthenticated = true;
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
            	$.fn.disableSpinner();
            	$.fn.handleError(jqXHR, textStatus, errorThrown);
            },
	        statusCode: {
	        	// Auth error code
	            401: function(jqXHR, textStatus, errorThrown) {
	            	$.fn.disableSpinner();
	            	$.fn.handleError(jqXHR, textStatus, errorThrown);
	            },
	            // Not found error code
            	404: function(jqXHR, textStatus, errorThrown) {
                	$.fn.disableSpinner();
                	$.fn.handleError(jqXHR, textStatus, errorThrown);
	            }
	        },
	    }).then(function(data) {
	    	$.fn.disableSpinner();
	    }).responseJSON;		
	}

	// Dynamically create a class
	$.fn.createClass = function (name, rules) {
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
	$.fn.deleteClass = function (name) {
	    var hd = $('head').children();
	    hd.each(
			function(index) {
				var ele = $(this)[0];
				if (typeof ele != 'undefined' && ele != null) {
					var sheet = $(this)[0].sheet;
				    try {
						if (typeof sheet != 'undefined' && sheet != null && typeof sheet.cssRules != 'undefined' && sheet.cssRules != null) {
							if (typeof sheet.cssRules[0].selectorText != 'undefined' && sheet.cssRules[0].selectorText != null) {
								var cssEle = sheet.cssRules[0].selectorText;
								if (typeof cssEle != 'undefined' && cssEle != null && cssEle != '' && cssEle === name) {
									// Fix for IE 9/Opera
									var theThis = $(this)[0];
									$(theThis).remove();
								}
							}
						}
				    } catch (e) {
				        try {
							if (typeof sheet != 'undefined' && sheet != null && typeof sheet.cssRules != 'undefined' && sheet.cssRules != null) {
								if (typeof sheet.cssRules[0].selectorText != 'undefined' && sheet.cssRules[0].selectorText != null) {
									var cssEle = sheet.cssRules[0].selectorText;
									if (typeof cssEle != 'undefined' && cssEle != null && cssEle != '' && cssEle === name) {
										// Fix for IE 9/Opera
										var theThis = $(this)[0];
										$(theThis).remove();
									}
								}
							}
				        } catch (e) {
				            try {
								if (typeof sheet != 'undefined' && sheet != null && typeof sheet.cssRules != 'undefined' && sheet.cssRules != null) {
									if (typeof sheet.cssRules[0].selectorText != 'undefined' && sheet.cssRules[0].selectorText != null) {
										var cssEle = sheet.cssRules[0].selectorText;
										if (typeof cssEle != 'undefined' && cssEle != null && cssEle != '' && cssEle === name) {
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
			}
		);
	}

	// Get all the cookies
	$.fn.getCookies = function() {
		var pairs = document.cookie.split(";");
		var cookies = {};
		for (var i=0; i<pairs.length; i++){
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

	// Check if already authenticated user
	var myCookies = $.fn.getCookies();
	if (typeof myCookies != 'undefined' && myCookies != '') {
    	basicAuth = $.fn.getCookie('BasicAuth');
		if (typeof basicAuth === 'undefined' || basicAuth === '') {
			var cId = $.fn.getParameterByName('id');
			if (typeof cId != 'undefined' && cId != null && cId != 'null' && cId != '') {
				window.document.location = "login.html?id=" + cId;	
			} else {
				window.document.location = "login.html";
			}
		}
	} else {
		// show login
		var cId = $.fn.getParameterByName('id');
		if (typeof cId != 'undefined' && cId != null && cId != 'null' && cId != '') {
			window.document.location = "login.html?id=" + cId;	
		} else {
			window.document.location = "login.html";
		}
	}