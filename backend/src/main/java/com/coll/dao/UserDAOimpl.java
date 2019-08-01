package com.coll.dao;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.UserDetail;


@Repository("userdetailDAO")
@Transactional
public class UserDAOimpl implements UserDetailDAO{

	@Autowired
	SessionFactory sessionFactory;


	
	public boolean updateUser(UserDetail user) {
		try
		{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}


	public List<UserDetail> getUsers() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UserDetail");
		List<UserDetail> listUsers=query.list();
		return listUsers;
	}

	
	public boolean addUser(UserDetail userDetail) {
		try {
			sessionFactory.getCurrentSession().save(userDetail);
			return true;
		}
		catch(Exception e) {
		    return false;
		}
	}

	
	public UserDetail getUser(String username) {
		Session session=sessionFactory.openSession();
	    UserDetail user=session.get(UserDetail.class,username);
	    session.close();
		return user;
	}

	
	public UserDetail checkUser(UserDetail userDetail) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UserDetail where username=:uname and password=:pword");
		query.setParameter("uname", userDetail.getUsername());
		query.setParameter("pword",userDetail.getPassword());
		List<UserDetail> listUsers=query.list();
		if(listUsers!=null) {
			return listUsers.get(0);
		}
		return null;
	}

	
}
