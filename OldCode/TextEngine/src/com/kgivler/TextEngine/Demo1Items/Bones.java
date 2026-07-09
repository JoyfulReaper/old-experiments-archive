package com.kgivler.TextEngine.Demo1Items;

import com.kgivler.TextEngine.*;

public class Bones extends WorldItem {
	public Bones(GameEngine engine)
	{
		super(engine);
		setName("BONES");
		setDescription("They look human... Maybe you should get out of here?");
	}
	
	@Override
	public void processUse() {
		GameEngine.addMessage(engine, "\nI'm really not sure what you want to do with those... You better leave them alone.\n");
	}
	
	public void processTake()
	{
		GameEngine.addMessage(engine, "You decide that you don't REALLY want to haul human remains around with you.\n");
	}
	
	public void processLook()
	{
		if(engine.getPlayer().hasItem("NOTE"))
		{
			GameEngine.addMessage(engine, this.getDescription() + " Perhaps these bones belong to the person who wrote the not you are holding?\n");
		}
		else
			super.processLook();
	}
}
