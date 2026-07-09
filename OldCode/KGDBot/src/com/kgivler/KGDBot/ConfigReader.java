package com.kgivler.KGDBot;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class ConfigReader {
	private static Logger logger = Logger.getLogger("KGDBot");
	
	public static HashMap<String,String> readConfig() throws IOException
	{
		HashMap<String,String> config = new HashMap<String,String>();
		File configFile = new File("config\\bot.conf");
		logger.info("Looking for config file in: " + configFile.getAbsolutePath());
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
			logger.info("Key: " + key + " Value: " + value);
			config.put(key, value);
		}
		
		reader.close();
		
		config = checkConfig(config);
		
		return config;
	}
	
	private static HashMap<String,String> checkConfig(HashMap<String,String> config)
	{
		if(!config.containsKey("server"))
		{
			logger.severe("\'server\' config option is missing");
			return null;
		}
		if(!config.containsKey("port"))
		{
			logger.warning("\'port\' config option is missing");
			config.put("port", "6667");
		}
		if(!config.containsKey("nick"))
		{
			logger.info("\'nick\' config option is missing");
			config.put("nick", "KGDBot");
		}
		if(!config.containsKey("realname"))
		{
			logger.info("\'realname\' config option is missing");
			config.put("realname", "KGDBot");
		}
		if(!config.containsKey("channel"))
		{
			logger.severe("\'channel\' config option is missing");
			return null;
		}
		if(!config.containsKey("prefix"))
		{
			logger.warning("\'prefix\' config option is missing");
			config.put("prefix", "!");
		}
		
		return config;
	}
}