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
	$('#background').addClass('background_on');
	$('#overlay-back').addClass('overlay_back_on');
}
// Disable spinner
$.fn.disableSpinner = function() {
	$('#background').removeClass('background_on');
	$('#overlay-back').removeClass('overlay_back_on');
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

// Handle the error(s) in a uniform way for now service call errors
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
		async: false,
		dataType : 'json',
		timeout : timeout,
		beforeSend : function(jqXHR, settings) {
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
$.fn.serviceCallText = function(type, data, url, timeout, successCallback) {
	// Call the search service
	$.ajax({
		type : type,
		contentType : 'application/json',
		data : data,
		url : url,
		async: false,
		dataType : 'text',
		timeout : timeout,
		beforeSend : function(jqXHR, settings) {
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

//Get the kbase tag from the parameters
$.fn.getParameterKbaseTag = function() {
	var tags = $.fn.getParameterByName('tags');
	if(tags != null && tags != undefined & tags != "") {
		tags = tags.split(',');
		tags = tags.filter(function(tag) {
			return tag.indexOf('kbase_') >= 0;
		});
	}
	if(tags == null || tags == undefined || tags == "") {
		tags = $('#kbase-selection').children();
		if(tags.length > 0) {
			for (var i = 0; i < tags.length; i++) {
				if(tags[i].id != null && tags[i].id != undefined && tags[i].id != "" && tags[i].id.indexOf('kbase_') >= 0) {
					tags = tags[i].id;
					break;
				}
			}
		}
	}
	if(tags == null) {
		tags = '';
	} else {
		tags = tags.toString();
	}
	return tags;
}

//Property Reader Service
$.fn.getProperty = function(property) {
	
	var query = '?property=' +property;
	var retValue = '';
		
	jQuery.ajaxSetup({
		async : false
	});
	// Call the service
	try {
		$.fn.serviceCallText('GET', '', verintKmServiceName + 'km/property/read'+ query, 15000, function(data) {
			 if (typeof data != 'undefined' && data != null && data != '') {			 
					retValue = data;					
			    }		
		});
	}
	catch(err) {
		log('$.fn.serviceCall Exception: ' +err.messagee);
		}
	jQuery.ajaxSetup({
			async : true
	});
	
	if (retValue === 'undefined' || retValue === null || retValue === 'null') {
		retValue = '';
	}
	
	log(property + ': '  + retValue); 
	
	return 	retValue;
}

//Check for web preview button
$.fn.isDraftContent = function() {
	var isDraftContent = false	
	var paramStr = $.fn.getAllParametersString();
	if (typeof paramStr != 'undefined' && paramStr != '') {
		var n = paramStr.indexOf("workflowstate=");
		if (n > -1){
			isDraftContent = true;
		}
	}
	log('isDraftContent = ' + isDraftContent + ' paramStr=' + paramStr);
	return isDraftContent;
}

//Check for dtree content
$.fn.isDTreeContent = function() {
	var isDTreeContent = false	
	var paramStr = $.fn.getAllParametersString();
	if (typeof paramStr != 'undefined' && paramStr != '') {
		var n = paramStr.indexOf("dtreeid=");
		if (n > -1){
			isDTreeContent = true;
		}
	}
	log('isDTreeContent = ' + isDTreeContent + ' paramStr=' + paramStr);
	return isDTreeContent;
} 