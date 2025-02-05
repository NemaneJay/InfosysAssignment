package com.example.demo.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.CustomerTransaction;
import com.example.demo.pojo.RewardPoints;
import com.example.demo.service.RewardPointsService;

@RestController
@RequestMapping("/api/rewards")
public class RewardPointsController {
    @Autowired
    private RewardPointsService rewardPointsService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<RewardPoints>> getRewardPoints(@PathVariable Long customerId) {
        List<RewardPoints> rewardPoints = rewardPointsService.getRewardPoints(customerId);
        return ResponseEntity.ok(rewardPoints);
    }

    @PostMapping("/calculate")
    public ResponseEntity<Void> calculateRewardPoints(@RequestBody RewardPointsRequest request) {
        rewardPointsService.calculateRewardPoints(request.getCustomerId(), request.getStartDate(), request.getEndDate());
        return ResponseEntity.ok().build();
    }
}