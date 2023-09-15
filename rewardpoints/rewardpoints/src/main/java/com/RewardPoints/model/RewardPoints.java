package com.RewardPoints.model;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="UserDetails")
public class RewardPoints {
	
	//UserId
	
	@Id
	@Column(name ="userId")
	private int id;
	@Column(name ="reward_points")
	@Range(min = 0l, message = "Please select positive numbers Only")
	
	//User RewardPoints
	private int rewardPoints;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Integer points) {
		this.rewardPoints = points;
	}
	public RewardPoints() {
		super();
	}
	public RewardPoints(int id, int rewardPoints) {
		super();
		this.id = id;
		this.rewardPoints = rewardPoints;
	}
	
	
}
