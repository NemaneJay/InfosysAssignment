package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import com.example.demo.pojo.CustomerTransaction;

public class TransactionDetailsResponse {
	
	private Long customerId;
    private String customerName;
    private List<CustomerTransaction> transactions;
    private Map<String, Integer> monthlyPoints;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<CustomerTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<CustomerTransaction> transactions) {
		this.transactions = transactions;
	}
	public Map<String, Integer> getMonthlyPoints() {
		return monthlyPoints;
	}
	public void setMonthlyPoints(Map<String, Integer> monthlyPoints) {
		this.monthlyPoints = monthlyPoints;
	}

}
