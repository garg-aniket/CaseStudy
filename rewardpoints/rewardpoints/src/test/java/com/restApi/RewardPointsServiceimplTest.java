package com.restApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.RewardPoints.model.RewardPoints;
import com.RewardPoints.repository.RewardPointsRepository;
import com.RewardPoints.services.RewardPointsServiceimpl;
import com.RewardPoints.Exception.ResourceNotFoundException;

public class RewardPointsServiceimplTest {

    @InjectMocks
    private RewardPointsServiceimpl rewardPointsService;

    @Mock
    private RewardPointsRepository rewardPointsRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRewardPointsById() {
        int userId = 1;

        // Create a dummy RewardPoints object to return from the repository
        RewardPoints dummyRewardPoints = new RewardPoints();
        dummyRewardPoints.setId(userId);
        //Set the appropriate values for the dummyRewardPoints object

        // Mock the behavior of the rewardPointsRepository
        when(rewardPointsRepository.findById(userId)).thenReturn(java.util.Optional.of(dummyRewardPoints));

        RewardPoints result = rewardPointsService.getRewardPointsById(userId);

        assertEquals(userId, result.getId());
        // Add additional assertions based on the expected behavior of the service method
    }

    @Test
    public void testGetRewardPointsByIdNotFound() {
        int userId = 1;

        // Mock the behavior of the rewardPointsRepository to simulate not finding the user
        when(rewardPointsRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rewardPointsService.getRewardPointsById(userId));
    }

    @Test
    public void testUpdateRewardPointsById() {
        int userId = 1;
        int points = 100;

        // Create a dummy RewardPoints object to return from the repository
        RewardPoints dummyRewardPoints = new RewardPoints();
        dummyRewardPoints.setId(userId);
        // Set the appropriate values for the dummyRewardPoints object

        // Mock the behavior of the rewardPointsRepository
        when(rewardPointsRepository.findById(userId)).thenReturn(java.util.Optional.of(dummyRewardPoints));
        when(rewardPointsRepository.save(dummyRewardPoints)).thenReturn(dummyRewardPoints);

        RewardPoints result = rewardPointsService.updateRewardPointsById(userId, points);

        assertEquals(points, result.getRewardPoints());
        
    }

    @Test
    public void testUpdateRewardPointsByIdNotFound() {
    	
    	int userId = 1;
        int points = 100;

        // Mock the behavior of the rewardPointsRepository to simulate not finding the user
        when(rewardPointsRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rewardPointsService.updateRewardPointsById(userId, points));

        // Verify that findById was called with the correct userId
        verify(rewardPointsRepository).findById(userId);
        
        // Verify that save was never called
        verify(rewardPointsRepository, never()).save(any());
    }
}