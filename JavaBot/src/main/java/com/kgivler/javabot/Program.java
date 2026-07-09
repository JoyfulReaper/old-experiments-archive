/*
MIT License

Copyright(c) 2021 Kyle Givler
https://github.com/JoyfulReaper

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.kgivler.javabot;

import com.kgivler.javabot.command.ConsoleHelper;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.Properties;

public class Program {
    public static void main (String[] args) throws IOException {

        // Print intro
        ConsoleHelper.colorWriteLine(ConsoleColor.ANSI_RED, "JavaBot");
        ConsoleHelper.colorWriteLine(ConsoleColor.ANSI_BLUE, "MIT License");
        ConsoleHelper.colorWriteLine(ConsoleColor.ANSI_BLUE, "Copyright(c) 2021 Kyle Givler (JoyfulReaper)");
        ConsoleHelper.colorWriteLine(ConsoleColor.ANSI_BLUE, "https://github.com/JoyfulReaper\n\n");

        try {
            // Setup Properties
            FileInputStream propFile = new FileInputStream("JavaBot.properties");
            Properties p = new Properties(System.getProperties());
            p.load(propFile);
            System.setProperties(p);
        } catch (IOException ex)
        {
            System.out.println("Unable to load JavaBot.properties");
            System.exit(1);
        }

        try {
            // Start the bot
            Bot bot = new Bot();
            bot.start();
        } catch (Exception e)
        {
            if(e.getClass().isAssignableFrom(LoginException.class))
            {
                System.out.println("Unable to log in, please verify your token is correct!");
                System.exit(2);
            }
        }

        while(true) {
            ConsoleHelper.colorWriteLine(ConsoleColor.ANSI_YELLOW, "Enter 'Q' to quit!");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            var input = br.readLine();

            if(input.equalsIgnoreCase("Q"))
            {
                break;
            }
        }

        System.exit(0);
    }
}
