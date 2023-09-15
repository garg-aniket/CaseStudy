package com.casestudy.LoginAndRegistration.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	   	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int userId;
		
		// User's name
	    @Column(name = "user_Name")
	    @NotEmpty
	    @Size(min = 4 , message = "Usename must be min of 4 characters !!")
	    
	    // User's email address
	    private String userName;
	    @Column(name = "email")
	    @Email(message = "Email address is not valid !!")
	    private String email;
	    
	    // User's password
	    @Column(name = "password")
	    @NotEmpty
	    @Size(min = 3 , max = 10 , message = "Password must be min of 3 chars and max of 10 characters")
	    private String password;
	    
	    // User's mobile phone number
	    @NotEmpty(message = "Invalid Phone number: Empty number")
	    @NotNull(message = "Invalid Phone number: Number is NULL")
	    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
	    private String mobile;
		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
					+ ", mobile=" + mobile + "]";
		} 

		

}
