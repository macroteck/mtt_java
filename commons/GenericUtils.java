/**
* <pre>
* Class: $Id: GenericUtils.java,v 1.42 2007/06/18 21:07:08 rqualis Exp $
* Design Server
*
* Descripion:  the application.
*
* @author Last modified by $author: rqualis
* @version $Revision: 1.0.0 $
*
*<pre>
*<strong>CVS log</strong>
* $Log: GenericUtils.java
*
*</pre>
**/
package nova.cisc0665.rq44.assignment.one.client.utils;


import javax.swing.*;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;


public class GenericUtils extends JApplet{

	static final long serialVersionUID = 1111111111;
	
	public static final String SCREEN = "_DEBUG_TO_SCREEN_";
	public static final String FILE = "_DEBUG_TO_FILE_";
	

	public GenericUtils(){}
	
	public static String generateRandomUUID () {
	    try {
	      //Initialize SecureRandom. This is a lengthy operation, to be done only upon
	      //initialization of the application
	      SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

	      //generate a random number
	      return new Integer( prng.nextInt() ).toString();
	      	      
	    } catch (NoSuchAlgorithmException ex ) {
	    	return null;
	    }
	}
	
	public static String getMessageDigest(String randomNum){
		try {
			//get its digest
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] digest =  sha.digest( randomNum.getBytes() );
		      
			String mDigest = getHexEncode(digest);
			//System.out.println("Message digest: " + mDigest);
			return mDigest;
		} catch (Exception e){
			return null;
		}
	}
	
	/**
	  * The byte[] returned by MessageDigest does not have a nice
	  * textual representation, so some form of encoding is usually performed.
	  *
	  * This implementation follows the example of David Flanagan's book
	  * "Java In A Nutshell", and converts a byte array into a String
	  * of hex characters.
	  *
	  * Another popular alternative is to use a "Base64" encoding.
	  */
	  static public String getHexEncode( byte[] aInput){
	    StringBuilder result = new StringBuilder();
	    char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	    for ( int idx = 0; idx < aInput.length; ++idx) {
	      byte b = aInput[idx];
	      result.append( digits[ (b&0xf0) >> 4 ] );
	      result.append( digits[ b&0x0f] );
	    }
	    return result.toString();
	  }

	/*
     *===========================================================================
	 * Called when the image property file can't be found.
	 *===========================================================================
	 */
    public static void HandleMissingResource(MissingResourceException e) {
        System.err.println();
        System.err.println("Can't find the properties file " +
                           "that contains the image names.");
        System.err.println("Its name should be imagenames.properties, " +
                           "and it should");
        System.err.println("contain a single line that specifies " +
                           "one or more image");
        System.err.println("files to be found in a directory " +
                           "named images.  Example:");
        System.err.println();
        System.err.println("    images=Bird.gif Cat.gif Dog.gif");
        System.err.println();
        throw(e);  //Used to be exit(1), but that causes the console to
                   //go away under Java Web Start; this way, you're
                   //more likely to see a relevant error message.
    }

    public static Vector ParseList(String theStringList) {
        Vector v = new Vector(10);
        StringTokenizer tokenizer = new StringTokenizer(theStringList, " ");
        while (tokenizer.hasMoreTokens()) {
            String image = tokenizer.nextToken();
            v.addElement(image);
        }
        return v;
    }

    public static void DEBUG(String outTo, String msg, Object obj){

    	if(outTo.equals(FILE)){

    	}else if(outTo.equals(SCREEN)){
    		System.out.println("[DEBUG] (" + obj.getClass().getName() + ") : " + msg);
    	}
    }
    
    /**
     * 
      * @param
      * @return void
     */
    public static void PrintSplashScreen(String...message){
    	
    }
    
    public static void PrintSplashScreen(String splashScreenTitle, int fieldWidth, HashMap <String, String>message){
    	System.out.println("===============================================================");
    	System.out.println("*   Configuration for " + splashScreenTitle);
    	if(!message.isEmpty()){
    		Set<String> keys = message.keySet();
    		Iterator <String>iter = keys.iterator();
    		while(iter.hasNext()){
    			String k = iter.next();
    			System.out.print("*  "+k + ":");
    			
    			int l = fieldWidth - k.length();
    			
    			for(int j=0; j < l; j++)
    				System.out.print(" ");
    			
    			System.out.println(message.get(k));
    		}
    	}
    	System.out.println("===============================================================");
    }
    
    public static void PrintVerbouse(Object message){
    	System.out.println("<"+getTimestamp() + "> <INFO> " + message);
    }
    
    public static Timestamp getTimestamp(){
    	return new Timestamp(getDate().getTime());
    }
    
    public static Date getDate(){
    	return new java.util.Date();
    }
}