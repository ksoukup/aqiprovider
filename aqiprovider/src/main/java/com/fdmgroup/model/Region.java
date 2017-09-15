package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Region {
	@Id
	String id;
	double latitude;
	double longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="region") //orphanRemoval = true,
    List<Record> records = new ArrayList<Record>();

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public List<Record> getRecords() {
		return records;
	}
	public void setRecord(List<Record> record) {
		this.records = record;
	}
	public void addRecord(Record record){
		records.add(record);
		record.setRegion(this);
	}
	public void removeRecord(Record record){
		records.remove(record);
		record.setRegion(null);
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("ID " + this.id + "\n");
		sb.append("Lat: " + this.latitude + "\n");
		sb.append("Long: " + this.longitude + "\n");
		for(Record record : this.records){
			sb.append(record.toString());
		}
		sb.append("\n");
		
		return sb.toString();
	}


}
