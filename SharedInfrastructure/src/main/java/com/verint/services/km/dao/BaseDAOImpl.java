/**
 * 
 */
package com.verint.services.km.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1BindingStub;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1PortType;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1ServiceLocator;
import com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1BindingStub;
import com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1PortType;
import com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1ServiceLocator;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1BindingStub;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1PortType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1ServiceLocator;
import com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1BindingStub;
import com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1PortType;
import com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1ServiceLocator;
import com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1BindingStub;
import com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1PortType;
import com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1ServiceLocator;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1BindingStub;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1PortType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1ServiceLocator;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1BindingStub;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1PortType;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1ServiceLocator;

/**
 * @author jmiller
 *
 */
public class BaseDAOImpl  {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAOImpl.class);
	protected static String AppID;
	protected static String Locale;
	protected static Boolean SpellCheckEnabled;
	protected static String MaxNumberOfUnitsPerGroup;
	protected static SearchV1PortType SearchPortType;
	protected static KMBookmarkServiceV1PortType KMBookmarkServicePortType;
	protected static ContentV1PortType ContentPortType;
	protected static TagV1PortType TagPortType;
	protected static RequestAnswerV1PortType RequestAnswerPortType;
	protected static FeedbackV1PortType FeedbackPortType;
	protected static LoginV1PortType LoginPortType;
	protected static int SOAP_TIMEOUT = 20;

	static {
		try {
			final String OSName = System.getProperty("os.name");
			LOGGER.debug("OSName: " + OSName);
			String fileLocation = "/opt/kmservices/";
			if (OSName != null && OSName.length() > 0) {
				if (OSName.startsWith("Windows")) {
					fileLocation = "C:\\opt\\kmservices\\";
				}
			}

			// Get the properties
			final Properties prop = new Properties();
			LOGGER.debug("FileLocation: " + fileLocation + "soapConnection.properties");
	        final InputStream in = new FileInputStream(fileLocation + "soapConnection.properties");
	        prop.load(in);
	        in.close();
	        
	        final String SearchV1Port_address = prop.getProperty("soap.searchservice");
	        final String ContentV1Port_address = prop.getProperty("soap.contentservice");
	        final String TagV1Port_address = prop.getProperty("soap.taggingservice");
	        final String BookmarkV1Port_address = prop.getProperty("soap.bookmarkservice");
	        final String RequestAnswerV1Port_address = prop.getProperty("soap.requestanswer");
	        final String FeedbackV1Port_address = prop.getProperty("soap.feedback");
	        final String LoginV1Port_address = prop.getProperty("soap.login");
	        
	        AppID = prop.getProperty("soap.appid");
	        Locale = prop.getProperty("soap.locale");
	        MaxNumberOfUnitsPerGroup = prop.getProperty("soap.maxnumberofunitspergroup");
	        SpellCheckEnabled = Boolean.valueOf(prop.getProperty("soap.spellcheckenabled"));

	        // Search Service
			final SearchV1ServiceLocator searchServiceLocator = new SearchV1ServiceLocator();
			SearchPortType = searchServiceLocator.getSearchV1Port(new URL(SearchV1Port_address));
			SearchV1BindingStub searchBinding = (SearchV1BindingStub) SearchPortType;
			searchBinding.setTimeout(SOAP_TIMEOUT * 1000);
			
			// Bookmark Service
			final KMBookmarkServiceV1ServiceLocator bookmarkServiceLocator = new KMBookmarkServiceV1ServiceLocator();
			KMBookmarkServicePortType = bookmarkServiceLocator.getKMBookmarkServiceV1Port(new URL(BookmarkV1Port_address));
			KMBookmarkServiceV1BindingStub bookmarkBinding = (KMBookmarkServiceV1BindingStub) KMBookmarkServicePortType;
			bookmarkBinding.setTimeout(SOAP_TIMEOUT * 1000);
			
			// Content Service
			final ContentV1ServiceLocator contentServiceLocator = new ContentV1ServiceLocator(); 
			ContentPortType = contentServiceLocator.getContentV1Port(new URL(ContentV1Port_address));
			ContentV1BindingStub contentBinding = (ContentV1BindingStub) ContentPortType;
			contentBinding.setTimeout(SOAP_TIMEOUT * 1000);

			// Tag Service
			final TagV1ServiceLocator tagServiceLocator = new TagV1ServiceLocator(); 
			TagPortType = tagServiceLocator.getTagV1Port(new URL(TagV1Port_address));
			TagV1BindingStub tagBinding = (TagV1BindingStub) TagPortType;
			tagBinding.setTimeout(SOAP_TIMEOUT * 1000);
			
			// RequestAnswer Service
			final RequestAnswerV1ServiceLocator requestAnswerServiceLocator = new RequestAnswerV1ServiceLocator();
			RequestAnswerPortType = requestAnswerServiceLocator.getRequestAnswerV1Port(new URL(RequestAnswerV1Port_address));
			RequestAnswerV1BindingStub requestAnswerBinding = (RequestAnswerV1BindingStub) RequestAnswerPortType;
			requestAnswerBinding.setTimeout(SOAP_TIMEOUT * 1000);
			
			// Feedback Service
			final FeedbackV1ServiceLocator feedbackServiceLocator = new FeedbackV1ServiceLocator();
			FeedbackPortType = feedbackServiceLocator.getFeedbackV1Port(new URL(FeedbackV1Port_address));
			FeedbackV1BindingStub feedbackBinding = (FeedbackV1BindingStub) FeedbackPortType;
			feedbackBinding.setTimeout(SOAP_TIMEOUT * 1000);

			// Login Service
			final LoginV1ServiceLocator loginServiceLocator = new LoginV1ServiceLocator();
			LoginPortType = loginServiceLocator.getLoginV1Port(new URL(LoginV1Port_address));
			LoginV1BindingStub loginBinding = (LoginV1BindingStub) LoginPortType;
			loginBinding.setTimeout(SOAP_TIMEOUT * 1000);

		} catch (FileNotFoundException fnfe) {
			LOGGER.error("FileNotFoundException", fnfe);
			System.exit(1);
		} catch (IOException ioe) {
			LOGGER.error("IOException", ioe);
			System.exit(1);
		} catch (ServiceException se) {
			LOGGER.error("ServiceException", se);
			System.exit(1);
		} catch (Throwable t) {
			LOGGER.error("Throwable Exception", t);
			System.exit(1);
		}
	}

	/**
	 * 
	 */
	public BaseDAOImpl() {
		super();
		LOGGER.info("Entering BaseDAOImpl()");
		LOGGER.info("Exiting BaseDAOImpl()");
	}

	/**
	 * @return the sOAP_TIMEOUT
	 */
	public static int getSOAP_TIMEOUT() {
		return SOAP_TIMEOUT;
	}

	/**
	 * @param sOAP_TIMEOUT the sOAP_TIMEOUT to set
	 */
	public static void setSOAP_TIMEOUT(int sOAP_TIMEOUT) {
		SOAP_TIMEOUT = sOAP_TIMEOUT;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
}