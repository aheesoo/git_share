package com.kt.olleh.hms.sysspec;

import java.nio.ByteBuffer;
import java.util.Scanner;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.StringEncoderComparator;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.kt.olleh.hms.sysspec.util.HomeCameraUtil;
import com.kt.smcp.gw.ca.util.ConvertUtil;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }


    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	String result = "";
    	String s = "SYSTEM";
    	String inputString = " ";  
    	String hexString = "";
    	byte[] inputBytes = inputString.getBytes(); 
    	for (byte b : inputBytes) {  
    	    hexString += Integer.toString((b & 0xF0) >> 4, 16);  
    	    hexString += Integer.toString(b & 0x0F, 16);  
    	}  
    	System.out.println("hex String   :" + hexString);  
        assertTrue( true );
        
    }
    
    @Test
    public void stringToHex(String s) {
    	s = "001234AABBCC";
        String result = "";

        for (int i = 0; i < s.length(); i++) {
          result += String.format("%02X ", (int) s.charAt(i));
        }
        
        Byte.decode(result);
        System.out.println(" sssssssssssss : "+result);
        System.out.println(" aaaaa : "+Byte.decode(result));
        
        assertTrue(true);
      }
    
}
