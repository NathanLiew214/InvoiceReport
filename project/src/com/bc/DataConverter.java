package com.bc;


import asg2.FileReader;


import bcWriters.JsonWriter;
import bcWriters.XmlWriter;

public class DataConverter {
	public static void main(String[] args) {
		
		FileReader readFile = new FileReader();
		
		JsonWriter.printJSON("data/Persons.json", readFile.readPeople());
		XmlWriter.printXML("data/Persons.xml", readFile.readPeople());
		
		JsonWriter.printJSON("data/Customers.json", readFile.readCustomers(readFile.readPeople()));
		XmlWriter.printXML("data/Customers.xml", readFile.readCustomers(readFile.readPeople()));

		JsonWriter.printJSON("data/Products.json",  readFile.readProducts());
		XmlWriter.printXML("data/Products.xml",  readFile.readProducts());
		
		JsonWriter.printJSON("data/Invoice.json", readFile.readInvoices(readFile.readProducts()));

	}

}
