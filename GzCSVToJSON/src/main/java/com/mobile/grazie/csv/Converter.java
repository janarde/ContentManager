package com.mobile.grazie.csv;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.mobile.grazie.csv.Venue;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;


public class Converter {

	private String csvFile;
	
	public Converter(String csvFile) {
		this.csvFile = csvFile;
		
	}
	
	/**
	 * Converts the csv line into a json file object
	 * @return File
	 **/
	public File convert() {
		String startFile = csvFile;
		
		String outFile = "./resources/temp-bl-locations.json";
		
		try {
			CSVReader reader = new CSVReader(new FileReader(startFile));
			String[] line = null;
			
			String[] header = reader.readNext();

			// make it a list of venues
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.setMode(XStream.NO_REFERENCES);
			xstream.alias("venue", Venue.class);
			xstream.alias("FacebookLocationType", FacebookLocationType.class);
			xstream.alias("StoreType", StoreType.class);
			Venue v = new Venue();
			while ((line = reader.readNext()) != null) {
				
				List<Identifiable> identifiers = new ArrayList<Identifiable>();
				
				for (int i = 0; i < header.length; i++ ) {
					// reflection!
					
					if (header[i].contentEquals("FacebookPlaceID")) {
						Identifiable id = new FacebookLocationType();
						id.setType("FacebookType");
						id.setExternalId(line[i]);
						identifiers.add(id);
					}else if (header[i].contentEquals("StoreID")) {
						Identifiable id = new StoreType();
						identifiers.add(id);
						id.setType("StoreIdType");
						id.setExternalId(line[i]);
					} else {
						Venue.class.getMethod("set"+header[i], String.class).invoke(v, line[i]);
					}
					
				}
				v.setExternalIdentifiers(identifiers);
			}
			
			xstream.toXML(v, new FileWriter(outFile, false));
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new File(outFile);
	}
	
}
