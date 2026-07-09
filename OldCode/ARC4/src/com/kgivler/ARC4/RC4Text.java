package com.kgivler.ARC4;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 * Encrypt a file using a key file
 * $Id: RC4Text.java,v 1.2 2010/11/23 23:13:20 kwgivler Exp $
 * $Log: RC4Text.java,v $
 * Revision 1.2  2010/11/23 23:13:20  kwgivler
 * removed unused import
 *
 * Revision 1.1  2010/11/22 04:26:00  kwgivler
 * Initial check in
 *
 * @author kwgivler
 *
 */
public class RC4Text {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ARC4 rc4;
		File keyFile;
		byte key[];
		Scanner in = new Scanner(System.in);
		
		while (true)
		{
			System.out.println("0. Quit");
			System.out.println("1. Generate key");
			System.out.println("2. En/decrypt File");
			System.out.print("Make choice: ");
			
			int choice = in.nextInt();
			in.nextLine(); // ignore /n
			
			if (choice == 0)
				System.exit(0);
			
			if (choice == 1)
			{
				OutputStream os;
				
				System.out.print("Filename for key: ");
				keyFile = new File(in.nextLine());
				
				rc4 = new ARC4();
				key = rc4.generateKey(128);
				os = new FileOutputStream(keyFile);
				os.write(key);
				
				System.out.println("Done.\n");
			}
			
			if (choice == 2)
			{
				InputStream is;
				File outFile;
				
				System.out.print("Key file: ");
				keyFile = new File(in.nextLine());
				
				is = new FileInputStream(keyFile);	
				long length = keyFile.length();
				key = new byte[(int)length];
				
				for (int i = 0; i < length; i++)
					key[i] =(byte) is.read();
				
				System.out.print("File to en/decrypt: ");
				String fileName = in.nextLine();
				outFile = new File(fileName);
				
				System.out.print("Output file name:");
				fileName = in.nextLine();
				File encFile = new File(fileName);
				
				rc4 = new ARC4(key);
				rc4.encFile(outFile, encFile);
				
				System.out.println("Output file is: " + fileName);
			}
			
		}
	}

}
