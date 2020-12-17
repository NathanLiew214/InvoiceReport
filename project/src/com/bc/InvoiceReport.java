
/**
Name: Nathan Liew & Dinesh Budhathoki
Program: InvoiceReport.java
Purpose : Print invoice report from database
**/

package com.bc;

import java.util.ArrayList;
import java.util.Arrays;

import asg2.Address;
import asg2.Concession;
import asg2.Customer;
import asg2.Invoice;
import asg2.MyLinkedList;
import asg2.Person;
import asg2.Product;
import asg2.Rental;
import asg2.Repair;
import asg2.Towing;

public class InvoiceReport {

	public double taxTOTAL = 0;
	private static double tax = 0;

	static ArrayList<Person> persons;
	static ArrayList<Customer> customers;
	static ArrayList<Product> products;
	static MyLinkedList<Invoice> invoices;
	
	public static void main(String[] args) {

		persons = DatabaseReader.getPerson();
		customers = DatabaseReader.getCustomer();
		products = DatabaseReader.getProduct();
		invoices = DatabaseReader.getInvoice();
		
		// PRINT INVOICE DETAILS
		printExecutiveSummaryReport(invoices, persons, customers, products);
		printInvoiceDetails(invoices, persons, customers, products);
	}

	// Summary Report
	private static void printExecutiveSummaryReport(MyLinkedList<Invoice> invoices, ArrayList<Person> persons, 
			ArrayList<Customer> customers, ArrayList<Product> products) {
		StringBuilder sb = new StringBuilder();

		System.out.println(sb);

		System.out.println("Executive Summary Report:\n");
		System.out.println(String.format("%-12s %-30s %-31s %-12s %-12s %-12s %-12s %-12s", "Code", "Owner",
				"Customer Account", "Subtotal", "Discount", "Fees", "Taxes", "Total"));
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------");

		Customer cPrint = null;
		Person pPrint = null;
		String tPrint = null;
		double taxRate = 0.0;
		double businessProcessingFee = 0.0;
		double totalSubtotal = 0;
		double totalDiscounts = 0;
		double totalTax = 0;
		double totalFee = 0;
		double totalGrandTotal = 0.0;
		
		for (int i = 0; i < invoices.getsize(); i++) {
			Invoice invoice = invoices.retrieveItemAtIndex(i);
			ArrayList<Product> getLP = invoice.getLinkInvoiceProduct();
			for (Person p : persons) {
				if (invoice.getOwnerCode().contains(p.getPersonCode())) {
					for (Customer c : customers) {
						if (invoice.getCustomerCode().contains(c.getCustomerCode())) {
							cPrint = c;

							pPrint = p;

							tPrint = c.getCustomerType();
							// Get Customer Type B or P
							if (tPrint.contains("B")) {
								taxRate = 4.25;
								businessProcessingFee = 75.5;
							} else {
								taxRate = 8.0;
								businessProcessingFee = 0.00;
							}
							totalFee += businessProcessingFee;
						}
					}
				}
			}

			totalSubtotal += calculationSubtotal(invoice);
			double discounts = calculateDiscount(invoice);
			totalDiscounts += discounts;
			double tax = Math.round(((calculationSubtotal(invoice) * taxRate) / 100.00) * 100.00) / 100.00;
			totalTax += tax;
			double grandTotal = Math.round((calculationSubtotal(invoice) + discounts + tax + businessProcessingFee) * 100.00) / 100.00;
			totalGrandTotal += grandTotal;

			System.out.println(String.format("%-12s %-30s %-30s $%10.2f $%10.2f $%10.2f $%10.2f $%10.2f",
					invoice.getInvoiceCode(), (pPrint.getLastName() + "," + pPrint.getFirstName()), cPrint.getName(),
					calculationSubtotal(invoice), discounts, businessProcessingFee, tax, grandTotal));
		}

		System.out.println(
				"================================================================================================================================");
		System.out.println(String.format("%-75s", "TOTALS") + String.format("$%10.2f $%10.2f $%10.2f $%10.2f $%10.2f",
				totalSubtotal, totalDiscounts, totalTax, totalFee, totalGrandTotal));
	}
	
	private static void printInvoiceDetails(MyLinkedList<Invoice> invoices, ArrayList<Person> persons,
			ArrayList<Customer> customers, ArrayList<Product> products) {
		Person pPrint = null;
		Customer cPrint = null;
		Person contact = null;
		String tPrint = null;
		// Customer Type
		double taxRate = 0.0;
		double businessProcessingFee = 0.0;
		double discount = 0.00;
		double pLoyaltyDiscount = 0.00;

		for (int i = 0; i < invoices.getsize(); i++) {
			Invoice invoice = invoices.retrieveItemAtIndex(i);
			ArrayList<Product> getLP = invoice.getLinkInvoiceProduct();

			System.out.println("\n\n\nInvoice Details");
			System.out.println(
					"=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
			System.out.println("Invoice  " + invoice.getInvoiceCode());
			System.out.println("---------------------------------------------");
			String Type = null;
			for (Person p : persons) {
				if (invoice.getOwnerCode().contains(p.getPersonCode())) {
					for (Customer c : customers) {
						if (invoice.getCustomerCode().contains(c.getCustomerCode())) {
							pPrint = p;
							cPrint = c;
							contact = c.getPrimaryContactCode();
							tPrint = c.getCustomerType();
							// Get Customer Type B or P
							// set the tax rates
							if (tPrint.contains("B")) {
								taxRate = 4.25;
								businessProcessingFee = 75.5;
								Type = "Business Account";

							} else {
								taxRate = 8.0;
								businessProcessingFee = 0.00;
								Type = "Personal Account";
							}
						}
					}
				}
			}
			Address getPersAdd;
			Address getCustAdd;
			if ((pPrint != null) && (cPrint != null)) {
				getPersAdd = pPrint.getAddress();
				getCustAdd = cPrint.getAddress();

				System.out.println("Owner:");
				System.out.println("   " + pPrint.getLastName() + "," + pPrint.getFirstName() + ",");
				System.out.println("   " + Arrays.toString(pPrint.getEmailAddress()));
				System.out.println("   " + getPersAdd.getStreet());
				System.out.println("   " + getPersAdd.getCity() + ", " + getPersAdd.getState() + " "
						+ getPersAdd.getCountry() + " " + getPersAdd.getZip());
				System.out.println("Customer:");
				System.out.println("   " + cPrint.getName() + " (" + Type + ")");
				System.out.println("   " + getCustAdd.getStreet());
				System.out.println("   " + getCustAdd.getCity() + ", " + getCustAdd.getState() + " "
						+ getCustAdd.getCountry() + " " + getCustAdd.getZip());

			} else {
				System.out.println("Owner:");
				System.out.println("   ");
				System.out.println("   ");
				System.out.println("   ");
				System.out.println("   ");
				System.out.println("Customer:");
				System.out.println("   ");
				System.out.println("   ");
				System.out.println("   ");
			}

			System.out.println("Products:\n");
			System.out.println("  " + String.format("%-12s", "Code") + String.format("%-71s", "Description")
					+ String.format("%-13s", "Subtotal") + String.format("%-13s", "Discount")
					+ String.format("%-13s", "Taxes") + String.format("%-13s", "Total"));
			System.out.println(
					"  ------------------------------------------------------------------------------------------------------------------------------------");

			boolean repair = false;
			boolean rental = false;

			// If contains associate Repair return true
			for (Product product : getLP) {
				if (product.getProductType().contains("F"))
					repair = true;
				if (product.getProductType().contains("R"))
					rental = true;
			}
			double invoiceItemTotals = 0.00;
			double invoiceDiscountTotals = 0.00;
			double invoiceTaxTotals = 0.00;
			for (Product product : getLP) {
				System.out.print("  " + String.format("%-11s", product.getProductCode()) + " ");
				double itemTotal = 0.00;
				if (product.getProductType().contains("R")) {
					Rental rLP = (Rental) product;
					double totalDailyCost = rLP.getDaysRented() * rLP.getDailyCost();
					subtotal = Math.round((totalDailyCost + rLP.getCleaningFee() - rLP.getDeposit()) * 100.00) / 100.00;
					System.out.print(String.format("%-70s", (product.getProductLabel() + " ($" + rLP.getDaysRented()
							+ " days @ $" + rLP.getDailyCost() + "day)")));
					System.out.print("$" + String.format("%10.2f", subtotal));
					// DISCOUNT
					discount = 0.00;
					System.out.print("  $" + String.format("%10.2f", discount));
					// TAXES
					tax = Math.round(((subtotal * taxRate) / 100.00) * 100.00) / 100.00;
					System.out.print("  $" + String.format("%10.2f", tax));
					// Total
					double lineTotal = subtotal + discount + tax;
					System.out.print("  $" + String.format("%10.2f", lineTotal));
					System.out.println();
					System.out.println(String.format("%14s", "") + "(+ $" + rLP.getCleaningFee() + " cleaning fee, -$"
							+ rLP.getDeposit() + " deposit refund)");
					itemTotal += subtotal;
					invoiceItemTotals += itemTotal;
					invoiceTaxTotals += tax;
				}
				if (product.getProductType().contains("F")) {
					Repair fLP = (Repair) product;
					double totalHourlyLaborCost = fLP.getHoursWorked() * fLP.getHourlyLaborCost();
					subtotal = Math.round((totalHourlyLaborCost + fLP.getPartsCost()) * 100.00) / 100.00;
					System.out.print(String.format("%-70s", (product.getProductLabel() + " (" + fLP.getHoursWorked()
							+ " hours of labor @ $" + fLP.getHourlyLaborCost() + "/hour)")));
					System.out.print("$" + String.format("%10.2f", subtotal));
					// DISCOUNT
					discount = 0.00;
					System.out.print("  $" + String.format("%10.2f", discount));
					// TAXES
					tax = Math.round(((subtotal * taxRate) / 100.00) * 100.00) / 100.00;
					System.out.print("  $" + String.format("%10.2f", tax));
					// Total
					double lineTotal = subtotal + discount + tax;
					System.out.print("  $" + String.format("%10.2f", lineTotal));
					System.out.println();
					System.out.println(String.format("%14s", "") + "(+ $" + fLP.getPartsCost() + " for parts)");
					itemTotal += subtotal;
					invoiceItemTotals += itemTotal;
					invoiceTaxTotals += tax;
				}
				if (product.getProductType().contains("C")) {
					Concession cLP = (Concession) product;
					double totalUnitCost = cLP.getQuantity() * cLP.getUnitCost();
					subtotal = Math.round((totalUnitCost) * 100.00) / 100.00;
					System.out.print(String.format("%-70s", (product.getProductLabel() + " ($" + cLP.getQuantity()
							+ " units @ $" + cLP.getUnitCost() + "/unit)")));
					System.out.print("$" + String.format("%10.2f", subtotal));
					// DISCOUNT
					if (cLP.isHasAssociatedRepair() == true) {
						discount = (totalUnitCost * -0.10);
					} else {
						discount = 0.00;

					}

					System.out.print("  $" + String.format("%10.2f", discount));
					// TAXES
					tax = Math.round((((subtotal + discount) * taxRate) / 100.00) * 100.00) / 100.00;
					System.out.print("  $" + String.format("%10.2f", tax));
					// Total
					double lineTotal = subtotal + discount + tax;
					System.out.print("  $" + String.format("%10.2f", lineTotal));
					System.out.println();
					itemTotal += subtotal;
					invoiceItemTotals += itemTotal;
					invoiceDiscountTotals += discount;
					invoiceTaxTotals += tax;
				}
				if (product.getProductType().contains("T")) {
					Towing tLP = (Towing) product;
					double totalCostPerMile = tLP.getMilesTowed() * tLP.getCostPerMile();
					subtotal = Math.round((totalCostPerMile) * 100.00) / 100.00;
					System.out.print(String.format("%-70s", (product.getProductLabel() + " (" + tLP.getMilesTowed()
							+ " miles @ $" + tLP.getCostPerMile() + "/mile)")));
					System.out.print("$" + String.format("%10.2f", subtotal));
					// DISCOUNT
					if ((repair) && (rental) == true) {
						discount = -subtotal;
					} else {
						discount = 0.00;
					}
					System.out.print("  $" + String.format("%10.2f", discount));
					// TAXES
					tax = Math.round((((subtotal + discount) * taxRate) / 100.00) * 100.00) / 100.00;
					System.out.print("  $" + String.format("%10.2f", tax));
					// Total
					double lineTotal = subtotal + discount + tax;
					System.out.print("  $" + String.format("%10.2f", lineTotal));
					System.out.println();
					itemTotal += subtotal;
					invoiceItemTotals += itemTotal;
					invoiceDiscountTotals += discount;
					invoiceTaxTotals += tax;
				}
			}

			System.out.println();

			System.out.println(
					"======================================================================================================================================");
			double invoiceTotalSubtotal = invoiceItemTotals + invoiceDiscountTotals + invoiceTaxTotals;
			double grandTotal = 0.00;
			System.out.println(String.format("%-84s", "ITEMS TOTAL:") + "$" + String.format("%10.2f", invoiceItemTotals)
					+ "  $" + String.format("%10.2f", invoiceDiscountTotals) + "  $"
					+ String.format("%10.2f", invoiceTaxTotals) + "  $"
					+ String.format("%10.2f", invoiceTotalSubtotal));
			if (tPrint.contains("B")) {
				System.out.println(String.format("%-123s", "BUSINESS ACCOUNT FEE:") + "$"
						+ String.format("%10.2f", businessProcessingFee));
				grandTotal = invoiceTotalSubtotal + businessProcessingFee;
			}

			else {
				if (contact.getEmailAddress().length > 1) {
					pLoyaltyDiscount = 5.0;
					double appliedPLoyaltyDiscount = Math
							.round(((-invoiceTotalSubtotal * pLoyaltyDiscount) / 100.00) * 100.00) / 100.00;
					System.out.println(String.format("%-123s", "LOYAL CUSTOMER DISCOUNT (5% OFF):") + "$"
							+ String.format("%10.2f", appliedPLoyaltyDiscount));
					grandTotal = invoiceTotalSubtotal + appliedPLoyaltyDiscount;
				} else {
					grandTotal = invoiceTotalSubtotal;
				}
			}

			System.out.println(String.format("%-123s", "GRANDTOTAL:") + "$" + String.format("%10.2f", grandTotal));
			System.out.println("\n\n\n\t\tTHANK YOU FOR DOING BUSINEESS WITH US!");
		}
	}
	
	private static double subtotal = 0;

	public static double calculateInvoiceTotal(Invoice invoice) {
		
		Customer cPrint = null;
		Person pPrint = null;
		String tPrint = null;
		
		double taxRate = 0.0;
		double businessProcessingFee = 0.0;
		
		ArrayList<Product> getLP = invoice.getLinkInvoiceProduct();
		for (Person p : persons) {
			if (invoice.getOwnerCode().contains(p.getPersonCode())) {
				for (Customer c : customers) {
					if (invoice.getCustomerCode().contains(c.getCustomerCode())) {
						cPrint = c;
						pPrint = p;
						tPrint = c.getCustomerType();
						// Get Customer Type B or P
						if (tPrint.contains("B")) {
							taxRate = 4.25;
							businessProcessingFee = 75.5;
						} else {
							taxRate = 8.0;
							businessProcessingFee = 0.00;
						}
					}
				}
			}
		}
		
		double discounts = InvoiceReport.calculateDiscount(invoice);
		double tax = Math.round(((InvoiceReport.calculationSubtotal(invoice) * taxRate) / 100.00) * 100.00) / 100.00;
		double grandTotal = Math.round((InvoiceReport.calculationSubtotal(invoice) + discounts + tax + businessProcessingFee) * 100.00) / 100.00;

		return grandTotal;
		
	}
	
	public static double calculateDiscount(Invoice invoice) {
		double discountTotal = 0.00;
		double discount = 0.00;
		ArrayList<Product> getLP = invoice.getLinkInvoiceProduct();

		boolean repair = false;
		boolean rental = false;

		for (Product product : getLP) {
			if (product.getProductType().contains("F"))
				repair = true;
			if (product.getProductType().contains("R"))
				rental = true;
		}

		for (Product p : getLP) {

			if (p.getProductType().contains("C")) {
				Concession cLP = (Concession) p;
				double totalUnitCost = cLP.getQuantity() * cLP.getUnitCost();
				if (repair) {
					discount = (totalUnitCost * -0.10);
				} else {
					discount = 0.00;
				}
				discountTotal += discount;

			} else if (p.getProductType().contains("T")) {
				Towing tLP = (Towing) p;
				double totalCostPerMile = tLP.getMilesTowed() * tLP.getCostPerMile();
				if ((repair) && (rental) == true) {
					discount = -totalCostPerMile;
				} else {
					discount = 0.00;	
				}
				discountTotal += discount;
			}

		}

		return discountTotal;
	}

	public static double calculationSubtotal(Invoice invoice) {

		double itemTOTAL = 0;
		ArrayList<Product> getLP = invoice.getLinkInvoiceProduct();

		for (Product p : getLP) {

			if (p.getProductType().contains("R")) {
				Rental rLP = (Rental) p;
				double totalDailyCost = rLP.getDaysRented() * rLP.getDailyCost();
				subtotal = totalDailyCost + rLP.getCleaningFee() - rLP.getDeposit();
				itemTOTAL += subtotal;

			} else if (p.getProductType().contains("F")) {
				Repair fLP = (Repair) p;
				double totalHourlyLaborCost = fLP.getHoursWorked() * fLP.getHourlyLaborCost();
				subtotal = totalHourlyLaborCost + fLP.getPartsCost();
				itemTOTAL += subtotal;

			} else if (p.getProductType().contains("C")) {

				Concession cLP = (Concession) p;
				double totalUnitCost = cLP.getQuantity() * cLP.getUnitCost();
				subtotal = totalUnitCost;
				itemTOTAL += subtotal;

			} else if (p.getProductType().contains("T")) {
				Towing tLP = (Towing) p;
				double totalCostPerMile = tLP.getMilesTowed() * tLP.getCostPerMile();
				subtotal = totalCostPerMile;
				itemTOTAL += subtotal;
			}

		}

		return itemTOTAL;

	}

}
