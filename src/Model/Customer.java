package Model;

import java.util.ArrayList;
import java.util.List;

import enums.CustomerTypeNames;

public class Customer extends User{

	private List<Ticket> tickets;
	
	private int collectedPoints;
	
	private CustomerTypeNames customerTypeName;

	private CustomerType customerType;
	
	public Customer() {
		super();
		this.collectedPoints = 0;
		this.customerTypeName = CustomerTypeNames.BRONZE;
		this.customerType = new CustomerType(CustomerTypeNames.BRONZE, 5, 0);
	}

	public Customer(List<Ticket> tickets, int collectedPoints, CustomerTypeNames customerTypeName) {
		super();
		this.tickets = tickets;
		this.collectedPoints = collectedPoints;
		this.customerTypeName = customerTypeName;
	}
	public Customer(int collectedPoints, CustomerTypeNames customerTypeName) {
		super();
		this.collectedPoints = collectedPoints;
		this.customerTypeName = customerTypeName;
		this.tickets = new ArrayList<>();
	}
	
	public Customer(int collectedPoints) {
		super();
		this.collectedPoints = collectedPoints;
		this.tickets = new ArrayList<>();
		
		
		if(collectedPoints < 500) {
			this.customerTypeName = CustomerTypeNames.BRONZE;
			this.customerType = new CustomerType(CustomerTypeNames.BRONZE, 5, 0);
		}else if(collectedPoints <1000){
			this.customerTypeName = CustomerTypeNames.SILVER;
			this.customerType = new CustomerType(CustomerTypeNames.SILVER, 10, 500);
		}else if(collectedPoints <1500){
			this.customerTypeName = CustomerTypeNames.GOLD;
			this.customerType = new CustomerType(CustomerTypeNames.GOLD, 15, 1000);
		}else if(collectedPoints <2000){
			this.customerTypeName = CustomerTypeNames.DIAMOND;
			this.customerType = new CustomerType(CustomerTypeNames.DIAMOND, 20, 1500);
		}
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public int getCollectedPoints() {
		return collectedPoints;
	}

	public void setCollectedPoints(int collectedPoints) {
		this.collectedPoints = collectedPoints;
	}

	public CustomerTypeNames getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(CustomerTypeNames customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	@Override
	public String toString() {
		return "Customer [tickets=" + tickets + ", collectedPoints=" + collectedPoints + ", customerType="
				+ customerType + ", toString()=" + super.toString() + "]";
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	
	
	
}
