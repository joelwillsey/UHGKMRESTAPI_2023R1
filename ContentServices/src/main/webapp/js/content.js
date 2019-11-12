var contentId;
var viewId;
var contentIds= new Array();
var externalLink = false;
var contentTitle = '';
var vccdInstalled = false;
var addVccdScript = false;
var runFunction = false;
var funcName;
var funcParams;
var funcDelay = 200;

var tagsPathModule = (function(){
	//This is used to store a map of the tag paths (value) keyed off systemTagName
	var pathMap;
	var pub = {}; 	// public object - returned at end of module
	
	pub.setMap = function(newMap){
		pathMap = newMap;
	}
	
	pub.getMap = function(){
		return pathMap;
	}
	
	return pub;  	// expose externally
}());

$(document).ready(function() {	
	

	
	$('li').on('click', function() {
		//alert('clicked');
	});
	
    // Setup ratings and rate functions
    $('.rate1').on('click', function() {
    	$('#ratingStars').css('width', '1.0em');
    	$.fn.rateContent(contentId, 1);
    });
    $('.rate2').on('click', function() {
    	$('#ratingStars').css('width', '2.0em');
    	$.fn.rateContent(contentId, 2);
    });
    $('.rate3').on('click', function() {
    	$('#ratingStars').css('width', '3.0em');
    	$.fn.rateContent(contentId, 3);
    });
    $('.rate4').on('click', function() {
    	$('#ratingStars').css('width', '4.0em');
    	$.fn.rateContent(contentId, 4);
    });
    $('.rate5').on('click', function() {
    	$('#ratingStars').css('width', '5.0em');
    	$.fn.rateContent(contentId, 5);
    });
    
  //check if vccd is installed for agent
	if (isIE() && VCCDorNull()){	
		vccdInstalled = true;
	}
	log('VCCD installed: ' + vccdInstalled);

    // Email link
    $('#content-email-link').on('click', function() {
        //mailto:mail@something.com;mail@something.com;mail@something.com?subject=subject&body=Body&cc=&bcc=mail@something.com
    	var agentInfo = $.fn.getCookie('AgentInfo');
    	var agentName = '';
    	if (agentInfo != null && agentInfo != '') {
    		agentName = agentInfo;
    	}

    	// Setup the email
    	var emailData = [];
    	emailData.push('mailto:?subject=');
    	emailData.push('Share: ' + contentTitle);
    	emailData.push('&body=');
    	emailData.push('Dear Customer,%0D%0A%0D%0A');
    	emailData.push('Thank you for contacting Knowledge Central. My name is ' + agentName + ' and I am here to assist you with your query. Based on our last contact, you may find the information inside the link below to be useful:%0D%0A%0D%0A');
    	emailData.push('     ' + window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + contentServiceName + 'content_container.html?id=' + contentId + '%0D%0A%0D%0A');
    	emailData.push('It is my pleasure to assist you. Please don’t hesitate to contact us again if you need more information.%0D%0A%0D%0A');
    	emailData.push('Sincerely,%0D%0A');
    	emailData.push(agentName + '%0D%0A');
    	log(emailData.join('\n'));
    	
    	// Open up a email window; This is primarly for IE9/10 because the browser limits the size you can put in mailto
    	var wnd = window.open (emailData.join('\n'), 'Email','menubar=1,resizable=1,width=10,height=10');
    	emailData.length = 0;
    	setTimeout(function() {
    		// Note IE9/IE10 doesn't return a handle to the window (stupid, I know); So this window is left hanging...
    		if (typeof wnd != 'undefined' && wnd != null && wnd != '') {
    			wnd.close();
    		}
    	}, 1000);
    });

    // Refresh Link
    $('#content-skipto-refresh').on('click', function() {
    	$.fn.retrieveContent(contentId);
    });

    // Setup Mobile Exit Button
    $('#exit-button-mobile').on('click', function() {
    	$('#content-loader').removeClass('content_loader_on');
    	// Show content on top of content; this removes each one
    	if (contentIds.length > 0) {
    		contentIds.shift();
    		var id = contentIds.shift();
    		$.fn.retrieveContent(id);
    	}
    });

    // Setup Exit Button
    $('#exit-button').on('click', function() {
    	$('#content-loader').removeClass('content_loader_on');
    	contentIds.shift();
    	if (contentIds.length > 0) {
    		var id = contentIds.shift();
        	if (typeof id != 'undefined') {
        		$.fn.retrieveContent(id);
        	} else {
    	    	if (externalLink) {
    	    		window.top.close();
    	    	}
    	        //show search window if this is a code search container.
    	    	$('.dpui-widget').trigger('dpui:showCodeSearch');
        	}
    	} else {
	    	if (externalLink) {
	    		window.close();
	    	}
	        //show search window if this is a code search container.
	    	$('.dpui-widget').trigger('dpui:showCodeSearch');
    	}
    });


    
    
    // Setup Mobile Left Array Button
	$('.content-header-mobile-top-left-arrow').on('click', function() {
		$('#content-container').removeClass('content_container_on');
    	log(contentIds.length);
    	contentIds.shift();
    	if (contentIds.length > 0) {
    		log(contentIds.length);
    		var id = contentIds.shift();
    		$.fn.retrieveContent(id);
    	} else {
        	$('#content-loader').removeClass('content_loader_on');
    	}
	});

	// Feedback link
	$('#feedback').on('click', function() {
		log('Init feedback contentId=' + contentId + ' viewId='+viewId );
		$.fn.initFeedbackData(contentId, 0, viewId);
		$('#popup').html($('#feedback-widget').html());
		$('#feedback-widget').html('');
		$('#background').addClass('background_on');
		$('#popup').addClass('popup_on');
	});
	
	$('#content-bookmark-header').on('click', function(){		
			$.fn.bookmarkFunction();
			if($('#tab-bookmarks-button').hasClass('sel')){
				$('#tab-bookmarks-button').click().delay(1500);
			}
	})
	
	// Get the parameter by name
	$.fn.getParameterByName = function(name) {
	    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
	    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
	}

	// Open up new window/tab to view content
	$.fn.launchViewContent = function(data) {
		log(data);
		var currentPathName = window.location.pathname;
		log('currentPathName: ' + currentPathName);
		//Check if this is an iset_content_container, if it is the pop-out needs to open in a set_content_container
		if (currentPathName.search('iset_content_container.html') == -1){
			window.open (contentServiceName + 'content_container.html?id=' + data, data + '_contentwindow','menubar=1,resizable=1,width=1030,height=850');
		} else {
			window.open (contentServiceName + 'iset_content_container.html?id=' + data, data + '_contentwindow','menubar=1,resizable=1,width=1030,height=850');
		}
	}
	
	// Open up new window
	$.fn.launchDTViewContent = function(data) {
		log(data);
		window.open (contentServiceName + 'content_container.html?dt=' + data, data + '_contentwindow','menubar=1,resizable=1,width=1030,height=850');
	}

	 //Bookmark function
   $.fn.bookmarkFunction = function() {	   
	   if($('#content-bookmark').hasClass('bookmarked') || $('#mobile-content-bookmark').hasClass('bookmarked')){
		   $('#mobile-content-bookmark').removeClass('bookmarked');
		   $('#content-bookmark').removeClass('bookmarked');
		   $.fn.addBookmark(contentId, false);
	   } else {
		   $('#mobile-content-bookmark').addClass('bookmarked');
		   $('#content-bookmark').addClass('bookmarked');
		   $.fn.addBookmark(contentId, true);  
	   }
    }
    
   //Launch Remote Documents in new window
   $.fn.autoOpenRemoteDocuments = function(data) {
	   	log('Auto open remote document: ' + data);
		var myWindow = window.open(data, '_blank');
		if(myWindow){
			if(myWindow.focus) myWindow.focus();
			 	return false;
		}
		myWindow = null;
		return true;
	}
   
	// Setup skip links
    $.fn.setupLinks = function(data) {
    	var links = [];
		links.push("<ul>");
		// PublicBody
		if ((typeof data.publicBody != 'undefined' && data.publicBody != '') || 
				(typeof data.publicSectionContent != 'undefined' && data.publicSectionContent.length > 0) ||
				(typeof data.publicSegmentContent != 'undefined' && data.publicSegmentContent.length > 0)) {
			links.push('<li><a class="content_skipto_links_field" href="#content-public">Public information</a></li>');
			links.push('<div class="content_skipto_links_divider">|</div>');
		}
		// PublicAnswer
		if (typeof data.publicAnswer != 'undefined' && data.publicAnswer != '') {
			links.push('<li><a class="content_skipto_links_field" href="#content-public">Public Answer</a></li>');
			links.push('<div class="content_skipto_links_divider">|</div>');
		}
		// PrivateBody
		if ((typeof data.privateBody != 'undefined' && data.privateBody != '') || 
				(typeof data.privateSectionContent != 'undefined' && data.privateSectionContent.length > 0) ||
				(typeof data.privateSegmentContent != 'undefined' && data.privateSegmentContent.length > 0)) {
			links.push('<li><a class="content_skipto_links_field" href="#content-private">Private information</a></li>');
			links.push('<div class="content_skipto_links_divider">|</div>');
		}
		// PrivateAnswer
		if (typeof data.privateAnswer != 'undefined' && data.privateAnswer != '') {
			links.push('<li><a class="content_skipto_links_field" href="#content-private">Private Answer</a></li>');
			links.push('<div class="content_skipto_links_divider">|</div>');
		}
		// URL Information - Remote Documents
		if (typeof data.customFields != 'undefined' && data.customFields != null && data.customFields.length > 0 && data.contentCategory == "content_remotedocument") {				
					links.push('<li><a class="content_skipto_links_field" href="#content-' + "URLInformation" + '">' + "URL Information" + '</a></li>');
					links.push('<div class="content_skipto_links_divider">|</div>');			
		}
		// URL Information - Remote Documents
		if (typeof data.customFields != 'undefined' && data.customFields != null && data.customFields.length > 0 && data.contentCategory == "content_uploadeddocument") {				
					links.push('<li><a class="content_skipto_links_field" href="#content-' + "File Information" + '">' + "File Information" + '</a></li>');
					links.push('<div class="content_skipto_links_divider">|</div>');			
		}
		// TagSets
		if (typeof data.tagSets != 'undefined' && data.tagSets.length > 0) {
			links.push('<li><a class="content_skipto_links_field" href="#content-tags">Tags</a></li>');
			links.push('<div class="content_skipto_links_divider">|</div>');
		}
		// RelatedContent
		if (typeof data.relatedContent != 'undefined' && typeof data.relatedContent.externalContents != 'undefined' && typeof data.relatedContent.contentEntries != 'undefined' &&
				(data.relatedContent.externalContents.length > 0 || data.relatedContent.contentEntries.length > 0)) {
			var count = data.relatedContent.externalContents.length + data.relatedContent.contentEntries.length;
			links.push('<li><a class="content_skipto_links_field" href="#content-related">Related Content (' + count + ')</a></li>');
			links.push('<div class="content_skipto_links_divider">|</div>');
		}
		// CustomFields
		if (typeof data.customFields != 'undefined' && data.customFields != null && data.customFields.length > 0) {
			for (var x = 0; x < data.customFields.length; x++) {
				//Supress the remote document custom fields, they are special cases
				if (!(data.contentCategory == "content_remotedocument" && data.customFields[x].name == "url") &&
						!(data.contentCategory == "content_remotedocument" && data.customFields[x].name == "urlDescription") &&
						!(data.contentCategory == "content_remotedocument" && data.customFields[x].name == "file") &&
						!(data.contentCategory == "content_uploadeddocument" && data.customFields[x].name == "file") &&
						!(data.contentCategory == "content_uploadeddocument" && data.customFields[x].name == "fileDescription") &&
						!(data.contentCategory == "content_uploadeddocument" && data.customFields[x].name == "File Information") &&
						data.customFields[x].name != "tagpaths" &&
						data.customFields[x].name != "mustRead"){
					if (data.customFields[x].name == "keywords"){
					links.push('<li><a class="content_skipto_links_field" href="#content-' + data.customFields[x].name + '">' + "Watchwords" + '</a></li>');
					} else if (data.customFields[x].name == "referenceName") {
						links.push('<li><a class="content_skipto_links_field" href="#content-' + data.customFields[x].name + '">' + "Reference Name" + '</a></li>');
					}else {					
						links.push('<li><a class="content_skipto_links_field" href="#content-' + data.customFields[x].name + '">' + data.customFields[x].name + '</a></li>');
					}
					links.push('<div class="content_skipto_links_divider">|</div>');
				}
			}
		}
		// Attachments
		if (typeof data.attachments != 'undefined' && data.attachments.length > 0) {
			links.push('<li><a class="content_skipto_links_field" href="#content-attachments">Attachments (' + data.attachments.length + ')</a></li>');
		}
		links.push('</ul>');
		// Get the data from the array
		var retValue = links.join('\n');
		links.length = 0;
		return retValue;
    }

    //Get the javascript includes in the
    $.fn.getJavaScript = function(data) {
    	if (typeof data != 'undefined' && data != ''){
	    	log("Get Script:" + data);
	    	$.getScript(data).done(function(script, textStatus, jqxhr) {log( "getScript: " + data + " Status: " + textStatus + " jqxhr:" + jqxhr.status );});
    	} else {
    		log("Get Script is undefined");
    	}
    }
    
    
	// Setup the content
    $.fn.setupContent = function(data) {
    	// Store the contentId and viewUUID
    	contentId = data.id;
    	viewId = data.viewUUID;    	
    	log('contentId='+contentId); 
    	log('viewId='+contentId);
    	
    	// Setup icon
    	$('.content_header_mobile_top_left').html('<div class="content_header_mobile_top_left_' + data.contentCategory + '">&nbsp;</div>');
    	$('.content_header_left_icon').html('<div class="content_header_left_icon_' + data.contentCategory + '">&nbsp;</div>');
    	
    	// Setup title
    	$('.content_header_mobile_bottom').html('<div class="title">' + data.title + '</div>');
    	$('.content_header_left_title').html(data.title);
    	
    	// Is this featured content?
    	if (data.isFeatured) {
    		$('.content_header_mobile_top_right_featured').html('<div class="content_header_mobile_top_right_featured_show">&nbsp;</div>');
    		$('.content_header_right_featured').html('<div class="content_header_right_featured_show">&nbsp;</div>');
    	} else {
    		$('.content_header_mobile_top_right_featured').html('<div class="content_header_mobile_top_right_not_featured_show">&nbsp;</div>');
    		$('.content_header_right_featured').html('<div class="content_header_right_not_featured_show">&nbsp;</div>');    		
    	}

    	// Setup published date
    	$('.content_body_author_info_left_date').html(data.publishedDate);

    	// Setup published id
    	$('.content_body_author_info_left_id').html(data.publishedId);

    	// Setup ratings
    	$('.content_body_author_info_right_ratings').html(data.numberOfRatings + ' ratings');
    	
    	// Setup average ratings
    	$('#ratingStars').css('width', data.averageRating + 'em');
    	$('#mobile-ratingStars').css('width', data.averageRating + 'em');
    	
    	// Setup skip links
    	var links = $.fn.setupLinks(data)
    	$('.content_skipto_links').html(links);
    	
    	// Setup Public Content 
    	var contentBody = [];
    	
    	contentBody = $.fn.setupPublic(data, contentBody);

        // Setup Public Answer
    	contentBody = $.fn.setupPublicAnswer(data, contentBody);

    	// Setup Private Content
    	contentBody = $.fn.setupPrivate(data, contentBody);

        // Setup Private Answer
    	contentBody = $.fn.setupPrivateAnswer(data, contentBody);
    	
    	// Setup Remote Document Fields
    	contentBody = $.fn.setupCustomFieldsRemoteDocuments(data, contentBody);
    	
    	// Setup Uploaded Document Fields
    	contentBody = $.fn.setupCustomFieldsUploadedDocuments(data, contentBody);

    	// Setup Tags
    	contentBody = $.fn.setupTags(data, contentBody);
    
    	// Setup Related Content
    	contentBody = $.fn.setupRelated(data, contentBody);

    	// Setup Custom Fields
    	contentBody = $.fn.setupCustomFields(data, contentBody);

    	// Setup Attachments
    	contentBody = $.fn.setupAttachments(data, contentBody);

    	//if vccd is installed we need to add the vccd script
    	if (addVccdScript){
    		log('Adding in VCCD <script>');
    		contentBody.push($.fn.addVccdScript());
    		addVccdScript = false;
    	}
    	
    	// Setup the content body
    	$('.content_body_fields').html(contentBody.join('\n'));
        $(function(){
        	//This line was causing the public field to overwrite private field
           // $('.content_body_field_data').replaceWith($(data.publicBody));
        });
    	contentBody.length = 0;

		// Is this bookmarked content?
    	if (data.bookmarked) {
    		$('#mobile-content-bookmark').addClass('bookmarked');
    		$('#content-bookmark').addClass('bookmarked');
    	} else {
    		$('#mobile-content-bookmark').removeClass('bookmarked');
    		$('#content-bookmark').removeClass('bookmarked');
    	}

    	// Show the content
    	$('#content-container').addClass('content_container_on');
    	$('#content-loader').addClass('content_loader_on');
    	$('#content-area').addClass('content_area_on');

    	// Currently a hack
    	// TODO fix this??
    	if ($(window).outerWidth(true) < 1025) {
			$('.search_area').addClass('search_area_off');
		}

    	// Email data
    	contentId = data.id;
    	contentTitle = data.title;

        // Setup the HTML title to match the document title
        // NOTE: this is done for bookmarking purposes
        document.title = data.title;

        // Scroll back to top
        $('.content_container').scrollTop(0);
        
    	// Resize the window if necessary
    	$.fn.setupContentWidget();
    	
    	
    	//check for function passed in via url parameters
    	var tmpfunctionName = $.fn.getParameterByName('funcName');
    	if (typeof tmpfunctionName != 'undefined' && tmpfunctionName != null && tmpfunctionName != '') {
    		//There is a function name defined
    		funcName = tmpfunctionName;
    		log("Found parameter funcName: " + funcName);
    		
    		var tmpfunctionParameters = $.fn.getParameterByName('funcParams');
    		if (typeof tmpfunctionParameters != 'undefined' && tmpfunctionParameters != null && tmpfunctionParameters != '') {
    			funcParams = tmpfunctionParameters;
    			log("Found parameter funcParams: " + funcParams);
    		}
    		runFunction = true;
    		log("Enabling runFunction: " + runFunction);
    	} 
    	
    	//There is a function to be run after the content is displayed
    	if(runFunction){    		
	    	$(function(){
	    		if(document.readyState == 'complete'){
	    			//load has already fired but text may not be displayed yet lets delay a bit then run the function
	    			log('load event already fired - delay for ' + funcDelay + 'ms to allow for text to render');
	    			setTimeout(function(){
		    			log('Running function name: "' + funcName + '" function Parameters: "' + funcParams + '"');
		    			if (typeof funcParams != 'undefined' && funcParams != null && funcParams != ''){	    	    	
	    	    			//Function has parameters
	    	    			window[funcName](funcParams);
	    	    		} else {
	    	    			//no parameters passed
	    	    			window[funcName]();
	    	    		}
	    			}, funcDelay);
	    		} else {
		    		$(window).on('load',function(){
		    			console.log('start $(window).load');
		    			log('$(window).load - running function name: "' + funcName + '" function Parameters: "' + funcParams + '"');
		    			if (typeof funcParams != 'undefined' && funcParams != null && funcParams != ''){	    	    	
	    	    			//Function has parameters
	    	    			window[funcName](funcParams);
	    	    		} else {
	    	    			//no parameters passed
	    	    			window[funcName]();
	    	    		}
		    	    });
	    		} 
	    	});	    
    	}
    	    	

    	

    }

    
    // Setup Public Content
    $.fn.setupPublic = function(data, contentBody) {
    	if ((typeof data.publicBody != 'undefined' && data.publicBody != '') || 
    			(typeof data.publicSectionContent != 'undefined' && data.publicSectionContent.length > 0) ||
    			(typeof data.publicSegmentContent != 'undefined' && data.publicSegmentContent.length > 0)) {
    		// We have public content
			contentBody.push('<section id="content-public" class="content_body_field public_field">');
			contentBody.push('  <div class="content_body_field_label">');
			contentBody.push('    <label class="public">Public Information</label>');
			contentBody.push('  </div>');
			contentBody.push('  <div class="content_body_field_data">');
	    	//check if vccd parsing is needed
	    	if (vccdInstalled){
	    		var newHtml = findPhoneNumber(data.publicBody);
	    		//is there a vccd phone number?
	    		if (newHtml.length > 0){
	    			contentBody.push(newHtml);
	    			addVccdScript = true;
	    		} else {
	    			contentBody.push(data.publicBody);
	    		}
	    	} else {
	    		contentBody.push(data.publicBody);
	    	}			
			contentBody.push('  </div>');
			for (var p = 0; p < data.publicSectionContent.length; p++) {
				contentBody.push('  <div class="content_body_field_resuable_content">');
				//contentBody.push(' <!-- Content Type for publicBody is: ' + data.publicSectionContent[p].type + ' -->');
				if (data.publicSectionContent[p].type == 'pageSet') {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.showDTContent(\'' + data.publicSectionContent[p].id + '\');">');
				} else {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.retrieveContent(\'' + data.publicSectionContent[p].id + '\');">');
				}
				contentBody.push('      <div class="content_body_field_resuable_content_icon ' + data.publicSectionContent[p].type + '">&nbsp;</div>');
				contentBody.push('      <div class="content_body_field_resuable_content_link">' + data.publicSectionContent[p].title + '</div>');
				contentBody.push('    </a>');
				if (data.publicSectionContent[p].type == 'pageSet') {
					contentBody.push('    <div class="content_body_field_resuable_link" href="javascript:void(0);" onclick="$.fn.launchDTViewContent(\'' + data.publicSectionContent[p].id + '\');">');
				} else {
					contentBody.push('    <div class="content_body_field_resuable_link" href="javascript:void(0);" onclick="$.fn.launchViewContent(\'' + data.publicSectionContent[p].id + '\');">');
				}
				contentBody.push('      <img src="' + contentServiceName + '/images/ReadLaterGray16x16.png"/>');
				contentBody.push('    </div>');
				contentBody.push('  </div>');
			}
			contentBody.push('</section>');
    	}    	
    	
    	return contentBody;
    }
    
    // Setup Public Answer
    $.fn.setupPublicAnswer = function(data, contentBody) {
    	if (typeof data.publicAnswer != 'undefined' && data.publicAnswer != '') {
    		// We have public answer content
			contentBody.push('<section id="content-public" class="content_body_field public_field">');
			contentBody.push('  <div class="content_body_field_label">');
			contentBody.push('    <label class="public">Public Answer</label>');
			contentBody.push('  </div>');
			contentBody.push('  <div class="content_body_field_data">');
			//check if vccd parsing is needed
	    	if (vccdInstalled){
	    		var newHtml = findPhoneNumber(data.publicAnswer);
	    		//is there a vccd phone number?
	    		if (newHtml.length > 0){
	    			contentBody.push(newHtml);
	    			addVccdScript = true;
	    		} else {
	    			contentBody.push(data.publicAnswer);
	    		}
	    	} else {
	    		contentBody.push(data.publicAnswer);
	    	}	
			contentBody.push('  </div>');
			contentBody.push('</section>');
    	}
    	return contentBody;
    }

    // Setup Private Content
    $.fn.setupPrivate = function(data, contentBody) {
		if ((typeof data.privateBody != 'undefined' && data.privateBody != '') || 
				(typeof data.privateSectionContent != 'undefined' && data.privateSectionContent.length > 0) ||
				(typeof data.privateSegmentContent != 'undefined' && data.privateSegmentContent.length > 0)) {
			// We have private content
			contentBody.push('<section id="content-private" class="content_body_field private_field">');
			contentBody.push('  <div class="content_body_field_label">');
			contentBody.push('    <label class="private">Private Information</label>');
			contentBody.push('  </div>');
			contentBody.push('  <div class="content_body_field_data">');
			//check if vccd parsing is needed
	    	if (vccdInstalled){
	    		var newHtml = findPhoneNumber(data.privateBody);
	    		//is there a vccd phone number?
	    		if (newHtml.length > 0){
	    			contentBody.push(newHtml);
	    			addVccdScript = true;
	    		} else {
	    			contentBody.push(data.privateBody);
	    		}
	    	} else {
	    		contentBody.push(data.privateBody);
	    	}
			contentBody.push('  </div>');
			for (var p = 0; p < data.privateSectionContent.length; p++) {
				contentBody.push('  <div class="content_body_field_resuable_content">');
				if (data.privateSectionContent[p].type == 'pageSet') {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.showDTContent(\'' + data.privateSectionContent[p].id + '\');">');
				} else {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.retrieveContent(\'' + data.privateSectionContent[p].id + '\');">');
				}
				contentBody.push('      <div class="content_body_field_resuable_content_icon ' + data.privateSectionContent[p].type + '">&nbsp;</div>');
				contentBody.push('      <div class="content_body_field_resuable_content_link">' + data.privateSectionContent[p].title + '</div>');
				contentBody.push('    </a>');
				if (data.privateSectionContent[p].type == 'pageSet') {
					contentBody.push('    <div class="content_body_field_resuable_link" href="javascript:void(0);" onclick="$.fn.launchDTViewContent(\'' + data.privateSectionContent[p].id + '\');">');
				} else {
					contentBody.push('    <div class="content_body_field_resuable_link" href="javascript:void(0);" onclick="$.fn.launchViewContent(\'' + data.privateSectionContent[p].id + '\');">');
				}
				contentBody.push('      <img src="' + contentServiceName + '/images/ReadLaterGray16x16.png"/>');
				contentBody.push('    </div>');
				contentBody.push('  </div>');
			}
			contentBody.push('</section>');
		}
		return contentBody;
    }

    // Setup Private Answer Content
    $.fn.setupPrivateAnswer = function(data, contentBody) {
		if ((typeof data.privateAnswer != 'undefined' && data.privateAnswer != '') || 
				(typeof data.privateSectionContent != 'undefined' && data.privateSectionContent.length > 0) ||
				(typeof data.privateSegmentContent != 'undefined' && data.privateSegmentContent.length > 0)) {
			// We have private answer content
			contentBody.push('<section id="content-private" class="content_body_field private_field">');
			contentBody.push('  <div class="content_body_field_label">');
			contentBody.push('    <label class="private">Private Answer</label>');
			contentBody.push('  </div>');
			contentBody.push('  <div class="content_body_field_data">');
	    	if (vccdInstalled){
	    		var newHtml = findPhoneNumber(data.privateAnswer);
	    		//is there a vccd phone number?
	    		if (newHtml.length > 0){
	    			contentBody.push(newHtml);
	    			addVccdScript = true;
	    		} else {
	    			contentBody.push(data.privateAnswer);
	    		}
	    	} else {
	    		contentBody.push(data.privateAnswer);
	    	}
			contentBody.push('  </div>');
			for (var p = 0; p < data.privateSectionContent.length; p++) {
				contentBody.push('  <div class="content_body_field_resuable_content">');
				if (data.privateSectionContent[p].type == 'pageSet') {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.showDTContent(\'' + data.privateSectionContent[p].id + '\');">');
				} else {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.retrieveContent(\'' + data.privateSectionContent[p].id + '\');">');
				}
				contentBody.push('      <div class="content_body_field_resuable_content_icon ' + data.privateSectionContent[p].type + '">&nbsp;</div>');
				contentBody.push('      <div class="content_body_field_resuable_content_link">' + data.privateSectionContent[p].title + '</div>');
				contentBody.push('    </a>');
				contentBody.push('  </div>');				
			}
			contentBody.push('</section>');
		}
		return contentBody;
    }

    // Setup Tags
    $.fn.setupTags = function(data, contentBody) {
		if (typeof data.tagSets != 'undefined' && data.tagSets.length > 0) {
			contentBody.push('<section id="content-tags" class="content_body_field tag_field">');
			contentBody.push('  <div class="content_body_field_label">');
			contentBody.push('    <label class="tag">Tags</label>');
			contentBody.push('  </div>');
			contentBody.push('  <div class="content_body_field_tags_data">');
			contentBody.push('    <div class="content_body_field_tags_data_field">');
	
			for (var p = 0; p < data.tagSets.length; p++) {

				contentBody.push('      <div class="content_body_field_tags_data_field_tags">');
				contentBody.push('        <div class="content_body_field_tags_data_field_tags_tag">' + data.tagSets[p].displayTagName + ':</div>');
					if (typeof data.tagSets[p].tags != 'undefined' && data.tagSets[p].tags.length > 0) {
				contentBody.push('          <div class="content_body_field_tags_data_field_tags_values">');
		    			for (var t=0; t < data.tagSets[p].tags.length; t++) {
		    				var tagPath = $.fn.getTagDisplayPath(data.tagSets[p].tags[t].systemTagName);
		    				if ( tagPath == null || tagPath == 'null' || tagPath == '' ){
		    					//No path use the default of display name
		    					tagPath = data.tagSets[p].tags[t].systemTagDisplayName
		    				}
		    				log("Tag path for " + data.tagSets[p].tags[t].systemTagName + ' is ' + tagPath);
		    	contentBody.push('            <div class="content_body_field_tags_data_field_tags_values_value" title="' + tagPath + '">&nbsp;' + data.tagSets[p].tags[t].systemTagDisplayName + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
		    			}
		    	contentBody.push('          </div>');
					}
				contentBody.push('      </div>');
			}
			contentBody.push('    </div>');
			contentBody.push('  </div>');
			contentBody.push('</section>');
		}
		return contentBody;
    }
	// Setup Related
    $.fn.setupRelated = function(data, contentBody) {
		if (typeof data.relatedContent != 'undefined' && data.relatedContent != null &&
				((data.relatedContent.contentEntries != 'undefined' && data.relatedContent.contentEntries != null && data.relatedContent.contentEntries.length > 0) ||
				 (data.relatedContent.externalContents != 'undefined' && data.relatedContent.externalContents != null && data.relatedContent.externalContents.length > 0)))
		{
			contentBody.push('<section id="content-related" class="content_body_field related_field">');
			contentBody.push('  <div class="content_body_field_label">');
			contentBody.push('    <label class="related">Related Content</label>');
			contentBody.push('  </div>');
			if (data.relatedContent.contentEntries != 'undefined' && 
					data.relatedContent.contentEntries != null &&
					data.relatedContent.contentEntries.length > 0) {
	
				for (var i = 0; i < data.relatedContent.contentEntries.length; i++) {
					contentBody.push('  <div class="content_body_field_related_data">');
					contentBody.push('    <div class="content_body_field_resuable_related_content">');
					if (data.relatedContent.contentEntries[i].type == 'pageSet') {
						contentBody.push('      <a href="javascript:void(0);" onclick="$.fn.showDTContent(\'' + data.relatedContent.contentEntries[i].id +  '\');">');
					} else {
						contentBody.push('      <a href="javascript:void(0);" onclick="$.fn.retrieveContent(\'' + data.relatedContent.contentEntries[i].id +  '\');">');
					}
					contentBody.push('        <div class="content_body_field_resuable_content_icon ' + data.relatedContent.contentEntries[i].type + '">&nbsp;</div>');
					contentBody.push('        <div class="content_body_field_resuable_content_link">' + data.relatedContent.contentEntries[i].title + '</div>');
					contentBody.push('      </a>');
					if (data.relatedContent.contentEntries[i].type == 'pageSet') {
						contentBody.push('		<div class="sr_lr_link" href="javascript:void(0);" title="Open in new window" onclick="$.fn.launchDTViewContent(\'' + data.relatedContent.contentEntries[i].id + '\');"><img src="images/ReadLaterGray16x16.png"></div>');
					}else {
						contentBody.push('		<div class="sr_lr_link" href="javascript:void(0);" title="Open in new window" onclick="$.fn.launchViewContent(\'' + data.relatedContent.contentEntries[i].id + '\');"><img src="images/ReadLaterGray16x16.png"></div>');
					}
					contentBody.push('    </div>');
					contentBody.push('  </div>');					
				}
			}
			
			if (data.relatedContent && data.relatedContent.externalContents &&	data.relatedContent.externalContents.length > 0) {    			
				for (var i = 0; i < data.relatedContent.externalContents.length; i++) {
					if(data.relatedContent.externalContents[i].url) {
						contentBody.push('  <div class="content_body_field_related_data">');
						contentBody.push('    <div class="content_body_field_resuable_content">');
						//we dont need to have a check on the content entries in order to post external content related content
						//and it actually breaks a lot of the time if we do
						contentBody.push('      <a target="_blank" href="' + data.relatedContent.externalContents[i].url + '">');
						// Going under the assumption here if it's undefined it's a uploaded doc coming across as just a url
						if (data.relatedContent.externalContents[i].type && data.relatedContent.externalContents[i].type != 'undefined'){
						contentBody.push('        <div class="content_body_field_resuable_content_icon ' + data.relatedContent.externalContents[i].type + '">&nbsp;</div>');
						} else { 
							contentBody.push('        <div class="content_body_field_resuable_content_icon ' + 'KnowledgeUploadED' + '">&nbsp;</div>');
						}
						
						contentBody.push('        <div class="content_body_field_resuable_content_link">' + data.relatedContent.externalContents[i].name + '</div>');
						contentBody.push('      </a>');
						contentBody.push('    </div>');
						contentBody.push('  </div>');
					}
				}
			}
			
			if (data.relatedContent.externalContents != 'undefined' && 
					data.relatedContent.externalContents != null &&
					data.relatedContent.externalContents.length > 0) {    			
				for (var i = 0; i < data.relatedContent.externalContents.length; i++) {
					if(data.relatedContent.externalContents[i].id) {
						contentBody.push('  <div class="content_body_field_related_data">');
						contentBody.push('    <div class="content_body_field_resuable_content">');
						//we dont need to have a check on the content entries in order to post external content related content
						//and it actually breaks a lot of the time if we do
						contentBody.push('      <a href="javascript:void(0);" onclick="$.fn.showDTContent(\'' + data.relatedContent.externalContents[i].id +  '\');">');
						contentBody.push('        <div class="content_body_field_resuable_content_icon ' + data.relatedContent.externalContents[i].type + '">&nbsp;</div>');
						contentBody.push('        <div class="content_body_field_resuable_content_link">' + data.relatedContent.externalContents[i].name + '</div>');
						contentBody.push('      </a>');
						contentBody.push('    </div>');
						contentBody.push('  </div>');
					}
				}
			}
			contentBody.push('</section>');
		}
		return contentBody;
    }
    
	// Setup Custom Fields
    $.fn.setupCustomFields = function(data, contentBody) {
		if (typeof data.customFields != 'undefined' && 
				data.customFields != null && 
				 data.customFields.length > 0)
		{
			for (var i = 0; i < data.customFields.length; i++) {
				//need to exclude the "url", "urlDescription" and file fields for content_remotedocument as they are handled in setupCustomFieldsRemoteDocuments
				if (!(data.contentCategory == "content_remotedocument" && data.customFields[i].name == "url") &&
						!(data.contentCategory == "content_remotedocument" && data.customFields[i].name == "urlDescription") &&
						!(data.contentCategory == "content_remotedocument" && data.customFields[i].name == "file") &&
						!(data.contentCategory == "content_uploadeddocument" && data.customFields[i].name == "file") &&
						!(data.contentCategory == "content_uploadeddocument" && data.customFields[i].name == "fileDescription") &&
						!(data.contentCategory == "content_uploadeddocument" && data.customFields[i].name == "File Information") &&
						data.customFields[i].name != "tagpaths" &&
						data.customFields[i].name != "mustRead"){
					contentBody.push('<section id="content-' + data.customFields[i].name + '" class="content_body_field custom_field">');
					contentBody.push('  <div class="content_body_field_label">');
					if (data.customFields[i].name == "keywords"){
						contentBody.push('    <label class="custom">' + "Watchwords" + '</label>');
						} else if (data.customFields[i].name == "referenceName"){
						contentBody.push('    <label class="custom">' + "Reference Name" + '</label>');
						} else {						
								contentBody.push('    <label class="custom">' + data.customFields[i].name + '</label>');
								}			
					contentBody.push('  </div>');
					contentBody.push('  <div class="content_body_field_custom_data">');
					contentBody.push(data.customFields[i].data);
					contentBody.push('  </div>');
					contentBody.push('</section>');
					}
			}
		}
		return contentBody;
    }

	// Setup Custom Fields for RemoteDocuments
    $.fn.setupCustomFieldsRemoteDocuments = function(data, contentBody) {
		if (typeof data.customFields != 'undefined' && 
				data.customFields != null && 
				 data.customFields.length > 0 && data.contentCategory == "content_remotedocument")
		{
			contentBody.push('<section id="content-' + "URLInformation" + '" class="content_body_field urlinformation_field">');
			contentBody.push('  <div class="content_body_field_label">');
			contentBody.push('    <label class="urlinformation">' + "URL Information" + '</label>');
			contentBody.push('  </div>');
			contentBody.push('  <div class="content_body_field_custom_data">');
			for (var i = 0; i < data.customFields.length; i++) {
				if (data.customFields[i].name == "url"){
					if (data.customFields[i].data != null){
						var urlArray = data.customFields[i].data.split("||");
						//Check for EM URL format Title||URL
						if (typeof urlArray != 'undefined' && urlArray.length == 2){
							if (urlArray[0].length > 0){
							contentBody.push("URL: " + '<a id="remote-document-link" target="_blank" href="' + urlArray[1] + '">' +  urlArray[0] + '</a><br><br>');
							$.fn.autoOpenRemoteDocuments(urlArray[1]);
							} else {
								//No title just use URL as title
								contentBody.push("URL: " + '<a id="remote-document-link" target="_blank" href="' + urlArray[1] + '">' +  urlArray[1] + '</a><br><br>');
								$.fn.autoOpenRemoteDocuments(urlArray[1]);
							}
						} else {
							contentBody.push("URL: " + '<a id="remote-document-link" target="_blank" href="' + data.customFields[i].data + '">' + data.customFields[i].data + '</a><br><br>');
							$.fn.autoOpenRemoteDocuments(data.customFields[i].data);
						}
					}
				}
				if (data.customFields[i].name == "urlDescription"){
					contentBody.push("URL Description: " + data.customFields[i].data + '<br>');
				}
			}
			contentBody.push('  </div>');
			contentBody.push('</section>');
		}
		return contentBody;
    }
    
    $.fn.setupCustomFieldsUploadedDocuments = function(data, contentBody) {
		if (typeof data.customFields != 'undefined' && 
				data.customFields != null && 
				 data.customFields.length > 0 && 
				 data.contentCategory == "content_uploadeddocument")
		{
			for (var i = 0; i < data.customFields.length; i++) {
				//need to exclude the "url", "urlDescription" and file fields for content_remotedocument as they are handled in setupCustomFieldsRemoteDocuments
				if (data.customFields[i].name == "File Information") {
					contentBody.push('<section id="content-' + data.customFields[i].name + '" class="content_body_field custom_field">');
					contentBody.push('  <div class="content_body_field_label">');
					if (data.customFields[i].name == "keywords"){
						contentBody.push('    <label class="custom">' + "Watchwords" + '</label>');
						}	else {						
								contentBody.push('    <label class="custom">' + data.customFields[i].name + '</label>');
								}			
					contentBody.push('  </div>');
					contentBody.push('  <div class="content_body_field_custom_data">');
					contentBody.push(data.customFields[i].data);
					contentBody.push('  </div>');
					contentBody.push('</section>');
					}
			}
		}
		return contentBody;
    }
    
	// Setup Attachments
    $.fn.setupAttachments = function(data, contentBody) {
		if (typeof data.attachments != 'undefined' && 
				data.attachments != null && 
				 data.attachments.length > 0)
		{
			contentBody.push('<section id="content-attachments" class="content_body_field attachment_field">');
			contentBody.push('  <div class="content_body_field_label">');
			contentBody.push('    <label class="attachment">Attachments</label>');
			contentBody.push('  </div>');
			for (var i = 0; i < data.attachments.length; i++) {
				contentBody.push('  <div class="content_body_field_attachment_content">');
				contentBody.push('    <a target= "_blank" href="' + data.attachments[i].url + '">');
				contentBody.push('      <div class="content_body_field_attachment_content_icon">&nbsp;</div>');
				contentBody.push('      <div class="content_body_field_attachment_content_link">' + data.attachments[i].name + '</div>');
				contentBody.push('    </a>');
				contentBody.push('  </div>');
			}
			contentBody.push('</section>');
		}
		return contentBody;
    }

    // Retrieve External Content
    $.fn.retrieveExternalContent = function(data) {
    	log(data);
    	
    	//need to set the contentID and ViewId for feedback
    	contentId = data.contentId;
    	// There is no viewID in data package to blank it out so it does not use the last viewId
    	viewId = '';    
    	
    	//need to mark this content as viewed, normally done in the GetContent API but external content does not go through this
    	var dataPackage = '{"contentId":"' + data.contentId  + '"}';
    	$.fn.serviceCall('POST', dataPackage, contentServiceName + 'km/markasviewed', CONTENT_SERVICE_TIMEOUT, function(data) {    		
			log('Get View ID for External Content: ' + data.viewUUID);
		    if (typeof data != 'undefined' && data != null && data != '') {
		    	viewId = data.viewUUID;
		    }
    	});
		    
    	log('contentId='+contentId); 
    	log('viewId='+viewId);
    	
    	// Setup icon
    	$('.content_header_mobile_top_left').html('<div class="content_header_mobile_top_left_content_spidereddocument">&nbsp;</div>');
    	$('.content_header_left_icon').html('<div class="content_header_left_icon_content_spidereddocument">&nbsp;</div>');
    	
    	// Setup title
    	$('.content_header_mobile_bottom').html('<div class="title">' + data.title + '</div>');
    	$('.content_header_left_title').html(data.title);
    	
    	// Is this featured content?
    	if (data.isFeatured) {
    		$('.content_header_mobile_top_right_featured').html('<div class="content_header_mobile_top_right_featured_show">&nbsp;</div>');
    		$('.content_header_right_featured').html('<div class="content_header_right_featured_show">&nbsp;</div>');
    	} else {
    		$('.content_header_mobile_top_right_featured').html('<div class="content_header_mobile_top_right_not_featured_show">&nbsp;</div>');
    		$('.content_header_right_featured').html('<div class="content_header_right_not_featured_show">&nbsp;</div>');    		
    	}		

    	// Is this bookmarked content?
    	if (data.bookmarked) {
    		$('#mobile-content-bookmark').addClass('bookmarked');
    		$('#content-bookmark').addClass('bookmarked');
    	} else {
    		$('#mobile-content-bookmark').removeClass('bookmarked');
    		$('#content-bookmark').removeClass('bookmarked');
    	}

    	// Setup published date
    	$('.content_body_author_info_left_date').html(data.publishedDate);

    	// Setup published id
    	$('.content_body_author_info_left_id').html(data.contentID);

    	// Setup ratings
    	$('.content_body_author_info_right_ratings').html(data.numberOfRatings + ' ratings');
    	
    	// Setup average ratings
    	$('#ratingStars').css('width', data.averageRating + 'em');
    	$('#mobile-ratingStars').css('width', data.averageRating + 'em');

    	// Setup Content Body
    	var contentBody = []; 
    	contentBody.push('<section class="content_body_fields"><section class="content_body_fields">');

    	// Setup External URL
    	contentBody.push('<section id="content-public" class="content_body_field public_field">');
    	contentBody.push('  <iframe src="' + data.url + '" width="100%" height="450px"/>');
    	contentBody.push('</section>');

    	log(data.tags);
    	
    	// Setup Tags
		if (typeof data.tags != 'undefined' && data.tags != null && data.tags != '') {
			// First parse the list
			var tagArray = data.tags.split(",");

			var kbaseArray = new Array();
			var topicArray = new Array();
			var productArray = new Array();
			var regionArray = new Array();
			var cntntsmeArray = new Array();
			var cntnttypeArray = new Array();
			var newchangeArray = new Array();
			var searchArray = new Array();
			if (typeof tagArray != 'undefined' && tagArray.length > 0) {
				for (var b=0; b < tagArray.length; b++) {
					var index = tagArray[b].indexOf('_');
					if (index != -1) {
						var tagSet = tagArray[b].substring(0, index);
						var tagName = tagArray[b].substring(index + 1);
						if (tagSet === 'topic') {
							topicArray.push(tagName);
						} else if (tagSet === 'product') {
							productArray.push(tagName);
						} else if (tagSet === 'region') {
							regionArray.push(tagName);
						} else if (tagSet === 'cntntsme') {
							cntntsmeArray.push(tagName);
						} else if (tagSet === 'kbase') {
							kbaseArray.push(tagName);
						} else if (tagSet === 'cntnttype') {
							cntnttypeArray.push(tagName);
						} else if (tagSet === 'newchang') {
							newchangeArray.push(tagName);
						} else if (tagSet === 'search') {
							searchArray.push(tagName);
						}
						
					}
				}
			}
			contentBody.push('<section id="content-tags" class="content_body_field tag_field">');
			contentBody.push('  <div class="content_body_field_label">');
			contentBody.push('    <label class="tag">Tags</label>');
			contentBody.push('  </div>');
			contentBody.push('  <div class="content_body_field_tags_data">');
			contentBody.push('    <div class="content_body_field_tags_data_field">');

			// kbase
			if (typeof kbaseArray != 'undefined' && kbaseArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">Knowledge Base:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < kbaseArray.length; t++) {
	    			var tagPath = $.fn.getTagDisplayPath('kbase_' + kbaseArray[t]);
    				if ( tagPath == null || tagPath == 'null' || tagPath == '' ){
    					//No path use the default of display name
    					tagPath = kbaseArray[t]
    				}
    				log("Tag path for " + 'kbase_' + kbaseArray[t] + ' is ' + tagPath);
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value" title="' + tagPath + '">&nbsp;' + kbaseArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
	    		}
	    		contentBody.push('  </div>');
	    		contentBody.push('</div>');
			}
			// Topic
			if (typeof topicArray != 'undefined' && topicArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">Topic:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < topicArray.length; t++) {
	    			var tagPath = $.fn.getTagDisplayPath('topic_' + topicArray[t]);
    				if ( tagPath == null || tagPath == 'null' || tagPath == '' ){
    					//No path use the default of display name
    					tagPath = topicArray[t]
    				}
    				log("Tag path for " + 'topic_' + topicArray[t] + ' is ' + tagPath);
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value" title="' + tagPath + '">&nbsp;' + topicArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
	    		}
	    		contentBody.push('  </div>');
	    		contentBody.push('</div>');
			}
			// Product
			if (typeof productArray != 'undefined' && productArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">Product:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < productArray.length; t++) {
	    			var tagPath = $.fn.getTagDisplayPath('product_' + productArray[t]);
    				if ( tagPath == null || tagPath == 'null' || tagPath == '' ){
    					//No path use the default of display name
    					tagPath = productArray[t]
    				}
    				log("Tag path for " + 'product_' + productArray[t] + ' is ' + tagPath);
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value" title="' + tagPath + '">&nbsp;' + productArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
	    		}
	    		contentBody.push('  </div>');
	    		contentBody.push('</div>');
			}
			// Region
			if (typeof regionArray != 'undefined' && regionArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">Region:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < regionArray.length; t++) {
	    			var tagPath = $.fn.getTagDisplayPath('region_' + regionArray[t]);
    				if ( tagPath == null || tagPath == 'null' || tagPath == '' ){
    					//No path use the default of display name
    					tagPath = regionArray[t]
    				}
    				log("Tag path for " + 'region_' + regionArray[t] + ' is ' + tagPath);
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value" title="' + tagPath + '">&nbsp;' + regionArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
	    		}
	    		contentBody.push('  </div>');
	    		contentBody.push('</div>');
			}
			// cntntsme
			if (typeof cntntsmeArray != 'undefined' && cntntsmeArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">Content SME:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < cntntsmeArray.length; t++) {
	    			var tagPath = $.fn.getTagDisplayPath('cntntsme_' + cntntsmeArray[t]);
    				if ( tagPath == null || tagPath == 'null' || tagPath == '' ){
    					//No path use the default of display name
    					tagPath = cntntsmeArray[t]
    				}
    				log("Tag path for " + cntntsmeArray[t] + ' is ' + tagPath);
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value" title="' + tagPath + '">&nbsp;' + cntntsmeArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
	    		}
	    		contentBody.push('  </div>');
	    		contentBody.push('</div>');
			}
			// cntnttype
			if (typeof cntnttypeArray != 'undefined' && cntnttypeArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">Content Type:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < cntnttypeArray.length; t++) {
	    			var tagPath = $.fn.getTagDisplayPath('cntnttype_' + cntnttypeArray[t]);
    				if ( tagPath == null || tagPath == 'null' || tagPath == '' ){
    					//No path use the default of display name
    					tagPath = cntnttypeArray[t]
    				}
    				log("Tag path for " + 'cntnttype_' + cntnttypeArray[t] + ' is ' + tagPath);
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value" title="' + tagPath + '">&nbsp;' + cntnttypeArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
	    		}
	    		contentBody.push('  </div>');
	    		contentBody.push('</div>');
			}
			// newchange
			if (typeof newchangeArray != 'undefined' && newchangeArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">New or Changed:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < newchangeArray.length; t++) {
	    			var tagPath = $.fn.getTagDisplayPath('newchange_' + newchangeArray[t]);
    				if ( tagPath == null || tagPath == 'null' || tagPath == '' ){
    					//No path use the default of display name
    					tagPath = newchangeArray[t]
    				}
    				log("Tag path for " + 'newchange_' + newchangeArray[t] + ' is ' + tagPath);
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value" title="' + tagPath + '">&nbsp;' +newchangArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
	    		}
	    		contentBody.push('  </div>');
	    		contentBody.push('</div>');
			}
			// search
			if (typeof searchArray != 'undefined' && searchArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">Searchability:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < searchArray.length; t++) {
	    			var tagPath = $.fn.getTagDisplayPath('search_' + searchArray[t]);
    				if ( tagPath == null || tagPath == 'null' || tagPath == '' ){
    					//No path use the default of display name
    					tagPath = searchArray[t]
    				}
    				log("Tag path for " + 'search_' + searchArray[t] + ' is ' + tagPath);
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value" title="' + tagPath + '">&nbsp;' + searchArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
	    		}
	    		contentBody.push('  </div>');
	    		contentBody.push('</div>');
			}
			
			contentBody.push('    </div>');
			contentBody.push('  </div>');
			contentBody.push('</section>');
		}
    	contentBody.push('</section>');

    	// Setup the content body
    	$('.content_body_fields').html(contentBody.join('\n'));
    	contentBody.length = 0;

    	// Show the content
    	$('#content-loader').addClass('content_loader_on');

    	// Resize the window if necessary
    	$.fn.setupContentWidget();
    	

    }
    
    //add javascript files to head
    $.fn.setJavaScript = function(filename) {
    	var targetelement = "script"
    	var targetattr= "src" 
        var addJS = true;
    	var allsuspects=document.getElementsByTagName(targetelement)
    	for (var i=allsuspects.length; i>=0; i--){ //search backwards within nodelist for matching elements 
	    	if (allsuspects[i] && allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(filename)!=-1){
	    		//element already exists no need to add it	    		
	    		addJS = false;
	    	} 
    	}
    	if (addJS) {
    	var oHead = document.getElementsByTagName('HEAD').item(0);
	    	var oScript= document.createElement("script");
	    	oScript.type = "text/javascript";
	    	oScript.src = filename;
	    	oHead.appendChild(oScript);
	    	log('Adding javascript to head element: ' + filename);
    	}  else {
    		log('Javascript src already exists in head element: ' + filename);
    	}
    }

    //add css files to head
    $.fn.setCSS = function(filename) {
    	var targetelement = "link"
    	var targetattr= "href" 
        var addCSS = true;
    	var allsuspects=document.getElementsByTagName(targetelement)
    	for (var i=allsuspects.length; i>=0; i--){ //search backwards within nodelist for matching elements 
	    	if (allsuspects[i] && allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(filename)!=-1){
	    		//element already exists no need to add it	    		
	    		addCSS = false;
	    	} 
    	}
    	if (addCSS) {
	    	var oHead = document.getElementsByTagName('HEAD').item(0);
	    	var oLink= document.createElement("link");
	    	oLink.type = "text/css";
	    	oLink.media = "screen";
	    	oLink.rel = "stylesheet";
	    	oLink.href = filename;
	    	oHead.appendChild(oLink);
	    	log('Adding CCS stylesheet to head element: ' + filename);
    	} else {
    		log('CCS stylesheet already exists in head element: ' + filename);
    	}
    }
    
    // Rate content
	$.fn.rateContent = function(id, rating) {
		var dataPackage = '{"contentId":"' + id + '", "rating":' + rating + '}';
		$.fn.serviceCall('POST', dataPackage, contentServiceName + 'km/rate', FEEDBACK_SERVICE_TIMEOUT, function(data) {
			// Do nothing for now...
		});
	}

    // Add bookmark
	$.fn.addBookmark = function(id, enable) {
		var dataPackage = '';
		
		jQuery.ajaxSetup({async:false});
		if (enable) {
			dataPackage = '{"contentId":"' + id + '","userAction":"ADD"}';
			$.fn.serviceCall('POST', dataPackage, contentServiceName + 'km/bookmarkservice/addbookmark', BOOKMARK_SERVICE_TIMEOUT, function(data) {
				// Do nothing for now...
			});
		} else {
			dataPackage = '{"contentId":"' + id + '","userAction":"REMOVE"}';
			$.fn.serviceCall('POST', dataPackage, contentServiceName + 'km/bookmarkservice/removebookmark', BOOKMARK_SERVICE_TIMEOUT, function(data) {
				// Do nothing for now...
			});
		}
		jQuery.ajaxSetup({async:true});
	}

	
	//Check for Hide in Search. If this is present then we do not show the bookmark option.
	$.fn.showHideBookmarkIcon = function(data) {
		var name;
		//check if the bookmark icon even exists
		if ($('#content-bookmark-header').length > 0){
			if ( data != 'undefined' && data != null && data != '') {
				if ( data.tagSets != 'undefined' && data.tagSets != null && data.tagSets != '') {
					for (var i = 0; i < data.tagSets.length; i++) {
						if ( data.tagSets[i].tags != 'undefined' && data.tagSets[i].tags != null && data.tagSets[i].tags != '') {
							name = data.tagSets[i].tags[0].systemTagName; 
							if (name == "search_hideinsearch"){
								log('Hiding bookmark icon for search_hideinsearch content')
								$('#content-bookmark-header').hide();
							}  else if (name == "search_showinsearch") {
								 if (document.getElementById("content-bookmark-header").style.display == "none")
								  {
									 log('Showing bookmark icon, is currently hidden');
									 $('#content-bookmark-header').show();							 
								  }						
							}
						}
					}
				}
			}
		}
	}
	
	// Retrieve content
	$.fn.retrieveContent = function(id) {
		log('Retrieve Content: ' + id);
    	var length = contentIds.unshift(id);

		// First check if pushState is supported and if so is it enabled
		// Changes the browser URL to use the parameters so users can save searches and/or bookmark them
		if (history.pushState && typeof historyPushEnabled != 'undefined' && historyPushEnabled) {
	    	var query = '?id=' + id;
	    	var stateObj = { path: query };
			// IE9 has an issue with history; Don't know if IE
	    	if ($.fn.isIE() === 9) {
	    		 // IE9 code
	    		log('IE9 Detected');
				//history.pushState(stateObj, "newPage", query);
			} else {
				//history.pushState(stateObj, "newPage", verintKmServiceName + 'verintkm.html' + query);
			}
		}
		$.fn.serviceCall('GET', '', contentServiceName + 'km/content/id/' + id, CONTENT_SERVICE_TIMEOUT, function(data) {
			log('Get content ID: ' + data);
		    if (typeof data != 'undefined' && data != null && data != '') {
		    	
		    	//if there are externalSourceFiles need to load them before rendering the content
		    	if (typeof data.externalSrcFiles != 'undefined' && data.externalSrcFiles != null && data.externalSrcFiles.length > 0) {
		    		
		    		//initilize array to keep track of js file downloads
		    		var finished = [];
		    		for (var z= 0; z < data.externalSrcFiles.length; z++){
		    			finished[z] = false;
		    			//console.log("finished[" + z + "]=" + finished[z]);
		    		}
		    		
		    		for (var x = 0; x < data.externalSrcFiles.length; x++) {						
		    			var scriptName = data.externalSrcFiles[x].src;
		    			console.log("scriptName " + data.externalSrcFiles[x].src + " file index: [" + x + "]");
		    			if (data.externalSrcFiles[x].async == "false") {
		    				jQuery.ajaxSetup({async:false});
							//this script must be loaded synchronously
							$.cachedScriptSync(scriptName).always(function( script, textStatus) {													
								//mark file as downloaded
								for (var w = 0; w < finished.length; w++){					
									if (data.externalSrcFiles[w].src == this.url ){
										finished[w] = true;
										//console.log("File Index [" + w + "] completed");
										break; // breaks out of loop completely
									}
								}								
								// check to see if all downloads are done
								var done = allDownloadsTrue(finished);								
								console.log('Get Script (synchronously): ' + this.url + ' textStatus: ' + textStatus + " ,File Index [" + w + "], all files retrieved: " + done);								
								if (done) {
									$.fn.setupContent(data);
								}
								
							});
							jQuery.ajaxSetup({async:true});
						} else {
							//this script can be loaded asynchronously
							$.cachedScriptAsync(scriptName).always(function( script, textStatus) {								
								//mark file as downloaded
								for (var w = 0; w < finished.length; w++){									
									if (data.externalSrcFiles[w].src == this.url ){
										finished[w] = true;
										//console.log("File Index [" + w + "] completed");
										break; // breaks out of loop completely
									}
								}
								// check to see if all downloads are done
								var done = allDownloadsTrue(finished);								
								console.log('Get Script (asynchronously): ' + this.url + ' textStatus: ' + textStatus + ", File Index [" + w + "], all files retrieved: " + done);									
								if (done) {
									$.fn.setupContent(data);
								}
							});
						}
					}
				} else {
					//no external files to load, just setup content
					$.fn.setupContent(data);
				}	
		    	
		    	// if the content type is an alert then we need to make the setread call to mark it as read.
		    	if (data.contentType === 'KnowledgeAlertED'){
		    		// Strip the first two characters from the content ID as we don't want to pass the KM part down.
		    		var contentID = data.publishedId;
		    		contentID = contentID.replace('KM', '');
		    		
		    		// make the setread call
		    		//check if the alert has already been read before we add it to the database
		 
		    		var datareturned = []; 
		    		var alertDetailsContentIds = [];
		    		var alertDetails = [];
		    		jQuery.ajaxSetup({async:false});
		    		 $.fn.serviceCall('GET', '', contentServiceName + 'km/alerts/getread', SEARCH_SERVICE_TIMEOUT, function(datareturned){
		    			 alertDetails = datareturned;
		    			 
		    			 //alertDetailsContentIds = $.fn.getContentIdFromAlertDetails(datareturned);
		    		 });  
		    		 jQuery.ajaxSetup({async:true});
		    		 
		    		 var adlen;
		    			adlen = alertDetails.readAlerts.length;  
		    			for (i = 0; i < adlen; i++) {
		    				// get rid of content ID from string as we need migration Id which is matched to data.contactId
		    				var string = alertDetails.readAlerts[i].substring(alertDetails.readAlerts[i].indexOf(",") + 1);
		    				var contactId;
		    				contactId = string.substring(string.indexOf("=") + 1, string.indexOf(','));
		    				alertDetailsContentIds[alertDetailsContentIds.length] = contactId;
		    			}
		    		
		    			var len;
		    			var bold = false;
		    			len = alertDetailsContentIds.length;  
		    			for (i = 0; i < len; i++) {
		    				if (alertDetailsContentIds[i] === data.id){
		    					bold = true;
		    					break;
		    				}
		    			}
		    			if (bold !== true){
		    				$.fn.serviceCall('POST', '', contentServiceName + 'km/alerts/setread?contentId='+contentID+'&migRefId='+data.id, SEARCH_SERVICE_TIMEOUT,function(data){
								log('Sent read alert update for content id:' + contentID);
		    				});
		    			}
		    		

		    		
    				
		    	}
		    	
		    	$.fn.showHideBookmarkIcon(data);
		    	
		    } else {
		    	//no data found
		    	$.fn.setupContent(data);
		    }
		});
	}
	
	function allDownloadsTrue (finished) {
		var done = true;
		
		for (var y = 0; y < finished.length; y++ ){									
			if (finished[y] != true){
				done = false;
			}
		}
		
		return done
	}
	  	
	jQuery.cachedScriptSync = function(url, options) {
     	
  	  // Allow user to set any option except for dataType, cache, and url
  	  options = $.extend( options || {}, {    	    
  		dataType: "script",
  	    cache: true,
  	    url: url,  
  	    async: false,
  	  });
  	 
  	jQuery.ajaxPrefilter(function( options, originalOptions, jqXHR ) {
	  	  // Modify options, control originalOptions, store jqXHR, etc
  			originalOptions.url = options.url;
	  	});
  	  // Use $.ajax() since it is more flexible than $.getScript
  	  // Return the jqXHR object so we can chain callbacks
  	  return jQuery.ajax(options);
  	};
  	
	jQuery.cachedScriptAsync = function(url, options) {
     	
	  	  // Allow user to set any option except for dataType, cache, and url
	  	  options = $.extend( options || {}, {    	    
	  		dataType: "script",
	  	    cache: true,
	  	    url: url,  
	  	    async: true
	  	  });
	  	 
	  	  // Use $.ajax() since it is more flexible than $.getScript
	  	  // Return the jqXHR object so we can chain callbacks
	  	  return jQuery.ajax(options);
	  	};
	  	
	$.fn.retrieveDraftContent = function(id, state) {
		log('Retrieve Draft Content id: ' + id);
		log('Retrieve Draft Content state: ' + state);
    	var length = contentIds.unshift(id);

		// First check if pushState is supported and if so is it enabled
		// Changes the browser URL to use the parameters so users can save searches and/or bookmark them
		if (history.pushState && typeof historyPushEnabled != 'undefined' && historyPushEnabled) {
	    	var query = '?id=' + id;
	    	var stateObj = { path: query };
			// IE9 has an issue with history; Don't know if IE
	    	if ($.fn.isIE() === 9) {
	    		 // IE9 code
	    		log('IE9 Detected');
				//history.pushState(stateObj, "newPage", query);
			} else {
				//history.pushState(stateObj, "newPage", verintKmServiceName + 'verintkm.html' + query);
			}
		}
		
		// Draft content might not be in the contentServiceName path because it is proxied so we need to get teh proxy path to bypass Siteminder 
		var contextPath = window.location.pathname.split('/');		 
		/**When we split the pathname the first array element will be blank because it looks like this /verintkm/verintkm.html
		contextPath[0]="", contextPath[1]="verintkm", contextPath[2]="verintkm.html", **/
		var contextPath = window.location.pathname.split('/');
		
		var newContentServiceName = '';
		
		if (contextPath.length > 1){
			var newContextPath = '/';
			//don't want the last element as it is the html page
			for (x=0; x < contextPath.length-1; x++){
				if(typeof contextPath[x] != 'undefined' && contextPath[x] != ''){
					newContextPath = newContextPath + contextPath[x] + "/";
				}
			}
			
			newContentServiceName = newContextPath;
		} else {
			newContentServiceName= contentServiceName;
		}
		
		$.fn.serviceCall('GET', '', newContentServiceName + 'km/content/id/' + id + '/state/' + state , CONTENT_SERVICE_TIMEOUT, function(data) {
			log('Get draft content ID: ' + data);
		    if (typeof data != 'undefined' && data != null && data != '') {
		    	
		    	//if there are externalSourceFiles need to load them before rendering the content
		    	if (typeof data.externalSrcFiles != 'undefined' && data.externalSrcFiles != null && data.externalSrcFiles.length > 0) {
		    		
		    		//initilize array to keep track of js file downloads
		    		var finished = [];
		    		for (var z= 0; z < data.externalSrcFiles.length; z++){
		    			finished[z] = false;
		    			//console.log("finished[" + z + "]=" + finished[z]);
		    		}
		    		
		    		for (var x = 0; x < data.externalSrcFiles.length; x++) {						
		    			var scriptName = data.externalSrcFiles[x].src;
		    			console.log("scriptName " + data.externalSrcFiles[x].src + " file index: [" + x + "]");
		    			if (data.externalSrcFiles[x].async == "false") {
		    				jQuery.ajaxSetup({async:false});
							//this script must be loaded synchronously
							$.cachedScriptSync(scriptName).always(function( script, textStatus) {													
								//mark file as downloaded
								for (var w = 0; w < finished.length; w++){					
									if (data.externalSrcFiles[w].src == this.url ){
										finished[w] = true;
										//console.log("File Index [" + w + "] completed");
										break; // breaks out of loop completely
									}
								}								
								// check to see if all downloads are done
								var done = allDownloadsTrue(finished);								
								console.log('Get Script (synchronously): ' + this.url + ' textStatus: ' + textStatus + " ,File Index [" + w + "], all files retrieved: " + done);								
								if (done) {
									$.fn.setupContent(data);
								}
								
							});
							jQuery.ajaxSetup({async:true});
						} else {
							//this script can be loaded asynchronously
							$.cachedScriptAsync(scriptName).always(function( script, textStatus) {								
								//mark file as downloaded
								for (var w = 0; w < finished.length; w++){									
									if (data.externalSrcFiles[w].src == this.url ){
										finished[w] = true;
										//console.log("File Index [" + w + "] completed");
										break; // breaks out of loop completely
									}
								}
								// check to see if all downloads are done
								var done = allDownloadsTrue(finished);								
								console.log('Get Script (asynchronously): ' + this.url + ' textStatus: ' + textStatus + ", File Index [" + w + "], all files retrieved: " + done);									
								if (done) {
									$.fn.setupContent(data);
								}
							});
						}
					}
				} else {
					//no external files to load, just setup content
					$.fn.setupContent(data);
				}	
		    } else {
		    	//no data found
		    	$.fn.setupContent(data);
		    }
		});
	}
	
	// Show the decision tree content
	$.fn.showDTContent = function(id) {
		log('showDTContent id: ' + id)
	    var millis = new Date().getTime();
		var query = '?dtreeid=' + id + '&ts=' + millis;

		// First check if pushState is supported and if so is it enabled
		// Changes the browser URL to use the parameters so users can save searches and/or bookmark them
		if (history.pushState && typeof historyPushEnabled != 'undefined' && historyPushEnabled) {
			log('query: ' + query);
	    	var stateObj = { path: query };
			// IE9 has an issue with history; Don't know if IE
	    	if ($.fn.isIE() === 9) {
	    		 // IE9 code
	    		log('IE9 Detected');
				//history.pushState(stateObj, "newPage", query);
			} else {
				history.pushState(stateObj, "newPage", verintKmServiceName + 'verintkm.html' + query);
			}
		}

		// This is a Decision Tree
    	// Show the content
	$.fn.setupContentWidget(); // Some bug w/ browsers where I need to call this
		
		var decisionTreeUrl = $.fn.getProperty('PageSetV1.RestPageSet.Url');
		
		
		if (decisionTreeUrl === 'undefined' || decisionTreeUrl === null || decisionTreeUrl === 'null' || decisionTreeUrl === '') {
			var errStatus = 'Error retriveing D-Tree confiuration (PageSetV1.RestPageSet.Url), contact administrator';
				$.fn.handleErrorText(errStatus);
		} else {			
			//decisionTreeUrl2 = 'http://localhost:8280/GTConnect/UnifiedAcceptor/AddKnowPageSetServices.Implementation.PageSetV1.RestPageSet';
			var inlineHtml = '<iframe name="dtree-id" src="' + decisionTreeUrl + query + '" style="width: 1030px; height: 850px;"/>' 
			inlineHtml = inlineHtml + '<script> window.onbeforeunload = function (e) { $.fn.logoutDTree(\'' + decisionTreeUrl + '\');};</script>'
			$('#content-decision-tree').css('display', 'block');
			$('#content-decision-tree').css('width', '100%');
			$('#content-decision-tree').css('height', '100%');
			log('inlineHtml: ' + inlineHtml);
			$('#content-decision-tree').html(inlineHtml);
		}
	}

	// Setup refresh of content
	$.fn.viewResultsContent = function(data) {
		// First reset view cache
		contentIds.length = 0;
		if (typeof data != 'undefined' && data != '' && data != null) {
			if (typeof data.contentType != 'undefined' && data.contentType === 'pageSet') {
				$.fn.showDTContent(data.contentId);
			} else {
				$('#content-decision-tree').css('display', 'none');
				$('#content-loader').addClass('content_loader_on');
				$.fn.retrieveContent(data.contentId);
			}
		}
	}

	$.fn.getTagPaths = function(requestTagsets) {
		log('Retrieving tag for tag paths- getTagPaths()')
		var url = filtersServiceName + 'km/tags/gettagsfortagsets?tagsets=' + requestTagsets;

		
		$.fn.serviceCall('GET', '', url, 60000, function(data) {
			$.fn.parseTagPaths(data);
		});

	}
	
	$.fn.parseTagPaths = function(data) {
		//This function will populate the tagsPathModule with the tags path map
		log('Creating tag paths - parseTagPaths()')
		var tagMap = new Map();
		var tagPathMap = new Map();
		
		//Make a map of system_code as key display name as value
		if (typeof data.tagSets != 'undefined' && data.tagSets != null && data.tagSets.length > 0) {
			for (var x = 0; x < data.tagSets.length; x++) {
				if (typeof data.tagSets[x].tags != 'undefined' && data.tagSets[x].tags != null && data.tagSets[x].tags.length > 0) {
					for (var y = 0; y < data.tagSets[x].tags.length; y++){	
						var tagDataObj = {parentTagName : data.tagSets[x].tags[y].parentTagName, systemTagDisplayName : data.tagSets[x].tags[y].systemTagDisplayName};
						tagMap.set(data.tagSets[x].tags[y].systemTagName, tagDataObj);
					}
				}
			}
		}

		//Make a map of system_code as tag path name as value
		if (typeof data.tagSets != 'undefined' && data.tagSets != null && data.tagSets.length > 0) {
			for (var x = 0; x < data.tagSets.length; x++) {
				if (typeof data.tagSets[x].tags != 'undefined' && data.tagSets[x].tags != null && data.tagSets[x].tags.length > 0) {
					for (var y = 0; y < data.tagSets[x].tags.length; y++){
						var path = '';						
						var tempTag = tagMap.get(data.tagSets[x].tags[y].systemTagName);
						if (typeof tempTag.parentTagName != 'undefined' && tempTag.parentTagName != null && tempTag.parentTagName != ''){
							//let it loop to max of 20 times per tag just in case of tag misocnfig and we don't want an infinte loop 
							var failSafe = 0
							//has a parent so there will be a path
							while(typeof tempTag.parentTagName != 'undefined' && tempTag.parentTagName != null && tempTag.parentTagName != '' && failSafe <= 20){
								if (tagMap.has(tempTag.parentTagName)){
									if (path === ''){
										path =  tempTag.systemTagDisplayName
									} else {
										path =  tempTag.systemTagDisplayName + ' > ' + path;
									}									
									tempTag = tagMap.get(tempTag.parentTagName);
								} else {
									log('Error in the tagMap, missing entry: ' + tempTag.parentTagName);
									break;
								}
								failSafe++;
							}
							//we have the path now
							tagPathMap.set(data.tagSets[x].tags[y].systemTagName, path);
						} else {
							//no parent so just add tag name to map
							tagPathMap.set(data.tagSets[x].tags[y].systemTagName, data.tagSets[x].tags[y].systemTagDisplayName);
						}
					}
				}
			}
		}

		//This sets the map to the global tagsPathModule
		tagsPathModule.setMap(tagPathMap);

	}
	
	// used to retrieve a tags path 
	$.fn.getTagDisplayPath = function(systemTagName){
		
		var path = '';	
		var pathMap = tagsPathModule.getMap();
		
		if (typeof systemTagName != 'undefined' && systemTagName != null && systemTagName != ''){
			if (typeof pathMap != 'undefined' && pathMap != null){
				if(pathMap.has(systemTagName)){
					path = pathMap.get(systemTagName);
				} else {
					//no entry in the map use default logic
					log('Missing entry in pathMap: ' + systemTagName);
				}			
			} else {
				//we lost the map for some reason
				log('pathMap is undefined or null');
			}
		} else {
			log('getTagDisplayPath() - systemTagName is not valid: ' + systemTagName);
		}
		return path;
	}
	
	// Setup cross widget communication
    $.widget('ui.ajaxStatus', {
        options: {
        },
        _create : function() {
            var self = this;
            self.element.addClass('dpui-widget');
            self.element.bind('dpui:startWidget', function(e) {
                log('startWidget');
                $('.dpui-widget').trigger('dpui:registerContentView');               
            });
            self.element.bind('dpui:registerSearchResults', function(e) {
                log('registerSearchResults');
            });
            self.element.bind('dpui:registerCodeSearchResults', function(e) {
                log('registerCodeSearchResults');
            });
            self.element.bind('dpui:viewContent', function(e, data) {
                log('viewContent');
                log(data);
                $.fn.viewResultsContent(data);
            });
            self.element.bind('dpui:viewExternalContent', function(e, data) {
                log('viewExternalContent');
                $.fn.retrieveExternalContent(data);
            });
        },
        destroy: function(){
            $.Widget.prototype.destroy.apply(this, arguments);
        },
    });

    // Tell other widgets this widget is starting up
    $.ui.ajaxStatus( {}, $('<div></div>').appendTo('body'));
    $('.dpui-widget').trigger('dpui:startWidget');

    //populate the tagsPathModule object so we can display tag paths
    $.fn.getTagPaths('topic,product,region,cntnttype,newchange,kbase,search');

	// Check to see if an id was passed in
	var cId = $.fn.getParameterByName('id');
	var cWorkflowState = $.fn.getParameterByName('workflowstate');
	if (typeof cId != 'undefined' && cId != null && cId != 'null' && cId != '') {
		log('ContentID: ' + cId);
		contentId = cId;
		externalLink = true;
		if (typeof cWorkflowState != 'undefined' && cWorkflowState != null && cWorkflowState != 'null' && cWorkflowState != '') {
			log('Workflow State: ' + cWorkflowState);
			$.fn.retrieveDraftContent(cId, cWorkflowState);
		} else {
			$.fn.retrieveContent(cId);
		}
	} 

	// Check to see if a decision tree is being launched
	var dt = $.fn.getParameterByName('dtreeid');
	if (typeof dt != 'undefined' && dt != null && dt != 'null' && dt != '') {
		log('dtreeid: ' + dt);
		externalLink = true;
		$.fn.showDTContent(dt);
	} 

	// Get external content if necessary
	var tmpcontentId = $.fn.getParameterByName('contentId');
	if (typeof tmpcontentId != 'undefined' && tmpcontentId != null && tmpcontentId != 'null' && tmpcontentId != '') {
		log('contentId: ' +tmpcontentId);
		externalLink = true;
    	packagedData = {
    			"contentId": $.fn.getParameterByName('contentId'),
    			"url": $.fn.getParameterByName('url'),
    			"isFeatured": $.fn.getParameterByName('isFeatured'),
    			"averageRating": $.fn.getParameterByName('averageRating'),
    			"numberOfRatings": $.fn.getParameterByName('numberOfRatings'),
    			"title": $.fn.getParameterByName('title'),
    			"publishedDate": $.fn.getParameterByName('publishedDate'),
    			"tags": $.fn.getParameterByName('tags'),
    			"bookmarked": false
    	};
		$.fn.retrieveExternalContent(packagedData);
	}

	
});
