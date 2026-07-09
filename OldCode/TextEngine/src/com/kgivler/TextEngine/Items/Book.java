package com.kgivler.TextEngine.Items;

import com.kgivler.TextEngine.*;

public class Book extends InvItem {
	private String useMessage = "\nYou don't recognize the language.";
	public Book(GameEngine engine)
	{
		super(engine);
		setName("BOOK");
		setDescription("It's not in English, so you can't read it");
	}
	
	@Override
	public void processUse() {
		if(engine.getPlayer().hasItem(this))
			GameEngine.addMessage(engine, useMessage);
		else
		{
			GameEngine.addMessage(engine, "\nYou have to take it first...");
		}
	}
}
