package com.kgivler.KGDTelnet;

import java.io.IOException;
import java.util.Scanner;

public class ListenThread implements Runnable {

	private Scanner in;
	private TelnetClient client;
	
	public ListenThread(TelnetClient client, Scanner in)
	{
		this.in = in;
		this.client = client;
	}

	public void run()
	{
		while (in.hasNext())
		{
			System.out.println(in.nextLine());
		}
		
		try
		{
			client.disconnect();
			System.exit(0);
		}
		catch(IOException e)
		{
			System.out.println("IOException trying to disconnect!");
			System.exit(0);
		}
	}

}
