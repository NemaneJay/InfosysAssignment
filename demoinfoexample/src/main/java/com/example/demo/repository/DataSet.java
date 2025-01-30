package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.example.demo.pojo.Transaction;

public class DataSet {
	public static List<Transaction> getTransactions() {
        return Arrays.asList(
            new Transaction("C001", 120, LocalDate.of(2025, 1, 15)),
            new Transaction("C002", 75, LocalDate.of(2025, 1, 20)),
            new Transaction("C001", 150, LocalDate.of(2025, 2, 5)),
            new Transaction("C003", 90, LocalDate.of(2025, 2, 10)),
            new Transaction("C002", 200, LocalDate.of(2025, 3, 8))
        );
    }
}
