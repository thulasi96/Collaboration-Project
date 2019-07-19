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
	public void sendfriendrequesttest() {
		Friend friend=new Friend();
		friend.setFriendusername("thulsi");
		friend.setUsername("imman");
		friend.setStatus("NA");
		assertTrue("problem in adding friend",friendDAO.sendFriendRequest(friend));
	}
	@Ignore
	@Test
	public void getfriendtest() {
		assertNotNull("problem in getting user",friendDAO.getFriend(0));
	}
	@Ignore
	@Test
	public void acceptfriendrequesttest() {
		Friend friend=friendDAO.getFriend(521);
		assertTrue("problem in adding friend",friendDAO.acceptFriendRequest(friend));
	}
    @Ignore
	@Test
	public void deletefriendrequesttest() {
		Friend friend=friendDAO.getFriend(523);
		assertTrue("problem in adding friend",friendDAO.deleteFriendRequest(friend));
	}
    @Ignore
	@Test
	public void showfriendlisttest() {
		List<Friend> listFriends=friendDAO.showFriendList("immadmin");
		for(Friend friend:listFriends) {
			System.out.println("username:"+friend.getFriendusername());
		}
	}
	@Ignore
	@Test
	public void showpendingfriendrequesttest() {
		List<Friend> listFriends=friendDAO.showpendingFriendRequests("immadmin");
		for(Friend friend:listFriends) {
			System.out.println("username:"+friend.getFriendusername());
		}
	}
	@Ignore
	@Test
	public void showsuggestedfriendstest() {
		List<UserDetail> listUsers=friendDAO.showSuggestedFriends("immadmin");
		for(UserDetail userDetail:listUsers) {
			System.out.println("username:"+userDetail.getUsername());
		}
	}
}
