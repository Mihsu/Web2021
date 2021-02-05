package dto;

import enums.Genders;
import enums.Roles;

public class UserDTO {

	private String username;
	
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private String birthDate;
	
	private int collectedPoints;

	
	public UserDTO() {
		super();
	}
	
	public UserDTO(String username,  String firstName, String lastName, String gender, String birthDate) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;

	}
	
	public UserDTO(String username,  String firstName, String lastName, String gender, String birthDate, int collectedPoints) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.collectedPoints = collectedPoints;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password="  + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", birthDate=" + birthDate + ", role="  + ", active=" 
				+ "]";
	}

	public int getCollectedPoints() {
		return collectedPoints;
	}

	public void setCollectedPoints(int collectedPoints) {
		this.collectedPoints = collectedPoints;
	}
}
