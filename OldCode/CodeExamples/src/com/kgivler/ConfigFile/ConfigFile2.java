package com.kgivler.ConfigFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class ConfigFile2 {
	
	public static void readConfig() throws IOException
	{
		File configFile = new File("config\\sample.conf");
		FileReader reader = null;
		reader = new FileReader(configFile);

		Properties properties = new Properties();
		properties.load(reader);

		Set<Object> set = properties.keySet();
		Iterator<Object> it = set.iterator();

		while (it.hasNext())
		{
			String key = (String) it.next();
			String value = properties.getProperty(key);
			System.out.println("Key: " + key + " Value: " + value);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		readConfig();
	}
}

