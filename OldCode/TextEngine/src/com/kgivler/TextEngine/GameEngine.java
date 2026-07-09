package com.kgivler.TextEngine;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class GameEngine {
	private String messages;
	private ArrayList<Location> locations;
	private Player player;
	private int turn;
	private boolean gameOver;

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	public GameEngine(Player player)
	{
		this.player = player;
		this.turn = 0;
		this.messages = "";
		this.locations = new ArrayList<Location>();
		this.gameOver = false;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void processCommand(String command)
	{
		command = command.toUpperCase();
		StringTokenizer st = new StringTokenizer(command);
		if(!st.hasMoreTokens())
			return;
		command = st.nextToken();


		if(command.equals("QUIT") || command.equals("EXIT"))
		{
			processQuit();
			return;
		}

		if(command.equals("INV") || command.equals("INVENTORY"))
		{
			processInv();
			return;
		}

		if(command.equals("LOOK") || command.equals("VIEW")
				|| command.equals("EXAMINE"))
		{
			processLook(st);
			return;
		}

		if(command.equals("USE"))
		{
			processUse(st);
			return;
		}
		if(command.equals("TAKE") || command.equals("GET")
				|| command.equals("STEAL"))
		{
			processTake(st);
			return;
		}
		if(command.equals("GO") || command.equals("RUN"))
		{
			processGo(st);
			return;
		}
		if(command.equals("DROP") || command.equals("DISCARD"))
		{
			processDrop(st);
			return;
		}


		addMessage(this, "\nYou don't know how to do that!");
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void processDrop(StringTokenizer st)
	{
		if(st.hasMoreTokens())
		{
			String dropWhat = st.nextToken();
			int numberOfItems = player.getItems().size();
			for(int i = 0; i < numberOfItems; i++)
			{
				if(dropWhat.equals(player.getItems().get(i).getName()))
				{
					player.getItems().get(i).setLocation(player.getLocation());
					Item.addItem(player.getItems().get(i));
					player.removeItem(player.getItems().get(i));
					addMessage(this, "\nYou dropped the " + dropWhat + ".");
				}
			}
		}
		else
			addMessage(this, "\nDrop what?");
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void processInv()
	{
		int numberOfItems = player.getItems().size();

		if(numberOfItems == 0)
			addMessage(this, "\nYou don't have any items!");
		else
		{
			for(int i = 0; i < numberOfItems; i++)
			{
				addMessage(this, player.getItems().get(i).getName() + " ");
			}
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void processQuit()
	{
		gameOver = true;
		addMessage(this, "\nGood-bye...");
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void processLook(StringTokenizer st)
	{
		if(st.hasMoreTokens())
		{
			// look for items in the world
			String lookAt = st.nextToken();
			int numberOfItems = player.getLocation().getItems().size();

			for(int i = 0; i < numberOfItems; i++)
			{
				if(lookAt.equals(player.getLocation().getItems().get(i).getName()))
				{
					player.getLocation().getItems().get(i).processLook();
					return;
				}
			}

			// Look in your inv
			numberOfItems = player.getItems().size();

			for(int i = 0; i < numberOfItems; i++)
			{
				if(lookAt.equals(player.getItems().get(i).getName()))
				{
					addMessage(this, "\n" + player.getItems().get(i).getDescription() + "\n");
					return;
				}
			}

			addMessage(this, "\nYou don't see that...");
			return;
		}

		if(!st.hasMoreTokens())
		{
			// look at the Location
			player.getLocation().processLook(this);
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void processGo(StringTokenizer st)
	{
		if(st.hasMoreTokens())
		{
			String goWhere = st.nextToken();
			int numberOfExits = player.getLocation().getExits().size();

			for(int i = 0; i < numberOfExits; i++)
			{
				if(goWhere.equals(player.getLocation().getExits().get(i).getDirectionShort()) || 
						goWhere.equals(player.getLocation().getExits().get(i).getDirectionLong()))
				{
					player.setLocation(player.getLocation().getExits().get(i).getLeadsTo());
					turn++;
					processCommand("LOOK");
					return;
				}
			}
			addMessage(this, "\nYou can't go that way!");
		}
		else
		{
			addMessage(this, "\nGo where?");
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void processTake(StringTokenizer st)
	{
		if(!st.hasMoreTokens())
		{
			addMessage(this, "\nTake what?");
		}
		else
		{
			String itemToTake = st.nextToken();
			int numberOfItems = player.getLocation().getItems().size();

			for(int i = 0; i < numberOfItems; i++)
			{
				if (player.getLocation().getItems().get(i).getName().equals(itemToTake))
				{
					player.getLocation().getItems().get(i).processTake();
					return;
				}
			}
		}
		addMessage(this, "\nYou don't see that...");
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void processUse(StringTokenizer st)
	{
		if(!st.hasMoreTokens())
		{
			addMessage(this, "\nUse what?");
		}
		else
		{
			String useWhat = st.nextToken();
			// look for item to use in player inventory
			int numberOfItems = player.getItems().size();
			for(int i = 0; i < numberOfItems; i++)
			{
				if(useWhat.equals(player.getItems().get(i).getName()))
				{
					player.getItems().get(i).processUse();
					return;
				}
			}
			
			numberOfItems = player.getLocation().getItems().size();
			for(int i = 0; i < numberOfItems; i++)
			{
				if(useWhat.equals(player.getLocation().getItems().get(i).getName()))
				{
					player.getLocation().getItems().get(i).processUse();
					return;
				}
			}
			addMessage(this, "You don't see that!");
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	public static void addMessage(GameEngine engine, String message)
	{
		engine.messages += message;
	}
	public static String getMessage(GameEngine engine)
	{
		String tmp = engine.messages;
		engine.messages = "";
		return tmp;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	public void addItem(Item item)
	{
		Item.addItem(item);
	}
	public void removeItem(Item item)
	{
		Item.removeItem(item);
	}

	public ArrayList<Item> getItemsAtLocation(Location loc)
	{
		ArrayList<Item> itemsAtLocation = loc.getItems();
		return itemsAtLocation;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	public void addLocation(Location location)
	{
		locations.add(location);
	}
	public void removeLocation(Location location)
	{
		locations.remove(location);
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	public void getLocation(Character character)
	{
		character.getLocation();
	}
	public void setLocation(Character character, Location location)
	{
		character.setLocation(location);
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean isGameOver()
	{
		return gameOver;
	}
	public void gameIsOver()
	{
		this.gameOver = true;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------

	public int getTurn()
	{
		return turn;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	public Player getPlayer()
	{
		return player;
	}
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
}
