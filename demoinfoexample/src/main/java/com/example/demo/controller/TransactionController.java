package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.CustomerTransaction;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	@Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    public CustomerTransaction addTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.addTransaction(transactionRequest.getCustomerId(), transactionRequest.getAmount(), transactionRequest.getDate());
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
		return "Deleted";
    }
    
}
