package com.example.demo.service;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.Transaction;

@Service
public class RewardService {
	public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }
 
    public HashMap<String, Integer> calculateMonthlyPoints(List<Transaction> transactions, YearMonth month) {
        HashMap<String, Integer> pointsMap = new HashMap<>();
        for (Transaction t : transactions) {
            if (YearMonth.from(t.getDate()).equals(month)) {
                pointsMap.merge(t.getCustomerId(), calculatePoints(t.getAmount()), Integer::sum);
            }
        }
        return pointsMap;
    }
 
    public HashMap<String, Integer> calculateTotalPoints(List<Transaction> transactions) {
        HashMap<String, Integer> pointsMap = new HashMap<>();
        for (Transaction t : transactions) {
            pointsMap.merge(t.getCustomerId(), calculatePoints(t.getAmount()), Integer::sum);
        }
        return pointsMap;
    }
}
