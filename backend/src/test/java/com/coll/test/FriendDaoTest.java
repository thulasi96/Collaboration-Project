package com.coll.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.FriendDAO;
import com.coll.model.Friend;
import com.coll.model.UserDetail;

public class FriendDaoTest {
static FriendDAO friendDAO;
	
	@BeforeClass
	public static void executefirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		friendDAO=(FriendDAO)context.getBean("friendDAO");
	}
	
	
	@Test
	public void sendFriendRequestTest()
	{
		Friend friend=new Friend();
		
		friend.setUsername("Thulsi");
		friend.setFriendName("Saravana");
		
		assertTrue("problem in sending Friend Request",friendDAO.sendFriendRequest(friend));
	}
	@Ignore
	@Test
	public void acceptFriendRequest()
	{
		assertTrue("Problem in Accepting Friend Request",friendDAO.acceptFriendRequest(29));
	}
	
	@Ignore
	@Test
	public void showFriendListTest()
	{
		List<Friend> friendList=friendDAO.showFriendList("Saravana");
		assertTrue("Problem in showing Friend Request:",friendList.size()>0);
		
		for(Friend friend:friendList)
		{
			System.out.println(friend.getUsername()+":");
			System.out.println(friend.getFriendName());
		}
	}
	
	@Ignore
	@Test
	public void ShowPendingFriendListTest() 
	{
		List<Friend> pendingFriendList=friendDAO.showPendingFriendList("Saravana");
		assertTrue("Problem in showing pending Friend List",pendingFriendList.size()>0);
		
		for(Friend friend:pendingFriendList)
		{
			System.out.println(friend.getUsername());
		}
	}
	
	@Ignore
	@Test
	public void showsuggestedfriendstest() {
		List<UserDetail> listUsers=friendDAO.showSuggestedFriend("Saravana");
		for(UserDetail userDetail:listUsers) {
			System.out.println("username:"+userDetail.getUsername());
		}
	}
}