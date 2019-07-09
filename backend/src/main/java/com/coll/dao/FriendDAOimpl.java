package com.coll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.coll.model.Friend;
import com.coll.model.UserDetail;

public class FriendDAOimpl implements FriendDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Friend> showpendingFriendRequests(String username) {
		
		return null;
	}

	@Override
	public List<Friend> showFriendList(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where (usernam=:uname or friendusername=:funame) and status='A'");
		query.setParameter("uname", username);
		query.setParameter("funame", username);
		List<Friend> friendList=(List<Friend>)query.list();
		return friendList;
	}

	@Override
	public List<UserDetail> showSuggestedFriends(String username) {
		Session session=sessionFactory.openSession();
		Query sqlQuery=session.createNativeQuery("select username from UserDetail where username not in(select friendusername from Friend where username='"+username+"')"
				+ "and username not in(select username from Friend where friendusername='"+username+"' and status='A')and username!='"+username+"'");
		List<String>listUsers=(List<String>)sqlQuery.list();
		
		ArrayList<UserDetail> listUserDetail=new ArrayList<UserDetail>();
		int count=0;
		while(count<listUsers.size())
		{
			UserDetail user=session.get(UserDetail.class, listUsers.get(count).toString().trim());
			listUserDetail.add(user);
			count++;
		}
		session.close();
		return listUserDetail;
	}

	@Override
	public boolean sendFriendRequest(Friend friend) {
		try
		{
			friend.setStatus("P");
			sessionFactory.getCurrentSession().save(friend);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public boolean deleteFriendRequest(int friendId) {
		try
		{
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class, friendId);
			session.close();
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public boolean acceptFriendRequest(int friendId) {
		try
		{
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class, friendId);
			session.close();
			friend.setStatus("A");
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}
	

}
