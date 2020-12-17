package bcWriters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import asg2.Concession;
import asg2.Customer;
import asg2.Person;
import asg2.Product;
import asg2.Rental;
import asg2.Repair;
import asg2.Towing;

public class XmlWriter {

	public static <T> void printXML(String filePath, ArrayList<T> list) {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Persons", Person.class);
		xstream.alias("Customers", Customer.class);
		xstream.alias("Rental", Rental.class);
		xstream.alias("Towing", Towing.class);
		xstream.alias("Concession", Concession.class);
		xstream.alias("Repairs", Repair.class);
		xstream.alias("Products", Product.class);

		
		if (filePath == "data/Persons.xml") {
			
			try {
				PrintWriter out = new PrintWriter(new File(filePath));
				out.print("<?xml version=\"1.0\"?>\n");
				String header = "<Persons>\n";
				String closer = "</Persons>";
				out.write(header);
				for (T entry : list) {
					out.write(xstream.toXML(entry));
					out.write("\n");
				}
				out.write(closer);
				out.close();
			} catch (FileNotFoundException e) {
				System.out.println("Persons File Not Found!");
			}

		} else if (filePath == "data/Customers.xml") {
	

			try {
				PrintWriter out = new PrintWriter(new File(filePath));
				out.print("<?xml version=\"1.0\"?>\n");
				String header = "<Customers>\n";
				String closer = "</Customers>";
				out.write(header);
				for (T entry : list) {
					out.write(xstream.toXML(entry));
					out.write("\n");
				}
				out.write(closer);
				out.close();
			} catch (FileNotFoundException e) {
				System.out.println("Customers File Not Found!");
			}

		} else if (filePath == "data/Products.xml") {
		
			try {
				PrintWriter out = new PrintWriter(new File(filePath));
				out.print("<?xml version=\"1.0\"?>\n");
				String header = "<Products>\n";
				String closer = "</Products";
				out.write(header);
				for (T entry : list) {
					out.write(xstream.toXML(entry));
					out.write("\n");

				}
				out.write(closer);
				out.close();
			} catch (FileNotFoundException e) {
				System.out.println("Products File Not Found!");

			}
		}

	}
}