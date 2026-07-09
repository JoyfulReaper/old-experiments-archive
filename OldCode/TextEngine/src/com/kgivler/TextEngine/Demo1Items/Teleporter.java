package com.kgivler.TextEngine.Demo1Items;

import com.kgivler.TextEngine.*;

public class Teleporter extends WorldItem {
	Location dest;
	public Teleporter(GameEngine engine, Location loc)
	{
		super(engine);
		this.dest = loc;
		setName("TELEPORTER");
		setDescription("It is a huge machine which is labeled 'teleporter'. What is a teleporter doing in the woods?");
	}
	
	@Override
	public void processUse() {
		GameEngine.addMessage(engine, "\nThe machince clicks and whirls... then poof! You have teleported!\n");
		engine.getPlayer().setLocation(dest);
	}
	
	public void processTake()
	{
		GameEngine.addMessage(engine, "You don't think it will fit in your pocket... You decided to let it be.\n");
	}
}
