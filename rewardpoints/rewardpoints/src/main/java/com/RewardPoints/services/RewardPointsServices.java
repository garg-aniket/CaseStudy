package com.RewardPoints.services;


import com.RewardPoints.model.RewardPoints;



public interface RewardPointsServices  {
	
	public RewardPoints getRewardPointsById(Integer id);

	public RewardPoints updateRewardPointsById(Integer id,Integer points);

	
	
	
}
