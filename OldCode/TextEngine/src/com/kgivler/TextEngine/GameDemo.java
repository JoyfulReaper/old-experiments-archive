package com.kgivler.TextEngine;

import java.util.Scanner;
import com.kgivler.TextEngine.Demo1Items.*;
import com.kgivler.TextEngine.Demo1Locations.*;

public class GameDemo {
	public static void main(String[] args)
	{	
		Scanner in = new Scanner(System.in);
		Player player = new Player("Player 1", "The hero of the game!", 100);
		final GameEngine game = new GameEngine(player);

		// Setup Locations
		Location room0_1 = new Room0_1(game);
		Location room0_2 = new Room0_2(game);
		Location room0_3 = new Room0_3(game);
		Location room0_4 = new Room0_4(game);
		Location room0_5 = new Room0_5(game);
		Location room0_6 = new Room0_6(game);
		Location room0_7 = new Room0_7(game);
		
		Location room1_1 = new Room1_1(game);
		Location room1_2 = new Room1_2(game);
		Location room1_3 = new Room1_3(game);
		Location room1_4 = new Room1_4(game);
		Location room1_5 = new Room1_5(game);
		Location room1_6 = new Room1_6(game);
		
		Location room2_1 = new Room2_1(game);
		Location room2_2 = new Room2_2(game);
		Location room2_3 = new Room2_3(game);
		Location room2_4 = new Room2_4(game);
		Location room2_5 = new Room2_5(game);
		Location room2_6 = new Room2_6(game);
		
		Location room3_1 = new Room3_1(game);
		Location room3_2 = new Room3_2(game);
		Location room3_3 = new Room3_3(game);
		Location room3_4 = new Room3_4(game);
		Location room3_5 = new Room3_5(game);
		Location room3_6 = new Room3_6(game);
		
		// Add locations to game world
		game.addLocation(room0_1);
		game.addLocation(room0_2);
		game.addLocation(room0_3);
		game.addLocation(room0_4);
		game.addLocation(room0_5);
		game.addLocation(room0_6);
		game.addLocation(room0_7);
		
		game.addLocation(room1_1);
		game.addLocation(room1_2);
		game.addLocation(room1_3);
		game.addLocation(room1_4);
		game.addLocation(room1_5);
		game.addLocation(room1_6);
		
		game.addLocation(room2_1);
		game.addLocation(room2_2);
		game.addLocation(room2_3);
		game.addLocation(room2_4);
		game.addLocation(room2_5);
		game.addLocation(room2_6);
		
		game.addLocation(room3_1);
		game.addLocation(room3_2);
		game.addLocation(room3_3);
		game.addLocation(room3_4);
		game.addLocation(room3_5);
		game.addLocation(room3_6);
		
		//Setup Exits
		Exit room1_1E = new Exit(Exit.EAST, room1_2);
		//Exit room1_2W = new Exit(Exit.WEST, room1_1); 
		room1_1.addExit(room1_1E);
		//room1_2.addExit(room1_2W);
		
		Exit room1_2S = new Exit(Exit.SOUTH, room1_4);
		Exit room1_4N = new Exit(Exit.NORTH, room1_2);
		room1_2.addExit(room1_2S);
		room1_4.addExit(room1_4N);
		
		Exit room1_4S = new Exit(Exit.SOUTH, room1_6);
		Exit room1_6N = new Exit(Exit.NORTH, room1_4);
		room1_4.addExit(room1_4S);
		room1_6.addExit(room1_6N);
		
		Exit room1_6W = new Exit(Exit.WEST, room1_5);
		Exit room1_5E = new Exit(Exit.EAST, room1_6);
		room1_6.addExit(room1_6W);
		room1_5.addExit(room1_5E);
		
		Exit room1_5U = new Exit(Exit.UP, room2_5);
		Exit room2_5D = new Exit(Exit.DOWN, room1_5);
		room1_5.addExit(room1_5U);
		room2_5.addExit(room2_5D);
		
		Exit room2_5E = new Exit(Exit.EAST, room2_6);
		Exit room2_6W = new Exit(Exit.WEST, room2_5);
		room2_5.addExit(room2_5E);
		room2_6.addExit(room2_6W);
		
		Exit room2_6N = new Exit(Exit.NORTH, room2_4);
		Exit room2_4S = new Exit(Exit.SOUTH, room2_6);
		room2_6.addExit(room2_6N);
		room2_4.addExit(room2_4S);
		
		Exit room2_4N = new Exit(Exit.NORTH, room2_2);
		Exit room2_2S = new Exit(Exit.SOUTH, room2_4);
		room2_4.addExit(room2_4N);
		room2_2.addExit(room2_2S);
		
		Exit room2_2W = new Exit(Exit.WEST, room2_1);
		Exit room2_1E = new Exit(Exit.EAST, room2_2);
		room2_2.addExit(room2_2W);
		room2_1.addExit(room2_1E);
		
		Exit room2_1U = new Exit(Exit.UP, room3_1);
		Exit room3_1D = new Exit(Exit.DOWN, room2_1);
		room2_1.addExit(room2_1U);
		room3_1.addExit(room3_1D);
		
		Exit room3_1E = new Exit(Exit.EAST, room3_2);
		Exit room3_2W = new Exit(Exit.WEST, room3_1);
		room3_1.addExit(room3_1E);
		room3_2.addExit(room3_2W);
		
		Exit room3_2S = new Exit(Exit.SOUTH, room3_4);
		Exit room3_4N = new Exit(Exit.NORTH, room3_2);
		room3_2.addExit(room3_2S);
		room3_4.addExit(room3_4N);
		
		Exit room3_4S = new Exit(Exit.SOUTH, room3_6);
		Exit room3_6N = new Exit(Exit.NORTH, room3_4);
		room3_4.addExit(room3_4S);
		room3_6.addExit(room3_6N);
		
		Exit room3_6W = new Exit(Exit.WEST, room3_5);
		Exit room3_5E = new Exit(Exit.EAST, room3_6);
		room3_6.addExit(room3_6W);
		room3_5.addExit(room3_5E);
		
		Exit room3_5N = new Exit(Exit.NORTH, room3_3);
		Exit room3_3S = new Exit(Exit.SOUTH, room3_5);
		room3_5.addExit(room3_5N);
		room3_3.addExit(room3_3S);
		
		Exit room3_3D = new Exit(Exit.DOWN, room2_3);
		Exit room2_3U = new Exit(Exit.UP, room3_3);
		room3_3.addExit(room3_3D);
		room2_3.addExit(room2_3U);
		
		Exit room2_3D = new Exit(Exit.DOWN, room1_3);
		Exit room1_3U = new Exit(Exit.UP, room2_3);
		room2_3.addExit(room2_3D);
		room1_3.addExit(room1_3U);
		
		Exit room1_3W = new Exit(Exit.WEST, room0_2);
		room1_3.addExit(room1_3W);
		
		Exit room0_2W = new Exit(Exit.WEST, room0_1);
		Exit room0_1E = new Exit(Exit.EAST, room0_2);
		room0_2.addExit(room0_2W);
		room0_1.addExit(room0_1E);
		
		Exit room0_1S = new Exit(Exit.SOUTH, room0_3);
		Exit room0_3N = new Exit(Exit.NORTH, room0_1);
		room0_1.addExit(room0_1S);
		room0_3.addExit(room0_3N);
		
		Exit room0_3E = new Exit(Exit.EAST, room0_4);
		Exit room0_4W = new Exit(Exit.WEST, room0_3);
		room0_3.addExit(room0_3E);
		room0_4.addExit(room0_4W);
		
		Exit room0_3S = new Exit(Exit.SOUTH, room0_5);
		Exit room0_5N = new Exit(Exit.NORTH, room0_3);
		room0_3.addExit(room0_3S);
		room0_5.addExit(room0_5N);
		
		Exit room0_5E = new Exit(Exit.EAST, room0_6);
		Exit room0_6W = new Exit(Exit.WEST, room0_5);
		room0_5.addExit(room0_5E);
		room0_6.addExit(room0_6W);
		
		Exit room0_6S = new Exit(Exit.SOUTH, room0_7);
		room0_6.addExit(room0_6S);
		
		// Setup Items
		Item teleporter = new Teleporter(game, room1_1);
		Item desk = new Desk(game);
		Item bones = new Bones(game);
		Item chair = new Chair(game);
		
		// set Items to Locations
		chair.setLocation(room1_4);
		teleporter.setLocation(room0_4);
		desk.setLocation(room1_2);
		bones.setLocation(room1_4);
		
		// Add items to game world.
		game.addItem(chair);
		game.addItem(teleporter);
		game.addItem(desk);
		game.addItem(bones);
		
		// Set starting location
		player.setLocation(room1_1);
		
		game.processCommand("LOOK");
		while (true)
		{
			if(game.isGameOver())
			{
				System.out.println(GameEngine.getMessage(game));
				System.exit(0);
			}
			
			System.out.println (GameEngine.getMessage(game));
			System.out.print("Command: ");
			game.processCommand(in.nextLine());
		}
	}
}
