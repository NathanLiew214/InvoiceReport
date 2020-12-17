package asg2;

public class Rental extends Product {
	private double dailyCost;
	private double deposit;
	private double cleaningFee;
	private double daysRented;

	public Rental(String productCode, String productType, String productLabel, double dailyCost, double deposit,
			double cleaningFee) {
		super(productCode, productType, productLabel);
		this.dailyCost = dailyCost;
	 	this.deposit = deposit;
		this.cleaningFee = cleaningFee;	
	}
	
	public Rental(String productCode, String productType, String productLabel, double dailyCost, double deposit,
			double cleaningFee,double daysRented) {
		super(productCode, productType, productLabel);
		this.dailyCost = dailyCost;
	 	this.deposit = deposit;
		this.cleaningFee = cleaningFee;	
		this.daysRented =  daysRented;	
	}
	
	public Rental(Rental rental) {
		super(rental.getProductCode(),rental.getProductType(), rental.getProductLabel());
		this.dailyCost =rental .getDailyCost();
	 	this.deposit = rental.getDeposit();
		this.cleaningFee = rental.getCleaningFee();
		this.daysRented = rental.getDaysRented();

	}

	
	public double getDailyCost() {
		return dailyCost;
	}
	
	public void setDailyCost(double dailyCost) {
		this.dailyCost = dailyCost;
	}
	

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getCleaningFee() {
		return cleaningFee;
	}

	public void setCleaningFee(double cleaningFee) {
		this.cleaningFee = cleaningFee;
	}


	public double getDaysRented() {
		return daysRented;
	}


	public void setDaysRented(double daysRented) {
		this.daysRented = daysRented;
	}


	
	

}