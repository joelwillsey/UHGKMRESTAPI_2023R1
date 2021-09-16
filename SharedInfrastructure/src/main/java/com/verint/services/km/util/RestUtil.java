package com.verint.services.km.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.utils.JsonUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.verint.services.km.util.VerintOIDCTokenUtil;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.nio.charset.Charset;
import java.security.KeyStore;

public class RestUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestUtil.class);

    private RestUtil() {

    }

    public static <T> ResponseEntity<T> getRestResponse(String url, String body, HttpMethod httpMethod,
                                                        Class<T> responseType, String oidcToken,
                                                        ResponseErrorHandler errorHandler, boolean urlAlreadyEncoded) {
        if (errorHandler == null) {
            errorHandler = new DefaultResponseErrorHandler();
        }

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        for (HttpMessageConverter converter : restTemplate.getMessageConverters()) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setWriteAcceptCharset(false);
            }
        }
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(getHttpClient()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "OIDC_id_token " + oidcToken);
        headers.add("Accept", "application/ld+json");
        headers.add("Content-Type", "application/ld+json");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<?> entity = new HttpEntity<>(body, headers);
        restTemplate.setErrorHandler(errorHandler);
        return restTemplate.exchange(builder.build(urlAlreadyEncoded).toUri(), httpMethod, entity, responseType);
    }

    public static <T> ResponseEntity<T> getRestResponseAuthCall(String url, String body, HttpMethod httpMethod,
    													Class<T> responseType, ResponseErrorHandler errorHandler) {
		if (errorHandler == null) {
		errorHandler = new DefaultResponseErrorHandler();
		}
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(getHttpClient()));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/ld+json");
		//UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		HttpEntity<?> entity = new HttpEntity<>(body, headers);
		restTemplate.setErrorHandler(errorHandler);
		return restTemplate.exchange(url, httpMethod, entity, responseType);
    }
    
    public static <T> T getAndDeserializeAuthCall(String url, String body, HttpMethod httpMethod, Class<T> classType, ResponseErrorHandler errorHandler) throws IOException {
        ResponseEntity<String> restResponse = RestUtil.getRestResponseAuthCall(url, body, httpMethod, String.class, errorHandler);
        return RestUtil.deserializeResponse(restResponse.getBody(), classType);
    }

    public static <T> T getAndDeserialize(String url, String body, HttpMethod httpMethod, Class<T> classType, String oidcToken, ResponseErrorHandler errorHandler, boolean urlAlreadyEncoded) throws IOException {
    	VerintOIDCTokenUtil oidcTokenObj = new VerintOIDCTokenUtil(oidcToken);
    	LOGGER.debug(httpMethod.name() + " Rest Call Request(" +oidcTokenObj.getSubject() +"): " + url +  " : " + body);
        ResponseEntity<String> restResponse = RestUtil.getRestResponse(url, body, httpMethod, String.class, oidcToken, errorHandler, urlAlreadyEncoded);
        LOGGER.debug(httpMethod.name() + " Rest Call Response(" +oidcTokenObj.getSubject() +"): " + url +  " : " + restResponse.getBody());
        return RestUtil.deserializeResponse(restResponse.getBody(), classType);
    }

    public static <T> T deserializeResponse(String jsonString, Class<T> classType) throws IOException {
        Object jsonObject = JsonUtils.fromString(jsonString);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(JsonUtils.toString(jsonObject), classType);
    }

    public static ResponseErrorHandler getIgnoreErrorHandler() {
        return new ResponseErrorHandler() {

            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                //Do Nothing
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                //Do Nothing
            }
        };
    }

    public static SSLContext getSSLContext() throws Exception {
        ConfigInfo props = new ConfigInfo();
        KeyStore keyStore = KeyStore.getInstance("JKS");
        File key = ResourceUtils.getFile(props.getSslkeystoreLocation());
        try (InputStream in = new FileInputStream(key)) {
            keyStore.load(in, props.getSslkeystorePassword().toCharArray());
        }
        return SSLContextBuilder.create()
                .loadKeyMaterial(keyStore, props.getSslkeystorePassword().toCharArray())
                .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
                .build();
    }

    public static HttpClient getHttpClient() {
        try {
            return HttpClients.custom()
                    .setSSLContext(getSSLContext())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while trying to load keystore details, skipping and trying without.", e);
        }
        return HttpClients.createSystem();
    }

    public static String convertContentRestType(String restType) {
        switch (restType) {
            case "vkm:ArticleContent":
            case "vkm:ArticleCategory":
                return "KnowledgeArticleED";
            case "vkm:AlertContent":
            case "vkm:AlertCategory":
                return "KnowledgeAlertED";
            case "vkm:FAQContent":
            case "vkm:FAQCategory":
                return "KnowledgeFAQED";
            case "vkm:UploadedContent":
            case "vkm:UploadedCategory":
                return "KnowledgeUploadED";
            //case "vkm:RemoteDocumentContent":
            case "content_remotedocument":
            case "vkm:CustomContent":
                return "KnowledgeRemoteDocumentED";
            case "vkm:SpideredContent":
            case "vkm:SpideredCategory":
                return "Spidered";
            case "vkm:DecisionTreeCategory":
            case "vkm:DecisionTreeContent":
                return "pageSet";
            case "content_segment":
                return "Segment";
            default:
                return restType;
        }
    }

    public static String getContentIdFromUrl(String url, String contentType) {
        if ("vkm:DecisionTreeContent".equals(contentType) ||
            "vkm:DecisionTreeCategory".equals(contentType)) {
            return url.substring(url.lastIndexOf("/")+1);
        }
        String[] idArr = url.split("/");
        return idArr[idArr.length-2];
    }
    
    public static String getContentVersionFromUrl(String url) {
    	String  version = "";
    	int parametersIndex = url.indexOf("?");
    	if (parametersIndex > -1) {
    		String[] paramsArr = url.substring(parametersIndex).split("&");
    		
    		for (int i=0; i <= paramsArr.length; i++) {
            	if (paramsArr[i].lastIndexOf("version=") > -1){
            		//found the version parameter
            		int versionIndex = "version=".length()+1;
            		if (paramsArr[i].length() >= versionIndex) {
            			version = paramsArr[i].substring(versionIndex);
            		}
            		break;
            	}
            }
    	}
        
        return version;
    }
}
