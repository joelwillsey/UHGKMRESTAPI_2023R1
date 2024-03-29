$(document).ready(function() {
	// Load content widget
	if ($.fn.isDraftContent()){
		// Draft content might not be in the contentServiceName path because it is proxied so we need to get the proxy path to bypass Siteminder 
		var contextPath = window.location.pathname.split('/');		 
		/**When we split the pathname the first array element will be blank because it looks like this /verintkm/verintkm.html
		contextPath[0]="", contextPath[1]="verintkm", contextPath[2]="verintkm.html", **/
		var contextPath = window.location.pathname.split('/');
		
		var URL = '';
		
		if (contextPath.length > 1){
			var newContextPath = '/';
			//don't want the last element as it is the html page
			for (x=0; x < contextPath.length-1; x++){
				if(typeof contextPath[x] != 'undefined' && contextPath[x] != ''){
					newContextPath = newContextPath + contextPath[x] + "/";
				}
			}
			log("This is draft content retrieving pathname as it may be proxied. context=" + newContextPath  + "content.html");
			$.get(newContextPath + 'content.html', function(data) {
				$('#content-widget').html(data);
			});
		} else {
			$.get(contentServiceName + 'content.html', function(data) {
				$('#content-widget').html(data);
			});
		}
	} else {

		$.get(contentServiceName + 'content.html', function(data) {
			$('#content-widget').html(data);
		});

	}
});

