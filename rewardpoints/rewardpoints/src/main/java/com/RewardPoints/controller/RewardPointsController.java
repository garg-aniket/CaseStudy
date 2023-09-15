package com.RewardPoints.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.RewardPoints.Response.ApiResponse;

import com.RewardPoints.services.RewardPointsServices;

import jakarta.validation.Valid;
@RestController
public class RewardPointsController {

	@Autowired
	private RewardPointsServices rewardPointsServices;
	
	// This method handles a GET request to retrieve reward points by user ID.
	@GetMapping("/getRewardPointsById/{id}")
	public ResponseEntity<ApiResponse> getRewardPointsById(@Valid @PathVariable Integer id){
		// Call the RewardPointsServices to retrieve reward points by user ID.
		this.rewardPointsServices.getRewardPointsById(id);
		// Return a ResponseEntity with a success message and HTTP status OK.
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Id Found Successfully", true),HttpStatus.OK);	}
	
	// This method handles a POST request to add reward points by user ID.
	@PostMapping("/addRewardPointsById/{id}/{points}")
	public ResponseEntity<ApiResponse> addRewardPointById(@PathVariable Integer id,@PathVariable Integer points ) {
		// Return a ResponseEntity with a success message and HTTP status OK.
		return new ResponseEntity<ApiResponse>(new ApiResponse("Reward point updated successfully", true),HttpStatus.OK);	
	}

	
}
