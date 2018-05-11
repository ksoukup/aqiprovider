package com.fdm.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import com.fdm.model.Reading;
import com.fdm.model.Region;

public class RegionController {
	
	String[] regionIds = new String[] {"RWE", "RSO", "RNO", "REA", "RCE", "NRS"};
	private final static Logger logger = Logger.getLogger(RegionController.class); 
	
	public RegionController() {
		
	}
	
	public List<Region> generateRegionsList(List<Reading> readings){
		List<Region> regions = new ArrayList<Region>();
		
		for(String regionId: regionIds) {
			Region region = new Region(readings.stream()
					.filter(r -> r.getRegionName().equals(regionId))
					.collect(Collectors.toCollection(ArrayList::new)));
				region.setRegionId(regionId);
				region.setRegionName(regionId);

			logger.trace(region);
			regions.add(region);
		}
		
		return regions;
		
	}

}
