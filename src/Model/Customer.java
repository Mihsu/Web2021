package Model;

import java.util.List;

public class Customer extends User{

	private List<Ticket> tickets;
	
	private int collectedPoints;
	
	private CustomerType customerType;

	
	public Customer() {
		super();
	}

	public Customer(List<Ticket> tickets, int collectedPoints, CustomerType customerType) {
		super();
		this.tickets = tickets;
		this.collectedPoints = collectedPoints;
		this.customerType = customerType;
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

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	
	
}
