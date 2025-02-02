package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@ManyToOne
@JoinColumn(name="member_id")
private Member member;
private double amount;
private String date;
private String plan;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Member getMember() {
	return member;
}
public void setMember(Member member) {
	this.member = member;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getPlan() {
	return plan;
}
public void setPlan(String plan) {
	this.plan = plan;
}
@Override
public String toString() {
	return "Transaction [id=" + id + ", member=" + member + ", amount=" + amount + ", date=" + date + ", plan=" + plan
			+ "]";
}

}
