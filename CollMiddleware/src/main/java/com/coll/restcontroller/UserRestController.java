package com.coll.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.UserDetailDAO;


import com.coll.model.UserDetail;

@RestController
public class UserRestController {
	@Autowired
	UserDetailDAO userdetailDAO;
	
	@GetMapping("/showAllUser")
	public ResponseEntity<List<UserDetail>> showAllUser()
	{
		List<UserDetail> listUserDetail=userdetailDAO.getUsers();
		
		if(listUserDetail.size()>0)
		{
			return new ResponseEntity<List<UserDetail>>(listUserDetail,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<UserDetail>>(listUserDetail,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getUser/{username}")

	public ResponseEntity<UserDetail> getBlog(@PathVariable("username")String username)
	{
	UserDetail user=(UserDetail)userdetailDAO.getUser(username);
	
	return new ResponseEntity<UserDetail>(user,HttpStatus.OK);
	}
	
	@PostMapping(value="/registerUser",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addUser(@RequestBody UserDetail userDetail)
	{
		if(userdetailDAO.addUser(userDetail))
			return new ResponseEntity<String>("User Added",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/checkuser")
	public ResponseEntity<UserDetail> checkLogin(@RequestBody UserDetail user,HttpSession session)
	{
		UserDetail user1=userdetailDAO.checkUser(user);
		
		if(user1!=null)
		{
			session.setAttribute("userDetail", user1);
			return new ResponseEntity<UserDetail>(user1,HttpStatus.OK);
		}
		else
			return new ResponseEntity<UserDetail>(user1,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PatchMapping(value="/updateUser",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody UserDetail userDetail)
	{
		UserDetail user1=userdetailDAO.getUser(userDetail.getUsername());
		user1.setRole(user1.getRole());
		user1.setStatus(user1.getStatus());
		user1.setIsOnline(user1.getIsOnline());
		
		
		if(userdetailDAO.updateUser(userDetail))
			return new ResponseEntity<String>("User Updated",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);	
	}

}