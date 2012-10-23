package com.mobile.grazie.csv;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.mobile.grazie.csv.Converter;

public class CommandLineInterface {
	
	private static List<HashMap<String, String>> optionMaps = new ArrayList<HashMap<String, String>>();
	private static HashMap<String, String> optionMap = new HashMap<String, String>();
	
	public static void usePosixParser(final String[] commandLineArgs) {
		final CommandLineParser cmdLinePosixParser = new PosixParser();
		final Options posixOptions = constructPosixOptions();
		CommandLine cmdLine;
		try {
			cmdLine = cmdLinePosixParser.parse(posixOptions, commandLineArgs);
			
			if (cmdLine.hasOption("u")) {
				optionMap.put("username", cmdLine.getOptionValue("u"));
				optionMaps.add(optionMap);
			}
			if (cmdLine.hasOption("p")) {
				optionMap.put("password", cmdLine.getOptionValue("p"));
				optionMaps.add(optionMap);
			}
			if (cmdLine.hasOption("f")) {
				optionMap.put("file", cmdLine.getOptionValue("f"));
				optionMaps.add(optionMap);
			}
			if (cmdLine.hasOption("url")) {
				optionMap.put("url", cmdLine.getOptionValue("url"));
				optionMaps.add(optionMap);
			}			
		} catch (ParseException pe) {
			System.err.println("Encountered Exception while parsing using PosixParser:\n" + pe.getMessage());
		}
	}
	
	public static Options constructPosixOptions() {
		final Options posixOptions = new Options();
		posixOptions.addOption("u", true, "Username");
		posixOptions.addOption("p", true, "password");
		posixOptions.addOption("f", true, "Full path of csv file");
		posixOptions.addOption("url", true, "URL of server");
		return posixOptions;
	}
	
	public static void displayProvidedArgs(final String[] cmdLineArgs, final OutputStream out) {
		final StringBuffer buffer = new StringBuffer();
		for (final String argument : cmdLineArgs) {
			buffer.append(argument).append(" ");
		}
		try {
			out.write((buffer.toString() + "\n").getBytes());
		} catch (IOException e) {
			System.err.println("WARNING: Exception encountered trying to write to OutputStream: \n" + e.getMessage());
			System.out.println(buffer.toString());
		}
	}
	
	public static void displayBlankLines(final int numberBlankLines, final OutputStream out) {
		try {
			for (int i = 0; i < numberBlankLines; ++i) {
				out.write("\n".getBytes());
			}
		} catch (IOException ioEx) {
			for (int i = 0; i < numberBlankLines; ++i) {
				System.out.println();
			}
		}
	}
	
	public static void displayHeader(final OutputStream out) {
		final String header = "Content Uploader Tool";
		try {
			out.write(header.getBytes());
		} catch (IOException ioEx) {
			System.out.println(header);
		}
	}
	
	public static void printUsage(final String applicationName, final Options options, final OutputStream out) {
		final PrintWriter writer = new PrintWriter(out);
		final HelpFormatter usageFormatter = new HelpFormatter();
		usageFormatter.printUsage(writer, 80, applicationName, options);
		writer.flush();
		
	}
	
	public static void printHelp(
			final Options options,
			final int printedRowWidth,
			final String header,
			final String footer,
			final int spacesBeforeOption,
			final int spacesBeforeOptionDescription,
			final boolean displayUsage,
			final OutputStream out) {
		final String commandLineSyntax = "java -cp ApacheCommonsCLI.jar";
		final PrintWriter writer = new PrintWriter(out);
		final HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp(
				writer, 
				printedRowWidth, 
				commandLineSyntax, 
				header, 
				options, 
				spacesBeforeOption, 
				spacesBeforeOptionDescription, 
				footer, 
				displayUsage);
		writer.flush();
	}
	
	
	public static void main(final String[] args) {
		final String applicationName = "ContentUploader";
		displayBlankLines(1, System.out);
		displayHeader(System.out);
		displayBlankLines(2, System.out);
		
		if (args.length < 1) {
			System.out.println("-- USAGE --");
			printUsage(applicationName, constructPosixOptions(), System.out);
			displayBlankLines(4, System.out);
			System.out.println("-- HELP --");
			printHelp(constructPosixOptions(), 80, "HELP", "End of Help", 3, 5, true, System.out);
		}
		
		displayProvidedArgs(args, System.out);
		usePosixParser(args);
		String file = null;
		String username = null;
		String password = null;
		String url = null;
		File json = null;
		for (int i=0; i<=(optionMaps.size() - 1); i++) {
			if (optionMaps.get(i).containsKey("file")) {
				System.out.println("got here");
				file = optionMaps.get(i).get("file");
			}
			if (optionMaps.get(i).containsKey("username")) {
				username = optionMaps.get(i).get("username");
			}
			if (optionMaps.get(i).containsKey("password")) {
				password = optionMaps.get(i).get("password");
			}
			if (optionMaps.get(i).containsKey("url")) {
				url = optionMaps.get(i).get("url");
			}
		}
		System.out.println("calling converter with file " + file);
		Converter c = new Converter(file);	
		json = c.convert();
		System.out.println("calling poster with url " + url);
		System.out.println("calling poster with json " + json.getAbsolutePath());
		System.out.println("calling poster with username " + username);
		System.out.println("calling poster with password " + password);
		Poster p = new Poster(url, json, username, password);
		p.doRequest();
		
	}
		
	
	

}
