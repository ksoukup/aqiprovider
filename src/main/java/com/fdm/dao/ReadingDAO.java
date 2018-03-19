package com.fdm.dao;

import java.sql.Timestamp;
import java.util.List;

import com.fdm.model.Reading;

public interface ReadingDAO {

	void addReading(Reading reading);

	void removeReading(String regionName, Timestamp timeStamp, String type);

	void updateReading(Reading reading);

	Reading getReading(String regionName, Timestamp timeStamp, String type);

	List<Reading> getReadingsByRegion(String regionName);

	List<Reading> getReadingsLatest();

}