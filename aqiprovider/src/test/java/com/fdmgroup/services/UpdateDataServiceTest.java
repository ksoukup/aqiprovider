package com.fdmgroup.services;

import org.junit.Test;

public class UpdateDataServiceTest {

	@Test
	public void UpdatePSIDataTest(){
		
		UpdateDataService.updatePSIData();
	}
	
	@Test
	public void updatePM25DataTest(){
		UpdateDataService.updatePM25Data();
	}
}
