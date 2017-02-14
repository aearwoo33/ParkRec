package com.aearwood.coordinator.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.aearwood.coordinator.domain.Role;

public class UserCreateForm {
	  @NotEmpty
	    private String email = "";
	  
	  @NotEmpty
		private String firstName = "";
		
	    @NotEmpty
		private String lastName = "";

	    @NotEmpty
	    private String password = "";

	    @NotEmpty
	    private String passwordRepeated = "";

	    @NotNull
	    private Role role;
	    
	    @NotNull
	    private String phoneNumber="";
	    
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPasswordRepeated() {
			return passwordRepeated;
		}

		public void setPasswordRepeated(String passwordRepeated) {
			this.passwordRepeated = passwordRepeated;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
}
