package com.mobile.grazie.csv;


import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;


public class Converter {

	
	
	public static void main(String[] args) {
		String startFile = "C:\\workspace\\GzCSVToJSON\\resources\\bl-locations.csv";
		String outFile = "C:\\workspace\\GzCSVToJSON\\resources\\bl-locations.xml";
		
		try {
			CSVReader reader = new CSVReader(new FileReader(startFile));
			String[] line = null;
			
			String[] header = reader.readNext();
			
			// make it a list of venues
			List<Object> out = new ArrayList<Object>();
			
			while ((line = reader.readNext()) != null) {
				List<String[]> item = new ArrayList<String[]>();
				for (int i = 0; i < header.length; i++ ) {
					String[] keyVal = new String[2];
					String string = header[i];
					String val = line[i];
					keyVal[0] = string;
					keyVal[1] = val;
					item.add(keyVal);
				}
				out.add(item);
			}
			
			XStream xstream = new XStream();
			xstream.toXML(out, new FileWriter(outFile, false));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
