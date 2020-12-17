package asg2;

public class Concession extends Product {

	private double unitCost;
	private double quantity;
	boolean hasAssociatedRepair;
	String associatedRepair;
	

	public Concession(String productCode, String productType, String productLabel, double unitCost) {
		super(productCode, productType, productLabel);
		this.unitCost = unitCost;
	}
	
	public Concession(String productCode, String productType, String productLabel, double unitCost,double quantity) {
		super(productCode, productType, productLabel);
		this.unitCost = unitCost;
		this.quantity = quantity;
	}
	
	
	public Concession(Concession concession) {
		super(concession.getProductCode(),concession.getProductType() ,concession.getProductLabel());
		this.unitCost = concession.getUnitCost();
		this.quantity= concession.getQuantity();
		
	}
	
	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}


	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}


//	public Repair getAssociatedRepair() {
//		return associatedRepair;
//	}
//
//
//	public void setAssociatedRepair(Repair associatedRepair) {
//		this.associatedRepair = associatedRepair;
//	}

	public boolean isHasAssociatedRepair() {
		return hasAssociatedRepair;
	}


	public void setHasAssociatedRepair(boolean hasAssociatedRepair) {
		this.hasAssociatedRepair = hasAssociatedRepair;
	}
	

	public String getAssociatedRepair() {
		return associatedRepair;
	}


	public void setAssociatedRepair(String associatedRepair) {
		this.associatedRepair = associatedRepair;
	}

	@Override
	public String toString() {
		return "Concession [unitCost=" + unitCost + ", quantity=" + quantity + ", associatedRepair=" + associatedRepair
				+ "]";
	}

	
	
}
