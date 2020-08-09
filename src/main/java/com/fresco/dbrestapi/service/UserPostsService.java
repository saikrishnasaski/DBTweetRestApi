package com.fresco.dbrestapi.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.fresco.dbrestapi.model.Post;
import com.fresco.dbrestapi.model.Userposts;

public interface UserPostsService {
	
	public Userposts findUserPostsById(String user);
	
	public void createPosts(String user, String postBody);
	
	public ArrayList<Post> getPosts(String user);
	
	public void subscribe(String user, String subuser);
	
	public void deletePost(String user, int postId);
	
	public ArrayList<String> getSubscribers(String user);
	
	public HashMap<String, Boolean> searchUser(String user, String searchText);

}
