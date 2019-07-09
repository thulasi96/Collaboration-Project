package com.coll.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogDAO;
import com.coll.model.Blog;

public class BlogdaoTest {

	static BlogDAO blogDAO; 
	
	@BeforeClass
	public static void executefirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		blogDAO=(BlogDAO)context.getBean("blogDAO");
	}

	
	@Test
	public void addblogtest() {
		Blog blog=new Blog();
		blog.setBlogName("Adv Java");
		blog.setBlogContent("Java Advance Content");
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(1);
		blog.setDislikes(1);
		assertTrue("problem in adding blog",blogDAO.addBlog(blog));
	}
	
}
