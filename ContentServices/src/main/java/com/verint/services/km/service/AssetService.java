package com.verint.services.km.service;

import com.verint.services.km.dao.AssetDAO;
import com.verint.services.km.util.RestUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Path("/asset")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class AssetService extends BaseService {

    private final Logger LOGGER = LoggerFactory.getLogger(AssetService.class);

    @Autowired
    private AssetDAO assetDAO;

    public AssetService() {
        super();
        LOGGER.debug("Entering AssetService()");
		LOGGER.debug("Exiting AssetService()");
    }

    @Path("/getasset")
    @GET
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void getAsset(@QueryParam("assetUrl") String assetUrl,
                    @Context HttpServletRequest httpRequest,
                    @Context HttpServletResponse httpResponse) throws IOException {
        //Bypassing the current rest call because of an embedded issue that decodes paths
        //This causes errors if the filename has special characters in it because the rest service expects them to be encoded
        //Double encoding the request does not work
        //This is caused by:
        //ProtocolExec calls rewriteRequestURI(...)
        //calls request.setURI(URIUtils.rewriteURIForRoute(...));
        //calls URIUtils.rewriteURI(...)
        //calls URIBuilder.parsePath(...)
        //calls urlDecode(...)

//        HttpResponse assetResponse = assetDAO.getAsset(assetUrl,
//                getOIDCToken(httpRequest));
        httpResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        callRestAssetService(assetUrl, getOIDCToken(httpRequest), httpResponse);
    }

    private void callRestAssetService(String assetUrl, String oidcToken, HttpServletResponse response) throws IOException {
        LOGGER.debug("getAsset():  " + assetUrl);
        URL url = new URL(assetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (AutoCloseable closeConnection = connection::disconnect) {
            byte[] body;
            if (connection instanceof HttpsURLConnection) {
                try {
                    HttpsURLConnection secured = (HttpsURLConnection) connection;
                    secured.setHostnameVerifier(NoopHostnameVerifier.INSTANCE);
                    secured.setSSLSocketFactory(RestUtil.getSSLContext().getSocketFactory());
                } catch (Exception e) {
                    LOGGER.debug("Error getting SSL configuration and converting to SSLContext");
                }
            }
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setRequestProperty("Authorization", "OIDC_id_token " + oidcToken);
            connection.setRequestMethod("GET");

            connection.connect();
            Map<String, List<String>> headers = connection.getHeaderFields();
            int status = connection.getResponseCode();
            LOGGER.debug("getAsset - Body--");
            try (InputStream isBody = status > 299 ? connection.getErrorStream() : connection.getInputStream()) {
                body = IOUtils.toByteArray(isBody);
            }
            connection.disconnect();

            LOGGER.debug("getAsset - List All headers--");
            headers.forEach((key, value) -> value.forEach(v -> LOGGER.debug("getAsset Header - {}:{}", key, v)));

            response.setStatus(status);
            setContentType(headers.get("Content-Type"), response);
            setContentDisposition(headers.get("Content-Disposition"), response);
            if (body == null) {
                return;
            }
            if (body.length >=3 && body[0] == -17 && body[1] == -69 && body[2] == -65) { //File starts with UTF-8-BOM character that is not supported
                body = Arrays.copyOfRange(body, 3, body.length);
            }
            response.setContentLength(body.length);
            response.getOutputStream().write(body);
        } catch (Exception e) {
            LOGGER.error("Error during asset proxy connection", e);
        }
    }

    private void setContentType(List<String> contentTypeHeaders, HttpServletResponse response) {
        if (contentTypeHeaders == null || contentTypeHeaders.isEmpty()) {
            LOGGER.debug("getAsset - No Content-Type Header");
            return;
        }
        contentTypeHeaders.forEach(contentType -> response.setHeader("Content-Type", contentType));
    }

    private void setContentDisposition(List<String> dispositionHeaderValues, HttpServletResponse response) {
        if (dispositionHeaderValues == null || dispositionHeaderValues.isEmpty()) {
            LOGGER.debug("getAsset - No Content-Disposition Header");
            return;
        }
        LOGGER.debug("getAsset - Content-Disposition Pre Name: " + dispositionHeaderValues.get(0));
        String dispositionHeader = updateContentDispositionFileName(dispositionHeaderValues.get(0));
        LOGGER.debug("getAsset - Content-Disposition: " + dispositionHeader);
        response.setHeader("Content-Disposition", dispositionHeader);
    }

    private String updateContentDispositionFileName(String contentDispostion) {
    	 int indexStartFileName = contentDispostion.indexOf("filename=\"");
    	 if (indexStartFileName != -1) {
    	     int indexEndFileName = contentDispostion.substring(indexStartFileName + 10).indexOf("\"") + 10 + indexStartFileName;
    	     if (indexEndFileName > indexStartFileName) {
    	         String fileName = contentDispostion.substring(indexStartFileName+10, indexEndFileName);
                 int indexFileNameStartTimeStamp = fileName.indexOf("_Time");
                 int indexFileNameEndTimeStamp = fileName.lastIndexOf(".");
                 if (indexFileNameStartTimeStamp != -1 && indexFileNameEndTimeStamp != -1) {
                     return contentDispostion.substring(0, indexStartFileName+10) +
                             fileName.substring(0, indexFileNameStartTimeStamp) + fileName.substring(indexFileNameEndTimeStamp) +
                             contentDispostion.substring(indexEndFileName);
                 }
             }
    	 }
    	 return contentDispostion;
    }
}
