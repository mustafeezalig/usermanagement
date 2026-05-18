package com.user.mgmt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.mgmt.entity.Post;

public interface UserPostRepository extends JpaRepository<Post, Long>{

}
