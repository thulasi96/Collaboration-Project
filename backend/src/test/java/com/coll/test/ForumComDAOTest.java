package com.coll.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.ForumCommentDAO;
import com.coll.model.ForumComment;
public class ForumComDAOTest 
{
	static ForumCommentDAO forumcommentDAO;
	
	@BeforeClass
	public static void executefirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		forumcommentDAO=(ForumCommentDAO)context.getBean("forumcommentDAO");
	}
	
	@Test
	public void addforumcommenttest() {
		ForumComment forumcomment=new ForumComment();
		forumcomment.setForumId(2);
		forumcomment.setForumComment("good");
		forumcomment.setCommentDate(new java.util.Date());
		forumcomment.setUsername("Arjun");
		assertTrue("problem in adding forum comment",forumcommentDAO.addForumComment(forumcomment));
	}
	@Ignore
	@Test
	public void getforumcommenttest() {
		assertNotNull("problem in getting forumcomment",forumcommentDAO.getForumComment(1003));
	}
	@Ignore
	@Test
	public void updateforumcommenttest() {
		ForumComment forumcomment=forumcommentDAO.getForumComment(1003);
		forumcomment.setForumComment("nice");
		assertTrue("problem in adding forum comment",forumcommentDAO.updateForumComment(forumcomment));
	}
	@Ignore
	@Test
	public void deleteforumcomment() {
		ForumComment forumcomment=forumcommentDAO.getForumComment(503);
		assertTrue("problem in deleting forum comment",forumcommentDAO.deleteForumComment(forumcomment));
	}
	@Ignore
	@Test
	public void listforumcomment() {
		List<ForumComment> listComments=forumcommentDAO.getForumComments();
	    for(ForumComment forumcomment:listComments) {
	    	System.out.println("id:"+forumcomment.getCommentId());
	    }
	}
}