package com.kgivler;

import java.util.Scanner;

public class Guess {
	public static void main (String argv[])
	{
		Scanner in = new Scanner(System.in);
		int maxNumber = 100;
		int number = (int) (Math.random() * maxNumber);
		int turn = 0;
		int maxGuesses = 10;
		boolean won = false;

		System.out.println("I am thinking of a number between 0 and " + maxNumber + ".");

		while (turn < maxGuesses)
		{
			turn++;
			System.out.print("What is your guess? (" + turn + " of " + maxGuesses + "): ");
			int guess = in.nextInt();
			if (guess == number)
			{
				System.out.print("\nYou guessed it in " + turn + " guesses! ");

				if(maxGuesses == 10)
				{
					if (turn <= 2)
						System.out.println("Lucky!");
					if (turn > 2 && turn <= 4)
						System.out.println("Good job!");
					if (turn > 4 && turn <= 6)
						System.out.println("Not Bad!");
					if (turn == 7)
						System.out.println("Pretty Average!");
					if (turn > 7)
						System.out.println("Maybe try again?");
				}
				won = true;
				break;
			}
			if(guess > number)
			{
				System.out.println("\nToo high!");
			}
			else
			{
				System.out.println("\nToo low!");
			}
		}
		if(!won)
		{
			System.out.println("\nYou are out of guesses! You lose! I was thinking of "+ number + "!");
		}
	}
}
