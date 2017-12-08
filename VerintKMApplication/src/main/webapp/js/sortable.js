/**
 * 
 */

alert('Running sortable.js');

$.fn.sortable = function() {
	  $( document ).ready( function( ) {
		  
			$( "#iainsortable ul" ).sortable({
				opacity: 0.8,
			   	connectWith: '#iainsortable ul',
			   	tolerance: 'pointer'
			});
			  
			  
			$( "#sortable ul" ).sortable({
				opacity: 0.8,
			   	connectWith: '#sortable ul',
			   	tolerance: 'pointer'
			});
			  
		    $( '.sortable li' ).each( function() {
		        if( $( this ).children( 'ul' ).length > 0 ) {
		            $( this ).addClass( 'parent' );     
		        }
		    });

		    $( '.sortable li.parent > a' ).click( function( ) {
		        $( this ).parent().toggleClass( 'active' );
		        $( this ).parent().children( 'ul' ).slideToggle( 'fast' );
		    });

		    $( '#all' ).click( function() {

		        $( '.sortable li' ).each( function() {
		            $( this ).toggleClass( 'active' );
		            $( this ).children( 'ul' ).slideToggle( 'fast' );
		        });
		    });

		});
}