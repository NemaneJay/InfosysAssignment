package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Customer;
import com.example.demo.pojo.RewardPoints;

@Repository
public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
    List<RewardPoints> findByCustomerId(Long customerId);
}
