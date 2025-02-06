package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	    public CustomerTransaction addTransaction(Long customerId, Double amount, LocalDate date) {
	        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
	        CustomerTransaction transaction = new CustomerTransaction();
	        transaction.setCustomerId(customerId);
	        transaction.setAmount(amount);
	        transaction.setDate(date);
	        return transactionRepository.save(transaction);
	    }
	    
	    public void deleteTransaction(Long id) {
	        transactionRepository.deleteById(id);
	    }
	    
}
