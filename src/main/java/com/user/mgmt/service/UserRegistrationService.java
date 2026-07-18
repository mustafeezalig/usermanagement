package com.user.mgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.mgmt.entity.UserRegistration;
import com.user.mgmt.repo.UserRegistrationRepository;

@Service
public class UserRegistrationService {

	@Autowired
	private UserRegistrationRepository userRegisRepo;
	
	public UserRegistration registerUser(UserRegistration userRegistration) {
		return userRegisRepo.save(userRegistration);
	}

}
