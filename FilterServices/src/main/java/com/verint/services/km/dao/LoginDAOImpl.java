/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginUserRequestBodyType;
import com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginUserResponseBodyType;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.LoginRequest;
import com.verint.services.km.model.LoginResponse;

/**
 * @author jmiller
 *
 */
@Repository
public class LoginDAOImpl extends BaseDAOImpl implements LoginDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginDAOImpl.class);

	/**
	 * 
	 */
	public LoginDAOImpl() {
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
	public LoginResponse login(LoginRequest request) throws RemoteException, AppException {
		LOGGER.info("Entering login()");
		LOGGER.debug("LoginRequest: " + request);
		final LoginResponse response = new LoginResponse();

		final LoginUserRequestBodyType body = new LoginUserRequestBodyType();
		body.setUsername(request.getUsername());
		body.setPassword(request.getPassword());

		Instant start = Instant.now();
		final LoginUserResponseBodyType responseBody = LoginPortType.loginUser(body);
		Instant end = Instant.now();
		LOGGER.debug("SOAP Request->Response - login() duration: " + Duration.between(start, end).toMillis() + "ms");

		if (responseBody != null && responseBody.getLoginResponse() != null) {
			com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginResponse lResponse = responseBody.getLoginResponse();
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
		LOGGER.debug("LoginResponse: " + response);
		LOGGER.info("Exiting login()");
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.LoginDAO#login(com.verint.services.km.model.LoginRequest)
	 */
/*	@Override
	public LoginResponse login(LoginRequest request) {
		LOGGER.info("Entering login()");
		LOGGER.debug("LoginRequest: " + request);
		final LoginResponse response = new LoginResponse();
		Connection connection = null;
		try {
			connection = ConnectionPool.getConnection();
			String sql = "select * from Agent where USERNAME = ?";
			final PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, request.getUsername());
			if (ps != null) {
				String password = null;
				String fullname = null;
				final ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					password = rs.getString("password");
					fullname = rs.getString("fullname");
				}
				rs.close();			
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(request.getPassword().getBytes(StandardCharsets.UTF_8));
				for (int x = 0; x < hash.length; x++) {
					LOGGER.debug("Hash: " + hash[x]);
				}
				LOGGER.debug("Hash: " + Arrays.toString(hash));
				LOGGER.debug("Password: " + password);
				LOGGER.debug("Fullname: " + fullname);
				response.setUsername(request.getUsername());
				response.setFullname(fullname);
				ps.close();
			}
		} catch (NoSuchAlgorithmException nsae) {
			LOGGER.error("NoSuchAlgorithmException", nsae);
		} catch (SQLException se) {
			LOGGER.error("SQLException", se);
		} finally {	
			try {
				connection.close();
			} catch (SQLException se) {
				
			}
		}
		LOGGER.info("Exiting login()");
		return response;
	}
*/
}