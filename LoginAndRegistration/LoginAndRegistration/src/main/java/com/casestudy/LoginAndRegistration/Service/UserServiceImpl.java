package com.casestudy.LoginAndRegistration.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.LoginAndRegistration.model.User;
import com.casestudy.LoginAndRegistration.Exception.ResourceNotFoundException;
import com.casestudy.LoginAndRegistration.Repo.UserRepo;


/**
 * UserServiceImpl provides the implementation for user-related operations.
 * It interacts with the UserRepo to perform CRUD operations on User entities.
 */

@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	    private UserRepo repo;
	 			
	 /**
	     * Create a new user.
	     * @param user The user to be created.
	     * @return The created user.
	     */
	    public User createUser(User users) {
	    	
	        return repo.save(users);
	    }
	    
	    /**
	     * Update an existing user by ID.
	     * @param user The updated user information.
	     * @param userId The ID of the user to be updated.
	     * @return The updated user.
	     * @throws ResourceNotFoundException If the user with the specified ID is not found.
	     */
	    public User update(User user , Integer userId) {
	    	
	    	User user1 = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , userId));
	    	
	    	user1.setUserName(user.getUserName());
	    	user1.setEmail(user.getEmail());
	    	user1.setPassword(user.getPassword());
	    	user1.setMobile(user.getMobile());
	    	
	    	User updatedUser = this.repo.save(user1);
	    	return updatedUser;
	    }
	    
	    /**
	     * Get a list of all users.
	     * @return A list of all users.
	     */
	    public List<User> listAll() {

	        return this.repo.findAll();
	    }

	    /**
	     * Delete a user by ID.
	     * @param userId The ID of the user to be deleted.
	     * @throws ResourceNotFoundException If the user with the specified ID is not found.
	     */
	    public void deleteUser(Integer userId) {
	    	
	    	User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , userId));
	        repo.delete(user);
	    }
	    
	    /**
	     * Get a single user by ID.
	     * @param userId The ID of the user to retrieve.
	     * @return The user with the specified ID.
	     * @throws ResourceNotFoundException If the user with the specified ID is not found.
	     */
		@Override
		public User getUserByID(Integer userId) {
			//get user from database with the help of user Repository
			User user  = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id" ,userId));
			return user;
		}

		

		

		

}
