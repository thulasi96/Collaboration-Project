package com.coll.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table

public class Friend {

	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE,generator="friendseq")
	@SequenceGenerator(name="friendidseq",allocationSize=1,sequenceName="Friendid_seq")
	int friendId;
	
	private String username;
	private String friendusername;
	private String status;
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFriendusername() {
		return friendusername;
	}
	public void setFriendusername(String friendusername) {
		this.friendusername = friendusername;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
