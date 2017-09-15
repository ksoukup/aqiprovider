package com.fdmgroup.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "record")
@Entity
@IdClass(CompositeKey.class)
public class Record {

	@Id
	Date timestamp;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID")
	Region region;
	@Id
	String type;
	double value;

	public Date getTimestamp() {
		return timestamp;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getRegion().getId() + "\n");
		sb.append(this.getRegion().getLongitude() + "\n");
		sb.append(this.getRegion().getLatitude() + "\n");
		sb.append( new SimpleDateFormat("dd-MMM-yy HH:mm:ss").format(this.getTimestamp())+"\n");
		sb.append(this.getType() + " : " +this.getValue()+"\n");
		return sb.toString();
	}

}

class CompositeKey implements Serializable{
	private Date timestamp;
	private String region;
	private String type;
}
