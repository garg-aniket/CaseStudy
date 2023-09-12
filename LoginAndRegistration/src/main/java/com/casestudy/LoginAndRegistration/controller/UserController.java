package com.casestudy.LoginAndRegistration.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.LoginAndRegistration.Response.ApiResponse;
import com.casestudy.LoginAndRegistration.model.User;

import jakarta.validation.Valid;

import com.casestudy.LoginAndRegistration.Service.UserService;
@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
    private UserService userService;

    @PostMapping(value = "/")
    private ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User user1 = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser() {
    	List<User> allUser = this.userService.listAll();
        return ResponseEntity.ok(allUser);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<User> update(@Valid @RequestBody User user, @PathVariable(name = "id") int userId) {
        User updatedUser = this.userService.update(user, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") int userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    private ResponseEntity<User> getUser(@PathVariable(name = "id") int userId) {
    	User user = userService.getUserByID(userId);
        return ResponseEntity.ok(user);
    }

}
