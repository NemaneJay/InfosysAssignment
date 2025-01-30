package com.example.demo.repository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.Transaction;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        transactionRepository.save(new Transaction(1L, 150.0, LocalDate.of(2025, 1, 15)));
        transactionRepository.save(new Transaction(1L, 90.0, LocalDate.of(2025, 1, 20)));
        transactionRepository.save(new Transaction(2L, 120.0, LocalDate.of(2025, 1, 25)));
        // Add more sample data as needed
    }
}
