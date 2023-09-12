package com.RewardPoints.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="UserDetails")
public class RewardPoints {
	
	@Id
	@Column(name ="userId")
	private int id;
	@Column(name ="reward_points")
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
		// TODO Auto-generated constructor stub
	}
	public RewardPoints(int id, int rewardPoints) {
		super();
		this.id = id;
		this.rewardPoints = rewardPoints;
	}
	
	
}
