package com.fdmgroup.services;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Test;
import org.w3c.dom.Document;

public class XMLDocumentGeneratorTest {
	


	@Test
	public void generatePSIUpdateDocumentFromFile(){
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("psiUpate.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Document document = XMLDocumentGenerator.getDocument(inputStream); 
		assertTrue(document != null);
		
	}
	
	@Test
	public void generatePSIUpdateDocumentFromURL(){
		InputStream inputStream = null;
		try{
			URL url = new URL("http://api.nea.gov.sg/api/WebAPI/?dataset=psi_update&keyref=781CF461BB6606AD1260F4D81345157FE21D05A9F8ACBCAF");
			inputStream = url.openStream();
		} catch(IOException e){
			
		}
		Document document = XMLDocumentGenerator.getDocument(inputStream); 
		assertTrue(document != null);
		
	}

}
