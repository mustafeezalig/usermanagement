package com.user.mgmt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.mgmt.entity.Post;
import com.user.mgmt.entity.User;
import com.user.mgmt.exception.UserNotFoundException;
import com.user.mgmt.service.UserManagementService;
import com.user.mgmt.service.UserPostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private UserPostService userPostSerivce;
	@Autowired
	private UserManagementService userManagementService;
	@PostMapping(path = "/user/{id}/post-create")
	public ResponseEntity<?> createUserPost(@PathVariable("id")  Long userId, @RequestBody Post post) throws UserNotFoundException {
		User user=userManagementService.getUserById(userId);
		post.setUser(user);
		Post savedPost = userPostSerivce.createUserPost(post);
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success", true, "message",
				"Post created successfully", "status", HttpStatus.CREATED.value(), "data", savedPost));
	}
	
	@GetMapping(path = "/user/{id}/posts")
	public ResponseEntity<List<Post>> getUserPostById(@PathVariable("id") Long userId) throws UserNotFoundException {
		User user = userManagementService.getUserById(userId);
		List<Post> posts = user.getPosts();
		return ResponseEntity.status(HttpStatus.OK).body(posts);
				

	}

}
