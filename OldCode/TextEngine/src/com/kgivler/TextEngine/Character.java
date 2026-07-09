package com.kgivler.TextEngine;

import java.util.ArrayList;

public class Character {
	private String name;
	private String description;
	private int health;
	private double money;
	private Location location;
	private ArrayList<Item> inventory;
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Character(String name, String desc, int health, Location loc)
	{
		this.inventory = new ArrayList<Item>();
		this.name = name;
		this.description = desc;
		this.health = health;
		this.location = loc;
	}
	public Character(String name, int health, Location loc)
	{
		this(name, null, health, loc);
	}
	public Character(String name, Location loc)
	{
		this(name, null, 100, loc);
	}
	public Character(String name)
	{
		this(name, null, 100, null);
	}
	public Character()
	{
		this("Character", null, 100, null);
	}
	
	public Item getItem(String itemName)
	{
		int numberOfItems = inventory.size();
		for(int i = 0; i < numberOfItems; i++)
		{
			if(inventory.get(i).getName().equals(itemName))
			{
				return inventory.get(i);
			}
		}
		return null; // use hasItem first!
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean hasItem(String itemName)
	{
		int numberOfItems = inventory.size();
		for(int i = 0; i < numberOfItems; i++)
		{
			if(inventory.get(i).getName().equals(itemName))
				return true;
		}
		return false;
	}
	
	public boolean hasItem(Item item)
	{
		int numberOfItems = inventory.size();
		for(int i = 0; i < numberOfItems; i++)
		{
			if(inventory.get(i) == item)
				return true;
		}
		return false;
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
	
	public void setLocation(Location loc)
	{
		this.location = loc;
	}
	public Location getLocation()
	{
		return location;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public int getHealth()
	{
		return health;
	}
	public void setHealth(int health)
	{
		this.health = health;
	}
	public void addHealth(int amount)
	{
		health += amount;
	}
	public void removeHealth(int amount)
	{
		health -= amount;
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public double getMoney()
	{
		return money;
	}
	public void setMoney(double amount)
	{
		this.money = amount;
	}
	public void addMoney(double amount)
	{
		money += amount;
	}
	public void removeMoney(double amount)
	{
		money -= amount;
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
	
	public void setItems(ArrayList<Item> items)
	{
		this.inventory = items;
	}
	public ArrayList<Item> getItems()
	{
		return inventory;
	}
	public void addItem(Item item)
	{
		inventory.add(item);
	}
	public void removeItem(Item item)
	{
		inventory.remove(item);
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
