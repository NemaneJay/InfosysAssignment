package com.example.demo.controller;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Transaction;
import com.example.demo.service.RewardService;

@RestController
@RequestMapping("/rewards")
public class RewardController {
	  @Autowired
	    private RewardService rewardService;
	 
	    @GetMapping("/monthly/{year}/{month}")
	    public HashMap<String, Integer> getMonthlyPoints(@PathVariable int year, @PathVariable int month, @RequestBody List<Transaction> transactions) {
	        YearMonth yearMonth = YearMonth.of(year, month);
	        return rewardService.calculateMonthlyPoints(transactions, yearMonth);
	    }
	 
	    @GetMapping("/total")
	    public HashMap<String, Integer> getTotalPoints(@RequestBody List<Transaction> transactions) {
	        return rewardService.calculateTotalPoints(transactions);
	    }
}
