package com.example.demo.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RewardPoints {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Integer transmonth;
    private Integer transyear;
    private Integer points;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getTransmonth() {
		return transmonth;
	}
	public void setTransmonth(Integer transmonth) {
		this.transmonth = transmonth;
	}
	public Integer getTransyear() {
		return transyear;
	}
	public void setTransyear(Integer transyear) {
		this.transyear = transyear;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
}
