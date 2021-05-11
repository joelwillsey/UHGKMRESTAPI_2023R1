$(document).ready(function() {
	var params = $.fn.getAllParametersString();

	// Catch enter key; call the search service
	/*$(document).keydown( function(event) {
		if (event.which === 13) {
			var username = $('#username').val();
			var password= $('#password').val();

			// Call the login service
			$.fn.loginService(username, password);
		}
	});*/
	
	$.fn.checkEnter = function(e) {
		if (e.which === 13) {
			var username = $('#username').val();
			var password= $('#password').val();

			// Call the login service
			$.fn.loginService(username, password);

		}
	}

	// Login Service
	$.fn.loginService = function(username, password) {
		// Check for valid username/password
		if (username === null || username === '' ||
			password === null || password === '') {
			$('#show-alert').addClass('alert_on');
			return;
		}
		
		
		
			
		// Call the service
		//var dataPackage = '{"username":"' + username + '", "password":"' + password + '"}';
		//$.fn.serviceCall('POST', dataPackage, 'km/login', LOGIN_SERVICE_TIMEOUT, function(data) {
		var dataPackage = '{"username":"' + username + '", "password":"' + password + '", "firstName":"", "lastName":"", "vemGroups":""}';
		$.fn.serviceCall('POST', dataPackage, 'km/login_v2', LOGIN_SERVICE_TIMEOUT, function(data) {
			// Check for a valid result code
			if (typeof data != 'undefined' && typeof data.loginResult != 'undefined' && data.loginResult === 1) {
				var username = $('#username').val();
	            var password= $('#password').val();
	            
	            // Setup token
	            $.fn.setupToken(username, password);
	            
	            // Setup the user information
	            $.fn.setupUserInfo(data);
	            var cookieSavedUrlValue = document.cookie.replace(/(?:(?:^|.*;\s*)savedurl\s*\=\s*([^;]*).*$)|^.*$/, "$1");	          
	          	
	            //if we got this far we got a good user/pass
	            log('getAuthTokenService()');
	            //can't have parameters on the redirect URL
	            var redirectUri =  $.fn.removeUrlParameters(cookieSavedUrlValue);
	            var useCookie = true;
	    		var dataPackage = '{"username":"' + username + '", "password":"' + password + '", "redirect_uri":"' + redirectUri + '", "useCookie":"' + useCookie + '"}';		
	    		$.fn.serviceCall('POST', dataPackage, 'km/authtoken', OIDC_TOKEN_TIMEOUT, function(data) {    		
	    			log('Get authTokenResponse for user: ' + username);
	    		    if (typeof data != 'undefined' && data != null && data != '') {
	    		    	//If you got here it worked :)
	    		    	if (data.id_token == undefined || data.id_token == ''){
	    		    		log('Unable to retrieve Verint OIDC token');
	    		    		$('#show-alert-token').addClass('alert_on');
	    		    	} else {
	    			    	log('authTokenResponse access_token: ' + data.access_token);
	    					log('authTokenResponse id_token: ' + data.id_token);
	    					log('authTokenResponse token_type: ' + data.token_type);
	    					log('authTokenResponse expires_in: ' + data.expires_in);
	    					
	    					// Check to see where to redirect
	    					if (typeof cookieSavedUrlValue != 'undefined' && cookieSavedUrlValue != '') {
	    		            	cookieSavedUrlValue = cookieSavedUrlValue.replace(/%3F/g, "?");
	    		 	            cookieSavedUrlValue = cookieSavedUrlValue.replace(/%3D/g, "=");
	    		            	window.document.location = cookieSavedUrlValue;
	    		            } else {
	    		            	if (typeof postLogin != 'undefined' && postLogin != '') {
	    	     		           if (typeof params != 'undefined' && params != '') {
	    	     		            	window.document.location = postLogin + '?' + params;
	    	     		            } else {
	    	     		            	window.document.location = postLogin;
	    	     		            }
	    		     	         }
	    		 		        window.document.location = postLogin;
	    		 		    }    
	    		    	} 
	    		    } else {
	    		    	$('#show-alert').addClass('alert_on');
	    		    }
	    		});	
	    	
			} else {
				if (typeof data != 'undefined' && typeof data.loginResult != 'undefined' && data.loginResult === 16){
					$('#show-alert-misconfigured').addClass('alert_on');
				} else {
					$('#show-alert').addClass('alert_on');
				}
			}
		});
	}

	/**
	 *  Get Verint OIDC Token
	 */


	$.fn.getAuthTokenService = function(userName, password, redirectUri, useCookie) {
		log('getAuthTokenService()');
		var dataPackage = '{"username":"' + userName + '", "password":"' + password + '", "redirect_uri":"' + redirectUri + '", "useCookie":"' + useCookie + '"}';		
		$.fn.serviceCall('POST', dataPackage, 'km/authtoken', OIDC_TOKEN_TIMEOUT, function(data) {    		
			log('Get authTokenResponse for user: ' + userName);
		    if (typeof data != 'undefined' && data != null && data != '') {
		    	//If you got here it worked :)
		    	if (data.id_token == undefined || data.id_token == ''){
		    		log('Bad login credentials supplied');
		    	} else {
			    	log('authTokenResponse access_token: ' + data.access_token);
					log('authTokenResponse id_token: ' + data.id_token);
					log('authTokenResponse token_type: ' + data.token_type);
					log('authTokenResponse expires_in: ' + data.expires_in);
		    	}
		    }
		});	
	}
	
	$.fn.removeUrlParameters = function(sourceURL){
		var rtn = sourceURL.split("?")[0];
		return rtn;
	}
	
	// Setup login button
	$('#loginButton').click(function() {
		var username = $('#username').val();
		var password= $('#password').val();

		$.fn.loginService(username, password);

	});	
});