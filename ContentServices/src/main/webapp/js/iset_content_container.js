$(document).ready(function() {
	// Load content widget
	$.get(contentServiceName + 'iset_content.html', function(data) {
		$('#iset-content-widget').html(data);
	});
	
	/*
	** Copy to comment content.
	*/
	$.fn.copyToVariable = function() {
		var autoDocText = (document.all) ? document.selection.createRange().text : document.getSelection();
		
		if(autoDocText == ""){
			alert("Please select text to copy to Comment.");
		}else{
			if(window.opener){
				var isetresponse = window.opener.addKMComments("[" + document.addSolsForm.Title.value + "] - " + autoDocText);
				
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
	}
});