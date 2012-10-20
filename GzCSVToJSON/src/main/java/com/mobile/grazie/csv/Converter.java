package com.mobile.grazie.csv;


import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.mobile.grazie.csv.Venue;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;


public class Converter {

	
	
	public static void main(String[] args) {
		String startFile = "C:\\Users\\jesse_anarde\\git\\ContentManager\\GzCSVToJSON\\resources\\bl-locations.csv";
		String outFile = "C:\\Users\\jesse_anarde\\git\\ContentManager\\GzCSVToJSON\\resources\\bl-locations.json";
		
		try {
			CSVReader reader = new CSVReader(new FileReader(startFile));
			String[] line = null;
			
			String[] header = reader.readNext();

			// make it a list of venues
			List<Venue> out = new ArrayList<Venue>();
			
			while ((line = reader.readNext()) != null) {
				Venue v = new Venue();
				
				for (int i = 0; i < header.length; i++ ) {
					// reflection!
					Venue.class.getMethod("set"+header[i], String.class).invoke(v, line[i]);
				}
				out.add(v);
			}
			
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.setMode(XStream.NO_REFERENCES);
			xstream.alias("Venues", List.class);
			xstream.alias("venue", Venue.class);
			xstream.toXML(out, new FileWriter(outFile, false));
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
