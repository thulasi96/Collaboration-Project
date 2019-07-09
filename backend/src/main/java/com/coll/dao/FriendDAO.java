package com.coll.dao;

import java.util.List;

import com.coll.model.Friend;
import com.coll.model.UserDetail;

public interface FriendDAO {
	public List<Friend> showpendingFriendRequests(String username);
	public List<Friend> showFriendList(String username);
	public List<UserDetail> showSuggestedFriends(String username);
	
	public boolean sendFriendRequest(Friend friend);
	public boolean deleteFriendRequest(int friendId);
	public boolean acceptFriendRequest(int friendId);
}
