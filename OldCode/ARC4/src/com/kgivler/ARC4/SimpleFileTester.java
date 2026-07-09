package com.kgivler.ARC4;

/**
 * Shows how to use the encFile() method of the ARC4 class
 * Example may be useful want to use the ARC4 class to en/decrypt files
 * @author Kyle Givler
 * 
 * $Id: SimpleFileTester.java,v 1.1 2010/11/20 20:25:47 kwgivler Exp $
 * $Log: SimpleFileTester.java,v $
 * Revision 1.1  2010/11/20 20:25:47  kwgivler
 * Initial check in
 *
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SimpleFileTester {
	
	public static void main(String[] args) throws IOException, FileNotFoundException{
		Scanner in = new Scanner(System.in);
		String fileName;
		File inFile;
		File outFile;
		String key = "FileKey";
		
		ARC4 rc4 = new ARC4(key);
		
		System.out.print("File to en\\decrypt: ");
		fileName = in.nextLine();
		
		inFile = new File(fileName);
		outFile = new File(fileName + ".out");
		rc4.encFile(inFile, outFile);
		
		System.out.println("Done");
		
	}

}
