package Model;

import enums.CustomerTypeNames;

public class CustomerType {

	private CustomerTypeNames name;
	
	private int discount;
	
	private int requiredPoints;

	public CustomerType() {
		super();
	}

	public CustomerType(CustomerTypeNames name, int discount, int requiredPoints) {
		super();
		this.name = name;
		this.discount = discount;
		this.requiredPoints = requiredPoints;
	}

	public CustomerTypeNames getName() {
		return name;
	}

	public void setName(CustomerTypeNames name) {
		this.name = name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getRequiredPoints() {
		return requiredPoints;
	}

	public void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}
	
	
}
