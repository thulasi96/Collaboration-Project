package com.coll.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coll.model.Friend;
import com.coll.model.UserDetail;

@Repository("friendDAO")
@Transactional
public class FriendDAOimpl implements FriendDAO{
	@Autowired
	SessionFactory sessionFactory;

	public List<Friend> showFriendList(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where(username=:uname or friendname=:fname) and status='A'");
		query.setParameter("uname", username);
		query.setParameter("fname", username);
		
		List<Friend> friendList=(List<Friend>)query.list();
		session.close();
		return friendList;
	}

	public List<Friend> showPendingFriendList(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where friendname=:uname and status='P'");
		query.setParameter("uname", username);
		List<Friend> friendList=query.list();
		return friendList;
	}

	public List<UserDetail> showSuggestedFriend(String username) {
		Session session=sessionFactory.openSession();
		Query sqlQuery=session.createNativeQuery("select username from userdetail where username not in (select friendname from friend where username='"+username+"')and username not in (select username from friend where friendname='"+username+"' and status='A')and username!='"+username+"'");
		
		List<String> userList=(List<String>)sqlQuery.list();
		
		ArrayList<UserDetail> userDetailList=new ArrayList<UserDetail>();
		
		int i=0;
	
		while(i<userList.size())
		{
			UserDetail user=session.get(UserDetail.class,userList.get(i).toString().trim());
			userDetailList.add(user);
			i++;
		}
		session.close();
		
		return userDetailList;
	}

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
}