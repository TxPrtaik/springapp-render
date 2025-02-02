package com.example.demo.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Membership {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@ManyToOne
@JoinColumn(name="plan_id")
private Plan p;
private int pa;
public int getPa() {
	return pa;
}
public void setPa(int pa) {
	this.pa = pa;
}
private String startDate;
private String feeStatus;
private String endDate;
private String fees;
private String paidFees;
private String activeStatus;
@ManyToOne
@JoinColumn(name="member_id")
private Member m;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Plan getP() {
	return p;
}
public void setP(Plan p) {
	this.p = p;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public String getFees() {
	return fees;
}
public void setFees(String fees) {
	this.fees = fees;
}
public String getPaidFees() {
	return paidFees;
}
public void setPaidFees(String paidFees) {
	this.paidFees = paidFees;
}
public Member getM() {
	return m;
}
public void setM(Member m) {
	this.m = m;
}
public String getFeeStatus() {
	return feeStatus;
}
public void setFeeStatus(String feeStatus) {
	this.feeStatus = feeStatus;
}

public String getActiveStatus() {
	return activeStatus;
}
public void setActiveStatus(String activeStatus) {
	this.activeStatus = activeStatus;
}
@Override
public String toString() {
	return "Membership [id=" + id + ", p=" + p + ", pa=" + pa + ", startDate=" + startDate + ", feeStatus=" + feeStatus
			+ ", endDate=" + endDate + ", fees=" + fees + ", paidFees=" + paidFees + ", activeStatus=" + activeStatus
			+ ", m=" + m + "]";
}



}
