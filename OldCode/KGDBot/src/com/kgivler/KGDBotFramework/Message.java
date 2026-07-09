/*
 * KGDBot:
 * Message.java
 * The message format used by the MessageParser
 * 
 * Version: 0.0.6
 * Released in to the public domain
 * Written by: Kyle Givler
 *
 *
 * $Id: Message.java,v 1.1 2011/03/06 23:10:01 kwgivler Exp $
 * $Log: Message.java,v $
 * Revision 1.1  2011/03/06 23:10:01  kwgivler
 * Change some packages. Start extensible bot
 *
 * Revision 1.11  2011/03/06 21:37:39  kwgivler
 * Remove experimental code
 *
 * Revision 1.10  2011/03/06 20:47:58  kwgivler
 * *** empty log message ***
 *
 * Revision 1.9  2011/03/04 17:23:23  kwgivler
 * Refactoring
 *
 * Revision 1.8  2011/02/15 05:47:29  kwgivler
 * Version 0.0.6
 *
 * 
 */


package com.kgivler.KGDBotFramework;

public class Message {
	private int type;
	private String[] parameters;
	private String message;
	private String nick;
	private String command;
	
	public static final int UNKNOWN = -1; // Message we do not know how to parse 
	public static final int CHANMSG = 1;  // PRIVMSG to a channel
	public static final int PRIVMSG = 2;  // PRIVMSG to the bot
	public static final int NUMERIC = 3;  // Numeric command
	
	/**
	 * Message Constructor
	 * @param type The Message type
	 * @param command The command portion of the message
	 * @param nick The nick associated with this message
	 * @param parameters The parameters to this message
	 * @param message The Message
	 */
	public Message(int type, String command, String nick, String[] parameters, String message)
	{
		this.type = type;
		this.parameters = parameters;
		this.message = message;
		this.nick = nick;
		this.command = command;
	}
	
	/**
	 * Get the type of this message
	 * @return The type of message
	 */
	public int getType()
	{
		return type;
	}
	
	/**
	 * Get the channel that is relevant to this message
	 * @return the channel relevant to this message
	 */
	public String[] getParameters()
	{
		return parameters;
	}
	
	/**
	 * Get the message
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}
	
	/**
	 * Get the nick that caused this message to be created
	 * @return the nick relevant to this message
	 */
	public String getNick()
	{
		return nick;
	}
	
	public String getCommand()
	{
		return command;
	}
	
	public String getChannel()
	{
		return parameters[0];
	}
}
