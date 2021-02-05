package dto;

import Model.Location;
import enums.ManifestationTypes;
import enums.ManifestationsStatus;

public class ManifestationDTO {

private String name;
	
	private ManifestationTypes type;
	
	private int capacity;
	
	private String dateAndTime;
	
	private double regularPrice;
	
	private Location location;

	
	public ManifestationDTO() {
		super();
	}

	public ManifestationDTO(String name, ManifestationTypes type, int capacity, String dateAndTime, double regularPrice, Location location) {
		super();
		this.name = name;
		this.type = type;
		this.capacity = capacity;
		this.dateAndTime = dateAndTime;
		this.regularPrice = regularPrice;

		this.location = location;

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


	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	
	
}
