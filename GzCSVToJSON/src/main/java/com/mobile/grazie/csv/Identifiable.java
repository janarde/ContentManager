package com.mobile.grazie.csv;

public interface Identifiable {
	
	public void setType(String type);
	
	public void setExternalId(String value);
	
	public String getType();
	
	public String getExternalId();

}
