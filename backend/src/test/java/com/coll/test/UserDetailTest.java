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
	public void addusertest() 
	{
		UserDetail user=new UserDetail();
		user.setUsername("Suriya");
		user.setFirstname("saravana");
		user.setLastname("siva");
		user.setPassword("suriya7");
		user.setEmailId("suriya@gmail.com");
		
		assertTrue("problem in adding user",userdetailDAO.addUser(user));
	}
	
	@Ignore
	@Test
	public void getusertest() {
		assertNotNull("problem in getting user",userdetailDAO.getUser("saravana"));
	}
	@Ignore
	@Test
	public void updateusertest() {
		UserDetail user=userdetailDAO.getUser("saran");
		user.setRole("ROLE_ADMIN");
		assertTrue("problem in updating user",userdetailDAO.updateUser(user));
	}
	@Ignore
	@Test
	public void listusertest() {
		List<UserDetail> listUsers=userdetailDAO.getUsers();
		for(UserDetail user:listUsers) {
			System.out.println("username:"+user.getUsername());
		}
	}
	@Ignore
	@Test
	public void checkusertest() {
		UserDetail user=userdetailDAO.getUser("Saravana");
		System.out.println("User has been checked");
		assertNotNull("problem in checking user",userdetailDAO.checkUser(user));
	}

}