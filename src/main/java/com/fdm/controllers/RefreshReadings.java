package com.fdm.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import com.fdm.dao.ReadingDAO;
import com.fdm.dao.ReadingDAOJPAImpl;
import com.fdm.model.Reading;
import com.fdm.parsers.ReadingsParser;
import com.fdm.parsers.ReadingsXMLParser;

public class RefreshReadings {
	
	private final static String psiUrl = "http://api.nea.gov.sg/api/WebAPI/?dataset=psi_update&keyref=781CF461BB6606AD1260F4D81345157FE21D05A9F8ACBCAF";
	private final static String pm25Url = "http://api.nea.gov.sg/api/WebAPI/?dataset=pm2.5_update&keyref=781CF461BB6606AD1260F4D81345157FE21D05A9F8ACBCAF";
	private static Logger logger = LogManager.getLogger(RefreshReadings.class);
	
	public void runParser(EntityManagerFactory emf) {
		this.parse(psiUrl, emf);
		this.parse(pm25Url, emf);
		logger.trace("Completed parsing");
	}
	
	private void parse(String url, EntityManagerFactory emf) {
		InputStream is = null;
		DocumentBuilderFactory dbFactory = null;
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			is = new URL(url).openStream();
			dbFactory = DocumentBuilderFactory.newInstance();;

		} catch (MalformedURLException e) {
			logger.error("Malformed URL", e.toString() );
		} catch (IOException e) {
			logger.error("IOException ", e.toString() );
		}
		ReadingsParser readingsParser = new ReadingsXMLParser(is, dbFactory);
		List<Reading> readingsList = new ArrayList<Reading>();
		readingsList = readingsParser.getReadings();
		
		
		for(Reading reading : readingsList) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			ReadingDAO readingDao = new ReadingDAOJPAImpl(em);
			readingDao.addReading(reading);
			tx.commit();
			em.close();
		}
	}
}
