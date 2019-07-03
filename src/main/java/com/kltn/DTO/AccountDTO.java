package com.kltn.DTO;

public class AccountDTO {
	
	private String loginName;
	private String password;
	private UserDTO userDTO;
	private ERoleDTO roleDTO;
	
	public AccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public ERoleDTO getRoleDTO() {
		return roleDTO;
	}
	public void setRoleDTO(ERoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}
	
	
}
