package com.kgivler.PluginFramework;

public abstract class Plugin {
	private String name;

	/**
	 * Plugin Constructor
	 * @param name This plugin's name
	 */
	public Plugin(String name)
	{
		this.name = name;
	}
	
	/**
	 * Get this plugin's name
	 * @return this plugin's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Called on unload
	 */
	public abstract void unload();
	/**
	 * Called on load
	 */
	public abstract void init();
	
	/**
	 * process comand...
	 * @param command the command
	 */
	public abstract void processCommand(String command);
}