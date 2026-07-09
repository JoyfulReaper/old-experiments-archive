package com.kgivler.CodeExamples;

public class Square {
	double length;
	
	public Square(double length)
	{
		// length of square
		this.length = length;
	}
	
	public double getArea()
	{
		// area of square (length squared)
		return Math.pow(length, 2);
	}
	
	public double getPerimeter()
	{
		// Parameter of square (length * length * length * length)
		return 4 * length;
	}
	
	public double getDiagonal()
	{
		// Pythagorean theorem
		return Math.sqrt( Math.pow(length, 2) + Math.pow(length, 2 ));
	}
}