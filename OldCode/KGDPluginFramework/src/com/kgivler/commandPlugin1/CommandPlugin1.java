package com.kgivler.commandPlugin1;

import java.util.ArrayList;
import com.kgivler.PluginFramework.Plugin;
import com.kgivler.PluginTester.PluginTester;


public class CommandPlugin1 extends Plugin {
	private ArrayList<String> myCommands = new ArrayList<String>();
	public CommandPlugin1()
	{
		super("Command Plugin");
	}
	
	public void init()
	{
		PluginTester.registerCommand("test", this);
		myCommands.add("test");
		PluginTester.registerCommand("hello", this);
		myCommands.add("hello");
	}
	
	public void processCommand(String command)
	{
		System.out.println(getName() + ": Processing \"" + command + "\" command!");
		if(command.equals("hello"))
			System.out.println("Hello World!");
	}
	
	public void unload()
	{
		for(int i = 0; i < myCommands.size(); i++)
			PluginTester.unregisterCommand(myCommands.get(i), this);
	}
}
