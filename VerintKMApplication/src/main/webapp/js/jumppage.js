/**
 * 
 */
var searchServiceName = "/searchservices/";
var contentServiceName = "/contentservices/";
//$(document).ready(function() {
$.fn.setUpJump=function() {
	var params = $.fn.getAllParametersString();
	
	$.fn.parsingURLVariables(params);
};

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
		
		var migratableReferenceId = "";
		
		var url = contentServiceName + 'km/iset/migref?refName='+refName+'&objType='+objType+'&objID='+objId;
		$.fn.serviceCall('GET', '', url, 15000, function(data) {
			log(data.migratableReferenceId["0"].migratableReferenceId);
			migratableReferenceId = data.migratableReferenceId["0"].migratableReferenceId;
			log(data.migratableReferenceId["0"].migratableReferenceId);
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
			
			switch(variables[2]) {
			case "Search":
				var Search = variables[3];
				//run a search on knowledgeBase and a keyword test
				var url = searchServiceName + "iset_search_container.html?tags=" + knowledgeBase + "&query="+Search;
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
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "p["){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for problem object on knowledgeBase and Problem RefID
					jQuery.ajaxSetup({async:false});
					var migratableReferenceId = $.fn.getIsetResponse(report,"SOLUTION","");
					jQuery.ajaxSetup({async:true});
					var url = contentServiceName + 'iset_content_container.html?id='+migratableReferenceId;
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "p("){
					var report = variables[3].substring(2, variables[3].length-1);
					
					var url = searchServiceName + "iset_search_container.html?tags=" + knowledgeBase + ","+report;
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "d{"){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for decision tree on knowledgeBase and Dtree GUID
					jQuery.ajaxSetup({async:false});
					var migratableReferenceId = $.fn.getIsetResponse("","DOCUMENT",report);
					jQuery.ajaxSetup({async:true});
					var url = contentServiceName + 'iset_content_container.html?id=' + migratableReferenceId;
					window.location.replace(url);
					break;

				}else if(variables[3].substring(0,2) == "d["){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for decision tree on knowledgeBase and Dtree RefID
					jQuery.ajaxSetup({async:false});
					var migratableReferenceId = $.fn.getIsetResponse(report,"DOCUMENT","");
					jQuery.ajaxSetup({async:true});
					var url = contentServiceName + 'iset_content_container.html?id=' + migratableReferenceId;
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "d("){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for decision tree on knowledgeBase and Migratable Reference
					var url = contentServiceName + 'iset_content_container.html?id=' + report;
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
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "r["){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for content on knowledgeBase and Case RefID
					jQuery.ajaxSetup({async:false});
					var migratableReferenceId = $.fn.getIsetResponse(report,"CASE","");
					jQuery.ajaxSetup({async:true});
					var url = contentServiceName + 'iset_content_container.html?id=' + migratableReferenceId;
					window.location.replace(url);
					break;
					
				}else if(variables[3].substring(0,2) == "r("){
					var report = variables[3].substring(2, variables[3].length-1);
					
					//run a search for content on knowledgeBase and Migratable Reference
					var url = contentServiceName + 'iset_content_container.html?id=' + report;
					window.location.replace(url);
					break;
					
				}
				break;
			case "Report":
				var reportSplit = variables[3].split(":");
				var content = reportSplit[0].substring(2, reportSplit[0].length-1);
				var contentFaq = reportSplit[1];
				
				//apply filter to result list with knowledgeBase, content, and contentFAQ
				var url = searchServiceName + "iset_search_container.html?tags=" + knowledgeBase + "&categories="+content+"&query="+contentFaq;
				window.location.replace(url);
				break;
				
			case "searchProperties":
				
				var searchProperties = variables[3];
				var search = variables[5];
				var forward = variables[7];
				
				//apply filter to to a code definition search
				
				break;
				
			default:
				
				$.fn.disableSpinner();
				$.fn.handleErrorText("URL is not in the correct format");
			
				break;
			}
		} else if (variables[0] == "displayByRefName"){
			// /verintkm/jumppage.html?displayByRefName= + refName 
			var refName = variables[1];

			//run a search for refName 
			jQuery.ajaxSetup({async:false});
			var migratableReferenceId = $.fn.getIsetResponse(refName,"all","");
			jQuery.ajaxSetup({async:true});
		//	document.cookie = 'savedurl=' + 'jumppage.html%3F' + variables[0] + '; path=/';
			var url = contentServiceName + 'iset_content_container.html?id='+migratableReferenceId;
			window.location.replace(url);
		
		} else {
			$.fn.handleErrorText("URL is not in the correct format");
		}
	}
	
	