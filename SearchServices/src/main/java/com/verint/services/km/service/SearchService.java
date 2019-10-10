/**
 * 
 */
package com.verint.services.km.service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.SearchDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.SearchRequest;
import com.verint.services.km.model.SearchResponse;


/**
 * @author jmiller
 *
 */
@Path("/knowledge")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class SearchService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

	@Autowired
	private SearchDAO searchDAO;

	/**
	 * 
	 */
	public SearchService() {
		super();
		LOGGER.debug("Entering SearchService()");
		LOGGER.debug("Exiting SearchService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param query
	 * @param tags
	 * @param categories
	 * @param page
	 * @param size
	 * @param sort
	 * @param httpRequest
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/search")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public SearchResponse search(@QueryParam("query") String query, 
    		@QueryParam("tags") String tags, 
    		@QueryParam("categories") String categories,
    		@QueryParam("page") BigInteger page,
    		@QueryParam("size") BigInteger size,
    		@QueryParam("sort") String sort,
    		@QueryParam("publishedid") String publishedId,
    		@Context HttpServletRequest httpRequest) throws AppException {
		LOGGER.info("Entering search()");
		SearchResponse searchResponse = null;

		try {
			LOGGER.debug("query: " + query);
			LOGGER.debug("tags: " + tags);
			LOGGER.debug("categories: " + categories);
			LOGGER.debug("page: " + page);
			LOGGER.debug("size: " + size);
			LOGGER.debug("sort: " + sort);
			LOGGER.debug("publishedid: " + publishedId);
	
			// Check for valid fields
			if (query == null || query.length() == 0) {
				query = "*";
			}
			if (tags == null || tags.length() == 0) {
				tags = "";
			}
			if (categories == null || categories.length() == 0) {
				categories = "";
			}
			if (sort == null || sort.length() == 0) {
				sort = "";
			}
			if (publishedId == null || publishedId.length() == 0) {
				publishedId = "";
			}

			// Get the authentication information
			String[] credentials = getAuthenticatinCredentials(httpRequest);

			// Create the SearchRequest
			final SearchRequest searchRequest = new SearchRequest();
			searchRequest.setQuery(query);
			searchRequest.setTags(tags);
			searchRequest.setCategories(categories);
			searchRequest.setPage(page);
			searchRequest.setSize(size);
			searchRequest.setUsername(credentials[0]);
			searchRequest.setPassword(credentials[1]);
			searchRequest.setSort(sort);
			searchRequest.setPublishedId(publishedId);
			LOGGER.debug("SearchRequest: " + searchRequest);

			// Do the search and get the response back
			Instant start = Instant.now();
			searchResponse = searchDAO.searchQuery(searchRequest, null);
			Instant end = Instant.now();
			LOGGER.debug("SearchService - search() duration: " + Duration.between(start, end).toMillis() + "ms");

			if (searchResponse != null) {
				searchResponse.setPage(searchRequest.getPage());
				searchResponse.setSize(searchRequest.getSize());
				Double totalPages = new Double(0);
				// Do we have valid number of results and size?
				if (searchResponse.getNumberOfResults() != null && searchRequest.getSize() != null) {
					totalPages = Math.ceil(searchResponse.getNumberOfResults().doubleValue()/searchRequest.getSize().doubleValue());
				}
				LOGGER.debug("totalPages: " + totalPages);
				searchResponse.setTotalPages(totalPages.intValue());
			} else {
				// Empty response
				searchResponse = new SearchResponse();
			}
		} catch (AppException ae) {
			LOGGER.error("AppException in search()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in search()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("SearchResponse: " + searchResponse);
		LOGGER.info("Exiting search()");
		return searchResponse;
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @param httpRequest
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/featuredcontent")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public SearchResponse getFeaturedContent(@QueryParam("page") BigInteger page,
    		@QueryParam("size") BigInteger size,
    		@QueryParam("tags") String tags,
    		@Context HttpServletRequest httpRequest) throws AppException {
		LOGGER.info("Entering getFeaturedContent()");
		SearchResponse searchResponse = null;

		try {
			LOGGER.debug("page: " + page);
			LOGGER.debug("size: " + size);

			// Get the authentication information
			String[] credentials = getAuthenticatinCredentials(httpRequest);

			// Create the SearchRequest
			final SearchRequest searchRequest = new SearchRequest();
			searchRequest.setPage(page);
			searchRequest.setSize(size);
			searchRequest.setTags(tags);
			searchRequest.setUsername(credentials[0]);
			searchRequest.setPassword(credentials[1]);
	
			// Do the search and get the response back
			Instant start = Instant.now();
			searchResponse = searchDAO.searchFeatured(searchRequest);
			Instant end = Instant.now();
			LOGGER.debug("SearchService - getFeaturedContent() duration: " + Duration.between(start, end).toMillis() + "ms");
			if (searchResponse != null) {
				searchResponse.setPage(searchRequest.getPage());
				searchResponse.setSize(searchRequest.getSize());
				Double totalPages = new Double(0);
				// Do we have valid number of results and size?
				if (searchResponse.getNumberOfResults() != null && searchRequest.getSize() != null) {
					totalPages = Math.ceil(searchResponse.getNumberOfResults().doubleValue()/searchRequest.getSize().doubleValue());
				}
				LOGGER.debug("totalPages: " + totalPages);
				searchResponse.setTotalPages(totalPages.intValue());
			} else {
				searchResponse = new SearchResponse();
			}
		} catch (AppException ae) {
			LOGGER.error("AppException in search()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in search()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("SearchResponse: " + searchResponse);
		LOGGER.info("Exiting getFeaturedContent()");
		return searchResponse;
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @param httpRequest
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/topcontent")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public SearchResponse getTopContent(@QueryParam("page") BigInteger page,
    		@QueryParam("size") BigInteger size,
    		@Context HttpServletRequest httpRequest) throws AppException {
		LOGGER.info("Entering getTopContent()");
		SearchResponse searchResponse = null;

		try {
			LOGGER.debug("page: " + page);
			LOGGER.debug("size: " + size);

			// Get the authentication information
			String[] credentials = getAuthenticatinCredentials(httpRequest);

			// Create the SearchRequest
			final SearchRequest searchRequest = new SearchRequest();
			searchRequest.setPage(page);
			searchRequest.setSize(size);
			searchRequest.setUsername(credentials[0]);
			searchRequest.setPassword(credentials[1]);
	
			// Do the search and get the response back
			Instant start = Instant.now();
			searchResponse = searchDAO.searchTopContent(searchRequest);
			Instant end = Instant.now();
			LOGGER.debug("SearchService - getTopContent() duration: " + Duration.between(start, end).toMillis() + "ms");
			if (searchResponse != null) {
				searchResponse.setPage(searchRequest.getPage());
				searchResponse.setSize(searchRequest.getSize());
				Double totalPages = new Double(0);
				// Do we have valid number of results and size?
				if (searchResponse.getNumberOfResults() != null && searchRequest.getSize() != null) {
					totalPages = Math.ceil(searchResponse.getNumberOfResults().doubleValue()/searchRequest.getSize().doubleValue());
				}
				LOGGER.debug("totalPages: " + totalPages);
				searchResponse.setTotalPages(totalPages.intValue());
			} else {
				searchResponse = new SearchResponse();
			}
		} catch (AppException ae) {
			LOGGER.error("AppException in search()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Exception in getFeaturedContent()", t);
		}

		LOGGER.debug("SearchResponse: " + searchResponse);
		LOGGER.info("Exiting getTopContent()");
		return searchResponse;
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @param httpRequest
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/bookmarks")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public SearchResponse getBookmarks(@QueryParam("page") BigInteger page,
    		@QueryParam("size") BigInteger size,
    		@Context HttpServletRequest httpRequest) throws AppException {
		LOGGER.info("Entering getBookmarks()");
		SearchResponse searchResponse = null;

		try {
			LOGGER.debug("page: " + page);
			LOGGER.debug("size: " + size);

			// Get the authentication information
			String[] credentials = getAuthenticatinCredentials(httpRequest);

			// Create the SearchRequest
			final SearchRequest searchRequest = new SearchRequest();
			searchRequest.setPage(page);
			searchRequest.setSize(size);
			searchRequest.setUsername(credentials[0]);
			searchRequest.setPassword(credentials[1]);
	
			// Do the search and get the response back
			Instant start = Instant.now();
			searchResponse = searchDAO.searchBookmarks(searchRequest);
			Instant end = Instant.now();
			LOGGER.debug("SearchService - getBookmarks() duration: " + Duration.between(start, end).toMillis() + "ms");

			if (searchResponse != null) {
				searchResponse.setPage(searchRequest.getPage());
				searchResponse.setSize(searchRequest.getSize());
				Double totalPages = new Double(0);
				// Do we have valid number of results and size?
				if (searchResponse.getNumberOfResults() != null && searchRequest.getSize() != null) {
					totalPages = Math.ceil(searchResponse.getNumberOfResults().doubleValue()/searchRequest.getSize().doubleValue());
				}
				LOGGER.debug("totalPages: " + totalPages);
				searchResponse.setTotalPages(totalPages.intValue());
			} else {
				searchResponse = new SearchResponse();
			}
		} catch (AppException ae) {
			LOGGER.error("AppException in search()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Exception in getFeaturedContent()", t);
		}

		LOGGER.debug("SearchResponse: " + searchResponse);
		LOGGER.info("Exiting getBookmarks()");
		return searchResponse;
	}
	
	@GET
	@Path("/blankResponse")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public SearchResponse blankResponse() {
		LOGGER.info("Entering blankResponse()");
		SearchResponse searchResponse = new SearchResponse();
		LOGGER.debug("SearchResponse: " + searchResponse);
		LOGGER.info("Exiting blankResponse()");
		return searchResponse;
	}

}