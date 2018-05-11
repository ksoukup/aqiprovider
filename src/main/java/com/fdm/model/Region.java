package com.fdm.model;

import java.util.ArrayList;
import java.util.List;

public class Region {
	private String regionId;
	private String regionName;
	private List<Reading> readings;
	
	public Region(List<Reading> readings) {
		if (readings != null) {
			this.readings = readings;
		}
		else {
			readings = new ArrayList<Reading>();
		}
	}
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public List<Reading> getReadings() {
		return readings;
	}
	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}

	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", regionName=" + regionName + ", readings=" + readings + "]";
	}
	
	

}
