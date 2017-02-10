package com.verint.services.km.fileserving;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.verint.services.km.util.ConfigInfo;


//@WebServlet("/UploadDownloadFileServlet")
public class FileServingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String FileLocation;

	static {
		try {
			
			ConfigInfo kmConfiguration = new ConfigInfo();
			LOGGER.debug("ConfigInfo: \n" + kmConfiguration.toString());
			FileLocation = kmConfiguration.getStaticcontentFilelocation();
			
		} catch (Throwable t) {
			System.exit(1);
		}
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		uri = uri.replaceFirst(contextPath, "");
		String fileName = FileLocation + uri;
		System.out.println("fileName: " + fileName);
		File file = new File(fileName);
		if(!file.exists()){
			throw new ServletException("File doesn't exists on server.");
		}
		System.out.println("File location on server::" + file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null? mimeType:"application/octet-stream");
		response.setContentLength((int) file.length());
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read=0;
		while((read = fis.read(bufferData))!= -1){
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
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