package com.kgivler.KGDTextEngine;

public abstract class Item {
	private String name;
	private String description;
	private Location location;
	
	/**
	 * Represents an Item
	 * @param name Name of item
	 * @param description Description of item
	 * @param location Location of item
	 */
	public Item(String name, String description, Location location)
	{
		this.name = name;
		this.description = description;
		this.location = location;
	}
	
	public Item(String name, String desciption)
	{
		this(name, desciption, null);
	}
	
	public Item()
	{
		this(null, null, null);
	}
	
	// ----------------------------------------------------------------------------------
	
	abstract public boolean canTake();
	
	// ----------------------------------------------------------------------------------
	
	/**
	 * Get the name of this Item
	 * @return name of item
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Set the name of this Item
	 * @param name name of item
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Get description of this Item
	 * @return Item's description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Set this Item's description
	 * @param desc item's description
	 */
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	/**
	 * Get the Location of this Item
	 * @return Item's Location
	 */
	public Location getLocation()
	{
		return location;
	}
	
	/**
	 * Set this Item's Location
	 * @param location Item's Location
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}
	
}
