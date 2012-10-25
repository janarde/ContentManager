package com.mobile.grazie.csv;

import com.mobile.grazie.csv.ExternalId;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Venue {
	
	@XmlElement(name="name")
	private String name;
	
	@XmlElement(name="address")
	private String address;
	
	@XmlElement(name="city")
	private String city;
	
	@XmlElement(name="zip")	
	private String zip;
	
	@XmlElement(name="phone-number")	
	private String phoneNum;
	
	@XmlElement(name="lat")	
	private String lat;
	
	@XmlElement(name="lng")	
	private String lng;
	
	@XmlElement(name="type")	
	private String type;
	
	@XmlElement(name="externalIds")
	private List<ExternalId> externalIds = new ArrayList<ExternalId>();
	
	@XmlElement(name="photo-url")
	private String photoUrl;
	
	@XmlElement(name="menuIds")
	private String menuIds;
	
	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNumber) {
		this.phoneNum = phoneNumber;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ExternalId> getExternalIdentifiers() {
		return externalIds;
	}

	public void setExternalIdentifiers(ExternalId externalIdentifiers) {
		this.externalIds.add(externalIdentifiers);
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	

}
