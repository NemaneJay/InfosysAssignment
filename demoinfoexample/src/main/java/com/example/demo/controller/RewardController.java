package com.example.demo.controller;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Transaction;
import com.example.demo.service.RewardService;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/{customerId}/{year}/{month}")
    public ResponseEntity<Map<Long, Integer>> getMonthlyPoints(@PathVariable Long customerId,
                                                               @PathVariable int year,
                                                               @PathVariable int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        Map<Long, Integer> points = rewardService.calculateMonthlyPoints(customerId, yearMonth);
        return ResponseEntity.ok(points);
    }
}