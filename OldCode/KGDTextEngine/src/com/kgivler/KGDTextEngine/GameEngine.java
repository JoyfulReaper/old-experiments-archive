package com.kgivler.KGDTextEngine;

import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class GameEngine {

	private ArrayList<Location> locations;
	private String message = "";
	private Character player;

	public GameEngine(Character player)
	{
		this.player = player;
		locations = new ArrayList<Location>();
	}

	/**
	 * Add an item to the game world
	 * @param item
	 */
	public void addItem(Item item)
	{
		ItemPool.addItem(item);
	}

	/**
	 * remove an item from the game world
	 * @param item
	 */
	public void removeItem(Item item)
	{
		ItemPool.removeItem(item);
	}

	/**
	 * Add a location to the game world
	 * @param location
	 */
	public void addLocation(Location location)
	{
		locations.add(location);
	}

	/**
	 * Remove a location from the game world
	 * @param location
	 */
	public void removeLocation(Location location)
	{
		locations.remove(location);
	}

	/**
	 * Add a message
	 * @param message
	 */
	public void addMessage(String message)
	{
		this.message += message;
	}

	/**
	 * Get pending messages
	 * @return
	 */
	public String getMessage()
	{
		String message = this.message;
		this.message = "";
		return message;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Process command
	 */
	public void processCommand(String command)
	{
		String[] commandAndParameters = command.split(" ");
		String[] parameters = new String[commandAndParameters.length - 1];
		String checkCommand = commandAndParameters[0];

		for(int i = 1; i < commandAndParameters.length; i++)
		{
			parameters[i - 1] = commandAndParameters[i];
		}

		if(checkCommand.startsWith("quit"))
			System.exit(0);
		else if(checkCommand.equalsIgnoreCase("look"))
			processLook(parameters);
		else if(checkCommand.equalsIgnoreCase("create"))
			processCreate(parameters);
		else if(checkCommand.equalsIgnoreCase("go"))
			processGo(parameters);
		else if (checkCommand.equalsIgnoreCase("set"))
			processSet(parameters);
		else
			addMessage("Unknown command\n");
	}

	/**
	 * Process "set" command
	 */
	public void processSet(String[] parameters)
	{
		Location playerLocation = player.getLocation();

		if(parameters.length == 0)
		{
			addMessage("\nSet What?");
			return;
		}

		if(parameters[0].equalsIgnoreCase("room") && parameters.length > 2)
		{
			if(parameters[1].equalsIgnoreCase("title"))
			{
				String title = "";
				for(int i = 2; i < parameters.length; i++)
				{
					title += parameters[i] + " ";
				}
				title = title.substring(0,title.length() - 1);
				playerLocation.setTitle(title);
			}
			else if(parameters[1].equalsIgnoreCase("description")
					|| parameters[1].equalsIgnoreCase("desc"))
			{
				String desc = "";
				for(int i = 2; i < parameters.length; i++)
				{
					desc += parameters[i] + " ";
				}
				desc = desc.substring(0,desc.length() - 1);
				playerLocation.setDescription(desc);
			}
		} 
		else
			addMessage("\nSet What?");
	}

	/**
	 * Process "go" command
	 */
	public void processGo(String[] parameters)
	{	
		if(parameters.length == 1)
		{
			Location playerLocation = player.getLocation();
			String goWhere = parameters[0].toUpperCase();
			ArrayList<Exit> exits = playerLocation.getExits();
			int numberOfExits = exits.size();

			for(int i = 0; i < numberOfExits; i++)
			{
				System.out.println(exits.get(i).getShortDirectionName());
				
				if( goWhere.equals(exits.get(i).getShortDirectionName()) || 
						goWhere.equals(exits.get(i).getDirectionName()) )
				{
					player.setLocation(exits.get(i).getLeadsTo());
					processCommand("LOOK");
					return;
				}
			}
			addMessage("\nYou can't go that way!");
		}
		else
		{
			addMessage("\nGo where?");
		}
	}

	/**
	 * Process "create" command
	 */
	public void processCreate(String[] parameters)
	{
		if(parameters.length == 0)
		{
			addMessage("\nCreate what?");
			return;
		}
		if(parameters[0].equalsIgnoreCase("room"))
			createRoom(parameters);
		else
			addMessage("\nCan't create that!");
	}

	/**
	 * process "look" command
	 */
	public void processLook(String[] parameters)
	{
		if(parameters.length == 0)
		{
			Location playerLocation = player.getLocation();
			addMessage("\n\"" + playerLocation.getTitle() + "\"\n");
			addMessage(playerLocation.getDescription() + "\n");

			addMessage("\nExits: ");
			ArrayList<Exit> exits = playerLocation.getExits();
			if(exits.size() == 0)
			{
				addMessage("No Exits\n");
			}
			else
			{
				for(int i = 0; i < exits.size(); i++)
				{
					addMessage(exits.get(i).getDirectionName() + " ");
				}
			}
		}
	}

	/**
	 * Create a new room
	 */
	public void createRoom(String[] parameters)
	{
		Location playerLoc = player.getLocation();	
		Location newLoc = new Location();
		Exit newExit = new Exit();
		String dir = parameters[1];
		int code = Exit.getDirectionCode(dir);

		newExit.setDirection(code);
		if(newExit.getDirection() == Exit.UNDEFINED)
		{
			newExit.setDirectionName(dir);
			newExit.setShortDirectionName(dir.substring(0, 1));
		}
		newExit.setLeadsTo(newLoc);
		playerLoc.addExit(newExit);

		Exit oppositeExit = new Exit(Exit.getOppositeDirection(code), playerLoc);
		newLoc.addExit(oppositeExit);
	}
}
