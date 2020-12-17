package asg2;

public abstract class Product {
	private String productCode;
	private String productType;
	private String productLabel;

	public Product(String productCode, String productType, String productLabel) {
		this.productCode = productCode;
		this.productType = productType;
		this.productLabel = productLabel;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductLabel() {
		return productLabel;
	}

	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}

}