package com.user.mgmt.security;

import lombok.Data;

@Data
public class LoginRequestDto {
	
	private String userName;
	private String passWord;

}
