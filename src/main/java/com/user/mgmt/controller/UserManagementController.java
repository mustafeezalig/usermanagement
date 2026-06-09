package com.user.mgmt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.mgmt.entity.User;
import com.user.mgmt.exception.UserNotFoundException;
import com.user.mgmt.response.UserResponse;
import com.user.mgmt.service.UserManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserManagementController {

	@Autowired
	private UserManagementService userManagementService;

	@GetMapping(path = "/user/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Long userId) throws UserNotFoundException {
		User user = userManagementService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("success", true, "message", "User fetched successfully",
				"status", HttpStatus.OK.value(), "data", user));
	}

	@PostMapping(path = "/user/create")
	public ResponseEntity<?> getUserById(@Valid @RequestBody User user) {
		User savedUser = userManagementService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success", true, "message",
				"User created successfully", "status", HttpStatus.CREATED.value(), "data", savedUser));
	}

	@GetMapping(path = "/users")
	public ResponseEntity<?> getUserById() throws UserNotFoundException {
		List<UserResponse> userResponse = userManagementService.getUsers();
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("success", true, "message", "User fetched successfully",
				"status", HttpStatus.OK.value(), "data", userResponse));
	}

	@DeleteMapping(path = "/user/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable String userId) throws UserNotFoundException {
		userManagementService.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	
}
