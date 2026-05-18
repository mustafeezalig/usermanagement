package com.user.mgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.mgmt.entity.Post;
import com.user.mgmt.repo.UserPostRepository;

@Service
public class UserPostService {

	@Autowired
	private UserPostRepository userPostRepository;

	public Post createUserPost(Post post) {
		 return userPostRepository.save(post);
	}

}
