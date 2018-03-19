package com.fdm.runner;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import org.w3c.dom.Document;

import com.fdm.dao.ReadingDAO;
import com.fdm.dao.ReadingDAOJPAImpl;
import com.fdm.model.Reading;
import com.fdm.parsers.ReadingsParser;
import com.fdm.parsers.ReadingsXMLParser;

public class Client {

	private final static Logger logger = LogManager.getLogger(Client.class);
	private static EntityManagerFactory emf ;
	
	public static void main(String[] args) throws Exception {
		emf = Persistence.createEntityManagerFactory("airquality");
//		xmlParser("http://api.nea.gov.sg/api/WebAPI/?dataset=psi_update&keyref=781CF461BB6606AD1260F4D81345157FE21D05A9F8ACBCAF");
//		xmlParser("http://api.nea.gov.sg/api/WebAPI/?dataset=pm2.5_update&keyref=781CF461BB6606AD1260F4D81345157FE21D05A9F8ACBCAF");
//		getRecentReadings();
//		getReadingsByRegion();
		getReading();
		updateReading();
		removeReading();
		getReading();
		addReading();
		emf.close();

	}
	
	private static void addReading() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		ReadingDAO readingDao = new ReadingDAOJPAImpl(em);
		tr.begin();
		Reading reading = new Reading();
		reading.setRegionName("rSO");
		reading.setTimeStamp(Timestamp.valueOf("2018-03-15 14:00:00.0"));
		reading.setType("CO_8HR_MAX");
		reading.setValue(0.45);
		readingDao.addReading(reading);
		tr.commit();
	}
	
	private static void removeReading() {
		EntityManager em = emf.createEntityManager();
		ReadingDAO readingDao = new ReadingDAOJPAImpl(em);
		readingDao.removeReading("rSO", Timestamp.valueOf("2018-03-15 14:00:00.0"), "CO_8HR_MAX");
				
	}

	private static void updateReading() {
		EntityManager em = emf.createEntityManager();
		ReadingDAO readingDao = new ReadingDAOJPAImpl(em);
		Reading reading = readingDao.getReading("rSO", Timestamp.valueOf("2018-03-15 14:00:00.0"), "CO_8HR_MAX");
		if(reading != null) {
			reading.setValue(1);
			readingDao.updateReading(reading);
			logger.trace(reading.toString());
		}
		
	}

	private static void getReading() {
		EntityManager em = emf.createEntityManager();
		ReadingDAO readingDao = new ReadingDAOJPAImpl(em);
		Reading reading = readingDao.getReading("rSO", Timestamp.valueOf("2018-03-15 14:00:00.0"), "CO_8HR_MAX");
		if(reading != null) logger.trace(reading.toString());
		else logger.trace("No reading found");
		
	}
	
	private static void getRecentReadings() {
		EntityManager em = emf.createEntityManager();
		ReadingDAO readingDao = new ReadingDAOJPAImpl(em);
		List<Reading> readingsList = readingDao.getReadingsLatest();
		
		for(Reading reading : readingsList) {
			logger.trace("Got this " + reading.toString());
		}
	}
	
	private static void getReadingsByRegion() {
		EntityManager em = emf.createEntityManager();
		ReadingDAO readingDao = new ReadingDAOJPAImpl(em);
		List<Reading> readingsList = readingDao.getReadingsByRegion("rWE");
		
		for(Reading reading : readingsList) {
			logger.trace("Got this " + reading.toString());
		}
	}
	
	private static void xmlParser(String url) {
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
