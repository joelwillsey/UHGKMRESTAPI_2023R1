package com.verint.services.km.errorhandling;

/**
 * 
 * @author jmiller
 *
 */
public class AppErrorMessage {
	public final static String SHARED_TEXT_SEARCH_ERROR = "Error running search, no valid response from service";
	public final static String FEATURED_CONTENT_ERROR = "Error retrieving featured content, no valid response from service";
	public final static String TOP_CONTENT_ERROR = "Error retrieving top content, no valid response from service";
	public final static String SEARCH_SUGGESTION_ERROR = "Error with search suggestions, no valid response from service";
	public final static String BOOKMARKS_ERROR = "Error retrieving bookmarks, no valid response from service";
	public final static String NO_CONTENTID_BOOKMARK = "Not a valid content ID";
	public final static String SUGGEST_CONTENT_ERROR = "Error with suggested content request";
	public final static String MANAGE_BOOKMARK_ERROR = "Error managing bookmarks";
	public final static String REORDER_BOOKMARK_ERROR = "Error reordering bookmarks";
	public final static String FEEDBACK_ERROR = "Error providing feedback";
	public final static String RATE_CONTENT_ERROR = "Rating of content error";
	public final static String ADD_BOOKMARK_ERROR = "Error adding bookmark";
	public final static String CONTENT_RETRIEVAL_ERROR = "Cannot find content with this ID";
	public final static String CONTENT_BOOKMARK_ERROR = "Error retrieving content bookmark";
	public final static String VIEW_CONTENT_ERROR = "Error marking content as viewed";
	public final static String LOGIN_ERROR = "Error during user login";
	public final static String CROSS_TAGS_ERROR = "Error with source tag or target tagset";
	public final static String UNAUTHORIZED_ACCESS = "Unauthorized: Authentication token was either missing or invalid";
	public final static String BAD_REQUEST = "Bad Request";
	public final static String UNEXPECTED_APPLICATION_EXCEPTION = "Unexpected application exception";
	public final static String HOVERTEXT_HOVERID_ERROR = "Error finding Hover Id for HoverText";
	public final static String HOVERTEXT_CONTENT_ERROR = "Error finding content for HoverText";
	public final static String BOOKMARKSV2_LISTALLBOOKMARKSV2_ERROR = "Error retrieving the bookmarks v2";	
}