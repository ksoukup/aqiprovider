package com.fdmgroup.dao;

import java.util.List;

import com.fdmgroup.model.Region;

public interface RegionDaoInt {

	void updateRegion(Region region);

	List<Region> getCurrentReadingsForAllRegions();
	
	Region getCurrentReadingForARegion(String id);

	Region getLast24HoursForRegion(String id);

	void removeRegion(String id);
	


}