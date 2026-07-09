package com.kgivler.KGDTextEngine;

/**
 * Represents a character
 * @author kwgivler
 *
 */
public class Character {
	
	private String name;
	private String description;
	private int max_health;
	private int health;
	private double money;
	private Location location;
	private Inventory inventory;
	
	/**
	 * Construct Character
	 * @param name character's name
	 * @param description character's description
	 * @param max_health character's maximum allowable health
	 * @param health character's starting health
	 * @param money character's starting amount of money
	 * @param location character's starting Location
	 * @param inventory character's starting Inventory
	 */
	public Character(String name, String description, int max_health, int health, double money, Location location, Inventory inventory)
	{
		this.name = name;
		this.description = description;
		this.max_health = max_health;
		this.health = health;
		this.money = money;
		this.location = location;
		this.inventory = inventory;
	}
	
	public Character(String name, String description, int health, double money)
	{
		this(name, description, 100, health, money, null, new Inventory());
	}
	
	public Character(String name, String description)
	{
		this(name, description, 100, 0);
	}
	
	
	public Character(String name)
	{
		this(name, null);
	}
	
	/**
	 * Construct an empty Character
	 */
	public Character()
	{
		this(null);
	}
	
	// -----------------------------------------------------------------------------------------
	
	/**
	 * Set this character's name
	 * @param name Name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Get this character's name
	 * @return Name
	 */
	public String getName()
	{
		if(name == null)
			return "Un-named";
		else
			return name;
	}
	
	/**
	 * Set this character's description
	 * @param desc description
	 */
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	/**
	 * Get this character's description
	 * @return description
	 */
	public String getDescription()
	{
		if(description == null)
			return getName();
		else
			return description;
	}
	
	/**
	 * Set this character's maximum health.
	 * The character's health will never go able this amount
	 * @param max_health Maximum health
	 */
	public void setMaxHealth(int max_health)
	{
		this.max_health = max_health;
	}
	
	/**
	 * Get the amount of health this character can not exceed
	 * @return maximum allowable health
	 */
	public int getMaxHealth()
	{
		return max_health;
	}
	
	/**
	 * Set this character's health
	 * @param health amount of health
	 */
	public void setHealth(int health)
	{
		if (health > max_health)
			health = max_health;
		
		this.health = health;
	}
	
	/**
	 * Get this character's health
	 * @return character's current health
	 */
	public int getHealth()
	{
		return health;
	}
	
	/**
	 * Heal this character by a certain amount
	 * @param amount amount to heal character
	 * @return character's current health
	 */
	public int addHealth(int amount)
	{
		health += health;
		if (health > max_health)
			health = max_health;
		
		return health;
	}
	
	/**
	 * Damage character's health by a certain amount
	 * @param amount The amount by which to damage this character's health
	 * @return character's current health
	 */
	public int removeHealth(int amount)
	{
		health -= amount;
		if(health < 0)
			health = 0;
		
		return health;
	}
	
	/**
	 * Set the amount of money this character has
	 * @param money Amount of money
	 */
	public void setMoney(double money)
	{
		this.money = money;
	}
	
	/**
	 * Get the amount of money this character has
	 * @return amount of money
	 */
	public double getMoney()
	{
		return money;
	}
	
	/**
	 * Add to the amount of money this character has
	 * @param amount amount to add
	 * @return amount player has
	 */
	public double addMoney(double amount)
	{
		money += amount;
		return money;
	}
	
	/** 
	 * Remove money for this character
	 * If the character does not have enough money, -1 is returned
	 * and the character's money is NOT decreased
	 * 
	 * @param amount amount to remove
	 * @return the amount of money the character has, -1 if not enough money
	 */
	public double removeMoney(double amount)
	{
		if(money - amount < 0)
		{
			return -1;
		}
		else
		{
			money -= amount;
			return money;
		}
	}
	
	/**
	 * Set this character's Location
	 * @param location
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	/**
	 * Get this character's Location
	 * @return The location of this character
	 */
	public Location getLocation()
	{
		return location;
	}
	
	/**
	 * Set this character's Inventory
	 * @param inv
	 */
	public void setInventory(Inventory inv)
	{
		inventory = inv;
	}
	
	/**
	 * Get this character's Inventory
	 * @return the character's Inventory
	 */
	public Inventory getInventory()
	{
		return inventory;
	}
	
	/**
	 * Is this character alive?
	 * @return true if health > 0, false if health = 0
	 */
	public boolean isAlive()
	{
		if (health == 0)
			return false;
		else
			return true;
	}
}
