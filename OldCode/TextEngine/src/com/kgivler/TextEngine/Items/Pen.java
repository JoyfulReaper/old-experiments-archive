package com.kgivler.TextEngine.Items;

import com.kgivler.TextEngine.*;

public class Pen extends InvItem{
	public Pen(GameEngine engine)
	{
		super(engine);
		setName("PEN");
		setDescription("It's a blue ball-point pen.");
	}
	
	public void processUse()
	{
		if(!engine.getPlayer().hasItem(this))
		{
			GameEngine.addMessage(engine, "\nYou have to take it first!");
			return;
		}
		
		if (engine.getPlayer().hasItem("BOOK"))
		{
			GameEngine.addMessage(engine, "\nYou draw a happy face on the cover of the book!");
			engine.getPlayer().getItem("BOOK").setDescription("\nSomeone drew a happy face on the cover!");
		}
		else
		{
			GameEngine.addMessage(engine, "\nIf only you had something to use it on...");
		}
	}
}
