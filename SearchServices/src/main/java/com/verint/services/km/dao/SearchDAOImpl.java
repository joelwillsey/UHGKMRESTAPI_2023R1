/**
 * 
 */
package com.verint.services.km.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.*;
import java.time.Instant;
import java.time.Duration;

import com.verint.services.km.model.SearchResponse;
import com.verint.services.km.model.rest.*;
import com.verint.services.km.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.BookmarkedContent;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksResponseBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.ControlData;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentRequestBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentResponseBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentRequestBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentResponseBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeResultSet;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeUnit;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.RatingInformation;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.ReplacedTerm;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchRequestBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SuggestedQuery;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.SearchRequest;
import com.verint.services.km.model.Tag;
import com.verint.services.km.util.ConfigInfo;
import org.springframework.util.StringUtils;

/**
 * @author jmiller
 *
 */
@Repository
public class SearchDAOImpl extends BaseDAOImpl implements SearchDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchDAOImpl.class);

	private static String REST_SEARCH_URL;
	private static String REST_TAGS_URL;

	static {
		try {
			ConfigInfo config = new ConfigInfo();
			REST_SEARCH_URL = config.getRestKmSearchService();
			REST_TAGS_URL = config.getRestKmTagService();
		} catch (Throwable t) {
			LOGGER.error("Throwable Exception", t);
			System.exit(1);
		}
	}

	/**
	 * 
	 */
	public SearchDAOImpl() {
		super();
		LOGGER.info("Entering SearcDAOImpl()");
		LOGGER.info("Exiting SearcDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SearchDAO searchDAO = new SearchDAOImpl();
			SearchRequest searchRequest = new SearchRequest();
			searchRequest.setQuery("vernt");
			//searchRequest.setUsername("kmagent");
			SearchResponse searchResponse = searchDAO.searchQuery(searchRequest, null, null);
			LOGGER.info("SearchResponse: " + searchResponse);
		} catch (Throwable t) {
			LOGGER.error("Search exception", t);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#searchQuery(com.verint.services.km.model.SearchRequest)
	 */
	@Override
	public SearchResponse searchQuery(SearchRequest searchRequest, Float searchPrecision, String searchTriggerType) throws IOException, AppException {
		LOGGER.info("Entering searchKnowledge()");
		LOGGER.debug("SearchRequest: " + searchRequest);   
		SearchResponse searchResponse = new SearchResponse();
		ConfigInfo prop = new ConfigInfo();

		// Setup the request object to the SOAP call
		final SharedTextSearchRequestBodyType request = new SharedTextSearchRequestBodyType();
		request.setApplicationID(AppID);
		request.setLocale(Locale);
		request.setUsername(searchRequest.getUsername());
		request.setPassword(searchRequest.getPassword());
		request.setSearchText(searchRequest.getQuery());
		request.setContentCategory(searchRequest.getCategories());
		request.setTags(searchRequest.getTags());
		request.setSortFieldName(searchRequest.getSort());
		request.setContentOwner("");
		request.setPublishedId(searchRequest.getPublishedId());
		request.setWorkflowState("");
		final SearchDateType sdt = new SearchDateType();
		sdt.setToDate("");
		sdt.setFromDate("");
		request.setExpirationDate(sdt);
		request.setModifiedDate(sdt);
		request.setPublishedDate(sdt);
		request.setContentVersion("");
		request.setSortFieldOrder(Integer.parseInt(prop.getSortOrder()));
		      
		// If search precision is passed in as a parameter then use it, is not, use the system property.
		if (searchPrecision != null) {
			request.setSearchPrecision(searchPrecision);
		}else {
			searchPrecision = Float.parseFloat(prop.getsearchPrecision()); 
			request.setSearchPrecision(searchPrecision);
		}
		
		
		// If search precision is passed in as a parameter then use it, is not, use the system property.
		if (searchTriggerType != null) {
			request.setsearchTriggerType(searchTriggerType);
		}else {
			searchTriggerType = prop.getsearchTriggerType(); 
			request.setsearchTriggerType(searchTriggerType);
		}
		
		request.setsearchData("");
		request.setsearchContextual("");
		request.setexternalSearchId("");

		// Setup the page# and size# (if any)
		final ControlData controlData = new ControlData();
		int startIndex = (searchRequest.getPage().intValue() - 1) * searchRequest.getSize().intValue();
		controlData.setPaginationStartIndex(BigInteger.valueOf(startIndex));
		controlData.setMaxNumberOfUnitsPerGroup(new BigInteger(MaxNumberOfUnitsPerGroup));
		controlData.setMaxNumberOfGroupResults(searchRequest.getSize());
		controlData.setSpellCheckEnabled(SpellCheckEnabled);
		request.setControlData(controlData);
		
		LOGGER.debug("request: " + request);

		String externalSearchId = UUID.randomUUID().toString();
		String restRequestURI = buildRestAPIRequest(searchRequest, searchPrecision, false, false, externalSearchId);

		// Call the search
		Instant start = Instant.now();
		RestSearchResponse searchJsonResponse = RestUtil.getAndDeserialize(restRequestURI,null,
				HttpMethod.GET, RestSearchResponse.class, searchRequest.getOidcToken(), null, false);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+searchRequest.getUsername()+") - searchQuery() duration: " + Duration.between(start, end).toMillis() + "ms");

		if (false) {
			// We have a problem with the service
			// We already have one, send necessary error message
			throw new AppException(500, AppErrorCodes.SHARED_TEXT_SEARCH_ERROR,
					AppErrorMessage.SHARED_TEXT_SEARCH_ERROR);
		}

		searchResponse = populateRESTApiResponse(searchJsonResponse, searchResponse);
		searchResponse.setExternalSearchId(externalSearchId);

		// Populate Suggested Query
		searchResponse = populateRestSuggestedQuery(searchJsonResponse, searchResponse);
		if (searchRequest.getPublishedId().length() > 0) {
			LOGGER.debug("searchKnowledge() Removing suggestedQueries becasue this was a KM id search : " + searchResponse.getSuggestedQueries());
			//This was a content ID (published id, KM id) search, removed suggested queries otherwise when it finds the one piece of content it suggest another search
			final List<com.verint.services.km.model.SuggestedQuery> suggestedQueries = new ArrayList<com.verint.services.km.model.SuggestedQuery>();
			final Set<com.verint.services.km.model.SuggestedQuery> setSuggestedQueries = new LinkedHashSet<com.verint.services.km.model.SuggestedQuery>(suggestedQueries);
			searchResponse.setSuggestedQueries(setSuggestedQueries);
			
		}
		LOGGER.info("Exiting searchKnowledge()");
		return searchResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#searchFeatured(com.verint.services.km.model.SearchRequest)
	 */
	@Override
	public SearchResponse searchFeatured(SearchRequest searchRequest) throws RemoteException, AppException {
		LOGGER.info("Entering searchFeatured()");
		LOGGER.debug("SearchRequest: " + searchRequest);
		SearchResponse searchResponse = new SearchResponse();
		
		final GetFeaturedContentRequestBodyType request = new GetFeaturedContentRequestBodyType();
		request.setApplicationID(AppID);
		request.setLocale(Locale);
		request.setUsername(searchRequest.getUsername());
		request.setPassword(searchRequest.getPassword());
		request.setVersion("");

		// Setup the page# and size# (if any)
		final ControlData controlData = new ControlData();
		int startIndex = (searchRequest.getPage().intValue() - 1) * searchRequest.getSize().intValue();
		controlData.setPaginationStartIndex(BigInteger.valueOf(startIndex));
		controlData.setMaxNumberOfUnitsPerGroup(new BigInteger(MaxNumberOfUnitsPerGroup));
		controlData.setMaxNumberOfGroupResults(searchRequest.getSize());
		controlData.setSpellCheckEnabled(SpellCheckEnabled);
		request.setControlData(controlData);

		// Do the search and get results
		Instant start = Instant.now();
		final GetFeaturedContentResponseBodyType response = SearchPortType.getFeaturedContent(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE - searchFeatured() duration: " + Duration.between(start, end).toMillis() + "ms");		
		if (response != null && response.getResponse() != null) {
			searchResponse = populateResponse(response.getResponse(), searchResponse);
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.FEATURED_CONTENT_ERROR,  
					AppErrorMessage.FEATURED_CONTENT_ERROR);
		}
		LOGGER.debug("SearchResponse: " + searchResponse);
		LOGGER.info("Exiting searchFeatured()");
		return searchResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#searchTopContent(com.verint.services.km.model.SearchRequest)
	 */
	@Override
	public SearchResponse searchTopContent(SearchRequest searchRequest) throws RemoteException, AppException {
		LOGGER.info("Entering searchTopContent()");
		LOGGER.debug("SearchRequest: " + searchRequest);
		SearchResponse searchResponse = new SearchResponse();
		final GetTopContentRequestBodyType request = new GetTopContentRequestBodyType();
		request.setApplicationID(AppID);
		request.setLocale(Locale);
		request.setUsername(searchRequest.getUsername());
		request.setPassword(searchRequest.getPassword());
		request.setMaxNumberOfTopContent(searchRequest.getSize());
		request.setToDate("");
		request.setFromDate("");

		// Do the search and get results
		Instant start = Instant.now();
		final GetTopContentResponseBodyType response = SearchPortType.getTopContent(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+searchRequest.getUsername()+") - searchTopContent() duration: " + Duration.between(start, end).toMillis() + "ms");
		if (response != null && response.getResponse() != null) {
			searchResponse = populateResponse(response.getResponse(), searchResponse);
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.TOP_CONTENT_ERROR,  
					AppErrorMessage.TOP_CONTENT_ERROR);
		}
		LOGGER.debug("SearchResponse: " + searchResponse);
		LOGGER.info("Exiting searchTopContent()");
		return searchResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#searchSuggestions(com.verint.services.km.model.SearchRequest)
	 */
	@Override
	public SearchResponse searchSuggestions(SearchRequest searchRequest) throws RemoteException, AppException {
		LOGGER.info("Entering searchSuggestions()");
		final SharedTextSearchRequestBodyType request = new SharedTextSearchRequestBodyType();
		SearchResponse searchResponse = new SearchResponse();
		request.setApplicationID(AppID);
		request.setLocale(Locale);
		request.setUsername(searchRequest.getUsername());
		request.setPassword(searchRequest.getPassword());
		request.setSearchText(searchRequest.getQuery());
		request.setContentCategory(searchRequest.getCategories());
		request.setTags(searchRequest.getTags());
		request.setSortFieldName(searchRequest.getSort());
		request.setContentOwner("");
		request.setPublishedId("");
		request.setWorkflowState("");
		final SearchDateType sdt = new SearchDateType();
		sdt.setToDate("");
		sdt.setFromDate("");
		request.setExpirationDate(sdt);
		request.setModifiedDate(sdt);
		request.setPublishedDate(sdt);

		// Call the search
		Instant start = Instant.now();
		final SharedTextSearchResponseBodyType response = SearchPortType.sharedTextSearch(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+searchRequest.getUsername()+") - searchSuggestions() duration: " + Duration.between(start, end).toMillis() + "ms");
		if (response != null && response.getResponse() != null) {
			// Valid response
			final KnowledgeResultSet resultSet = response.getResponse();
			// Populate Suggested Query
			searchResponse = populateSuggestedQuery(resultSet, searchResponse);
		} else {
			// We have a problem with the service
			// We already have one, send necessary error message
			throw new AppException(500, AppErrorCodes.SEARCH_SUGGESTION_ERROR,  
					AppErrorMessage.SEARCH_SUGGESTION_ERROR);
		}
		LOGGER.info("Entering searchSuggestions()");
		return searchResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#searchBookmarks(com.verint.services.km.model.SearchRequest)
	 */
	@Override
	public SearchResponse searchBookmarks(SearchRequest searchRequest) throws RemoteException, AppException {
		LOGGER.info("Entering searchBookmarks()");
		LOGGER.debug("SearchRequest: " + searchRequest);
		

		final SearchResponse searchResponse = new SearchResponse();
		final ListAllBookmarksRequestBodyType request = new ListAllBookmarksRequestBodyType();
		request.setApplicationID(AppID);
		request.setUserName(searchRequest.getUsername());
		request.setPassword(searchRequest.getPassword());
		request.setSortColumnName("");
		request.setSortOrder("");
		
		final List<com.verint.services.km.model.KnowledgeGroupUnit> knowledgeGroupUnits = new ArrayList<com.verint.services.km.model.KnowledgeGroupUnit>();
		Instant start = Instant.now();
		
		
		
		final ListAllBookmarksResponseBodyType response = KMBookmarkServicePortType.listAllBookmarks(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+searchRequest.getUsername()+") - searchBookmarks() duration: " + Duration.between(start, end).toMillis() + "ms");

		// Check for valid response
		if (response != null && response.getContentList() != null) {
			final BookmarkedContent[] content = response.getContentList();
			for (int x = 0; (content != null) && (x < content.length); x++) {
				final com.verint.services.km.model.KnowledgeGroupUnit responseKgu = new com.verint.services.km.model.KnowledgeGroupUnit();
				responseKgu.setContentID(content[x].getContentId());
				responseKgu.setContentType(content[x].getContentType());
				responseKgu.setLocale(content[x].getLocaleName());
				responseKgu.setTitle(content[x].getTitle());
				responseKgu.setIsFeatured(content[x].isIsFeatured());

				final List<com.verint.services.km.model.KnowledgeUnit> knowledgeUnits = new ArrayList<com.verint.services.km.model.KnowledgeUnit>();
				final com.verint.services.km.model.KnowledgeUnit responseKu = new com.verint.services.km.model.KnowledgeUnit();
				final Date dt = content[x].getCreatedDate();
				responseKu.setLastPublishedDate(dt.toString());
				responseKu.setSynopsis(content[x].getSynopsis());
				responseKu.setWorkflowState("PUBLISHED");
	
				// Setup the content type info
				final Set<Tag> rTags = new HashSet<Tag>();
				final Tag tag = new Tag();
				tag.setSystemTagName(content[x].getContentType());
				rTags.add(tag);
				responseKu.setContentCategoryTags(rTags);
				// content[x].getSequenceNumber();

				knowledgeUnits.add(responseKu);
				final Set<com.verint.services.km.model.KnowledgeUnit> setKnowledgeUnits = new LinkedHashSet<com.verint.services.km.model.KnowledgeUnit>(knowledgeUnits);
				responseKgu.setKnowledgeUnits(setKnowledgeUnits);
				knowledgeGroupUnits.add(responseKgu);
			}
			// Set the knowledge group units
			final Set<com.verint.services.km.model.KnowledgeGroupUnit> setKnowledgeGroupUnits = new LinkedHashSet<com.verint.services.km.model.KnowledgeGroupUnit>(knowledgeGroupUnits);
			searchResponse.setKnowledgeGroupUnits(setKnowledgeGroupUnits);
			BigInteger numResults = BigInteger.valueOf(0);
			if (content != null) {
				numResults = BigInteger.valueOf(content.length);
			}
			// Include the number of results
			searchResponse.setNumberOfResults(numResults);
		} else {
			// We have a problem with the service
			// We already have one, send necessary error message
			throw new AppException(500, AppErrorCodes.BOOKMARKS_ERROR,  
					AppErrorMessage.BOOKMARKS_ERROR);
		}

		LOGGER.debug("SearchResponse: " + searchResponse);
		LOGGER.info("Exiting searchBookmarks()");
		return searchResponse;
	}

	/**
	 * 
	 * @param resultSet
	 * @param searchResponse
	 * @return
	 */
	private SearchResponse populateResponse(KnowledgeResultSet resultSet, SearchResponse searchResponse) {
		LOGGER.info("Entering populateResponse()");
		// Get the response information
		searchResponse.setNumberOfResults(resultSet.getNumberOfResults());
		final KnowledgeGroupUnit[] kgu = resultSet.getKnowledgeGroupUnits();

		final List<com.verint.services.km.model.KnowledgeGroupUnit> knowledgeGroupUnits = new ArrayList<com.verint.services.km.model.KnowledgeGroupUnit>();
		if (kgu != null && kgu.length > 0) {
			for (KnowledgeGroupUnit knowledgeGroupUnit : kgu) {
				com.verint.services.km.model.KnowledgeGroupUnit responseKgu = new com.verint.services.km.model.KnowledgeGroupUnit();
				responseKgu.setAverageRating(new Double(knowledgeGroupUnit.getRatingInformation().getAverageRating()));
				responseKgu.setContentID(knowledgeGroupUnit.getContentID());
				responseKgu.setContentSource(knowledgeGroupUnit.getContentSource());
				responseKgu.setContentType(knowledgeGroupUnit.getContentType());
				responseKgu.setIsFeatured(knowledgeGroupUnit.isIsFeatured());
				responseKgu.setLocale(knowledgeGroupUnit.getLocale());
				responseKgu.setNumberOfRatings(knowledgeGroupUnit.getRatingInformation().getNumberOfRatings());
				responseKgu.setTitle(knowledgeGroupUnit.getTitle());
				responseKgu.setViewCount(knowledgeGroupUnit.getViewCount());
				
				List<com.verint.services.km.model.KnowledgeUnit> knowledgeUnits = new ArrayList<com.verint.services.km.model.KnowledgeUnit>();
				final KnowledgeUnit[] ku = knowledgeGroupUnit.getKnowledgeUnits();
				for (KnowledgeUnit knowledgeUnit : ku) {
					final com.verint.services.km.model.KnowledgeUnit responseKu = new com.verint.services.km.model.KnowledgeUnit();
					responseKu.setAssociatedContentURL(knowledgeUnit.getAssociatedContentURL());
					responseKu.setContentOwner(knowledgeUnit.getContentOwner());
					responseKu.setContentUnitID(knowledgeUnit.getContentUnitID());
					responseKu.setContentVersion(knowledgeUnit.getContentVersion());
					responseKu.setLastModifiedDate(knowledgeUnit.getLastModifiedDate());
					String pubDate = knowledgeUnit.getLastPublishedDate();
					if (pubDate != null && pubDate.length() > 0) {
						int index = pubDate.indexOf("T");
						if (index != -1) {
							pubDate = pubDate.substring(0, index);
							responseKu.setLastPublishedDate(pubDate);						
						}
					} else {
						String modDate = knowledgeUnit.getLastModifiedDate();
						if (modDate != null && modDate.length() > 0) {
							int index = modDate.indexOf("T");
							if (index != -1) {
								modDate = modDate.substring(0, index);
								responseKu.setLastPublishedDate(modDate);						
							}
						}
					}
					responseKu.setPageIdentifier(knowledgeUnit.getPageIdentifier());
					responseKu.setPublishedID(knowledgeUnit.getPublishedID());
					responseKu.setSynopsis(knowledgeUnit.getSynopsis());
					responseKu.setTitle(knowledgeUnit.getTitle());
					responseKu.setWorkflowState(knowledgeUnit.getWorkflowState());
					responseKu.setContentCategoryTags(parseForTags(knowledgeUnit.getContentCategoryTags()));
					responseKu.setTags(parseForTags(knowledgeUnit.getTags()));
					responseKu.setRelevanceScore(new Double(knowledgeUnit.getRelevanceScore()));
					knowledgeUnits.add(responseKu);
				}
				// Set the knowledge units
				final Set<com.verint.services.km.model.KnowledgeUnit> setKnowledgeUnits = new LinkedHashSet<com.verint.services.km.model.KnowledgeUnit>(knowledgeUnits);
				responseKgu.setKnowledgeUnits(setKnowledgeUnits);
				knowledgeGroupUnits.add(responseKgu);
			}
		}
		// Set the knowledge group units
		final Set<com.verint.services.km.model.KnowledgeGroupUnit> setKnowledgeGroupUnits = new LinkedHashSet<com.verint.services.km.model.KnowledgeGroupUnit>(knowledgeGroupUnits);
		searchResponse.setKnowledgeGroupUnits(setKnowledgeGroupUnits);
		
		LOGGER.info("Entering populateResponse()");
		return searchResponse;
	}
	
	
	
	/**
	 * 
	 * @param resultSet
	 * @param searchResponse
	 * @return
	 */
	private SearchResponse populateSuggestedQuery(KnowledgeResultSet resultSet, SearchResponse searchResponse) {
		LOGGER.info("Entering populateSuggestedQuery()");
		// Setup the Suggest Query
		final SuggestedQuery[] suggestedQuery = resultSet.getSuggestedQueryList();
		final List<com.verint.services.km.model.SuggestedQuery> suggestedQueries = new ArrayList<com.verint.services.km.model.SuggestedQuery>();
		for (int x = 0; (suggestedQuery != null) && (x < suggestedQuery.length); x++) {
			final com.verint.services.km.model.SuggestedQuery sq = new com.verint.services.km.model.SuggestedQuery();
			sq.setNumberOfResults(suggestedQuery[x].getNumberOfGroupResultsFound());
			sq.setQueryText(suggestedQuery[x].getQueryText());
			sq.setType(suggestedQuery[x].getType());
			sq.setSearchPrecision(suggestedQuery[x].getSearchPrecision());
			final ReplacedTerm[] rt = suggestedQuery[x].getReplacedTermsList();
			final List<com.verint.services.km.model.ReplacedTerm> replacedTerms = new ArrayList<com.verint.services.km.model.ReplacedTerm>();
			for (int y = 0; (rt != null) && (y < rt.length); y++) {
				final com.verint.services.km.model.ReplacedTerm replacedTerm = new com.verint.services.km.model.ReplacedTerm();
				replacedTerm.setReplacement(rt[y].getReplacement());
				replacedTerm.setTerm(rt[y].getTerm());
				replacedTerms.add(replacedTerm);
			}
			final Set<com.verint.services.km.model.ReplacedTerm> setReplacedTerms = new LinkedHashSet<com.verint.services.km.model.ReplacedTerm>(replacedTerms);
			sq.setReplacedTerms(setReplacedTerms);

			final KnowledgeGroupUnit[] skgu = suggestedQuery[x].getKnowledgeGroupUnits();
			final List<com.verint.services.km.model.KnowledgeGroupUnit> knowledgeGroupUnits2 = new ArrayList<com.verint.services.km.model.KnowledgeGroupUnit>();
			for (int z = 0; (skgu != null) && (z < skgu.length); z++) {
				final com.verint.services.km.model.KnowledgeGroupUnit nKnowledgeGroupUnit = new com.verint.services.km.model.KnowledgeGroupUnit();
				nKnowledgeGroupUnit.setContentID(skgu[z].getContentID());
				nKnowledgeGroupUnit.setContentSource(skgu[z].getContentSource());
				nKnowledgeGroupUnit.setContentType(skgu[z].getContentType());
				nKnowledgeGroupUnit.setLocale(skgu[z].getLocale());
				nKnowledgeGroupUnit.setTitle(skgu[z].getTitle());
				nKnowledgeGroupUnit.setViewCount(skgu[z].getViewCount());
				final KnowledgeUnit[] fku = skgu[z].getKnowledgeUnits();
				final List<com.verint.services.km.model.KnowledgeUnit> knowledgeUnit2 = new ArrayList<com.verint.services.km.model.KnowledgeUnit>();
				for (int a = 0; (fku != null) && (a < fku.length); a++) {
					final com.verint.services.km.model.KnowledgeUnit nKnowledgeUnit = new com.verint.services.km.model.KnowledgeUnit();
					nKnowledgeUnit.setAssociatedContentURL(fku[a].getAssociatedContentURL());
					nKnowledgeUnit.setContentOwner(fku[a].getContentOwner());
					nKnowledgeUnit.setContentUnitID(fku[a].getContentUnitID());
					nKnowledgeUnit.setContentVersion(fku[a].getContentVersion());
					nKnowledgeUnit.setLastModifiedDate(fku[a].getLastModifiedDate());
					nKnowledgeUnit.setLastPublishedDate(fku[a].getLastPublishedDate());
					nKnowledgeUnit.setPageIdentifier(fku[a].getPageIdentifier());
					nKnowledgeUnit.setPublishedID(fku[a].getPublishedID());
					nKnowledgeUnit.setRelevanceScore(new Double(fku[a].getRelevanceScore()));
					nKnowledgeUnit.setSynopsis(fku[a].getSynopsis());
					nKnowledgeUnit.setContentCategoryTags(parseForTags(fku[a].getContentCategoryTags()));
					nKnowledgeUnit.setTags(parseForTags(fku[a].getTags()));
					nKnowledgeUnit.setTitle(fku[a].getTitle());
					nKnowledgeUnit.setWorkflowState(fku[a].getWorkflowState());
					knowledgeUnit2.add(nKnowledgeUnit);
				}
				final Set<com.verint.services.km.model.KnowledgeUnit> setKnowledgeUnit2 = new LinkedHashSet<com.verint.services.km.model.KnowledgeUnit>(knowledgeUnit2);
				nKnowledgeGroupUnit.setKnowledgeUnits(setKnowledgeUnit2);

				final RatingInformation ratingInfo = skgu[z].getRatingInformation();
				if (ratingInfo != null) {
					nKnowledgeGroupUnit.setAverageRating(new Double(ratingInfo.getAverageRating()));
				}
				knowledgeGroupUnits2.add(nKnowledgeGroupUnit);
			}
			final Set<com.verint.services.km.model.KnowledgeGroupUnit> setKnowledgeGroupUnits2 = new LinkedHashSet<com.verint.services.km.model.KnowledgeGroupUnit>(knowledgeGroupUnits2);
			sq.setKnowledgeGroupUnits(setKnowledgeGroupUnits2);
			suggestedQueries.add(sq);
		}
		final Set<com.verint.services.km.model.SuggestedQuery> setSuggestedQueries = new LinkedHashSet<com.verint.services.km.model.SuggestedQuery>(suggestedQueries);
		searchResponse.setSuggestedQueries(setSuggestedQueries);

		LOGGER.info("Exiting populateSuggestedQuery()");
		return searchResponse;
	}

	private SearchResponse populateRestSuggestedQuery(RestSearchResponse restResponse, SearchResponse searchResponse) {
		LOGGER.info("Entering populateSuggestedQuery()");
		// Setup the Suggest Query
		final List<com.verint.services.km.model.SuggestedQuery> suggestedQueries = new ArrayList<>();
		List<SearchAlternative> restAlternative = restResponse.getAlternative();
		if (restAlternative != null && !restAlternative.isEmpty()) {
			for (int i = 0; i < restAlternative.size(); i++) {
				final com.verint.services.km.model.SuggestedQuery sq = new com.verint.services.km.model.SuggestedQuery();
				sq.setQueryText(restAlternative.get(0).getSearchCriteria().getQuery());
				sq.setType("REPLACED_SPECIFIC_TERMS");
				suggestedQueries.add(sq);
			}
		}

		List<SearchExpanded> restSuggestions = restResponse.getExpanded();
		for (int x = 0; (restSuggestions != null) && (x < restSuggestions.size()); x++) {
			final com.verint.services.km.model.SuggestedQuery sq = new com.verint.services.km.model.SuggestedQuery();
			sq.setNumberOfResults(BigInteger.valueOf(restSuggestions.get(x).getTotalItems()));
			sq.setQueryText(restSuggestions.get(x).getSearchCriteria().getQuery());
			sq.setType(restSuggestions.get(x).getType().get(0));
//			sq.setSearchPrecision(suggestedQuery[x].getSearchPrecision());
//			final ReplacedTerm[] rt = suggestedQuery[x].getReplacedTermsList();
//			final List<com.verint.services.km.model.ReplacedTerm> replacedTerms = new ArrayList<com.verint.services.km.model.ReplacedTerm>();
//			for (int y = 0; (rt != null) && (y < rt.length); y++) {
//				final com.verint.services.km.model.ReplacedTerm replacedTerm = new com.verint.services.km.model.ReplacedTerm();
//				replacedTerm.setReplacement(rt[y].getReplacement());
//				replacedTerm.setTerm(rt[y].getTerm());
//				replacedTerms.add(replacedTerm);
//			}
//			final Set<com.verint.services.km.model.ReplacedTerm> setReplacedTerms = new LinkedHashSet<com.verint.services.km.model.ReplacedTerm>(replacedTerms);
//			sq.setReplacedTerms(setReplacedTerms);

			final List<HydraMember> extraResults = restSuggestions.get(x).getHydraMember();
			final List<com.verint.services.km.model.KnowledgeGroupUnit> knowledgeGroupUnits2 = new ArrayList<com.verint.services.km.model.KnowledgeGroupUnit>();
			for (int z = 0; (extraResults != null) && (z < extraResults.size()); z++) {
				final com.verint.services.km.model.KnowledgeGroupUnit nKnowledgeGroupUnit = new com.verint.services.km.model.KnowledgeGroupUnit();
				MemberDetails restExtraResult = extraResults.get(0).getMemberDetails().get(0);
				nKnowledgeGroupUnit.setContentID(RestUtil.getContentIdFromUrl(restExtraResult.getUrl(), restExtraResult.getCategory().get(0).getId()));
//				nKnowledgeGroupUnit.setContentSource(skgu[z].getContentSource());
				nKnowledgeGroupUnit.setContentType(RestUtil.convertContentRestType(restExtraResult.getCategory().get(0).getId()));
				nKnowledgeGroupUnit.setLocale(restExtraResult.getInLanguage());
				nKnowledgeGroupUnit.setTitle(restExtraResult.getName());
				nKnowledgeGroupUnit.setViewCount(restExtraResult.getUserInteractionCount());
				final List<com.verint.services.km.model.KnowledgeUnit> knowledgeUnit2 = new ArrayList<>();
				final com.verint.services.km.model.KnowledgeUnit nKnowledgeUnit = new com.verint.services.km.model.KnowledgeUnit();
//				nKnowledgeUnit.setAssociatedContentURL(fku[a].getAssociatedContentURL());
//				nKnowledgeUnit.setContentOwner(fku[a].getContentOwner());
//				nKnowledgeUnit.setContentUnitID(RestUtil.getContentIdFromUrl(restExtraResult.getUrl()));
				nKnowledgeUnit.setContentVersion(restExtraResult.getVersion());
				nKnowledgeUnit.setLastModifiedDate(restExtraResult.getDateModified());
				nKnowledgeUnit.setLastPublishedDate(restExtraResult.getDatePublished());
//				nKnowledgeUnit.setPageIdentifier(fku[a].getPageIdentifier());
//				nKnowledgeUnit.setPublishedID(fku[a].getPublishedID());
//				nKnowledgeUnit.setRelevanceScore(new Double(fku[a].getRelevanceScore()));
				nKnowledgeUnit.setSynopsis(restExtraResult.getDescription());
				nKnowledgeUnit.setContentCategoryTags(parseForTags(RestUtil.convertContentRestType(restExtraResult.getCategory().get(0).getId())));
//				nKnowledgeUnit.setTags(parseForTags(fku[a].getTags()));
				nKnowledgeUnit.setTitle(restExtraResult.getName());
				nKnowledgeUnit.setWorkflowState("PUBLISHED");
				knowledgeUnit2.add(nKnowledgeUnit);
				final Set<com.verint.services.km.model.KnowledgeUnit> setKnowledgeUnit2 = new LinkedHashSet<>(knowledgeUnit2);
				nKnowledgeGroupUnit.setKnowledgeUnits(setKnowledgeUnit2);

				if (restExtraResult.getAggregateRating() != null) {
					nKnowledgeGroupUnit.setAverageRating(Double.parseDouble(restExtraResult.getAggregateRating().getRatingValue()));
				}
				knowledgeGroupUnits2.add(nKnowledgeGroupUnit);
			}
			final Set<com.verint.services.km.model.KnowledgeGroupUnit> setKnowledgeGroupUnits2 = new LinkedHashSet<com.verint.services.km.model.KnowledgeGroupUnit>(knowledgeGroupUnits2);
			sq.setKnowledgeGroupUnits(setKnowledgeGroupUnits2);
			suggestedQueries.add(sq);
		}
		final Set<com.verint.services.km.model.SuggestedQuery> setSuggestedQueries = new LinkedHashSet<>(suggestedQueries);
		searchResponse.setSuggestedQueries(setSuggestedQueries);

		LOGGER.info("Exiting populateSuggestedQuery()");
		return searchResponse;
	}


	/**
	 * 
	 * @param cTags
	 * @return
	 */
	private Set<Tag> parseForTags(String cTags) {
		LOGGER.info("Entering parseForTags()");
		final Set<Tag> rTags = new HashSet<Tag>();
		// Content Tags
		if (cTags != null && cTags.length() > 0) {
			final StringTokenizer st = new StringTokenizer(cTags, ",");
			while (st.hasMoreElements()) {
				final Tag tag = new Tag();
				tag.setSystemTagName((String)st.nextElement());
				rTags.add(tag);
			}
		}
		LOGGER.info("Exiting parseForTags()");
		return rTags;
	}
	
	// Build rest API response
	private SearchResponse populateRESTApiResponse(RestSearchResponse searchJsonResponse, SearchResponse searchResponse) {
		LOGGER.info("Entering populateRESTApiResponse()");
		final List<com.verint.services.km.model.KnowledgeGroupUnit> knowledgeGroupUnits = new ArrayList<com.verint.services.km.model.KnowledgeGroupUnit>();

		searchResponse.setNumberOfResults(searchJsonResponse.getTotalItems());
		searchResponse.setSearchFeedbackURL(searchJsonResponse.getSearchFeedbackURL());
		
		LOGGER.info("Search Response number of results = " + searchResponse.getNumberOfResults());
		
		List<HydraMember> resultsList = searchJsonResponse.getHydraMember();
		
		LOGGER.info("Search resultsList size = " + (resultsList == null ? 0 : resultsList.size()));

		if (resultsList != null && resultsList.size() > 0) {  
			for (int i = 0; i < resultsList.size(); i++) {
				com.verint.services.km.model.KnowledgeGroupUnit restResponseKgu = new com.verint.services.km.model.KnowledgeGroupUnit();
				List<com.verint.services.km.model.KnowledgeUnit> knowledgeUnits = new ArrayList<com.verint.services.km.model.KnowledgeUnit>();
				

				//LOGGER.info("In resultList loop"); 
				restResponseKgu.setTitle(resultsList.get(i).getName());
				restResponseKgu.setContentID(resultsList.get(i).getContentId());
				
				List<MemberDetails> detailsList = resultsList.get(i).getMemberDetails();
				if (detailsList != null) {
					//LOGGER.info("detailsList size() = " + detailsList.size());  
					
					if (!detailsList.isEmpty()) {
						for (int j = 0; j < detailsList.size(); j++) {
							final com.verint.services.km.model.KnowledgeUnit restResponseKu = new com.verint.services.km.model.KnowledgeUnit();
							MemberDetails memberDetails = detailsList.get(j);

							// Set details
							//restResponseKgu
							restResponseKgu.setIsFeatured(memberDetails.isFeatured());
							restResponseKgu.setLocale(memberDetails.getInLanguage());
							restResponseKgu.setViewCount(memberDetails.getUserInteractionCount());
							if (memberDetails.getAggregateRating() != null) {
								restResponseKgu.setAverageRating(new Double(memberDetails.getAggregateRating().getRatingValue()));
							}
														
							//restResponseKu
							restResponseKu.setLastModifiedDate(memberDetails.getDateModified());
							String pubDate = memberDetails.getDatePublished();
							if (pubDate != null && pubDate.length() > 0) {
								int index = pubDate.indexOf("T");
								if (index != -1) {
									pubDate = pubDate.substring(0, index);
									restResponseKu.setLastPublishedDate(pubDate);						
								}
							} else {
								String modDate = memberDetails.getDateModified();
								if (modDate != null && modDate.length() > 0) {
									int index = modDate.indexOf("T");
									if (index != -1) {
										modDate = modDate.substring(0, index);
										restResponseKu.setLastPublishedDate(modDate);						
									}
								}
							}
							
							restResponseKu.setRelevanceScore(new Double(memberDetails.getRelevance()));
							restResponseKu.setTitle(memberDetails.getName());
							restResponseKu.setContentVersion(memberDetails.getVersion());
							restResponseKu.setSynopsis(memberDetails.getDescription());
							restResponseKu.setAssociatedContentURL(memberDetails.getUrl());
							
							
							
							// results list but go in knowledgeUnit
							restResponseKu.setPublishedID(resultsList.get(i).getIdentifier());
							restResponseKu.setContentUnitID(resultsList.get(i).getContentId());
							
							//Hard coded as not returned from REST API call and is needed for display.
							restResponseKu.setWorkflowState("PUBLISHED");
							
							
							////////////////////////////////////
							//not returned in REST API and not needed.
							//restResponseKu.setPageIdentifier("1.0"); 
							//restResponseKu.setTags(parseForTags("kbase_test,search_showinsearch,newchange_neworchanged"));
							//restResponseKu.setContentOwner("UserED.80004_666_-1");
							//restResponseKgu.setContentSource("Authored");
							//BigInteger noOfRatings = new BigInteger("0");
							//restResponseKgu.setNumberOfRatings(noOfRatings);
							////////////////////////////////////
							
							//category list
							if (memberDetails.getCategory() != null) {
								List<Category> categoryList = memberDetails.getCategory();
								if (categoryList != null && categoryList.size() > 0) { 
									for (int k = 0; k < categoryList.size(); k++) {
										restResponseKgu.setContentType(categoryList.get(k).getDisplayName());
										restResponseKu.setContentCategoryTags(parseForTags(categoryList.get(k).getContentCategoryTag()));
									}
								}
							}

							//Highlight words in title and description
							if (memberDetails.getAnnotations() != null && !memberDetails.getAnnotations().isEmpty()) {
								List<String[]> textReplaceList = new ArrayList<>();
								for (Annotation anno : memberDetails.getAnnotations()) {
									if ("oa:highlighting".equals(anno.getMotivation())) {
										int replaceStart = -1;
										int replaceEnd = -1;
										String path = null;
										for (AnnotationSelector selector : anno.getTarget().getSelector()) {
											if (selector.getType() != null) {
												if ("vkm:PropertyPathSelector".equals(selector.getType().get(0))) {
													path = selector.getPath().get(0).getId();
												} else if ("oa:TextPositionSelector".equals(selector.getType().get(0))) {
													replaceStart = selector.getStart();
													replaceEnd = selector.getEnd();
												}
											}
										}
										if (replaceStart != -1 && replaceEnd != -1 && path != null) {
											textReplaceList.add(new String[]{replaceStart + "", replaceEnd + "", path});
										}
									}
								}
								textReplaceList.sort(Comparator.comparingInt(o -> Integer.parseInt(o[0])));
								Collections.reverse(textReplaceList);
								for (String[] textReplace : textReplaceList) {
									if ("vkm:name".equals(textReplace[2])) {
										String newTitle = restResponseKu.getTitle();
										newTitle = newTitle.substring(0, Integer.parseInt(textReplace[0])) +
												"<strong>" + newTitle.substring(Integer.parseInt(textReplace[0]), Integer.parseInt(textReplace[1])) +
												"</strong>" + newTitle.substring(Integer.parseInt(textReplace[1]));
										restResponseKu.setTitle(newTitle);
										restResponseKgu.setTitle(newTitle);
									} else if ("vkm:description".equals(textReplace[2])) {
										String newDescription = restResponseKu.getSynopsis();
										newDescription = newDescription.substring(0, Integer.parseInt(textReplace[0])) +
												"<strong>" + newDescription.substring(Integer.parseInt(textReplace[0]), Integer.parseInt(textReplace[1])) +
												"</strong>" + newDescription.substring(Integer.parseInt(textReplace[1]));
										restResponseKu.setSynopsis(newDescription);
									}
								}
							}

							// Set the knowledge group units
							knowledgeUnits.add(restResponseKu);
						}			
					}
				}
				
				// Set the knowledge group units
				final Set<com.verint.services.km.model.KnowledgeUnit> setKnowledgeUnits = new LinkedHashSet<com.verint.services.km.model.KnowledgeUnit>(knowledgeUnits);
				restResponseKgu.setKnowledgeUnits(setKnowledgeUnits);
				knowledgeGroupUnits.add(restResponseKgu);
			}
		}
		
		
		// Set the knowledge group units
		final Set<com.verint.services.km.model.KnowledgeGroupUnit> setKnowledgeGroupUnits = new LinkedHashSet<com.verint.services.km.model.KnowledgeGroupUnit>(knowledgeGroupUnits);
		searchResponse.setKnowledgeGroupUnits(setKnowledgeGroupUnits);
		
		LOGGER.info("Leaving populateRESTApiResponse()");
		
		return searchResponse;
	}
	
	
	private String buildRestAPIRequest(SearchRequest searchRequest, Float searchPrecision, Boolean isFeatured, Boolean isBookmark, String externalSearchId) {
		
		// initalise variables
		String restAPIRequest = null;
		String customCategories = null;
		String categorySpideredContent = "content_spidereddocument";
		String sp = null;
		String language = null;
		String orderBy = null;
		String orderdirection = null;
		String size = null;
		String featured = null;
		String bookmarked = null;
		String start = null;
		ConfigInfo prop = new ConfigInfo();
	
		// set URL (this will need to be config not hard coded)
		restAPIRequest = REST_SEARCH_URL + "/default/search?";
		
		// set the search query
		String query = "query=" + searchRequest.getQuery();
		if (searchRequest.getPublishedId() != "") {
			if (!searchRequest.getQuery().equals("*") && !searchRequest.getQuery().equals("*")) {
			//query = "query=" + searchRequest.getPublishedId() + " AND " + searchRequest.getQuery();
				//This does not work in the Test API call any additional text to a KM number brings back 0 results
				query = "query=" + searchRequest.getPublishedId();
			} else {
				query = "query=" + searchRequest.getPublishedId();
			}
			LOGGER.info("BuildRestAPIRequest PublishedId = " + searchRequest.getPublishedId() + " Adding to search text: " + query);
		}
		
		restAPIRequest = restAPIRequest + query;
		
		// set tags
		restAPIRequest = addRestTagsToURL(restAPIRequest, searchRequest);
		
		// set categories
		String categories = searchRequest.getCategories();
		
		if (categories != null && categories.length() > 0) {
		
			// If categories has a , at the end of the string then remove it.
			if (categories.charAt(categories.length() - 1) == ',') {
				categories = categories.substring(0, categories.length() - 1);
			}
			categories = "&category=" + String.join("&category=", categories.split(","));
			categories = categories.replace("category=content_spidereddocument","category=vkm:SpideredCategory");
			restAPIRequest = restAPIRequest + categories;
        
		}
		
		// Search Precision
		// If search precision is passed in as a parameter then use it, is not, use the system property.
		if (searchPrecision != null) {
			sp ="&sp=" + searchPrecision;
			
		}else {
			searchPrecision = Float.parseFloat(prop.getsearchPrecision()); 
			sp ="&sp=" + searchPrecision;
		}
		restAPIRequest = restAPIRequest + sp;
		
		// Language (locale)
		if (Locale != null) {
			language = "&lang=" + Locale;
			restAPIRequest = restAPIRequest + language;
		} 
		
		// Order by
		orderdirection = "&orderdirection=vkm:ItemListOrderDescending";
		if ("publishedDate".equals(searchRequest.getSort())) {
			orderBy = "&orderby=vkm:datePublished";
		} else {
			orderBy = "&orderby=vkm:relevance";
		}
		restAPIRequest = restAPIRequest + orderBy;
		restAPIRequest = restAPIRequest + orderdirection;


		
		// Size
		if(searchRequest.getSize() != null) {
			size = "&size=" + searchRequest.getSize();
			restAPIRequest = restAPIRequest + size;
		}
		
		// featured
		if(isFeatured) {
			featured = "&featured=vkm:MatchOnly";
			restAPIRequest = restAPIRequest + featured;
		}
		
		// bookmark
		if(isBookmark) {
			bookmarked = "&bookmarked=vkm:MatchOnly";
			restAPIRequest = restAPIRequest + bookmarked;
		} 
		
		//page start item number
		LOGGER.info("BuildRestAPIRequest page number = " + searchRequest.getPage());
		int pageStart = searchRequest.getPage().intValue() - 1;
		pageStart = pageStart * searchRequest.getSize().intValue();
		LOGGER.info("BuildRestAPIRequest pageStart = " + pageStart);
		start = "&start=" + Integer.toString(pageStart);
		LOGGER.info("BuildRestAPIRequest start = " + start);
		restAPIRequest = restAPIRequest + start;
		
		
		//entitlements


		//externalSearchId
		restAPIRequest = restAPIRequest + "&externalSearchId=" + externalSearchId;

        LOGGER.info(" restAPIRequest = " + restAPIRequest); 
        LOGGER.info("Exiting BuildRestAPIRequest"); 
		
		
		return restAPIRequest;
	}

	private String addRestTagsToURL(String restAPIRequest, SearchRequest searchRequest) {
		LOGGER.info("BuildRestAPIRequest tags = {0}" + searchRequest.getTags());
		String[] tags = (String[]) Arrays.stream(searchRequest.getTags().split(","))
				.map(tag -> convertTagToCoverageTag(tag, searchRequest))
				.toArray(String[]::new);
		String tagUrlString = "&tag=" + String.join("&tag=", tags);
		return restAPIRequest + tagUrlString;
	}

	private String convertTagToCoverageTag(String tag, SearchRequest searchRequest) {
		if (tag.startsWith("kbase_") || tag.startsWith("search_")) {
			return tag;
		}
		RestTag restTag = getTag(searchRequest.getUsername(), searchRequest.getOidcToken(), tag);
		if (restTag != null && !StringUtils.isEmpty(restTag.getCoverage())) {
			return restTag.getCoverage();
		}
		return tag;
	}


	private RestTag getTag(String username, String oidcToken, String systemTagName) {
		Instant start = Instant.now();
		RestTag tagJsonResponse = null;
		try {
			tagJsonResponse = RestUtil.getAndDeserialize(REST_TAGS_URL + "/default/tag/" + systemTagName,null,
					HttpMethod.GET, RestTag.class, oidcToken, null, false);
		} catch (IOException e) {
			LOGGER.error("Error during getTag()", e);
		}
		Instant end = Instant.now();
		LOGGER.debug("getTag SERVICE_CALL_PERFORMANCE(" + username + ", " + systemTagName + ") - duration: " + Duration.between(start, end).toMillis() + "ms");

		return tagJsonResponse;
	}
}
