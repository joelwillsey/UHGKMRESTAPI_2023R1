var contentId;
var viewId;
var contentIds= new Array();
var externalLink = false;
var contentTitle = '';


$(document).ready(function() {

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
    	emailData.push('Thank you for contacting Verint Customer Support. My name is ' + agentName + ' and I am here to assist you with your query. Based on our last contact, you may find the information inside the link below to be useful:%0D%0A%0D%0A');
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
        	}
    	} else {
	    	if (externalLink) {
	    		window.close();
	    	}
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
		$.fn.initFeedbackData(contentId, 0, viewId);
		$('#popup').html($('#feedback-widget').html());
		$('#feedback-widget').html('');
		$('#background').addClass('background_on');
		$('#popup').addClass('popup_on');
	});

	// Get the parameter by name
	$.fn.getParameterByName = function(name) {
	    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
	    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
	}

	// Open up new window/tab to view content
	$.fn.launchViewContent = function(data) {
		log(data);
		window.open (contentServiceName + 'content_container.html?id=' + data, data + '_contentwindow','menubar=1,resizable=1,width=1030,height=850');
	}
	$.fn.launchDTViewContent = function(data) {
		log(data);
		window.open (contentServiceName + 'content_container.html?dt=' + data, data + '_contentwindow','menubar=1,resizable=1,width=1030,height=850');
	}

	// Bookmark function
    var bookmarkFunction = function() {
		$('#mobile-content-bookmark').addClass('bookmarked');
		$('#content-bookmark').addClass('bookmarked');
    	$.fn.addBookmark(contentId);
    	$(".content_header_right_bookmark_action").off('click');
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
				links.push('<li><a class="content_skipto_links_field" href="#content-' + data.customFields[x].name + '">' + data.customFields[x].name + '</a></li>');
				links.push('<div class="content_skipto_links_divider">|</div>');
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

	// Setup the content
    $.fn.setupContent = function(data) {
    	// Store the contentId and viewUUID
    	contentId = data.id;
    	viewId = data.viewUUID;

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

    	// Is this bookmarked content?
    	if (data.bookmarked) {
    		$('#mobile-content-bookmark').addClass('bookmarked');
    		$('#content-bookmark').addClass('bookmarked');
    	} else {
    		$('#mobile-content-bookmark').removeClass('bookmarked');
    		$('#content-bookmark').removeClass('bookmarked');
    	    $('.content_header_right_bookmark_action').on('click', bookmarkFunction);
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

    	// Setup Tags
    	contentBody = $.fn.setupTags(data, contentBody);
    
    	// Setup Related Content
    	contentBody = $.fn.setupRelated(data, contentBody);

    	// Setup Custom Fields
    	contentBody = $.fn.setupCustomFields(data, contentBody);

    	// Setup Attachments
    	contentBody = $.fn.setupAttachments(data, contentBody);

    	// Setup the content body
    	$('.content_body_fields').html(contentBody.join('\n'));
    	contentBody.length = 0;

    	// Show the content
    	$('#content-container').addClass('content_container_on');
    	$('#content-loader').addClass('content_loader_on');
    	$('#content-area').css('display', 'block');

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

    	// Resize the window if necessary
    	$.fn.setupContentWidget();
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
			contentBody.push(data.publicBody);
			contentBody.push('  </div>');
			for (var p = 0; p < data.publicSectionContent.length; p++) {
				contentBody.push('  <div class="content_body_field_resuable_content">');
				if (data.publicSectionContent[p].type == 'pageSet') {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.showDTContent(\'' + data.publicSectionContent[p].id + '\');">');
				} else {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.retrieveContent(\'' + data.publicSectionContent[p].id + '\');">');
				}
				contentBody.push('      <div class="content_body_field_resuable_content_icon ' + data.publicSectionContent[p].type + '">&nbsp;</div>');
				contentBody.push('      <div class="content_body_field_resuable_content_link">' + data.publicSectionContent[p].title + '</div>');
				contentBody.push('    </a>');
				contentBody.push('  </div>');
				contentBody.push('  <div class="content_body_field_resuable_link">');
				if (data.publicSectionContent[p].type == 'pageSet') {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.launchDTViewContent(\'' + data.publicSectionContent[p].id + '\');">');
				} else {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.launchViewContent(\'' + data.publicSectionContent[p].id + '\');">');
				}
				contentBody.push('      <img src="' + contentServiceName + '/images/ReadLaterGray16x16.png"/>');
				contentBody.push('    </a>');
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
			contentBody.push(data.publicAnswer);
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
			contentBody.push(data.privateBody);
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
				contentBody.push('  <div class="content_body_field_resuable_link">');
				if (data.privateSectionContent[p].type == 'pageSet') {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.launchDTViewContent(\'' + data.privateSectionContent[p].id + '\');">');
				} else {
					contentBody.push('    <a href="javascript:void(0);" onclick="$.fn.launchViewContent(\'' + data.privateSectionContent[p].id + '\');">');
				}
				contentBody.push('      <img src="' + contentServiceName + '/images/ReadLaterGray16x16.png"/>');
				contentBody.push('    </a>');
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
			contentBody.push(data.privateAnswer);
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
	    	contentBody.push('            <div class="content_body_field_tags_data_field_tags_values_value">&nbsp;' + data.tagSets[p].tags[t].systemTagDisplayName + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
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
					contentBody.push('    </div>');
					contentBody.push('  </div>');
				}
			}
			if (data.relatedContent.externalContents != 'undefined' && 
					data.relatedContent.externalContents != null &&
					data.relatedContent.externalContents.length > 0) {    			
				for (var i = 0; i < data.relatedContent.externalContents.length; i++) {
					contentBody.push('  <div class="content_body_field_related_data">');
					contentBody.push('    <div class="content_body_field_resuable_content">');
					if (data.relatedContent.contentEntries[i].type == 'pageSet') {
						contentBody.push('      <a href="javascript:void(0);" onclick="$.fn.showDTContent(\'' + data.relatedContent.externalContents[i].url +  '\');">');
					} else {
						contentBody.push('      <a href="javascript:void(0);" onclick="$.fn.retrieveContent(\'' + data.relatedContent.externalContents[i].url +  '\');">');
					}
					contentBody.push('        <div class="content_body_field_resuable_content_icon ' + data.relatedContent.externalContents[i].type + '">&nbsp;</div>');
					contentBody.push('        <div class="content_body_field_resuable_content_link">' + data.relatedContent.externalContents[i].name + '</div>');
					contentBody.push('      </a>');
					contentBody.push('    </div>');
					contentBody.push('  </div>');
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
				contentBody.push('<section id="content-' + data.customFields[i].name + '" class="content_body_field custom_field">');
				contentBody.push('  <div class="content_body_field_label">');
				contentBody.push('    <label class="custom">' + data.customFields[i].name + '</label>');
				contentBody.push('  </div>');
				contentBody.push('  <div class="content_body_field_custom_data">');
				contentBody.push(data.customFields[i].data);
				contentBody.push('  </div>');
				contentBody.push('</section>');
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
				contentBody.push('    <a href="' + data.attachments[i].url + '">');
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
    	    $('.content_header_right_bookmark_action').on('click', bookmarkFunction);
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

			var topicArray = new Array();
			var productArray = new Array();
			var regionArray = new Array();
			var processArray = new Array();
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
						} else if (tagSet === 'process') {
							processArray.push(tagName);
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

			// Topic
			if (typeof topicArray != 'undefined' && topicArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">Topic:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < topicArray.length; t++) {
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value">&nbsp;' + topicArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
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
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value">&nbsp;' + productArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
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
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value">&nbsp;' + regionArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
	    		}
	    		contentBody.push('  </div>');
	    		contentBody.push('</div>');
			}
			// Process
			if (typeof processArray != 'undefined' && processArray.length > 0) {
				contentBody.push('<div class="content_body_field_tags_data_field_tags">');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_tag">Process:</div>');
				contentBody.push('  <div class="content_body_field_tags_data_field_tags_values">');
	    		for (var t=0; t < processArray.length; t++) {
	    			contentBody.push('  <div class="content_body_field_tags_data_field_tags_values_value">&nbsp;' + processArray[t] + '&nbsp;</div><div class="content_body_field_tags_data_field_tags_values_spacer">&nbsp;</div>');
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

    // Rate content
	$.fn.rateContent = function(id, rating) {
		var dataPackage = '{"contentId":"' + id + '", "rating":' + rating + '}';
		$.fn.serviceCall('POST', dataPackage, contentServiceName + 'km/rate', 15000, function(data) {
			// Do nothing for now...
		});
	}

    // Add bookmark
	$.fn.addBookmark = function(id) {
		var dataPackage = '{"contentId":"' + id + '"}';
		$.fn.serviceCall('POST', dataPackage, contentServiceName + 'km/addbookmark', 15000, function(data) {
				// Do nothing for now...
		});
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
				history.pushState(stateObj, "newPage", query);
			} else {
				history.pushState(stateObj, "newPage", verintKmServiceName + 'verintkm.html' + query);
			}
		}
		$.fn.serviceCall('GET', '', contentServiceName + 'km/content/id/' + id, 20000, function(data) {
			log('Get content ID: ' + data);
		    if (typeof data != 'undefined' && data != null && data != '') {
		    	$.fn.setupContent(data);
		    }
		});
	}
	
	// Show the decision tree content
	$.fn.showDTContent = function(id) {
		// First check if pushState is supported and if so is it enabled
		// Changes the browser URL to use the parameters so users can save searches and/or bookmark them
		if (history.pushState && typeof historyPushEnabled != 'undefined' && historyPushEnabled) {
	    	var query = '?dt=' + id;
	    	var stateObj = { path: query };
			// IE9 has an issue with history; Don't know if IE
	    	if ($.fn.isIE() === 9) {
	    		 // IE9 code
	    		log('IE9 Detected');
				history.pushState(stateObj, "newPage", query);
			} else {
				history.pushState(stateObj, "newPage", verintKmServiceName + 'verintkm.html' + query);
			}
		}

		// This is a Decision Tree
    	// Show the content
		$.fn.setupContentWidget(); // Some bug w/ browsers where I need to call this
		var inlineHtml = '<iframe src="' + decisionTreeUrl + id + '" style="width: 100%; height: 100%;"/>';
		$('#content-decision-tree').css('display', 'block');
		$('#content-decision-tree').css('width', '100%');
		$('#content-decision-tree').css('height', '100%');
		$('#content-decision-tree').html(inlineHtml);		
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

	// Check to see if an id was passed in
	var cId = $.fn.getParameterByName('id');
	if (typeof cId != 'undefined' && cId != null && cId != 'null' && cId != '') {
		log(cId);
		externalLink = true;
		$.fn.retrieveContent(cId);
	} 

	// Check to see if a decision tree is being launched
	var dt = $.fn.getParameterByName('dt');
	if (typeof dt != 'undefined' && dt != null && dt != 'null' && dt != '') {
		log(dt);
		externalLink = true;
		$.fn.showDTContent(dt);
	} 
	
	// Get external content if necessary
	var contentId = $.fn.getParameterByName('contentId');
	if (typeof contentId != 'undefined' && contentId != null && contentId != 'null' && contentId != '') {
		log(contentId);
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