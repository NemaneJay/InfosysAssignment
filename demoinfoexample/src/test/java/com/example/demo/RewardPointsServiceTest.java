package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.pojo.Customer;
import com.example.demo.pojo.CustomerTransaction;
import com.example.demo.pojo.RewardPoints;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RewardPointsRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.RewardPointsService;

@SpringBootTest
public class RewardPointsServiceTest {
	
	@Mock
    private TransactionRepository transactionRepository;

    @Mock
    private RewardPointsRepository rewardPointsRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private RewardPointsService rewardPointsService;

    public RewardPointsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    

    
}
