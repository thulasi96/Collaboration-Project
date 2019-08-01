package com.coll.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOimpl implements BlogDAO {
	@Autowired
	SessionFactory sessionFactory;

	public boolean addBlog(Blog blog) {

		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception e) {
		return false;
		}
	}

	public boolean updateBlog(Blog blog) {
		
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e) {
		return false;
		}
	}

	public boolean deleteBlog(Blog blog) {

		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e) {
		return false;
		}
	}

	public Blog getBlog(int blogId) {
		Session session=sessionFactory.openSession();
		Blog blog=session.get(Blog.class,blogId);
		session.close();
		return blog;
	}

	public List<Blog> getBlogs() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Blog");
		List<Blog> listBlogs=query.list();
		return listBlogs;
	}

	public boolean incrementLikes(int blogId) {
		
		try {
			Blog blog=getBlog(blogId);
			blog.setLikes(blog.getLikes()+1);
			blog.setDislikes(blog.getDislikes()-1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean incrementDislikes(int blogId) {
		
		try {
			Blog blog=getBlog(blogId);
			blog.setDislikes(blog.getDislikes()+1);
			blog.setLikes(blog.getLikes()-1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean rejectBlog(int blogId) {
		
		try {
			Blog blog=getBlog(blogId);
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e) {
			return false;
		}

	}

	public boolean approveBlog(Blog blog) {
		try
		{
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			return false;	
		}
	}

	public boolean rejectBlog(Blog blog) {
		try
		{
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			return false;	
		}
	}
	
}