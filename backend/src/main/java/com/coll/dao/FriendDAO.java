package com.coll.dao;

import java.util.List;

import com.coll.model.Friend;
import com.coll.model.UserDetail;

public interface FriendDAO {
	public List<Friend> showFriendList(String username);
	public List<Friend> showPendingFriendList(String username);
	public List<UserDetail> showSuggestedFriend(String username);
	
	public boolean sendFriendRequest(Friend friend);
	public boolean acceptFriendRequest(int friendId);
	public boolean deleteFriendRequest(int friendId);
}
