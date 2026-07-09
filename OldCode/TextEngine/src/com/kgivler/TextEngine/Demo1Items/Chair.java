package com.kgivler.TextEngine.Demo1Items;

import com.kgivler.TextEngine.*;

public class Chair extends WorldItem {
	static String desc = "It is a large wooden chair.";
	private boolean viewed;
	public Chair(GameEngine engine)
	{
		super("CHAIR", desc, engine);
		viewed = false;
	}

	public void processTake()
	{
		GameEngine.addMessage(engine, "Your gut tells you that it is too heavy... You decide to let it there.\n");
	}
	
	public void processUse()
	{
		GameEngine.addMessage(engine, "You sit down on the chair! It's not very comfortable...\n");
	}
	
	public void processLook()
	{
		if(!viewed)
		{
			viewed = true;
			GameEngine.addMessage(engine, desc + " Hey! There is a key taped under it! It says 'key1'.\n");
			
			Item key1 = new Key1(engine);
			key1.setLocation(engine.getPlayer().getLocation());
			engine.addItem(key1);
		}
		else
		{
			GameEngine.addMessage(engine, desc);
		}
	}
}
