package com.example.demo.controller;

import java.util.Map;

public class RewardPointsResponse {
	private Long customerId;
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
	public Map<String, Integer> getMonthlyPoints() {
		return monthlyPoints;
	}
	public void setMonthlyPoints(Map<String, Integer> monthlyPoints) {
		this.monthlyPoints = monthlyPoints;
	}
	private String customerName;
    private Map<String, Integer> monthlyPoints;
 

}
