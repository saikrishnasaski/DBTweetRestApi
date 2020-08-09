package com.fresco.dbrestapi.model;

public class DeletePostRequest {

	private String user;
	private int postId;

	public DeletePostRequest() {

	}

	public DeletePostRequest(String user, int postId) {
		super();
		this.user = user;
		this.postId = postId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

}
