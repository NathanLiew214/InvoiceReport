package asg2;

public class Towing extends Product {
	private double costPerMile;
	private double milesTowed;

	public Towing(String productCode, String productType, String productLabel, double costPerMile) {
		super(productCode, productType, productLabel);
		this.costPerMile = costPerMile;

	}
	
	public Towing(String productCode, String productType, String productLabel, double costPerMile,double milesTowed) {
		super(productCode, productType, productLabel);
		this.costPerMile = costPerMile;
		this.milesTowed = milesTowed;

	}
	
	public Towing(Towing towing) {
		super(towing.getProductCode(),towing.getProductType() ,towing.getProductLabel());
		this.costPerMile = towing.getCostPerMile();
		this.milesTowed = towing.getMilesTowed();
		
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	
	public void setCostPerMile(double costPerMile) {
		this.costPerMile = costPerMile;
	}


	public double getMilesTowed() {
		return milesTowed;
	}


	public void setMilesTowed(double milesTowed) {
		this.milesTowed = milesTowed;
	}

	
}
