package com.verint.services.km.fileserving;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.ServletException;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.naming.resources.FileDirContext;
import org.apache.naming.resources.ProxyDirContext;

/**
 * Servlet to serve files from a static directory
 */
public class FileServingServlet extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	private static String FileLocation;

	static {
		try {
			final String OSName = System.getProperty("os.name");
			String fileLocation = "/opt/kmservices/";
			if (OSName != null && OSName.length() > 0) {
				if (OSName.startsWith("Windows")) {
					fileLocation = "C:\\opt\\kmservices\\";
				}
			}

			// Get the properties
			final Properties prop = new Properties();
			final InputStream in = new FileInputStream(fileLocation + "externalFiles.properties");
			prop.load(in);
			in.close();
			FileLocation = prop.getProperty("filelocation");
		} catch (FileNotFoundException fnfe) {
			System.exit(1);
		} catch (IOException ioe) {
			System.exit(1);
		} catch (Throwable t) {
			System.exit(1);
		}
	}

	public void init() throws ServletException {
		super.init();

		final Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(ProxyDirContext.HOST, resources.getHostName());
		env.put(ProxyDirContext.CONTEXT, resources.getContextName());
		final String docBase = FileLocation;

		final FileDirContext context = new FileDirContext(env);
		context.setDocBase(docBase);

		// Load the proxy dir context.
		final Hashtable<String, String> stupid = new Hashtable<String, String>();
		stupid.put(ProxyDirContext.HOST, resources.getHostName());
		stupid.put(ProxyDirContext.CONTEXT, resources.getContextName());
		resources = new ProxyDirContext(stupid, context);

		if (super.debug > 0) {
			log("FileServingServlet:  docBase=" + docBase);
		}
	}
}