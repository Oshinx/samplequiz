package com.rewritingmole.model;

public class User {
 private String userid;
 private String email;
 private String password;
 private String role;
 
 
 
 public User() {
	 
 }
 
 
  public User(String userid, String email, String password, String role) {
	super();
	this.userid = userid;
	this.email = email;
	this.password = password;
	this.role = role;
     }  



public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
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
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
 
 
 
}
