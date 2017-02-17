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

import com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginUserRequestBodyType;
import com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginUserResponseBodyType;
import com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2PortType;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.LoginV2Request;
import com.verint.services.km.model.LoginV2Response;

/**
 * @author jmiller
 *
 */
@Repository
public class LoginV2DAOImpl extends BaseDAOImpl implements LoginV2DAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginV2DAOImpl.class);

	/**
	 * 
	 */
	public LoginV2DAOImpl() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.LoginDAO#login(com.verint.services.km.model.LoginRequest)
	 */
	@Override
	public LoginV2Response login(LoginV2Request request) throws RemoteException, AppException {
		LOGGER.info("Entering loginV2()");
		LOGGER.debug("LoginV2Request: " + request);
		final LoginV2Response response = new LoginV2Response();

		final LoginUserRequestBodyType body = new LoginUserRequestBodyType();
		body.setUsername(request.getUsername());
		body.setPassword(request.getPassword());
		body.setFirstName(request.getFirstName());
		body.setLastName(request.getLastName());
		body.setVemGroups(request.getVemGroups());
		
		Instant start = Instant.now();
		final LoginUserResponseBodyType responseBody = LoginV2PortType.loginUser(body);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + body.getUsername() + ") - loginV2() duration: " + Duration.between(start, end).toMillis() + "ms");

		if (responseBody != null && responseBody.getLoginResponse() != null) {
			com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginResponse lResponse = responseBody.getLoginResponse();
			response.setUsername(request.getUsername());
			response.setDisplayName(lResponse.getDisplayName());
			response.setExpires(lResponse.getExpires());
			response.setFirstName(lResponse.getFirstName());
			response.setFullName(lResponse.getFullName());
			response.setGrace(lResponse.getGrace());
			response.setLastName(lResponse.getLastName());
			response.setLocale(lResponse.getLocale());
			response.setMessage(lResponse.getMessage());
			response.setLoginResult(lResponse.getLoginResult());
			response.setIsDeleted(lResponse.isIsDeleted());
			response.setIsDisabled(lResponse.isIsDisabled());
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.LOGIN_ERROR,
					AppErrorMessage.LOGIN_ERROR);
		}
		LOGGER.debug("LoginV2Response: " + response);
		LOGGER.info("Exiting loginV2()");
		return response;
	}


}