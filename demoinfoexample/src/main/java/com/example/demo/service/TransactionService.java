package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.RewardPointsResponse;
import com.example.demo.controller.TransactionDetailsResponse;
import com.example.demo.pojo.Customer;
import com.example.demo.pojo.CustomerTransaction;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {
	 @Autowired
	    private TransactionRepository transactionRepository;
	    @Autowired
	    private CustomerRepository customerRepository;

	    public RewardPointsResponse addTransaction(Long customerId, double amount, LocalDate date) {
	        CustomerTransaction transaction = new CustomerTransaction();
	        transaction.setCustomerId(customerId);
	        transaction.setAmount(amount);
	        transaction.setDate(date);
	        transactionRepository.save(transaction);

	        return calculateMonthlyRewardPoints(customerId);
	    }

	    public RewardPointsResponse calculateMonthlyRewardPoints(Long customerId) {
	        List<CustomerTransaction> transactions = transactionRepository.findByCustomerId(customerId);
	        Map<String, Integer> monthlyPoints = new HashMap<>();
	        int totalPoints = 0;

	        for (CustomerTransaction transaction : transactions) {
	            String month = transaction.getDate().getMonth().toString();
	            int points = calculatePoints(transaction);
	            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
	            totalPoints += points;
	        }

	        monthlyPoints.put("Total", totalPoints);

	        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
	        RewardPointsResponse response = new RewardPointsResponse();
	        response.setCustomerId(customerId);
	        response.setCustomerName(customer.getName());
	        response.setMonthlyPoints(monthlyPoints);

	        return response;
	    }
	    
	    public List<TransactionDetailsResponse> getAllCustomersWithPoints() {
	        List<Customer> customers = customerRepository.findAll();
	        List<TransactionDetailsResponse> responses = new ArrayList<>();

	        for (Customer customer : customers) {
	            List<CustomerTransaction> transactions = transactionRepository.findByCustomerId(customer.getId());
	            Map<String, Integer> monthlyPoints = new HashMap<>();
	            int totalPoints = 0;

	            for (CustomerTransaction transaction : transactions) {
	                String month = transaction.getDate().getMonth().toString();
	                int points = calculatePoints(transaction);
	                monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
	                totalPoints += points;
	            }

	            monthlyPoints.put("Total", totalPoints);

	            TransactionDetailsResponse response = new TransactionDetailsResponse();
	            response.setCustomerId(customer.getId());
	            response.setCustomerName(customer.getName());
	            response.setTransactions(transactions);
	            response.setMonthlyPoints(monthlyPoints);

	            responses.add(response);
	        }

	        return responses;
	    }

	    private int calculatePoints(CustomerTransaction transaction) {
	        // Example logic: 1 point for every $1 spent
	        return (int) Math.floor(transaction.getAmount());
	    }
	
	
	    public void deleteTransaction(Long customerId) {
	    	Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
	        customerRepository.deleteById(customer.getId());
	    }
}
