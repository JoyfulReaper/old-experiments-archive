/*
 * KGDBot:
 * MessageParser.java
 * Parses message from the IRC server
 * 
 * Version: 0.0.6
 * Released in to the public domain
 * Written by: Kyle Givler
 *
 *
 * $Id: MessageParser.java,v 1.1 2011/03/06 23:10:01 kwgivler Exp $
 * $Log: MessageParser.java,v $
 * Revision 1.1  2011/03/06 23:10:01  kwgivler
 * Change some packages. Start extensible bot
 *
 * Revision 1.21  2011/03/06 21:37:39  kwgivler
 * Remove experimental code
 *
 * Revision 1.20  2011/03/06 20:47:58  kwgivler
 * *** empty log message ***
 *
 * Revision 1.19  2011/03/04 17:23:23  kwgivler
 * Refactoring
 *
 * Revision 1.18  2011/03/04 16:56:12  kwgivler
 * Refactoring
 *
 * Revision 1.17  2011/02/15 05:47:29  kwgivler
 * Version 0.0.6
 *
 * 
 */

package com.kgivler.KGDBotFramework;

import java.util.logging.Logger;
import java.util.logging.Level;

import com.kgivler.KGDBotFramework.Message;

public class MessageParser {
	private BotCore core;
	private Logger logger = Logger.getLogger("KGDBotFramework");

	/**
	 * Constructor
	 * @param bot The bot we are parsing messages for
	 */
	public MessageParser(BotCore core)
	{
		this.core = core;
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Attempt to parse raw message from server
	 * @param message Message to parse
	 */
	public void parseMessage(String message)
	{
		/*
		 * Originally Based on code from:
		 * http://calebdelnay.com/blog/2010/11/parsing-the-irc-message-format-as-a-client
		 */
		String trailing = "";
		String prefix = "";
		String command = "";
		String nick = "";
		String[] parameters;
		int prefixEnd = -1;
		int trailingStart = message.indexOf(" :");

		//logger.log(Level.INFO, "Message: " + message);

		// Extract nick
		if (message.indexOf("!") >= 0)
		{
			int nickEnd = message.indexOf("!");
			nick = message.substring(1, nickEnd);
		}

		// Extract prefix (contains :Nick!user@host)
		if (message.startsWith(":"))
		{
			prefixEnd = message.indexOf(" ");
			prefix = message.substring(1, prefixEnd);
		}

		// Extract trailing (Last part of message, example: The text sent to a channel)
		if (trailingStart >= 0)
			trailing = message.substring(trailingStart + 2);
		else
			trailingStart = message.length();

		// extract command and command parameters (example: command = PRIVMSG parameters = #channel)
		String[] commandAndParameters = message.substring(prefixEnd + 1, trailingStart).split(" ");
		command = commandAndParameters[0];
		parameters = new String[commandAndParameters.length -1];

		if (commandAndParameters.length > 1){
			for(int i = 1; i < commandAndParameters.length; i++){
				parameters[i-1] = commandAndParameters[i];
			}
		}

		int type = getType(command, parameters);
		sendMessage(type, command, nick, parameters, trailing);

		// Debugging
		String debug = "Type: " + type;
		debug += " Prefix: " + prefix;
		debug += " Command: " + command;
		for(int i = 0; i < parameters.length; i++)
		{
			debug += " Param[" + i + "]: " + parameters[i];
		}
		debug += " Trailing: " + trailing;
		logger.log(Level.INFO, debug);
	}

	// --------------------------------------------------------------------------------------------------

	private int getType(String command, String[] parameters)
	{
		// Determine message type
		int type = Message.UNKNOWN;
		if(command.equals("PRIVMSG"))
		{
			if( parameters[0].equals( core.getNick() ) )
				type = Message.PRIVMSG;
			else
				type = Message.CHANMSG;
		}

		try{
			Integer.parseInt(command);
			type = Message.NUMERIC;
		} catch (NumberFormatException e)
		{
			// do nothing, command is not numeric
		}
		return type;
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Add message to message queue
	 * @param type type of message
	 * @param command
	 * @param sender
	 * @param parameter
	 * @param message contents of message
	 */
	private void sendMessage(int type, String command, String sender, String[] parameters, String message)
	{
		Message newMessage = new Message(type, command, sender, parameters, message);
		core.receiveParsedMessage(newMessage);
	}
}
