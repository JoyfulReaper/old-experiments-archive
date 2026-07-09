package com.kgivler.TextEngine.Demo1Items;

import com.kgivler.TextEngine.*;

public class Key1 extends InvItem {
	static String desc = "It is on a key ring. The key ring has a note attached to it, which says 'Key 1'\n";
	public Key1(GameEngine engine)
	{
		super("KEY1", desc, engine);
	}
	
	public void processUse(){
		if(!engine.getPlayer().hasItem(this))
		{
			GameEngine.addMessage(engine, "You don't have it. Maybe you should TAKE it first...\n");
			return;
		}
		if(!engine.getPlayer().getLocation().getTitle().equals("Another Room"))
			GameEngine.addMessage(engine, "You don't see a lock that this key will fit in...\n");
		else
		{
			
		}
	}
}
