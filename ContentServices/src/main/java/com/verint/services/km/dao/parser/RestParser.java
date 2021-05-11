package com.verint.services.km.dao.parser;

import com.verint.services.km.model.ContentResponse;
import com.verint.services.km.model.rest.RestContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;


public class RestParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestParser.class);

    public void parseRestContent(ContentResponse response, RestContentResponse restContentResponse, String ExternalUrl) throws UnsupportedEncodingException {
        //Could refactor mapToContentResponse into this class file
  	
        restContentResponse.mapToContentResponse(response, ExternalUrl);

        if (!StringUtils.isEmpty(response.getPublicAnswer())) {
            response.setPublicAnswer(parseField(response.getPublicAnswer(), response));
        }
        if (!StringUtils.isEmpty(response.getPrivateAnswer())) {
            response.setPrivateAnswer(parseField(response.getPrivateAnswer(), response));
        }
        if (!StringUtils.isEmpty(response.getPublicBody())) {
            response.setPublicBody(parseField(response.getPublicBody(), response));
        }
        if (!StringUtils.isEmpty(response.getPrivateBody())) {
            response.setPrivateBody(parseField(response.getPrivateBody(), response));
        }

    }

    public String parseField(String fieldValue, ContentResponse response) {
        fieldValue = ElementParser.parseInlineContent(fieldValue); //handles Segements with embdded links

        resultParseScriptPlaceHolders scriptPlaceHolders = ElementParser.parseScriptPlaceHolders(fieldValue);        
        fieldValue = scriptPlaceHolders.getData();
        response.getExternalSrcFiles().addAll(scriptPlaceHolders.getExternalSrcFiles());
        fieldValue = ElementParser.parseLinkPlaceHolders(fieldValue);
        return fieldValue;
    }
}
