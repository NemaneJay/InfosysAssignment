package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	@Autowired
    private TransactionService transactionService;

	@PostMapping("/add")
    public RewardPointsResponse addTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.addTransaction(transactionRequest.getCustomerId(), transactionRequest.getAmount(), transactionRequest.getDate());
    }
    
    @DeleteMapping("/delete/{customerId}")
    public String deleteTransaction(@PathVariable Long customerId) {
        transactionService.deleteTransaction(customerId);
		return "Deleted";
    }
    
    @GetMapping("/rewards/monthly/{customerId}")
    public RewardPointsResponse getMonthlyRewardPoints(@PathVariable Long customerId) {
        return transactionService.calculateMonthlyRewardPoints(customerId);
    }
    
    @GetMapping("/customers/points")
    public List<TransactionDetailsResponse> getAllCustomersWithPoints() {
        return transactionService.getAllCustomersWithPoints();
    }
}
