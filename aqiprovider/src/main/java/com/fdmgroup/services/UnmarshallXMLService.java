package com.fdmgroup.services;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fdmgroup.model.Reading;
import com.fdmgroup.model.Record;
import com.fdmgroup.model.Region;

public class UnmarshallXMLService {

	private static final Logger logger = LogManager.getLogger(UnmarshallXMLService.class);
	private Document document;
	private static final String dateFormat = "yyyyMMddHHmmss";

	public UnmarshallXMLService(Document document){
		this.document = document;
		
	}

	public List<Region> getRegions(){
		List<Region> regions= new ArrayList<Region>();
		NodeList regionNodes = document.getElementsByTagName("region");
		for (int n = 0; n < regionNodes.getLength(); ++n) {
			regionNodes.item(n).getNodeName();
			regions.add(getRegionInfo(regionNodes.item(n)));
		}
		return regions;
	}

	private Region getRegionInfo(Node item) {
		Region region = new Region();
		region.setId(getChildByName(item, "id").getTextContent());
		region.setLatitude(Double.parseDouble(getChildByName(item, "latitude").getTextContent()));
		region.setLongitude(Double.parseDouble(getChildByName(item, "longitude").getTextContent()));
		for(Record record : getRecordInfo(getChildByName(item, "record"))){
			region.addRecord(record);
		}
		return region;
	}

	private List<Record> getRecordInfo(Node item) {
		List<Record> records = new ArrayList<Record>();
		Date date = stringToDate(item.getAttributes().getNamedItem("timestamp").getTextContent());
		for(Reading reading : getReadingInfo(item.getChildNodes())){
			Record record = new Record();
			record.setTimestamp(date);
			record.setType(reading.getType());
			record.setValue(reading.getValue());
			records.add(record);
		}
		return records;
	}

	private List<Reading> getReadingInfo(NodeList readingNodes) {
		List<Reading> readings = new ArrayList<Reading>();
		for (int n = 0; n < readingNodes.getLength(); ++n) {
			if(readingNodes.item(n).hasAttributes()){
				Reading reading = new Reading();
				reading.setType(readingNodes.item(n).getAttributes().getNamedItem("type").getTextContent());
				reading.setValue(Double.parseDouble(readingNodes.item(n).getAttributes().getNamedItem("value").getTextContent()));
				readings.add(reading);
			}
		}

		return readings;
	}

	private Date stringToDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Date date = new Date();
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			logger.error("Date Parsing error", UnmarshallXMLService.class, e);
		}
		return date;
	}

	private Node getChildByName(Node parent, String name) {
		Node child = parent.getFirstChild();

		while(child != null) {
			if(child.getNodeType() == Node.ELEMENT_NODE) {
				if(child.getNodeName().equals(name)) {
					return child;
				}
			}
			child = child.getNextSibling();
		}
		return null;
	}

}
