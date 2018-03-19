package com.fdm.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fdm.model.Reading;

public class ReadingsXMLParser implements ReadingsParser {
	
	InputStream is;
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	private final Logger logger = LogManager.getLogger(ReadingsXMLParser.class);
	
	
	public ReadingsXMLParser() throws MalformedURLException, IOException {
		
		this(new URL("http://api.nea.gov.sg/api/WebAPI/?dataset=psi_update&keyref=781CF461BB6606AD1260F4D81345157FE21D05A9F8ACBCAF").openStream(),
				DocumentBuilderFactory.newInstance());
		
	}
	
	public ReadingsXMLParser(InputStream is, DocumentBuilderFactory dbFactory) {
		this.is = is;
		this.dbFactory = dbFactory;
		
		try {
			try {
				this.dBuilder =dbFactory.newDocumentBuilder();
				this.doc = dBuilder.parse(is);
			} catch (SAXException e) {
				logger.error("SAX Exception" + e.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.doc.getDocumentElement().normalize();
		
	}
	
	/* (non-Javadoc)
	 * @see com.fdm.parsers.ReadingsParser#getReadings()
	 */
	@Override
	public List<Reading> getReadings(){
		
		ArrayList<Reading> readings = new ArrayList<Reading>();
		NodeList regionList = doc.getElementsByTagName("region");
		
		for(int x = 0; x < regionList.getLength();  x++) {
			Node regionNode = regionList.item(x);

			if (regionNode.getNodeType() == Node.ELEMENT_NODE) {
				Element regionElement = (Element) regionNode;

				String regionName = regionElement.getElementsByTagName("id").item(0).getTextContent();
				NodeList recordList = regionElement.getElementsByTagName("record");
				for(int y = 0; y < recordList.getLength(); y++) {
					Node recordNode = recordList.item(y);
					if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
						Element recordElement = (Element) recordNode;
				
						Timestamp timeStamp = Timestamp.valueOf(LocalDateTime.parse(recordElement.getAttribute("timestamp"), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
						NodeList readingList = recordElement.getElementsByTagName("reading");
						for(int a = 0; a < readingList.getLength(); a ++) {
							Node readingNode = readingList.item(a);
							if (readingNode.getNodeType() == Node.ELEMENT_NODE) {
								Element readingElement = (Element) readingNode;

								Reading reading = new Reading();
								reading.setRegionName(regionName);
								reading.setTimeStamp(timeStamp);
								reading.setType(readingElement.getAttribute("type"));
								reading.setValue(Double.parseDouble(readingElement.getAttribute("value")));
								readings.add(reading);
								logger.trace(reading.toString());
							}
							

						}
					}
					
					
				}
				
				
			}
		
		}
		return readings;
	}
	

}
