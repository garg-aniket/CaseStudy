package com.RewardPoints.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RewardPoints.model.RewardPoints;
import com.RewardPoints.repository.RewardPointsRepository;
import com.RewardPoints.Exception.ResourceNotFoundException;


@Service
public class RewardPointsServiceimpl implements RewardPointsServices {
	
	@Autowired
	private RewardPointsRepository csd;

	// This method retrieves reward points by user ID.
	@Override
	public RewardPoints getRewardPointsById(Integer id) {
		
		// Use the RewardPointsRepository to find a reward points entry by its ID.
		// If not found, throw a ResourceNotFoundException.
		
		return csd.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id" ,id));
	}

	// This method updates reward points by user ID.
	@Override
	public RewardPoints updateRewardPointsById(Integer id, Integer points) {
		
		// Retrieve the existing reward points entry by its ID.
		RewardPoints rp=csd.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id" ,id));
		
		// Update the reward points value with the provided points.
		rp.setRewardPoints(points);
		
		// Save the updated entry using the RewardPointsRepository and return it.
		return csd.save(rp);
	}

}
