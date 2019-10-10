/**
 * 
 */
package com.verint.services.km.dao;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.time.Instant;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.verint.services.km.model.SearchResponse;
import com.verint.services.km.model.Tag;
import com.verint.services.km.util.ConfigInfo;

/**
 * @author jmiller
 *
 */
@Repository
public class SearchDAOImpl extends BaseDAOImpl implements SearchDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchDAOImpl.class);

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
			SearchResponse searchResponse = searchDAO.searchQuery(searchRequest, null);
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
	public SearchResponse searchQuery(SearchRequest searchRequest, Float searchPrecision) throws RemoteException, AppException {
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
		request.setSortFieldOrder(null);
		      
		// If search precision is passed in as a parameter then use it, is not, use the system property.
		if (searchPrecision != null) {
			request.setSearchPrecision(searchPrecision);
		}else {
			searchPrecision = Float.parseFloat(prop.getsearchPrecision()); 
			request.setSearchPrecision(searchPrecision);
		}
		

		// Setup the page# and size# (if any)
		final ControlData controlData = new ControlData();
		int startIndex = (searchRequest.getPage().intValue() - 1) * searchRequest.getSize().intValue();
		controlData.setPaginationStartIndex(BigInteger.valueOf(startIndex));
		controlData.setMaxNumberOfUnitsPerGroup(new BigInteger(MaxNumberOfUnitsPerGroup));
		controlData.setMaxNumberOfGroupResults(searchRequest.getSize());
		controlData.setSpellCheckEnabled(SpellCheckEnabled);
		request.setControlData(controlData);

		// Call the search
		Instant start = Instant.now();
		final SharedTextSearchResponseBodyType response = SearchPortType.sharedTextSearch(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+searchRequest.getUsername()+") - searchQuery() duration: " + Duration.between(start, end).toMillis() + "ms");
		if (response != null && response.getResponse() != null) {
			// Valid response
			final KnowledgeResultSet resultSet = response.getResponse();
			// Populate Base Response
			searchResponse = populateResponse(resultSet, searchResponse);
			// Populate Suggested Query
			searchResponse = populateSuggestedQuery(resultSet, searchResponse);
		} else {
			// We have a problem with the service
			// We already have one, send necessary error message
			throw new AppException(500, AppErrorCodes.SHARED_TEXT_SEARCH_ERROR,  
					AppErrorMessage.SHARED_TEXT_SEARCH_ERROR);
		}
		LOGGER.debug("SearchResponse: " + searchResponse);
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
}