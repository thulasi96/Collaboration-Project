package com.coll.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.FriendDAO;
import com.coll.model.Friend;
import com.coll.model.UserDetail;


@RestController
public class FriendRestController {

	@Autowired
	FriendDAO friendDAO;
	
	@GetMapping("/getFriend/{friendId}") 
	public ResponseEntity<Friend> getFriend(@PathVariable("friendId") int friendId) 
	{
		Friend friend=friendDAO.getFriend(friendId);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@GetMapping("/showFriendList/{username}")
	public ResponseEntity<List<Friend>> showFriendList(@PathVariable("username") String username) 
	{
		List<Friend> listFriends=friendDAO.showFriendList(username);
		if(listFriends.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/showpendingFriendRequests/{username}")
	public ResponseEntity<List<Friend>> showPendingFriendRequest(@PathVariable("username") String username) 
	{
		List<Friend> listFriends=friendDAO.showpendingFriendRequests(username);
		if(listFriends.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/showSuggestedFriends/{username}")
	public ResponseEntity<List<UserDetail>> showSuggestedFriend(@PathVariable("username") String username) 
	{
		List<UserDetail> listFriends=friendDAO.showSuggestedFriends(username);
		if(listFriends.size()>0)
		{
			return new ResponseEntity<List<UserDetail>>(listFriends,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<UserDetail>>(listFriends,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/sendFriendRequest",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> sendFriendRequest(@RequestBody Friend friend)
	{
		if(friendDAO.sendFriendRequest(friend))
		{
			return new ResponseEntity<String>("Friend added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Error adding friend",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/acceptFriendRequest/{friendId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> acceptFriendRequest(@PathVariable("friendId") int friendId)
	{
		Friend friend=friendDAO.getFriend(friendId);
		if(friendDAO.acceptFriendRequest(friend)) 
		{
			return new ResponseEntity<String>("Friend accepted",HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Error accepted friend",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteFriendRequest/{friendId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteFriendRequest(@PathVariable("friendId") int friendId)
	{
		Friend friend=friendDAO.getFriend(friendId);
		if(friendDAO.deleteFriendRequest(friend)) 
		{
			return new ResponseEntity<String>("Friend deleted",HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Error deleting friend",HttpStatus.NOT_FOUND);
		}
	}
}