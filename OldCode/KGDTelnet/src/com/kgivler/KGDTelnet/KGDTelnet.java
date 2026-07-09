package com.kgivler.KGDTelnet;

import java.io.IOException;
import java.util.Scanner;

public class KGDTelnet {

	public static void main(String[] args) throws IOException
	{
		String server = "";
		int port = -1;
		
		if (args.length == 2)
		{
			server = args[0];
			port = Integer.parseInt(args[1]);
		}
		else if(args.length == 1)
		{
			server = args[0];
			port = 23;
		}
		else
		{
			System.out.println("Usage: KGDTelnet (server) (port)");
			System.exit(0);
		}
		
		TelnetClient client = new TelnetClient(server, port);
		
		client.connect();
		
		Scanner clientMessage = new Scanner(System.in);
		while (client.isConnected())
		{
			String output = clientMessage.nextLine();
			client.send(output);
		}
		
		System.exit(0);
	}
	
}
