package com.mobile.grazie.csv;

import com.mobile.grazie.csv.ExternalId;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;


/**
 * Venue POJO
 * @author Jesse_Anarde
 *
 */
public class Venue implements Serializable {

	private static final long serialVersionUID = -7201086832877111883L;

	public static enum VenueType { RESTAURANT, BAR, NIGHTCLUB, COFFEE }
	
	private String name;
	private String address;
	private String city;
	private String zip;
	private String phoneNum;
	private String lat;
	private String lng;
	private String type;
	private List<ExternalId> externalIds = new ArrayList<ExternalId>();
	private String photoUrl;
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
