package com.example.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.controller.RewardPointsResponse;
import com.example.demo.controller.TransactionDetailsResponse;
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
        double amount = 100.0;
        LocalDate date = LocalDate.of(2025, 2, 6);
        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setCustomerId(customerId);
        transaction.setAmount(amount);
        transaction.setDate(date);
        when(transactionRepository.save(any(CustomerTransaction.class))).thenReturn(transaction);
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(Arrays.asList(transaction));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer(customerId, "Jay")));
        RewardPointsResponse response = transactionService.addTransaction(customerId, amount, date);
        assertNotNull(response);
        assertEquals(customerId, response.getCustomerId());
        assertEquals("Jay", response.getCustomerName());
        assertEquals(100, response.getMonthlyPoints().get("FEBRUARY"));
        assertEquals(100, response.getMonthlyPoints().get("Total"));
    }
    @Test
    public void testCalculateMonthlyRewardPoints() {
        Long customerId = 1L;
        CustomerTransaction transaction1 = new CustomerTransaction();
        transaction1.setCustomerId(customerId);
        transaction1.setAmount(100.0);
        transaction1.setDate(LocalDate.of(2025, 2, 6));
        CustomerTransaction transaction2 = new CustomerTransaction();
        transaction2.setCustomerId(customerId);
        transaction2.setAmount(50.0);
        transaction2.setDate(LocalDate.of(2025, 2, 7));
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(Arrays.asList(transaction1, transaction2));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer(customerId, "Jay")));
        RewardPointsResponse response = transactionService.calculateMonthlyRewardPoints(customerId);
        assertNotNull(response);
        assertEquals(customerId, response.getCustomerId());
        assertEquals("Jay", response.getCustomerName());
        assertEquals(150, response.getMonthlyPoints().get("FEBRUARY"));
        assertEquals(150, response.getMonthlyPoints().get("Total"));
    }
    @Test
    public void testGetAllCustomersWithPoints() {
        Customer customer1 = new Customer(1L, "Jay");
        Customer customer2 = new Customer(2L, "Sagar");
        CustomerTransaction transaction1 = new CustomerTransaction();
        transaction1.setCustomerId(1L);
        transaction1.setAmount(100.0);
        transaction1.setDate(LocalDate.of(2025, 2, 6));
        CustomerTransaction transaction2 = new CustomerTransaction();
        transaction2.setCustomerId(2L);
        transaction2.setAmount(50.0);
        transaction2.setDate(LocalDate.of(2025, 2, 7));
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
        when(transactionRepository.findByCustomerId(1L)).thenReturn(Arrays.asList(transaction1));
        when(transactionRepository.findByCustomerId(2L)).thenReturn(Arrays.asList(transaction2));
        List<TransactionDetailsResponse> responses = transactionService.getAllCustomersWithPoints();
        assertNotNull(responses);
        assertEquals(2, responses.size());
        TransactionDetailsResponse response1 = responses.get(0);
        assertEquals(1L, response1.getCustomerId());
        assertEquals("Jay", response1.getCustomerName());
        assertEquals(100, response1.getMonthlyPoints().get("FEBRUARY"));
        assertEquals(100, response1.getMonthlyPoints().get("Total"));
        TransactionDetailsResponse response2 = responses.get(1);
        assertEquals(2L, response2.getCustomerId());
        assertEquals("Sagar", response2.getCustomerName());
        assertEquals(50, response2.getMonthlyPoints().get("FEBRUARY"));
        assertEquals(50, response2.getMonthlyPoints().get("Total"));
    }
}

