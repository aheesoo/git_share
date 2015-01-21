package com.kt.olleh.hms.sysspec;

import java.nio.ByteBuffer;

import com.kt.olleh.hms.sysspec.util.HomeCameraUtil;
import com.kt.smcp.gw.ca.util.ConvertUtil;

import junit.framework.Test;
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
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	String hex = "1EDC5808";
    	
        byte ba[] = new byte[hex.length() / 2];
        for(int i = 0; i < ba.length; i++)
            ba[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);

        System.out.println("byte -----> "+ba);

        System.out.println("result ---> "+ByteBuffer.wrap(ba).getInt());
    	 
        assertTrue( true );
    }
    
}
