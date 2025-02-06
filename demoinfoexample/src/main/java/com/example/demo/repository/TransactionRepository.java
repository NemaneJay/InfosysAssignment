package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.CustomerTransaction;
@Repository
public interface TransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    List<CustomerTransaction> findByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);
    List<CustomerTransaction> findByCustomerId(Long customerId);
}

