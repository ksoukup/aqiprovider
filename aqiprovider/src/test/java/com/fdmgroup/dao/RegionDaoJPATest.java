package com.fdmgroup.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.fdmgroup.model.Region;

public class RegionDaoJPATest {

	@Test
	public void getAllRecordsForARegion(){
		RegionDaoJpa regionDao = new RegionDaoJpa();
		Region region = regionDao.getAllReadingsForARegion("rNO");
		assertTrue(region != null);
		System.out.println("Region From Test" + region.toString());
		
	}
	
	@Test
	public void getAllRegionIds(){
		RegionDaoJpa regionDao = new RegionDaoJpa();
		String[] regionIDs = regionDao.getAllRegionIds();
		assertTrue(regionIDs.length > 0);
		System.out.println("Region IDs " + regionIDs);
	}
	

}
