package Model;

import Enums.CustomerTypeName;

public class CustomerType {

	private CustomerTypeName name;
	
	private int discount;
	
	private int requiredPoints;

	public CustomerType() {
		super();
	}

	public CustomerType(CustomerTypeName name, int discount, int requiredPoints) {
		super();
		this.name = name;
		this.discount = discount;
		this.requiredPoints = requiredPoints;
	}

	public CustomerTypeName getName() {
		return name;
	}

	public void setName(CustomerTypeName name) {
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
