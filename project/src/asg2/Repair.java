package asg2;

public class Repair extends Product {

	private double partsCost;
	private double hourlyLaborCost;
	private double hoursWorked;
	
	public Repair(String productCode, String productType, String productLabel, double partsCost,
			double hourlyLaborCost,double hoursWorked) {
		super(productCode, productType, productLabel);
		this.partsCost = partsCost;
		this.hourlyLaborCost = hourlyLaborCost;
		this.hoursWorked = hoursWorked;
	
	}
	
	
	
	public Repair(String productCode, String productType, String productLabel, double partsCost,
			double hourlyLaborCost) {
		super(productCode, productType, productLabel);
		this.partsCost = partsCost;
		this.hourlyLaborCost = hourlyLaborCost;
	}
	

	public Repair(Repair repair) {
		super(repair.getProductCode(), repair.getProductType() , repair.getProductLabel());
		this.partsCost = repair.getPartsCost();
		this.hourlyLaborCost = repair.getHourlyLaborCost();
		this.hoursWorked = repair.getHoursWorked();
		
	}
	
	public double getPartsCost() {
		return partsCost;
	}


	public void setPartsCost(double partsCost) {
		this.partsCost = partsCost;
	}

	public double getHourlyLaborCost() {
		return hourlyLaborCost;
	}

	public void setHourlyLaborCost(double hourlyLaborCost) {
		this.hourlyLaborCost = hourlyLaborCost;
	}


	public double getHoursWorked() {
		return hoursWorked;
	}


	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	

}