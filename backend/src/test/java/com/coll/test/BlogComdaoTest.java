package com.coll.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogCommentdao;
import com.coll.model.BlogComment;

public class BlogComdaoTest {

static BlogCommentdao blogcommentDAO;
	
	@BeforeClass
	public static void executefirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		blogcommentDAO=(BlogCommentdao)context.getBean("blogCommentDAO");
	}
	
	@Test
	public void addblogcommenttest() {
		BlogComment blogcomment=new BlogComment();
		blogcomment.setBlogId(501);
		blogcomment.setCommentText("Good");
		blogcomment.setCommentDate(new java.util.Date());
		blogcomment.setUsername("thulasi");
		assertTrue("problem in adding blog comment",blogcommentDAO.addBlogComment(blogcomment));
	}

	@Ignore
	@Test
	public void getblogcommenttest() {
		assertNotNull("problem in getting blogcomment",blogcommentDAO.getBlogComment(501));
	}
	
	@Ignore
	@Test
	public void deleteblogcomment() {
		BlogComment blogcomment=blogcommentDAO.getBlogComment(501);
		assertTrue("problem in deleting blog comment",blogcommentDAO.deleteBlogComment(blogcomment));
	}

	
}
