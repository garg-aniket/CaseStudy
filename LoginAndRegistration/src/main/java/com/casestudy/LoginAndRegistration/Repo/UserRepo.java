package com.casestudy.LoginAndRegistration.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.LoginAndRegistration.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
