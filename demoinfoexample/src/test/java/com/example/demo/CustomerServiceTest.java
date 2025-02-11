package com.example.demo;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.pojo.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterCustomer() {
        Customer customer = new Customer();
        customer.setUserName("John");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        assertEquals("John", customer.getUserName());
    }

    @Test
    public void testFindCustomerById() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUserName("John");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(1L);

        assertNotNull(foundCustomer);
        assertEquals("John", foundCustomer.getUserName());
    }
}
