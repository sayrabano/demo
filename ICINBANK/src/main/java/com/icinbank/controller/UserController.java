package com.icinbank.controller;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

public class UserController {

	@Id
	 @GeneratedValue
		private Long id;
	    private String username;
		private String firstName;
		private String lastName;
		private String email;
		private String phone;
		public UserController() {
			super();
			// TODO Auto-generated constructor stub
		}
		public UserController(Long id, String username, String firstName, String lastName, String email, String phone,
				String password, String address) {
			super();
			this.id = id;
			this.username = username;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.phone = phone;
			this.password = password;
			this.address = address;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		private String password;
		private String address;
}
