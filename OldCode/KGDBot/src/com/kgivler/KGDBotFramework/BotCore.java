/*
 * KGDBot:
 * KDGBot IRC bot
 * BotCore.java
 * 
 * IRC Bot framework
 * See SecretWordBot for a sample IRC bot
 * 
 * Version: 0.0.6
 * Released in to the public domain
 * Written by: Kyle Givler
 *
 *
 * $Id: BotCore.java,v 1.1 2011/03/06 23:10:01 kwgivler Exp $
 * $Log: BotCore.java,v $
 * Revision 1.1  2011/03/06 23:10:01  kwgivler
 * Change some packages. Start extensible bot
 *
 * Revision 1.5  2011/03/06 18:52:47  kwgivler
 * API changes
 *
 * Revision 1.4  2011/03/06 18:12:41  kwgivler
 * going to refactor
 *
 * Revision 1.3  2011/03/06 17:57:26  kwgivler
 * Break down commands sent on connect. Add ablity to set realname
 *
 * Revision 1.2  2011/03/04 17:09:49  kwgivler
 * More Refactoring
 *
 * Revision 1.1  2011/03/04 16:56:12  kwgivler
 * Refactoring
 *
 * 
 */

package com.kgivler.KGDBotFramework;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import com.kgivler.KGDBotFramework.Message;

public class BotCore {
	private String server; // IRC Server address
	private int port; // port on which to connect
	private String nick; // Bot's nick on server
	private String realName; // Bot's "realname"

	Socket socket;
	private Scanner in; // Scanner for reading lines from the server
	private PrintWriter out; // PrintWriter for sending lines to the server
	private ListenThread listenThread; // Thread which listens for server output
	private IRCBot bot; // The bot we are providing services for

	private ArrayList<String> channels = new ArrayList<String>(); // Channels the bot is currently in
	private boolean connected = false; // are we connected?
	private MessageParser parser = new MessageParser(this);
	private Logger logger; // Used for logging

	/**
	 * KGDBot constructor
	 * @param server IRC Server Address
	 * @param port IRC Port
	 * @param nick nickname for the bot
	 * @param realName the realname of the bot
	 * @param bot the bot we are providing services for
	 */
	public BotCore(String server, int port, String nick, String realName, IRCBot bot)
	{
		logger = Logger.getLogger("KGDBotFramework");
		this.realName = realName;
		this.port = port;
		this.nick = nick;
		this.server = server;
		this.bot = bot;
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * KGDBot constructor
	 * @param server IRC Server Address
	 * @param port IRC Port
	 * @param nick nickname for the bot
	 * @param bot the bot we are providing services for
	 */
	public BotCore(String server, int port, String nick, IRCBot bot)
	{
		this(server, port, nick, "KGDBot", bot);
	}
	// --------------------------------------------------------------------------------------------------

	/**
	 * KGDBot constructor
	 * @param server IRC Server address
	 * @param port IRC port
	 * @param bot the bot we are providing services for
	 */
	public BotCore(String server, int port, IRCBot bot)
	{
		this(server, port, "KGDBot", bot);
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Connect to IRC server
	 */
	public void connect()
	{
		try {
			socket = new Socket(server, port);
			InputStream instream = socket.getInputStream();
			OutputStream outstream = socket.getOutputStream();
			in = new Scanner(instream);
			out = new PrintWriter(outstream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// Set up thread which listens for server output
		listenThread = new ListenThread(this, in);
		Thread serverListener = new Thread(listenThread);
		serverListener.start();

		String hostName="localhost";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			// Get hostname
			hostName = addr.getHostName();
			logger.info("hostname: " + hostName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// Register Connection
		sendRawMessage("NICK " + nick + "\r\n");
		sendRawMessage("USER " + nick + " " + hostName + " " + server + " :" + realName + "\r\n");

		while(!connected)
		{
			System.out.println("Connecting...");
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Disconnect from server
	 */
	public void disconnect()
	{
		if(!connected)
			logger.info("Not Connected");

		sendRawMessage("QUIT\r\n");
		try
		{
			socket.close();
			out.close();
			in.close();
		} catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}

		connected = false;
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Send string directly to server
	 * @param command String to send IRC server
	 */
	public void sendRawMessage(String command)
	{	
		logger.log(Level.INFO, "SENDING: " + command);
		out.print(command);
		out.flush();
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Join a channel
	 * @param channel channel to join
	 * @throws IllegalStateException if not connected
	 */
	public void join(String channel)
	{
		if(!connected)
			throw new IllegalStateException("Not Connected");

		sendRawMessage("JOIN " + channel + "\r\n");
		channels.add(channel);
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Part a channel
	 * @param channel channel to part
	 */
	public void part(String channel)
	{
		if(!connected)
			throw new IllegalStateException("Not connected");

		for (int i = 0; i < channels.size(); i++)
		{
			if(channels.get(i).equals(channel))
			{
				channels.remove(i);
				sendRawMessage("PART " + channel + "\r\n");
			}
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Send message to channel (PRIVMSG command)
	 * @param channel channel on which to send message
	 * @param message message to send to channel
	 * @throws IllegalStateException if not connected
	 */
	public void sendPrivMsg(String channel, String message)
	{		
		if(!connected)
			throw new IllegalStateException("Not Connected");

		sendRawMessage("PRIVMSG " + channel + " :" + message + "\r\n");
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Check if bot is connected
	 * @return true if connected to server, false if not connected
	 */
	public boolean isConnected()
	{
		return connected;
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Get the nick of the bot
	 * @return The bot's nick
	 */
	public String getNick()
	{
		return nick;
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Get the channels the bot is in
	 * @return An ArrayList of the channels the bot is in
	 */
	public ArrayList<String> getChannels()
	{
		ArrayList<String> myChannels = new ArrayList<String>();
		for(int i = 0; i < channels.size(); i++)
		{
			myChannels.add(channels.get(i));
		}
		return myChannels;
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Receive raw message from server
	 */
	protected void receiveRawMessage(String message)
	{
		parser.parseMessage(message);
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * Receive parsed message from message parser
	 */
	protected void receiveParsedMessage(Message message)
	{
		if(connected)
		{
			bot.receiveMessage(message);
		} else {
			connectReceiver(message);
		}
	}

	// --------------------------------------------------------------------------------------------------

	/** 
	 * Process messages during connection
	 */
	private void connectReceiver(Message message)
	{
		String command = message.getCommand();

		if(command.equals("433"))
		{
			System.out.println("Nick in use!");
			disconnect();
			System.exit(0);
		}

		if(command.equals("004"))
		{
			System.out.println("Connected!");
			connected = true;
		}
	}
}