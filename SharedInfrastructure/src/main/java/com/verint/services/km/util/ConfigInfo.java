package com.verint.services.km.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConfigInfo {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInfo.class);
	
	
	//Connection
	private String connectionDriverclassname = "";		
	private String connectionUrl="";
	private String connectionUsername="";
	private String connectionPassword="";
	private String connectionMaxtotal="2";
	private String connectionMaxidle="1";
	
	//ConnectionRS
	private String connectionRSDriverclassname = "";		
	private String connectionRSUrl="";
	private String connectionRSUsername="";
	private String connectionRSPassword="";
	private String connectionRSMaxtotal="2";
	private String connectionRSMaxidle="1";
	//SOAP Properties
	private String soapSearchservice="";
	private String soapContentservice="";
	private String soapTaggingservice="";
	private String soapBookmarkservice="";
	private String soapBookmarkserviceV2="";
	private String soapRequestanswer="";
	private String soapFeedback="";
	private String soapLogin="";
	private String soapLoginV2="";
	private String soapNeworchangedservice="";
	private String soapFeaturedservice="";
	private String soapAppid="AD";
	private String soapLocale="en-US";
	private String soapMaxnumberofunitspergroup="10";
	private String soapSpellcheckenabled="true";
	//Rest Services
	private String oidcTokenService="";
	private String agentService="";
	private String kmAssetService="";
	private String kmBookmarkService="";
	private String kmContentService="";
	private String kmSearchService="";
	private String kmTagService="";
	private String kmAuthoringService="";
	private String tenantId="";	
	private String oidcTokenScope;
	private String hoverTestServiceAccountUser;
	private String hoverTestServiceAccountPassword;
	private boolean convertTagServiceToHTTP = false;
	
	private String staticcontentFilelocation="";
	private String staticcontentServerurl="";
	
	private String assetWrapperUrl="";
	
	private String solrAutosuggestURI = "";
	private String alertsNumDays = "";
	private String searchPrecision = "";
	private String searchTriggerType = "";
	private String sortOrder = "";
	
	private String connectionPoolFile;
	private String systemFile;
	private String readerFile;
	private String connectionPoolRSFile;

	private String sslkeystoreLocation;
	private String sslkeystorePassword;


	public ConfigInfo() {
		
		final String OSName = System.getProperty("os.name");
		//connectionPoolFile = getConfigLocation() + "/" + getEnvironmentName() + "/connectionPool.properties";
		//connectionPoolRSFile = getConfigLocation() + "/" + getEnvironmentName() + "/connectionPoolRS.properties";
		//systemFile = getConfigLocation() + "/" + getEnvironmentName() + "/" + getMachineName() + "/" + getContainerName() + "/system.properties";
		//readerFile = getConfigLocation() + "/" + getEnvironmentName() + "/propertyReader.properties";
		
		connectionPoolFile = getConfigLocation() + "/connectionPool.properties";
		connectionPoolRSFile = getConfigLocation() + "/connectionPoolRS.properties";
		systemFile = getConfigLocation() + "/system.properties";
		readerFile = getConfigLocation() + "/propertyReader.properties";
		
		if (OSName != null && OSName.length() > 0 && OSName.startsWith("Windows")) {
			//connectionPoolFile = getConfigLocation() + "\\" + getEnvironmentName() + "\\connectionPool.properties";
			//connectionPoolRSFile = getConfigLocation() + "\\" + getEnvironmentName() + "\\connectionPoolRS.properties";
			//systemFile = getConfigLocation() + "\\" + getEnvironmentName() + "\\" + getMachineName() + "\\" + getContainerName() + "\\system.properties";
			//readerFile = getConfigLocation() + "\\" + getEnvironmentName() + "\\propertyReader.properties";
			
			connectionPoolFile = getConfigLocation() + "\\connectionPool.properties";
			connectionPoolRSFile = getConfigLocation() + "\\connectionPoolRS.properties";
			systemFile = getConfigLocation()  + "\\system.properties";
			readerFile = getConfigLocation() + "\\propertyReader.properties";
		}

		// Get the properties
		try {
				final Properties prop = new Properties();
				
				//Read the connectionPool.properties
				final InputStream inConnection = new FileInputStream(connectionPoolFile);
				prop.load(inConnection);
				inConnection.close();

				connectionDriverclassname = prop.getProperty("connection.driverclassname");		
				connectionUrl= prop.getProperty("connection.url");
				connectionUsername= prop.getProperty("connection.username");
				connectionPassword= prop.getProperty("connection.password");
				connectionMaxtotal= prop.getProperty("connection.maxtotal");
				connectionMaxidle= prop.getProperty("connection.maxidle");
				
				final InputStream inConnectionRS = new FileInputStream(connectionPoolRSFile);
				prop.load(inConnectionRS);
				inConnectionRS.close();

				connectionRSDriverclassname = prop.getProperty("connection.driverclassname");		
				connectionRSUrl= prop.getProperty("connection.url");
				connectionRSUsername= prop.getProperty("connection.username");
				connectionRSPassword= prop.getProperty("connection.password");
				connectionRSMaxtotal= prop.getProperty("connection.maxtotal");
				connectionRSMaxidle= prop.getProperty("connection.maxidle");
				
				//Read the system.properties
				final InputStream inSystem = new FileInputStream(systemFile);
				prop.load(inSystem);
				inSystem.close();
				
				soapSearchservice= prop.getProperty("soap.searchservice");
				soapContentservice= prop.getProperty("soap.contentservice");
				soapTaggingservice= prop.getProperty("soap.taggingservice");
				soapBookmarkservice= prop.getProperty("soap.bookmarkservice");
				soapBookmarkserviceV2= prop.getProperty("soap.bookmarkservicev2");
				soapRequestanswer= prop.getProperty("soap.requestanswer");
				soapFeedback= prop.getProperty("soap.feedback");
				soapLogin= prop.getProperty("soap.login");
				soapLoginV2= prop.getProperty("soap.login.v2");
				soapNeworchangedservice= prop.getProperty("soap.neworchangedservice");
				soapFeaturedservice= prop.getProperty("soap.featuredservice");
				soapAppid=prop.getProperty("soap.appid");
				soapLocale=prop.getProperty("soap.locale");
				soapMaxnumberofunitspergroup = prop.getProperty("soap.maxnumberofunitspergroup");
				soapSpellcheckenabled = prop.getProperty("soap.spellcheckenabled");
				
				oidcTokenService=prop.getProperty("rest.odictokenservice");
				agentService=prop.getProperty("rest.agentservice");
				kmAssetService=prop.getProperty("rest.kmassetservice");
				kmBookmarkService=prop.getProperty("rest.kmbookmarkservice");
				kmContentService=prop.getProperty("rest.kmcontentservice");
				kmSearchService=prop.getProperty("rest.kmsearchservice");
				kmTagService=prop.getProperty("rest.kmtagservice");
				convertTagServiceToHTTP = prop.getProperty("rest.kmtagservice.forcehttp") != null;
				kmAuthoringService=prop.getProperty("rest.kmauthoringservice");
				tenantId = prop.getProperty("rest.tentantid");								
				oidcTokenScope=prop.getProperty("rest.odictokenscope");
				hoverTestServiceAccountUser=prop.getProperty("hovertext.serviceaccountuser");
				hoverTestServiceAccountPassword=prop.getProperty("hovertext.serviceaccountpassword");
				staticcontentFilelocation= prop.getProperty("staticcontent.filelocation");
				staticcontentServerurl= prop.getProperty("staticcontent.serverurl");
				
				assetWrapperUrl= prop.getProperty("asset.wrapperurl");
				
				solrAutosuggestURI = prop.getProperty("solr.autosuggestURI");
				alertsNumDays=prop.getProperty("alerts.numDays");
				searchPrecision=prop.getProperty("search.precision");
				searchTriggerType = prop.getProperty("search.TriggerType");
				sortOrder=prop.getProperty("sort.order");

				sslkeystoreLocation=prop.getProperty("ssl.keystore.location");
				sslkeystorePassword=prop.getProperty("ssl.keystore.password");


			} catch (FileNotFoundException fnfe) {
				LOGGER.error("FileNotFoundException", fnfe);
				System.exit(1);
			} catch (IOException ioe) {
				LOGGER.error("IOException", ioe);
				System.exit(1);
			} catch (Throwable t) {
				LOGGER.error("Throwable Exception", t);
				System.exit(1);
			} 
	}
	
	//getters
	public String getConnectionDriverclassname() {
		return connectionDriverclassname;
	}
	public String getConnectionUrl() {
		return connectionUrl;
	}
	public String getConnectionUsername() {
		return connectionUsername;
	}
	public String getConnectionPassword() {
		return connectionPassword;
	}
	public String getConnectionMaxtotal() {
		return connectionMaxtotal;
	}
	public String getConnectionMaxidle() {
		return connectionMaxidle;
	}
	
	
	
	public String getConnectionRSDriverclassname() {
		return connectionRSDriverclassname;
	}
	public String getConnectionRSUrl() {
		return connectionRSUrl;
	}
	public String getConnectionRSUsername() {
		return connectionRSUsername;
	}
	public String getConnectionRSPassword() {
		return connectionRSPassword;
	}
	public String getConnectionRSMaxtotal() {
		return connectionRSMaxtotal;
	}
	public String getConnectionRSMaxidle() {
		return connectionRSMaxidle;
	}
	
	
		
	public String getSoapSearchservice() {
		return soapSearchservice;
	}
	public String getSoapContentservice() {
		return soapContentservice;
	}
	public String getSoapTaggingservice() {
		return soapTaggingservice;
	}
	public String getSoapBookmarkservice() {
		return soapBookmarkservice;
	}
	public String getSoapBookmarkserviceV2() {
		return soapBookmarkserviceV2;
	}
	public String getSoapRequestanswer() {
		return soapRequestanswer;
	}
	public String getSoapFeedback() {
		return soapFeedback;
	}
	public String getSoapLogin() {
		return soapLogin;
	}
	public String getSoapLoginV2() {
		return soapLoginV2;
	}
	public String getSoapNeworchangedservice() {
		return soapNeworchangedservice;
	}
	public String getSoapFeaturedservice() {
		return soapFeaturedservice;
	}	
	public String getRestOidcTokenService() {
		return oidcTokenService;
	}
	public String getRestAgentService() {
		return agentService;
	}
	public String getRestKmAssetService() {
		return kmAssetService;
	}
	public String getRestKmBookmarkService() {
		return kmBookmarkService;
	}
	public String getRestKmContentService() {
		return kmContentService;
	}
	public String getRestKmSearchService() {
		return kmSearchService;
	}
	public String getRestKmTagService() {
		return kmTagService;
	}
	public boolean getConvertTagServiceToHTTP() {
		return convertTagServiceToHTTP;
	}
	public String getRestTenantId() {
		return tenantId;
	}
	public String getRestKmAuthoringService() {
		return kmAuthoringService;
	}
	public String getSoapAppid() {
		return soapAppid;
	}
	public String getSoapLocale() {
		return soapLocale;
	}
	public String getSoapMaxnumberofunitspergroup() {
		return soapMaxnumberofunitspergroup;
	}
	public String getSoapSpellcheckenabled() {
		return soapSpellcheckenabled;
	}
	public String getStaticcontentFilelocation() {
		return staticcontentFilelocation;
	}
	public String getStaticcontentServerurl() {
		return staticcontentServerurl;
	}
	public String getAssetWrapperUrl() {
		return assetWrapperUrl;
	}
	public String getsolrAutosuggestURI() {
		return solrAutosuggestURI;
	}
	public String getalertsNumDays(){
		return alertsNumDays;
	}
	public String getsearchPrecision(){
		return searchPrecision;
	}
	public String getsearchTriggerType(){
		return searchTriggerType;
	}
	public String getSortOrder(){
		return sortOrder;
	}
	public String getSystemFile() {
		return systemFile;
	}
	public String getReaderFile() {
		return readerFile;
	}
	public String getRestOidcTokenScope() {
		return oidcTokenScope;
	}
	public String getHoverTestServiceAccountUser() {
		return hoverTestServiceAccountUser;
	}
	public String getHoverTestServiceAccountPassword() {
		return hoverTestServiceAccountPassword;
	}
	public String getSslkeystoreLocation() {
		return sslkeystoreLocation;
	}
	public String getSslkeystorePassword() {
		return sslkeystorePassword;
	}

	public String getConnectionPoolFile() {
		return connectionPoolFile;
	}
	public String getConnectionPoolRSFile() {
		return connectionPoolRSFile;
	}
	public String getEnvironmentName() {
		
		String result = "";
		
		Properties property = System.getProperties();
		String environmentName = (String)property.get("environment.name");
	
		result =  "environment." + environmentName;
		
		return result;	
		
	}
	
	public String getContainerName() {
		
		String result = "";
		
		Properties property = System.getProperties();
		String containerName = (String)property.get("container.name");
	
		result =  "container." + containerName;
		
		return result;	
		
	}

	public String getMachineName() {
		
		String result = "";
		
		try {
			Properties property = System.getProperties();
			String machineName = (String)property.get("machine.name");
			
			if (machineName == ""){
				machineName = InetAddress.getLocalHost().getHostName();
			}
			
			result =  "machine." + machineName;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;	
		
	}

	public String getConfigLocation() {
	
	String result = "";
	
	Properties property = System.getProperties();
	String configLocation = (String)property.get("configLocation");

	result =  configLocation;
	
	return result;	
	
	}
	
	//@Override
	public String toString() {
		return 	"connectionPool.properties=" + connectionPoolFile +	"\n" +
		"system.properties=" + systemFile +	"\n" +
		"propertyReader.properties=" + readerFile +	"\n" +
		"connection.driverclassname=" + connectionDriverclassname +	"\n" +	
		"connection.url=" + connectionUrl + "\n" +
		"connection.username=" + connectionUsername + "\n" +
		"connection.password=" + connectionPassword + "\n" +
		"connection.maxtotall=" + connectionMaxtotal + "\n" +
		"connection.maxidle=" + connectionMaxidle + "\n" +
		"soap.searchservice=" + soapSearchservice + "\n" +
		"soap.contentservice=" + soapContentservice + "\n" +
		"soap.taggingservice=" + soapTaggingservice + "\n" +
		"soap.bookmarkservice=" + soapBookmarkservice + "\n" +
		"soap.bookmarkservicev2=" + soapBookmarkserviceV2 + "\n" +
		"soap.requestanswer=" + soapRequestanswer + "\n" +
		"soap.feedback=" + soapFeedback + "\n" +
		"soap.login=" + soapLogin + "\n" +
		"soap.login.v2=" + soapLoginV2 + "\n" +
		"soap.neworchangedservice=" + soapNeworchangedservice + "\n" +
		"soap.featuredservice=" + soapFeaturedservice + "\n" +
		"soap.appid=" + soapAppid + "\n" +
		"soap.locale=" + soapLocale + "\n" +
		"soap.maxnumberofunitspergroup=" + soapMaxnumberofunitspergroup + "\n" +
		"soap.spellcheckenabled=" + soapSpellcheckenabled + "\n" +			
		"staticcontent.filelocation=" + staticcontentFilelocation + "\n" +
		"staticcontent.serverurl=" + staticcontentServerurl	+ "\n" +
		"assetWrapperUrl=" + assetWrapperUrl + "\n" +
		"solr.autosuggestURI=" + solrAutosuggestURI + "\n" +
		"rest.odictokenservice=" + oidcTokenService + "\n" +
		"rest.agentservice=" + agentService + "\n" +
		"rest.kmassetservice=" + kmAssetService + "\n" +
		"rest.kmbookmarkservice=" + kmBookmarkService + "\n" +
		"rest.kmcontentservice=" + kmContentService + "\n" +
		"rest.kmsearchservice=" + kmSearchService + "\n" +
		"rest.kmtagservice=" + kmTagService + "\n" +
		"rest.kmtagservice.forcehttp=" + convertTagServiceToHTTP + "\n" +
		"rest.kmauthoringservice=" + kmAuthoringService + "\n" +
		"rest.tentantid=" + tenantId + "\n" +
		"rest.odictokenscope=" +oidcTokenScope + "\n" +
		"hovertext.serviceaccountuser=" + hoverTestServiceAccountUser + "\n" +
		"hovertext.serviceaccountpassword=********" + "\n" +
		"alerts.numDays=" + alertsNumDays + "\n" +
		"search.precision=" + searchPrecision + "\n" +
		"search.TriggerType=" + searchTriggerType + "\n" +
		"sort.order=" + sortOrder + "\n" +
		"ssl.keystore.location=" + sslkeystoreLocation
		;
	}
	
}
