package com.kgivler.KGDTextEngine;

import java.util.Scanner;

public class GameTester extends GameEngine {
	public GameTester(Character player)
	{
		super(player);
		Location location = new Location("Empty Room", "You are in it!");
		player.setLocation(location);
	}



	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		Character player = new Character("Player", "It's you!", 100, 100);
		GameTester tester = new GameTester(player);

		tester.processCommand("look");
		while (true)
		{
			System.out.print(tester.getMessage());
			System.out.print("\nCommand: ");
			String input = in.nextLine();
			tester.processCommand(input);
		}
	}
}