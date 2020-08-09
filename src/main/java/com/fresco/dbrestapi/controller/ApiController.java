package com.fresco.dbrestapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fresco.dbrestapi.exception.ResourceNotFoundException;
import com.fresco.dbrestapi.model.CreatePostRequest;
import com.fresco.dbrestapi.model.DeletePostRequest;
import com.fresco.dbrestapi.model.Post;
import com.fresco.dbrestapi.model.SearchUserRequest;
import com.fresco.dbrestapi.model.SubscriberDetails;
import com.fresco.dbrestapi.service.UserPostsService;

@RestController
public class ApiController {
	private Logger log = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private UserPostsService userPostsService;

	@CrossOrigin
	@PostMapping(path = "/addpost", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String post(String user, String postBody) throws JsonParseException, JsonMappingException, IOException {
		log.info("user: {}, postBody: {}", user, postBody);
		userPostsService.createPosts(user, postBody);
		return "OK 200";
	}

	@CrossOrigin
	@PostMapping(path = "/getposts", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public ArrayList<Post> getPosts(String user) throws ResourceNotFoundException, IOException {
		log.info("user posts: {}", user);
		return userPostsService.getPosts(user);
	}

	@CrossOrigin
	@PostMapping(path = "/delpost", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String delPosts(String user, String postId) throws JsonParseException, JsonMappingException, IOException {
		log.info("user: {}, postId: {}", user, postId);
		userPostsService.deletePost(user, Integer.parseInt(postId));
		return "OK 200";
	}

	@CrossOrigin
	@PostMapping(path = "/searchuser", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public HashMap<String, Boolean> searchUser(String user, String searchText) throws JsonParseException, JsonMappingException, IOException {
		log.info("user: {}, searchText: {}", user, searchText);
		return userPostsService.searchUser(user, searchText);
	}

	@CrossOrigin
	@PostMapping(path = "/subscriber", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String subscriber(String user, String subuser) throws JsonParseException, JsonMappingException, IOException {
		log.info("user: {}, subuser: {}", user, subuser);
		userPostsService.subscribe(user, subuser);
		return "OK 200";
	}
}
