package com.fdmgroup.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class URLStreamGenerator {

	private static final Logger logger = LogManager.getLogger(URLStreamGenerator.class);
	
	public static InputStream getInputStream(String urlString){
		InputStream inputStream = null;
		try {
			URL url = new URL(urlString);
			inputStream = url.openStream();
			logger.trace("Got Stream");
		} catch (MalformedURLException e) {
			logger.error("Malformed URL Exception thrown", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IO Exception thrown", e);
			e.printStackTrace();
		}

		return inputStream;
	}

}
