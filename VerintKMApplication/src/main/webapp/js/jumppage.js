/**
 * 
 */
var searchServiceName = "/searchservices/";
var contentServiceName = "/contentservices/";
var filtersServiceName = '/filterservices/';
var kbaseTags = null;

//$(document).ready(function() {


$.fn.setUpJump=function() {
	var params = $.fn.getAllParametersString();
	
	$.fn.parsingURLVariables(params);
}

$.fn.parsingURLVariables = function( params) {
	//splits up the parameters
	var variablesAndValues = params.split("&");
	
	
	
	//creates a blank current variable
	var currentVariable = "";
	
	//creates blank variables
	var variablesWithValues = [];
	
	for(var i = 0; i < variablesAndValues.length; ++i ){
		currentVariable = variablesAndValues[i].split("=");
		
		//populate array, with variables followed by value
		variablesWithValues.push(currentVariable[0]);
		variablesWithValues.push(currentVariable[1]);
	}
	
	//passing the array of variables and values into the function
	//to call the respective services
	$.fn.callingRespectiveService( variablesWithValues);
}
	
// Get all the iset return from the hopefully working service
$.fn.getIsetResponse = function(refName, objType, objId) {
	
	log("refName: " + refName + " objType: " + objType + " objId: " + objId);
	var migratableReferenceId = "";
	
	var url = contentServiceName + 'km/iset/migref?refName='+refName+'&objType='+objType+'&objID='+objId;
	$.fn.serviceCall('GET', '', url, 15000, function(data) {
		if (typeof data.migratableReferenceId != 'undefined' && data.migratableReferenceId != null && data.migratableReferenceId.length > 0){
			migratableReferenceId = data.migratableReferenceId["0"].migratableReferenceId;
			log("migratableReferenceId=" + migratableReferenceId);
		} else {
			log("migratableReferenceId=");
			log(data);
		}
	});
	return migratableReferenceId;
}
	
$.fn.callingRespectiveService = function( variables) {
	
	
	log("ISET Variables: " + variables);
	
	
	
	//we want to check that the first element is create
	if( variables[0] == "create"){
		var knowledgeBase = "";
		var split = variables[1].split(":");
		knowledgeBase = split[1];
		
		if($.fn.checkKBaseTags(knowledgeBase)){
			switch(variables[2]) {
			case "Search":
				var Search = variables[3];
				//run a search on knowledgeBase and a keyword test
				var url = searchServiceName + "iset_search_container.html?tags=" + knowledgeBase + "&query="+Search;
				log('search URL: ' + url);
				window.location.replace(url);
				break;
			case "report":
				if(variables[3].substring(0,2) == "p{"){
					var report = variables[3].substring(2, variables[3].length-1);

					//run a search for problem object on knowledgeBase and Problem GUID
					jQuery.ajaxSetup({async:false});
					var migratableReferenceId = $.fn.getIsetResponse("","SOLUTION",report);
					jQuery.ajaxSetup({async:true});
					var url = contentServiceName + 'iset_content_container.html?id='+migratableReferenceId;
					log('report URL: ' + url);
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "p["){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for problem object on knowledgeBase and Problem RefID
					jQuery.ajaxSetup({async:false});
					var migratableReferenceId = $.fn.getIsetResponse(report,"SOLUTION","");
					jQuery.ajaxSetup({async:true});
					var url = contentServiceName + 'iset_content_container.html?id='+migratableReferenceId;
					log('report URL: ' + url);
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "p("){
					var report = variables[3].substring(2, variables[3].length-1);
					
					var url = searchServiceName + "iset_search_container.html?tags=" + knowledgeBase + ","+report + "&query=*";
					log('report URL: ' + url);
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "d{"){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for decision tree on knowledgeBase and Dtree GUID
					$.fn.launchISetDTContent(report);
					var url = contentServiceName + 'open_content_message.html?dtreeid=' + report;
					log('report URL: ' + url);
					window.location.replace(url);
//						jQuery.ajaxSetup({async:false});
//						var migratableReferenceId = $.fn.getIsetResponse("","DOCUMENT",report);
//						jQuery.ajaxSetup({async:true});
//						var url = contentServiceName + 'iset_content_container.html?dtreeid=' + migratableReferenceId;
//						window.location.replace(url);
					break;

				}else if(variables[3].substring(0,2) == "d["){
					var report = variables[3].substring(2, variables[3].length-1);
					var url = contentServiceName + 'open_content_message.html?dtreeid=' + report;
					log('report URL: ' + url);
					window.location.replace(url);
					
					//run a search for decision tree on knowledgeBase and Dtree RefID
//						jQuery.ajaxSetup({async:false});
//						var migratableReferenceId = $.fn.getIsetResponse(report,"DOCUMENT","");
//						jQuery.ajaxSetup({async:true});
//						$.fn.launchISetDTContent(migratableReferenceId);
//						var url = contentServiceName + 'iset_content_container.html?id=' + migratableReferenceId;
//						window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "d("){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for decision tree on knowledgeBase and Migratable Reference
					$.fn.launchISetDTContent(report);
					var url = contentServiceName + 'open_content_message.html?dtreeid=' + report;
					log('report URL: ' + url);
					window.location.replace(url);
					break;
					
				}
				break;
			case "view()":
				if(variables[3].substring(0,2) == "r{"){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for content on knowledgeBase and Case GUID
					jQuery.ajaxSetup({async:false});
					var migratableReferenceId = $.fn.getIsetResponse("","CASE",report);
					jQuery.ajaxSetup({async:true});
					var url = contentServiceName + 'iset_content_container.html?id=' + migratableReferenceId;
					log('veiw URL: ' + url);
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "r["){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for content on knowledgeBase and Case RefID
					jQuery.ajaxSetup({async:false});
					var migratableReferenceId = $.fn.getIsetResponse(report,"CASE","");
					jQuery.ajaxSetup({async:true});
					var url = contentServiceName + 'iset_content_container.html?id=' + migratableReferenceId;
					log('veiw URL: ' + url);
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "r("){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for content on knowledgeBase and Migratable Reference
					var url = contentServiceName + 'iset_content_container.html?id=' + report;
					log('veiw URL: ' + url);
					window.location.replace(url);
					break;
					
				}
				break;
			case "Report":
				var reportSplit = variables[3].split(":");
				var contentText = reportSplit[0].substring(2, reportSplit[0].length-1);
				var contentType = reportSplit[1];
				
				//apply filter to result list with knowledgeBase, content, and contentFAQ
				var url = searchServiceName + "iset_search_container.html?tags=" + knowledgeBase + "&categories="+contentType+"&query="+contentText;
				log('veiw URL: ' + url);
				window.location.replace(url);
				break;
				
			case "searchProperties":
				
				var searchProperties = variables[3];
				var search = decodeURI(variables[5]);
				var forward = variables[7];
				var constSearchStart = '(title<starts>"'
				var constSearchEnd	= '")';
				//search should be in the form of &search=(title<starts>”CodeSearchValue“)
					
				var indexStart = search.indexOf(constSearchStart); 	
				var indexEnd = search.indexOf(constSearchEnd);
				
				log('search='+search);
				
				if (indexStart != -1){
					if (indexEnd != -1){
						var search_term = search.substring(indexStart+constSearchStart.length, indexEnd -1)
						//apply filter to to a code definition search
						var url = searchServiceName + "code_search_container.html?query=" + search_term;
						log('veiw URL: ' + url);
						window.location.replace(url);
					} else {
						log('searchProperties format is incorrect, missing ")');
					}
				} else{
					log('searchProperties format is incorrect, missing (title<starts>"');
				}									
				
			default:
				
				$.fn.disableSpinner();
				$.fn.handleErrorText("URL is not in the correct format");
			
				break;
			}
		} else {
			//not allowed to see content as they don't have the correct kbase tag
			$.fn.handleErrorText('You do not have the authorization for this knowledge base');
			//alert('Error: You do not have the authorization for this knowledge base');
			log('Agent does not have the knowledge base team that contains the kbase tag: ' + knowledgeBase);
		}
	} else if (variables[0] == "displayByRefName"){
		// /verintkm/jumppage.html?displayByRefName= + refName 
		var refName = variables[1];

		var params = '';
		for (i=0; i < variables.length; i++){
			if(variables[i] == 'funcName' || variables[i] == 'funcParams' && i+1 < variables.length){
				params = params + '&' + variables[i] + '=' + variables[i+1];
			}
		}
		
		//run a search for refName 
		jQuery.ajaxSetup({async:false});
		var migratableReferenceId = $.fn.getIsetResponse(refName,"all","");
		jQuery.ajaxSetup({async:true});
	//	document.cookie = 'savedurl=' + 'jumppage.html%3F' + variables[0] + '; path=/';
		if (typeof migratableReferenceId != 'undefined' && migratableReferenceId != null && migratableReferenceId !='') {
			var url = contentServiceName + 'content_container.html?id='+migratableReferenceId+params;
			window.location.replace(url);
		} else {
			$.fn.handleErrorText('\'displayByRefName=' + refName + '\' is not a valid reference name.');
		}
	
	} else if (variables[0] == "isetDisplayByRefName"){
		// /verintkm/jumppage.html?isetDisplayByRefName= + refName 
		var refName = variables[1];

		var params = '';
		for (i=0; i < variables.length; i++){
			if(variables[i] == 'funcName' || variables[i] == 'funcParams' && i+1 < variables.length){
				params = params  + '&' + variables[i] + '=' + variables[i+1];
			}
		}
		
		//run a search for refName 
		jQuery.ajaxSetup({async:false});
		var migratableReferenceId = $.fn.getIsetResponse(refName,"all","");
		jQuery.ajaxSetup({async:true});
	//	document.cookie = 'savedurl=' + 'jumppage.html%3F' + variables[0] + '; path=/';
		if (typeof migratableReferenceId != 'undefined' && migratableReferenceId != null && migratableReferenceId !='') {
			var url = contentServiceName + 'iset_content_container.html?id='+migratableReferenceId+params;
			window.location.replace(url);
		} else {
			// Content resize
			window.resizeTo(512,440);
			//Move to top right hand side
			var ow =  screen.width - window.outerWidth;	
			window.moveTo(ow,0);
			$.fn.handleErrorText('\'isetDisplayByRefName=' + refName + '\' is not a valid reference name.');
		}
	
	} else {
		$.fn.handleErrorText("URL is not in the correct format");		
	}
}
	
$.fn.checkKBaseTags = function(systemTagName) {
	var url = filtersServiceName + 'km/kbasetags';		
	var result = false;
	
	jQuery.ajaxSetup({
		async : false
	});
	
	$.fn.serviceCall('GET', '', url, 15000, function(data) {
		
		var kbaseData = '';
		if (typeof data.tags != 'undefined' && data.tags != null && data.tags.length > 0) {
			for (var x = 0; x < data.tags.length; x++) {
				if (typeof data.tags[x] != 'undefined' && data.tags[x] != null && data.tags[x].systemTagName.length > 0) {
					//log('tag[' + x + '].systemTagName=' + data.tags[x].systemTagName);
					if (data.tags[x].systemTagName == systemTagName){
						result = true;
						//log ('result = ' + result);
						break;}
					}
				}
			}
	});

	jQuery.ajaxSetup({
		async : true
	});
	log ('Agent result of $.fn.checkKBaseTags(' + systemTagName + ') = '+ result);
	return result
}	
	
$.fn.launchISetDTContent = function(data){
	window.open (contentServiceName + 'iset_content_container.html?dtreeid=' + data, data + '_contentwindow','scrollbars=1,menubar=1,resizable=1,width=1040,height=850');
}
//});
