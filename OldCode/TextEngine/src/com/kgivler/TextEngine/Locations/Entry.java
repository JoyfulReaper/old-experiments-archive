package com.kgivler.TextEngine.Locations;

import com.kgivler.TextEngine.*;

public class Entry extends Location{
	private static String desc = "You awake with a headache. Where are you? How did you get here? You are in a plain room, which has cement walls. There are no doors, however you do see a ladder.";
	public Entry(GameEngine engine)
	{
		super("Entry Room", desc, engine);
	}
}
