package com.example.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.pojo.Customer;
import com.example.demo.pojo.CustomerTransaction;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTransaction() {
        Long customerId = 1L;
        Double amount = 100.0;
        LocalDate date = LocalDate.now();

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John");

        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setCustomerId(customerId);
        transaction.setAmount(amount);
        transaction.setDate(date);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(transactionRepository.save(any(CustomerTransaction.class))).thenReturn(transaction);

        CustomerTransaction savedTransaction = transactionService.addTransaction(customerId, amount, date);

        assertNotNull(savedTransaction);
        assertEquals(customerId, savedTransaction.getCustomerId());
        assertEquals(amount, savedTransaction.getAmount());
        assertEquals(date, savedTransaction.getDate());
    }

    @Test
    public void testAddTransactionCustomerNotFound() {
        Long customerId = 1L;
        Double amount = 100.0;
        LocalDate date = LocalDate.now();

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transactionService.addTransaction(customerId, amount, date);
        });

        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    public void testDeleteTransaction() {
        Long transactionId = 1L;

        doNothing().when(transactionRepository).deleteById(transactionId);

        transactionService.deleteTransaction(transactionId);

        verify(transactionRepository, times(1)).deleteById(transactionId);
    }
}
