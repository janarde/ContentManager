package com.mobile.grazie.csv;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.mobile.grazie.csv.Venue;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;


public class JSONWriter {

	private String csvFile;
	
	public JSONWriter(String csvFile) {
		this.csvFile = csvFile;
		
	}
	
	/**
	 * Converts the csv line into a json file object
	 * @return File
	 **/
	public File convert() {
		String startFile = csvFile;
		
		//String outFile = "./resources/temp-bl-locations.json";
		String outFile = "./resources/temp-bl-locations.xml";
		
		try {
			CSVReader reader = new CSVReader(new FileReader(startFile));
			String[] line = null;
			
			String[] header = reader.readNext();

			// make it a list of venues
			//XStream xstream = new XStream(new JettisonMappedXmlDriver());
			XStream xstream = new XStream();
			xstream.setMode(XStream.NO_REFERENCES);
			xstream.alias("venue", Venue.class);
			xstream.alias("externalId", ExternalId.class);
			
			
			while ((line = reader.readNext()) != null) {
				Venue v = new Venue();
				for (int i = 0; i < header.length; i++ ) {
					// reflection!
					
					if (header[i].contentEquals("FacebookPlaceID")) {
						ExternalId id = new ExternalId();
						id.setType("FACEBOOK_PLACE_ID");
						id.setExternalId(line[i]);
						v.setExternalIdentifiers(id);
					}else if (header[i].contentEquals("StoreID")) {
						ExternalId id = new ExternalId();
						id.setType("STORE_ID");
						id.setExternalId(line[i]);
						v.setExternalIdentifiers(id);
					} else {
						Venue.class.getMethod("set"+header[i], String.class).invoke(v, line[i]);
					}
					
				}
				xstream.toXML(v, new FileWriter(outFile, false));
			}
			
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new File(outFile);
	}
	
}
