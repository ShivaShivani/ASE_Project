package com.umkc.pojo;

public class RegisterPojo {

	String first_name;
	String user_name;
	
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String lastName) {
		this.last_name = lastName;
	}
	public String getUserName() {
		return user_name;
	}
	public void setUserName(String userName) {
		this.user_name = userName;
	}
	public String getEmailID() {
		return email;
	}
	public void setEmailID(String emailID) {
		this.email = emailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return password_confirmation;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.password_confirmation = confirmPassword;
	}
	String last_name;
	String email;
	String password;
	String password_confirmation;
	
}
