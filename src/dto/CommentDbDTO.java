package dto;


public class CommentDbDTO {

	private String customerUsername;
	
	private String manifestationName;
	
	private String content;
	
	private int rating;

	
	public CommentDbDTO() {
		super();
	}

	public CommentDbDTO(String customerUsername, String manifestationName, String content, int rating) {
		super();
		this.customerUsername = customerUsername;
		this.manifestationName = manifestationName;
		this.content = content;
		this.rating = rating;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomer(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getManifestationName() {
		return manifestationName;
	}

	public void setManifestation(String manifestationName) {
		this.manifestationName = manifestationName;
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

	@Override
	public String toString() {
		return "CommentDbDTO [customerUsername=" + customerUsername + ", manifestationName=" + manifestationName
				+ ", content=" + content + ", rating=" + rating + "]";
	}
	
}
