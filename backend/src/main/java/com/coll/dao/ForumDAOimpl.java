package com.coll.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coll.model.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOimpl implements ForumDAO{

	@Autowired
	SessionFactory sessionFactory;

	
	public boolean addForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}
		catch(Exception e) {
		return false;
		}
	}

	
	public boolean updateForum(Forum forum) {
	
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e) {
		return false;
		}
	}

	public boolean deleteForum(Forum forum) {
		
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception e) {
		return false;
		}
	}


	public Forum getForum(int forumId) {
		Session session=sessionFactory.openSession();
		Forum forum=session.get(Forum.class,forumId);
		session.close();
		return forum;
	}

	
	public List<Forum> getForums() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Forum");
		List<Forum> listForums=query.list();
		return listForums;
	}
	
	
	public boolean approveForum(Forum forum) {
	
		try {
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	
	public boolean rejectForum(Forum forum) {
		
		try {
			forum.setStatus("NA");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
