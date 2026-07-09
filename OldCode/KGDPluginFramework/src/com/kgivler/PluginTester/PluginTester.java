package com.kgivler.PluginTester;

import java.util.HashMap;
import java.util.Scanner;
import com.kgivler.PluginFramework.Plugin;
import com.kgivler.PluginFramework.PluginManager;

public class PluginTester {
	private static HashMap<String, Plugin> handlers = new HashMap<String, Plugin>();
	public static void main (String[] args) throws Exception
	{
		new PluginTester();
	}
	
	public PluginTester() throws Exception
	{
		Scanner in = new Scanner(System.in);
		PluginManager pm = new PluginManager();
		pm.loadPlugins();
		pm.initPlugins();
		
		while(true)
		{
			System.out.print("Command: ");
			String command = in.nextLine().toLowerCase();
			
			if(command.equalsIgnoreCase("quit"))
			{
				System.exit(0);
				pm.unloadPlugins();
			}
			
			if(command.equalsIgnoreCase("unload"))
			{
				pm.unloadPlugins();
				continue;
			}
			
			if(handlers.containsKey(command))
			{
				handlers.get(command).processCommand(command);
			}
			else
				System.out.println("No handler registered for " + command);
		}
	}
	
	public static void registerCommand(String command, Plugin plugin)
	{
		handlers.put(command.toLowerCase(), plugin);
	}
	
	public static void unregisterCommand(String command, Plugin plugin)
	{
		command = command.toLowerCase();
		if(handlers.containsKey(command))
		{
			if (handlers.get(command) == plugin)
				handlers.remove(command);
		}
	}
}
