package com.coll.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.coll.dao.UserDetailDAO;
import com.coll.model.UserDetail;

public class UserDetailTest {

static UserDetailDAO userdetailDAO;
	
	@BeforeClass
	public static void executefirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		userdetailDAO=(UserDetailDAO)context.getBean("userdetailDAO");
	}
	
	
	@Test
	public void addusertest() {
		UserDetail userDetail=new UserDetail();
		userDetail.setUsername("Thulsi");
		userDetail.setPassword("12345");
		userDetail.setEmailId("thulsi@gmail.com");
		assertTrue("problem in adding user",userdetailDAO.addUser(userDetail));
	}

	@Ignore
	@Test
	public void getusertest() {
		assertNotNull("problem in getting user",userdetailDAO.getUser(""));
	}
	@Ignore
	@Test
	public void updateusertest() {
		UserDetail userDetail=userdetailDAO.getUser("");
		userDetail.setUsername("");
		assertTrue("problem in updating user",userdetailDAO.updateUser(userDetail));
	}
	
	@Ignore
	@Test
	public void checkusertest() {
		UserDetail userDetail=userdetailDAO.getUser("");
		System.out.println("username:"+userDetail.getUsername());
		assertNotNull("problem in checking user",userdetailDAO.checkUser(userDetail));
	}
}
