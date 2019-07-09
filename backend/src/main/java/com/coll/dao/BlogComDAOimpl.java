package com.coll.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coll.model.BlogComment;

@Repository("blogCommentdao")
public class BlogComDAOimpl implements BlogCommentdao
{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addComment(BlogComment blogComment) {
		try
		{
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public boolean deleteComment(BlogComment blogComment) {
		try
		{
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public List<BlogComment> listBlogComments(int blogId) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogComment");
		List<BlogComment>commentList=query.list();
		return commentList;
	}

	@Override
	public BlogComment getBlogComment(int blogCommentId) {
		Session session=sessionFactory.openSession();
		BlogComment blogComment=session.get(BlogComment.class,blogCommentId);
		return blogComment;
	}

}
