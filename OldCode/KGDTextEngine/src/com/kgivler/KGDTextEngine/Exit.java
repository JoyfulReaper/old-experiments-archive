package com.kgivler.KGDTextEngine;

/**
 * Represents an exit to a Location
 * 
 * Based on: http://www.javacoffeebreak.com/text-adventure/tutorial2/tutorial2b.html
 * 
 * @author kwgivler
 *
 */
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

	// Long direction names
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
	private static final String[] directionShortName = 
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
	private int direction; // This Exit's direction code
	private String directionLong; // Long name of direction
	private String directionShort; // Short name of direction 
	
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
			directionShort = directionShortName[direction];
		}
		else
		{
			directionLong = "ERROR";
			directionShort = "ERROR";
		}
	}
	
	/**
	 * Create an uninitialized Exit
	 */
	public Exit()
	{
		this(UNDEFINED, null);
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
		if(dir <= directionName.length)
		{
			directionLong = directionName[dir];
			directionShort = directionShortName[dir];
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Get the friendly name of the direction (NORTH, SOUTH, etc)
	 * @return The direction to the Exit Location
	 */
	public String getDirectionName()
	{
		return directionLong;
	}
	/**
	 * Set the friendly name of the direction to the Exit Location (NORTH, SOUTH, etc)
	 * @param dirName the friendly name of the direction to the Exit Location
	 */
	public void setDirectionName(String dirName)
	{
		this.directionLong = dirName;
		
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Get the short name of the direction to the Exit Location (N, S, etc)
	 * @return the short name of the direction to the Exit Location
	 */
	public String getShortDirectionName()
	{
		return directionShort;
	}
	/**
	 * Set the short name of the direction to the Exit Location (N, S, etc)
	 * @param dirShort The short name of the direction to the Exit Location
	 */
	public void setShortDirectionName(String dirShort)
	{
		this.directionShort = dirShort;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	public String toString()
	{
		return directionLong;
	}
	
	public static int getDirectionCode(String dir)
	{
		dir = dir.toUpperCase();
		int code = Exit.UNDEFINED;
		
		if(dir.equals("N") || dir.equals("NORTH"))
			code = Exit.NORTH;
		if(dir.equals("S") || dir.equals("SOUTH"))
			code = Exit.SOUTH;
		if(dir.equals("E") || dir.equals("EAST"))
			code = Exit.EAST;
		if(dir.equals("W") || dir.equals("WEST"))
			code = Exit.WEST;
		if(dir.equals("U") || dir.equals("UP"))
			code = Exit.UP;
		if(dir.equals("D") || dir.equals("DOWN"))
			code = Exit.DOWN;
		if(dir.equals("I") || dir.equals("IN"))
			code = Exit.IN;
		if(dir.equals("O") || dir.equals("OUT"))
			code = Exit.OUT;
		
		return code;
	}
	
	public static int getOppositeDirection(int dirCode)
	{
		int code = UNDEFINED;
		
		if(dirCode % 2 == 0 && dirCode <= directionName.length)
			code = dirCode - 1;
		else
			code = dirCode + 1;
		
		return code;
	}
}
