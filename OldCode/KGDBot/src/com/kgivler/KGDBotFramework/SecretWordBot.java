/*
 * KGDBot:
 * SecretWordBot 
 * A simple IRC game playing bot
 * 
 * Version: 0.0.6
 * Released in to the public domain
 * Written by: Kyle Givler
 *
 *
 * $Id: SecretWordBot.java,v 1.1 2011/03/06 23:10:01 kwgivler Exp $
 * $Log: SecretWordBot.java,v $
 * Revision 1.1  2011/03/06 23:10:01  kwgivler
 * Change some packages. Start extensible bot
 *
 * Revision 1.31  2011/03/06 18:52:47  kwgivler
 * API changes
 *
 * Revision 1.30  2011/03/04 17:29:57  kwgivler
 * Refactor
 *
 * Revision 1.29  2011/03/04 16:56:12  kwgivler
 * Refactoring
 *
 * Revision 1.28  2011/02/15 05:56:08  kwgivler
 * Fix minor bug in checkForSecretWord()
 *
 * Revision 1.27  2011/02/15 05:47:29  kwgivler
 * Version 0.0.6
 *
 * 
 */

package com.kgivler.KGDBotFramework;

import java.io.IOException;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.LinkedList;

import com.kgivler.KGDBotFramework.Message;

/**
 * The SecretWordBot is an example IRC bot.
 * It plays a game with you over IRC. The bot picks a "secret word"
 * and keeps score of who guesses the secret word.
 * 
 * @author kwgivler
 *
 */
public class SecretWordBot extends IRCBot {
	// Settings
	private final static String server = "192.168.1.5"; // IRC Server
	private final static int port = 6667; // IRC port
	private final static String nick = "SecretBot"; // bot nick
	private final String initialChannel = "#test"; // Channel to join
	private String prefix = "@"; // Command prefix

	private File scoreFile = new File("scores.dat"); //Score file
	private final int MAX_LIST_SIZE = 1000; // LinkList will not grow larger than this many words.
	private final int MIN_FOR_SECRET = 40; // LinkedList must have at least this many words before picking a secret word
	private final String GREETING = "Hello, I am the SecretWord bot! I save scores on quit!"; // Greeting on channel join
	// End of settings

	private Logger logger = Logger.getLogger("SecretWordBot"); // Logger
	private boolean secretPicked = false; // Have we picked a word?
	private String secretWord; // The secret word!
	private HashMap<String, Integer> scores; // TODO: Something better than a HashMap? Anyway to know what keys are in the HashMap? An Array of keys?
	private LinkedList<String> wordList = new LinkedList<String>(); // words to pick the secret word from

	public SecretWordBot()
	{
		super(server, port, nick);
	}
	
	public void start() throws IOException
	{
		if(scoreFile.exists())
		{
			try{
				ObjectInputStream infile = new ObjectInputStream(new FileInputStream(scoreFile));
				scores = (HashMap<String, Integer>) infile.readObject(); // FIXME: unchecked cast
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		else
		{
			scores = new HashMap<String, Integer>();
		}

		connect();
		join(initialChannel);
		sendPrivMsg(initialChannel, GREETING);
	}

	// -------------------------------------------------------------------------------------------------------

	// We were sent a private message. Lets tell everyone!
	private void receivedPrivMsg(Message message)
	{
		ArrayList<String> channels = getChannels();
		for(int i = 0; i < channels.size(); i++)
		{
			sendPrivMsg(channels.get(i), "Someone messaged me! Sender: " + message.getNick() + " Message: " + message.getMessage());
		}

	}

	// -------------------------------------------------------------------------------------------------------

	/**
	 * Help command
	 */
	private void help(String fromNick, String fromChannel)
	{
		sendPrivMsg(fromChannel, fromNick + ": commands: ");
		sendPrivMsg(fromChannel, prefix + "quit " + prefix + "help " + prefix + "join <channel> " + prefix + "leave <channel> " + prefix + "score <nick> " + prefix + "cheat");
	}

	// -------------------------------------------------------------------------------------------------------

	/**
	 * Join command
	 */
	private void join(String fromNick, String fromChannel, String checkMessage)
	{
		StringTokenizer tokenizer = new StringTokenizer(checkMessage);
		tokenizer.nextToken(); // strip join

		if(tokenizer.hasMoreTokens())
		{
			String newChannel = tokenizer.nextToken();
			join(newChannel);
			sendPrivMsg(newChannel, GREETING);
		}
		else
			sendPrivMsg(fromChannel, "Join what channel?");
	}

	// -------------------------------------------------------------------------------------------------------

	/**
	 * Leave command
	 */
	private void leave(String fromNick, String fromChannel, String checkMessage)
	{
		StringTokenizer tokenizer = new StringTokenizer(checkMessage);
		tokenizer.nextToken(); // strip leave

		if(!tokenizer.hasMoreTokens())
		{
			sendPrivMsg(fromChannel, "Leave which channel?");
			return;
		}
		String channel = tokenizer.nextToken();

		ArrayList<String> channels = getChannels();
		if(channels.size() == 1)
		{
			sendPrivMsg(fromChannel, "Only in one channel!");
			return;
		}
		for(int i = 0; i < channels.size(); i++)
		{
			if(channels.get(i).equals(channel))
			{
				sendPrivMsg(channels.get(i), fromNick + " in " + fromChannel + " told me to leave this channel! Goodbye!");
				part(channel);
			}
		}
	}

	// -------------------------------------------------------------------------------------------------------

	/**
	 * Cheat command
	 */
	private void cheat(String fromNick, String fromChannel)
	{
		ArrayList<String> channels = getChannels();
		int score;

		if(scores.get(fromNick) == null)
		{
			score = -5;
			scores.put(fromNick, score);
		}
		else
		{
			score = scores.get(fromNick);
			score = score - 5;
			scores.put(fromNick, score);
		}

		if(!secretPicked)
		{
			for (int i = 0; i < channels.size(); i++)
			{
				sendPrivMsg(channels.get(i), fromNick + " in channel " + fromChannel + " wants to cheat!");
				sendPrivMsg(channels.get(i), "However, I haven't picked a secret word yet! Too bad for " + fromNick + "!");
				sendPrivMsg(channels.get(i), fromNick + "'s score is now: " + score);
			}
		}
		for (int i = 0; i < channels.size(); i++)
		{
			sendPrivMsg(channels.get(i), fromNick + " in channel " + fromChannel + " is cheating!");
			sendPrivMsg(channels.get(i), "The secret word is: " + secretWord);
			sendPrivMsg(channels.get(i), fromNick + "'s score is now: " + score);
		}	
	}

	// -------------------------------------------------------------------------------------------------------

	/**
	 * Quit Command
	 */
	private void quit(String fromNick, String fromChannel)
	{
		try {
			ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream(scoreFile));
			outfile.writeObject(scores);
			outfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ArrayList<String> channels = getChannels();
			for(int i = 0; i < channels.size(); i++)
			{
				sendPrivMsg(channels.get(i), fromNick + " in " + fromChannel + " told me to quit! Goodbye, cruel world!");
				part(channels.get(i));
			}
			disconnect();
			System.exit(0);
		}
	}

	// -------------------------------------------------------------------------------------------------------

	/**
	 * Score command
	 */
	private void score(String fromNick, String fromChannel, String checkMessage)
	{
		StringTokenizer tokenizer = new StringTokenizer(checkMessage);
		tokenizer.nextToken(); // skip score

		if(!tokenizer.hasMoreTokens())
		{
			sendPrivMsg(fromChannel, fromNick + ": See who's score?");
			return;
		}

		String scoreOf = tokenizer.nextToken();
		scoreOf = scoreOf.toLowerCase();
		int score;

		if(scores.get(scoreOf) == null)
		{
			sendPrivMsg(fromChannel, fromNick + ": " + scoreOf + " has not scored!");
		}
		else
		{
			score = scores.get(scoreOf);
			sendPrivMsg(fromChannel, scoreOf + "'s score is: " + score);
		}
	}

	// -------------------------------------------------------------------------------------------------------

	/*
	 * Add words to word bank
	 * TODO: Should we not add duplicates? 
	 * Words such as "the, and, a..." are more likely to be picked as the secret word
	 */
	private void addWordsToList(String checkMessage)
	{
		checkMessage = checkMessage.replaceAll("[^A-Za-z ]", "");
		String[] words = checkMessage.split(" ");
		for(int i = 0; i < words.length; i++)
		{
			wordList.add(words[i]); // adds to end of list
		}

		logger.log(Level.INFO, "wordList size: " + wordList.size());

		if (wordList.size() > MAX_LIST_SIZE)
		{
			int numberToRemove = wordList.size() - MAX_LIST_SIZE;
			for(int i = 0; i < numberToRemove; i++)
			{
				wordList.remove(); // removes from head of list
			}
			logger.log(Level.INFO, "Removed words, new wordList size: " + wordList.size());
		}
	}

	// -------------------------------------------------------------------------------------------------------

	/*
	 * Used to pick the secret word
	 */
	private void pickSecretWord()
	{
		if (wordList.size() >= MIN_FOR_SECRET)
		{
			logger.log(Level.INFO, "I have enough words to pick one! Picking word...");
			int secretElement = (int) (Math.random() * (wordList.size() -1) );
			secretWord = wordList.get(secretElement).toLowerCase();
			secretPicked = true;
			logger.log(Level.INFO, "I picked: " + secretWord);
		}
		else
		{
			logger.log(Level.INFO, "I was told to pick a word, but I don't know enough words yet!");
		}
	}

	// -------------------------------------------------------------------------------------------------------

	/*
	 * Did someone say the secret word?
	 */
	private void checkForSecretWord(String fromNick, String fromChannel, String checkMessage)
	{
		checkMessage = checkMessage.replaceAll("[^A-Za-z ]", "");
		StringTokenizer tokenizer = new StringTokenizer(checkMessage);

		while (tokenizer.hasMoreTokens())
		{
			String checkForSecret = tokenizer.nextToken();
			fromNick = fromNick.toLowerCase();
			if (checkForSecret.equals(secretWord))
			{
				int score;
				if(scores.get(fromNick) == null)
				{
					score = 1;
					scores.put(fromNick, score);
				}
				else
				{
					score = scores.get(fromNick);
					score++;
					scores.put(fromNick, score);
				}

				ArrayList<String> channels = getChannels();
				for(int i = 0; i < channels.size(); i++)
				{
					sendPrivMsg(channels.get(i), fromNick + " said the secret word in " + fromChannel + "! It was: " + secretWord);
					sendPrivMsg(channels.get(i), fromNick + "'s score: " + score);
				}
				secretPicked = false;
				pickSecretWord();
				return;
			}
		}
	}

	// -------------------------------------------------------------------------------------------------------

	/*
	 * Something was said on a channel we are in
	 */
	private void receivedChannelMessage(Message message)
	{		
		String checkMessage = message.getMessage().toLowerCase();
		String fromChannel = message.getChannel();
		String fromNick = message.getNick();

		// Show a nick's score
		if(checkMessage.startsWith(prefix + "score"))
		{
			score(fromNick, fromChannel, checkMessage);
		}

		// Someone wants to cheat!
		if(checkMessage.equalsIgnoreCase(prefix + "cheat"))
		{
			cheat(fromNick, fromChannel);
		}

		// We were told to quit
		if(checkMessage.equalsIgnoreCase(prefix + "quit"))
		{
			quit(fromNick, fromChannel);
		}

		// We were asked for help
		if(checkMessage.startsWith(prefix + "help"))
		{
			help(fromNick, fromChannel);
		}

		// We were told to join another channel
		if(checkMessage.startsWith(prefix + "join"))
		{
			join(fromNick, fromChannel, checkMessage);
		}

		// We were told to leave a channel
		if(checkMessage.startsWith(prefix + "leave"))
		{
			leave(fromNick, fromChannel, checkMessage);
		}

		addWordsToList(checkMessage);

		// We need to pick a secret word
		if(!secretPicked)
		{
			pickSecretWord();
		}
		else // Did someone say the secret word?
		{
			checkForSecretWord(fromNick, fromChannel, checkMessage);
		}
	}


	public void receiveMessage(Message message) {

		if (message.getType() == Message.CHANMSG) // Message to a channel
			receivedChannelMessage(message);

		if (message.getType() == Message.PRIVMSG) // Message to the bot
			receivedPrivMsg(message);
	}

	public static void main(String[] args) throws IOException
	{
		SecretWordBot bot = new SecretWordBot();
		bot.start();
	}
}