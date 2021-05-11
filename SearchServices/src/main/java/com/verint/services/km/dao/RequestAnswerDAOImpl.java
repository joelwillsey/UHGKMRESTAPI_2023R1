/**
 * 
 */
package com.verint.services.km.dao;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;

import com.verint.services.km.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerRequestBodyType;
import com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerResponseBodyType;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.RequestAnswerRequest;

/**
 * @author jmiller
 *
 */
@Repository
public class RequestAnswerDAOImpl extends BaseDAOImpl implements RequestAnswerDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestAnswerDAOImpl.class);

	/**
	 * 
	 */
	public RequestAnswerDAOImpl() {
		super();
		LOGGER.info("Entering RequestAnswerDAOImpl()");
		LOGGER.info("Exiting RequestAnswerDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.RequestAnswerDAO#suggestContent(com.verint.services.km.model.RequestAnswerRequest)
	 */
	@Override
	public void suggestContent(RequestAnswerRequest requestAnswer, String oidcToken) throws RemoteException, AppException {
		LOGGER.info("Entering suggestContent()");
		LOGGER.debug("RequestAnswerRequest: " + requestAnswer);

		final RequestAnswerRequestBodyType request = new RequestAnswerRequestBodyType();
		request.setUsername(requestAnswer.getUsername());
		request.setPassword(requestAnswer.getPassword());
		request.setLocale(Locale);
		request.setExpectation(requestAnswer.getExpectation());
		request.setKeyword(requestAnswer.getKeyword());
		request.setSearchDate(requestAnswer.getSearchDate());
		request.setSelectedFilter(requestAnswer.getSelectedFilter());

		/////
		//TODO to convert this to rest we should do two things:
		//1) Pass the Feedback URL from Rest Search Service through the js and as a param into this request so we do not craft it on the fly
		//2) Make sure that jsonInputString is valid, may need to escape " marks
//		String jsonInputString = "{ \"@type\": \"vkm:SearchFeedback\", \"vkm:description\":\"" + requestAnswer.getExpectation();
//		jsonInputString = jsonInputString + "\" }";
//
//		RestUtil.getRestResponse(requestAnswer.getSearchFeedbackURL(), jsonInputString, HttpMethod.POST,
//				String.class, oidcToken, null, true);
		///////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		// Call the service
		Instant start = Instant.now();
		final RequestAnswerResponseBodyType response = RequestAnswerPortType.requestAnswer(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+requestAnswer.getUsername()+") - suggestContent() duration: " + Duration.between(start, end).toMillis() + "ms");

		LOGGER.debug("RequestAnswerResponseBodyType: " + response);
		LOGGER.info("Exiting suggestContent()");
	}
}
