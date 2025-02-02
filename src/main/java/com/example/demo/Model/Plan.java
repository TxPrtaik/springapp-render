package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
	private String name;
private String days;
private String price;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDays() {
	return days;
}
public void setDays(String days) {
	this.days = days;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
@Override
public String toString() {
	return "Plan [id=" + id + ", name=" + name + ", days=" + days + ", price=" + price + "]";
}

}
