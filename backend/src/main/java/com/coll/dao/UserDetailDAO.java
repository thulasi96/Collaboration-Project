package com.coll.dao;

import com.coll.model.UserDetail;

public interface UserDetailDAO {

	public boolean registerUser(UserDetail user);
	public UserDetail getUserDetail(String username);
	public UserDetail checkCredentail(UserDetail user);
	public boolean updateUser(UserDetail user);
	
}
