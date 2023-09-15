package com.casestudy.LoginAndRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.casestudy.LoginAndRegistration.Exception.ResourceNotFoundException;
import com.casestudy.LoginAndRegistration.Repo.UserRepo;
import com.casestudy.LoginAndRegistration.Service.UserServiceImpl;
import com.casestudy.LoginAndRegistration.model.User;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepo userRepo;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

 // Test user creation and validate the result
    @Test
    public void testCreateUser() {
    	
        User user = new User();
        user.setUserId(1);
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("testpassword");
        user.setMobile("1234567890");

        when(userRepo.save(user)).thenReturn(user);

        User createdUser = userServiceImpl.createUser(user);

        assertEquals(user.getUserId(), createdUser.getUserId());
        assertEquals(user.getUserName(), createdUser.getUserName());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
        assertEquals(user.getMobile(), createdUser.getMobile());
    }
    
    //Test updating an existing user and validate the result
    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setUserId(1);
        existingUser.setUserName("existinguser");
        existingUser.setEmail("existinguser@example.com");
        existingUser.setPassword("existingpassword");
        existingUser.setMobile("9876543210");

        User updatedUser = new User();
        updatedUser.setUserId(1);
        updatedUser.setUserName("updateduser");
        updatedUser.setEmail("updateduser@example.com");
        updatedUser.setPassword("updatedpassword");
        updatedUser.setMobile("73979589884");

        when(userRepo.findById(existingUser.getUserId())).thenReturn(java.util.Optional.of(existingUser));
        when(userRepo.save(existingUser)).thenReturn(updatedUser);

        User result = userServiceImpl.update(updatedUser, existingUser.getUserId());

        assertEquals(updatedUser.getUserId(), result.getUserId());
        assertEquals(updatedUser.getUserName(), result.getUserName());
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getPassword(), result.getPassword());
        assertEquals(updatedUser.getMobile(), result.getMobile());
    }

    // Test updating a non-existent user and expect a ResourceNotFoundException
    @Test
    public void testUpdateUserNotFound() {
        User updatedUser = new User();
        updatedUser.setUserId(1);

        when(userRepo.findById(updatedUser.getUserId())).thenReturn(java.util.Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.update(updatedUser, updatedUser.getUserId()));
    }

    // Test listing all users and validate the result
    @Test
    public void testListAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        when(userRepo.findAll()).thenReturn(userList);

        List<User> result = userServiceImpl.listAll();

        assertEquals(userList.size(), result.size());
    }

    // Test deleting a user and verify the delete operation
    @Test
    public void testDeleteUser() {
        int userId = 1;
        when(userRepo.findById(userId)).thenReturn(java.util.Optional.of(new User()));

        userServiceImpl.deleteUser(userId);

        verify(userRepo, times(1)).delete(any());
    }

    // Test deleting a non-existent user and expect a ResourceNotFoundException
    @Test
    public void testDeleteUserNotFound() {
        int userId = 1;
        when(userRepo.findById(userId)).thenReturn(java.util.Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.deleteUser(userId));
    }
    
    // Test getting a user by ID and validate the result
    @Test
    public void testGetUserByID() {
        int userId = 1;
        User user = new User();
        user.setUserId(userId);

        when(userRepo.findById(userId)).thenReturn(java.util.Optional.of(user));

        User result = userServiceImpl.getUserByID(userId);

        assertEquals(userId, result.getUserId());
    }

    // Test getting a non-existent user by ID and expect a ResourceNotFoundException
    @Test
    public void testGetUserByIDNotFound() {
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(java.util.Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUserByID(userId));
    }
}
