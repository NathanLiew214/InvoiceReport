/**
Name: Nathan Liew & Dinesh Budhathoki
Program: DatabaseReader.java
Purpose : ReadFiles from existing databases
**/

package com.bc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bc.ext.DatabaseInfo;

import asg2.Address;
import asg2.Customer;
import asg2.Invoice;
import asg2.MyLinkedList;
import asg2.Person;
import asg2.Product;
import asg2.Rental;
import asg2.Repair;
import asg2.Towing;
import asg2.Concession;

public class DatabaseReader {
	private static ArrayList<Person> personArray = new ArrayList<>();
	private static ArrayList<Customer> customerArray = new ArrayList<>();
	private static ArrayList<Product> productArray = new ArrayList<>();

	public static ArrayList<Person> getPerson() {

		Connection conn = DatabaseInfo.getConnection();

		String query = "Select * From Person P";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("personId");
				String personCode = rs.getString("personCode");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");

				String[] emailAddress = getEmails(personId);

				Address address = getAddress(rs.getInt("addressId"));

				Person p = new Person(personCode, lastName, firstName, address, emailAddress);
				personArray.add(p);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personArray;

	}

	public static Address getAddress(int _addressId) {
		Address a = null;
		// Get Address from incoming id
		Connection conn = DatabaseInfo.getConnection();

		String query = "SELECT A1.street, A1.city, A2.state, A1.zip, A3.country"
				+ " FROM Address As A1 Left Join AddressState As A2 ON A1.addressStateId = A2.addressStateId"
				+ " Left Join AddressCountry As A3 ON A1.addressCountryId = A3.addressCountryId"
				+ " WHERE A1.addressId = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, _addressId);
			rs = ps.executeQuery();

			while (rs.next()) {
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				String country = rs.getString("country");

				a = new Address(street, city, state, zip, country);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	public static String[] getEmails(int personId) {
		Connection conn = DatabaseInfo.getConnection();
		String query = "Select email From Email Where personId = ?";
		String query2 = "Select Count(email) From Email Where personId = ?";

		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		int size = -1;

		try {
			ps2 = conn.prepareStatement(query2);
			ps2.setInt(1, personId);
			rs2 = ps2.executeQuery();

			rs2.next();
			size = rs2.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if (ps2 != null && !ps2.isClosed()) {
				ps2.close();
			}
			if (rs2 != null && !rs2.isClosed()) {
				rs2.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String EmailList[] = new String[size];

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();

			int i = 0;
			while (rs.next()) {
				EmailList[i] = rs.getString("email");
				i++;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return EmailList;
	}

	public static ArrayList<Customer> getCustomer() {

		Connection conn = DatabaseInfo.getConnection();

		String query = "Select * From Customer As C";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				String customerCode = rs.getString("customerCode");
				String customerType = rs.getString("customerType");
				String name = rs.getString("customerName");
				String primaryContactCode = rs.getString("primaryContactCode");
				int address = rs.getInt("addressId");

				Address a = getAddress(address);

				Person p = findPerson(primaryContactCode);
				Customer c = new Customer(customerCode, customerType, name, p, a);
				customerArray.add(c);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerArray;
	}

	public static Person findPerson(String customerPersonCode) {

		Person p = null;

		Connection conn = DatabaseInfo.getConnection();
		String query = "Select * From Person As P Where P.personCode = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, customerPersonCode);
			rs = ps.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("personId");
				String personCode = rs.getString("personCode");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				String[] emailArray = getEmails(personId);
				Address a = getAddress(rs.getInt("addressId"));
				p = new Person(personCode, lastName, firstName, a, emailArray);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public static ArrayList<Product> getProduct() {

		Connection conn = DatabaseInfo.getConnection();
		// String query = "select * from Product join InvoiceProduct;";
		String query = "select * from Product as p left join InvoiceProduct ip on p.productId=ip.productid;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				String productCode = rs.getString("productCode");
				String productType = rs.getString("productType");
				String productLabel = rs.getString("productLabel");

				switch (productType) {
				case "F":
					Repair f = new Repair(productCode, productType, productLabel, rs.getDouble("partsCost"),
							rs.getDouble("hourlyLabourCost"));
					productArray.add(f);
					break;
				case "R":
					Rental r = new Rental(productCode, productType, productLabel, rs.getDouble("dailyCost"),
							rs.getDouble("deposit"), rs.getDouble("cleaningFee"));
					productArray.add(r);
					break;
				case "C":
					Concession c = new Concession(productCode, productType, productLabel, rs.getDouble("unitCost"));
					productArray.add(c);
					break;
				case "T":
					Towing t = new Towing(productCode, productType, productLabel, rs.getDouble("costPerMile"));
					productArray.add(t);
					break;
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productArray;
	}

	//MyLinkList

	public static MyLinkedList<Invoice> getInvoice() {	
		
		MyLinkedList<Invoice> invoiceArray = new MyLinkedList<>(MyLinkedList.InvoiceValueComparator);
		
		Connection conn = DatabaseInfo.getConnection();

		String query = "Select I.invoiceId, I.invoiceCode, C.customerCode, P.personCode"
				+ " From Invoice As I Left Join Person P On I.personId = P.personId"
				+ " Left Join Customer C On I.customerId = C.customerId";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				int invoiceId = rs.getInt("invoiceId");
				String invoiceCode = rs.getString("invoiceCode");
				String ownerCode = rs.getString("personCode");
				String customerCode = rs.getString("customerCode");

				String[] productList = getListOfProducts(invoiceId);
				ArrayList<Product> linkInvoiceProduct = getLinkInvoiceProduct(invoiceId);

				Invoice invoice = new Invoice(invoiceCode, ownerCode, customerCode, productList, linkInvoiceProduct);

				invoiceArray.insert(invoice);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invoiceArray;
	}

	public static String[] getListOfProducts(int invoiceId) {

		Connection conn = DatabaseInfo.getConnection();
		String query = "Select * From InvoiceProduct Where invoiceId = ?";

		String query2 = "Select Count(invoiceId) From InvoiceProduct Where invoiceId = ?";

		int size = -1;

		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			ps2 = conn.prepareStatement(query2);
			ps2.setInt(1, invoiceId);
			rs2 = ps2.executeQuery();
			rs2.next();
			size = rs2.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if (ps2 != null && !ps2.isClosed()) {
				ps2.close();
			}
			if (rs2 != null && !rs2.isClosed()) {
				rs2.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[] productList = new String[size];

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			rs = ps.executeQuery();

			int i = 0;
			while (rs.next()) {
				productList[i] = rs.getString("productId");
				i++;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public static ArrayList<Product> getLinkInvoiceProduct(int invoiceId) {

		ArrayList<Product> productReturnforInvoice = new ArrayList<Product>();

		Connection conn = DatabaseInfo.getConnection();
		String query = "Select IP.invoiceProductId, IP.productId, IP.invoiceId, IP.daysRented, IP.hoursWorked, IP.quantity,"
				+ " IP.milesTowed, IP.associatedRepair, P.productType, P.productCode, P.productLabel, P.partsCost, P.dailyCost, P.deposit, P.cleaningFee, P.hourlyLabourCost, P.costPerMile, P.unitCost"
				+ " From InvoiceProduct As IP Left Join Product P On IP.productId = P.productId Where invoiceId = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			rs = ps.executeQuery();

			Product productChecked = null;
			while (rs.next()) {

				productChecked = null;
				switch (rs.getString("productType")) {
				case "R":
					productChecked = new Rental(rs.getString("productCode"), rs.getString("productType"),
							rs.getString("productLabel"), rs.getDouble("dailyCost"), rs.getDouble("deposit"),
							rs.getDouble("cleaningFee"), rs.getDouble("daysRented"));
					break;
				case "F":
					productChecked = new Repair(rs.getString("productCode"), rs.getString("productType"),
							rs.getString("productLabel"), rs.getDouble("partsCost"), rs.getDouble("hourlyLabourCost"),
							rs.getDouble("hoursWorked"));
					break;
				case "T":
					productChecked = new Towing(rs.getString("productCode"), rs.getString("productType"),
							rs.getString("productLabel"), rs.getDouble("costPerMile"), rs.getDouble("milesTowed"));
					break;
				case "C":
					productChecked = new Concession(rs.getString("productCode"), rs.getString("productType"),
							rs.getString("productLabel"), rs.getDouble("unitCost"), rs.getDouble("quantity"));
					break;
				}

				productReturnforInvoice.add(productChecked);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productReturnforInvoice;
	}

	public static int getQuantity(int invoiceId, String productCode, String productType) {
		// Get the product quantity;
		int productQuantity = -1;

		Connection conn = DatabaseInfo.getConnection();
		String query = "";
		switch (productType) {
		case "R":
			query = "Select IP.daysRented As QTY FROM InvoiceProduct As IP "
					+ " Left Join Product As P ON IP.productId = P.productID Where IP.invoiceId = ? and P.productCode = ?";
			break;
		case "F":
			query = "Select IP.hoursWorked As QTY FROM InvoiceProduct As IP "
					+ " Left Join Product As P ON IP.productId = P.productID Where IP.invoiceId = ? and P.productCode = ?";
			break;
		case "T":
			query = "Select IP.milesTowed AS QTY FROM InvoiceProduct As IP "
					+ " Left Join Product As P ON IP.productId = P.productID Where IP.invoiceId = ? and P.productCode = ?";
			break;
		case "C":
			query = "Select IP.quantity AS QTY FROM InvoiceProduct As IP "
					+ " Left Join Product As P ON IP.productId = P.productID Where IP.invoiceId = ? and P.productCode = ?";
			break;
		}

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			ps.setString(2, productCode);
			rs = ps.executeQuery();

			while (rs.next()) {
				productQuantity = rs.getInt("QTY");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productQuantity;
	}

}