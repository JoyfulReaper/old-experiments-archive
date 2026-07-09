package com.kgivler.ARC4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

/**
 * Encrypt a file using RC4
 * This is an example file!
 * See SimpleFileTester class for how to encrypt files using the ARC4 class
 * 
 * @author Kyle Givler
 * 
 * $Id: FileTester.java,v 1.3 2010/11/20 20:41:30 kwgivler Exp $
 * $Log: FileTester.java,v $
 * Revision 1.3  2010/11/20 20:41:30  kwgivler
 * Ready for Initial Release
 *
 * Revision 1.2  2010/11/20 20:05:02  kwgivler
 * Always close the file :)
 *
 * Revision 1.1  2010/11/20 18:40:15  kwgivler
 * Added FileTester
 *
 *
 */

public class FileTester {

	public static void main(String[] args) throws IOException
	{
		File in = new File("C:\\test.txt");
		File out = new File ("C:\\test.enc");
		InputStream inStream = new FileInputStream(in); // file to encrypt
		OutputStream outStream = new FileOutputStream(out); // file to write to
		
		String key = "FileKey";
		long length = in.length();
		ARC4 rc4 = new ARC4(key);
		
        // Encrypt File
        for (int i = 0; i < length; i++)
        {
        	byte inByte;
        	inByte = (byte) inStream.read();
        	byte OutByte = rc4.arc4Crypt(inByte);
        	
        	outStream.write(OutByte);
        }
        
        // Decrypt File
        in = new File("C:\\test.enc");
        out = new File("C:\\test.denc");
        inStream = new FileInputStream(in);
        outStream = new FileOutputStream(out);
        rc4.arc4Init(key.getBytes());
        
        for (int i = 0; i < length; i++)
        {
        	byte inByte;
        	inByte = (byte) inStream.read();
        	byte OutByte = rc4.arc4Crypt(inByte);
        	
        	outStream.write(OutByte);
        }
        
        inStream.close();
        outStream.close();
        
        System.out.println("Done");
        
	}
}
