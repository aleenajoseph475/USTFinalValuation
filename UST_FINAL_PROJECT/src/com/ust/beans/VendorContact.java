package com.ust.beans;

public class VendorContact {
	
	private int vendorId;
	private String vendorName;
	private String vendorAddress;
	private String vendorLocation;
	private String service;
	private int pincode;
	private int isActiveV;
	
	private int contactId;
	private String contactName;
	private String department;
	private String email;
	private String phone;
	private int isActiveC;
	
	public VendorContact() {
		super();
	}

	public VendorContact(int vendorId, String vendorName, String vendorAddress,
			String vendorLocation, String service, int pincode, int isActiveV,
			int contactId, String contactName, String department, String email,
			String phone, int isActiveC) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorAddress = vendorAddress;
		this.vendorLocation = vendorLocation;
		this.service = service;
		this.pincode = pincode;
		this.isActiveV = isActiveV;
		this.contactId = contactId;
		this.contactName = contactName;
		this.department = department;
		this.email = email;
		this.phone = phone;
		this.isActiveC = isActiveC;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public String getVendorLocation() {
		return vendorLocation;
	}

	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getIsActiveV() {
		return isActiveV;
	}

	public void setIsActiveV(int isActiveV) {
		this.isActiveV = isActiveV;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public int getIsActiveC() {
		return isActiveC;
	}

	public void setIsActiveC(int isActiveC) {
		this.isActiveC = isActiveC;
	}

	@Override
	public String toString() {
		return "VendorContact [vendorId=" + vendorId + ", vendorName="
				+ vendorName + ", vendorAddress=" + vendorAddress
				+ ", vendorLocation=" + vendorLocation + ", service=" + service
				+ ", pincode=" + pincode + ", isActiveV=" + isActiveV
				+ ", contactId=" + contactId + ", contactName=" + contactName
				+ ", department=" + department + ", email=" + email
				+ ", phone=" + phone + ", isActiveC=" + isActiveC + "]";
	}
	
	

}
