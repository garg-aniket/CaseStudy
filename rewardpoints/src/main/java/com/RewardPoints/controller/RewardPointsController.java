package com.RewardPoints.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RewardPoints.model.RewardPoints;
import com.RewardPoints.services.RewardPointsServices;
@RestController
public class RewardPointsController {

	@Autowired
	private RewardPointsServices rewardPointsServices;
	
//	get all user
	@GetMapping("/getRewardPointsById/{id}")
	public RewardPoints getRewardPointsById(@PathVariable Integer id){
		
		return this.rewardPointsServices.getRewardPointsById(id);
	}
	
	@PostMapping("/addRewardPointsById/{id}/{points}")
	public RewardPoints addUser(@PathVariable Integer id,@PathVariable Integer points ) {
		return this.rewardPointsServices.updateRewardPointsById(id, points);	
	}

	
}
