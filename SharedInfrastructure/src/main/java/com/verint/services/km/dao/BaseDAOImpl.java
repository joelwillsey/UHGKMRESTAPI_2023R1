/**
 * 
 */
package com.verint.services.km.dao;


import java.io.IOException;
import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1BindingStub;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1PortType;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1ServiceLocator;
import com.kana.contactcentre.services.model.FeaturedV1Service_wsdl.FeaturedV1BindingStub;
import com.kana.contactcentre.services.model.FeaturedV1Service_wsdl.FeaturedV1PortType;
import com.kana.contactcentre.services.model.FeaturedV1Service_wsdl.FeaturedV1ServiceLocator;
import com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1BindingStub;
import com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1PortType;
import com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1ServiceLocator;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1BindingStub;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1PortType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1ServiceLocator;
import com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1BindingStub;
import com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1PortType;
import com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1ServiceLocator;
import com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2BindingStub;
import com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2PortType;
import com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2ServiceLocator;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1BindingStub;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1PortType;
import com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1ServiceLocator;
import com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1BindingStub;
import com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1PortType;
import com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1ServiceLocator;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1BindingStub;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1PortType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1ServiceLocator;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1BindingStub;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1PortType;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1ServiceLocator;
import com.verint.services.km.util.ConfigInfo;


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
	protected static NewOrChangedV1PortType NewOrChangedPortType;
	protected static FeaturedV1PortType FeaturedPortType;
	protected static ContentV1PortType ContentPortType;
	protected static TagV1PortType TagPortType;
	protected static RequestAnswerV1PortType RequestAnswerPortType;
	protected static FeedbackV1PortType FeedbackPortType;
	protected static LoginV1PortType LoginPortType;
	protected static LoginV2PortType LoginV2PortType;
	protected static int SOAP_TIMEOUT = 40;
	protected static int SOAP_LOGIN_TIMEOUT = 20;
	protected static int SOAP_FEEDBACK_TIMEOUT = 20;
	protected static int SOAP_REQUEST_ANSWER_TIMEOUT = 20;
	protected static int SOAP_TAG_TIMEOUT = 20;
	protected static int SOAP_CONTENT_TIMEOUT = 40;
	protected static int SOAP_NEW_OR_CHANGED_TIMEOUT = 20;
	protected static int SOAP_FEATURED_TIMEOUT = 20;
	protected static int SOAP_BOOKMARK_TIMEOUT = 20;
	protected static int SOAP_SEARCH_TIMEOUT = 40;
	

	static {
		try {
		
			// Get the properties			
			ConfigInfo kmConfiguration = new ConfigInfo();
			LOGGER.debug("ConfigInfo: \n" + kmConfiguration.toString());
			
	        final String SearchV1Port_address =  kmConfiguration.getSoapSearchservice();
	        final String ContentV1Port_address = kmConfiguration.getSoapContentservice();
	        final String TagV1Port_address = kmConfiguration.getSoapTaggingservice();
	        final String BookmarkV1Port_address = kmConfiguration.getSoapBookmarkservice();
	        final String NewOrChangedV1Port_address = kmConfiguration.getSoapNeworchangedservice();
	        final String FeaturedV1Port_address = kmConfiguration.getSoapFeaturedservice();
	        final String RequestAnswerV1Port_address = kmConfiguration.getSoapRequestanswer();
	        final String FeedbackV1Port_address = kmConfiguration.getSoapFeedback();
	        final String LoginV1Port_address = kmConfiguration.getSoapLogin();
	        final String LoginV2Port_address = kmConfiguration.getSoapLoginV2();
	        
	        AppID = kmConfiguration.getSoapAppid();
	        Locale = kmConfiguration.getSoapLocale();
	        MaxNumberOfUnitsPerGroup = kmConfiguration.getSoapMaxnumberofunitspergroup();
	        SpellCheckEnabled = Boolean.valueOf(kmConfiguration.getSoapSpellcheckenabled());

	        // Search Service
			final SearchV1ServiceLocator searchServiceLocator = new SearchV1ServiceLocator();
			SearchPortType = searchServiceLocator.getSearchV1Port(new URL(SearchV1Port_address));
			SearchV1BindingStub searchBinding = (SearchV1BindingStub) SearchPortType;
			searchBinding.setTimeout(SOAP_SEARCH_TIMEOUT * 1000);
			
			// Bookmark Service
			final KMBookmarkServiceV1ServiceLocator bookmarkServiceLocator = new KMBookmarkServiceV1ServiceLocator();
			KMBookmarkServicePortType = bookmarkServiceLocator.getKMBookmarkServiceV1Port(new URL(BookmarkV1Port_address));
			KMBookmarkServiceV1BindingStub bookmarkBinding = (KMBookmarkServiceV1BindingStub) KMBookmarkServicePortType;
			bookmarkBinding.setTimeout(SOAP_BOOKMARK_TIMEOUT * 1000);
			
			// New or Changed Service
			final NewOrChangedV1ServiceLocator newOrChangedServiceLocator = new NewOrChangedV1ServiceLocator();
			NewOrChangedPortType = newOrChangedServiceLocator.getNewOrChangedV1Port(new URL(NewOrChangedV1Port_address));
			NewOrChangedV1BindingStub newOrChangedBinding = (NewOrChangedV1BindingStub) NewOrChangedPortType;
			newOrChangedBinding.setTimeout(SOAP_NEW_OR_CHANGED_TIMEOUT * 1000);
			
			// Featured Service
			final FeaturedV1ServiceLocator featuredServiceLocator = new FeaturedV1ServiceLocator();
			FeaturedPortType = featuredServiceLocator.getFeaturedV1Port(new URL(FeaturedV1Port_address));
			FeaturedV1BindingStub featuredBinding = (FeaturedV1BindingStub) FeaturedPortType;
			featuredBinding.setTimeout(SOAP_FEATURED_TIMEOUT * 1000);
			
			// Content Service
			final ContentV1ServiceLocator contentServiceLocator = new ContentV1ServiceLocator(); 
			ContentPortType = contentServiceLocator.getContentV1Port(new URL(ContentV1Port_address));
			ContentV1BindingStub contentBinding = (ContentV1BindingStub) ContentPortType;
			contentBinding.setTimeout(SOAP_CONTENT_TIMEOUT * 1000);

			// Tag Service
			final TagV1ServiceLocator tagServiceLocator = new TagV1ServiceLocator(); 
			TagPortType = tagServiceLocator.getTagV1Port(new URL(TagV1Port_address));
			TagV1BindingStub tagBinding = (TagV1BindingStub) TagPortType;
			tagBinding.setTimeout(SOAP_TAG_TIMEOUT * 1000);
			
			// RequestAnswer Service
			final RequestAnswerV1ServiceLocator requestAnswerServiceLocator = new RequestAnswerV1ServiceLocator();
			RequestAnswerPortType = requestAnswerServiceLocator.getRequestAnswerV1Port(new URL(RequestAnswerV1Port_address));
			RequestAnswerV1BindingStub requestAnswerBinding = (RequestAnswerV1BindingStub) RequestAnswerPortType;
			requestAnswerBinding.setTimeout(SOAP_REQUEST_ANSWER_TIMEOUT * 1000);
			
			// Feedback Service
			final FeedbackV1ServiceLocator feedbackServiceLocator = new FeedbackV1ServiceLocator();
			FeedbackPortType = feedbackServiceLocator.getFeedbackV1Port(new URL(FeedbackV1Port_address));
			FeedbackV1BindingStub feedbackBinding = (FeedbackV1BindingStub) FeedbackPortType;
			feedbackBinding.setTimeout(SOAP_FEEDBACK_TIMEOUT * 1000);

			// Login Service
			final LoginV1ServiceLocator loginServiceLocator = new LoginV1ServiceLocator();
			LoginPortType = loginServiceLocator.getLoginV1Port(new URL(LoginV1Port_address));
			LoginV1BindingStub loginBinding = (LoginV1BindingStub) LoginPortType;
			loginBinding.setTimeout(SOAP_LOGIN_TIMEOUT * 1000);
			
			// LoginV2 Service
			final LoginV2ServiceLocator loginV2ServiceLocator = new LoginV2ServiceLocator();
			LoginV2PortType = loginV2ServiceLocator.getLoginV2Port(new URL(LoginV2Port_address));
			LoginV2BindingStub loginV2Binding = (LoginV2BindingStub) LoginV2PortType;
			loginBinding.setTimeout(SOAP_LOGIN_TIMEOUT * 1000);

		
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