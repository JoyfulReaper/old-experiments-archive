package com.kgivler.KGDBotFramework;

import java.util.ArrayList;

public abstract class IRCBot {
	private BotCore core;
	
	public IRCBot(String server, int port, String nick, String realName)
	{
		core = new BotCore(server, port, nick, realName, this);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	public IRCBot(String server, int port, String nick)
	{
		this(server, port, nick, "KGDBot");
	}
	
	// --------------------------------------------------------------------------------------------------
	
	public IRCBot(String server, int port)
	{
		this(server, port, "KGDBot", "KGDBot");
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * Connect to server
	 */
	public void connect()
	{
		core.connect();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * Disconnect from server
	 */
	public void disconnect()
	{
		core.disconnect();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * Get bot's nick
	 * @return Bot's nick
	 */
	public String getNick()
	{
		return core.getNick();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * get list of channels bot is in
	 * @return channels bot is currently in
	 */
	public ArrayList<String> getChannels()
	{
		return core.getChannels();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * Join a channel
	 * @param channel to join
	 */
	public void join(String channel)
	{
		core.join(channel);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * Leave a channel
	 * @param channel to leave
	 */
	public void part(String channel)
	{
		core.part(channel);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * Send a PRIVMSG (message to channel or nick)
	 * @param channel channel or nick to send message to
	 * @param message message to send
	 */
	public void sendPrivMsg(String channel, String message)
	{
		core.sendPrivMsg(channel, message);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * Send message directly to irc server
	 * @param command message to send to server
	 */
	public void sendRawMessage(String command)
	{
		core.sendRawMessage(command);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * @return true is bot is connected, false if not connected
	 */
	public boolean isConnected()
	{
		return core.isConnected();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	
	public abstract void receiveMessage(Message message);

}
