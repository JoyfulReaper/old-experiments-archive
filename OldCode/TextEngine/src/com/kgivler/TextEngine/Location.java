package com.kgivler.TextEngine;

import java.util.ArrayList;

public class Location {
	private String title;
	private String description;
	private ArrayList<Exit> exits;
	protected GameEngine engine;

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Create a gaming location
	 * @param title The title of this Location
	 * @param desc A description of this Location
	 * @param exits Exits from this Location to other Locations
	 */
	public Location(String title, String desc, ArrayList<Exit> exits, GameEngine engine)
	{
		this.title = title;
		this.description = desc;
		this.exits = exits;
		this.engine = engine;
	}

	/**
	 * Create a gaming location with no exits
	 * @param title The title of this Location
	 * @param desc A description of this Location
	 */
	public Location(String title, String desc, GameEngine engine)
	{
		this(title, desc, new ArrayList<Exit>(), engine);
	}

	/**
	 * Create an uninitialized Location
	 */
	public Location(GameEngine engine)
	{
		this(null, null, engine);
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void processLook(GameEngine engine)
	{
		GameEngine.addMessage(engine, "\n\"" + engine.getPlayer().getLocation().getTitle() + "\"\n");
		GameEngine.addMessage(engine, engine.getPlayer().getLocation().getDescription() + "\n\n");

		int numberOfItems = engine.getPlayer().getLocation().getItems().size();
		GameEngine.addMessage(engine, "Items: ");
		if(numberOfItems == 0)
			GameEngine.addMessage(engine, "You don't see any items here.");
		else
		{
			for(int i = 0; i < numberOfItems; i++)
			{
				GameEngine.addMessage(engine, engine.getPlayer().getLocation().getItems().get(i).getName() + " ");
			}
		}

		int numberOfExits = engine.getPlayer().getLocation().getExits().size();
		GameEngine.addMessage(engine, "\nExits: ");
		if(numberOfExits == 0)
			GameEngine.addMessage(engine, "You don't see any exits!");
		else
		{
			for(int i = 0; i < numberOfExits; i++)
			{
				GameEngine.addMessage(engine, engine.getPlayer().getLocation().getExits().get(i).getDirectionLong() + " ");
			}
		}
	}
	
	public boolean hasItem(Item item)
	{
		int numberOfItems = Item.getItems().size();
		for(int i = 0; i < numberOfItems; i++)
		{
			if(Item.getItems().get(i).getLocation() == this)
				return true;
		}
		return false;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	public ArrayList<Item> getItems()
	{
		ArrayList<Item> itemsHere = new ArrayList<Item>();
		int numberOfItems = Item.getItems().size();
		for(int i = 0; i < numberOfItems; i++)
		{
			if(Item.getItems().get(i).getLocation() == this)
				itemsHere.add(Item.getItems().get(i));
		}
		return itemsHere;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	public ArrayList<Exit> getExits()
	{
		return exits;
	}
	public void setExits(ArrayList<Exit> exits)
	{
		this.exits = exits;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	public void addExit(Exit exit)
	{
		exits.add(exit);
	}
	public void removeExit(Exit exit)
	{
		exits.remove(exit);
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
}
