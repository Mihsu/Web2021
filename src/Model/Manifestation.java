package Model;

import java.util.Date;

import Enums.ManifestationStatus;
import Enums.ManifestationType;

public class Manifestation {
	
	private String name;
	
	private ManifestationType type;
	
	private int capacity;
	
	private Date dateAndTime;
	
	private double regularPrice;
	
	private ManifestationStatus status;
	
	private Location location;
	
	private String posterImagePath;

	
	public Manifestation() {
		super();
	}

	public Manifestation(String name, ManifestationType type, int capacity, Date dateAndTime, double regularPrice,
			ManifestationStatus status, Location location, String posterImagePath) {
		super();
		this.name = name;
		this.type = type;
		this.capacity = capacity;
		this.dateAndTime = dateAndTime;
		this.regularPrice = regularPrice;
		this.status = status;
		this.location = location;
		this.posterImagePath = posterImagePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ManifestationType getType() {
		return type;
	}

	public void setType(ManifestationType type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public ManifestationStatus getStatus() {
		return status;
	}

	public void setStatus(ManifestationStatus status) {
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
	
	

}
