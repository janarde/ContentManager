package com.mobile.grazie.csv;

public class FacebookLocationType implements Identifiable {

	private String type;
	private String externalID;
	
	public void setType(String type) {
		this.type = type;
	}

	public void setExternalId(String externalID) {
		this.externalID = externalID;
	}

	public String getType() {
		return type;
	}

	public String getExternalId() {
		return externalID;
	}

}
