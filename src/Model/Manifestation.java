package Model;

import java.util.Date;

import enums.ManifestationsStatus;
import enums.ManifestationTypes;

public class Manifestation {
	
	private String name;
	
	private ManifestationTypes type;
	
	private int capacity;
	
	private String dateAndTime;
	
	private double regularPrice;
	
	private ManifestationsStatus status;
	
	private Location location;
	
	private String posterImagePath;

	private double rating;
	
	public Manifestation() {
		super();
	}

	public Manifestation(String name, ManifestationTypes type, int capacity, String dateAndTime, double regularPrice,
			ManifestationsStatus status, Location location, String posterImagePath,double rating) {
		super();
		this.name = name;
		this.type = type;
		this.capacity = capacity;
		this.dateAndTime = dateAndTime;
		this.regularPrice = regularPrice;
		this.status = status;
		this.location = location;
		this.posterImagePath = posterImagePath;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ManifestationTypes getType() {
		return type;
	}

	public void setType(ManifestationTypes type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public ManifestationsStatus getStatus() {
		return status;
	}

	public void setStatus(ManifestationsStatus status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPosterImagePath() {
		return posterImagePath;
	}

	public void setPosterImagePath(String posterImagePath) {
		this.posterImagePath = posterImagePath;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	

}
