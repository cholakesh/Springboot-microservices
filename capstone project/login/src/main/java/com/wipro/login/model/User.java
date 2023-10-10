package com.wipro.login.model;

public class User {

	private String userName;
	private String paasword;
	private String userRole;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPaasword() {
		return paasword;
	}
	public void setPaasword(String paasword) {
		this.paasword = paasword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String paasword, String userRole) {
		super();
		this.userName = userName;
		this.paasword = paasword;
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", paasword=" + paasword + ", userRole=" + userRole + "]";
	}
	
	
	
}
