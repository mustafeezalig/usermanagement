package com.user.mgmt.repo;

import org.springframework.data.repository.CrudRepository;

import com.user.mgmt.entity.UserRegistration;

public interface UserRegistrationRepository extends CrudRepository<UserRegistration, Integer> {

}
