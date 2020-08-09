package com.fresco.dbrestapi.model;

public class SubscriberDetails {

	private String user;
	private String subuser;

	public SubscriberDetails() {
	}

	public SubscriberDetails(String user, String subuser) {
		super();
		this.user = user;
		this.subuser = subuser;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSubuser() {
		return subuser;
	}

	public void setSubuser(String subuser) {
		this.subuser = subuser;
	}

}
