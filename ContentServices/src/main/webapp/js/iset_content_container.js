$(document).ready(function() {
	// Content resize
	window.resizeTo(512,440);
	
	//Move window to top right corner
	var ow =  screen.width - window.outerWidth;	
	window.moveTo(ow,0);
	
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
			$.get(newContextPath + 'iset_content.html', function(data) {
				$('#content-widget').html(data);
			});
		} else {
			$.get(contentServiceName + 'iset_content.html', function(data) {
				$('#content-widget').html(data);
			});
		}
	} else {

		$.get(contentServiceName + 'iset_content.html', function(data) {
			$('#content-widget').html(data);
		});
	
	}
	
});

/*
** Copy to comment content.
*/
$.fn.copyToVariable = function() {
	var autoDocText = (document.all) ? document.selection.createRange().text : document.getSelection();
	var contentTitle = $(".content_header_left_title").text();
	var isetDomain = 'uhc.com';
	var currentDomain = document.domain;
	
	log('CopyToComments current domain is ' + currentDomain + ' setting domain to ' + isetDomain);
	//Set domain to main domain, current domain is probably the sub domain
	try {
		document.domain = isetDomain;
		
		if(autoDocText == ""){
			alert("Please select text to copy to Comment.");
		}else{
			if(window.opener){
				var isetResponse = window.opener.addKMComments("[" + contentTitle + "] - " + autoDocText);			
				
				if(isetResponse!="ok"){
					alert(isetResponse);
				}else{
					window.opener.focus();
				}
			}else{
				alert("Unable to communicate with ISET.");
			}
			autoDocText="";
		}
		//Set domain back to sub domain, this causing issue
		//document.domain = currentDomain;
		
	} catch (ex) {
		log(ex);
		$.fn.handleErrorText('Copy to comments exception, ' + ex);
	}
}