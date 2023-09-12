package com.RewardPoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RewardPoints.model.RewardPoints;


@Repository
public interface RewardPointsRepository extends JpaRepository<RewardPoints, Integer> {

	
	

}
