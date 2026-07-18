package com.user.mgmt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.mgmt.entity.UserRegistration;
import com.user.mgmt.service.UserRegistrationService;

@RestController("/public")
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRegistration userRegistration) {
         System.out.println("Call..............");
		UserRegistration userReg = userRegService.registerUser(userRegistration);
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("success", true, "message", "User fetched successfully",
				"status", HttpStatus.OK.value(), "data", userReg));
	}

}
