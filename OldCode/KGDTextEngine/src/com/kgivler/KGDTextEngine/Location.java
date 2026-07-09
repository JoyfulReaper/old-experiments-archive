package com.kgivler.KGDTextEngine;

import java.util.ArrayList;

/**
 * Represents a Location with Exits
 * @author kwgivler
 *
 */
public class Location {
	private String title;
	private String description;
	private ArrayList<Exit> exits;

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Create a gaming location
	 * @param title The title of this Location
	 * @param desc A description of this Location
	 * @param exits Exits from this Location to other Locations
	 */
	public Location(String title, String desc, ArrayList<Exit> exits)
	{
		this.title = title;
		this.description = desc;
		this.exits = exits;
	}

	/**
	 * Create a gaming location with no exits
	 * @param title The title of this Location
	 * @param desc A description of this Location
	 */
	public Location(String title, String desc)
	{
		this(title, desc, new ArrayList<Exit>());
	}

	/**
	 * Create an uninitialized Location
	 */
	public Location()
	{
		this(null, null);
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Get the title of this Location
	 * @return title of location
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Set the title of the Location
	 * @param title title of Location
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Get the description of this Location
	 * @return This Location's description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Set this Location's description
	 * @param desc The description for this Location
	 */
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Get the exits from this Location to other Locations
	 * @return exits from this Location, or null if there are no exits
	 */
	public ArrayList<Exit> getExits()
	{
			return exits;
	}

	/**
	 * Set the exits from this Location
	 * @param exits Exits from this Location
	 */
	public void setExits(ArrayList<Exit> exits)
	{
		this.exits = exits;
	}

	/**
	 * Add an Exit to this Location
	 * @param exit Exit to add
	 */
	public void addExit(Exit exit)
	{
		exits.add(exit);
	}

	/**
	 * Remove an Exit from this Location
	 * @param exit Exit to remove
	 */
	public void removeExit(Exit exit)
	{
		exits.remove(exit);
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
}