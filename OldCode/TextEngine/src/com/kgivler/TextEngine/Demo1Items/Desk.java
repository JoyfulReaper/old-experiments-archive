package com.kgivler.TextEngine.Demo1Items;

import com.kgivler.TextEngine.*;

public class Desk extends WorldItem {
	static String desc = "It is a large wooden desk.";
	private boolean viewed;
	public Desk(GameEngine engine)
	{
		super("DESK", desc, engine);
		viewed = false;
	}

	public void processTake()
	{
		GameEngine.addMessage(engine, "Your gut tells you that it is too heavy... You decide to let it there.\n");
	}
	
	public void processLook()
	{
		if(!viewed)
		{
			viewed = true;
			GameEngine.addMessage(engine, desc + " There is a note on it.\n");
			
			Item note = new Note(engine);
			note.setLocation(engine.getPlayer().getLocation());
			engine.addItem(note);
		}
		else
		{
			GameEngine.addMessage(engine, desc);
		}
	}
}
