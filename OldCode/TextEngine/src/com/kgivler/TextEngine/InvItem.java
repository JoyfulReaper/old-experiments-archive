package com.kgivler.TextEngine;

public class InvItem extends WorldItem {
	protected GameEngine engine;
	
	public InvItem(GameEngine engine)
	{
		super(engine);
		this.engine = engine;
	}
	
	public InvItem(String name, String desc, GameEngine engine)
	{
		super(name, desc, engine);
		this.engine = engine;
	}
	
	public InvItem(String name, String desc, Location loc, GameEngine engine)
	{
		super(name, desc, loc, engine);
		this.engine = engine;
	}
	
	@Override
	public void processTake() {
		Player player = engine.getPlayer();
		
		player.addItem(this);
		engine.removeItem(this);
		GameEngine.addMessage(engine, "You took the " + getName() + ".");
	}
	
}
