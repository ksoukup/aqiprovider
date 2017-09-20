package com.fdmgroup.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.fdmgroup.model.Record;

public class RecordDaoJPATest {

	@Test
	public void getCurrentRecordForARegionTest(){
		RecordDaoJpa recordDao = new RecordDaoJpa();
		List<Record> records = recordDao.getCurrentReadingForARegion("rNO");
		assertTrue(records != null);
		for(Record record : records){
			System.out.println("Record From Test \n" + record.toString());
		}
	}

	@Test
	public void getCurrentRecordForARegionInJSONTest(){
		RecordDaoJpa recordDao = new RecordDaoJpa();
		String recordsAsJson = recordDao.getCurrentReadingForARegionAsAJSON("rNO");
		assertTrue(recordsAsJson != null);
		System.out.println("Record From Test \n" + recordsAsJson);
	}
}
