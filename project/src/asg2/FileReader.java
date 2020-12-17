package asg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

	
	// Pass in the Old product array and (Loop through oldProductsArray)
	// search the invoiceProductCode(invProdSplit[0]) that matches the productCode
	// in Product Array(invoiceProductCode = productProductCode)
	// set the number for added field in the class,
	// for example,(daysRented/hourslyWorked) to the invNum from (invProdSplit[1]),
	// initially has no values
	// Once its done, return it to the arrayList known as invoiceProducts
	// in readInvoices it is stored in the linkInvoiceProducts depending on the
	// invProdSplitLength
	public Product findProduct(ArrayList<Product> oldProductArray, String invProductCode, String quantity, String associateRepair) {

		

		Product productChecked = null;
		for (Product i : oldProductArray) {
			if (i.getProductCode().equals(invProductCode)) {
				if (i.getProductType().contains("R")) {
					((Rental) i).setDaysRented(Double.parseDouble(quantity));
					productChecked = new Rental((Rental) i);
				} else if (i.getProductType().contains("F")) {
					((Repair) i).setHoursWorked(Double.parseDouble(quantity));
					productChecked = new Repair((Repair) i);
				} else if (i.getProductType().contains("T")) {
					((Towing) i).setMilesTowed(Double.parseDouble(quantity));
					productChecked = new Towing((Towing) i);
				} else if (i.getProductType().contains("C")) {
					((Concession) i).setQuantity(Double.parseDouble(quantity));
					productChecked = new Concession((Concession) i);	
					
					if(associateRepair!=null) {
						((Concession) productChecked).setHasAssociatedRepair(true);
						((Concession) productChecked).setAssociatedRepair(associateRepair);
						//System.out.println(productChecked);
					
					 }

				}
			}
		}
		return productChecked;
	}

	// Read Invoice Data from invoice.dat
	// return Arraylist with multiple invoices
	ArrayList<Invoice> inv = new ArrayList<Invoice>();
	// Pass in the oldProductArray that has been given
	// linkInvoiceProduct stores arrayList from invoiceProduct arrayList
	// inv arrayList stores multiple invoice objects
	public ArrayList<Invoice> readInvoices(ArrayList<Product> oldProductArray) {

		Product linkInvoiceProduct = null;

		Scanner s = null;
		try {
			s = new Scanner(new File("data/Invoices.dat"));
		} catch (FileNotFoundException e) {
			System.out.println("Invoice File not found!");
		} catch (NullPointerException e) {
			System.out.println("Returns a null value");
		} catch (Exception e) {
			System.out.println("Something else went wrong!");
			e.printStackTrace();
		}
		s.nextLine();

		// Read the Invoice File
		while (s.hasNext()) {
			String Line = s.nextLine();
			String tokens[] = Line.split(";");
			String invoiceCode = tokens[0];
			String ownerCode = tokens[1];
			String customerCode = tokens[2];
			// qwerty:3 listOfProducts[0]
			String listOfProducts[] = tokens[3].split(",");

			ArrayList<Product> productReturnforInvoice = new ArrayList<Product>();

			for (String line : listOfProducts) {
				// (qwerty )invProdSplit[0], //(3) invProdSplit[1]
				String invProdSplit[] = line.split(":");
				// Set the additional information from invoice.dat into the array
				// linkProduct(ArrayList type)
				if (invProdSplit.length == 3) {
					linkInvoiceProduct = findProduct(oldProductArray, invProdSplit[0], invProdSplit[1],invProdSplit[2]);
					productReturnforInvoice.add(linkInvoiceProduct);
				} else {
					linkInvoiceProduct = findProduct(oldProductArray, invProdSplit[0], invProdSplit[1], null);
					productReturnforInvoice.add(linkInvoiceProduct);
				}
			}
			// Create an invoice object that takes in 3 String, 1 String[] and 1	
			// ArrayList(link Product)
			// Add each of the invoice object in the inv arrayList	
			Invoice invoice = new Invoice(invoiceCode, ownerCode, customerCode, listOfProducts,productReturnforInvoice);
			inv.add(invoice);

		}

		return inv;

	}

	// Read Product from product.dat
	// instantiated all subclasses and add into Arraylist
	// return Arraylist of product

	ArrayList<Product> product = new ArrayList<Product>();

	public ArrayList<Product> readProducts() {
		Scanner s = null;
		try {
			s = new Scanner(new File("data/Products.dat"));
		} catch (FileNotFoundException e) {
			System.out.println("Products Data file not found!");
		} catch (Exception e) {
			System.out.println("Something else went wrong!");
			e.printStackTrace();
		}

		s.nextLine();

		while (s.hasNext()) {
			String Line = s.nextLine();
			String tokens[] = Line.split(";");
			Rental forRental = null;
			Repair forRepair = null;
			Concession forConcession = null;
			Towing forTowing = null;

			if (tokens[1].contains("R")) {
				String rental = Line;
				String rent[] = rental.split(";");
				forRental = new Rental(rent[0], rent[1], rent[2], Double.parseDouble(rent[3]),
						Double.parseDouble(rent[4]), Double.parseDouble(rent[5]));
				product.add(forRental);

			} else if (tokens[1].contains("F")) {
				String repair = Line;
				String rep[] = repair.split(";");
				forRepair = new Repair(rep[0], rep[1], rep[2], Double.parseDouble(rep[3]), Double.parseDouble(rep[4]));
				product.add(forRepair);
			} else if (tokens[1].contains("C")) {
				String concession = Line;
				String conc[] = concession.split(";");
				forConcession = new Concession(conc[0], conc[1], conc[2], Double.parseDouble(conc[3]));
				product.add(forConcession);

			} else if (tokens[1].contains("T")) {
				String towing = Line;
				String tow[] = towing.split(";");
				forTowing = new Towing(tow[0], tow[1], tow[2], Double.parseDouble(tow[3]));
				product.add(forTowing);
			}

		}
		return product;
	}

	// Read Persons Data
	ArrayList<Person> pers = new ArrayList<Person>();

	public ArrayList<Person> readPeople() {
		Scanner s = null;
		try {
			s = new Scanner(new File("data/Persons.dat"));
		} catch (FileNotFoundException e) {
			System.out.println("Persons Data File not found!");
		} catch (Exception e) {
			System.out.println("Something else went wrong!");
			e.printStackTrace();
		}
		s.nextLine();
		while (s.hasNext()) {
			String Line = s.nextLine();
			String tokens[] = Line.split(";");
			String personCode = tokens[0];

			String name[] = tokens[1].split(",");
			String lastName = name[0];
			String firstName = name[1];

			String add[] = tokens[2].split(",");
			// An address object that contains multiple string array of add[]
			Address address = new Address(add[0], add[1], add[2], add[3], add[4]);

			String emailAddress[] = null;
			if (tokens.length == 4) {
				emailAddress = tokens[3].split(",");
			}
			Person p = new Person(personCode, lastName, firstName, address, emailAddress);

			pers.add(p);
		}
		return pers;

	}

	// pass in the old person array list and search the
	// customerPersonCode(primaryContactCode) that matches the personCode in Person
	// Array
	public Person findPerson(ArrayList<Person> passInPeopleArray, String customerPersonCode) {
		for (Person p : passInPeopleArray) {
			if (p.getPersonCode().equals(customerPersonCode)) {
				return p;
			}
		}
		return null;

	}

	// Read Customers Data from customer.dat
	// Return ArrayList Of Customer
	ArrayList<Customer> cus = new ArrayList<Customer>();

	public ArrayList<Customer> readCustomers(ArrayList<Person> oldPeopleArray) {
		Scanner s = null;
		try {
			s = new Scanner(new File("data/Customers.dat"));
		} catch (FileNotFoundException e) {
			System.out.println("Customers Data File not found!");
		} catch (NullPointerException e) {
			System.out.println("Returns a null value");
		} catch (Exception e) {
			System.out.println("Something else went wrong!");
			e.printStackTrace();
		}

		s.nextLine();
		while (s.hasNext()) {
			String Line = s.nextLine();
			String tokens[] = Line.split(";");
			String customerCode = tokens[0];
			String customerType = tokens[1];
			String name = tokens[2];
			String primaryContactCode = tokens[3].trim();
			String add[] = tokens[4].split(",");
			Address address = new Address(add[0], add[1], add[2], add[3], add[4]);
			// Using the find person method, pass in the oldPeopleArray and the
			// customerPersonCode (match primary and personCode)
			Person linkcontact = findPerson(oldPeopleArray, primaryContactCode);
			Customer c = new Customer(customerCode, customerType, name, linkcontact, address);
			cus.add(c);

		}
		return cus;

	}

}




