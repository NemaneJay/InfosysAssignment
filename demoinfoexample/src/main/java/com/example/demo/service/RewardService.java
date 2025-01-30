package com.example.demo.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Transaction;
import com.example.demo.repository.TransactionRepository;

@Service
public class RewardService {

    @Autowired
    private TransactionRepository transactionRepository;

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

    public Map<Long, Integer> calculateMonthlyPoints(Long customerId, YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);

        int totalPoints = transactions.stream()
                                      .mapToInt(t -> calculatePoints(t.getAmount()))
                                      .sum();

        Map<Long, Integer> result = new HashMap<>();
        result.put(customerId, totalPoints);
        return result;
    }
}
