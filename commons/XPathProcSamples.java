/**
 * 
 */
package com.macroteck.services.processors.xpath;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpression;
import org.xml.sax.InputSource;
//import org.apache.xpath.NodeSet;		need: xalan-j-current-bin.jar

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;



/**
 * 
 * @author (C) <a href="http://www.macroteck.com">Richard Qualis</a>, 2009
 * @email rqualis@macroteck.com
 * @date Jul 15, 2009
 * @time 4:02:49 PM
 * 
 * @class XPathProcSamples.java
 * 
 * Have to be used with Dom
 * http://www.javabeat.net/tips/182-how-to-query-xml-using-xpath.html
 *
 */
public class XPathProcSamples {

	/**
	 * 
	 */
	public XPathProcSamples() {
		// TODO Auto-generated constructor stub
	}

	/**
	  * 
	  * xPathExpression:
	  * 		/catalog/journal/article[@date='July 14, 2009']/@level
	  * 
	  * 			attributes are: date and level. The @ sign indicates
	  * 			what are the attributes.
	  * 
	  * inputSource:
	  * 		InputSource object for an XML doc
	  *  
	  * @param xPathExpression
	  * @param inputSource
	  * @return String
	 */
	public static String getAttributeValue(String xpExpression, InputSource inputSource) throws Exception{
		
		XPathFactory xPathFactory = XPathFactory.newInstance();				
		XPath xPath = xPathFactory.newXPath();
		String data = "";
		
		try {
			System.out.println("xPathExpression Received: " + xpExpression);
						
			/**
			 * Parse an XML document with XPath expression
			 */
			XPathExpression xPathXpression = xPath.compile(xpExpression);
			
			//http://www.roseindia.net/tutorials/xPath/java-xpath.shtml
			xPathXpression = xPath.compile("//Attribute/*/text()");
			data =  xPathXpression.evaluate(inputSource);
			
			System.out.println("data: " + data);
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	public static void TestGetAttributeValue() throws Exception{
		
		File xmlDocument = new File("/Users/rqualis/Development/Companies/Macroteck/Development/maindev/XPathPerocessingService/src/test/resources/SampleXML_1.xml");
		String xpExpression = "/catalog/journal/article[@date='July 14, 2009']/@level";
			
		//Create input source
		InputSource inputSource = new InputSource( new FileInputStream(xmlDocument) );
		
		System.out.println(
								getAttributeValue(xpExpression, inputSource)
						);
	}

	//http://www.w3schools.com/xpath/xpath_syntax.asp
	public static void TestGetAttributeValue2() throws Exception{
		
		File xmlDocument = new File("/Users/rqualis/Development/Companies/Macroteck/Development/maindev/XPathPerocessingService/src/test/resources/EleCorrectedIsEO.xml");
		String xpExpression = "/Entity/Attribute[@ColumnName='ACTION_CODE']/@level";
			
		//Create input source
		InputSource inputSource = new InputSource( new FileInputStream(xmlDocument) );
		
		System.out.println(
								getAttributeValue(xpExpression, inputSource)
						);
	}
	
	
	/**
	 * @param
	 * @return void
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		try{
			System.out.println("Start......");
			TestGetAttributeValue();
			TestGetAttributeValue2();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}



