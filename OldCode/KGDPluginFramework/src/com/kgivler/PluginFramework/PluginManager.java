/**
 * Based on code from: 
 * http://faheemsohail.com/2011/01/writing-a-small-plugin-framework-for-your-apps/
 */

package com.kgivler.PluginFramework;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;


public class PluginManager {
	private File pluginPath = new File("plugins"); // Where to look for plugins
	private ArrayList<Plugin> plugins = new ArrayList<Plugin>(); // Plugins we found
	private Logger logger = Logger.getLogger("PluginFramework"); // Logging

	/**
	 * Set the location to search for plugins
	 * @param path Path to search
	 */
	public void SetPluginPath(File path)
	{
		this.pluginPath = path;
	}

	/**
	 * Get the path searched for plugins
	 * @return location to check for plugins
	 */
	public File getPluginPath()
	{
		return pluginPath;
	}

	/**
	 * Attempt to load plugins from pluginPath
	 * @throws Exception
	 */
	public void loadPlugins() throws Exception
	{
		logger.info("Looking for plugins in: " + pluginPath.getCanonicalPath());
		
		File files[] = pluginPath.listFiles();
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].isFile() && files[i].getCanonicalFile().toString().endsWith(".jar")) // Filename ends in .jar
			{
				logger.info("Found potential plugin: " + files[i].getCanonicalFile().toString());
				JarFile jar = new JarFile(files[i]);
				Manifest manifest = jar.getManifest();
				Attributes attrs = manifest.getMainAttributes();
				String pluginMainClass = attrs.getValue("Plugin");
				if(pluginMainClass == null)
				{
					logger.warning("Jar Manifest is missing Plugin attribute, not adding");
				} else {
					logger.info("Adding Plugin. pluginMainClass: " + pluginMainClass);

					URL[] url = { files[i].toURI().toURL() };
					ClassLoader loader = URLClassLoader.newInstance(url);
					Plugin plugin = (Plugin) loader.loadClass(pluginMainClass).newInstance();
					plugins.add(plugin);
				}
			}
		}
	}
	
	/**
	 * Get loaded plugins
	 * @return loaded plugins
	 */
	public ArrayList<Plugin> getPlugins()
	{
		return plugins;
	}
	
	/**
	 * Initialize all loaded plugins
	 */
	public void initPlugins()
	{
		for(int i = 0; i < plugins.size(); i++)
		{
			plugins.get(i).init();
		}
	}
	
	/**
	 * unload all plugins
	 */
	public void unloadPlugins()
	{
		for(int i = 0; i < plugins.size(); i++)
		{
			plugins.get(i).unload();
			plugins.remove(i);
		}
	}
}