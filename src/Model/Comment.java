package Model;

public class Comment {

	private Customer customer;
	
	private Manifestation manifestation;
	
	private String content;
	
	private int rating;

	
	public Comment() {
		super();
	}

	public Comment(Customer customer, Manifestation manifestation, String content, int rating) {
		super();
		this.customer = customer;
		this.manifestation = manifestation;
		this.content = content;
		this.rating = rating;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Manifestation getManifestation() {
		return manifestation;
	}

	public void setManifestation(Manifestation manifestation) {
		this.manifestation = manifestation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
