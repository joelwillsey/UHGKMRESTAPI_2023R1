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
	           // if (typeof postLogin != 'undefined' && postLogin != '') {
		       //     if (typeof params != 'undefined' && params != '') {
		       //     	window.document.location = postLogin + '?' + params;
		       //     } else {
		       //     	window.document.location = postLogin;
		       //     }
	           // }
			} else {
				if (typeof data != 'undefined' && typeof data.loginResult != 'undefined' && data.loginResult === 16){
					$('#show-alert-misconfigured').addClass('alert_on');
				} else {
					$('#show-alert').addClass('alert_on');
				}
			}
		});
	}

	// Setup login button
	$('#loginButton').click(function() {
		var username = $('#username').val();
		var password= $('#password').val();
		
		$.fn.loginService(username, password);
	});	
});