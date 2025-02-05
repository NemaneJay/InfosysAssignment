package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Customer;
import com.example.demo.pojo.CustomerTransaction;
import com.example.demo.pojo.RewardPoints;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RewardPointsRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class RewardPointsService {
	 @Autowired
	    private TransactionRepository transactionRepository;
	    @Autowired
	    private RewardPointsRepository rewardPointsRepository;
	    @Autowired
	    private CustomerRepository customerRepository;

	    public void calculateRewardPoints(Long customerId, LocalDate startDate, LocalDate endDate) {
	        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
	        List<CustomerTransaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
	        int totalPoints = 0;
	        for (CustomerTransaction transaction : transactions) {
	            double amount = transaction.getAmount();
	            if (amount > 100) {
	                totalPoints += (amount - 100) * 2 + 50;
	            } else if (amount > 50) {
	                totalPoints += (amount - 50);
	            }
	        }
	        RewardPoints rewardPoints = new RewardPoints();
	        rewardPoints.setCustomerId(customerId);
	        rewardPoints.setTransmonth(startDate.getMonthValue());
	        rewardPoints.setTransyear(startDate.getYear());
	        rewardPoints.setPoints(totalPoints);
	        rewardPointsRepository.save(rewardPoints);
	    }

	    public List<RewardPoints> getRewardPoints(Long customerId) {
	        return rewardPointsRepository.findByCustomerId(customerId);
	    }
}