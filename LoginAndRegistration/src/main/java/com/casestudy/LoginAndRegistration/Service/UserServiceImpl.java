package com.casestudy.LoginAndRegistration.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.LoginAndRegistration.model.User;
import com.casestudy.LoginAndRegistration.Exception.ResourceNotFoundException;
import com.casestudy.LoginAndRegistration.Repo.UserRepo;
@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	    private UserRepo repo;

	    public User createUser(User users) {
	    	
	        return repo.save(users);
	    }
	    
	    public User update(User user , Integer userId) {
	    	
	    	User user1 = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , userId));
	    	
	    	user1.setUserName(user.getUserName());
	    	user1.setEmail(user.getEmail());
	    	user1.setPassword(user.getPassword());
	    	
	    	User updatedUser = this.repo.save(user1);
	    	return updatedUser;
	    }

	    public List<User> listAll() {

	        return this.repo.findAll();
	    }


	    public void deleteUser(Integer userId) {
	    	
	    	User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , userId));
	        repo.delete(user);
	    }

		@Override
		public User getUserByID(Integer userId) {
			
			return repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id" ,userId));
		}

		

		

		

}
