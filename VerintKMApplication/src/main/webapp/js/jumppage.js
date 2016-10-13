/**
 * 
 */
$(document).ready(function() {
	var params = $.fn.getAllParametersString();
	
	$.fn.getIsetResponse("REFERENCE_NAME","OBJECT_TYPE","IQ_OBJECT_ID");
	
	$.fn.parsingURLVariables(params);
});

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
		
		var url = contentServiceName + 'km/iset/migref?refName='+refName+'&objType='+objType+'&objID='+objId;
		$.fn.serviceCall('GET', '', url, 15000, function(data) {
			//$.fn.parseTags(data);
		});
	
	$.fn.callingRespectiveService = function( variables) {
		
		//we want to check that the first element is create
		if( variables[0] == "create"){
			var UHG = "";
			var split = variables[1].split(":");
			UHG = split[1];
			
			switch(variables[2]) {
			case "Search":
				var Search = variables[3];
				//run a search on UHG and a keyword test
				var url = "http://localhost:8090/searchservices/search_container.html?tags=" + UHG + "&search=\""+report+"\"";
				window.location.replace(url);
				break;
			case "report":
				if(variables[3].substring(0,2) == "p{"){
					var report = variables[3].substring(2, variables[3].length-1);
					//run a search for problem object on UHG and Problem GUID
				}else if(variables[3].substring(0,2) == "p["){
					var report = variables[3].substring(2, variables[3].length-1);
					//run a search for problem object on UHG and Problem RefID
				}else if(variables[3].substring(0,2) == "p("){
					var report = variables[3].substring(2, variables[3].length-1);
					
					var url = "http://localhost:8090/searchservices/search_container.html?tags=" + UHG + ","+report+"\"";
					window.location.replace(url);
				}else if(variables[3].substring(0,2) == "d{"){
					var report = variables[3].substring(2, variables[3].length-1);
					//run a search for decision tree on UHG and Dtree GUID
				}else if(variables[3].substring(0,2) == "d["){
					var report = variables[3].substring(2, variables[3].length-1);
					//run a search for decision tree on UHG and Dtree RefID
				}else if(variables[3].substring(0,2) == "d("){
					var report = variables[3].substring(2, variables[3].length-1);
					//run a search for decision tree on UHG and Migratable Reference
				}
				break;
			case "view()":
				if(variables[3].substring(0,2) == "r{"){
					var report = variables[3].substring(2, variables[3].length-1);
					//run a search for content on UHG and Case GUID
				}else if(variables[3].substring(0,2) == "r["){
					var report = variables[3].substring(2, variables[3].length-1);
					//run a search for content on UHG and Case RefID
				}else if(variables[3].substring(0,2) == "r("){
					var report = variables[3].substring(2, variables[3].length-1);
					//run a search for content on UHG and Migratable Reference
				}
				break;
			case "Report":
				var reportSplit = variables[3].split(":");
				var content = reportSplit[0].substring(2, reportSplit[0].length-1);
				var contentFaq = reportSplit[1];
				//apply filter to result list with UHG, content, and contentFAQ
				break;
			case "searchProperties":
				
				var searchProperties = variables[3];
				var search = variables[5];
				var forward = variables[7];
				
				//apply filter to to a code definition search
				
				break;
			}
		}
	}
	}
	
	