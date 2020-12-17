package asg2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Invoice {
	@Override
	public String toString() {
		return "Invoice [invoiceCode=" + invoiceCode + ", ownerCode=" + ownerCode + ", customerCode=" + customerCode
				+ ", listOfProducts=" + Arrays.toString(listOfProducts) + ", linkInvoiceProduct=" + linkInvoiceProduct
				+ "]";
	}

	String invoiceCode;
	String ownerCode;
	String customerCode;
	String[] listOfProducts;
	private ArrayList<Product> linkInvoiceProduct;
	private double total;
	

	public Invoice(String invoiceCode, String ownerCode, String customerCode, String[] listOfProducts,
			ArrayList<Product> linkInvoiceProduct) {
		this.invoiceCode = invoiceCode;
		this.ownerCode = ownerCode;
		this.customerCode = customerCode;
		this.listOfProducts = listOfProducts;
		this.linkInvoiceProduct = linkInvoiceProduct;
	}

	

	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}




	public String getInvoiceCode() {
		return invoiceCode;
	}


	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String[] getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(String[] listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	
	public ArrayList<Product> getLinkInvoiceProduct() {
		return linkInvoiceProduct;
	}

	public void setLinkInvoiceProduct(ArrayList<Product> linkInvoiceProduct) {
		this.linkInvoiceProduct = linkInvoiceProduct;
	}

	public static Comparator<Invoice> InvoiceComparator = new Comparator<Invoice>()
	{

		public int compare(Invoice invoice1, Invoice invoice2){

			return Double.compare(invoice1.getTotal(), invoice2.getTotal());
		}

	};

}
