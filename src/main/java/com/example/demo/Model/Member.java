package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String name;
private String contact;
private String address;
private String membershipStatus;
private String joinDate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getMembershipStatus() {
	return membershipStatus;
}
public void setMembershipStatus(String membershipStatus) {
	this.membershipStatus = membershipStatus;
}
public String getJoinDate() {
	return joinDate;
}
public void setJoinDate(String joinDate) {
	this.joinDate = joinDate;
}
@Override
public String toString() {
	return "Member [id=" + id + ", name=" + name + ", contact=" + contact + ", address=" + address
			+ ", membershipStatus=" + membershipStatus + ", joinDate=" + joinDate + "]";
}

}
