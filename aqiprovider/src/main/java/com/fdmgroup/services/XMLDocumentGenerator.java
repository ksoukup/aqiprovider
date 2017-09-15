package com.fdmgroup.services;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLDocumentGenerator {
	private static final Logger logger = LogManager.getLogger(XMLDocumentGenerator.class);
	
	public static Document getDocument(InputStream inputStream){
		Document document = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(inputStream);

		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException thrown", e);
			e.printStackTrace();
		} catch (SAXException e) {
			logger.error("SAXException thrown",  e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IOException thrown", e);
			e.printStackTrace();
		}
		logger.trace("Created UnmarshallXMLService");
		
		return document;
		
	}
	
	

}
