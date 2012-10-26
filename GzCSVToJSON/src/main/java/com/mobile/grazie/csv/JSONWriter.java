package com.mobile.grazie.csv;

import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.mobile.grazie.csv.Venue;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This worker class converts a csv file into an array for files to be posted.
 * @author Jesse_Anarde
 *
 */

public class JSONWriter {
	
	final Logger logger = LoggerFactory.getLogger(JSONWriter.class);

	private String csvFile;
	private List<File> fileList = new ArrayList<File>();
	
	/**
	 * Constructor for this writer
	 * @param csvFile
	 */
	public JSONWriter(String csvFile) {
		this.csvFile = csvFile;
		logger.debug("The csv file being passed to the JSONWriter is " + csvFile);
	}
	
	/**
	 * Converts the csv line into a json file array object
	 * @return File
	 **/
	public List<File> convert() {
		
		String startFile = csvFile;
		String outFile = "./resources/temp-bl-locations.json";
		
		try {
			CSVReader reader = new CSVReader(new FileReader(startFile));
			String[] line = null;
			
			String[] header = reader.readNext();
			
			// Setting up to object to json mapping stuff
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.setMode(XStream.NO_REFERENCES);
			xstream.alias("venue", Venue.class);
			xstream.omitField(List.class, "externalIds");
			xstream.alias("externalIds", ExternalId.class);
			xstream.addImplicitCollection(Venue.class, "externalIds");
			
			
			while ((line = reader.readNext()) != null) {
				Venue v = new Venue();
				for (int i = 0; i < header.length; i++ ) {
					
					// BEGIN: special logic for the embedded externalId case
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
					// END: special logic for the embedded externalId case						
					} else {
						// using reflection, we call the method that corresponds to the header
						Venue.class.getMethod("set"+header[i], String.class).invoke(v, line[i]);
					}
				}
				logger.debug("writing out venue " + v.getName());
				
				// convert the venue object to a temp json file - this *will* write to disk for a moment
				xstream.toXML(v, new FileWriter(outFile.concat("." + v.toString()), false));
				File tempFile = new File(outFile.concat("." + v.toString()));
				
				// add the temp file to the array.
				fileList.add(tempFile);
				
				// delete the file. We cleanup everything a bit later, but we can do this now too.
				tempFile.delete();
			}			
			
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileList;
	}
	
}
