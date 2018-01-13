package com.fusion.parser;

public class FullName {
	private String firstName;
	private String MiddleName;
	private String lastName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FullName [firstName=");
		builder.append(firstName);
		builder.append(", MiddleName=");
		builder.append(MiddleName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append("]");
		return builder.toString();
	}
}
