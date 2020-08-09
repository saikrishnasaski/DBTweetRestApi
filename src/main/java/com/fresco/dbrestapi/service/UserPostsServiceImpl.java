package com.fresco.dbrestapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresco.dbrestapi.exception.NoContentException;
import com.fresco.dbrestapi.exception.ResourceNotFoundException;
import com.fresco.dbrestapi.model.Post;
import com.fresco.dbrestapi.model.Userposts;
import com.fresco.dbrestapi.repo.UserpostsRepository;

@Service
public class UserPostsServiceImpl implements UserPostsService {
	private Logger log = LoggerFactory.getLogger(UserPostsServiceImpl.class);

	@Autowired
	UserpostsRepository postsRepo;

	@Override
	public void createPosts(String user, String postBody) {
		Post post = new Post();
		int postId = 1;
		try {
			Userposts userPosts = findUserPostsById(user);
			if (userPosts.getPosts() != null) {
				postId = userPosts.getPosts().size() + 1;
			}
			post.setPostId(postId);
			post.setPostBody(postBody);
			post.setPostDate("02-08-2020");
			userPosts.getPosts().add(post);
			postsRepo.save(userPosts);
		} catch (NoSuchElementException e) {
			post.setPostId(postId);
			post.setPostBody(postBody);
			post.setPostDate("02-08-2020");
			Userposts userpost = new Userposts();
			userpost.set_id(user);
			userpost.setPosts(new ArrayList<>(Arrays.asList(post)));
			postsRepo.save(userpost);
		}
	}

	@Override
	public ArrayList<Post> getPosts(String user) {
		final ArrayList<Post> posts = new ArrayList<>();
		try {
			Userposts userPosts = findUserPostsById(user);
			log.info("user posts: {}", userPosts);
			if (userPosts.getPosts() != null) {
				posts.addAll(userPosts.getPosts());
				if (userPosts.getSubscribed() != null) {
					userPosts.getSubscribed().forEach(sub -> {
						Optional<Userposts> subPosts = postsRepo.findById(sub);
						posts.addAll(subPosts.get().getPosts());
					});
				}
			} else {
				throw new ResourceNotFoundException("Posts Not Found");
			}
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("User Not Found");
		}
		return posts;
	}

	@Override
	public void subscribe(String user, String subuser) {
		try {
			Userposts userPosts = findUserPostsById(user);
			if (userPosts.getSubscribed() != null) {
				userPosts.getSubscribed().add(subuser);
			} else {
				userPosts.setSubscribed(new ArrayList<>(Arrays.asList(subuser)));
			}
			postsRepo.save(userPosts);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("User Not Found");
		}

	}

	@Override
	public void deletePost(String user, int postId) {
		try {
			Userposts userPost = findUserPostsById(user);
			boolean postFound = false;
			if (userPost.getPosts() != null) {
				ListIterator iterator = userPost.getPosts().listIterator();
				while (iterator.hasNext()) {
					Post nextPost = (Post) iterator.next();
					if (nextPost.getPostId() == postId) {
						postFound = true;
						iterator.remove();
					}
				}
				if (!postFound) {
					throw new ResourceNotFoundException("Post Not Found");
				}
			} else {
				throw new ResourceNotFoundException("Post Not Found");
			}
			log.info("before deleting:  {}", userPost);
			postsRepo.save(userPost);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("User Not Found");
		}

	}
	
	@Override
	public HashMap<String, Boolean> searchUser(String user, String searchText) {
		final HashMap<String, Boolean> result = new HashMap<>();
		List<Userposts> matchedUsers = getMatchingUsers(searchText);
		if (matchedUsers == null) {
			throw new NoContentException("No Matching Users Found");
		}
		ArrayList<String> subscribers = getSubscribers(user);
		if (subscribers != null) {
			matchedUsers.forEach(matchedUser -> {
				if (subscribers.contains(matchedUser.get_id())) {
					result.put(matchedUser.get_id(), true);
				} else {
					result.put(matchedUser.get_id(), false);
				}
			});
		}
		return result;
	}

	/**
	 * @throws NoSuchElementException
	 */
	@Override
	public Userposts findUserPostsById(String user) {
		Optional<Userposts> userPosts = postsRepo.findById(user);
		return userPosts.get();
	}

	@Override
	public ArrayList<String> getSubscribers(String user) {
		Optional<Userposts> userPosts = postsRepo.findById(user);
		Userposts userpost = null;
		try {
			userpost = userPosts.get();
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("User Not Found");
		}
		return userpost.getSubscribed();
	}
	
	private List<Userposts> getMatchingUsers(String searchText) {
		List<Userposts> matchedUsers = null;
		List<Userposts> users = postsRepo.findAll();
		if (users != null) {
			matchedUsers = users.stream().filter(user -> user.get_id().contains(searchText)).collect(Collectors.toList());
		}
		return matchedUsers;
	}

}
