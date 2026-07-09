package com.kgivler.GuiGuess;

public class Guess {
	
	private int targetNumber;
	private int maxNumber = 100;
	
	public Guess()
	{
		
	}
	
	public void init()
	{
		targetNumber = (int) (Math.random() * maxNumber);
	}
	
	public int getTargetNumber()
	{
		return targetNumber;
	}
}
