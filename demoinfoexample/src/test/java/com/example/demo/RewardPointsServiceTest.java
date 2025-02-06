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
import com.example.demo.pojo.Customer;
import com.example.demo.pojo.CustomerTransaction;
import com.example.demo.pojo.RewardPoints;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RewardPointsRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.RewardPointsService;
public class RewardPointsServiceTest {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private RewardPointsRepository rewardPointsRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private RewardPointsService rewardPointsService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCalculateRewardPoints() {
        Long customerId = 1L;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 31);
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John Doe");
        CustomerTransaction transaction1 = new CustomerTransaction();
        transaction1.setCustomerId(customerId);
        transaction1.setAmount(120.0);
        transaction1.setDate(LocalDate.of(2023, 1, 10));
        CustomerTransaction transaction2 = new CustomerTransaction();
        transaction2.setCustomerId(customerId);
        transaction2.setAmount(80.0);
        transaction2.setDate(LocalDate.of(2023, 1, 20));
        List<CustomerTransaction> transactions = Arrays.asList(transaction1, transaction2);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate)).thenReturn(transactions);
        rewardPointsService.calculateRewardPoints(customerId, startDate, endDate);
        verify(rewardPointsRepository, times(1)).save(any(RewardPoints.class));
    }
    @Test
    public void testGetRewardPoints() {
        Long customerId = 1L;
        RewardPoints points1 = new RewardPoints();
        points1.setCustomerId(customerId);
        points1.setPoints(100);
        RewardPoints points2 = new RewardPoints();
        points2.setCustomerId(customerId);
        points2.setPoints(200);
        List<RewardPoints> pointsList = Arrays.asList(points1, points2);
        when(rewardPointsRepository.findByCustomerId(customerId)).thenReturn(pointsList);
        List<RewardPoints> foundPoints = rewardPointsService.getRewardPoints(customerId);
        assertNotNull(foundPoints);
        assertEquals(2, foundPoints.size());
        assertEquals(100, foundPoints.get(0).getPoints());
        assertEquals(200, foundPoints.get(1).getPoints());
    }
}
