/*
 * KGDBot:
 * ListenThread.java
 * Listens for message from the IRC server
 * 
 * Version: 0.0.6
 * Released in to the public domain
 * Written by: Kyle Givler
 *
 *
 * $Id: ListenThread.java,v 1.1 2011/03/06 23:10:01 kwgivler Exp $
 * $Log: ListenThread.java,v $
 * Revision 1.1  2011/03/06 23:10:01  kwgivler
 * Change some packages. Start extensible bot
 *
 * Revision 1.16  2011/03/06 18:52:47  kwgivler
 * API changes
 *
 * Revision 1.15  2011/03/04 16:56:12  kwgivler
 * Refactoring
 *
 * Revision 1.14  2011/02/15 05:47:29  kwgivler
 * Version 0.0.6
 *
 * 
 */


package com.kgivler.KGDBotFramework;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListenThread implements Runnable {
	
	private Scanner in; // Scanner from which we will get input
	private BotCore core;
	private Logger logger; // logging
	
	/**
	 * Constructor
	 * @param core bot core for which we are listening
	 * @param in Scanner we are listening to
	 */
	public ListenThread(BotCore core, Scanner in)
	{
		logger = Logger.getLogger("KGDBotFramework");
		this.core = core;
		this.in = in;
	}
	
	public void run()
	{
		while(true)
		{
			if( !in.hasNext() )
				return;
				
			String input = in.nextLine();
			logger.log(Level.INFO, "GOT: " + input);

			if (input.startsWith("PING ") )
				handlePing(input);
			else
			{
				core.receiveRawMessage(input);
			}
		}
	}
	
	
	/**
	 * Respond to PING
	 * @param ping String containing the PING we were sent
	 */
	private void handlePing(String ping)
	{	
		if( ping.startsWith("PING") )
		{
			String command = "PONG " + ping.substring(5) + "\r\n";
			core.sendRawMessage(command);
		}
	}
	
}