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

	@Ignore
	@Test
	public void addblogtest() {
		Blog blog=new Blog();
		blog.setBlogName("new blog");
		blog.setBlogContent("third content");
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(1);
		blog.setDislikes(1);
		assertTrue("problem in adding blog",blogDAO.addBlog(blog));
	}
	
	@Ignore
	@Test
	public void getblogtest() {
		Blog blog=blogDAO.getBlog(563);
		blog.getBlogName();
		System.out.println(blog.getBlogName());
		assertNotNull("problem in getting blog",blogDAO.getBlog(563));
	}

	
	@Test
	public void updateblogtest() {
		Blog blog=blogDAO.getBlog(3);
		blog.setBlogName("Java");
		assertTrue("problem in updating blog",blogDAO.updateBlog(blog));
	}
	
	@Ignore
	@Test
	public void deleteblogtest() {
		Blog blog=blogDAO.getBlog(561);
		assertTrue("problem in deleting blog",blogDAO.deleteBlog(blog));
	}
	
	@Ignore
	@Test
	public void listblogtest() {
		List<Blog> listBlogs=blogDAO.getBlogs();
		for(Blog blog:listBlogs) {
			System.out.println("id:"+blog.getBlogid());
		}
	}
	
	@Ignore
	@Test
	public void incrementlikestest() 
	{
		assertTrue("problem in incrementing likes",blogDAO.incrementLikes(523));
	}
	@Ignore
	@Test
	public void incrementdislikestest() {
		assertTrue("problem in incrementing likes",blogDAO.incrementDislikes(1001));
	}
	@Ignore
	@Test
	public void approveblogtest() {
		Blog blog=blogDAO.getBlog(575);
	assertTrue("problem in Approving Blog",blogDAO.approveBlog(blog));
	}
	
	@Ignore
	@Test
	public void rejectblogtest() {
		Blog blog=blogDAO.getBlog(541);
		assertTrue("problem in Rejecting Blog",blogDAO.rejectBlog(blog));

	}
}