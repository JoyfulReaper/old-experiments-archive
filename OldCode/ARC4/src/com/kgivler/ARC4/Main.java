package com.kgivler.ARC4;

/**
 * 
 * See the SimpleTester as well.
 * This class was used for debugging the ARC4 class and is probably not a good example
 * of how to use it...
 * 
 * Tester for debugging the simple example implementation of the (Alleged) RC4 algorithm
 * @author Kyle Givler
 */

 /* 
 * $Id: Main.java,v 1.7 2010/12/02 21:11:55 kwgivler Exp $
 * $Log: Main.java,v $
 * Revision 1.7  2010/12/02 21:11:55  kwgivler
 * Updating documentation/typos and other minor revisions
 *
 * Revision 1.6  2010/11/20 20:41:30  kwgivler
 * Ready for Initial Release
 *
 *
 */

import java.security.NoSuchAlgorithmException;

public class Main {
	
	public static void main(String[] args) {
		
		String keyString = "Secret";
		String data = "Attack at dawn";
		byte keyBytes[] = keyString.getBytes();
		byte dataBytes[] = data.getBytes();
		byte resultBytes[];
		String resultString;
		ARC4 rc4;
		
		// if you really want to use this tester, set one of more to true
		boolean easyTest = false;
		boolean stringTest = false;
		boolean encTest = false;
		boolean keystreamTest = false;
		boolean keyGenTest = false;
		
		System.out.println("Initial Key and Data:");
		System.out.println("Key (hex):  " + getHex(keyBytes));
		System.out.println("Data (hex): " + getHex(dataBytes));		
		System.out.println("Key:        " + keyString);
		System.out.println("Data:       " + data);
		System.out.println();
		
		if (easyTest)
		{
			// Encryption/Decryption cycle (encrypt() and decrypt() tests)
			System.out.println("Running encrypt(data) [hex: " + getHex(dataBytes) + "]: ");
			rc4 = new ARC4(keyBytes);
			resultBytes = rc4.encrypt(data);
			resultString = getHex(resultBytes);
			System.out.println("Data (hex): " + resultString);
			
			System.out.println("Running decrypt(resultBytes): ");
			rc4 = new ARC4(keyBytes);
			resultString = rc4.decrypt(resultBytes);
			System.out.println("Data:       " + resultString);
			System.out.println();
		}
		
		if (encTest)
		{
			// Encryption/Decryption cycle (arc4Crypt() tests)
			System.out.println("Running arc4Crypt(dataBytes) [" + getHex(dataBytes) + "]: ");
			rc4 = new ARC4(keyBytes);
			resultBytes = rc4.arc4Crypt(dataBytes);
			resultString = getHex(resultBytes);
			System.out.println("Encrypted data (hex): " + resultString);

			rc4 = new ARC4(keyBytes);
			resultBytes = rc4.arc4Crypt(resultBytes);
			resultString = getHex(resultBytes);
			System.out.println("Decrypted data (hex): " + resultString);

			System.out.println("2nd run: arc4Crypt(dataBytes) [" + getHex(dataBytes) + "]:");
			rc4 = new ARC4(keyBytes);
			resultBytes = rc4.arc4Crypt(resultBytes);
			resultString = getHex(resultBytes);
			System.out.println("Encrypted data (hex): " + resultString);

			rc4 = new ARC4(keyBytes);
			resultBytes = rc4.arc4Crypt(resultBytes);
			resultString = getHex(resultBytes);
			System.out.println("Decrypted data (hex): " + resultString);
			System.out.println();
		}
		
		if (stringTest)
		{
			// Encryption/Decryption cycle (arc4Crypt() tests using Strings)
			System.out.println("Running arc4Crypt(dataBytes) [" + getHex(dataBytes) + "] string tests: ");
			rc4 = new ARC4(keyString);
			resultBytes = rc4.arc4Crypt(data);
			resultString = getHex(resultBytes);
			System.out.println("Encrypted data (hex): " + resultString);

			rc4 = new ARC4(keyString);
			resultBytes = rc4.arc4Crypt(resultBytes);
			resultString = getHex(resultBytes);
			System.out.println("Decrypted data (hex): " + resultString);

			System.out.println("2nd run: arc4Crypt(dataBytes) [" + getHex(dataBytes) + "]");
			rc4 = new ARC4(keyString);
			resultBytes = rc4.arc4Crypt(resultBytes);
			resultString = getHex(resultBytes);
			System.out.println("Encrypted data (hex): " + resultString);

			rc4 = new ARC4(keyString);
			resultBytes = rc4.arc4Crypt(resultBytes);
			resultString = getHex(resultBytes);
			System.out.println("Decrypted data (hex): " + resultString);
		}
		
		if (keystreamTest)
		{
			// keystream test
			System.out.println("\nKeystream for \"" + keyString + "\" [hex: " + getHex(keyBytes) + "]");
			rc4 = new ARC4(keyBytes);
			resultBytes = new byte[keyString.length()];
			for (int i = 0; i < keyString.length(); i++)
			{
				resultBytes[i] = rc4.arc4PRGA();
			}
			resultString = getHex(resultBytes);
			System.out.println(resultString + "...");
			System.out.println();
		}
		
		if(keyGenTest)
		{
			// key generator test
			byte key[];
			rc4 = new ARC4();
			System.out.println("Running generateKey() (hex):");
			try {
				 key = rc4.generateKey(128);
				 System.out.println(getHex(key));
				 
				 
				 System.out.println("\nRunning arc4Crypt(dataBytes) [" + getHex(dataBytes) + "]");
				 rc4 = new ARC4(key);
				 resultBytes = rc4.arc4Crypt(dataBytes);
				 resultString = getHex(resultBytes);
				 System.out.println("Encrypted data (hex): " + resultString);

				 rc4 = new ARC4(key);
				 resultBytes = rc4.arc4Crypt(resultBytes);
				 resultString = getHex(resultBytes);
				 System.out.println("Decrypted data (hex): " + resultString);

				 System.out.println("2nd run: arc4Crypt(dataBytes) [" + getHex(dataBytes) + "]");
				 rc4 = new ARC4(key);
				 resultBytes = rc4.arc4Crypt(resultBytes);
				 resultString = getHex(resultBytes);
				 System.out.println("Encrypted data (hex): " + resultString);

				 rc4 = new ARC4(key);
				 resultBytes = rc4.arc4Crypt(resultBytes);
				 resultString = getHex(resultBytes);
				 System.out.println("Decrypted data (hex): " + resultString);
				 
			} 
			catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	  static final String HEXES = "0123456789ABCDEF";
	  public static String getHex( byte [] raw ) {
	    if ( raw == null ) {
	      return null;
	    }
	    final StringBuilder hex = new StringBuilder( 2 * raw.length );
	    for ( final byte b : raw ) {
	      hex.append(HEXES.charAt((b & 0xF0) >> 4))
	         .append(HEXES.charAt((b & 0x0F)));
	    }
	    return hex.toString();
	  }


}
