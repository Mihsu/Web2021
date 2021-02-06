package dto;

import enums.CustomerTypeNames;
import enums.Genders;
import enums.Roles;

public class UsersDTO {

	private String username;
	
	
	private String firstName;
	
	private String lastName;
	
	private Genders gender;
	
	private String birthDate;
	
	private int collectedPoints;
	
	private Roles role;
	
	private CustomerTypeNames customerType;

	public UsersDTO() {
		super();
	}
	
	public UsersDTO(String username, String firstName, String lastName, Genders gender, String birthDate,
			int collectedPoints, Roles role, CustomerTypeNames customerType) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.collectedPoints = collectedPoints;
		this.role = role;
		this.customerType = customerType;
	}

	public UsersDTO(String username, String firstName, String lastName, Genders gender, String birthDate, Roles role) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;

		this.role = role;

	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Genders getGender() {
		return gender;
	}

	public void setGender(Genders gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public int getCollectedPoints() {
		return collectedPoints;
	}

	public void setCollectedPoints(int collectedPoints) {
		this.collectedPoints = collectedPoints;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public CustomerTypeNames getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerTypeNames customerType) {
		this.customerType = customerType;
	}
	
	
	
	
}
