var authToken;
var authenticated = false;
var errorMessage;
var userName = '';

var debug = true;
var params = $.fn.getAllParametersString();

// Setup the header to send token and authorization info
$.fn.setupHeader = function(jqXHR) {
	var token = Math.random().toString(36).substr(2) + Math.random().toString(36).substr(2);

	document.cookie = 'CSRF-TOKEN=' + token + '; path=/';
	jqXHR.setRequestHeader('X-CSRF-TOKEN', token);
	jqXHR.setRequestHeader('x-km-authorization', authToken);
	jqXHR.setRequestHeader('USERNAME', userName);
}
    

// Look at HTTP Response header
$.fn.interrogateResponse = function(headers) {
	log(headers);
}


$.fn.handleAuthError = function(jqXHR, textStatus, errorThrown) {
		log('handleAuthError()');
	if (typeof jqXHR != 'undefined' && typeof jqXHR.status != 'undefined'
			&& jqXHR.status === 401) {
		// Authentication error
		$('#background').addClass('background_on');
		$('#error-body').html(
				'Error: ' + errorMessage);
		$('#error-message').addClass('error_message_on');
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
	log('setupToken()');
    var encodedCredentials = '';
	if (window.btoa) {
		encodedCredentials = btoa(username+':'+password);
	} else {
		var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}} return t;}}
		encodedCredentials = Base64.encode(username+':'+password);
	}
    authToken = ' Basic ' + encodedCredentials;
    document.cookie = 'AuthToken=' + authToken + '; path=/';
    userName = username;
}

// Setup the user information
$.fn.setupUserInfo = function(data) {
	log('setupUserInfo()');
    // Check for first/last names
    if (typeof data.firstName != 'undefined' && data.firstName != '' &&
    	typeof data.lastName != 'undefined' && data.lastName != '') {
    	document.cookie = 'AgentInfo=' + data.firstName + ' ' + data.lastName + '; path=/';
    }
}

$.fn.getUserNameFromAuthToken = function(token){
	var user_name = ''; 
	if (typeof token != 'undefined' || token != '' || token != null)
		var decodedString = $.fn.decodeToken(authToken); 
		var infoArray = decodedString.split(':');		
		if (infoArray.length == 2){
			if (typeof infoArray[0] != 'undefined' && infoArray[0] != ''){
				user_name = infoArray[0];
				log('AuthToken userName=' + user_name);
			} else {
				log('Unable to retrieve userName from AuthToken');
			}
	 } else {
		log('AuthToken in wrong format');
	 }
		return user_name;
}

//Check if already authenticated user
$.fn.isUserAuthenticated = function() {
	log('isUserAuthenticated()');
    authenticated = false;
	var myCookies = $.fn.getCookies();
	if (typeof myCookies != 'undefined' && myCookies != '') {
		authToken = $.fn.getCookie('AuthToken');
		log('AuthToken: ' + authToken);
        if (typeof authToken === 'undefined' || authToken === '' || authToken === null) 
	      authenticated = false;
	    else {
	      authenticated = true;
	      userName = $.fn.getUserNameFromAuthToken(authToken);
	    }        
	}
	
	log('isUserAuthenticated()='+authenticated);
	return authenticated;
}


// Determine if we are authenticated or not
$.fn.determineAuth = function() {
	var myCookies = $.fn.getCookies();
	if (typeof myCookies != 'undefined' && myCookies != '') {
		authToken = $.fn.getCookie('AuthToken');
		// Check for a valid authentication
		log('AuthToken: ' + authToken);
		if (typeof authToken != 'undefined' && authToken != '') {
	//		window.document.location = postLogin + '?' + $.fn.getAllParametersString();
		}
	} else {
		log('AuthToken is invalid');
	}
}

var delete_cookie = function(name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};

// Login Service
$.fn.ssoLoginService = function() {


	var basicLogin = $.fn.getParameterByName('basicLogin');
	log('basicLogin=' + basicLogin);
	if (basicLogin == 'true'){
		log('Basic Authentication Requested');
		window.document.location = "/verintkm/login.html";
		return;
	}
	
	if ($.fn.isUserAuthenticated()){
		var authTokenCookie = $.fn.getCookie('AuthToken');	
		if (typeof authTokenCookie != 'undefined' && authTokenCookie != ''){
			log('Found AuthToken: ' + authTokenCookie);
			userName = $.fn.getUserNameFromAuthToken(authToken);
		} else {
			log('Could not retrieve AuthToken');
		}
	} else {
		log('No AuthToken found redirecting to /verintkm/km/ping/login');
		window.document.location = "/verintkm/km/ping/login";
	}
	
	var agentInfoCookie = $.fn.getCookie('AgentInfo');
	if (typeof agentInfoCookie != 'undefined' && agentInfoCookie != ''){
		log('Found AgentInfo: ' + agentInfoCookie);
	}
	            
  }

//Get parameter by name
$.fn.getParameterByName = function(name) {
	var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
	var retValue = match && decodeURIComponent(match[1].replace(/\+/g, ' '));
	if (retValue === 'undefined' || retValue === null || retValue === 'null') {
		retValue = '';
	}
	return retValue;
}

//decode basic token
$.fn.decodeToken = function(token){
	log('decodeToken()');
	var tokenName = 'Basic ';
	var credIndex = token.indexOf(tokenName);
	var encodedString = '';
	var decodedCredentials = '';
	if (credIndex != -1){
		encodedString = token.substring(tokenName.length, token.length);		
		if (window.btoa) {
			decodedCredentials = atob(encodedString);		
		} else {
			var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}} return t;}}
			decodedCredentials = Base64.decode(encodedString);			
		}			
	} 	
	return decodedCredentials;
}