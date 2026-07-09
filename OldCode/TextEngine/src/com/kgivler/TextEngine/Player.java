package com.kgivler.TextEngine;

public class Player extends Character{
	public Player(String name, String desc, int health, Location loc)
	{
		super(name, desc, health, loc);
	}
	public Player(String name, String desc, int health)
	{
		super(name, desc, health, null);
	}
	public Player(String name, int health, Location loc)
	{
		super(name, null, health, loc);
	}
	public Player(String name, Location loc)
	{
		super(name, null, 100, loc);
	}
	public Player(String name)
	{
		super(name, null, 100, null);
	}
	public Player()
	{
		super("Player", null, 100, null);
	}
}
