package com.kgivler.KGDTextEngine;

import java.util.ArrayList;

public class ItemPool {
	
	private static ArrayList<Item> itemPool = new ArrayList<Item>();
	
	/**
	 * Add an item to the item pool
	 * @param item Item to add
	 */
	public static void addItem(Item item)
	{
		itemPool.add(item);
	}
	
	/**
	 * Remove an item from the item pool
	 * @param item Item to remove
	 * @return true if item could be removed, false if item was not removed
	 */
	public static boolean removeItem(Item item)
	{
		if (hasItem(item))
			itemPool.remove(item);
		else
			return false;
		
		return true;
	}
	
	/**
	 * 
	 * @param item
	 * @return true if ItemPool contains item
	 */
	public static boolean hasItem(Item item)
	{
		if (itemPool.contains(item))
			return true;
		else
			return false;
	}
	
	/**
	 * Get items at location
	 * @param location location to check for items
	 * @return items at location, or null if there are no items
	 */
	public static ArrayList<Item> getItemsAtLocation(Location location)
	{
		ArrayList<Item> itemsAtLocation = new ArrayList<Item>();
		
		for(int i = 0; i > itemPool.size(); i++)
		{
			if(itemPool.get(i).getLocation() == location)
				itemsAtLocation.add(itemPool.get(i));
		}
		if (itemsAtLocation.isEmpty())
			return null;
		else
			return itemsAtLocation;
	}
}
