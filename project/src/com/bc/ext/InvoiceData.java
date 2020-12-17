/**
Name: Nathan Liew & Dinesh Budhathoki
Program: InvoiceData.java
Purpose : Java Database Connection Methods to add, update or remove records
**/


package com.bc.ext;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/* DO NOT change or remove the import statements beneath this.
* They are required for the webgrader to run this phase of the project.
* These lines may be giving you an error; this is fine. 
* These are webgrader code imports, you do not need to have these packages.
*/
//import com.bc.model.Concession;
//import com.bc.model.Invoice;
//import com.bc.model.Customer;
//import com.bc.model.Towing;
//import com.bc.model.Person;
//import com.bc.model.Product;
//import com.bc.model.Rental;
//import com.bc.model.Repair;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application. 16 methods in
 * total, add more if required. Do not change any method signatures or the
 * package name.
 * 
 * Adapted from Dr. Hasan's original version of this file
 * 
 * @author Chloe
 *
 */
public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */

	public static void removeAllPersons() {
		
		// *1&2 Load the JDBC Driver & Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();

		// 3* Create a query and Execute a prepared query
		String query1 = "delete from Email";
		String query2 = "delete from InvoiceProduct";
		String query3 = "delete from Invoice";
		String query4 = "delete from Customer";
		String query5 = "delete from Person ";

		PreparedStatement ps = null;

		// 4 * Process the result
		try {
			ps = conn.prepareStatement(query1);
			ps.executeUpdate();
			ps = conn.prepareStatement(query2);
			ps.executeUpdate();
			ps = conn.prepareStatement(query3);
			ps.executeUpdate();
			ps = conn.prepareStatement(query4);
			ps.executeUpdate();
			ps = conn.prepareStatement(query4);
			ps.executeUpdate();
			ps = conn.prepareStatement(query5);
			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// 5 * Close connection twice for confirmation
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */

	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country) {
		
		// *1&2 Load the JDBC Driver & Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();
		int addressId = getAddressId(street, city, state, zip, country);
		int personId = getPersonIdWithCode(personCode);

		if (personId == 0 && addressId != 0) {

			// 3* Create a query and Execute a prepared query
			String query = "insert into Person(personId, personCode, firstName, lastName,addressId) values (?,?,?,?,?)";

			// 4 * Process the result
			PreparedStatement ps = null;

			try {
				
				ps = conn.prepareStatement(query);
				ps.setInt(1, personId);
				ps.setString(2, personCode);
				ps.setString(3, firstName);
				ps.setString(4, lastName);
				ps.setInt(5, addressId); 
				ps.executeUpdate();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */

	public static void addEmail(String personCode, String email) {
		// *1 & 2 Load the JDBC Driver & Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();
		int personId = getPersonIdWithCode(personCode);

		if (personId != 0) {		
			// 3* Create a query and Execute a prepared query
			String query = "insert into Email (personId, email) values (?,?)";

			// 4 * Process the result
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);// Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, personId);
				ps.setString(2, email);
				ps.executeUpdate();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCusomters() {
		// *1 & 2 Load the JDBC Driver & Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();
		
		// 3* Create a query and Execute a prepared query
		String query1 = "delete from InvoiceProduct";
		String query2 = "delete from Invoice";
		String query3 = "delete from Customer";

		PreparedStatement ps = null;
		
		// 4 * Process the result
		try {
			ps = conn.prepareStatement(query1);
			ps.executeUpdate();
			ps = conn.prepareStatement(query2);
			ps.executeUpdate();
			ps = conn.prepareStatement(query3);
			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// 5 * Close connection twice for confirmation
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 5. Method to add a customer record to the database with the provided data
	 * 
	 * @param customerCode
	 * @param customerType
	 * @param primaryContactPersonCode
	 * @param name
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,
			String name, String street, String city, String state, String zip, String country) {

		// *1 & 2 Load the JDBC Driver & Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();
		
		int addressId = getAddressId(street, city, state, zip, country);
		int personId = getPersonIdWithCode(primaryContactPersonCode);
		int customerId = getCustomerIdWithCode(customerCode);

	
		// 3* Create a query and Execute a prepared query
		if (personId != 0 && customerId == 0 && addressId != 0) {
			String query = "Insert into Customer(customerCode, customerType, customerName, primaryContactCode,addressId, personId) values (?,?,?,?,?,?)";
			PreparedStatement ps = null;
			
			// 4 * Process the result
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, customerCode);
				ps.setString(2, customerType);
				ps.setString(3, name);
				ps.setString(4, primaryContactPersonCode);
				ps.setInt(5, addressId); // ?
				ps.setInt(6, personId); // ?
				ps.executeUpdate();
				ps.close();
				conn.close();
				
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 6. Removes all product records from the database
	 */

	public static void removeAllProducts() {
		
		// *1 & 2 Load the JDBC Driver & Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();
		
		// 3* Create a query and Execute a prepared query
		String query = "delete from Product";
		PreparedStatement ps = null;
		
		// 4 * Process the result
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// 5 * Close connection twice for confirmation
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 7. Adds a concession record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param unitCost
	 */
	public static void addConcession(String productCode, String productLabel, double unitCost) {

		// *1 & 2 Load the JDBC Driver & Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();
		
		int productId = getProductIdWithCode(productCode);

		if (productId == 0) {	
			// 3* Create a query and Execute a prepared query
			String query = "insert into Product(productCode, productType, productLabel,unitCost) values (?,?,?,?)";
			PreparedStatement ps = null;
			
			// 4 * Process the result
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, productCode);
				ps.setString(2, "C");
				ps.setString(3, productLabel);
				ps.setDouble(4, unitCost);
				ps.executeUpdate();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 8. Adds a repair record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param proudctLabel
	 * @param partsCost
	 * @param laborRate
	 */
	public static void addRepair(String productCode, String productLabel, double partsCost, double laborRate) {
		
		// *1 & 2 Load the JDBC Driver & Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();
		
		int productId = getProductIdWithCode(productCode);

		if (productId == 0) {
			
			// 3* Create a query and Execute a prepared query
			String query = "insert into Product(productCode, productType, productLabel,partsCost,hourlyLabourCost) values (?,?,?,?,?)";				
			PreparedStatement ps = null;
			
			// 4 * Process the result
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, productCode);
				ps.setString(2, "F");
				ps.setString(3, productLabel);
				ps.setDouble(4, partsCost);
				ps.setDouble(5, laborRate);
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 9. Adds a towing record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param costPerMile
	 */
	public static void addTowing(String productCode, String productLabel, double costPerMile) {
		
		// *1 & 2 Load the JDBC Driver & Create a connection to our DB	
		Connection conn = DatabaseInfo.getConnection();
	
		int productId = getProductIdWithCode(productCode);

		if (productId == 0) {
			// 3* Create a query and Execute a prepared query
			String query = "insert into Product(productCode, productType, productLabel,costPerMile) values (?,?,?,?)";
			
			PreparedStatement ps = null;
			
			// 4 * Process the result
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, productCode);
				ps.setString(2, "T");
				ps.setString(3, productLabel);
				ps.setDouble(4, costPerMile);
				ps.executeUpdate();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);

			}
			
			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 10. Adds a rental record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param dailyCost
	 * @param deposit
	 * @param cleaningFee
	 */
	public static void addRental(String productCode, String productLabel, double dailyCost, double deposit,
			double cleaningFee) {
		/* TODO */
		int productId = getProductIdWithCode(productCode);
		if (productId == 0) {
			Connection conn = DatabaseInfo.getConnection();
			String query = "insert into Product(productCode, productType, productLabel, dailyCost, deposit,cleaningFee) values (?,?,?,?,?,?)";

			PreparedStatement ps = null;

			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, productCode);
				ps.setString(2, "R");
				ps.setString(3, productLabel);
				ps.setDouble(4, dailyCost);
				ps.setDouble(5, deposit);
				ps.setDouble(6, cleaningFee);
				ps.executeUpdate();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		/* TODO */
		Connection conn = DatabaseInfo.getConnection();

		String query1 = "delete from InvoiceProduct";
		String query2 = "delete from Invoice";

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(query1);
			ps.executeUpdate();
			ps = conn.prepareStatement(query2);
			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		// 5 * Close connection twice for confirmation
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 * 
	 * @param invoiceCode
	 * @param ownerCode
	 * @param customertCode
	 */
	public static void addInvoice(String invoiceCode, String ownerCode, String customerCode) {
		/* TODO */

		int personId = getPersonIdWithCode(ownerCode);
		int customerId = getCustomerIdWithCode(customerCode);
		int invoiceId = getInvoiceIdWithCode(invoiceCode);

		if (personId != 0 && customerId != 0 && invoiceId == 0) {

			Connection conn = DatabaseInfo.getConnection();

			String query = "insert into Invoice(invoiceCode, personId, customerId) values (?,?,?)";

			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, invoiceCode);
				ps.setInt(2, personId);
				ps.setInt(3, customerId);
				ps.executeUpdate();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 13. Adds a particular Towing (corresponding to <code>productCode</code> to an
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of miles towed
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param milesTowed
	 */
	public static void addTowingToInvoice(String invoiceCode, String productCode, double milesTowed) {
		/* TODO */
		int invoiceId = getInvoiceIdWithCode(invoiceCode);
		int productId = getProductIdWithCode(productCode);
		int invoiceProductId = getInvoiceProductIdWithCode(productCode, invoiceCode);

		if (invoiceProductId == 0 && invoiceId != 0 && productId != 0) {

			Connection conn = DatabaseInfo.getConnection();

			String query = "insert into InvoiceProduct(productId, invoiceId, milesTowed) values (?,?,?)";
			;
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, productId);
				ps.setInt(2, invoiceId);
				ps.setDouble(3, milesTowed);
				ps.executeUpdate();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 14. Adds a particular Repair (corresponding to <code>productCode</code> to an
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of hours worked
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param hoursWorked
	 */
	public static void addRepairToInvoice(String invoiceCode, String productCode, double hoursWorked) {
		/* TODO */
		int invoiceId = getInvoiceIdWithCode(invoiceCode);
		int productId = getProductIdWithCode(productCode);
		int invoiceProductId = getInvoiceProductIdWithCode(productCode, invoiceCode);

		if (invoiceProductId == 0 && invoiceId != 0 && productId != 0) {
			Connection conn = DatabaseInfo.getConnection();

			String query = "insert into InvoiceProduct(productId, invoiceId, hoursWorked) values (?,?,?)";

			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, productId);
				ps.setInt(2, invoiceId);
				ps.setDouble(3, hoursWorked);
				ps.executeUpdate();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 15. Adds a particular Concession (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given number of quantity. NOTE: repairCode may be null
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param quantity
	 * @param repairCode
	 */
	public static void addConcessionToInvoice(String invoiceCode, String productCode, int quantity, String repairCode) {
		/* TODO */
		int invoiceId = getInvoiceIdWithCode(invoiceCode);
		int productId = getProductIdWithCode(productCode);
		int idForRepair = getProductIdWithCode(repairCode);

		int invoiceProductId = getInvoiceProductIdWithCode(productCode, invoiceCode);

		if (invoiceProductId == 0 && invoiceId != 0 && productId != 0) {

			Connection conn = DatabaseInfo.getConnection();

			String query = null;
			if (idForRepair != 0) {
				query = "insert into InvoiceProduct(productId, invoiceId, quantity, associatedRepair) values (?,?,?,?)";

				PreparedStatement ps = null;
				try {
					ps = conn.prepareStatement(query);
					ps.setInt(1, productId);
					ps.setInt(2, invoiceId);
					ps.setInt(3, quantity);
					ps.setInt(4, idForRepair);
					ps.executeUpdate();
					ps.close();
					conn.close();

				} catch (SQLException e) {
					System.out.println("SQLException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				
				// 5 * Close connection twice for confirmation
				try {
					if (ps != null && !ps.isClosed()) {
						ps.close();
					}
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				query = "insert into InvoiceProduct(productId, invoiceId, quantity) values (?,?,?)";

				PreparedStatement ps = null;
				try {
					ps = conn.prepareStatement(query);
					ps.setInt(1, productId);
					ps.setInt(2, invoiceId);
					ps.setInt(3, quantity);
					ps.executeUpdate();
					ps.close();
					conn.close();

				} catch (SQLException e) {
					System.out.println("SQLException: ");
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				
				// 5 * Close connection twice for confirmation
				try {
					if (ps != null && !ps.isClosed()) {
						ps.close();
					}
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	/**
	 * 16. Adds a particular Rental (corresponding to <code>productCode</code> to an
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of days rented.
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param daysRented
	 */
	public static void addRentalToInvoice(String invoiceCode, String productCode, double daysRented) {
		/* TODO */
		int invoiceId = getInvoiceIdWithCode(invoiceCode);
		int productId = getProductIdWithCode(productCode);
		int invoiceProductId = getInvoiceProductIdWithCode(productCode, invoiceCode);

		if (invoiceProductId == 0 && invoiceId != 0 && productId != 0) {
			Connection conn = DatabaseInfo.getConnection();

			String query = "insert into InvoiceProduct(productId, invoiceId, daysRented) values (?,?,?)";

			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, productId);
				ps.setInt(2, invoiceId);
				ps.setDouble(3, daysRented);
				ps.executeUpdate();
				ps.close();
				conn.close();

			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			// 5 * Close connection twice for confirmation
			try {
				if (ps != null && !ps.isClosed()) {
					ps.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

// Extra  Helper Functions
	
	public static int getCountryId(String country) {
		int addressCountryId = 0;

		Connection conn = DatabaseInfo.getConnection();
		// 3* Create a query and Execute a prepared query
		String query = "Select addressCountryId from AddressCountry where country = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		// 4 * Process the result
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, country);
			rs = ps.executeQuery();

			// 4 b)checking if the countryId already exists else return 0
			if (rs.next()) {
				addressCountryId = rs.getInt("addressCountryId");
				rs.close();
				ps.close();
				conn.close();
				return addressCountryId;
			}

			
			query = "Insert into AddressCountry (country) values (?)";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, country);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				addressCountryId = rs.getInt(1);
			}
			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		// 5 * Close connection twice for confirmation
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addressCountryId;
	}

	public static int getStateId(String state) {

		int addressStateId = 0;

		// 2 *Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();

		// 3* Create a query and Execute a prepared query
		String query = "Select addressStateId from AddressState where state = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		// 4 * Process the result

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, state);
			rs = ps.executeQuery();

			if (rs.next()) {
				addressStateId = rs.getInt("addressStateId");
				rs.close();
				ps.close();
				conn.close();
				return addressStateId;
			}

			//4b checking if the stateId already exists and if not create one
			query = "Insert into AddressState (state) values (?)";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, state);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				addressStateId = rs.getInt(1);

			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		
		// 5 * Close connection twice for confirmation
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addressStateId;
	}

	public static int getAddressId(String street, String city, String state, String zip, String country) {

		int addressCountryId = getCountryId(country);
		int addressStateId = getStateId(state);
		int addressId = 0;

		// 2 *Create a connection to our DB
		Connection conn = DatabaseInfo.getConnection();

		// 3* Create a query and Execute a prepared query
		String query = "Select addressId from Address where street = ? and  city = ? and addressStateId = ? and zip = ? "
				+ "and addressCountryId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 4 * Process the result

		try {
			ps = conn.prepareStatement(query); // Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setInt(3, addressStateId);
			ps.setString(4, zip);
			ps.setInt(5, addressCountryId);
			rs = ps.executeQuery();

			//4b  checking if the stateId already exists and if not create one
			if (rs.next()) {
				addressId = rs.getInt("addressId");
				rs.close();
				ps.close();
				conn.close();
				return addressId;
			}
			query = "Insert into Address (street, city , addressStateId, zip, addressCountryId) values (?,?,?,?,?)";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setInt(3, addressStateId);
			ps.setString(4, zip);
			ps.setInt(5, addressCountryId);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				addressId = rs.getInt(1);
				rs.close();
				ps.close();
				conn.close();
				return addressId;
			}
			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// 5 * Close connection twice for confirmation
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addressId;
	}

	public static int getPersonIdWithCode(String personCode) {

		int personId = 0;

		Connection conn = DatabaseInfo.getConnection();

		String query = "select personId from Person where personCode = ?"; // retrieves the personID of specifc person

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();

			// closing the resources
			if (rs.next()) {
				personId = rs.getInt("personId");
			} else {
				rs.close();
				ps.close();
				conn.close();
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// 5 * Close connection twice for confirmation
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personId;

	}

	public static int getCustomerIdWithCode(String customerCode) {

		int customerId = 0;

		Connection conn = DatabaseInfo.getConnection();

		String query = "select customerId from Customer where customerCode = ?"; // retrieves the personID of specifc
																					// person

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, customerCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				customerId = rs.getInt("customerId");
			} else {
				rs.close();
				ps.close();
				conn.close();
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		// 5 * Close connection twice for confirmation
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
		return customerId;

	}

	public static int getProductIdWithCode(String productCode) {

		int productId = 0;

		Connection conn = DatabaseInfo.getConnection();
		String query = "select productId from Product where productCode = ?"; // retrieves the personID of specifc
																				// person

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				productId = rs.getInt("productId");
			} else {
				rs.close();
				ps.close();
				conn.close();
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		// 5 * Close connection twice for confirmation
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
		return productId;

	}

	public static int getInvoiceIdWithCode(String invoiceCode) {

		int invoiceId = 0;

		Connection conn = DatabaseInfo.getConnection();

		String query = "select invoiceId from Invoice where invoiceCode = ?"; // retrieves the personID of specifc
																				// person

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				invoiceId = rs.getInt("invoiceId");
			} else {
				rs.close();
				ps.close();
				conn.close();
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		// 5 * Close connection twice for confirmation
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invoiceId;

	}

	public static int getInvoiceProductIdWithCode(String productCode, String invoiceCode) {

		int productId = getProductIdWithCode(productCode);
		int invoiceId = getInvoiceIdWithCode(invoiceCode);
		int invoiceProductId = 0;

		Connection conn = DatabaseInfo.getConnection();

		String query = "select invoiceProductId from InvoiceProduct where productId = ? and invoiceId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, invoiceId);
			rs = ps.executeQuery();

			// closing the resources
			if (rs.next()) {
				invoiceProductId = rs.getInt("invoiceProductId");
			} else {
				rs.close();
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		// 5 * Close connection twice for confirmation
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invoiceProductId;

	}

}
