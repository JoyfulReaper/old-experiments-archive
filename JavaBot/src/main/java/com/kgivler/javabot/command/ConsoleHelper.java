/*
MIT License

Copyright (c) 2020 Kyle Givler

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


package com.kgivler.javabot.command;

import com.kgivler.javabot.ConsoleColor;

import java.util.List;
import java.util.Random;

public class ConsoleHelper {

    public static String DefaultColor = ConsoleColor.ANSI_RESET;
    private static Random _random = new Random();

    public static void colorWrite(final String color, final String message) {
        System.out.print(color + message + ConsoleColor.ANSI_RESET);
    }

    public static void colorWrite(String message)
    {
        colorWrite(DefaultColor, message);
    }

    public static void colorWriteLine(String color, String message)
    {
        colorWrite(color, message + "\n");
    }

    public static void colorWriteLine(String message)
    {
        colorWriteLine(DefaultColor, message);
    }

    public static void multiColorWrite(List<String> colors, String message, boolean random)
    {
        int colorIndex = -1;

        for (int i = 0; i < message.length(); i++)
        {
            if (random)
            {
                colorIndex = _random.nextInt(colors.size());
            }
            else
            {
                if (!Character.isWhitespace(message.charAt(i)))
                {
                    colorIndex++;
                    if (colorIndex >= colors.size())
                    {
                        colorIndex = 0;
                    }
                }
            }
            colorWrite(colors.get(colorIndex), String.valueOf(message.charAt(i)));
        }
    }

    public static void multiColorWriteLine(List<String> colors, String message, boolean random)
    {
        multiColorWrite(colors, message + "\n" , random);
    }
}
