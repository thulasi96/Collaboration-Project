package com.coll.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.ForumDAO;
import com.coll.model.Forum;


@RestController
public class ForumRestController {

	@Autowired
	ForumDAO forumDAO;
	
	@GetMapping("/showAllForum")
	public ResponseEntity<List<Forum>> showAllForums()
	{
		List<Forum> listForums=forumDAO.getForums();
		
		if(listForums.size()>0)
		{
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getForum/{forumId}")
	
		public ResponseEntity<Forum> getForum(@PathVariable("forumId")int forumId)
		{
		Forum forum=(Forum)forumDAO.getForum(forumId);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
	
	@PostMapping(value="/addForum",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addForum(@RequestBody Forum forum)
	{
		forum.setCreateDate(new java.util.Date());
		
		
		if(forumDAO.addForum(forum))
		{
			return new ResponseEntity<String>("Forum Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/updateForum",produces=MediaType.TEXT_PLAIN_VALUE)
	
	public ResponseEntity<String> updateBlog(@RequestBody Forum forum)
	{
		Forum forum1=forumDAO.getForum(forum.getForumId());
		forum1.setCreateDate(forum.getCreateDate());
		forum1.setForumContent(forum.getForumContent());
		forum1.setForumName(forum.getForumName());
											
		if(forumDAO.updateForum(forum1))
		{
			return new ResponseEntity<String>("Forum Updated",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/deleteForum/{forumId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteForum(@PathVariable("forumId")int forumId)
	{
		Forum forum=(Forum)forumDAO.getForum(forumId);
		
		if(forumDAO.deleteForum(forum))
		{
			return new ResponseEntity<String>("Forum Deleted",HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/approveForum/{forumId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> approveForum(@PathVariable("forumId")int forumId)
	{
		Forum forum=(Forum)forumDAO.getForum(forumId);
		if(forumDAO.approveForum(forum))
		{
			return new ResponseEntity<String>("Forum Approved",HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);				
	}
	
	@GetMapping(value="/rejectForum/{forumId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> rejectBlog(@PathVariable("forumId")int forumId)
	{
		Forum forum=(Forum)forumDAO.getForum(forumId);
		
		if(forumDAO.rejectForum(forum))
		{
			return new ResponseEntity<String>("Forum Rejected",HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);				
	}
}