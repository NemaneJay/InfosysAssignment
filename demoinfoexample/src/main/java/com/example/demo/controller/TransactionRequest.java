package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionRequest {
	 private Long customerId;
	    private Double amount;
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	    private LocalDate date;
		public Long getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
}
