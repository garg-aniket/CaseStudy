package com.casestudy.LoginAndRegistration.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	   	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int userId;
	    @Column(name = "user_Name")
	    @NotEmpty
	    @Size(min = 4 , message = "Usename must be min of 4 characters !!")
	    private String userName;
	    @Column(name = "email")
	    @Email(message = "Email address is not valid !!")
	    private String email;
	    @Column(name = "password")
	    @NotEmpty
	    @Size(min = 3 , max = 10 , message = "Password must be min of 3 chars and max of 10 characters")
	    private String password;
		public User(int userId, String userName, String email, String password) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.email = email;
			this.password = password;
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		public long getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
					+ "]";
		}

}
