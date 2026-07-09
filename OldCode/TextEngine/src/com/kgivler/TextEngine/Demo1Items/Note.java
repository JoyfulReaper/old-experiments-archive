package com.kgivler.TextEngine.Demo1Items;

import com.kgivler.TextEngine.*;

public class Note extends InvItem {
	static String desc = "It says: 'Help! I have been trapped in this cement tower for years!'\n";
	public Note(GameEngine engine)
	{
		super("NOTE", desc, engine);
	}
	
	public void processUse()
	{
		processLook();
	}
}
