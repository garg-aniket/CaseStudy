package com.restApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.RewardPoints.Response.ApiResponse;
import com.RewardPoints.controller.RewardPointsController;
import com.RewardPoints.model.RewardPoints;
import com.RewardPoints.services.RewardPointsServices;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardPointsControllerTest {

    @InjectMocks
    private RewardPointsController rewardPointsController;

    @Mock
    private RewardPointsServices rewardPointsServices;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRewardPointsById() {
        int userId = 1;

        // Create a dummy RewardPoints object to return from the service
        RewardPoints dummyRewardPoints = new RewardPoints();
        // set the appropriate values for the dummyRewardPoints object

        // Mock the behavior of the rewardPointsServices
        when(rewardPointsServices.getRewardPointsById(userId)).thenReturn(dummyRewardPoints);

        ResponseEntity<ApiResponse> response = rewardPointsController.getRewardPointsById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Id Found Successfully", response.getBody().getMessage());
        assertEquals(true, response.getBody().isSuccess());
    }

    @Test
    public void testAddRewardPointById() {
        int userId = 1;
        int points = 100;

        ResponseEntity<ApiResponse> response = rewardPointsController.addRewardPointById(userId, points);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reward point updated successfully", response.getBody().getMessage());
        assertEquals(true, response.getBody().isSuccess());
    }
}