package com.fresco.dbrestapi.model;

public class CreatePostRequest {
	
	private String user;
	private String postBody;
	
	public CreatePostRequest() {
	}
	public CreatePostRequest(String user, String postBody) {
		this.user = user;
		this.postBody = postBody;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPostBody() {
		return postBody;
	}
	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}
	
	

}
