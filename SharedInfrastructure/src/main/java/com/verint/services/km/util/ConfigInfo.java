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
	
	//SOAP Properties
	private String soapSearchservice="";
	private String soapContentservice="";
	private String soapTaggingservice="";
	private String soapBookmarkservice="";
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
	
	private String staticcontentFilelocation="";
	private String staticcontentServerurl="";
	
	private String solrAutosuggestURI = "";
	
	private String connectionPoolFile;
	private String systemFile;
	private String readerFile;
	


	public ConfigInfo() {
		
		final String OSName = System.getProperty("os.name");
		connectionPoolFile = getConfigLocation() + "/" + getEnvironmentName() + "/connectionPool.properties";
		systemFile = getConfigLocation() + "/" + getEnvironmentName() + "/" + getMachineName() + "/" + getContainerName() + "/system.properties";
		readerFile = getConfigLocation() + "/" + getEnvironmentName() + "/propertyReader.properties";
		
		if (OSName != null && OSName.length() > 0 && OSName.startsWith("Windows")) {
			connectionPoolFile = getConfigLocation() + "\\" + getEnvironmentName() + "\\connectionPool.properties";
			systemFile = getConfigLocation() + "\\" + getEnvironmentName() + "\\" + getMachineName() + "\\" + getContainerName() + "\\system.properties";
			readerFile = getConfigLocation() + "\\" + getEnvironmentName() + "\\propertyReader.properties";
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
				
				//Read the system.properties
				final InputStream inSystem = new FileInputStream(systemFile);
				prop.load(inSystem);
				inSystem.close();
				
				soapSearchservice= prop.getProperty("soap.searchservice");
				soapContentservice= prop.getProperty("soap.contentservice");
				soapTaggingservice= prop.getProperty("soap.taggingservice");
				soapBookmarkservice= prop.getProperty("soap.bookmarkservice");
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
					
				staticcontentFilelocation= prop.getProperty("staticcontent.filelocation");
				staticcontentServerurl= prop.getProperty("staticcontent.serverurl");
				
				solrAutosuggestURI = prop.getProperty("solr.autosuggestURI");
				
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
	public String getsolrAutosuggestURI() {
		return solrAutosuggestURI;
	}
	public String getSystemFile() {
		return systemFile;
	}
	public String getReaderFile() {
		return readerFile;
	}

	public String getConnectionPoolFile() {
		return connectionPoolFile;
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
		"solr.autosuggestURI=" + solrAutosuggestURI;
	}
	
}
