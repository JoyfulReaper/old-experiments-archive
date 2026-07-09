package com.kgivler.TextEngine.Demo1Locations;
import com.kgivler.TextEngine.*;
public class Room1_2 extends Location{
	private boolean viewed = false;
	public Room1_2(GameEngine engine)
	{
		super("Another Cement Room", "Well, it's starting to look like all of the rooms in this place have cement walls. Hey! There is a desk here!", engine);
	}
	
	@Override
	public void processLook(GameEngine engine) {
		super.processLook(engine);
		if(!viewed)
			GameEngine.addMessage(engine, "\nIt looks like the door has slammed behind you! And it's locked! I guess you're not going back in that room for anything...\n");
		viewed = true;
		
	}
}
