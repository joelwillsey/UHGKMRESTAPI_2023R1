/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.GetNewOrChangedContentResponseBodyType;
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
	public void suggestContent(RequestAnswerRequest requestAnswer) throws RemoteException, AppException {
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

		// Call the service
		Instant start = Instant.now();
		final RequestAnswerResponseBodyType response = RequestAnswerPortType.requestAnswer(request);
		Instant end = Instant.now();
		LOGGER.debug("Service Call Performance("+requestAnswer.getUsername()+") - suggestContent() duration: " + Duration.between(start, end).toMillis() + "ms");

		LOGGER.debug("RequestAnswerResponseBodyType: " + response);
		LOGGER.info("Exiting suggestContent()");
	}
}