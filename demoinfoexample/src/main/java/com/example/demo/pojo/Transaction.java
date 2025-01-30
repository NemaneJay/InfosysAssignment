package com.example.demo.pojo;

import java.time.LocalDate;

public class Transaction {
	private String customerId;
    private double amount;
    private LocalDate date;
	public Transaction(String string, int i, LocalDate of) {
		// TODO Auto-generated constructor stub
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
