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
    public void testCalculateMonthlyRewardPoints() {
        Long customerId = 1L;
        LocalDate date = LocalDate.now();

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setUserName("Jay");

        CustomerTransaction transaction1 = new CustomerTransaction();
        transaction1.setCustomerId(customerId);
        transaction1.setAmount(120.0);
        transaction1.setDate(date);

        CustomerTransaction transaction2 = new CustomerTransaction();
        transaction2.setCustomerId(customerId);
        transaction2.setAmount(80.0);
        transaction2.setDate(date.minusMonths(1));

        List<CustomerTransaction> transactions = Arrays.asList(transaction1, transaction2);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(transactions);

        RewardPointsResponse response = transactionService.calculateMonthlyRewardPoints(customerId);

        assertNotNull(response);
        assertEquals(customerId, response.getCustomerId());
        assertEquals("Jay", response.getCustomerName());
        assertTrue(response.getMonthlyPoints().containsKey(date.getMonth().toString()));
        assertTrue(response.getMonthlyPoints().containsKey(date.minusMonths(1).getMonth().toString()));
        assertTrue(response.getMonthlyPoints().containsKey("Total"));
    }
    @Test
    public void testGetAllCustomersWithPoints() {
        Long customerId = 1L;
        LocalDate date = LocalDate.now();

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setUserName("Jay");

        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setCustomerId(customerId);
        transaction.setAmount(120.0);
        transaction.setDate(date);

        List<Customer> customers = Arrays.asList(customer);
        List<CustomerTransaction> transactions = Arrays.asList(transaction);

        when(customerRepository.findAll()).thenReturn(customers);
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(transactions);

        List<TransactionDetailsResponse> responses = transactionService.getAllCustomersWithPoints();

        assertNotNull(responses);
        assertFalse(responses.isEmpty());
        assertEquals(customerId, responses.get(0).getCustomerId());
        assertEquals("Jay", responses.get(0).getCustomerName());
        assertTrue(responses.get(0).getMonthlyPoints().containsKey(date.getMonth().toString()));
        assertTrue(responses.get(0).getMonthlyPoints().containsKey("Total"));
    }
    
    @Test
    public void testDeleteTransaction() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setUserName("Jay");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        transactionService.deleteTransaction(customerId);
        verify(customerRepository, times(1)).deleteById(customerId);
    }
    @Test
    public void testEditTransaction() {
        Long customerId = 1L;
        double amount = 150.0;
        LocalDate date = LocalDate.now();

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setUserName("Jay");

        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setCustomerId(customerId);
        transaction.setAmount(120.0);
        transaction.setDate(date);

        List<CustomerTransaction> transactions = Arrays.asList(transaction);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(transactions);

        RewardPointsResponse response = transactionService.editTransaction(customerId, amount, date);

        assertNotNull(response);
        assertEquals(customerId, response.getCustomerId());
        assertEquals("Jay", response.getCustomerName());
        assertTrue(response.getMonthlyPoints().containsKey(date.getMonth().toString()));
        assertTrue(response.getMonthlyPoints().containsKey("Total"));
    }
    
}

