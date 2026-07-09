package com.kgivler.ConfigFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigFile {
	private Logger logger = Logger.getLogger("ConfigFile");

	public ConfigFile() throws IOException
	{
		parseConfig();
	}

	private void parseConfig() throws IOException
	{
		File configFile = new File("config\\sample.conf");
		logger.info("Looking for config file in: " + configFile.getCanonicalPath());

		FileReader reader = null;
		try {
			reader = new FileReader(configFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(0);
		}

		Properties properties = new Properties();
		try {
			properties.load(reader);
			System.out.println("test1: " + properties.getProperty("test1"));
			System.out.println("test2: " + properties.getProperty("test2"));
		} catch (IOException e) {
		}

	}

	public static void main(String[] args) throws IOException
	{
		new ConfigFile();
	}

}
