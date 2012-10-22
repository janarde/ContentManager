package com.mobile.grazie.csv;

import com.mobile.grazie.csv.Identifiable;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Venue {
	
	@XmlElement(name="store-name")
	private String storeName;
	
	@XmlElement(name="address")
	private String address;
	
	@XmlElement(name="city")
	private String city;
	
	@XmlElement(name="state")
	private String state;
	
	@XmlElement(name="zip")	
	private String zip;
	
	@XmlElement(name="phone-number")	
	private String phoneNumber;
	
	@XmlElement(name="latitude")	
	private String latitude;
	
	@XmlElement(name="longitude")	
	private String longitude;
	
	@XmlElement(name="type")	
	private String type;
	
	@XmlElementWrapper(name="external-identifier")
	@XmlElement(name="externalIdentity")
	private List<Identifiable> externalIdentifiers;
	
	@XmlElement(name="logo")
	private String logo;
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Identifiable> getExternalIdentifiers() {
		return externalIdentifiers;
	}

	public void setExternalIdentifiers(List<Identifiable> externalIdentifiers) {
		this.externalIdentifiers = externalIdentifiers;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}


}
