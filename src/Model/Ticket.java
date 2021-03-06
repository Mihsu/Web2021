package Model;

import java.util.Date;

import enums.TicketsStatus;
import enums.TicketTypes;

public class Ticket {
	
	private int id;
	
	private Manifestation manifestation;
	
	private Date dateAndTime;
	
	private double price;
	
	private Customer buyer;
	
	private TicketsStatus status;
	
	private TicketTypes ticketType;

	
	public Ticket() {
		super();
	}

	public Ticket(int id, Manifestation manifestation, Date dateAndTime, double price, Customer buyer,
			TicketsStatus status, TicketTypes ticketType) {
		super();
		this.id = id;
		this.manifestation = manifestation;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.buyer = buyer;
		this.status = status;
		this.ticketType = ticketType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Manifestation getManifestation() {
		return manifestation;
	}

	public void setManifestation(Manifestation manifestation) {
		this.manifestation = manifestation;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getBuyer() {
		return buyer;
	}

	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}

	public TicketsStatus getStatus() {
		return status;
	}

	public void setStatus(TicketsStatus status) {
		this.status = status;
	}

	public TicketTypes getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketTypes ticketType) {
		this.ticketType = ticketType;
	}
	
	
}
