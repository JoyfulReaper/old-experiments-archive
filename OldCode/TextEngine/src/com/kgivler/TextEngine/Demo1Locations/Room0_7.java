package com.kgivler.TextEngine.Demo1Locations;

import com.kgivler.TextEngine.*;

public class Room0_7 extends Location{
	public Room0_7(GameEngine engine)
	{
		super("Room0_7", "You have escaped the woods and won this adventure!", engine);
	}
	@Override
	public void processLook(GameEngine engine) {
		super.processLook(engine);
		GameEngine.addMessage(engine, "\nYou won in " + engine.getTurn() + " turns!");
		engine.gameIsOver();
	}
}