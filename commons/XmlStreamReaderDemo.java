/**
 * 
 */
package com.macroteck.services.processors.xpath;

import javax.xml.stream.XMLStreamReader;

/**
 * 
 * @author (C) <a href="http://www.macroteck.com">Richard Qualis</a>, 2009
 * @email rqualis@macroteck.com
 * @date May 8, 2011
 * @time 12:32:57 AM
 * 
 * @class XmlStreamReaderDemo.java
 *
 */
public class XmlStreamReaderDemo {

	// Create a class Book for processing  
    
	   public class Book {
	     String name;
	     String author;
	   }

	   Book[] parseBooks(XMLStreamReader reader) {
	     Book[] books = new Book[0];
	     while(reader.hasNext()) {

	//  Start at the beginning of the book and make sure that it is a book  
	    
	         if (reader.getEventType() == XmlTag.START_ELEMENT) {
	            if ("Book" == reader.getLocalName()) {

	//  Pass the book to the parseBook method (below)   
	    
	                Book book = parseBook(reader);
	                books.add(book);
	            }
	         }
	        reader.next();
	     }
	    return books;
	   }

	// Parse through the XML, deterimine the auther and the characters  
	    
	   Book parseBook(XmlStreamReader reader) {
	     Book book = new Book();
	     book.author = reader.getAttributeValue(null, 'author');
	     while(reader.hasNext()) {
	        if (reader.getEventType() == XmlTag.END_ELEMENT) {
	           break;
	        } else if (reader.getEventType() == XmlTag.CHARACTERS) {
	           book.name = reader.getText();
	        }
	        reader.next();
	     }
	     return book;
	   }

	// Test that the XML string contains specific values  
	    
	   static testMethod void testBookParser() {

	     XmlStreamReaderDemo demo = new XmlStreamReaderDemo();

	     String str = '<books><book author="Chatty">Foo bar</book>' +
	        '<book author="Sassy">Baz</book></books>';

	     XmlStreamReader reader = new XmlStreamReader(str);
	     Book[] books = demo.parseBooks(reader);

	     System.debug(books.size());

	     for (Book book : books) {
	       System.debug(book);
	     }
	   }

}
