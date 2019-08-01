package com.coll.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.coll.model.ForumComment;
@Repository("forumcommentDAO")
@Transactional
public class ForumCommentDAOimpl implements ForumCommentDAO{
	@Autowired
	SessionFactory sessionFactory;

	public boolean addForumComment(ForumComment forumcomment) {
		try {
			sessionFactory.getCurrentSession().save(forumcomment);
			System.out.println("Forum comment data created");
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}

	public boolean updateForumComment(ForumComment forumcomment) {
		try {
			sessionFactory.getCurrentSession().update(forumcomment);
			System.out.println("Forum comment data updated");
			return true;
		}
		catch(Exception e) 
		{
		return false;
		}
	}

	public boolean deleteForumComment(ForumComment forumcomment) {
		try {
			sessionFactory.getCurrentSession().delete(forumcomment);
			System.out.println("Forum comment data deleted");
			return true;
		}
		catch(Exception e) 
		{
		return false;
		}
	}

	public ForumComment getForumComment(int commentId) {
		Session session=sessionFactory.openSession();
		ForumComment forumcomment=session.get(ForumComment.class,commentId);
		session.close();
		return forumcomment;
	}

	public List<ForumComment> getForumComments() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from ForumComment");
		List<ForumComment> listComments=query.list();
		return listComments;
	}

	public List<ForumComment> listForumComments(int forumId) {
		Session session=sessionFactory.openSession();
	    Query query=session.createQuery("from ForumComment where forumid=:forumId");
	    query.setParameter("forumId", forumId);
	    List<ForumComment> listComments=query.list();
	    return listComments;
	}

}