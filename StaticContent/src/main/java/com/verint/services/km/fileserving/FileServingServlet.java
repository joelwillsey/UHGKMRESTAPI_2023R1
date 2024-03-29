package com.verint.services.km.fileserving;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.verint.services.km.util.ConfigInfo;

//@WebServlet("/UploadDownloadFileServlet")
public class FileServingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(FileServingServlet.class);
	private static String FileLocation;

	static {
		
		ConfigInfo kmConfiguration = new ConfigInfo();
		LOGGER.debug("ConfigInfo: \n" + kmConfiguration.toString());
		FileLocation = kmConfiguration.getStaticcontentFilelocation();
		
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException{
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		LOGGER.info("Entering doGet()");
		ServletOutputStream os = null;
		InputStream fis = null;
		try {
			String uri = request.getRequestURI();
			final String contextPath = request.getContextPath();
			uri = uri.replaceFirst(contextPath, "");
			String uriDecoded = URLDecoder.decode(uri, "UTF-8");
			final String fileName = FileLocation + uriDecoded;
			LOGGER.debug("URI: " + uri);
			LOGGER.debug("FileName: " + fileName);
			final File file = new File(fileName);
			if (!file.exists()) {
				LOGGER.error("File does not exist on server.");
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			final ServletContext ctx = getServletContext();
			fis = new FileInputStream(file);
			final String mimeType = ctx.getMimeType(file.getAbsolutePath());
			response.setContentType(mimeType != null? mimeType:"application/octet-stream");
			response.setContentLength((int) file.length());
			
			os = response.getOutputStream();
			byte[] bufferData = new byte[1024];
			int read=0;
			while((read = fis.read(bufferData))!= -1){
				os.write(bufferData, 0, read);
			}
			os.flush();
		} catch (IOException io) {
			LOGGER.error("IOException", io);
		} finally {
			try {
				if (os != null)
					os.close();
				if (fis != null)
					fis.close();
			} catch (Throwable t) {
				LOGGER.error("Throwable Exception", t);
			}
		}
 		LOGGER.info("Exiting doGet()");
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}