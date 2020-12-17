package asg2;

public class Customer {
	private String customerCode;
	private String customerType;
	private String name;
	private Person primaryContactCode;
	private Address address;

	public Customer(String customerCode, String customerType, String name, Person primaryContactCode,
			Address address) {
		this.customerCode = customerCode;
		this.customerType = customerType;
	 	this.name = name;
		this.primaryContactCode = primaryContactCode;
		this.address = address;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getPrimaryContactCode() {
		return primaryContactCode;
	}

	public void setPrimaryContactCode(Person primaryContactCode) {
		this.primaryContactCode = primaryContactCode;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}