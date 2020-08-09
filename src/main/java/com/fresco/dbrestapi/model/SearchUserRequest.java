package com.fresco.dbrestapi.model;

public class SearchUserRequest {

	private String user;
	private String searchText;

	public SearchUserRequest() {
		super();
	}

	public SearchUserRequest(String user, String searchText) {
		super();
		this.user = user;
		this.searchText = searchText;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

}
