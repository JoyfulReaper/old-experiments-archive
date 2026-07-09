package com.kgivler.KGDBot;

import java.io.IOException;
import java.util.HashMap;

import com.kgivler.KGDBotFramework.IRCBot;
import com.kgivler.KGDBotFramework.Message;

public class KGDBot extends IRCBot{
	private static String server;
	private static int port;
	private static String nick;
	private static String realName;
	
	public KGDBot(String server, int port, String nick, String realName)
	{		
		super(server, port, nick, realName);
	}

	public void start()
	{

	}
	
	public void receiveMessage(Message message)
	{
		
	}
	
	public static void main(String[] args)
	{
		HashMap<String,String> settings = null;
		try {
			settings = ConfigReader.readConfig();
			if(settings == null)
			{
				System.out.println("A required config option is missing. See logger output for more information.");
				System.exit(0);
			}
		} catch (IOException e) {
			System.out.println("Error loading config file!");
			e.printStackTrace();
			System.exit(0);
		}
		
		server = settings.get("server");
		port = Integer.parseInt(settings.get("port"));
		nick = settings.get("nick");
		realName = settings.get("realname");
		
		KGDBot bot = new KGDBot(server, port, nick, realName);
		bot.start();
	}
}
