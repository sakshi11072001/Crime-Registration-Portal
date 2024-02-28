package com.Signup.Signup.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Complaint_table")
public class ComplaintModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	private String userLogin;
    private String phone;
    private String city;
    private String pincode;
    private String address;
    private String incident;
    private String place;
    private String complaintFor;
    private String date;
    private String victimName;
    private String additionalInfo;
    private String status; // Add the status field with default value

    public ComplaintModel() {
        this.status = "not received"; // Set default value for status
    }
    
	public String getUserLogin() {
		return userLogin;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getComplaintFor() {
		return complaintFor;
	}
	public void setComplaintFor(String complaintFor) {
		this.complaintFor = complaintFor;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVictimName() {
		return victimName;
	}
	public void setVictimName(String victimName) {
		this.victimName = victimName;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(additionalInfo, address, city, complaintFor, date, incident, phone, pincode, place,
				userLogin, victimName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplaintModel other = (ComplaintModel) obj;
		return Objects.equals(additionalInfo, other.additionalInfo) && Objects.equals(address, other.address)
				&& Objects.equals(city, other.city) && Objects.equals(complaintFor, other.complaintFor)
				&& Objects.equals(date, other.date) && Objects.equals(incident, other.incident)
				&& Objects.equals(phone, other.phone) && Objects.equals(pincode, other.pincode)
				&& Objects.equals(place, other.place) && Objects.equals(userLogin, other.userLogin)
				&& Objects.equals(victimName, other.victimName);
	}
	@Override
	public String toString() {
		return "ComplaintModel [userLogin=" + userLogin + ", phone=" + phone + ", city=" + city + ", pincode=" + pincode
				+ ", address=" + address + ", incident=" + incident + ", place=" + place + ", complaintFor="
				+ complaintFor + ", date=" + date + ", victimName=" + victimName + ", additionalInfo=" + additionalInfo
				+ "]";
	}
	

}


/*
 Database query
  CREATE TABLE signup.Complaint_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userLogin VARCHAR(255),
    phone VARCHAR(20),
    city VARCHAR(100),
    pincode VARCHAR(10),
    address VARCHAR(255),
    incident TEXT,
    place VARCHAR(100),
    complaintFor VARCHAR(50),
    date DATE,
    victimName VARCHAR(100),
    additionalInfo TEXT
);

 */
