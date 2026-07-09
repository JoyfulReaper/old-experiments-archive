package com.kgivler.TextEngine;

public class Exit {
	// Numerical direction codes
	public static int UNDEFINED = 0;
	public static int NORTH = 1;
	public static int SOUTH = 2;
	public static int EAST = 3;
	public static int WEST = 4;
	public static int IN = 5;
	public static int OUT = 6;
	public static int UP = 7;
	public static int DOWN = 8;

	// Direction names
	private static final String[] directionName = 
	{ 
		"UNDEFINED",
		"NORTH",
		"SOUTH",
		"EAST",
		"WEST",
		"IN",
		"OUT",
		"UP",
		"DOWN"
	};
	
	// Short direction names
	private static final String[] shortDirectionName = 
	{
		"NULL",
		"N",
		"S",
		"E",
		"W",
		"I",
		"O",
		"U",
		"D"		
	};

	private Location leadsTo; // Location that this exit leads to
	private int direction; // Direction of exit (numeric)
	private String directionLong; // Direction of exit (String)
	private String directionShort; // Short Direction of exit (String) 
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Create an Exit
	 * @param direction numeric direction code of this exit
	 * @param leadsTo Location which this exit leads to
	 */
	public Exit(int direction, Location leadsTo)
	{
		this.direction = direction;
		this.leadsTo = leadsTo;
		
		if(direction <= directionName.length)
		{
			directionLong = directionName[direction];
			directionShort = shortDirectionName[direction];
		}
	}
	
	/**
	 * Create an uninitialized Exit
	 */
	public Exit()
	{
		this(0, null);
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Get the Location this Exit leads to
	 * @return the Location this Exit leads to
	 */
	public Location getLeadsTo()
	{
		return leadsTo;
	}
	/**
	 * Set the Location this Exit leads to.
	 * @param leadsTo The Location this exit leads to
	 */
	public void setLeadsTo(Location leadsTo)
	{
		this.leadsTo = leadsTo;
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Get direction of the Exit
	 * @return direction to Exit
	 */
	public int getDirection()
	{
		return direction;
	}
	/**
	 * Set direction of the Exit
	 * @param dir direction to the Exit
	 */
	public void setDirection(int dir)
	{
		this.direction = dir;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Get the friendly name of the direction (NORTH, SOUTH, etc)
	 * @return The direction to the Exit Location
	 */
	public String getDirectionLong()
	{
		return directionLong;
	}
	/**
	 * Set the friendly name of the direction to the Exit Location (NORTH, SOUTH, etc)
	 * @param dirLong the friendly name of the direction to the Exit Location
	 */
	public void setDirectionLong(String dirLong)
	{
		this.directionLong = dirLong;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Get the short name of the direction to the Exit Location (N, S, etc)
	 * @return the short name of the direction to the Exit Location
	 */
	public String getDirectionShort()
	{
		return directionShort;
	}
	/**
	 * Set the short name of the direction to the Exit Location (N, S, etc)
	 * @param dirShort The short name of the direction to the Exit Location
	 */
	public void setDirectionShort(String dirShort)
	{
		this.directionShort = dirShort;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
}
