package com.kgivler.KGDTextEngine;

import java.util.ArrayList;

/**
 * Represents a container for Items
 * @author kwgivler
 *
 */
public class Inventory {
	private ArrayList<Item> inventory = new ArrayList<Item>();

	/**
	 * Add an Item to this Inventory
	 * @param item item to add
	 */
	public void addItem(Item item)
	{
		inventory.add(item);
	}

	/**
	 * Remove an item from this Inventory
	 * @param item item to remove
	 * @return false if item could not be removed, true if removed
	 */
	public boolean removeItem(Item item)
	{
		if (hasItem(item))
			inventory.remove(item);
		else
			return false;
		
		return true;
	}

	/**
	 * 
	 * @param item
	 * @return true if this Inventory contains item, otherwise false
	 */
	public boolean hasItem(Item item)
	{
		for(int i = 0; i < inventory.size(); i++)
		{
			if (inventory.contains(item))
				return true;
		}
		return false;
	}
}
