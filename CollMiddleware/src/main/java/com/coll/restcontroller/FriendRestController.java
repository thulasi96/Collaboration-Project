package com.coll.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coll.dao.FriendDAO;

public class FriendRestController {

	@Autowired
	FriendDAO friendDAO;
	
}
