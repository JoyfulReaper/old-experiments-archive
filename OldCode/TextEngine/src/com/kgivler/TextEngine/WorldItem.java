package com.kgivler.TextEngine;

public class WorldItem extends Item{
	protected GameEngine engine;
	
	public WorldItem(GameEngine engine)
	{
		super();
		this.engine = engine;
	}
	
	public WorldItem(String name, String desc, GameEngine engine)
	{
		super(name, desc, null);
		this.engine = engine;
	}
	
	public WorldItem(String name, String desc, Location loc, GameEngine engine)
	{
		super(name, desc, loc);
		this.engine = engine;
	}

	
	@Override
	public void processCommand(String command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processTake() {
		GameEngine.addMessage(engine, "\nYou can't take that!");
		
	}

	@Override
	public void processUse() {
		GameEngine.addMessage(engine, "\nYou can't use that!");
		
	}

	@Override
	public void processLook() {
		GameEngine.addMessage(engine, getDescription());
	}
}
