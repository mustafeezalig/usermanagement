package com.user.mgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.mgmt.entity.User;
import com.user.mgmt.exception.UserNotFoundException;
import com.user.mgmt.repo.UserManagementRepository;

@Service
public class UserManagementService {
	private final UserManagementRepository userRepository;

	public UserManagementService(UserManagementRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	public User getUserById(Long id) throws UserNotFoundException {

		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
