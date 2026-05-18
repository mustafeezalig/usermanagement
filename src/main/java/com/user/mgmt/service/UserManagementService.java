package com.user.mgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.mgmt.entity.User;
import com.user.mgmt.exception.UserNotFoundException;
import com.user.mgmt.mapper.UserMapper;
import com.user.mgmt.repo.UserManagementRepository;
import com.user.mgmt.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManagementService {
	private final UserManagementRepository userRepository;
	 private final UserMapper userMapper;
		/*
		 * public UserManagementService(UserManagementRepository userRepository) {
		 * this.userRepository = userRepository; }
		 */

	public User getUserById(Long id) throws UserNotFoundException {

		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public List<UserResponse> getUsers() {
		return userMapper.toResponseList(userRepository.findAll());
		
	}
}
