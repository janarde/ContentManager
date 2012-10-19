package com.mobile.grazie.csv;


import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.mobile.grazie.csv.Venue;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;


public class Converter {

	
	
	public static void main(String[] args) {
		String startFile = "C:\\Users\\Jesse_Anarde\\git\\ContentManager\\GzCSVToJSON\\resources\\bl-locations.csv";
		String outFile = "C:\\Users\\Jesse_Anarde\\git\\ContentManager\\GzCSVToJSON\\resources\\bl-locations.xml";
		
		try {
			CSVReader reader = new CSVReader(new FileReader(startFile));
			String[] line = null;
			
			String[] header = reader.readNext();
			
			for (String h: header) {
				System.out.println(h);
			}
			// make it a list of venues
			List<Venue> out = new ArrayList<Venue>();
			
			while ((line = reader.readNext()) != null) {
				
				for (int i = 0; i < header.length; i++ ) {
					System.out.println("populating " + header[i] + " with " + line[i]);
					Venue v = new Venue();
					if (header[i] == "StoreID") {
						System.out.println("got here setting storeId = " + line[i]);
						v.setStoreID(line[i]);
					} else if (header[i] == "StoreName") {
						v.setStoreName(line[i]);
					} else if (header[i] == "Address") {
						v.setAddress(line[i]);
					} else if (header[i] == "City") {
						v.setCity(line[i]);
					} else if (header[i] == "State") {
						v.setState(line[i]);
					} else if (header[i] == "Zip") {
						v.setZip(line[i]);
					} else if (header[i] == "PhoneNumber") {
						v.setPhoneNumber(line[i]);
					} else if (header[i] == "Latitude") {
						v.setLatitude(line[i]);
					} else if (header[i] == "Longitude") {
						v.setLongitude(line[i]);
					} else if (header[i] == "Type") {
						v.setType(line[i]);
					} else if (header[i] == "FacebookPlaceID") {
						v.setFbLocId(line[i]);
					} else if (header[i] == "Logo") {
						v.setLogo(line[i]);
					} else {
						System.out.println("no match");
					}
					out.add(v);
				}
				
			}
			
			XStream xstream = new XStream();
			xstream.alias("Venues", List.class);
			xstream.alias("venue", Venue.class);
			xstream.toXML(out, new FileWriter(outFile, false));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
