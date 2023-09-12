package com.RewardPoints.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RewardPoints.model.RewardPoints;
import com.RewardPoints.repository.RewardPointsRepository;


@Service
public class RewardPointsServiceimpl implements RewardPointsServices {
	
	@Autowired
	private RewardPointsRepository csd;


	


	@Override
	public RewardPoints getRewardPointsById(Integer id) {
		// TODO Auto-generated method stub
		return csd.findById(id).get();
	}


	@Override
	public RewardPoints updateRewardPointsById(Integer id, Integer points) {
		// TODO Auto-generated method stub
		RewardPoints rp=csd.findById(id).get();
		rp.setRewardPoints(points);
		
		return csd.save(rp);
	}

}
