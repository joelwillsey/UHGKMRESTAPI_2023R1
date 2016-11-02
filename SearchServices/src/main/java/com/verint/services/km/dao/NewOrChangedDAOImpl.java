package com.verint.services.km.dao;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.GetNewOrChangedContentRequestBodyType;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.GetNewOrChangedContentResponseBodyType;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.KnowledgeGroupUnit;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.KnowledgeResultSet;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.KnowledgeUnit;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.RatingInformation;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.ReplacedTerm;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.SuggestedQuery;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.NewOrChangedRequest;
import com.verint.services.km.model.NewOrChangedResponse;
import com.verint.services.km.model.Tag;
/**
 * @author ERaygorodetskiy
 *
 */
@Repository
public class NewOrChangedDAOImpl extends BaseDAOImpl implements NewOrChangedDAO{
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchDAOImpl.class);

	/**
	 * 
	 */
	public NewOrChangedDAOImpl() {
		super();
		LOGGER.info("Entering NewOrChangedDAOImpl()");
		LOGGER.info("Exiting NewOrChangedDAOImpl()");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NewOrChangedDAO newOrChangedDAO = new NewOrChangedDAOImpl();
			NewOrChangedRequest newOrChangedRequest = new NewOrChangedRequest();
			newOrChangedRequest.setUsername("kmagent");
			newOrChangedRequest.setKBaseTags("");
			NewOrChangedResponse newOrChangedResponse = newOrChangedDAO.newOrChangedQuery(newOrChangedRequest);
			LOGGER.info("NewOrChangedResponse", newOrChangedResponse);
			} catch (Throwable t) {
			LOGGER.error("New or Changed exception", t);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.NewOrChangedDAO#newOrChangedQuery(com.verint.services.km.model.NewOrChangedRequest)
	 */
	@Override
	public NewOrChangedResponse newOrChangedQuery(NewOrChangedRequest newOrChangedRequest) throws RemoteException, AppException {
		LOGGER.info("Entering newOrChangedQuery");
		LOGGER.debug("NewOrChangedRequest: " + newOrChangedRequest);
		NewOrChangedResponse newOrChangedResponse = new NewOrChangedResponse();
		
		// Setup the request object to the SOAP call
		final GetNewOrChangedContentRequestBodyType request = new GetNewOrChangedContentRequestBodyType();
		request.setLocale(Locale);
		request.setPassword(newOrChangedRequest.getPassword());
		request.setUsername(newOrChangedRequest.getUsername());
		request.setApplicationID(AppID);
		request.setKbase_tags(newOrChangedRequest.getKBaseTags());
		request.setMaxNumberOfGroupResults(newOrChangedRequest.getMaxNumberOfGroupResults());
		request.setMaxNumberOfUnitsPerGroup(newOrChangedRequest.getMaxNumberOfUnitsPerGroup());
		
		// Call the service
		final GetNewOrChangedContentResponseBodyType response = NewOrChangedPortType.getNewOrChangedContent(request);
		if (response != null && response.getResponse() != null) {
			// Valid response
			final KnowledgeResultSet resultSet = response.getResponse();
			// Populate Base Response
			newOrChangedResponse = populateResponse(resultSet, newOrChangedResponse);
			// Populate Suggested Query
			newOrChangedResponse = populateSuggestedQuery(resultSet, newOrChangedResponse);
		} else {
			// We have a problem with the service
			// We already have one, send necessary error message
			throw new AppException(500, AppErrorCodes.SHARED_TEXT_SEARCH_ERROR,  
					AppErrorMessage.SHARED_TEXT_SEARCH_ERROR);
		}
		LOGGER.debug("NewOrChangedResponse: " + newOrChangedResponse);
		LOGGER.info("Exiting searchKnowledge()");		
		return newOrChangedResponse;
	}
	
	/**
	 * 
	 * @param resultSet
	 * @param newOrChangedResponse
	 * @return
	 */
	private NewOrChangedResponse populateResponse(KnowledgeResultSet resultSet, NewOrChangedResponse newOrChangedResponse){
		LOGGER.info("Entering populateResponse()");
		// Get the response information
		newOrChangedResponse.setNumberOfResults(resultSet.getNumberOfResults());
		final KnowledgeGroupUnit[] kgu = resultSet.getKnowledgeGroupUnits();
		
		final List<com.verint.services.km.model.KnowledgeGroupUnit> knowledgeGroupUnits = new ArrayList<com.verint.services.km.model.KnowledgeGroupUnit>();
		if(kgu != null && kgu.length > 0){
			for(KnowledgeGroupUnit knowledgeGroupUnit : kgu) {
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
		newOrChangedResponse.setKnowledgeGroupUnits(setKnowledgeGroupUnits);
		
		LOGGER.info("Exiting populateResponse()");
		return newOrChangedResponse;
		
	}
	
	/**
	 * 
	 * @param resultSet
	 * @param newOrChangedResponse
	 * @return
	 */
	private NewOrChangedResponse populateSuggestedQuery(KnowledgeResultSet resultSet, NewOrChangedResponse newOrChangedResponse){
		LOGGER.info("Entering populateSuggestedQuery()");
		// Setup the Suggest Query
		final SuggestedQuery[] suggestedQuery = resultSet.getSuggestedQueryList();
		final List<com.verint.services.km.model.SuggestedQuery> suggestedQueries = new ArrayList<com.verint.services.km.model.SuggestedQuery>();
		for (int x = 0; (suggestedQuery != null) && (x < suggestedQuery.length); x++) {
			final com.verint.services.km.model.SuggestedQuery sq = new com.verint.services.km.model.SuggestedQuery();
			sq.setNumberOfResults(suggestedQuery[x].getNumberOfGroupResultsFound());
			sq.setQueryText(suggestedQuery[x].getQueryText());
			sq.setType(suggestedQuery[x].getType());
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
		newOrChangedResponse.setSuggestedQueries(setSuggestedQueries);
		
		LOGGER.info("Exiting populateSuggestedQuery()");
		return newOrChangedResponse;
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
