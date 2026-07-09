package com.kgivler.TextEngine;

import java.util.ArrayList;

abstract public class Item {
	
	private static ArrayList<Item> gameItems = new ArrayList<Item>();
	private String name;
	private String description;
	private Location location;
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Item(String name, String desc, Location loc)
	{
		this.name = name;
		this.description = desc;
		this.location = loc;
	}
	
	public Item()
	{
		this(null, null, null);
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	abstract public void processCommand(String command);
	abstract public void processTake();
	abstract public void processUse();
	abstract public void processLook();

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static ArrayList<Item> getItems()
	{
		return (ArrayList<Item>) gameItems.clone();
	}

	public static void addItem(Item item)
	{
		gameItems.add(item);
	}
	public static void removeItem(Item item)
	{
		gameItems.remove(item);
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	public String getDescription()
	{
		return description;
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void setLocation(Location loc)
	{
		this.location = loc;
	}
	public Location getLocation()
	{
		return this.location;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
}
