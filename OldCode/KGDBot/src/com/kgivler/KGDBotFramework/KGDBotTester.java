/*
 * KGDBotTester.java
 * A test bot for testing the KGDBot framework
 * 
 * Version: 0.0.6
 * Released in to the public domain
 * Written by: Kyle Givler
 *
 *
 * $Id: KGDBotTester.java,v 1.1 2011/03/06 23:10:01 kwgivler Exp $
 * $Log: KGDBotTester.java,v $
 * Revision 1.1  2011/03/06 23:10:01  kwgivler
 * Change some packages. Start extensible bot
 *
 * Revision 1.34  2011/03/06 21:37:39  kwgivler
 * Remove experimental code
 *
 * Revision 1.33  2011/03/06 20:47:58  kwgivler
 * *** empty log message ***
 *
 * Revision 1.32  2011/03/06 18:52:47  kwgivler
 * API changes
 *
 * Revision 1.31  2011/03/06 17:57:26  kwgivler
 * Break down commands sent on connect. Add ablity to set realname
 *
 * Revision 1.30  2011/03/04 17:09:49  kwgivler
 * More Refactoring
 *
 * Revision 1.29  2011/03/04 16:56:12  kwgivler
 * Refactoring
 *
 * Revision 1.28  2011/02/15 05:47:29  kwgivler
 * Version 0.0.6
 *
 * 
 */


package com.kgivler.KGDBotFramework;

import com.kgivler.KGDBotFramework.Message;

/**
 * An example IRC bot.
 * It repeats what is said in a channel
 * 
 * @author kwgivler
 *
 */
public class KGDBotTester extends IRCBot {
	private final String prefix = "^";
	private final String channel = "#souleater";
	private final String GREETING = "Hello! I am the KGDTestBot!";
	private final static String server = "192.168.1.5";
	private final static int port = 6667;
	private final static String nick = "KGDTestBot";

	public KGDBotTester()
	{
		super(server, port, nick);
	}
	
	// ------------------------------------------------------------------------------
	
	/**
	 * Start the bot
	 */
	public void start()
	{
		connect();
		join(channel);
		sendPrivMsg(channel, GREETING);
	}
	
	// ------------------------------------------------------------------------------
	
	public void receiveMessage(Message message)
	{
		if (message.getType() == Message.CHANMSG)
			receivedChannelMessage(message);

		if (message.getType() == Message.PRIVMSG)
			receivedPrivMsg(message);
	}

	// ------------------------------------------------------------------------------
	
	private void receivedPrivMsg(Message message)
	{
		sendPrivMsg(message.getChannel(), "Someone messaged me! Sender: " + message.getNick() + " Message: " + message.getMessage());
	}

	// ------------------------------------------------------------------------------
	
	private void receivedChannelMessage(Message message)
	{		
		if(message.getMessage().equalsIgnoreCase(prefix + "quit"))
		{
			sendPrivMsg(channel, "Goodbye, cruel world!");
			part(channel);
			disconnect();
			System.exit(0);
		}

		sendPrivMsg(message.getChannel(), message.getNick() + " sent the message: " + message.getMessage());
	}

	// ------------------------------------------------------------------------------
	
	public static void main(String[] args)
	{
		KGDBotTester tester = new KGDBotTester();
		tester.start();
	}
}
