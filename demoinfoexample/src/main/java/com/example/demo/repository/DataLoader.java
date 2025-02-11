package com.example.demo.repository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.Customer;
import com.example.demo.pojo.CustomerTransaction;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create sample customers
        Customer customer1 = new Customer();
        customer1.setUserName("Jay");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setUserName("Sagar");
        customerRepository.save(customer2);

        // Create sample transactions for customer1
        transactionRepository.save(new CustomerTransaction(null, customer1.getId(), 150.0, LocalDate.of(2025, 1, 15)));
        transactionRepository.save(new CustomerTransaction(null, customer1.getId(), 90.0, LocalDate.of(2025, 2, 20)));
        transactionRepository.save(new CustomerTransaction(null, customer1.getId(), 200.0, LocalDate.of(2025, 3, 15)));
        transactionRepository.save(new CustomerTransaction(null, customer1.getId(), 50.0, LocalDate.of(2025, 4, 5)));

        // Create sample transactions for customer2
        transactionRepository.save(new CustomerTransaction(null, customer2.getId(), 120.0, LocalDate.of(2025, 1, 25)));
        transactionRepository.save(new CustomerTransaction(null, customer2.getId(), 60.0, LocalDate.of(2025, 2, 10)));
        transactionRepository.save(new CustomerTransaction(null, customer2.getId(), 80.0, LocalDate.of(2025, 3, 20)));
        transactionRepository.save(new CustomerTransaction(null, customer2.getId(), 110.0, LocalDate.of(2025, 4, 10)));
    }
}