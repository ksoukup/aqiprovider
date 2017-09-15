package com.fdmgroup.services;

import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import com.fdmgroup.dao.RecordDaoJpa;
import com.fdmgroup.dao.RegionDaoJpa;
import com.fdmgroup.model.Region;

public class UpdateDataService {
	
	private static final String psiURLString = "http://api.nea.gov.sg/api/WebAPI/?dataset=psi_update&keyref=781CF461BB6606AD1260F4D81345157FE21D05A9F8ACBCAF";
	private static final String pm25URLString = "http://api.nea.gov.sg/api/WebAPI/?dataset=pm2.5_update&keyref=781CF461BB6606AD1260F4D81345157FE21D05A9F8ACBCAF";
	private static final Logger logger = LogManager.getLogger(UpdateDataService.class);
	
	public static void updateAllData(){
		updatePSIData();
		updatePM25Data();
	}
	
	
	public static void updatePSIData(){
		InputStream inputStream = URLStreamGenerator.getInputStream(psiURLString);
		Document document = XMLDocumentGenerator.getDocument(inputStream);
		logger.trace("Got PSI Data XML From NEA");
		UnmarshallXMLService xmlService = new UnmarshallXMLService(document);
		List<Region> regions = xmlService.getRegions();
		RegionDaoJpa regionDao = new RegionDaoJpa();
		for(Region region : regions){
			regionDao.updateRegion(region);
		}
	}
	
	public static void updatePM25Data(){
		InputStream inputStream = URLStreamGenerator.getInputStream(pm25URLString);
		Document document = XMLDocumentGenerator.getDocument(inputStream);
		logger.trace("Got PM 25 Data XML From NEA");
		UnmarshallXMLService xmlService = new UnmarshallXMLService(document);
		List<Region> regions = xmlService.getRegions();
		RegionDaoJpa regionDao = new RegionDaoJpa();
		for(Region region : regions){
			regionDao.updateRegion(region);
		}
	}

}
