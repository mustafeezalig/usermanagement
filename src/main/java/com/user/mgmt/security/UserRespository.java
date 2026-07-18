package com.user.mgmt.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRespository extends JpaRepository<UserInfo,Long> {

	Optional<UserDetails> findByUserName(String username);

}
