package com.casestudy.LoginAndRegistration.Service;
import java.util.List;

import com.casestudy.LoginAndRegistration.model.User;

public interface UserService{
	
	 User createUser(User users);
	 User update(User user , Integer userId);
	 List<User> listAll() ;
	 void deleteUser(Integer _id);
	 User getUserByID(Integer userId);
	 
}

