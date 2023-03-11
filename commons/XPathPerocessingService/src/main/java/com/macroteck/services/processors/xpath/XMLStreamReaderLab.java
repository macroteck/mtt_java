/**
 * 
 */
package com.macroteck.services.processors.xpath;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * 
 * @author (C) <a href="http://www.macroteck.com">Richard Qualis</a>, 2009
 * @email rqualis@macroteck.com
 * @date May 8, 2011
 * @time 12:12:18 AM
 * 
 * @class XMLStreamReaderLab.java
 *
 */
public class XMLStreamReaderLab {

	/**
	 * 
	 */
	public XMLStreamReaderLab() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param
	 * @return void
	 * http://tutorials.jenkov.com/java-xml/stax-xmlstreamreader.html
	 */
	public static void main(String[] args) {
		
		Map <String, Map<String, String>> dataDefinition = new HashMap<String, Map<String, String>>();
		Map <String, String> pair = new HashMap<String, String>();
		
		XMLInputFactory factory = XMLInputFactory.newInstance();

		try {
			
			Map sampleData = new HashMap();
			sampleData.put("ACTION_CODE", "actin code");
			
			SampleClass sc = new SampleClass();
			//(SampleClass)Class.forName("com.macroteck.services.processors.xpath.SampleClass");

		    XMLStreamReader streamReader = factory.createXMLStreamReader(
		    	    new FileReader(
		    	    		"/Users/rqualis/Development/Companies/Macroteck/Development"+
		    	    		"/maindev/XPathPerocessingService/src/test/resources/EleCorrectedIsEO.xml"
		    	    	)
		    );

		    	while(streamReader.hasNext()){
		    	    streamReader.next();
		    	    if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT){
		    	    	
		    	    	if(null != streamReader.getAttributeValue(null, "Type")){
		    	    		System.out.println(
		    	        		streamReader.getLocalName() + 
		    	        		" Name: " + streamReader.getAttributeValue(null, "Name") +
		    	        		" ColumnName: " + streamReader.getAttributeValue(null, "ColumnName")+
		    	        		" Type: " + streamReader.getAttributeValue(null, "Type"));
		    	    		//"Class: " + Class.forName(streamReader.getAttributeValue(null, "Type"))
		    	    		
		    	    		pair = new HashMap<String, String>();
		    	    		pair.put(
		    	    					streamReader.getAttributeValue(null, "Name"), 
		    	    					streamReader.getAttributeValue(null, "Type")
		    	    		);
		    	        
		    	    		dataDefinition.put(streamReader.getAttributeValue(null, "ColumnName"), pair);
		    	    	}
		    	        
		    	    }
		    	}
		    	
		    	System.out.println("dataDefinition: " + dataDefinition);
		    	
		    	
		    	sampleData

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
