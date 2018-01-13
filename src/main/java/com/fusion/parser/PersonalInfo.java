package com.fusion.parser;

import java.util.ArrayList;
import java.util.List;

public class PersonalInfo {
	private FullName fullName;
	private String email;
	private String phone;
	private String jobTitle;
	private List<String> socialLinks = new ArrayList<String>(); 
	private String addressLine1;
	private String addressLine2;
	private String zipCode;
	public FullName getFullName() {
		return fullName;
	}
	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public List<String> getSocialLinks() {
		return socialLinks;
	}
	public void addSocialLink(String socialLink) {
		socialLinks.add(socialLink);
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonalInfo [fullName=");
		builder.append(fullName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", jobTitle=");
		builder.append(jobTitle);
		builder.append(", socialLinks=");
		builder.append(socialLinks);
		builder.append(", addressLine1=");
		builder.append(addressLine1);
		builder.append(", addressLine2=");
		builder.append(addressLine2);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append("]");
		return builder.toString();
	}
}
