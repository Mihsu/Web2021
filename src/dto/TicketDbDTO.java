package dto;


import enums.TicketsStatus;
import enums.TicketTypes;

public class TicketDbDTO {

	private int id;
	
	private String manifestationName;
	
	private String dateAndTime;
	
	private double price;
	
	private String buyerUsername;
	
	private TicketsStatus status;
	
	private TicketTypes ticketType;

	private boolean commentable;
	
	public TicketDbDTO() {
		super();
	}

	public TicketDbDTO(int id, String manifestationName, String dateAndTime, double price, String buyer,
			TicketsStatus status, TicketTypes ticketType) {
		super();
		this.id = id;
		this.manifestationName = manifestationName;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.buyerUsername = buyer;
		this.status = status;
		this.ticketType = ticketType;
		this.commentable = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManifestationName() {
		return manifestationName;
	}

	public void setManifestationName(String manifestation) {
		this.manifestationName = manifestation;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBuyerUsername() {
		return buyerUsername;
	}

	public void setBuyerUsername(String buyer) {
		this.buyerUsername = buyer;
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

	public boolean isCommentable() {
		return commentable;
	}

	public void setCommentable(boolean commentable) {
		this.commentable = commentable;
	}
	
}
