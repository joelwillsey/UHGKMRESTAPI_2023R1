var filterType;

$(document).ready(function() {
	// Find input
	$.fn.findInput = function() {
		$('#add-filter-clear').addClass('add_filter_body_find_buttons_find');
		$('#add-filter-clear').removeClass('add_filter_body_find_buttons_clear');
	}
	
	// Clear Button
	$.fn.findButtonClear = function() {
		$('#find-input').val('');
		$('#add-filter-clear').removeClass('add_filter_body_find_buttons_find');
		$('#add-filter-clear').addClass('add_filter_body_find_buttons_clear');
	}
	
	// Filter function
	$.fn.addFilterSearch = function (element, what) {
		log(element);
	    var value = element;
	    value = value.toLowerCase().replace(/\b[a-z]/g, function(letter) {
	        return letter.toUpperCase();
	    });

	    // Filter list
	    if (value === '') {
	        $(what+' > li').show();
	    } else {
	        $(what + ' > li:not(:contains(' + value + '))').hide();
	        $(what + ' > li:contains(' + value + ')').show();
	        // Get the first in the list and highlight
	        var first = true;
	    	$(what + ' > li').each(
				function(index) {
					var li = $(this);
					if (li.is(":visible") && first) {
						log(li);
						li.addClass('selected');
						first = false;
					} else {
						li.removeClass('selected');
					} 
				}
			);
	    	
	    }
	};
	
	// Find Button
	$.fn.findButtonFind = function(object) {
		// ul-region-tags
		//filter_type = '#ul-filter-section-tree';
		var fInput = $('#find-input').val();
		
		$('#div-product-tags').css('display', 'none');
		$('#searchlist').css('display', 'block');
		$.fn.addFilterSearch(fInput, '#searchlist ul');
//        $(filter_type + ' > li:not(:contains(' + $('#find-input').val() + '))').hide();
//        $(filter_type + ' > li:contains(' + $('#find-input').val() + ')').show();
//
//        // Get the first in the list and highlight
//        var first = true;
//    	$(filter_type + ' > li').each(
//			function(index) {
//				var li = $(this);
//				if (li.is(":visible") && first) {
//					log(li);
//					li.addClass('selected');
//					first = false;
//				} else {
//					li.removeClass('selected');
//				} 
//			}
//		);
	}
	
	// Done Button
	$.fn.addFilterDone = function() {
		$.fn.addFilterCancel();
//		$('#div-' + filterType + '-tags').insertAfter('#' + filterType + '-tags-wrapper');
//		$('#div-' + filterType + '-tags').css('display', 'none');
//		$('#add-filter-widget').html($('#popup').html());
//		$('#background').removeClass('background_on');
//		$('#popup').removeClass('popup_on');
//		$('#popup').removeClass('popup_filters');
//		$('#popup').html('');
	}

	// Cancel Button
	$.fn.addFilterCancel = function() {
		$('#div-' + filterType + '-tags').insertAfter('#' + filterType + '-tags-wrapper');
		$('#div-' + filterType + '-tags').css('display', 'none');
		$('#add-filter-widget').html($('#popup').html());
		$('#background').removeClass('background_on');
		$('#popup').removeClass('popup_on');
		$('#popup').removeClass('popup_filters');
		$('#popup').html('');
		$('#searchlist').css('display', 'none');
	}
});