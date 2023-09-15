package com.casestudy.LoginAndRegistration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.casestudy.LoginAndRegistration.Service.UserService;
import com.casestudy.LoginAndRegistration.controller.UserController;
import com.casestudy.LoginAndRegistration.model.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


/**
 * UserControllerTest is a test class for testing the UserController.
 * It contains test methods for various UserController endpoints.
 */
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    
    //Test creating a new user via POST request.
    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUserName("testuser");
        user.setPassword("testpassw");
        user.setUserId(1);
        user.setEmail("testuser@example.com");
        user.setMobile("8784799498");
        given(userService.createUser(any(User.class))).willReturn(user);

     // Perform POST request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"testuser\",\"password\":\"testpassw\",\"email\":\"testuser@example.com\",\"mobile\":\"8784799498\"}"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("testuser"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("testpassw"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.mobile").value("8784799498"));
}
    
    //Test retrieving a list of all users via GET request.
    @Test
    public void testGetAllUser() throws Exception {
    	
    	 // Test listing all users and response validation
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"user1", "pk26@gmail.com","password1","9775446788"));
        userList.add(new User(2,"user2", "gk26@gmail.com","password2","9658446788"));

        given(userService.listAll()).willReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].userName").value("user2"));
    }

    // Test updating an existing user via PUT request.
    @Test
    public void testUpdateUser() throws Exception {
    	
    	// Test updating user information and response validation
    	 User user = new User();
         user.setUserName("tesuser");
         user.setPassword("testpassw");
         user.setUserId(1);
         user.setEmail("testuser@example.com");
         user.setMobile("8784799498");
         given(userService.update(any(User.class), any(Integer.class))).willReturn(user);

         mockMvc.perform(MockMvcRequestBuilders.put("/api/users/1")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content("{\"userName\":\"tesuser\",\"password\":\"testpassw\",\"email\":\"testuser@example.com\",\"mobile\":\"8784799498\"}"))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("tesuser"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("testpassw"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
         .andExpect(MockMvcResultMatchers.jsonPath("$.mobile").value("8784799498"));
    }

    //Test deleting a user via DELETE request.
    @Test
    public void testDeleteUser() throws Exception {
    	
    	// Test deleting a user and response validation
        Mockito.doNothing().when(userService).deleteUser(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User deleted Successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
    }

    //Test retrieving a single user by ID via GET request.
    @Test
    public void testGetUser() throws Exception {
    	
    	// Test getting a single user by ID and response validation
        User user = new User(1,"user1", "pk26@gmail.com","password1","9775446788");

        given(userService.getUserByID(1)).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("user1"));
    }
}