package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.RewardService;

@SpringBootTest
public class RewardServiceTest {
	
	 @Autowired
	 private RewardService rewardService;

	    @Test
	    public void testCalculatePoints() {
	        assertEquals(90, rewardService.calculatePoints(120.0));
	        assertEquals(200, rewardService.calculatePoints(175.0));
	    }

}
