package com.mobile.grazie.csv;

/**
 * External ID POJO
 * @author Jesse_Anarde
 *
 */
public class ExternalId {

	private String externalId;
	private String type;
	
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setExternalId(String value) {
		this.externalId = value;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getExternalId() {
		return this.externalId;
	}
}
