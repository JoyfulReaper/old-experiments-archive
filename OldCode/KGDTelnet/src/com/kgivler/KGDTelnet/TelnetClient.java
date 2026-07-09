package com.kgivler.KGDTelnet;

import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class TelnetClient {

	private String server;
	private int port;
	private Scanner in;
	private PrintWriter out;
	private Socket s;
	private boolean connected;
	private Queue<String> messages = new LinkedList<String>();

	public TelnetClient (String server, int port)
	{
		this.server = server;
		this.port = port;
	}

	public void connect() throws IOException
	{
		s = new Socket(server, port);
		InputStream instream = s.getInputStream();
		OutputStream outstream = s.getOutputStream();
		in = new Scanner(instream);
		out = new PrintWriter(outstream);

		connected = true;

		ListenThread listener = new ListenThread(this, in);
		Thread listenThread = new Thread(listener);
		listenThread.start();
	}

	public void disconnect() throws IOException
	{
		connected = false;
		s.close();
	}

	public void send(String message)
	{
		out.println(message);
		out.flush();
	}

	public boolean isConnected()
	{
		return connected;
	}
	
}
